package com.eDecree.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eDecree.model.ActionResponse;
import com.eDecree.model.AllowEfiling;
import com.eDecree.model.CaseFileDetail;
import com.eDecree.utility.GlobalFunction;
import com.efiling.model.EfilingCaseFileDetail;
import com.efiling.service.AddCaseEfilingService;
import com.efiling.service.AllowToEfilingService;
import com.efiling.service.AmendmentService;


@Controller
@RequestMapping("/allowtoefiling")
public class AllowToEfilingController {

	
	private GlobalFunction cm;
	@Autowired
	ServletContext context;
	@Autowired
	private AmendmentService amendmentService;
	@Autowired
	private AddCaseEfilingService addcaseEfilingService;
	
	@Autowired
	private AllowToEfilingService aes;

	String case_year = null;
	String case_type = null;
	String caseno = null;

	public AllowToEfilingController() {
		cm = new GlobalFunction();
	}
	
	
	@RequestMapping(value = "/getallowform", method = RequestMethod.GET)
	public String getForm() {
		
		return "allowtoefiling/getallowform";
		
	}
	
	
	@RequestMapping(value = "/searchCaseFileForAllow", method = RequestMethod.GET)
	@ResponseBody
	public String searchCaseFile(HttpServletRequest request) {

		case_year = request.getParameter("case_year");
		case_type = request.getParameter("case_type");
		caseno = request.getParameter("case_no");

		Long caseyear = new Long(case_year);
		Long casetype = new Long(case_type);

		List<EfilingCaseFileDetail> cfd = amendmentService.getCaseFile(caseyear, casetype, caseno);

		ActionResponse<EfilingCaseFileDetail> response = new ActionResponse<EfilingCaseFileDetail>();
		String jsonData = null;

		if (cfd != null) {
			response.setResponse("TRUE");
			response.setModelList(cfd);
			jsonData = cm.convert_to_json(response);

		} else {
			response.setResponse("FALSE");
			jsonData = cm.convert_to_json(response);
		}
		return jsonData;

	}
	
	
	
	@RequestMapping(value = "/saveCaseVerificationCode", method = RequestMethod.POST)
	public @ResponseBody String getCaseList(
			@RequestBody AllowEfiling allowEfiling, HttpSession session) {     
		ActionResponse<AllowEfiling> response = new ActionResponse<>();
		System.out.println("allow efilingggggggggg"+allowEfiling);
		
		AllowEfiling ae =null;
		
		allowEfiling.setAe_status(1);
		allowEfiling.setAe_allow_for('C');
		
		
		String jsonData = null;
		
		if(aes.getAeCode(allowEfiling.getAe_code()) != null) {
			response.setResponse("already exists");
			jsonData = cm.convert_to_json(response);

			return jsonData;
			
		} 
		
		ae =aes.saveAllowCase(allowEfiling);
		
		
		
		response.setResponse("TRUE");
		response.setModelData(ae);

		jsonData = cm.convert_to_json(response);

		return jsonData;
	}
	
	//RequestMapping(value = "/saveApplicationVerificationCode", method = RequestMethod.POST)
	@RequestMapping(value = "/saveApplicationVerificationCode", method = RequestMethod.POST)
	public @ResponseBody String getApplicationForAllow(
			@RequestBody AllowEfiling allowEfiling, HttpSession session) {     
		ActionResponse<AllowEfiling> response = new ActionResponse<>();
		System.out.println("allow efilingggggggggg"+allowEfiling);
		
		AllowEfiling ae =null;
		
		allowEfiling.setAe_status(1);
		allowEfiling.setAe_allow_for('A');
		
		
		
		String jsonData = null;
		
		if(aes.getAeCode(allowEfiling.getAe_code()) != null) {
			response.setResponse("already exists");
			jsonData = cm.convert_to_json(response);

			return jsonData;
			
		} 
		
		ae =aes.saveAllowCase(allowEfiling);
		
		
		
		response.setResponse("TRUE");
		response.setModelData(ae);

		jsonData = cm.convert_to_json(response);

		return jsonData;
	}
	
	@RequestMapping(value = "/addCaseEflingForAllow", method = RequestMethod.POST)
	@ResponseBody
	public String addCaseEfling(@RequestBody CaseFileDetail cfd,HttpSession session) {

		ActionResponse<EfilingCaseFileDetail> response = new ActionResponse<EfilingCaseFileDetail>();
		String jsonData = null;
		Long caseType =cfd.getFd_case_type();
		String caseNo= cfd.getFd_case_no();
		Integer year = cfd.getFd_case_year();
		Long caseYear= new Long(year);
		
		EfilingCaseFileDetail efilingcfd = amendmentService.getEilingCaseFileDetails(caseYear, caseType, caseNo);	
		if (efilingcfd!= null) 
		{
		/*	efilingcfd.setFd_first_petitioner(cfd.getFirst_petitioner());
			efilingcfd.setFd_first_respondent(cfd.getFirst_respondent());
			addcaseEfilingService.addCaseEfling(efilingcfd);*/
			response.setData("Case already exist in Efiling");
			response.setResponse("TRUE");
		}
		else 
		{
			EfilingCaseFileDetail ecfd1 =null;
			EfilingCaseFileDetail ecfd2 =null;
			EfilingCaseFileDetail ecfd = new EfilingCaseFileDetail();
			ecfd.setFd_case_type(cfd.getFd_case_type());
			ecfd.setFd_case_no(cfd.getFd_case_no());
			ecfd.setFd_case_year(cfd.getFd_case_year());
			//ecfd.setFd_first_petitioner(cfd.getFirst_petitioner());
			//ecfd.setFd_first_respondent(cfd.getFirst_respondent());
			ecfd.setFd_cr_by(90009L);
			ecfd.setFd_cr_date(new Date());
			ecfd.setFd_rec_status(1);
			
			response.setData("Case added to Efiling Successfully");
			response.setResponse("TRUE");
			
			ecfd1 =addcaseEfilingService.saveEfiling(ecfd);
			ecfd2 =addcaseEfilingService.getCaseFromEfiling(ecfd1.getFd_id());
			response.setModelData(ecfd2);
		}
		if (response != null)
			jsonData = cm.convert_to_json(response);

		return jsonData;
	}
}
