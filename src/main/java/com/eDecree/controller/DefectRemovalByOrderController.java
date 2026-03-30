package com.eDecree.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eDecree.model.ActionResponse;
import com.eDecree.model.User;
import com.eDecree.service.LookupService;
import com.eDecree.utility.GlobalFunction;
import com.efiling.model.Amendment;
import com.efiling.model.DefectRemovalByOrder;
import com.efiling.model.EfilingLookup;
import com.efiling.service.AmendmentService;
import com.efiling.service.DefectRemovalByOrderService;
import com.efiling.service.LookupServiceEfiling;

@Controller
@RequestMapping("/defectremovalbyorder")
public class DefectRemovalByOrderController {
	
	private GlobalFunction cm;
	@Autowired
	ServletContext context;
	@Autowired
	private AmendmentService amendmentService;
	
	@Autowired
	private DefectRemovalByOrderService drbService;
	
	@Autowired
	private LookupServiceEfiling lookUpService;
	
	public DefectRemovalByOrderController() {
	cm = new GlobalFunction();
	}
	
	
	@RequestMapping(value = "/searchForDefectRemoval", method = RequestMethod.GET)
	public String addCaveat() {

		return "/defectremoval/searchcasefile";
	}
	
	
	@RequestMapping(value = "/manage/{id}", method = RequestMethod.GET)
	public String addApplication(Model model, @PathVariable Integer id) {

		model.addAttribute("fd_id", id);

		return "/defectremoval/manage";
	}
	
	
	@RequestMapping(value = "/getDrpHistory/{id}", method = RequestMethod.GET)
	public @ResponseBody String getAmendmentHistory(@PathVariable Long id, HttpSession session) {
		String jsonData = null;

		ActionResponse<Amendment> response = new ActionResponse<Amendment>();
		List<Amendment> amendments = amendmentService.getAmendments(id);
		if (amendments.size() > 0) {
			response.setModelList(amendments);
			response.setResponse("TRUE");
		} else {
			response.setResponse("FALSE");
			response.setData("No Records found");
		}
		jsonData = cm.convert_to_json(response);
		return jsonData;
	}
	
	@RequestMapping(value = "/addForDefectRemoval", method = RequestMethod.POST)
	@ResponseBody
	public String addAmendment(@RequestBody Amendment amendment, HttpSession session) {
		String jsonData = null;
		User u = (User) session.getAttribute("USER");
		ActionResponse<DefectRemovalByOrder> response = new ActionResponse<>();
		
		
		
		
		DefectRemovalByOrder drb1 =null;
		
		List<EfilingLookup> stages =lookUpService.getAllFromEfiling("DRP_STAGE");
		
		drb1 =drbService.getDefectByRemovalByOrder(amendment.getAm_fd_mid() ,stages);
		
		if(drb1 != null) {
			response.setResponse("False");
			response.setData("Case Already Assigned For Defect Removal");
			
		}
		else {
		
		DefectRemovalByOrder drb =new DefectRemovalByOrder();
		
		drb.setDrp_advUm_mid(amendment.getAm_um_mid());
		drb.setDrp_fd_mid(amendment.getAm_fd_mid());
		drb.setDrp_assign_to(amendment.getAm_um_mid());
		drb.setDrp_stage_lid(1000076L);
		drb.setDrp_cr_by(u.getUm_id());
		drb.setDrp_cr_date(new Date());
		
		
		drb =drbService.saveDefectRemovalByOrder(drb);
		
		response.setResponse("True");
		response.setData("SuccessFully Assigned For Defect Removal");
		response.setModelData(drb);
		jsonData = cm.convert_to_json(response);
		return jsonData;
		}
		jsonData = cm.convert_to_json(response);
		
		return jsonData;
	}

}
