package com.eDecree.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eDecree.model.ActionResponse;
import com.eDecree.model.CaseFileDetail;
import com.eDecree.model.Lookup;
import com.eDecree.model.User;
import com.eDecree.service.CaseFileDetailService;
import com.eDecree.service.LookupService;
import com.eDecree.service.MasterService;
import com.eDecree.utility.GlobalFunction;
import com.efiling.model.OLReport;
import com.efiling.service.LookupServiceEfiling;
import com.efiling.service.OLReportService;
import com.itextpdf.text.pdf.PdfReader;

@Controller
@RequestMapping("/reserve")
public class ReservedCaseController {
	
	@Autowired
	ServletContext context;

	@Autowired
	private LookupService lookupService;

	@Autowired
	private MasterService masterService;

	@Autowired
	private CaseFileDetailService caseFileDetailService;
		
	private GlobalFunction globalfunction;
	
	public ReservedCaseController() {
		
		globalfunction = new GlobalFunction();
	}
	
	@RequestMapping(value = "/reservedCase", method = RequestMethod.GET)
	public String ReservedCase() {
		
		return "/casefile/reservedCase";
		
	}
	
	@RequestMapping(value = "/judgeReservedCase", method = RequestMethod.GET)
	public String JudgeReservedCase() {
		
		return "/casefile/reservedCase";
		
	}
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manage()
	{
		return "/olreport/manage";
	}
	@RequestMapping(value = "/getAllReserveCase", method = RequestMethod.GET)
	public @ResponseBody String getAllReserveCase(HttpSession session)
	{
		ActionResponse<CaseFileDetail> response = new ActionResponse<CaseFileDetail>();
		String jsonData="";
		User user = (User) session.getAttribute("USER");
		List<CaseFileDetail> reserveCaseList = caseFileDetailService.getReservedCaseList(user.getUm_id());
		response.setModelList(reserveCaseList);
		response.setResponse("TRUE");
		jsonData=globalfunction.convert_to_json(response);
		return jsonData;
	}
	
	
	@RequestMapping(value = "/reservedCase/{id}", method = RequestMethod.GET)
	public @ResponseBody String reservedCase(@PathVariable("id") Long fd_id, HttpSession session)
	{
		String jsonData = null;
		ActionResponse<CaseFileDetail> response = new ActionResponse<CaseFileDetail>();
		User user = (User) session.getAttribute("USER");
		
		CaseFileDetail cfd =caseFileDetailService.getCaseFileDetail(fd_id);
		Boolean reservCaseFlag = true;
		if(cfd.getFd_rc_flag().equals(true))
		{
			reservCaseFlag = false;
			cfd.setFd_rc_flag(reservCaseFlag);
			cfd.setFd_assign_to(user.getUm_id());
		}
		else
		{
			cfd.setFd_assign_to(user.getUm_id());
			cfd.setFd_rc_flag(reservCaseFlag);
		}
		
		caseFileDetailService.save(cfd);
		
		response.setResponse("TRUE");
		response.setModelData(cfd);
		
		jsonData = globalfunction.convert_to_json(response);
		
		return jsonData;
	}
	
	@RequestMapping(value = "/removeReservedCase/{id}", method = RequestMethod.GET)
	public @ResponseBody String removeReservedCase(@PathVariable("id") Long fd_id, HttpSession session)
	{
		String jsonData = null;
		ActionResponse<CaseFileDetail> response = new ActionResponse<CaseFileDetail>();
		User user = (User) session.getAttribute("USER");
		CaseFileDetail cfd =caseFileDetailService.getCaseFileDetail(fd_id);
		Boolean reservCaseFlag = false;
		cfd.setFd_rc_flag(reservCaseFlag);
		cfd.setFd_assign_to(null);
		caseFileDetailService.save(cfd);
		response.setResponse("TRUE");
		response.setModelData(cfd);
		
		jsonData = globalfunction.convert_to_json(response);
		
		return jsonData;
	}
	
}
