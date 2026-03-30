package com.eDecree.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.spi.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eDecree.model.ActionResponse;
import com.eDecree.model.ApplicationTypes;
import com.eDecree.model.CaseFileDetail;
import com.eDecree.model.CaseLkoToAldHistory;
import com.eDecree.model.CaseType;
import com.eDecree.model.DownloadFile;
import com.eDecree.model.DownloadModel;
import com.eDecree.model.DownloadReport;
import com.eDecree.model.ImpugnedOrder;
import com.eDecree.model.IndexField;
import com.eDecree.model.Judge;
import com.eDecree.model.Lookup;
import com.eDecree.model.MetaData;
import com.eDecree.model.Notes;
import com.eDecree.model.OrderReport;
import com.eDecree.model.Petitioner;
import com.eDecree.model.PetitionerCounsel;
import com.eDecree.model.RegularToDefective;
import com.eDecree.model.Respondent;
import com.eDecree.model.RespondentCounsel;
import com.eDecree.model.SubDocument;
import com.eDecree.model.User;
import com.eDecree.service.ApplicationTypesService;
import com.eDecree.service.CaseFileDetailService;
import com.eDecree.service.CasetypeService;
import com.eDecree.service.CourtMasterService;
import com.eDecree.service.DownloadFileService;
import com.eDecree.service.LookupService;
import com.eDecree.service.MasterService;
import com.eDecree.service.OrderReportService;
import com.eDecree.service.SubDocumentService;
import com.eDecree.service.UserRoleService;
import com.eDecree.service.UserService;
import com.eDecree.utility.GlobalFunction;
import com.eDecree.utility.PDFCreator;
import com.eDecree.utility.PDFMerger;
import com.efiling.model.EfilingCaseFileDetail;
import com.itextpdf.text.pdf.PdfReader;
import com.lowagie.text.DocumentException;

@Controller
@RequestMapping("/lkocasefile")
public class LKOCaseFileController {

	@Autowired
	ServletContext context;

	@Autowired
	private LookupService lookupService;

	@Autowired
	private MasterService masterService;

	@Autowired
	private CaseFileDetailService caseFileDetailService;
	

	@Autowired
	private OrderReportService orderReportService;
	
	@Autowired
	private SubDocumentService subDocumentService;

	@Autowired
	private CasetypeService casetypeService;

	@Autowired
	private DownloadFileService downloadService;
	
	@Autowired
	private UserService usermaster;

	@Autowired
	private ApplicationTypesService applicationTypesservice;
	
	@Autowired
	private CourtMasterService courtMasterService;
	
	private GlobalFunction globalfunction;

	
	public LKOCaseFileController() {
		globalfunction = new GlobalFunction();
		
	}

	@RequestMapping(value = "/case_lko_to_ald", method = RequestMethod.GET)
	public String lko_to_ald() {

		return "/casefile/case_lko_to_ald";
	}
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String Manage() {

		return "/casefile/manage";
	}

	@RequestMapping(value = "/download_manage", method = RequestMethod.GET)
	public String download_manage() {

		return "/casefile/download_manage";
	}

	@RequestMapping(value = "/viewdetaillko/{id}", method = RequestMethod.GET)
	public String viewDetail(@PathVariable("id") Long docId, Model model) {
		model.addAttribute("doc_id", docId);
		return "/casefile/viewdetaillko";
	}

	@RequestMapping(value = "/downloadlistLKO/{id}", method = RequestMethod.GET)
	public String downloadlistlko(@PathVariable("id") Long docId, Model model) {
		model.addAttribute("doc_id", docId);
		return "/casefile/downloadlistlko";
	}

