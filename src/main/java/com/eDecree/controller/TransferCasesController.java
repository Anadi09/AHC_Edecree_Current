package com.eDecree.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eDecree.model.ActionResponse;
import com.eDecree.model.CauseList;
import com.eDecree.model.User;
import com.eDecree.service.BenchService;
import com.eDecree.service.CaseFileDetailService;
import com.eDecree.service.CauseListService;
import com.eDecree.service.CourtMasterService;
import com.eDecree.service.LookupService;
import com.eDecree.service.MasterService;
import com.eDecree.utility.GlobalFunction;

@Controller
@RequestMapping("/transferCase")
public class TransferCasesController {
	
	@Autowired
	CauseListService causeListService;
	@Autowired
	ServletContext context;

	@Autowired
	CourtMasterService courtService;

	@Autowired
	LookupService lookupService;

	@Autowired
	private CaseFileDetailService caseFileDetailService;

	@Autowired
	MasterService masterService;
	
	@Autowired
	CourtMasterService courtMasterService;
	
	@Autowired
	BenchService benchService;
	
	
	private GlobalFunction globalfunction;
	
	public TransferCasesController() {
		globalfunction = new GlobalFunction();
	}
	
	@RequestMapping(value = "/getTransferPage", method = RequestMethod.GET)
	public String adminHome() {

		return "/bench/transferCases";

	}
	
	
	@RequestMapping(value = "/submitTransferCases", method = RequestMethod.POST)
	public @ResponseBody String submitTransferCases(@RequestBody List<CauseList> causeList, HttpSession session) 
	{
		String jsonData = null;
		ActionResponse<CauseList> response=new ActionResponse<>();
		User u=(User) session.getAttribute("USER");
	
		/*causeList.setCaseType(causeList.getCaseType());
		causeList.setCl_case_no(causeList.getCl_case_no());
		causeList.setCl_case_year(causeList.getCl_case_year());
		causeList.setCl_ano(causeList.getCl_ano());
		causeList.setCl_ayr(causeList.getCl_ayr());
		causeList.setCl_serial_no(causeList.getCl_serial_no());
		causeList.setCl_list_type_mid(causeList.getCl_list_type_mid());
		causeList.setCl_court_no(causeList.getCl_court_no());
		causeList.setCl_dol(causeList.getCl_dol1());
		causeList.setCl_first_petitioner(causeList.getCl_first_petitioner());
		causeList.setCl_first_respondent(causeList.getCl_first_respondent());
		causeList.setCl_petitioner_council(causeList.getCl_petitioner_council());
		causeList.setCl_respondent_council(causeList.getCl_respondent_council());
		causeList.setCl_rec_status(1);*/
		
		for(CauseList cl :causeList) {
			if(cl.getCl_transfer_to() != null) {
			if(cl.getCl_court_no().equals(cl.getCl_transfer_to())) {
				cl.setCl_rec_status(1);	
				cl.setCl_transfer_to(null);
			}
			else {
				if(cl.isChecked()) {
					cl.setCl_rec_status(3);
					
				}
				else {
					cl.setCl_rec_status(1);	
					cl.setCl_transfer_to(null);
				}
			}
		}
			causeListService.save(cl);
		}
		
		response.setResponse("TRUE");
		response.setData("Case Transfered Successfully");
		
		jsonData = globalfunction.convert_to_json(response);
		return jsonData;
			
	}
	
	
	@RequestMapping(value = "/submitAllTransferCases", method = RequestMethod.POST)
	public @ResponseBody String submitAllTransferCases(@RequestBody CauseList causeList, HttpSession session) 
	{
		String jsonData = null;
		ActionResponse<CauseList> response=new ActionResponse<>();
		User u=(User) session.getAttribute("USER");
	
		System.out.println("Submit All Called "+causeList);
		
		
		
		response.setResponse("TRUE");
		response.setData("Case Transfered Successfully");
		
		jsonData = globalfunction.convert_to_json(response);
		return jsonData;
			
	}
	
	

}
