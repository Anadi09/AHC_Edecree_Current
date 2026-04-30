package com.eDecree.controller;




import java.io.OutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ccms.CaseDetailsCcms;
import com.ccms.CcmsAdvoates;
import com.ccms.CcmsLowerCourt;
import com.ccms.CcmsPartyDetails;
import com.ccms.CcmsPrimary;
import com.eDecree.model.ActionResponse;
import com.eDecree.model.ApplicationNotice;
import com.eDecree.model.CaseFileDetail;
import com.eDecree.model.CaseNotice;
import com.eDecree.model.DecreeExamDTO;
import com.eDecree.model.DecreeFileUploaded;
import com.eDecree.model.DecreeForm;
import com.eDecree.model.DecreeStage;
import com.eDecree.model.IndexField;
import com.eDecree.model.Lookup;
import com.eDecree.model.SubDocument;
import com.eDecree.model.User;
import com.eDecree.model.UserRole;
import com.eDecree.service.CaseFileDetailService;
import com.eDecree.service.LookupService;
import com.eDecree.service.MasterService;
import com.eDecree.service.NoticeService;
import com.eDecree.service.SubDocumentService;
import com.eDecree.service.UserService;
import com.eDecree.utility.GlobalFunction;
import com.eDecree.utility.PDFGenerate;
import com.eDecree.utility.SendMail;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.FontFactoryImp;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import com.lowagie.text.DocumentException;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	static String emailToRecipient, emailSubject, emailMessage;
    static final String emailFromRecipient = "bilalkhan0408@gmail.com";

	@Autowired
    private JavaMailSender mailSenderObj;
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private LookupService lookupService;
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CaseFileDetailService cfd;

	@Autowired
	private MasterService masterService;
	
	@Autowired
	private SubDocumentService subDocumentService;

	@Autowired
	private NoticeService noticeService;

	@Autowired
	private CaseFileDetailService caseFileDetailService;
	
	private GlobalFunction globalfunction =new GlobalFunction();

	@Autowired
	private UserService usermaster;

	@Autowired
	private PDFGenerate pdfGenrate;

	@RequestMapping(value = "/getCaseForNotice", method = RequestMethod.GET)
	public String download_manage() {

		return "/notice/manage";
	}
	
	
	@RequestMapping(value = "/getDecreeForExam", method = RequestMethod.GET)
	public String getDecreeForExam() {

		return "/decree/decreeExam";
	}
	
	@RequestMapping(value = "/getDecreeForApprove", method = RequestMethod.GET)
	public String getDecreeForApprove() {

		return "/decree/getDecreeForApprove";
	}
	

	@RequestMapping(value = "/getApproveDecreeList", method = RequestMethod.GET)
	public String getApprovedDecree() {

		return "/decree/approvedDecreeList";
	}
	
	
	

	@RequestMapping(value = "/case_notice",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	@ResponseBody
	public void addCaseNotice(@RequestBody CaseNotice caseNotice,HttpSession session) {
		
		/*SendMail sm =new SendMail();
		sm.sendMail("sushant", "sushantmishra09@gmail.com","what are you doind","Heloooooo");
		*/
		
		User u =(User)session.getAttribute("USER");
		String path="D:/notice/case.pdf";
		try {  
			pdfGenrate.createCaseNoticeRptPdf(caseNotice, path);

			File file = new File(path);  
			if (file.exists()) {  
				System.out.println("New File is created!"); 
				caseNotice.setCn_cr_by(u.getUm_id());
				caseNotice.setCn_cr_date(new Date());
				caseNotice.setCn_rec_status(1);
				noticeService.save(caseNotice);
			} else {  
				System.out.println("something went wrong!!!.");  
			}  
		} catch (IOException e) {  
			e.printStackTrace();  
		}
		
	}

	@RequestMapping(value = "/application_notice",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	@ResponseBody
	public void addApplicationNotice(@RequestBody ApplicationNotice applicationNotice, HttpSession session) {
		
		
		
		 emailSubject ="check highcourt notice";
	      emailMessage = "mail send without attachment";
	        emailToRecipient = "bilalkhan0408@gmail.com";
	 
	        // Logging The Email Form Parameters For Debugging Purpose
	     //   System.out.println("\nReceipient?= " + emailToRecipient + ", Subject?= " + emailSubject + ", Message?= " + emailMessage + "\n");
	        System.out.println("mail send without attachment");
	       mailSenderObj.send(new MimeMessagePreparator() {
				
				
				
			
			
	            public void prepare(MimeMessage mimeMessage) throws Exception {
	 
	                MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");             
	                mimeMsgHelperObj.setTo(emailToRecipient);
	                mimeMsgHelperObj.setFrom(emailFromRecipient);               
	                mimeMsgHelperObj.setText(emailMessage);
	                mimeMsgHelperObj.setSubject(emailSubject);
	                
	                File attachFileObj =new File("D:/application/application.pdf");
	                
	                /*if ((attachFileObj != null) && (attachFileObj.getSize() > 0) && (!attachFileObj.equals(""))) {
	                    System.out.println("\nAttachment Name?= " + attachFileObj.getOriginalFilename() + "\n");
	                    mimeMsgHelperObj.addAttachment(attachFileObj.getOriginalFilename(), new InputStreamSource() {                   
	                        public InputStream getInputStream() throws IOException {
	                            return attachFileObj.getInputStream();
	                        }
	                    });
	                } else {
	                    System.out.println("\nNo Attachment Is Selected By The User. Sending Text Email!\n");
	                }*/
	            }
	        });
		
		User u = (User) session.getAttribute("USER");
		
		String path="D:/application/application.pdf";
		try {  
			pdfGenrate.createApplicationNoticeRptPdf(applicationNotice, path);

			File file = new File(path);  
			if (file.exists()) {  
				System.out.println("New File is created!");  
				applicationNotice.setAn_rec_status(1);
				applicationNotice.setAn_cr_by(u.getUm_id());
				applicationNotice.setAn_cr_date(new Date());
				
				noticeService.save(applicationNotice);
			} else {  
				System.out.println("something went wrong!!!.");  
			}  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  

		
	}
	
	
	@RequestMapping(value = "/getCaseDetails/{id}", method = RequestMethod.GET)
	public @ResponseBody String getCaseDetails(@PathVariable("id") Long docId) {
		ActionResponse<Object[]> response = new ActionResponse<Object[]>();
		CaseFileDetail fd=cfd.getCaseFileDetail(docId);
		String jsonData = "";
		/*Object[] types = noticeService.getCaseDetails("WRIC", 1, 2022);*/
		
		//Object[] types = noticeService.getCaseDetails(fd.getCaseType().getCt_label(),Integer.parseInt(fd.getFd_case_no()),fd.getFd_case_year());
		
		
		RestTemplate restTemplate = new RestTemplate();
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		 
		 MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		 
		 HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
		 
		 map.add("CaseType",fd.getCaseType().getCt_ccms_id().toString());
		 map.add("CaseNumber", fd.getFd_case_no());
		 map.add("CaseYear",fd.getFd_case_year().toString());
		 
		 HttpEntity<Object> response1 =
			     restTemplate.exchange("http://192.168.0.114/testapi/API/CaseStatus/BriefCaseDetailsByTypeNoYear",
			                           HttpMethod.POST,
			                           entity,
			                           Object.class);
		 Object caseIntial=(Object) response1.getBody();
		
		 ObjectMapper m = new ObjectMapper();
		 m.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		 CcmsPrimary props = m.convertValue(caseIntial, CcmsPrimary.class);
		List<Object> jgNmae=null;
		
		CaseDetailsCcms ccms=new CaseDetailsCcms();
		
		if(!props.getCase_id().isEmpty()) {
			map.clear();
			map.add("Caseid", props.getCase_id());
			
			HttpEntity<Object> response2 =
				     restTemplate.exchange("http://192.168.0.114/testapi/API/CaseStatus/CaseDetailsByCaseId",
				                           HttpMethod.POST,
				                           entity,
				                           Object.class);
			 Object caseCcms=(Object) response2.getBody();
			 
			
			 m.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			 ccms = m.convertValue(caseCcms, CaseDetailsCcms.class);
		}
		
		if(!ccms.getCaseId().isEmpty()) {
		// jgNmae = noticeService.getJudgeName(types[8].toString());
		}
		
		//response.setData("TRUE");
		response.setData(ccms);
		response.setDataList(jgNmae);
		
		
		jsonData = globalfunction.convert_to_json(response);
		return jsonData;
	}
	@RequestMapping(value = "/digitalSignDecree/{id}", method = RequestMethod.GET)
	public @ResponseBody String createDecreePdfToUploader(@PathVariable("id") Long caseFileId) throws IOException	
	{
		
		ActionResponse<Object> response = new ActionResponse<Object>();
		String jsonData = "";
		 String b64=null;
		
		String uploadPath = context.getRealPath("");

		 Document doc=new Document();
		 
		 DecreeForm officeRpt=noticeService.getDecreeForm(caseFileId);
		 if(officeRpt.getDf_locked()==true) {
			 SubDocument sd =noticeService.getDecreePdf(caseFileId);
			 CaseFileDetail caseFileDetail = caseFileDetailService.getCaseFileDetail(caseFileId);
			 
			 Lookup lookupRepo = lookupService.getLookUpObject("REPOSITORYPATH");
			 String srcPath = lookupRepo.getLk_longname() + File.separator
						+ caseFileDetail.getCaseType().getCt_label()
						+ File.separator + sd.getIndexField().getIf_name()
						+ File.separator + sd.getSd_document_name()
						+ ".pdf";

				File source = new File(srcPath);

				String uploadPath1 = context.getRealPath("");
				File dest = new File(uploadPath + File.separator + "uploads"
						+ File.separator + sd.getSd_document_name()
						+ ".pdf");
				
				System.out.println("destination: "+dest);

				try {
					FileUtils.copyFile(source, dest);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			    byte [] bytes = Files.readAllBytes(dest.toPath());

			      b64 = Base64.getEncoder().encodeToString(bytes);
			      System.out.println(b64);
			      
			      response.setData(b64);
					 
					 jsonData = globalfunction.convert_to_json(response);
				
				
		 }
		 else {
		 HTMLWorker htmlWorker = new HTMLWorker(doc);
		 
			Font underlin =new Font(Font.FontFamily.HELVETICA  , 20, Font.BOLDITALIC);
			 
			 PdfWriter writer;
			 
			
			try {
				writer = PdfWriter.getInstance(doc, new FileOutputStream(uploadPath + File.separator + "uploads"
						+ File.separator +  "Decree.pdf"));
			
			 
			 doc.open();
			/* Paragraph title=new Paragraph(Font.BOLDITALIC,"Office Report");
			 title.setFont(underlin);
			
			 title.setAlignment(Element.ALIGN_CENTER);*/
			 
			
			 htmlWorker.parse(new StringReader("<br><br>"+officeRpt.getDf_first_div()));
				
				doc.newPage();
				
				
				htmlWorker.parse(new StringReader("<br><br>"+officeRpt.getDf_2nd_div()));
				
				
				htmlWorker.parse(new StringReader("<br><br>"+officeRpt.getDf_editor()));
				
				
				 doc.newPage();
				 Font font = new Font(FontFamily.HELVETICA, 12f, Font.STRIKETHRU);
					htmlWorker.parse(new StringReader("<br><br>"+officeRpt.getDf_3rd_div()));
					
					LineSeparator ls1=new LineSeparator();
					doc.add(new Chunk(ls1));
					doc.add(new Paragraph("* Here enter the date of judgement or order upon which the decree is rounded."));
				
	             doc.newPage();
	             
	             Map<String,Object> providers = new HashMap<String, Object>();

	             defaultFontProvider dfp = new defaultFontProvider("arial.ttf");

	             providers.put(HTMLWorker.FONT_PROVIDER, dfp);
	             
	             htmlWorker.setProviders(providers);
				
				htmlWorker.parse(new StringReader("<br><br>"+officeRpt.getDf_4th_div()));
				
				/*doc.add(Chunk.NEWLINE);*/
				 Font f=new Font(FontFamily.TIMES_ROMAN,7.0f);
				Paragraph pDr = new Paragraph((String) (officeRpt.getDf_aprrove_by()==null ? "" : officeRpt.getDf_aprrove_by()),f);
				pDr.setAlignment(Element.ALIGN_RIGHT);
				pDr.add("*Deputy Registrar \n Allahabad/Lucknow \n");
				/*pDr.add("\n");*/
				pDr.add("*(The Deputy Registrar shall give below his\n" + 
						"signature the date on which he actually\n" + 
						"signs the decree)");
				doc.add(pDr);
				doc.add(Chunk.NEWLINE);
				
				 Chunk glue = new Chunk(new VerticalPositionMark());
				
				 Paragraph p = new Paragraph("Prepared by",f); 
				 p.setLeading(8f);
				 p.add(new Chunk(glue));
				 p.add("Advocate for appellant");
				 doc.add(p);
				 
				 //doc.add(Chunk.NEWLINE);
				 
				 Paragraph p1 = new Paragraph("Decree Writer :"+officeRpt.getCrBy().getUm_fullname(),f); 
				 p1.add(new Chunk(glue));
				 p1.add("Date");
				 doc.add(p1);
				 
				 //doc.add(Chunk.NEWLINE);
				 
				 
				 Paragraph p2 = new Paragraph("Date",f); 
				 doc.add(p2);
				 
				// doc.add(Chunk.NEWLINE);
				 
				 Paragraph p3 = new Paragraph("Examined by",f); 
				 p3.add(new Chunk(glue));
				 p3.add("Advocate for respondent");
				 doc.add(p3);
				 
				 doc.add(Chunk.NEWLINE);
				 
				 
				 Paragraph p4 = new Paragraph("Decree Writer :"+officeRpt.getCrBy().getUm_fullname(),f);
				 p4.add(new Chunk(glue));
				 p4.add("Date");
				 doc.add(p4);
				 
				// doc.add(Chunk.NEWLINE);
				 
				 
				 Paragraph p5 = new Paragraph("Date",f); 
				 doc.add(p5);
				 
				// doc.add(Chunk.NEWLINE);
				 
				 
				   // Paragraph p=new Paragraph("New PdF",f);
				 Paragraph p6 = new Paragraph("* Not signed by the Advocates \n for appellant and repondent through served",f); 
				 p6.add("\n");
				 p6.add("Decree-Writer");
				 p6.add("\n");
				 p6.add("Date");
				 doc.add(p6);
//				 
//				 doc.add(Chunk.NEWLINE);
//				 doc.add(Chunk.NEWLINE);
//				 doc.add(Chunk.NEWLINE);
				 
				 
				LineSeparator ls=new LineSeparator();
				Paragraph p7=new Paragraph(new Chunk(ls));
				p7.setLeading(10f);
				p7.setFont(f);
				p7.add("\n*To be scored out when the Advocates have put their signatures.");
				doc.add(p7);
			 
			
			
			 
			 
			 doc.close();
			 writer.close();
			 
			 File file = new File(uploadPath + File.separator + "uploads"
						+ File.separator +  "Decree.pdf");
		      byte [] bytes = Files.readAllBytes(file.toPath());

		      b64 = Base64.getEncoder().encodeToString(bytes);
		      System.out.println(b64);
		      
		     
			 
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (com.itextpdf.text.DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			 response.setData(b64);
			 
			 jsonData = globalfunction.convert_to_json(response);
		 }
				
				return jsonData;
		
	
	}
	
	@RequestMapping(value = "/genratePdfStrem", method = RequestMethod.POST)
	public @ResponseBody String genratePdfStrem(@RequestBody DecreeForm decreeForm, HttpSession session) {
		String jsonData = "";
		ActionResponse<DecreeForm> response = new ActionResponse<DecreeForm>();
		User user = (User) session.getAttribute("USER");
		Lookup lookup = lookupService.getLookUpObject("REPOSITORYPATH");
		IndexField indexField = masterService.getIndexField(44L);
		Integer count = subDocumentService.getCount(decreeForm.getCaseFileDetail().getFd_id());
		SubDocument sb=new SubDocument();
		List<UserRole> userroles = user.getUserroles();
		String userRole = "";		
		
		count=count+1;
		
		String filename = decreeForm.getCaseFileDetail().getFd_document_name() + "_"
				+ indexField.getIf_type_code() + "_" + count;
		String newfilepath = lookup.getLk_longname() + File.separator
				+ decreeForm.getCaseFileDetail().getCaseType().getCt_label()
				+ File.separator + indexField.getIf_name() + File.separator
				+ filename + ".pdf";
		
		
		SubDocument subDocument = new SubDocument();
		subDocument.setSd_cr_by(user.getUm_id());
		subDocument.setSd_cr_date(new Date());
		subDocument.setSd_fd_mid(decreeForm.getCaseFileDetail().getFd_id());
		subDocument.setSd_if_mid(44L);
		subDocument.setSd_version(1);
		subDocument.setSd_document_name(filename);
		/*subDocument.setSd_document_id(at_id);*/
		subDocument.setSd_submitted_date(new Date());
		subDocument.setSd_rec_status(1);
		subDocument.setSd_minor_sequence(count);
		/*subDocument.setSd_document_no(sd_document_no);
		subDocument.setSd_document_year(sd_document_year);*/
		
		
		
		 File source = new File(newfilepath);

		    try ( FileOutputStream fos = new FileOutputStream(source); ) {
		      // To be short I use a corrupted PDF string, so make sure to use a valid one if you want to preview the PDF file
		      String b64 =decreeForm.getPdfStrem();
		      byte[] decoder = Base64.getDecoder().decode(b64);

		      fos.write(decoder);
		     
				PdfReader reader = new PdfReader(source.getAbsolutePath());
				Integer no_of_pages = reader.getNumberOfPages();
				subDocument.setSd_no_of_pages(no_of_pages);
				subDocument = subDocumentService.save(subDocument);
				
				decreeForm.setDf_locked(true);
				
				DecreeForm mapping = noticeService.saveDecree(decreeForm);
		      System.out.println("PDF File Saved");
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
						return jsonData;
					
		
	}
	
	@RequestMapping(value = "/downloadDecree/{id}", method = RequestMethod.GET)
	public void createDecreePdf(@PathVariable("id") Long caseFileId,HttpServletRequest request,HttpServletResponse response,
			HttpSession session) throws IOException {
		 Document doc=new Document();
		 
		 DecreeForm officeRpt=noticeService.getDecreeForm(caseFileId);
		 if (officeRpt == null) {
			    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Decree not found");
			    return;
			}
		 Lookup lookup = lookupService.getLookUpObject("REPOSITORYPATH");
		 
		 List<DecreeStage> stage = noticeService.getDecreeStage(caseFileId);
		 
		 if(officeRpt.getDf_locked()==false) {
			 HTMLWorker htmlWorker = new HTMLWorker(doc);
		//===================================start hindi  font =========================
			 URL fontUrl = getClass()
				        .getClassLoader()
				        .getResource("fonts/NotoSansDevanagari.ttf");
				       
				final String fontPath = fontUrl.toString();

				FontFactory.register(fontPath, "hindiFont");

				Map<String, Object> providers = new HashMap<>();

				providers.put(HTMLWorker.FONT_PROVIDER, new FontFactoryImp() {

				    @Override
				    public Font getFont(String fontname, String encoding, boolean embedded,
				                        float size, int style, BaseColor color) {

				        try {

				            BaseFont bf = BaseFont.createFont(
				                    fontPath,
				                    BaseFont.IDENTITY_H,
				                    BaseFont.EMBEDDED);

				            return new Font(bf, size, style, color);

				        } catch (Exception e) {
				            e.printStackTrace();
				            return super.getFont(fontname, encoding, embedded, size, style, color);
				        }
				    }
				});

				htmlWorker.setProviders(providers);
			 
			 
			 
			 //==========================================
	//	 HTMLWorker htmlWorker = new HTMLWorker(doc);
		 
		User examBy=userService.getByuserid(officeRpt.getDf_exam_by());
		User examBy2 =userService.getByuserid(officeRpt.getDf_exam2_by());
		User examBy3 =userService.getByuserid(officeRpt.getDf_exam3_by());
			 
			 PdfWriter writer;
			 
			 String decreePdf=lookup.getLk_longname()+File.separator+"decree"+File.separator+officeRpt.getDf_id()+".pdf";
			try {
				writer = PdfWriter.getInstance(doc, new FileOutputStream(decreePdf));
			
			 
			 doc.open();
			/* Paragraph title=new Paragraph("");
			
			 title.setAlignment(Element.ALIGN_CENTER);
			 
			 doc.add(title);*/
			
			
			htmlWorker.parse(new StringReader("<br><br>"+officeRpt.getDf_first_div()));
			
			doc.newPage();
			
			
			htmlWorker.parse(new StringReader("<br><br>"+officeRpt.getDf_2nd_div()));
			
			
		//	htmlWorker.parse(new StringReader("<br><br>"+officeRpt.getDf_editor()));
			htmlWorker.parse(new StringReader("<br><br>"+officeRpt.getDf_editor()));
			
			 doc.newPage();
			 Font font = new Font(FontFamily.HELVETICA, 12f, Font.STRIKETHRU);
				htmlWorker.parse(new StringReader("<br><br>"+officeRpt.getDf_3rd_div()));
				
				LineSeparator ls1=new LineSeparator();
				doc.add(new Chunk(ls1));
				doc.add(new Paragraph("* Here enter the date of judgement or order upon which the decree is rounded."));
			
             doc.newPage();
             
    //         Map<String,Object> providers = new HashMap<String, Object>();

             defaultFontProvider dfp = new defaultFontProvider("NotoSansDevanagari.ttf");

             providers.put(HTMLWorker.FONT_PROVIDER, dfp);
             
             htmlWorker.setProviders(providers);
			
			htmlWorker.parse(new StringReader(""+officeRpt.getDf_4th_div()));
			
			
			User assignTo =userService.getByuserid(officeRpt.getDf_assign_to());
		
			
		//==================================================end==========================	
			/*doc.add(Chunk.NEWLINE);*/
			 Font f=new Font(FontFamily.TIMES_ROMAN,7.0f);
			Paragraph pDr = new Paragraph((String) (officeRpt.getDf_aprrove_by()==null ? "" : officeRpt.getAprBy().getUm_fullname()),f);
			pDr.setAlignment(Element.ALIGN_RIGHT);
			
			if(officeRpt.getDf_stage_lid()==4008) {
				pDr.add(assignTo.getUm_fullname());
				
				
			}
		
			pDr.add("\n");
			pDr.add("*Deputy Registrar \n Allahabad/Lucknow \n");
			/*pDr.add("\n");*/
			pDr.add("*(The Deputy Registrar shall give below his\n" + 
					"signature the date on which he actually\n" + 
					"signs the decree)");
			doc.add(pDr);
			doc.add(Chunk.NEWLINE);
			
			 Chunk glue = new Chunk(new VerticalPositionMark());
			
			 Paragraph p = new Paragraph("Prepared by",f); 	
			 p.setLeading(8f);
			 p.add(new Chunk(glue));
			/* p.add("Examined by");
			 p.add(new Chunk(glue));*/
			 p.add("Advocate for appellant");
			 doc.add(p);
			 
			 
			 //doc.add(Chunk.NEWLINE);
			 
			 Paragraph p1 = new Paragraph("Decree Writer :"+officeRpt.getCrBy().getUm_fullname(),f); 
			 p1.add(new Chunk(glue));
			/* p1.add("Decree Writer :"+examBy2.getUm_fullname());
			 p1.add(new Chunk(glue));*/
			 p1.add("Date");
			 doc.add(p1);
			 
			 //doc.add(Chunk.NEWLINE);
			 SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		     String dateString = dateFormat.format(officeRpt.getDf_cr_date());
			 
			 Paragraph p2 = new Paragraph("Date :" +dateString,f); 
			/* p2.add(new Chunk(glue));
			 p2.add("Date");*/
			 p2.add(new Chunk(glue));
			 p2.add(" ");
			 doc.add(p2);
			 
			 doc.add(Chunk.NEWLINE);
			 
			 Paragraph p3 = new Paragraph("Examined by",f); 
			 p3.add(new Chunk(glue));
			/*p3.add("Examined by");
			p3.add(new Chunk(glue));*/			 
			 p3.add("Advocate for respondent");
			 doc.add(p3);
			 
			
		//	if(officeRpt.getDf_exam_by()!=null || officeRpt.getDf_exam3_by()!=null) { 
			 
			 Paragraph p4 = new Paragraph("Decree Writer :"+examBy.getUm_fullname(),f);					
			/* p4.add(new Chunk(glue));
			 p4.add("Decree Writer :" +examBy3.getUm_fullname());	*/
			/* p4.add("Decree Writer :" +examBy.getUm_fullname());*/
			 p4.add(new Chunk(glue));
			 p4.add("Date");
			 doc.add(p4);
			 
			/*}
			else {
				Paragraph p4 = new Paragraph("Decree Writer :"+examBy.getUm_fullname(),f);					
				 p4.add(new Chunk(glue));
				 p4.add("Decree Writer :" +examBy3.getUm_fullname());		
				 p4.add(new Chunk(glue));
				 p4.add("Date");
				 doc.add(p4);
			}*/


			 
			// doc.add(Chunk.NEWLINE);
			 
			 SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
				/* String dateString1 = dateFormat.format(officeRpt.getDf_exam_date()); */
			 String dateString1 = "";

			 if (stage != null && stage.size() > 0 && stage.get(0).getDs_cr_date() != null) {
			     dateString1 = dateFormat.format(stage.get(0).getDs_cr_date());
			 } else {
			     dateString1 = "N/A"; // or handle properly
			 }
			 
			 Paragraph p5 = new Paragraph("Date :" +dateString1,f); 
			/* p5.add(new Chunk(glue));
			 p5.add("Date");*/
			 p5.add(new Chunk(glue));
			 p5.add(" ");
			 doc.add(p5);
			 
			// doc.add(Chunk.NEWLINE);
			 
			 
			   // Paragraph p=new Paragraph("New PdF",f);
			 Paragraph p6 = new Paragraph("* Not signed by the Advocates \n for appellant and repondent through served",f); 
			 p6.add("\n");
			 p6.add("Decree-Writer");
			 p6.add("\n");
			 p6.add("Date");
			 doc.add(p6);
//			 
//			 doc.add(Chunk.NEWLINE);
//			 doc.add(Chunk.NEWLINE);
//			 doc.add(Chunk.NEWLINE);
			 
			 
			LineSeparator ls=new LineSeparator();
			Paragraph p7=new Paragraph(new Chunk(ls));
			p7.setLeading(10f);
			p7.setFont(f);
			p7.add("\n*To be scored out when the Advocates have put their signatures.");
			doc.add(p7);
			 
			 doc.close();
			 writer.close();
			 
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (com.itextpdf.text.DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int n=0;
			
			/*ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

			String path = classLoader.getResource("/src/main/webapp/assets/img/FINAL_LOGO.png").getPath();*/

			
			try {
                PdfReader reader = new PdfReader(decreePdf);
                 n = reader.getNumberOfPages();
               String sourceFile = lookup.getLk_longname()+File.separator+"decree"+File.separator+officeRpt.getDf_id()+"tmp.pdf";
               String imgPath=request.getContextPath();
               //System.out.println(imgPath+"/src/main/webapp/assets/img/FINAL_LOGO.png");
                PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(sourceFile));
                Image img = Image.getInstance(getClass().getClassLoader().getResource("FINAL_LOGO.png"));
               /* Image img = Image.getInstance("C:\\Users\\Alok\\Desktop\\eclipse\\FINAL_LOGO.png");*/
                float w = img.getScaledWidth();
                float h = img.getScaledHeight();
                img.scaleToFit(200, 200);
                PdfGState gs1 = new PdfGState();
                gs1.setFillOpacity(0.2f);
                int i = 1;
                while (i <= n) {
                    PdfContentByte over = stamper.getOverContent(i);
                    over.saveState();
                    over.setGState(gs1);
                    over.addImage(img, w, 0.0f, 0.0f, h, 15.0f, 35.0f);
                    over.restoreState();
                    ++i;
                }
                try {
                    stamper.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                stamper.close();
                reader.close();
                if(n!=0)
                {
                	File source = new File(sourceFile);
                    File dest = new File(lookup.getLk_longname()+File.separator+"decree"+File.separator+officeRpt.getDf_id()+"W.pdf");
                    FileUtils.copyFile(source, dest);
                    
                    source.delete();
                 
                }
              
            }
            catch (Exception e) {
                System.out.println(e);
            }
			
			try {
				response.setContentType("application/pdf");
				PrintWriter out = response.getWriter();
				/*String filename = "DecreeW.pdf";
				String filepath = "D:/DecreeW.pdf";*/
				
				 
				//File zipFile = new File(dest_folder + ".zip");
				
				List<InputStream> list=  new ArrayList<InputStream>();
				
				list.add(new FileInputStream(new File(lookup.getLk_longname()+File.separator+"decree"+File.separator+officeRpt.getDf_id()+"W.pdf")));
				
				for(DecreeFileUploaded dfu : officeRpt.getDecreeFileUploaded()) {

					String draftBasepath =
							 lookup.getLk_longname() + File.separator
								+ officeRpt.getCaseFileDetail().getCaseType().getCt_label()
								+ File.separator + "Decree" + File.separator+dfu.getDfu_file_name();
					list.add(new FileInputStream(new File(draftBasepath)));
					System.out.println("Repository path in decree----- " +draftBasepath);
					
				/*if(officeRpt.getDf_locked()==true) {
					String draftBasepath =
							 lookup.getLk_longname() + File.separator
								+ officeRpt.getCaseFileDetail().getCaseType().getCt_label()
								+ File.separator + "Decree" + File.separator+officeRpt.getDf_final_file();
					list.add(new FileInputStream(new File(draftBasepath)));"Decree" + File.separator+dfu.getDfu_file_name();
					System.out.println("Repository path in decree----- " +draftBasepath);*/
				
					
				}
				
				
				String dest2=lookup.getLk_longname()+File.separator+"decree"+File.separator+officeRpt.getDf_id()+"W2.pdf";
				globalfunction.doMerge(list, dest2);
				
				File f=new File(lookup.getLk_longname()+File.separator+"decree"+File.separator+officeRpt.getDf_id()+"W2.pdf");

				//response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment; filename=\""
						+ f.getName() + "\"");
				
				/*response.setHeader("Content-Disposition", "inline; filename=Decree.pdf");*/
				

				FileInputStream fileInputStream = new FileInputStream(lookup.getLk_longname()+File.separator+"decree"+File.separator+officeRpt.getDf_id()+"W2.pdf");

				int i;
				while ((i = fileInputStream.read()) != -1) {
					out.write(i);
				}
				fileInputStream.close();
				out.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		 }
		 String draftBasepath =
				 lookup.getLk_longname() + File.separator
					+ officeRpt.getCaseFileDetail().getCaseType().getCt_label()
					+ File.separator + "Decree" + File.separator+officeRpt.getDf_final_file()+".pdf";
		
		System.out.println("Repository path in decree----- " +draftBasepath);
	
		 File file = new File(draftBasepath);
	        if (file.exists()) {
	            // Set content type as PDF
	            response.setContentType("application/pdf");
	            
	            // Set the Content-Disposition header to prompt a file download
	            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());

	            // Stream the file content to the response output stream
	            try (FileInputStream fileInputStream = new FileInputStream(file);
	                 ServletOutputStream outputStream = response.getOutputStream()) {
	                
	                byte[] buffer = new byte[14096];
	                int bytesRead;
	                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
	                    outputStream.write(buffer, 0, bytesRead);
	                }
	            }
	        } else {
	            // If file does not exist, send a 404 error
	            response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found.");
	        }
	     
		

	}
	
	
	// ======================================  Vijay chaurasiya ===================================================
	
	@RequestMapping(value = "/downloadDecreeDoc/{id}", method = RequestMethod.GET)
	public void createDocA4(@PathVariable("id") Long caseFileId,
	                      HttpServletResponse response) throws IOException {

	    try {
	        DecreeForm officeRpt = noticeService.getDecreeForm(caseFileId);
	        
	        
	    	User examBy=userService.getByuserid(officeRpt.getDf_exam_by());
			User examBy2 =userService.getByuserid(officeRpt.getDf_exam2_by());
			User examBy3 =userService.getByuserid(officeRpt.getDf_exam3_by());
			
			User crBY=userService.getByuserid(officeRpt.getCrBy().getUm_id());
		
			
			
			
			User approveBy=userService.getByuserid(officeRpt.getDf_aprrove_by());
			
			User assignTo=userService.getByuserid(officeRpt.getDf_assign_to());
	        
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			
			String examDate = "";
			String crDate = "";
			if (officeRpt.getDf_exam_date() != null) {
			    examDate = sdf.format(officeRpt.getDf_exam_date());
			    crDate=sdf.format(	officeRpt.getDf_cr_date());
			}
			
			
			String assignHtml = "";

			if (officeRpt.getDf_stage_lid() != null && officeRpt.getDf_stage_lid() == 4008) {
			    if (assignTo != null) {
			        assignHtml = "<h6 style=\\\"margin:0; line-height:14px;\\\" >" + assignTo.getUm_fullname() + "</h6>";
			    }
			}
			
			
	        
	        System.out.println("Office Report: " + officeRpt.getDf_5th_div());

	        String html =
	        		"<html>" +
	        		"<head><meta charset='UTF-8'></head>" +
	        		"<body>" +

	        		// MAIN CONTENT
	        		officeRpt.getDf_first_div() + "<br><br>" +
	        		officeRpt.getDf_2nd_div() + "<br><br>" +
	        		officeRpt.getDf_editor() + "<br><br>" +
	        		officeRpt.getDf_3rd_div() + "<br><br>" +
	        		officeRpt.getDf_4th_div() + "<br><br>" +

	        		// TABLE START
	        		"<table style='width:100%;'>" +
	        		"<tr>" +

	        		// ================= LEFT SIDE =================
	        		"<td style='width:50%; vertical-align:top;'>" +

	        		"<br>" + 	"<br>" +	"<br>" +	"<br>" +	"<br>" + // 👈 THIS is the ONLY reliable spacing in iText

	        		"<h6 style='margin:0; line-height:14px;'>" +
	        		"Prepared By <br> Decree-Writer : " + crBY.getUm_fullname() + "<br>" +
	        		"Date : " + crDate + "<br><br>" +

	        		"Examined By <br> Decree-Writer : " +
	        		(examBy != null ? examBy.getUm_fullname() : "") + "<br>" +
	        		"Date : " + examDate +
	        		"</h6>" +

	        		"<h6 style='margin:0; line-height:14px;'>" +
	        		"*Not signed by the Advocates for <br>" +
	        		"appellant and respondent <br>" +
	        		"though served.<br>" +
	        		"Decree-Writer <br>Date" +
	        		"</h6>" +

	        		"</td>" +

	        		// ================= RIGHT SIDE =================
	        		"<td style='width:50%; vertical-align:top; text-align:right;'>" +

	        		(assignHtml != null ? assignHtml : "") +

	        		"<h6 style='margin:0; line-height:14px;'>" +
	        		"*Deputy Registrar<br>" +
	        		"Allahabad/Lucknow<br>" +
	        		"*(The Deputy Registrar shall give below his<br>" +
	        		"signature the date on which he actually<br>" +
	        		"signs the decree)<br><br>" +

	        		"Advocate for appellant<br>Date<br><br>" +
	        		"Advocate for respondent<br>Date" +
	        		"</h6>" +

	        		"</td>" +

	        		"</tr>" +
	        		"</table>" +

	        		// FOOTER
	        		"<div style='width:100%; padding-top:10px;'>" +
	        		"<hr>" +
	        		"<p style='font-size:11px; margin:0;'>" +
	        		"* To be scored out when the Advocates have put their signatures." +
	        		"</p>" +
	        		"</div>" +

	        		"</body></html>";
	        //  IMPORTANT: Set response headers
	        response.setContentType("application/msword");
	        response.setHeader("Content-Disposition", "attachment; filename=eDecree_doc_for_hindi.doc");

	        //  Write to browser instead of file
	        response.getOutputStream().write(html.getBytes("UTF-8"));
	        response.getOutputStream().flush();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	
	

	

	
	
	
	@RequestMapping(value = "/isFinalFilePresent", method = RequestMethod.GET)
	@ResponseBody
	public boolean isFinalFilePresent(@RequestParam("dfFdMid") Long dfFdMid) {

	    return noticeService.isFinalFilePresent(dfFdMid);
	}
	
	
	// ======================================  Vijay chaurasiya  end===================================================	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/getDecreeCaseStatus", method = RequestMethod.GET)
	public String getDecreeCaseStatusData1(HttpServletRequest request, Model model) {

	    List<Object[]> data = noticeService.getDecreeCaseStatus();
	    System.out.println("DATA SIZE: " + (data != null ? data.size() : "NULL"));

	    // send data to JSP
	    model.addAttribute("decreeData", data);

	    return "notice/decreeCaseStatus";
	}
	
//	<!-- ===================================== 	JAVA FULLSTACK DEVELOPER VIJAY CHAURASIYA ================================== -->
	
	@RequestMapping(value = "/nextStage", method = RequestMethod.POST)
	public @ResponseBody String nextStage(@RequestBody DecreeForm decreeForm, HttpSession session) {
		String jsonData = "";
		ActionResponse<DecreeForm> response = new ActionResponse<DecreeForm>();
		
		
		User us=userService.getByuserid(decreeForm.getDf_assign_to());
		
		if(us.getUserroles().get(0).getLk().getLk_longname().equals("Deputy Registrar(Decree)") && decreeForm.getDf_stage_lid() >= 4003L) {
			decreeForm.setDf_stage_lid(4008L);
		}
		
		DecreeForm mapping = noticeService.saveDecree(decreeForm);
		User user = (User) session.getAttribute("USER");
		
		DecreeStage ds =new DecreeStage();
		ds.setDs_cr_by(user.getUm_id());
		ds.setDs_df_mid(mapping.getDf_id());
		ds.setDs_stage_lid(mapping.getDf_stage_lid());
		ds.setDs_cr_date(new Date());
	//	ds.setDs_rec_status(1);
		
		ds= noticeService.saveDecreeStage(ds);
		
		
		
		response.setResponse("TRUE");
		response.setModelData(mapping);
		jsonData = globalfunction.convert_to_json(response);
		
		return jsonData;
		
	}
	
	
	
	@RequestMapping(value = "/saveDecreeForm", method = RequestMethod.POST)
	public @ResponseBody String saveDecreeForm(@RequestBody DecreeForm decreeForm, HttpSession session) {
		String jsonData = "";
		ActionResponse<DecreeForm> response = new ActionResponse<DecreeForm>();
		User user = (User) session.getAttribute("USER");
		List<UserRole> userroles = user.getUserroles();
		String userRole = "";		
		for (UserRole userrole : userroles) {
			userRole = userrole.getLk().getLk_longname();
		}
		
		if(decreeForm.getDf_id()==null) {
			
			decreeForm.setDf_stage_lid(4000L);
			decreeForm.setDf_cr_by(user.getUm_id());
			decreeForm.setDf_cr_date(new Date());
			decreeForm.setDf_assign_to(user.getUm_id());	
			decreeForm.setDf_rec_status(1);
			
			/*String s=decreeForm.getDf_4th_div().replace("Prepared By  <br/>  Decree-Writer  <br/> Date", "Prepared By :"+user.getUm_fullname()+"  <br/>  Decree-Writer  <br/> Date"+new Date());*/
			//String b=decreeForm.getDf_4th_div().matches("(?i).*Prepared By  <br/>  Decree-Writer  <br/> Date.*");
			if(decreeForm.getDf_4th_div().matches("(?i).*Prepared By  <br/>  Decree-Writer  <br/> Date.*")) {
				String s=decreeForm.getDf_4th_div().replaceAll("\\Prepared By  <br/>  Decree-Writer  <br/> Date\\","test");
				System.out.println(s);
			}
			
			
			
		}
		else if(decreeForm.getDf_stage_lid()==4000L){
			
			decreeForm.setDf_cr_by(user.getUm_id());
			decreeForm.setDf_cr_date(new Date());
			
		}
		else if(decreeForm.getDf_stage_lid()==4001L){
			decreeForm.setDf_stage_lid(4002L);
			decreeForm.setDf_exam_by(user.getUm_id());
			decreeForm.setDf_exam_date(new Date());
		}
		else if(decreeForm.getDf_stage_lid()==4003L){
			decreeForm.setDf_stage_lid(4004L);
			decreeForm.setDf_exam2_by(user.getUm_id());
			decreeForm.setDf_exam2_date(new Date());
		}
		else if(decreeForm.getDf_stage_lid()==4005L){
			decreeForm.setDf_stage_lid(4006L);
			decreeForm.setDf_exam3_by(user.getUm_id());
			decreeForm.setDf_exam3_date(new Date());
		}
		else if(decreeForm.getDf_stage_lid()==4008L){
			decreeForm.setDf_stage_lid(4008L);
			decreeForm.setDf_locked(true);
			decreeForm.setDf_approve_date(new Date());
			decreeForm.setDf_aprrove_by(user.getUm_id());
		}
		else {
			decreeForm.setDf_mod_by(user.getUm_id());
			decreeForm.setDf_mod_date(new Date());
			decreeForm.setDf_assign_to(user.getUm_id());
		}
		
		
		/*
		 * if (decreeForm.getDf_id() == null) { throw new
		 * RuntimeException("Invalid request: ID required for update"); }
		 */			
					DecreeForm mapping = noticeService.saveDecree(decreeForm);
					
					DecreeStage ds=new DecreeStage();
					ds.setDs_df_mid(mapping.getDf_id());
					ds.setDs_cr_date(new Date());
					ds.setDs_cr_by(user.getUm_id());
					ds.setDs_stage_lid(mapping.getDf_stage_lid());
					
					ds = noticeService.saveDecreeStage(ds);
					
						response.setResponse("TRUE");
						response.setModelData(mapping);
						jsonData = globalfunction.convert_to_json(response);
						/*try {
							createDecreePdf(mapping);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
						return jsonData;
					
		
	}
	
	@RequestMapping(value = "/getDecreeForm/{id}", method = RequestMethod.GET)
	public @ResponseBody String getDecreeForm(@PathVariable("id") Long cino,HttpSession session) {
		ActionResponse<DecreeForm> response = new ActionResponse<DecreeForm>();
		String jsonData = "";
		User user = (User) session.getAttribute("USER");
		DecreeForm types = noticeService.getDecreeForm(cino);		
				
		System.out.println("Decreeeeeeeeee form data typesss" +types);
		
		// List<DecreeStage> stage = noticeService.getDecreeStage(user.getUm_id()); 
		
		List<DecreeStage> stage = noticeService.getDecreeStage(cino);		
		
		
		if(types!=null && types.getDf_assign_to().equals(user.getUm_id()) && types.getDf_rec_status() != 0) {
		/*if(types!=null && types.getDf_assign_to().equals(user.getUm_id())) {*/
			response.setData("TRUE");
			response.setModelData(types);
			response.setDecreeStage(stage);
		
		}
		else {
			response.setData("FALSE");
			response.setModelData(types);
			response.setDecreeStage(stage);
		}
		
		
		jsonData = globalfunction.convert_to_json(response); 
		return jsonData;
	}
	
	
	@RequestMapping(value = "/getDeleteDecree/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String getDeleteDecree(@PathVariable("id") Long id) {
		ActionResponse<DecreeForm> response = new ActionResponse<DecreeForm>();
		String jsonData = null;	
		
		DecreeForm decree = new DecreeForm();
		decree = noticeService.getDecreeForm(id);
		if(decree.getDf_locked()==false) {
			decree.setDf_rec_status(0);
			noticeService.updateDecreeForm(decree);
		}
		
		
		response.setResponse("TRUE");
		jsonData = globalfunction.convert_to_json(response);

		return jsonData;

	}
	
	
	
	@RequestMapping(value = "/getDecreeCreator/{id}", method = RequestMethod.GET)
	public @ResponseBody String getDecreeCreator(@PathVariable("id") Long cino) {
		ActionResponse<List<User>> response = new ActionResponse<List<User>>();
		String jsonData = "";
		List<User> types = noticeService.getDecreeCreator(cino);
		
		if(types!=null) {
			response.setData("TRUE");
			response.setModelData(types);
		}
		else {
			response.setData("FALSE");
			response.setModelData(types);
		}
		
		jsonData = globalfunction.convert_to_json(response);
		return jsonData;
	}
	
	
	
	@RequestMapping(value = "/getAdv/{id}", method = RequestMethod.GET)
	public @ResponseBody String getAdv(@PathVariable("id") String cino) {
		ActionResponse<List<Object[]>> response = new ActionResponse<List<Object[]>>();
		String jsonData = "";
		//List<Object[]> types = noticeService.getPetCivic(cino);
		
		
		RestTemplate restTemplate = new RestTemplate();
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		 
		 MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		 
		 HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
		 
		 map.add("Caseid",cino);
		 
		 HttpEntity<Object> response1 =
			     restTemplate.exchange("http://192.168.0.114/testapi/API/CaseStatus/AdvocateDetailsByCaseId",
			                           HttpMethod.POST,
			                           entity,
			                           Object.class);
		 Object caseIntial=(Object) response1.getBody();
		
		 ObjectMapper m = new ObjectMapper();
		 m.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		 CcmsAdvoates props = m.convertValue(caseIntial, CcmsAdvoates.class);
		
		
		//response.setData("TRUE");
		response.setData(props);
		
		
		jsonData = globalfunction.convert_to_json(response);
		return jsonData;
	}
	
	
	@RequestMapping(value = "/getPet/{id}", method = RequestMethod.GET)
	public @ResponseBody String getPet(@PathVariable("id") String cino) {
		ActionResponse<List<Object[]>> response = new ActionResponse<List<Object[]>>();
		String jsonData = "";
		//List<Object[]> types = noticeService.getPetCivic(cino);
		
		
		RestTemplate restTemplate = new RestTemplate();
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		 
		 MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		 
		 HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
		 
		 map.add("Caseid",cino);
		 
		 HttpEntity<Object> response1 =
			     restTemplate.exchange("http://192.168.0.114/testapi/API/CaseStatus/PartyDetailsByCaseId",
			                           HttpMethod.POST,
			                           entity,
			                           Object.class);
		 Object caseIntial=(Object) response1.getBody();
		
		 ObjectMapper m = new ObjectMapper();
		 m.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		 CcmsPartyDetails props = m.convertValue(caseIntial, CcmsPartyDetails.class);
		
		
		//response.setData("TRUE");
		response.setData(props);
		
		
		jsonData = globalfunction.convert_to_json(response);
		return jsonData;
	}
	
	@RequestMapping(value = "/getLowerDetails/{id}", method = RequestMethod.GET)
	public @ResponseBody String getLowerDetails(@PathVariable("id") String cino) {
		ActionResponse<List<Object[]>> response = new ActionResponse<List<Object[]>>();
		String jsonData = "";
		//List<Object[]> types = noticeService.getLowerTrial(cino);
		
		
		RestTemplate restTemplate = new RestTemplate();
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		 
		 MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		 
		 HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
		 
		 map.add("Caseid",cino);
		 
		 HttpEntity<Object> response1 =
			     restTemplate.exchange("http://192.168.0.114/testapi/API/CaseStatus/LowerCourtDetailByCaseId",
			                           HttpMethod.POST,
			                           entity,
			                           Object.class);
		 List<Object> caseIntial=(List<Object>) response1.getBody();
		
		 ObjectMapper m = new ObjectMapper();
		 m.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		 CcmsLowerCourt props = m.convertValue(caseIntial.get(0), CcmsLowerCourt.class);
		
		
		response.setData(props);
		//response.setModelData(types);
		
		
		jsonData = globalfunction.convert_to_json(response);
		return jsonData;
	}
	
	@RequestMapping(value = "/getDecreeDR", method = RequestMethod.GET)
	public @ResponseBody String getDecreeDR(HttpSession session) {
		ActionResponse<List<DecreeExamDTO>> response = new ActionResponse<List<DecreeExamDTO>>();
		String jsonData = "";
		User u = (User) session.getAttribute("USER");
		List<DecreeExamDTO> types = noticeService.getDecreeForExam(u.getUm_id());
		
		response.setData("TRUE");
		response.setModelData(types);
		
		
		jsonData = globalfunction.convert_to_json(response);
		return jsonData;
	}
	
	
	@RequestMapping(value = "/upload_compo_files",method = RequestMethod.POST)
	public @ResponseBody String create(MultipartHttpServletRequest request,HttpSession session,HttpServletRequest req) throws DocumentException 
	{
		
		String jsonData="";

		ActionResponse<DecreeForm> response=new ActionResponse<DecreeForm>();
		response.setResponse("TRUE");
		String ipaddress = request.getRemoteAddr();
		String file_id=req.getParameter("rcd_id");
		DecreeForm df= new DecreeForm();
		
		df=noticeService.getDecreeForm(Long.parseLong(file_id));
		
		Integer count=noticeService.getDecreefileCount(df.getDf_id());
			
		User user=new User();
		user=(User) session.getAttribute("USER");
		
		
		Long rcd_id=Long.valueOf(file_id);
		

		String documentname = df.getDf_id().toString()+"_"+count+1;
 
		 MultipartFile mpf = null;
		 Iterator<String> itr = request.getFileNames();
		     
		     
		 String basePath="";
		 Lookup lookup = lookupService.getLookUpObject("REPOSITORYPATH");
		 String draftBasepath =/*lookupForRaw.get(0).getLk_longname()+File.separator+"criminaldetail";	*/
		 lookup.getLk_longname() + File.separator
			+ df.getCaseFileDetail().getCaseType().getCt_label()
			+ File.separator + "Decree" + File.separator;
			//CriminalDetailUpload cdu =new CriminalDetailUpload();
			
			List <Object> errorList=new ArrayList();

				while (itr.hasNext()) 
				{
					try
					{
					mpf = request.getFile(itr.next());
			     
					String filename = mpf.getOriginalFilename();  
					
					//Integer count =scrutinyService.getCount(rcd_id);
				String filename1 =rcd_id+"_"+(count+1)+".pdf";
			      /*  String temppath=draftBasepath + File.separator+filename1;*/
				  String temppath=draftBasepath + filename1;
			        
			        if(df.getDf_stage_lid()==4008L) {
			        	/*filename1=df.getCaseFileDetail().getFd_document_name()+"_DCR_"+(count+1)+".pdf";*/
			        	filename1=df.getCaseFileDetail().getFd_document_name()+"_DCR_"+(count+1);
			        	String finalfile =filename1+".pdf";
			        	temppath=draftBasepath + finalfile;
			        	
			        }
	                
					String ext = FilenameUtils.getExtension(filename);
					

					DecreeFileUploaded dfu=new DecreeFileUploaded();
					
					  if(ext.equalsIgnoreCase("pdf"))
						{	
						  	FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(temppath));
						    
						    PdfReader readernewFile = new PdfReader(temppath);
							Integer newPageCount =readernewFile.getNumberOfPages();
							readernewFile.close();
							 if(df.getDf_stage_lid()==4008L) {
								 df.setDf_final_file(filename1);
						//		 df.setDf_locked(true);
								 
								 SubDocument subDocument = new SubDocument();
									subDocument.setSd_cr_by(user.getUm_id());
									subDocument.setSd_cr_date(new Date());
									subDocument.setSd_fd_mid(df.getDf_fd_mid());
									subDocument.setSd_if_mid(44L);			//localdatabase index no 
								//	subDocument.setSd_if_mid(54L);			//live database index no change
									subDocument.setSd_version(1);
									subDocument.setSd_document_name(filename1);
								//	subDocument.setSd_document_id(at_id);
									subDocument.setSd_submitted_date(new Date());
									subDocument.setSd_rec_status(1);
								//	subDocument.setSd_minor_sequence(count);
									subDocument = subDocumentService.save(subDocument);
							 }
							 else {
							dfu.setDfu_fd_mid(df.getDf_fd_mid());
							dfu.setDfu_df_mid(df.getDf_id());
							dfu.setDfu_file_name(filename1);
							dfu.setDfu_rec_status(1);
							dfu.setDfu_uploaded_by(user.getUm_id());
							dfu.setDfu_uploaded_date(new Date());
							dfu=noticeService.saveDecreeFile(dfu);
							 }
					  
							df=noticeService.saveDecree(df);
						     
					       	response.setResponse("TRUE");
					       	response.setModelData(df);

						}
						 else 
						 {
							errorList.add(" Please Upload PDF file...!");
							response.setResponse("FALSE");
						 }	
				}
					catch (IOException e) {
					e.printStackTrace();
				}
			 			
		 }
		  
				//response.setDataMapList(error);
				response.setDataList(errorList);
				if(response != null)
				{
					jsonData = globalfunction.convert_to_json(response);
				}

		
		return jsonData;

	}
	
	// ======================================  Vijay chaurasiya  end===================================================	
	
	@RequestMapping(value = "/getDecreeExamList", method = RequestMethod.GET)
	public @ResponseBody ActionResponse<List<DecreeExamDTO>> getDecreeExamList(HttpSession session) {

	    User u = (User) session.getAttribute("USER");

	    List<DecreeExamDTO> types = noticeService.getDecreeForExam(u.getUm_id());

	    ActionResponse<List<DecreeExamDTO>> response = new ActionResponse<>();
	    response.setData("TRUE");
	    response.setModelData(types);

	    return response;
	}
	
	
	@RequestMapping(value = "/getApprovedDecreeList", method = RequestMethod.GET)
	public @ResponseBody ActionResponse<List<DecreeExamDTO>> getApprovedDecreeList(HttpSession session) {

	    User u = (User) session.getAttribute("USER");

	    List<DecreeExamDTO> types = noticeService.getApprovedDecree(u.getUm_id());

	    ActionResponse<List<DecreeExamDTO>> response = new ActionResponse<>();
	    response.setData("TRUE");
	    response.setModelData(types);

	    return response;
	}
	
	
	
	@RequestMapping(value = "/previewFile/{id}", method = RequestMethod.GET)
	public void previewFile(@PathVariable("id") Long id, HttpServletResponse response) {
	    try {
	        DecreeForm df = noticeService.getDecreeForm(id);

	        // Build file path (same logic as upload)
	        Lookup lookup = lookupService.getLookUpObject("REPOSITORYPATH");
	        String basePath = lookup.getLk_longname() + File.separator
	                + df.getCaseFileDetail().getCaseType().getCt_label()
	                + File.separator + "Decree" + File.separator;

	        String fileName = df.getDf_final_file() + ".pdf"; // adjust if needed
	        File file = new File(basePath + fileName);

	        if (file.exists()) {
	            response.setContentType("application/pdf");
	            response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");
	            
	            FileInputStream fis = new FileInputStream(file);
	            OutputStream os = response.getOutputStream();

	            byte[] buffer = new byte[1024];
	            int len;
	            while ((len = fis.read(buffer)) != -1) {
	                os.write(buffer, 0, len);
	            }

	            fis.close();
	            os.flush();
	        } else {
	            response.sendError(HttpServletResponse.SC_NOT_FOUND);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	
	
	
	@RequestMapping(value = "/getRes/{id}", method = RequestMethod.GET)
	public @ResponseBody String getRes(@PathVariable("id") String cino) {
		ActionResponse<List<Object[]>> response = new ActionResponse<List<Object[]>>();
		String jsonData = "";
		List<Object[]> types = noticeService.getResCivic(cino);
		
		response.setData("TRUE");
		response.setModelData(types);
		
		
		jsonData = globalfunction.convert_to_json(response);
		return jsonData;
	}
	
	

	@RequestMapping(value = "/uploadNotice", method = RequestMethod.POST)
	public @ResponseBody String uploadJudgement(
			MultipartHttpServletRequest request, HttpSession session)
					throws DocumentException {
		ActionResponse<SubDocument> response = new ActionResponse<SubDocument>();
		User u = (User) session.getAttribute("USER");
		String jsonData = "";
		Lookup lookup = lookupService.getLookUpObject("REPOSITORYPATH"); 

		Integer at_id=null;



		MultipartFile mpf = null;
		Iterator<String> itr = request.getFileNames();
		String newfilepath = "D:\\noticeform\\a.pdf";


		while (itr.hasNext()) {
			mpf = request.getFile(itr.next());

			SubDocument subDocument = new SubDocument();

			try {
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(
						newfilepath));
				File source = new File(newfilepath);
				PdfReader reader = new PdfReader(source.getAbsolutePath());   
				Integer no_of_pages = reader.getNumberOfPages();
				subDocument.setSd_no_of_pages(no_of_pages);
				System.out.println("file saveddddddddddddddddddddddd");
				//SendEmailWithAttachment.sendMail("bilalkhan0408@gmail.com","bilal.khan@nexsussolutions.com" , source);

				SendMail sm =new SendMail();
				sm.sendMail("sushant", "sushantmishra09@gmail.com","what are you doing","Heloooooo");


				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		response.setResponse("TRUE");

		return jsonData;
	}
	
	@RequestMapping(value = "/deleteFile/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteFile(@PathVariable("id") Long id,HttpSession session) {
		ActionResponse<DecreeForm> response = new ActionResponse<DecreeForm>();
		String jsonData = null;
	
		Long pfile=Long.valueOf(id);
		User u = (User) session.getAttribute("USER");
		//DecreeForm df = noticeService.getByFd(id);
		
          DecreeFileUploaded df1= new DecreeFileUploaded();
		
		df1=noticeService.getDecreeFile(id);
		
		df1.setDfu_rec_status(2);		
		noticeService.saveDecreeFile(df1);		
		response.setResponse("TRUE");
		jsonData = globalfunction.convert_to_json(response);

		return jsonData;

	}
	
	
	
	
	
	
	
	/*@RequestMapping(value = "/updateDecreeForm/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String updateDecreeForm(@PathVariable("id") Long id,HttpSession session) {
		ActionResponse<DecreeForm> response = new ActionResponse<DecreeForm>();
		String jsonData = null;
	
		
		
		response.setResponse("TRUE");
		jsonData = globalfunction.convert_to_json(response);

		return jsonData;

	}*/
	
	
	/*@RequestMapping(value = "/updateDecreeForm", method = RequestMethod.POST)
	public @ResponseBody String updateDecreeForm(
			@RequestBody DecreeForm dec, HttpSession session) {
		String jsonData = null;
		ActionResponse<DecreeForm> response = new ActionResponse<DecreeForm>();
		DecreeForm decree = noticeService.getDecree(dec.getDf_id());
		
		DecreeForm decree = new DecreeForm();
		decree.setDf_editor(dec.getDf_editor());
	
		System.out.println("decreeeeeeeeeeeeeeee user id " +decree);
		
		DecreeForm dc =noticeService.updateDecreeForm(decree);
	if(dc != null) {
		response.setResponse("TRUE");
		response.setModelData(dc);
	}
	else {
		response.setResponse("FALSE");
		
	}
		

		jsonData = globalfunction.convert_to_json(response);

		return jsonData;
	}*/
	
	
	
	
	// ======================================  Vijay chaurasiya start===================================================	
	@RequestMapping(value="/previewFile",method=RequestMethod.GET)
	@ResponseBody
	public String previewFile(HttpServletRequest request)
	{
		String jsonData = null;
		ActionResponse<DecreeForm> response = new ActionResponse<DecreeForm>();
		response.setResponse("TRUE");
		String file_id=request.getParameter("df_fd_mid");
		Long pfile=Long.valueOf(file_id);
		
		DecreeFileUploaded df1= new DecreeFileUploaded();
		
		df1=noticeService.getDecreeFile(Long.parseLong(file_id));
		
        DecreeForm df= new DecreeForm();
		
		df=noticeService.getDecreeForm(df1.getDfu_fd_mid());
		
		Lookup lookUp=lookupService.getLookUpObject("REPOSITORYPATH");	
	
		 String d_path =
				 lookUp.getLk_longname() + File.separator
					+ df.getCaseFileDetail().getCaseType().getCt_label()
					+ File.separator + "Decree" + File.separator;
	
		 String filename1 =df1.getDfu_file_name();
		File source = new File(d_path+File.separator+filename1);	
		
		String uploadPath = context.getRealPath("");
		
		File dest = new File(uploadPath+"/uploads/"+filename1);
		
		System.out.println("File dest " +dest);
		
	
			try {
				    FileUtils.copyFile(source, dest);
				    response.setResponse("TRUE");
				    response.setData(filename1);
				} 
				catch (IOException e) {
				    e.printStackTrace();
				    response.setResponse("FALSE");
				    response.setData("Error in displaying file");
				}
		
		jsonData = globalfunction.convert_to_json(response);
		return jsonData;
	}
	
	// ======================================  Vijay chaurasiya  end===================================================	
	
	
	
	
	
	
	
	/*change stage by devvvvvvvvvvv..........*/
    
	@RequestMapping(value = "/getDecreeRemark/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String getDecreeRemark(HttpServletRequest request,HttpSession session,@PathVariable("id")Long df_fd_mid) {
		String jsonData = null;
		Lookup lkStage = lookupService.getLookup("DMS_ROLE", "DECREE CREATOR");
		ActionResponse<List<DecreeForm>> response = new ActionResponse<List<DecreeForm>>();
		String df_id1 = request.getParameter("df_id");
		Long df_id = Long.valueOf(df_id1);
		String remark = request.getParameter("remark");
		DecreeForm ad = noticeService.getDecreeUser(df_fd_mid);
		
		
		DecreeStage ds = noticeService.getReturnDecreeStage(df_fd_mid);
		
		
		User user = (User) session.getAttribute("USER");
		
	//	noticeService.updateDecreeRemark(df_id,remark);
		try {
		
			ad.setDf_stage_lid(ds.getDs_stage_lid());
			ad.setDf_assign_to(ds.getDs_cr_by());
			ad.setDf_remark(remark);
			
			
			noticeService.updateDecreeForm(ad);	
		
			
			response.setResponse("SUBMIT");
			response.setData("RETURN TO DECREE");
	//		System.out.println(ds+"Parin _______________-+"+df);
		} catch (Exception e) {
			response.setResponse("");
			// TODO: handle exception
		}
		
		jsonData = globalfunction.convert_to_json(response);
		
		return jsonData;
	}
	
	
}
/*===========================End main Bracket==================================*/
class defaultFontProvider extends FontFactoryImp {

    private String _default;

    public defaultFontProvider(String def) {
        _default = def;
    }

    public Font getFont(String fontName, String encoding, boolean embedded, float size, int style, BaseColor color, boolean cached) {
        if (fontName == null || size == 0) {
            fontName = _default;
        }

        return super.getFont(fontName, encoding, embedded, 8.5f, style, color, cached);
    }

}
    
