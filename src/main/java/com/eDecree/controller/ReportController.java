package com.eDecree.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eDecree.model.ActionResponse;
import com.eDecree.model.CaseFileDetail;
import com.eDecree.model.DownloadFile;
import com.eDecree.model.DownloadReport;
import com.eDecree.model.Lookup;
import com.eDecree.model.SubDocument;
import com.eDecree.service.CaseFileDetailService;
import com.eDecree.service.DownloadFileService;
import com.eDecree.service.LookupService;
import com.eDecree.service.SubDocumentService;
import com.eDecree.utility.GlobalFunction;

@Controller
@RequestMapping("/reports")
public class ReportController {
	
	@Autowired
	private LookupService lookupService;
	
	@Autowired
	private SubDocumentService subDocumentService;
	@Autowired
	private CaseFileDetailService caseFileDetailService;
	@Autowired
	ServletContext context;
	
	@Autowired
	private DownloadFileService downloadService;
	
	private GlobalFunction globalfunction;
	
	public ReportController() {
		globalfunction = new GlobalFunction();
	}
	
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public String Download() {
		return "/reports/download";
	}

	@RequestMapping(value = "/officeReport", method = RequestMethod.GET)
	public String searchByParty() {
		return "/reports/officeReport";
	}
	
	@RequestMapping(value = "/getdownloadhistory", method = RequestMethod.GET)
	public @ResponseBody String getdownloadhistory(@RequestParam(value="itemsPerPage")int itemsPerPage,@RequestParam(value="pagenumber")int pagenumber) {
		String jsonData = null;
		ActionResponse<DownloadReport> response=new ActionResponse<>();
		
		List<DownloadReport> reports=downloadService.getDownloadReports(itemsPerPage,pagenumber);
		Integer total_count=downloadService.getDownloadReportsCount();
		response.setResponse("TRUE");
		response.setModelList(reports);
		response.setData(total_count);
		jsonData = globalfunction.convert_to_json(response);

		return jsonData;
	}
	@RequestMapping(value = "/getdownloadedfiles/{id}", method = RequestMethod.GET)
	public @ResponseBody String getdownloadedfiles(@PathVariable("id") Long dr_id,HttpSession session) {
		String jsonData = null;
		ActionResponse<DownloadFile> response=new ActionResponse<>();
		
		List<DownloadFile> files=downloadService.getFiles(dr_id);
		response.setResponse("TRUE");
		response.setModelList(files);
		
		jsonData = globalfunction.convert_to_json(response);

		return jsonData;
	}

	
	
	

}
