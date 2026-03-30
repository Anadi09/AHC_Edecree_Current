package com.eDecree.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eDecree.model.ActionResponse;
import com.eDecree.model.CauseList;
import com.eDecree.model.CauseListType;
import com.eDecree.model.CourtMaster;
import com.eDecree.model.CourtUserMapping;
import com.eDecree.model.Sub_Benches;
import com.eDecree.model.User;
import com.eDecree.model.UserRole;
import com.eDecree.service.BenchService;
import com.eDecree.service.CaseFileDetailService;
import com.eDecree.service.CauseListService;
import com.eDecree.service.CourtMasterService;
import com.eDecree.service.LookupService;
import com.eDecree.service.MasterService;
import com.eDecree.utility.GlobalFunction;

@Controller
@RequestMapping("/bench")
public class BenchController {
	
	
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

	public BenchController() {
		globalfunction = new GlobalFunction();
	}
	
	
	@RequestMapping(value = "/manageBench", method = RequestMethod.GET)
	public String adminHome() {

		return "/bench/manageBench";

	}
	
	
	@RequestMapping(value = "/deleteSubBenches/{id}/", method = RequestMethod.DELETE)
	@ResponseBody
	public String deletePetitioner(@PathVariable Long id, HttpSession session) {

		Sub_Benches response = null;

		User user = (User) session.getAttribute("USER");
		ActionResponse<Sub_Benches> pd = new ActionResponse<Sub_Benches>();
		String jsonData = null;

		response = benchService.deleteSubBeches(id);
		if (response != null) {
			pd.setResponse("TRUE");
			jsonData = globalfunction.convert_to_json(pd);

		}
		return jsonData;

	}
	
	@RequestMapping(value = "/addBenches", method = RequestMethod.POST)
	public @ResponseBody String addBenches(@RequestBody Sub_Benches sb, HttpSession session) {
		String jsonData = "";
		ActionResponse<Sub_Benches> response = new ActionResponse<Sub_Benches>();
		User user = (User) session.getAttribute("USER");
		List<UserRole> userroles = user.getUserroles();
		String userRole = "";
		for (UserRole userrole : userroles) {
			userRole = userrole.getLk().getLk_longname();
		}
		
		sb.setSb_rec_status(1);
		
		if(!sb.isUpdateFlag()) {
		
		if(sb.getSb_bench_id() != null) {
			Sub_Benches cm1	= benchService.getBenches(sb.getSb_bench_id());
			CourtMaster cmm1	= benchService.getCourtByBenchId(sb.getSb_bench_id());
			
			if(cm1 != null || cmm1 != null) {
				
				response.setResponse("FALSE");
				response.setModelData(cm1);
				jsonData = globalfunction.convert_to_json(response);
				return jsonData;
				
				
				
			}
		}
		
		
			Sub_Benches mapping = benchService.addBenches(sb);
		
	
if(mapping != null) {
		
		response.setResponse("TRUE");
		response.setModelData(sb);
}
else {
	response.setResponse("FALSE");
}
		jsonData = globalfunction.convert_to_json(response);
		return jsonData;
		
	}
		
		else {
			if(sb.getSb_bench_id() != null) {/*
				Sub_Benches cm2	=  benchService.getBenches(sb.getSb_bench_id());
				CourtMaster cmm2	= benchService.getCourtByBenchId(sb.getSb_bench_id());
				if(cm2 != null || cmm2 != null) {
					
					cm2.setSb_bench_id(null);
					Sub_Benches mapping = benchService.addBenches(cm2);
					if(cm2 != null) {
						Sub_Benches mapping1 = benchService.addBenches(sb);
						response.setResponse("TRUE");
						response.setModelData(mapping1);
						jsonData = globalfunction.convert_to_json(response);
						return jsonData;
					}
					
					
					
					
				}
			*/}
			return jsonData;
		}
	}
	
	@RequestMapping(value = "/updateCourt", method = RequestMethod.POST)
	public @ResponseBody String getCauseList(@RequestBody CourtMaster courtmaster, HttpSession session) {
		String jsonData = "";
		ActionResponse<CourtMaster> response = new ActionResponse<CourtMaster>();
		User user = (User) session.getAttribute("USER");
		List<UserRole> userroles = user.getUserroles();
		String userRole = "";
		for (UserRole userrole : userroles) {
			userRole = userrole.getLk().getLk_longname();
		}
		
		if(!courtmaster.isUpdateFlag()) {
		
		if(courtmaster.getCm_bench_id() != null) {
			CourtMaster cm1	= benchService.getCourtByBenchId(courtmaster.getCm_bench_id());
			
			if(cm1 != null) {
				
				response.setResponse("FALSE");
				response.setModelData(cm1);
				jsonData = globalfunction.convert_to_json(response);
				return jsonData;
				
				
				
			}
		}
		
		
			CourtMaster mapping = benchService.updateCourt(courtmaster);
		
	
if(mapping != null) {
		
		response.setResponse("TRUE");
		response.setModelData(courtmaster);
}
else {
	response.setResponse("FALSE");
}
		jsonData = globalfunction.convert_to_json(response);
		return jsonData;
		
	}
		
		else {
			if(courtmaster.getCm_bench_id() != null) {
				CourtMaster cm2	= benchService.getCourtByBenchId(courtmaster.getCm_bench_id());
				
				if(cm2 != null) {
					
					cm2.setCm_bench_id(null);
					CourtMaster mapping = benchService.updateCourt(cm2);
					if(cm2 != null) {
						CourtMaster mapping1 = benchService.updateCourt(courtmaster);
						response.setResponse("TRUE");
						response.setModelData(mapping1);
						jsonData = globalfunction.convert_to_json(response);
						return jsonData;
					}
					
					
					
					
				}
			}
			return jsonData;
		}
	}
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)	
	public @ResponseBody String create(@RequestBody CourtMaster cm,HttpSession session) {	
		String jsonData="";
		ActionResponse<CourtMaster> response = new ActionResponse<>();		
		
		User user = (User) session.getAttribute("USER");
		cm.setCm_cr_by(user.getUm_id());
		cm.setCm_cr_date(new Date());
		if(cm.getCm_rec_status()==1) {
			cm.setCm_name("Court_"+cm.getCm_name());
		}
		
		if(cm.getCm_rec_status()==3) {
			cm.setCm_name("In-Chamber-"+cm.getCm_name());
		}
		
		CourtMaster cmExist=benchService.getCourtByBenchName(cm.getCm_name());
		if(cmExist!=null) {
			response.setResponse("FALSE");
			response.setData("Court Already Exist");
		}
		else {
			cm=benchService.updateCourt(cm);
			if(cm!=null) {
				response.setResponse("TRUE");
				response.setData("Court Added Successfully");
			}
		}
		
		
		jsonData = globalfunction.convert_to_json(response);

		return jsonData;
	}
	
	
	@RequestMapping(value = "/getAllCourts", method = RequestMethod.GET)
	public @ResponseBody String getCauseListTypes() {
		ActionResponse<CourtMaster> response = new ActionResponse<CourtMaster>();
		String jsonData = "";
		List<CourtMaster> types = benchService.getAllCourts();
		if(!types.isEmpty()) {
		response.setData("TRUE");
		response.setModelList(types);
		
		}
		else {
			response.setData("FALSE");
			
		}
		jsonData = globalfunction.convert_to_json(response);
		return jsonData;
	}

}