	@RequestMapping(value = "/showfile/{id}", method = RequestMethod.GET)
	public String showfile(@PathVariable("id") Long docId, Model model) {
		SubDocument subDocument = subDocumentService.getByPKLKO(docId);
		String returnview = "/casefile/showfile";
		if (subDocument == null) {
			returnview = "/casefile/notfound";
		} else {

			CaseFileDetail caseFileDetail = caseFileDetailService.getCaseFileDetailLKO(subDocument.getSd_fd_mid());
			model.addAttribute("document_name",subDocument.getSd_document_name());
			System.out.println("filename=" + subDocument.getSd_document_name());
			Lookup lookupRepo = lookupService.getLookUpObject("REPOSITORYPATHLKO");
			String srcPath = lookupRepo.getLk_longname() + File.separator
					+ caseFileDetail.getCaseType().getCt_label()
					+ File.separator + subDocument.getIndexField().getIf_name()
					+ File.separator + subDocument.getSd_document_name()
					+ ".pdf";

			File source = new File(srcPath);

			String uploadPath = context.getRealPath("");
			File dest = new File(uploadPath + File.separator + "uploads"
					+ File.separator + subDocument.getSd_document_name()
					+ ".pdf");

			try {
				FileUtils.copyFile(source, dest);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return returnview;
	}

	@RequestMapping(value = "/getLKOCaseFileList", method = RequestMethod.POST)
	public @ResponseBody String getCaseListlko(
			@RequestBody CaseFileDetail casefile, HttpSession session) {
		String jsonData = null;
		ActionResponse<CaseFileDetail> response = new ActionResponse<>();

		//List<CaseFileDetail> casefileList = LKOcaseFileDetailService.getCaseFilesListLKO(casefile);
		List<CaseFileDetail> casefileList = caseFileDetailService.getCaseFilesListLKO(casefile);
		response.setResponse("TRUE");
		response.setModelList(casefileList);

		jsonData = globalfunction.convert_to_json(response);

		return jsonData;
	}
	
	/*@RequestMapping(value = "/getLKOCaseFileList", method = RequestMethod.POST)
	public @ResponseBody String getCaseLists(
			@RequestBody CaseFileDetail casefile, HttpSession session) {
		
		String jsonData = null;
		ActionResponse<CaseFileDetail> response = new ActionResponse<>();

		CaseFileDetail casefileList = LKOcaseFileDetailService.getCaseFilelko(casefile);
		if(casefileList!=null) {
		response.setResponse("TRUE");
		}
		else {
			response.setResponse("FALSE");
		}
		response.setModelData(casefileList);
		

		jsonData = globalfunction.convert_to_json(response);

		return jsonData;
	} */

	@RequestMapping(value = "/uploadJudgement", method = RequestMethod.POST)
	public @ResponseBody String uploadJudgement(
			MultipartHttpServletRequest request, HttpSession session)
			throws DocumentException {
		ActionResponse<SubDocument> response = new ActionResponse();
		User u = (User) session.getAttribute("USER");
		String jsonData = "";
		Lookup lookup = lookupService.getLookUpObject("REPOSITORYPATH");
		Long caseFileId = Long.parseLong(request.getParameter("sd_fd_mid"), 10);
		Long indexFieldId = Long.parseLong(request.getParameter("if_id"), 10);
		Integer at_id = Integer.parseInt(request.getParameter("at_id"), 10);
		String ord_remark = request.getParameter("ord_remark");
		String order_date = request.getParameter("sd_submitted_date");
		String consignmentsno =request.getParameter("ord_consignment_no");
		Integer sd_document_no = null;
		Integer sd_document_year = null;
		try {
			sd_document_no = Integer.parseInt(request.getParameter("sd_document_no"));
			sd_document_year = Integer.parseInt(request.getParameter("sd_document_year"));
		} catch (NumberFormatException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		Date orderDate = new Date();

		try {
			orderDate = new SimpleDateFormat("yyyy-MM-dd").parse(order_date);

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		MultipartFile mpf = null;
		Iterator<String> itr = request.getFileNames();
		String newfilepath = "";
		CaseFileDetail caseFileDetail = caseFileDetailService.getCaseFileDetail(caseFileId);
		IndexField indexField = masterService.getIndexField(indexFieldId);
		Integer count = subDocumentService.getCount(caseFileId);
		count = count + 1;
		while (itr.hasNext()) {
			mpf = request.getFile(itr.next());
			String filename = caseFileDetail.getFd_document_name() + "_"
					+ indexField.getIf_type_code() + "_" + count;
			newfilepath = lookup.getLk_longname() + File.separator
					+ caseFileDetail.getCaseType().getCt_label()
					+ File.separator + indexField.getIf_name() + File.separator
					+ filename + ".pdf";
			SubDocument subDocument = new SubDocument();
			subDocument.setSd_cr_by(u.getUm_id());
			subDocument.setSd_cr_date(new Date());
			subDocument.setSd_fd_mid(caseFileId);
			subDocument.setSd_if_mid(indexFieldId);
			subDocument.setSd_version(1);
			subDocument.setSd_document_name(filename);
			subDocument.setSd_document_id(at_id);
			subDocument.setSd_submitted_date(orderDate);
			subDocument.setSd_rec_status(1);
			subDocument.setSd_minor_sequence(count);
			subDocument.setSd_document_no(sd_document_no);
			subDocument.setSd_document_year(sd_document_year);
			try {
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(
						newfilepath));
				File source = new File(newfilepath);
				PdfReader reader = new PdfReader(source.getAbsolutePath());
				Integer no_of_pages = reader.getNumberOfPages();
				subDocument.setSd_no_of_pages(no_of_pages);

				subDocument = subDocumentService.save(subDocument);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (at_id == 100003)
			{
				OrderReport or = new OrderReport();
				if (orderDate!=null)
				{	
					or.setOrd_created(orderDate);
				}
				else
				{
					or.setOrd_created(new Date());
				}
				or.setOrd_remark(ord_remark);
				or.setOrd_fd_mid(caseFileId);
				or.setOrd_sd_mid(subDocument.getSd_id());
				or.setOrd_created_by(u.getUm_id());
				int sts = 1;
				or.setOrd_rec_status(sts);
				or.setOrd_remark(ord_remark);
				or.setOrd_submitted_date(new Date());
				if(consignmentsno!=null)
				{
					or.setOrd_consignment_no(consignmentsno.toUpperCase());
				}
				orderReportService.save(or);
			}
			
		}

		response.setResponse("TRUE");
		jsonData = globalfunction.convert_to_json(response);
		return jsonData;
	}

	@RequestMapping(value = "/addreportdata", method = RequestMethod.POST)
	public @ResponseBody String addReportData(HttpServletRequest request,
			HttpSession session) throws DocumentException {
		ActionResponse<SubDocument> response = new ActionResponse();
		User u = (User) session.getAttribute("USER");
		String jsonData = "";
		Long caseFileId = Long.parseLong(request.getParameter("sd_fd_mid"), 10);
		Long indexFieldId = 39L;

		String ord_remark = request.getParameter("ord_remark");
		System.out.println("ord_remark----"+ord_remark);
		String date = request.getParameter("sd_submitted_date");
		String consigmtno =request.getParameter("ord_consignment_no").toUpperCase();
		Date d1 =null;
		try {
			d1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (indexFieldId.longValue() == 39L) {
			OrderReport or = new OrderReport();
			if (d1!=null)
			{	
				or.setOrd_created(d1);
			}
			else
			{	
				Date d2 = new Date();                          
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");          
	            try {
					d2 = dateFormat.parse(d2.toString());
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
				
				or.setOrd_created(d2);
			}
			or.setOrd_fd_mid(caseFileId);
			or.setOrd_created_by(u.getUm_id());
			or.setOrd_rec_status(1);
			or.setOrd_submitted_date(new Date());
			if(consigmtno!=null && !consigmtno.equals("UNDEFINED"))
			{
				or.setOrd_consignment_no(consigmtno);
			}
			if(ord_remark.equals("UNDEFINED"))
			{}
			else
			{
				or.setOrd_remark(ord_remark);
			}
			orderReportService.save(or);

		}
		response.setResponse("TRUE");
		jsonData = globalfunction.convert_to_json(response);
		return jsonData;
	}

	@RequestMapping(value = "/downloadfiles/{id}", method = RequestMethod.GET)
	public void downloadCaseFile(@PathVariable("id") Long caseFileId,
			HttpServletResponse response, HttpSession session) {
		List<SubDocument> subDocuments = subDocumentService
				.getAllSubDocuments(caseFileId);
		Lookup lookupRepo = lookupService.getLookUpObject("REPOSITORYPATH");
		Lookup lookUpZip = lookupService.getLookUpObject("ZIP_DOWNLOAD_PATH");

		CaseFileDetail caseFileDetail = caseFileDetailService
				.getCaseFileDetail(caseFileId);
		String dest_folder = lookUpZip.getLk_longname() + File.separator
				+ caseFileDetail.getCaseType().getCt_label()
				+ caseFileDetail.getFd_case_no()
				+ caseFileDetail.getFd_case_year();
		File destFolder = new File(dest_folder);

		if (!destFolder.exists()) {
			destFolder.mkdir();
		}
		for (SubDocument subDocument : subDocuments) {
			String srcPath = lookupRepo.getLk_longname() + File.separator
					+ caseFileDetail.getCaseType().getCt_label()
					+ File.separator + subDocument.getIndexField().getIf_name()
					+ File.separator + subDocument.getSd_document_name()
					+ ".pdf";
			String destPath = dest_folder + File.separator
					+ subDocument.getSd_document_name() + ".pdf";
			File source = new File(srcPath);
			File dest = new File(destPath);
			try {
				FileUtils.copyFile(source.getAbsoluteFile(),
						dest.getAbsoluteFile());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			globalfunction.zipFolder(destFolder.getAbsolutePath(),
					destFolder.getAbsolutePath() + ".zip");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// to download file
		try {
			response.setContentType("application/zip");
			PrintWriter out = response.getWriter();
			String filename = destFolder.getName() + ".zip";
			String filepath = destFolder.getAbsolutePath() + ".zip";
			File zipFile = new File(dest_folder + ".zip");

			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ filename + "\"");

			FileInputStream fileInputStream = new FileInputStream(filepath);

			int i;
			while ((i = fileInputStream.read()) != -1) {
				out.write(i);
			}
			fileInputStream.close();
			out.close();
			FileUtils.deleteDirectory(destFolder);
			zipFile.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/viewordersLKO/{id}", method = RequestMethod.GET)
	public String viewOrders(@PathVariable("id") Long docId, Model model) {
		Long metafieldId = 39L;
		List<SubDocument> subDocuments = subDocumentService.getByFieldLKO(docId,metafieldId);
		String returnview = "/casefile/viewLKO";
		if (subDocuments.size() == 0) {
			returnview = "/casefile/notfound";
		} else {
			String uploadPath = context.getRealPath("");
			CaseFileDetail caseFileDetail = caseFileDetailService.getCaseFileDetailLKO(docId);
			String filename = "ORDS"
					+ caseFileDetail.getCaseType().getCt_label()
					+ caseFileDetail.getFd_case_no()
					+ caseFileDetail.getFd_case_year();
			String orderswithbk = "Orders"
					+ caseFileDetail.getCaseType().getCt_label()
					+ caseFileDetail.getFd_case_no()
					+ caseFileDetail.getFd_case_year();
			String outputFilePath = uploadPath + File.separator + "uploads"
					+ File.separator + filename;
			String bookmarkFile = uploadPath + File.separator + "uploads"
					+ File.separator + orderswithbk;
			String basePath = "";
			Lookup lookupRepo = lookupService.getLookUpObjectLKO("REPOSITORYPATHLKO");
			basePath = lookupRepo.getLk_longname() + File.separator
					+ caseFileDetail.getCaseType().getCt_label();

			List<InputStream> list = new ArrayList<InputStream>();
			Map<String, Integer> map = new HashMap<String, Integer>();
			Integer pagecount = 0;
			int i = 0;
			try {
				if (subDocuments.size() > 1) {
					OutputStream out = new FileOutputStream(new File(outputFilePath + ".pdf"));
					for (SubDocument subDocument : subDocuments) {
						String srcPath = basePath + File.separator
								+ subDocument.getIndexField().getIf_name()
								+ File.separator
								+ subDocument.getSd_document_name() + ".pdf";
						String label = subDocument.getIndexField()
								.getIf_label();
						SimpleDateFormat formatter = new SimpleDateFormat(
								"dd-MM-yyyy");
						if (subDocument.getSd_submitted_date() != null) {
							String date = formatter.format(subDocument
									.getSd_submitted_date());
							label = label + "_" + date;
						}
						if (i == 0) {
							String lastfilePath = srcPath;
							PdfReader reader = new PdfReader(lastfilePath);
							map.put(label, 1);
							reader.close();
						}
						if (i > 0) {

							String lastfilePath = basePath
									+ File.separator
									+ subDocuments.get(i - 1).getIndexField()
											.getIf_name()
									+ File.separator
									+ subDocuments.get(i - 1)
											.getSd_document_name() + ".pdf";
							PdfReader reader = new PdfReader(lastfilePath);
							pagecount += reader.getNumberOfPages();
							map.put(label, pagecount + 1);
							reader.close();
						}
						list.add(new FileInputStream(new File(srcPath)));
						i++;
					}
					PDFMerger.doMerge(list, out);

					// globalfunction.doBookmark(outputFilePath+".pdf",bookmarkFile+".pdf",map);
					// File f=new File(outputFilePath+".pdf");
					// f.delete();
				} else {
					String srcPath = basePath + File.separator
							+ subDocuments.get(0).getIndexField().getIf_name()
							+ File.separator
							+ subDocuments.get(0).getSd_document_name();
					File src = new File(srcPath + ".pdf");
					File dest = new File(bookmarkFile + ".pdf");
					FileUtils.copyFile(src, dest);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			model.addAttribute("document_name", filename);
			model.addAttribute("doc_id", docId);
			model.addAttribute("isApplication", null);
			model.addAttribute("isImpugnedOrder", null);
		}

		return returnview;
	}

	@RequestMapping(value = "/viewLKO/{id}", method = RequestMethod.GET)
	public String viewLKO(@PathVariable("id") Long docId, Model model) {

		SubDocument subDocument = subDocumentService.getPetitionSubDocumentLKO(docId, 1);
		String returnview = "/casefile/viewLKO";
		if (subDocument == null) {
			returnview = "/casefile/notfound";
		} else {

			CaseFileDetail caseFileDetail = caseFileDetailService.getCaseFileDetailLKO(docId);
			model.addAttribute("document_name",subDocument.getSd_document_name());
			model.addAttribute("doc_id", docId);
			model.addAttribute("isApplication", null);
			model.addAttribute("isImpugnedOrder", null);
			Lookup lookupRepo = lookupService.getLookUpObjectLKO("REPOSITORYPATHLKO");
			String srcPath = lookupRepo.getLk_longname() + File.separator
					+ caseFileDetail.getCaseType().getCt_label()
					+ File.separator + subDocument.getIndexField().getIf_name()
					+ File.separator + subDocument.getSd_document_name()
					+ ".pdf";

			File source = new File(srcPath);

			String uploadPath = context.getRealPath("");
			File dest = new File(uploadPath + File.separator + "uploads"
					+ File.separator + subDocument.getSd_document_name()
					+ ".pdf");

			try {
				FileUtils.copyFile(source, dest);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return returnview;
	}
	
	
	
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long docId, Model model) {

		SubDocument subDocument = subDocumentService.getPetitionSubDocument(
				docId, 1);
		String returnview = "/casefile/view";
		if (subDocument == null) {
			returnview = "/casefile/notfound";
		} else {

			CaseFileDetail caseFileDetail = caseFileDetailService
					.getCaseFileDetail(docId);
			model.addAttribute("document_name",
					subDocument.getSd_document_name());
			model.addAttribute("doc_id", docId);
			model.addAttribute("isApplication", null);
			model.addAttribute("isImpugnedOrder", null);
			Lookup lookupRepo = lookupService.getLookUpObject("REPOSITORYPATH");
			String srcPath = lookupRepo.getLk_longname() + File.separator
					+ caseFileDetail.getCaseType().getCt_label()
					+ File.separator + subDocument.getIndexField().getIf_name()
					+ File.separator + subDocument.getSd_document_name()
					+ ".pdf";

			File source = new File(srcPath);

			String uploadPath = context.getRealPath("");
			File dest = new File(uploadPath + File.separator + "uploads"
					+ File.separator + subDocument.getSd_document_name()
					+ ".pdf");

			try {
				FileUtils.copyFile(source, dest);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return returnview;
	}

	@RequestMapping(value = "/impugnedorderLKO/{id}", method = RequestMethod.GET)
	public String impugnedorder(@PathVariable("id") Long ioId, Model model) {

		ImpugnedOrder io = caseFileDetailService.getImpugnedOrderLKO2(ioId);
		Integer caseYear = null;
		try {
			caseYear = Integer.parseInt(io.getIo_case_year());
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		CaseFileDetail cfd = caseFileDetailService.getCaseFileDetailLKO(io.getIo_hc_case_type(), io.getIo_case_no(), caseYear);
		Long docId = io.getIo_fd_mid();

		SubDocument subDocument = subDocumentService.getPetitionSubDocumentLKO(cfd.getFd_id(), 1);
		String returnview = "/casefile/viewLKO";
		if (subDocument == null) {
			returnview = "/casefile/notfound";
		} else {

			CaseFileDetail caseFileDetail = caseFileDetailService.getCaseFileDetailLKO(cfd.getFd_id());
			model.addAttribute("document_name",
					subDocument.getSd_document_name());
			model.addAttribute("doc_id", docId);
			model.addAttribute("isApplication", null);
			model.addAttribute("isImpugnedOrder", 1);
			model.addAttribute("casetype", cfd.getCaseType().getCt_label());
			model.addAttribute("caseno", cfd.getFd_case_no());
			model.addAttribute("caseyear", cfd.getFd_case_year());
			Lookup lookupRepo = lookupService.getLookUpObjectLKO("REPOSITORYPATHLKO");
			String srcPath = lookupRepo.getLk_longname() + File.separator
					+ caseFileDetail.getCaseType().getCt_label()
					+ File.separator + subDocument.getIndexField().getIf_name()
					+ File.separator + subDocument.getSd_document_name()
					+ ".pdf";

			File source = new File(srcPath);

			String uploadPath = context.getRealPath("");
			File dest = new File(uploadPath + File.separator + "uploads"
					+ File.separator + subDocument.getSd_document_name()
					+ ".pdf");

			try {
				FileUtils.copyFile(source, dest);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return returnview;
		}

	
	@RequestMapping(value = "/subdocumentLKO/{id}", method = RequestMethod.GET)
	public @ResponseBody String subdocument(@PathVariable("id") Long subDocId, Model model) {
		SubDocument subDocument = subDocumentService.getByPKLKO(subDocId);

		String returnview = "/casefile/viewLKO";
		if (subDocument == null) {
			returnview = "/casefile/notfound";
		} else {
			Long docId = subDocument.getSd_fd_mid();
			CaseFileDetail caseFileDetail = caseFileDetailService.getCaseFileDetailLKO(docId);

			model.addAttribute("doc_id", docId);
			model.addAttribute("document_name",subDocument.getSd_document_name());
			model.addAttribute("isApplication", null);
			model.addAttribute("isImpugnedOrder", null);
			Lookup lookupRepo = lookupService.getLookUpObjectLKO("REPOSITORYPATHLKO");
			String srcPath = lookupRepo.getLk_longname() + File.separator
					+ caseFileDetail.getCaseType().getCt_label()
					+ File.separator + subDocument.getIndexField().getIf_name()
					+ File.separator + subDocument.getSd_document_name()
					+ ".pdf";

			File source = new File(srcPath);

			String uploadPath = context.getRealPath("");
			File dest = new File(uploadPath + File.separator + "uploads"
					+ File.separator + subDocument.getSd_document_name()
					+ ".pdf");
			
			System.out.println("destination: "+dest);

			try {
				FileUtils.copyFile(source, dest);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//String response=globalfunction.convert_to_json(returnview);
		
		String jsonData = null;
		ActionResponse<Model> response = new ActionResponse<>();

		response.setResponse("TRUE");
		response.setModelData(model);

		jsonData = globalfunction.convert_to_json(response);

		return jsonData;
	}
	
	@RequestMapping(value = "/subdocumentTabLKO/{id}", method = RequestMethod.GET)
	public String subdocumentTab(@PathVariable("id") Long subDocId, Model model) {
		SubDocument subDocument = subDocumentService.getByPKLKO(subDocId);

		String returnview = "/casefile/viewLKO";
		if (subDocument == null) {
			returnview = "/casefile/notfound";
		} else {
			Long docId = subDocument.getSd_fd_mid();
			CaseFileDetail caseFileDetail = caseFileDetailService.getCaseFileDetailLKO(docId);

			model.addAttribute("doc_id", docId);
			model.addAttribute("document_name",subDocument.getSd_document_name());
			model.addAttribute("isApplication", null);
			model.addAttribute("isImpugnedOrder", null);
			Lookup lookupRepo = lookupService.getLookUpObjectLKO("REPOSITORYPATHLKO");
			String srcPath = lookupRepo.getLk_longname() + File.separator
					+ caseFileDetail.getCaseType().getCt_label()
					+ File.separator + subDocument.getIndexField().getIf_name()
					+ File.separator + subDocument.getSd_document_name()
					+ ".pdf";

			File source = new File(srcPath);

			String uploadPath = context.getRealPath("");
			File dest = new File(uploadPath + File.separator + "uploads"
					+ File.separator + subDocument.getSd_document_name()
					+ ".pdf");
			
			System.out.println("destination: "+dest);

			try {
				FileUtils.copyFile(source, dest);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//String response=globalfunction.convert_to_json(returnview);
	return 	returnview;
	
	}
	

	@RequestMapping(value = "/applicationview", method = RequestMethod.GET)
	public String subdocument(@RequestParam("app_no") Integer app_no,
			@RequestParam("app_year") Integer app_year,
			@RequestParam("case_id") Long case_id, Model model) {
		SubDocument subDocument = subDocumentService.getApplicationByCase(app_no,
				app_year, case_id);
		String returnview = "/casefile/view";
		if (subDocument == null) {
			returnview = "/casefile/notfound";
		} else {
			Long docId = subDocument.getSd_fd_mid();
			CaseFileDetail caseFileDetail = caseFileDetailService
					.getCaseFileDetail(docId);
			Date date = subDocument.getSd_submitted_date();

			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			String submitted_date = "";
			if (date != null) {
				submitted_date = formatter.format(date);
			}

			model.addAttribute("doc_id", docId);
			model.addAttribute("document_name",
					subDocument.getSd_document_name());
			model.addAttribute("isApplication", 1);
			model.addAttribute("isImpugnedOrder", null);
			model.addAttribute("application_type", subDocument
					.getDocumentType().getAt_name());
			model.addAttribute("application_no",
					subDocument.getSd_document_no());
			model.addAttribute("application_year",
					subDocument.getSd_document_year());
			model.addAttribute("submitted_date", submitted_date);
			model.addAttribute("party", subDocument.getSd_party());
			model.addAttribute("name", subDocument.getSd_description());
			model.addAttribute("counsel", subDocument.getSd_counsel());

			Lookup lookupRepo = lookupService.getLookUpObject("REPOSITORYPATH");
			String srcPath = lookupRepo.getLk_longname() + File.separator
					+ caseFileDetail.getCaseType().getCt_label()
					+ File.separator + subDocument.getIndexField().getIf_name()
					+ File.separator + subDocument.getSd_document_name()
					+ ".pdf";

			File source = new File(srcPath);

			String uploadPath = context.getRealPath("");
			File dest = new File(uploadPath + File.separator + "uploads"
					+ File.separator + subDocument.getSd_document_name()
					+ ".pdf");

			try {
				FileUtils.copyFile(source, dest);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return returnview;
	}

	@RequestMapping(value = "/getsubdocumentsLKO/{id}", method = RequestMethod.GET)
	public @ResponseBody String getSubDocumentsLKO(@PathVariable("id") Long fd_id,
			HttpSession session) {
		String jsonData = null;
		ActionResponse<SubDocument> response = new ActionResponse<>();

		List<SubDocument> subDocumentsLKO = subDocumentService.getSubDocumentsLKO(fd_id);
		response.setResponse("TRUE");
		response.setModelList(subDocumentsLKO);

		jsonData = globalfunction.convert_to_json(response);

		return jsonData;
	}

	@RequestMapping(value = "/getcasefiledetailsLKO/{id}", method = RequestMethod.GET)
	public @ResponseBody String getcasefiledetailsLKO(
			@PathVariable("id") Long fd_id, HttpSession session) {
		String jsonData = null;
		ActionResponse<CaseFileDetail> response = new ActionResponse<>();

		CaseFileDetail casefile = caseFileDetailService.getCaseFileDetailLKO(fd_id);

		Petitioner pt =null; 
				pt=caseFileDetailService.getPetitionerBycaseLKO(fd_id);
		Respondent rt =null;
		
		rt=caseFileDetailService.getRespondentBycaseLKO(fd_id);
            
		if(pt!=null && rt!=null){
		casefile.setFirst_petitioner(pt.getPt_name());
		casefile.setFirst_respondent(rt.getRt_name());
		}
		response.setResponse("TRUE");
		response.setModelData(casefile);

		jsonData = globalfunction.convert_to_json(response);

		return jsonData;
	}

	@RequestMapping(value = "/getorderreportsLKO/{id}", method = RequestMethod.GET)
	public @ResponseBody String getorderreports(@PathVariable("id") Long fd_id,
			HttpSession session) {
		String jsonData = null;
		ActionResponse<Object> response = new ActionResponse<>();
		List<Object> orderReportData = orderReportService.getOrderReportsLKO2(fd_id);
		response.setResponse("TRUE");
		response.setModelList(orderReportData);

		jsonData = globalfunction.convert_to_json(response);

		return jsonData;
	}
	

	@RequestMapping(value = "/getmetadata/{id}", method = RequestMethod.GET)
	public @ResponseBody String getMetadata(@PathVariable("id") Long fd_id,
			HttpSession session) {
		String jsonData = null;
		ActionResponse<MetaData> response = new ActionResponse<>();

		List<MetaData> metadata = caseFileDetailService.getMetadata(fd_id);
		response.setResponse("TRUE");
		response.setModelList(metadata);

		jsonData = globalfunction.convert_to_json(response);

		return jsonData;
	}

	@RequestMapping(value = "/viewdocument/{id}", method = RequestMethod.GET)
	public void copysubdocument(@PathVariable("id") Long sd_id,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String jsonData = null;
		/*
		 * ActionResponse<SubDocument> response=new ActionResponse<>();
		 * 
		 * SubDocument subDocument = subDocumentService.getByPK(sd_id);
		 * CaseFileDetail
		 * caseFileDetail=caseFileDetailService.getCaseFileDetail(
		 * subDocument.getSd_fd_mid()); User user=(User)
		 * session.getAttribute("USER");
		 * 
		 * Lookup lookupRepo=lookupService.getLookUpObject("REPOSITORYPATH");
		 * String
		 * srcPath=lookupRepo.getLk_longname()+File.separator+caseFileDetail
		 * .getCaseType
		 * ().getCt_label()+File.separator+subDocument.getIndexField(
		 * ).getIf_name
		 * ()+File.separator+subDocument.getSd_document_name()+".pdf";
		 * 
		 * File source = new File(srcPath);
		 * 
		 * String uploadPath = context.getRealPath(""); File dest = new
		 * File(uploadPath
		 * +File.separator+"uploads"+File.separator+subDocument.getSd_document_name
		 * ()+".pdf");
		 * 
		 * try { FileUtils.copyFile(source, dest); } catch (IOException e) {
		 * e.printStackTrace(); } response.setResponse("TRUE");
		 * response.setData(subDocument.getSd_document_name()+".pdf");
		 * jsonData=globalfunction.convert_to_json(response); return jsonData;
		 */
		SubDocument subDocument = subDocumentService.getByPK(sd_id);
		CaseFileDetail caseFileDetail = caseFileDetailService
				.getCaseFileDetail(subDocument.getSd_fd_mid());

		Lookup lookupRepo = lookupService.getLookUpObject("REPOSITORYPATH");
		String srcPath = lookupRepo.getLk_longname() + File.separator
				+ caseFileDetail.getCaseType().getCt_label() + File.separator
				+ subDocument.getIndexField().getIf_name() + File.separator
				+ subDocument.getSd_document_name() + ".pdf";
		System.out.println("filename=" + srcPath);
		File source = new File(srcPath);
		// response.setContentType("application/pdf");
		// response.setHeader("Content-Disposition", "inline; filename='" +
		// srcPath + "'");
		// response.setContentLength((int) source.length());
		File file = new File(srcPath);
		response.setHeader("content-disposition", "inline");
		response.setContentType("application/pdf");
		response.setContentLength((int) file.length());

		ServletOutputStream out = response.getOutputStream();
		FileInputStream stream = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(stream);
		BufferedOutputStream bos = new BufferedOutputStream(out);

		byte[] buff = new byte[2048];
		int bytesRead;

		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);
		}
		bis.close();
		stream.close();
		bos.close();
		out.close();
	}

	@RequestMapping(value = "/deletesubdocument/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteSubdocument(@PathVariable("id") Long id,
			HttpSession session) {
		ActionResponse<SubDocument> response = new ActionResponse<SubDocument>();
		String jsonData = null;
		User u = (User) session.getAttribute("USER");
		SubDocument sd = subDocumentService.getByPK(id);
		sd.setSd_rec_status(0);
		sd.setSd_mod_date(new Date());
		sd.setSd_mod_by(u.getUm_id());
		subDocumentService.save(sd);

		response.setResponse("TRUE");
		jsonData = globalfunction.convert_to_json(response);

		return jsonData;

	}

	@RequestMapping(value = "/deleteofficereport/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String officereport(@PathVariable("id") Long id, HttpSession session) {
		ActionResponse<OrderReport> response = new ActionResponse<OrderReport>();
		String jsonData = null;
		User u = (User) session.getAttribute("USER");
		OrderReport or = orderReportService.getOrderReport(id);
		or.setOrd_rec_status(0);
		or.setOrd_mod_by(u.getUm_id());
		or.setOrd_mod_date(new Date());
		orderReportService.save(or);
		if (or.getSubDocument() != null) {
			SubDocument sd = subDocumentService.getByPK(or.getSubDocument()
					.getSd_id());
			sd.setSd_rec_status(0);
			sd.setSd_mod_date(new Date());
			sd.setSd_mod_by(u.getUm_id());
			subDocumentService.save(sd);
		}

		response.setResponse("TRUE");
		jsonData = globalfunction.convert_to_json(response);

		return jsonData;

	}

	@RequestMapping(value = "/moveCaseLKOtoALD", method = RequestMethod.POST)
	public @ResponseBody String movaCaseLKOtoALD(HttpServletRequest request,HttpSession session) throws DocumentException {
		ActionResponse<CaseFileDetail> response = new ActionResponse();
		
		CaseFileDetail cfdResult = null;
		SubDocument subdocumentResult = null;
		Petitioner pResult = null;
		Respondent rResult = null;
		PetitionerCounsel pcResult = null;
		RespondentCounsel rcResult = null;
		OrderReport orResult = null;
		MetaData mdResult = null;
		ImpugnedOrder ioResult = null;
		EfilingCaseFileDetail ecfdResult = null;
		
		
		User u = (User) session.getAttribute("USER");
		String jsonData = "";
		Long caseFilefdIdlko = Long.parseLong(request.getParameter("fd_id"), 10);
		Long newCaseTypeId = Long.parseLong(request.getParameter("new_case_type"), 10);
		String newCaseNo = request.getParameter("new_case_no");
		String newCaseYear = request.getParameter("new_case_year");
		CaseFileDetail cfdlko = caseFileDetailService.getCaseFileDetailLKO(caseFilefdIdlko);
		
		CaseFileDetail cfald = new CaseFileDetail();
		cfald.setFd_case_type(newCaseTypeId);
		cfald.setFd_case_no(newCaseNo);
		cfald.setFd_case_year(Integer.valueOf(newCaseYear));
		
		List<SubDocument> subdocuments = subDocumentService.getAllSubDocumentsLKO(caseFilefdIdlko);
		Lookup lookupRepolko = lookupService.getLookUpObjectLKO("REPOSITORYPATHLKO");
		Lookup lookupRepo = lookupService.getLookUpObject("REPOSITORYPATH");
		System.out.println("Allahabad DMS file path --"+lookupRepolko);
		System.out.println("Lucknow DMS file path --"+lookupRepo);
		CaseType newCaseType = casetypeService.getById(newCaseTypeId);
		boolean copyflag = false;
		Integer newYear = null;
		
		CaseFileDetail casefileListald = caseFileDetailService.getCaseFile(cfald);
		Long Casefdid =0l;
		if(casefileListald==null)
		{
			cfald.setFd_document_name(newCaseType.getCt_label()+newCaseNo+newCaseYear);
			cfald.setFd_file_source("LKO");
			cfald.setFd_rec_status(cfdlko.getFd_rec_status());
			cfald.setFd_stage_lid(cfdlko.getFd_stage_lid());
			cfald.setFd_cr_by(cfdlko.getFd_cr_by());
			cfald.setFd_cr_date(cfdlko.getFd_cr_date());
			cfald.setFd_mod_by(cfdlko.getFd_mod_by());
			cfald.setFd_mod_date(cfdlko.getFd_mod_date());
			cfald.setFd_category_code(cfdlko.getFd_category_code());
			cfald.setFd_district(cfdlko.getFd_district());
			cfald.setFd_disposal_date(cfdlko.getFd_disposal_date());
			cfald.setFd_act_section(cfdlko.getFd_act_section());
			cfald.setFd_keywords(cfdlko.getFd_keywords());
			cfald.setFd_bench_type(cfdlko.getFd_bench_type());
			cfald.setFd_bench_code(8l);
			cfdResult = caseFileDetailService.save(cfald);
			
			Casefdid = cfdResult.getFd_id();
			
			CaseLkoToAldHistory lkotoaldh = new  CaseLkoToAldHistory();
			lkotoaldh.setOld_fd_mid(cfdlko.getFd_id());
			lkotoaldh.setOld_case_type(cfdlko.getFd_case_type().intValue());
			lkotoaldh.setOld_case_no(cfdlko.getFd_case_no());
			lkotoaldh.setOld_case_year(cfdlko.getFd_case_year().intValue());
			lkotoaldh.setNew_fd_mid(Casefdid);
			lkotoaldh.setNew_case_type(newCaseTypeId.intValue());
			lkotoaldh.setNew_case_no(newCaseNo);
			lkotoaldh.setNew_case_year(Integer.valueOf(newCaseYear));
			lkotoaldh.setUser_cr_by(u.getUm_id());
			lkotoaldh.setLkotoald_mod_date(new Date());
			caseFileDetailService.saveCLKOtoALD(lkotoaldh);
			
			List<Petitioner> petlist =cfdlko.getPetitioners();
			for (Petitioner pet : petlist) 
			{
				pet.setPt_fd_mid(Casefdid);
				pet.setPt_id(null);
				pResult = caseFileDetailService.save(pet);
			}
			List<Respondent> resplist =cfdlko.getRespondents();
			for (Respondent resp : resplist) 
			{
				resp.setRt_fd_mid(Casefdid);
				resp.setRt_id(null);
				rResult = caseFileDetailService.save(resp);
			}
			List<PetitionerCounsel> pc = cfdlko.getpCounsels();
			for (PetitionerCounsel petitionerCounsel : pc) 
			{
					petitionerCounsel.setPc_fd_mid(Casefdid);
					petitionerCounsel.setPc_id(null);
					pcResult = caseFileDetailService.save(petitionerCounsel);
			}
			List<RespondentCounsel> rc = cfdlko.getrCounsels();
			for (RespondentCounsel respondentCounsel : rc) 
			{
					respondentCounsel.setRc_fd_mid(Casefdid);
					respondentCounsel.setRc_id(null);
					rcResult = caseFileDetailService.save(respondentCounsel);
			}
			
			List <OrderReport>orlko = orderReportService.getOrderReportsLKO(caseFilefdIdlko);
			for (OrderReport orderReport : orlko) 
			{
				orderReport.setOrd_fd_mid(Casefdid);
				orderReport.setOrd_id(null);
				orResult = orderReportService.save(orderReport);
			}
			
			List<MetaData> metadatalist=caseFileDetailService.getMetadataLKO(caseFilefdIdlko);
			for (MetaData metaData : metadatalist) {
				metaData.setMd_fd_mid(Casefdid);
				metaData.setMd_id(null);
				mdResult = caseFileDetailService.save(metaData);
			}
			
			List<ImpugnedOrder> imo= caseFileDetailService.getImpugnedOrderLKO(caseFilefdIdlko);
			for (ImpugnedOrder impugnedOrder : imo) {
				impugnedOrder.setIo_fd_mid(Casefdid);
				impugnedOrder.setIo_id(null);
				ioResult = caseFileDetailService.save(impugnedOrder);
			}
			
			for (SubDocument subDocument : subdocuments) 
			{	
				@SuppressWarnings("null")
				Integer count = subDocumentService.getCount(Casefdid);
				count = count + 1;
				
				newYear = new Integer(newCaseYear);
				String srcPath = lookupRepolko.getLk_longname() + File.separator
						+ cfdlko.getCaseType().getCt_label() + File.separator
						+ subDocument.getIndexField().getIf_name() + File.separator
						+ subDocument.getSd_document_name() + ".pdf";
				String filename = subDocument.getSd_document_name();
				filename = filename.replace(cfdlko.getCaseType().getCt_label(),newCaseType.getCt_label());
				filename = filename.replace(cfdlko.getFd_case_no().toString(),newCaseNo);
				filename = filename.replace(cfdlko.getFd_case_year().toString(),newCaseYear);
				
				if(subDocument.getSd_if_mid()!=1)
					{
						int index = filename.lastIndexOf("_");
						filename = filename.substring(0, index+1);
					}
			
				String newFilename = filename+count;
				String destPath = lookupRepo.getLk_longname() + File.separator
						+ newCaseType.getCt_label() + File.separator
						+ subDocument.getIndexField().getIf_name() + File.separator
						+ filename +count+ ".pdf";
				
				subDocument.setSd_fd_mid(Casefdid);
				subDocument.setSd_id(null);
				subDocument.setSd_document_name(newFilename);
				File source = new File(srcPath);
				File dest = new File(destPath);
				try {
					if (!dest.exists()) {
						// if copy of file does not exist in new case type folder
						FileUtils.copyFile(source, dest);
						Integer documentd_id = subDocument.getSd_document_id();
						if(documentd_id!=null)
						{
							System.out.println("LKO-documentd_id=="+documentd_id);
							Integer at_id =applicationTypesservice.getApplicationTypes(documentd_id);
							
							if(at_id!=null)
							{
								subDocument.setSd_document_id(at_id);
								subDocument.getDocumentType().setAt_id(at_id);
								//subDocument.setSd_document_no(subDocument.getSd_document_no());
								System.out.println("ALD at_id=="+at_id);
								subdocumentResult = subDocumentService.save(subDocument);
							}
							else
							{
								// for application at_id is mismatch
								if(documentd_id==47 || documentd_id==27)
								{
									subDocument.setSd_document_id(47);
									subDocument.setSd_if_mid(14l);
									subDocument.getDocumentType().setAt_id(47);
									subdocumentResult = subDocumentService.save(subDocument);
								}
								else
								{
								subDocument.setSd_document_id(99);
								subDocument.setSd_if_mid(14l);
								subDocument.getDocumentType().setAt_id(99);
								//subDocument.setSd_document_no(subDocument.getSd_document_no());
								subdocumentResult = subDocumentService.save(subDocument);
								}
							}
						}
						else
						{
							subdocumentResult = subDocumentService.save(subDocument);
						}
						
						copyflag = true;
					}
				} catch (IOException e) {

					e.printStackTrace();
				}
				
				
			}
			
			
		}
		
		
		if (copyflag) {
			if (cfdlko.getFd_file_source().equals("O")) 
			{
				// insert into onlinecasefiling 
				//EfilingCaseFileDetail ecfd = caseFileDetailService.getEfilingCaseFileDetail(cfd.getFd_case_type(),cfd.getFd_case_no(), cfd.getFd_case_year());
				EfilingCaseFileDetail ecfd = new EfilingCaseFileDetail();
				ecfd.setFd_case_type(newCaseTypeId);
				ecfd.setFd_case_no(newCaseNo);
				ecfd.setFd_case_year(newYear);
				ecfd.setFd_first_petitioner(caseFileDetailService.getPetitionerBycaseLKO(caseFilefdIdlko).getPt_name());
				ecfd.setFd_first_respondent(caseFileDetailService.getRespondentBycaseLKO(caseFilefdIdlko).getRt_name());
				ecfd.setFd_cr_date(new Date());
				ecfd.setFd_rec_status(1);
				//ecfdResult = caseFileDetailService.saveCaseFileEfiling(ecfd);
			}

			
			
			//response.setResponse("TRUE");
		} 
		
		
		
		if(cfdResult==null && subdocumentResult==null && pResult==null && rResult==null && 
				pcResult==null && rcResult==null && orResult==null && mdResult==null && ioResult==null && ecfdResult==null)
		{
			String message = " Some data mismatch while case tarsfering LKO to ALD";
			response.setResponse("FALSE");
			response.setData(message);
		}
		else
		{
			String message = "Successully Case Move Lucknow to Allahabad";
			response.setData(message);
			response.setResponse("TRUE");
		}
		
		jsonData = globalfunction.convert_to_json(response);
		return jsonData;
	}

	@RequestMapping(value = "/deletepetitioner/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deletepetitioner(@PathVariable("id") Long id,
			HttpSession session) {
		ActionResponse<Petitioner> response = new ActionResponse<Petitioner>();
		String jsonData = null;
		User u = (User) session.getAttribute("USER");
		Petitioner pet = caseFileDetailService.getPetitioner(id);
		pet.setPt_rec_status(0);
		pet.setPt_mod_by(u.getUm_id());
		pet.setPt_mod_date(new Date());
		caseFileDetailService.save(pet);

		response.setResponse("TRUE");
		jsonData = globalfunction.convert_to_json(response);

		return jsonData;

	}

	@RequestMapping(value = "/deleterespondent/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deleterespondent(@PathVariable("id") Long id,
			HttpSession session) {
		ActionResponse<Respondent> response = new ActionResponse<Respondent>();
		String jsonData = null;
		User u = (User) session.getAttribute("USER");
		Respondent res = caseFileDetailService.getRespondent(id);
		res.setRt_rec_status(0);
		res.setRt_mod_by(u.getUm_id());
		res.setRt_mod_date(new Date());
		caseFileDetailService.save(res);

		response.setResponse("TRUE");
		jsonData = globalfunction.convert_to_json(response);

		return jsonData;

	}

	@RequestMapping(value = "/deletepcounsel/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deletepcounsel(@PathVariable("id") Long id,
			HttpSession session) {
		ActionResponse<PetitionerCounsel> response = new ActionResponse<PetitionerCounsel>();
		String jsonData = null;
		User u = (User) session.getAttribute("USER");
		PetitionerCounsel pc = caseFileDetailService.getPetitionerCounsel(id);
		pc.setPc_rec_status(0);
		pc.setPc_mod_by(u.getUm_id());
		pc.setPc_mod_date(new Date());
		caseFileDetailService.save(pc);

		response.setResponse("TRUE");
		jsonData = globalfunction.convert_to_json(response);

		return jsonData;

	}

	@RequestMapping(value = "/deletercounsel/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deletercounsel(@PathVariable("id") Long id,
			HttpSession session) {
		ActionResponse<RespondentCounsel> response = new ActionResponse<RespondentCounsel>();
		String jsonData = null;
		User u = (User) session.getAttribute("USER");
		RespondentCounsel rc = caseFileDetailService.getRespondentCounsel(id);
		rc.setRc_rec_status(0);
		caseFileDetailService.save(rc);

		response.setResponse("TRUE");
		jsonData = globalfunction.convert_to_json(response);

		return jsonData;

	}

	@RequestMapping(value = "/uploadpetition", method = RequestMethod.POST)
	public @ResponseBody String uploadPetition(
			MultipartHttpServletRequest request, HttpSession session)
			throws DocumentException {
		ActionResponse<SubDocument> response = new ActionResponse();
		response.setResponse("FALSE");
		User u = (User) session.getAttribute("USER");
		String jsonData = "";
		Lookup lookup = lookupService.getLookUpObject("REPOSITORYPATH");
		Long caseFileId = Long.parseLong(request.getParameter("fd_id"), 10);
		SubDocument sd = subDocumentService.getPetitionSubDocument(caseFileId,
				1);
		if (sd != null) {
			sd.setSd_rec_status(0);
			sd = subDocumentService.save(sd);
		} else {
			sd = subDocumentService.getPetitionSubDocument(caseFileId, 0);
		}

		MultipartFile mpf = null;
		Iterator<String> itr = request.getFileNames();
		String newfilepath = "";
		CaseFileDetail caseFileDetail = caseFileDetailService
				.getCaseFileDetail(caseFileId);
		IndexField indexField = masterService.getIndexField(1L);
		Integer count = subDocumentService.getCount(caseFileId);
		count = count + 1;

		while (itr.hasNext()) {
			mpf = request.getFile(itr.next());
			String filename = caseFileDetail.getFd_document_name() + "_"
					+ indexField.getIf_type_code() + "_" + count;
			newfilepath = lookup.getLk_longname() + File.separator
					+ caseFileDetail.getCaseType().getCt_label()
					+ File.separator + indexField.getIf_name() + File.separator
					+ filename + ".pdf";
			SubDocument subDocument = new SubDocument();
			subDocument.setSd_cr_by(u.getUm_id());
			subDocument.setSd_cr_date(new Date());
			subDocument.setSd_fd_mid(caseFileId);
			subDocument.setSd_if_mid(1L);
			subDocument.setSd_document_name(filename);
			subDocument.setSd_document_id(sd.getSd_document_id());
			subDocument.setSd_submitted_date(sd.getSd_submitted_date());
			subDocument.setSd_rec_status(1);
			subDocument.setSd_document_no(sd.getSd_document_no());
			subDocument.setSd_document_year(sd.getSd_document_year());
			subDocument.setSd_counsel(sd.getSd_counsel());
			subDocument.setSd_description(sd.getSd_description());
			subDocument.setSd_party(sd.getSd_party());
			try {
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(
						newfilepath));
				File source = new File(newfilepath);
				PdfReader reader = new PdfReader(source.getAbsolutePath());
				Integer no_of_pages = reader.getNumberOfPages();
				subDocument.setSd_no_of_pages(no_of_pages);

				subDocument = subDocumentService.save(subDocument);
				reader.close();
				response.setResponse("TRUE");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		jsonData = globalfunction.convert_to_json(response);
		return jsonData;
	}

	@RequestMapping(value = "/addfiles", method = RequestMethod.POST)
	public @ResponseBody String addfiles(
			@RequestBody DownloadModel downloadModel, HttpSession session,
			HttpServletRequest request) {
		String jsonData = "";
		ActionResponse<DownloadModel> response = new ActionResponse<>();
		User u = (User) session.getAttribute("USER");
		List<OrderReport> orderReports = downloadModel.getOrderReports();
		List<SubDocument> subDocuments = downloadModel.getSubDocuments();
		String ipaddress = request.getRemoteAddr();
		DownloadReport dr = new DownloadReport();
		Integer pages = 0;
		Double dr_amount = 0.00;
		if (!subDocuments.isEmpty() || !orderReports.isEmpty()) {
			dr.setDr_cr_date(new Date());
			dr.setDr_fd_mid(downloadModel.getFd_id());
			dr.setDr_cr_by(u.getUm_id());
			dr.setDr_rec_status(1);
			dr.setDr_ip_address(ipaddress);
			dr = downloadService.saveReport(dr);
			response.setResponse("TRUE");
			response.setData("Files added for downloading");
		} else {
			response.setResponse("FALSE");
			response.setData("Please select files for downloading");
		}
		if (!subDocuments.isEmpty()) {
			for (SubDocument subDocument : subDocuments) {
				DownloadFile df = new DownloadFile();
				df.setDf_dr_mid(dr.getDr_id());
				df.setDf_sd_mid(subDocument.getSd_id());
				df.setDf_pages(subDocument.getSd_no_of_pages());
				df.setDf_submitted_date(subDocument.getSd_submitted_date());
				df = downloadService.saveFile(df);
				pages = pages + subDocument.getSd_no_of_pages();
			}
		}
		if (!orderReports.isEmpty()) {
			for (OrderReport orderReport : orderReports) {
				DownloadFile df = new DownloadFile();
				df.setDf_dr_mid(dr.getDr_id());
				df.setDf_ord_mid(orderReport.getOrd_id());
				df.setDf_pages(1);
				df.setDf_submitted_date(orderReport.getOrd_created());
				df = downloadService.saveFile(df);
				pages = pages + 1;
				if (orderReport.getSubDocument() != null) {
					SubDocument subDocument = orderReport.getSubDocument();
					DownloadFile df2 = new DownloadFile();
					df2.setDf_dr_mid(dr.getDr_id());
					df2.setDf_sd_mid(subDocument.getSd_id());
					df2.setDf_pages(subDocument.getSd_no_of_pages());
					df2.setDf_submitted_date(subDocument.getSd_submitted_date());
					downloadService.saveFile(df2);
					pages = pages + subDocument.getSd_no_of_pages();
				}
			}
		}
		if (pages > 0) {
			if (pages <= 10) {
				dr_amount = 15.00;
			} else {
				Double amount1 = 15.00;
				Integer pagesremaining = pages - 10;
				Double amount2 = Math.ceil(pagesremaining / 5.0) * 10;
				dr_amount = amount1 + amount2;
			}
		}
		dr.setDr_amount(dr_amount);
		downloadService.saveReport(dr);
		jsonData = globalfunction.convert_to_json(response);
		return jsonData;
	}

	@RequestMapping(value = "/getdownloadhistory/{id}", method = RequestMethod.GET)
	public @ResponseBody String getdownloadhistory(
			@PathVariable("id") Long fd_id, HttpSession session) {
		String jsonData = null;
		ActionResponse<DownloadReport> response = new ActionResponse<>();

		List<DownloadReport> reports = downloadService.getDownloadReport(fd_id);
		response.setResponse("TRUE");
		response.setModelList(reports);

		jsonData = globalfunction.convert_to_json(response);

		return jsonData;
	}

	@RequestMapping(value = "/getdownloadedfiles/{id}", method = RequestMethod.GET)
	public @ResponseBody String getdownloadedfiles(
			@PathVariable("id") Long dr_id, HttpSession session) {
		String jsonData = null;
		ActionResponse<DownloadFile> response = new ActionResponse<>();

		List<DownloadFile> files = downloadService.getFiles(dr_id);
		response.setResponse("TRUE");
		response.setModelList(files);

		jsonData = globalfunction.convert_to_json(response);

		return jsonData;
	}

	@RequestMapping(value = "/downloadfile/{id}", method = RequestMethod.GET)
	public void downloadfiles(@PathVariable("id") Long dr_id,
			HttpSession session, HttpServletResponse response) {
		String jsonData = null;

		DownloadReport report = downloadService.getById(dr_id);

		if (report.getDr_rec_status() == 1) {
			Lookup lookupRepo = lookupService.getLookUpObject("REPOSITORYPATH");
			Lookup lookupDownload = lookupService.getLookUpObject("DOWNLOADPATH");
			CaseFileDetail caseFileDetail = caseFileDetailService.getCaseFileDetail(report.getDr_fd_mid());
			String basePath = lookupRepo.getLk_longname() + File.separator
					+ caseFileDetail.getCaseType().getCt_label();

			List<InputStream> list = new ArrayList<InputStream>();

			List<DownloadFile> files = downloadService.getFiles(report.getDr_id());
			String downloadFolder = lookupDownload.getLk_longname()
					+ File.separator + caseFileDetail.getFd_document_name();
			File dir = new File(downloadFolder);
			if (!dir.exists()) {
				dir.mkdir();
			}
			String outputFilePath = downloadFolder + File.separator
					+ File.separator + caseFileDetail.getFd_document_name();
			OutputStream out;
			try {
				out = new FileOutputStream(new File(outputFilePath + ".pdf"));

				for (DownloadFile file : files) {
					if (file.getDf_sd_mid() != null) {
						SubDocument subDocument = file.getSubDocument();
						String srcPath = basePath + File.separator
								+ subDocument.getIndexField().getIf_name()
								+ File.separator
								+ subDocument.getSd_document_name() + ".pdf";
						list.add(new FileInputStream(new File(srcPath)));

					}
					if (file.getDf_ord_mid() != null) {
						String pdfname = downloadFolder + File.separator
								+ file.getDf_id() + ".pdf";
						PDFCreator.createPDF(file.getOrdeReport(), pdfname);
						list.add(new FileInputStream(new File(pdfname)));
					}
				}
				PDFMerger.doMerge(list, out);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				globalfunction.zipFolder(dir.getAbsolutePath(),
						dir.getAbsolutePath() + ".zip");
				response.setContentType("application/zip");
				PrintWriter out1 = response.getWriter();
				String filename = dir.getName() + ".zip";
				String filepath = dir.getAbsolutePath() + ".zip";
				File zipFile = new File(dir + ".zip");

				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + filename + "\"");

				FileInputStream fileInputStream = new FileInputStream(filepath);

				int i;
				while ((i = fileInputStream.read()) != -1) {
					out1.write(i);
				}
				fileInputStream.close();
				out1.close();
				// FileUtils.deleteDirectory(dir);
				zipFile.delete();
				dir.delete();
				report.setDr_rec_status(2);
				downloadService.saveReport(report);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	@RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
	public void download(@PathVariable("id") Long fd_id,
			HttpSession session, HttpServletResponse response) {
		String jsonData = null;

		//DownloadReport report = downloadService.getById(dr_id);

		//if (report.getDr_rec_status() == 1) {
			Lookup lookupRepo = lookupService.getLookUpObject("REPOSITORYPATH");
			Lookup lookupDownload = lookupService
					.getLookUpObject("DOWNLOADPATH");
			CaseFileDetail caseFileDetail = caseFileDetailService
					.getCaseFileDetail(fd_id);
			String basePath = lookupRepo.getLk_longname() + File.separator
					+ caseFileDetail.getCaseType().getCt_label();

			List<InputStream> list = new ArrayList<InputStream>();

			List<SubDocument> subDocuments = downloadService.getSubFiles(fd_id);
			String downloadFolder = lookupDownload.getLk_longname()
					+ File.separator + caseFileDetail.getFd_document_name();
			File dir = new File(downloadFolder);
			if (!dir.exists()) {
				dir.mkdir();
			}
			String outputFilePath = downloadFolder + File.separator
					+ File.separator + caseFileDetail.getFd_document_name();
			OutputStream out;
			try {
				out = new FileOutputStream(new File(outputFilePath + ".pdf"));
				OrderReport or=null;
				for (SubDocument file : subDocuments) {
					if (file.getSd_fd_mid() != null) {
						//SubDocument subDocument = file.getSubDocument();
						String srcPath = basePath + File.separator
								+ file.getIndexField().getIf_name()
								+ File.separator
								+ file.getSd_document_name() + ".pdf";
						list.add(new FileInputStream(new File(srcPath)));

					}
					 /* or= new OrderReport();
					   or=downloadService.getOrderReport(file.getSd_id());
					if (or.getOrd_id() != null) {
						String pdfname = downloadFolder + File.separator
								+ or.getOrd_id() + ".pdf";
						PDFCreator.createPDF(or, pdfname);
						list.add(new FileInputStream(new File(pdfname)));
					}*/
				}
				PDFMerger.doMerge(list, out);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				globalfunction.zipFolder(dir.getAbsolutePath(),
						dir.getAbsolutePath() + ".zip");
				response.setContentType("application/zip");
				PrintWriter out1 = response.getWriter();
				String filename = dir.getName() + ".zip";
				String filepath = dir.getAbsolutePath() + ".zip";
				File zipFile = new File(dir + ".zip");

				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + filename + "\"");

				FileInputStream fileInputStream = new FileInputStream(filepath);

				int i;
				while ((i = fileInputStream.read()) != -1) {
					out1.write(i);
				}
				fileInputStream.close();
				out1.close();
				// FileUtils.deleteDirectory(dir);
				zipFile.delete();
				dir.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	@RequestMapping(value = "/deleteDownloadHistory/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteDownloadHistory(
			@PathVariable("id") Long fd_id, HttpSession session) {
		String jsonData = null;
		ActionResponse<DownloadReport> response = new ActionResponse<>();
		boolean result = false;
		result = downloadService.deleteDownloadHistory(fd_id);
		if (result) {
			response.setResponse("TRUE");
		}
		jsonData = globalfunction.convert_to_json(response);

		return jsonData;
	}


	@RequestMapping(value = "/downloadCase", produces = { "application/pdf" }, method = RequestMethod.GET)
	public @ResponseBody String downloadCase() {
		String jsonData = null;
		
		File file = new File("D:\\Allahabad High Court\\Allahabad\\CLRE\\petition\\CLRE642018_PETN_1.pdf");
		
		
		
		
		

		return jsonData;
	}

	
	 /*--------------Sushant---------------------*/        
	
	@RequestMapping(value = "/NotesSaveLKO", method = RequestMethod.POST)
	public @ResponseBody String saveNotes(@RequestBody Notes Note, HttpSession session) {
		Notes nt = null;
		System.err.println("Fd_Id : " + Note.getNt_fd_mid());

		User user = (User) session.getAttribute("USER");
		ActionResponse<Notes> response = new ActionResponse<Notes>();
		System.out.println("== Notes Update ==");
		String jsonData = null;
		Long l =Note.getNt_fd_mid();
		Notes prenot = subDocumentService.getNoteLKO(l);
		System.out.println("== Note==id "+prenot);
		if(prenot==null)
		{
			nt = new Notes();
			nt.setNt_id(Note.getNt_id());
			nt.setNt_fd_mid(Note.getNt_fd_mid());
			nt.setNt_notes(Note.getNt_notes());
			nt.setNt_cr_date(new Date());
			nt.setNt_cr_by(user.getUm_id());
			subDocumentService.saveNoteLKO(nt);
			response.setResponse("TRUE");
			
		}
		else
		{
			Note.setNt_notes(Note.getNt_notes());
			Note.setNt_mod_date(new Date());
			int flag=subDocumentService.updateNoteLKO(Note);
			if(flag==1) {
				response.setResponse("TRUE");				
			}
		}
		
	/*	
		if (Note.getNt_notes() != null) {
			nt = new Notes();
			nt.setNt_id(Note.getNt_id());
			nt.setNt_fd_mid(Note.getNt_fd_mid());
			nt.setNt_notes(Note.getNt_notes());
			nt.setNt_cr_date(new Date());
			nt.setNt_cr_by(user.getUm_id());
			subDocumentService.saveNote(nt);
			response.setResponse("TRUE");
		} else 
		{	
			
			response.setResponse("FALSE");
			
		}*/
		
		
		
		jsonData = globalfunction.convert_to_json(response);
		return jsonData;
	}
		
		@RequestMapping(value = "/getnotes/{id}", method = RequestMethod.GET)
		public @ResponseBody String getNotes(@PathVariable("id") Long fd_id, HttpSession session) {
			String jsonData = null;
			User user = (User) session.getAttribute("USER");
			ActionResponse<Notes> response = new ActionResponse<>();
			Judge judge = courtMasterService.getJudgeByCourtMappingUmid(user.getUm_id());
			Notes notedata = subDocumentService.getNote(fd_id,judge.getJg_id());
			
			response.setResponse("TRUE");
			response.setModelData(notedata);
			
			jsonData = globalfunction.convert_to_json(response);
			
			return jsonData;
		}
	
		@RequestMapping(value = "/getnotesLKO/{id}", method = RequestMethod.GET)
		public @ResponseBody String getNotesLKO(@PathVariable("id") Long fd_id, HttpSession session) {
			String jsonData = null;
			ActionResponse<Notes> response = new ActionResponse<>();
			Notes notedata = subDocumentService.getNoteLKO(fd_id);
			
			response.setResponse("TRUE");
			response.setModelData(notedata);
			
			jsonData = globalfunction.convert_to_json(response);
			
			return jsonData;
		}
	
		
		
		
}
