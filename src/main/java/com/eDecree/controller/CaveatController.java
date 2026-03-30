package com.eDecree.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eDecree.model.ActionResponse;
import com.eDecree.model.CaseFileDetail;
import com.eDecree.model.Lookup;
import com.eDecree.model.ManualCaveat;
import com.eDecree.model.SubDocument;
import com.eDecree.model.User;
import com.eDecree.service.CaseFileDetailService;
import com.eDecree.service.CaseNominatedService;
import com.eDecree.service.CasetypeService;
import com.eDecree.service.CauseListService;
import com.eDecree.service.CaveatService;
import com.eDecree.service.CourtMasterService;
import com.eDecree.service.DownloadFileService;
import com.eDecree.service.LookupService;
import com.eDecree.service.MasterService;
import com.eDecree.service.OrderReportService;
import com.eDecree.service.SubDocumentService;
import com.eDecree.service.UserService;
import com.eDecree.utility.GlobalFunction;
import com.efiling.service.AddCaseEfilingService;
import com.efiling.service.AmendmentService;
import com.efiling.service.EilingApplicationService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;

@Controller
@RequestMapping("/caveat")
public class CaveatController {

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
	private AmendmentService amendmentService;

	@Autowired
	private AddCaseEfilingService addcaseEfilingService;

	@Autowired
	private CauseListService causeListService;

	@Autowired
	private CaseNominatedService caseNominatedService;

	@Autowired
	private CourtMasterService courtMasterService;
	
	


	@Autowired
	private EilingApplicationService eilingApplicationService;

	private GlobalFunction globalfunction;

	@Autowired
	private CaveatService caveatService;

	public CaveatController() {
		globalfunction = new GlobalFunction();
	}

	@RequestMapping(value = "/addCaveat", method = RequestMethod.GET)
	public String Manage() {

		return "caveat/addCaveat";

	}

	@RequestMapping(value = "/addManualCaveat", method = RequestMethod.POST)
	public @ResponseBody String addCaveat(MultipartHttpServletRequest request, HttpSession session)
			throws DocumentException {

		ActionResponse<ManualCaveat> response = new ActionResponse<ManualCaveat>();
		User u = (User) session.getAttribute("USER");
		String jsonData = "";
		Lookup lookup = lookupService.getLookUpObject("REPOSITORYPATH");
		
		ManualCaveat manualCaveat=null;
		Integer mcav_no = Integer.parseInt(request.getParameter("mcav_no"));
		Integer mcav_year = Integer.parseInt(request.getParameter("mcav_year"));
		String order_date = request.getParameter("mcav_submitted_date");
		Date date = new Date();

		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(order_date);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		manualCaveat=caveatService.getCaveatDetail(mcav_year , mcav_no);
		if (manualCaveat != null) 
		{
			response.setData("Caveat already exist in Efiling");
			response.setResponse("FALSE");
			
		}
		else {
		MultipartFile mpf = null;
		Iterator<String> itr = request.getFileNames();
	
		while (itr.hasNext()) {
			mpf = request.getFile(itr.next());
			String filename = mcav_no+"_"+mcav_year+".pdf";

			manualCaveat = new ManualCaveat();
			manualCaveat.setMcav_no(mcav_no);
			manualCaveat.setMcav_year(mcav_year);
			manualCaveat.setMcav_submitted_date(date);
			manualCaveat.setMcav_document_name(filename);
			manualCaveat.setMcav_stage_lid(1L);
			manualCaveat.setMcav_cr_date(new Date());
			String newfilepath = lookup.getLk_longname()+File.separator+"Manual_Caveat"+File.separator+ manualCaveat.getMcav_document_name();
			
			try {
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(newfilepath));
				File source = new File(newfilepath);
				PdfReader readernewFile = new PdfReader(source.getAbsolutePath());
				response.setData("Caveat added to Efiling Successfully");
				response.setResponse("TRUE");
				
				readernewFile.close();
				
				if(source.exists()) {
					caveatService.save(manualCaveat);
				}
				else {
					response.setData("Some Problem in uploading file");
					response.setResponse("FALSE");
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		}
		
		jsonData = globalfunction.convert_to_json(response);
		return jsonData;
	
	}
	
	@RequestMapping(value = "/getCaveatList", method = RequestMethod.POST)
	public @ResponseBody String getCaveatList(@RequestBody ManualCaveat manualCaveat, HttpSession session) {
		String jsonData = null;
		ActionResponse<ManualCaveat> response = new ActionResponse<>();
		List<ManualCaveat> caveatList = caveatService.getCaveatList(manualCaveat);
		
		for(ManualCaveat mc: caveatList) {
			if(mc != null && mc.getMcav_stage_lid() != null && mc.getMcav_fd_mid() != null) {
				if(mc.getMcav_stage_lid()==2L) {
					mc.setCaseFileDetail(caseFileDetailService.getCaseFileDetail(mc.getMcav_fd_mid()));
					
				}
						
			}
		}
		
		response.setResponse("TRUE");
		response.setModelList(caveatList);

		jsonData = globalfunction.convert_to_json(response);
		return jsonData;
	}	
	
	
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	public @ResponseBody String update(MultipartHttpServletRequest request,HttpSession session)
	{
		ActionResponse<ManualCaveat> response = new ActionResponse<>();
		User u = (User) session.getAttribute("USER");
		String jsonData = "";
		Lookup lookup = lookupService.getLookUpObject("REPOSITORYPATH");
		
		ManualCaveat manualCaveat=null;
		Integer mcav_no = Integer.parseInt(request.getParameter("mcav_no"));
		Integer mcav_year = Integer.parseInt(request.getParameter("mcav_year"));
		String order_date = request.getParameter("mcav_submitted_date");
	//	Date date = new Date();
		manualCaveat=caveatService.getCaveatDetail(mcav_year , mcav_no);
		if (manualCaveat != null && manualCaveat.getMcav_fd_mid() != null) 
		{
			response.setData("Caveat is already to its case");
			response.setResponse("FALSE");
			
			jsonData = globalfunction.convert_to_json(response);
			return jsonData;
			
		}
		
		
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(order_date);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		
		
		MultipartFile mpf = null;
		Iterator<String> itr = request.getFileNames();
		while (itr.hasNext()) 
		{
			mpf = request.getFile(itr.next());
			String filename = mpf.getOriginalFilename();
			
			
			manualCaveat.setMcav_document_name(filename);
			
			File oldFilename = new File(lookup.getLk_longname()+File.separator+"Manual_Caveat"+File.separator+ manualCaveat.getMcav_document_name());
			
			File RenameOldFile = new File(lookup.getLk_longname()+File.separator+"Manual_Caveat"+File.separator+ manualCaveat.getMcav_document_name()+" newFile ");
			
			int filecount=1;
			while(RenameOldFile.exists())
			{
				RenameOldFile = new File(lookup.getLk_longname()+File.separator+"Manual_Caveat"+File.separator+ manualCaveat.getMcav_document_name()+" newFile "+filecount);
				
				filecount++;	
			}
			
			oldFilename.renameTo(RenameOldFile);
			
			File newFile = new File(lookup.getLk_longname()+File.separator+"Manual_Caveat"+File.separator+ manualCaveat.getMcav_document_name());
			try {
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(newFile));
			} catch (FileNotFoundException e) {
			
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			manualCaveat.setMcav_modify_date(new Date());
			
            caveatService.save(manualCaveat);
			response.setResponse("TRUE");
			response.setModelData(manualCaveat);
		jsonData = globalfunction.convert_to_json(response);
		
		}
		  
		return jsonData;
	}
}
