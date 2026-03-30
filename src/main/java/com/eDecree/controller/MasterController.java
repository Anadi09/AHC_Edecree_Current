package com.eDecree.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eDecree.model.ActionResponse;
import com.eDecree.model.ApplicationTypes;
import com.eDecree.model.CaseType;
import com.eDecree.model.District;
import com.eDecree.model.IndexField;
import com.eDecree.model.Judge;
import com.eDecree.model.Lookup;
import com.eDecree.model.LowerCourtCaseType;
import com.eDecree.model.User;
import com.eDecree.service.LookupService;
import com.eDecree.service.MasterService;
import com.eDecree.utility.GlobalFunction;

@Controller
@RequestMapping("/master")
public class MasterController {

	static int seq = 0;
	private GlobalFunction globalfunction;
	
	@Autowired
	private LookupService lookupService;
	
	@Autowired
	private MasterService masterService;
	
	public MasterController() {
		// TODO Auto-generated constructor stub
		globalfunction = new GlobalFunction();
	}

	@RequestMapping(value = "/getcasetypes", method = RequestMethod.GET)
	public @ResponseBody String getCaseTypes(HttpSession session) {
		User user=(User) session.getAttribute("USER");
		String role=user.getUserroles().get(0).getLk().getLk_longname();
		String jsonData="";
		ActionResponse<CaseType> response=new ActionResponse<>();
		List<CaseType> caseTypes=new ArrayList<>();
		if(role.equals("Review_Officer") || role.equals("Assistant Review Officer"))
		{
			caseTypes=masterService.getCaseTypesByUser(user.getUm_id());
		}
		else{
			caseTypes=masterService.getCaseTypes();
		}
		response.setModelList(caseTypes);
		
		jsonData=globalfunction.convert_to_json(response);
		
		return jsonData;

	}
	
	@RequestMapping(value = "/getcasetypesforchange", method = RequestMethod.GET)
	public @ResponseBody String getCaseTypesForChange(HttpSession session) {
		User user=(User) session.getAttribute("USER");
		String role=user.getUserroles().get(0).getLk().getLk_longname();
		String jsonData="";
		ActionResponse<CaseType> response=new ActionResponse<>();
		List<CaseType> caseTypes=new ArrayList<>();
		
			caseTypes=masterService.getCaseTypes();
		
		response.setModelList(caseTypes);
		
		jsonData=globalfunction.convert_to_json(response);
		
		return jsonData;

	}
	
	@RequestMapping(value = "/getindexfields", method = RequestMethod.GET)
	public @ResponseBody String getIndexFields(HttpSession session) {
		String jsonData="";
		User u=(User) session.getAttribute("USER");
		ActionResponse<IndexField> response=new ActionResponse<>();
		List<IndexField> indexFields=null;
		if(u.getUserroles().get(0).getLk().getLk_longname().equals("Stamp_Reporter")) {
			indexFields=masterService.getSelectecdIndexFieldsStamp();
		}
		else {
			indexFields=masterService.getSelectecdIndexFields();
		}
		
		
		response.setModelList(indexFields);		
		jsonData=globalfunction.convert_to_json(response);		
		return jsonData;
	}
	
	@RequestMapping(value = "/getapplications", method = RequestMethod.GET)
	public @ResponseBody String getOrdersList() {
		String jsonData="";
		ActionResponse<ApplicationTypes> response=new ActionResponse<>();
		List<ApplicationTypes> indexFields=masterService.getApplicationsByType("ORDER");
		response.setModelList(indexFields);		
		jsonData=globalfunction.convert_to_json(response);		
		return jsonData;
	}
	
	@RequestMapping(value = "/getApplicationsTypeList", method = RequestMethod.GET)
	public @ResponseBody String getapplicationTypelist() 
	{
		String jsonData="";
		ActionResponse<ApplicationTypes> response=new ActionResponse<>();
		List<ApplicationTypes> applicationType = masterService.getApplicationsTypeList();
		response.setModelList(applicationType);		
		jsonData=globalfunction.convert_to_json(response);		
		return jsonData;
	}
	
	@RequestMapping(value = "/getapplications/{id}", method = RequestMethod.GET)
	public @ResponseBody String getApplications(@PathVariable("id") Long if_id,Model model,HttpSession session) 
	{
		User user=(User) session.getAttribute("USER");
		String role=user.getUserroles().get(0).getLk().getLk_longname();
		
		String jsonData="";
		ActionResponse<ApplicationTypes> response=new ActionResponse<>();
		IndexField index_field=masterService.getIndexField(if_id);
		
		List<ApplicationTypes> indexFields=masterService.getApplicationsByType(index_field.getIf_label());
		if(role.equals("Review_Officer"))
		{
			indexFields.remove(1);
			response.setModelList(indexFields);
		
		}
		else
		{
			response.setModelList(indexFields);
		}
		jsonData=globalfunction.convert_to_json(response);		
		return jsonData;
	}
	@RequestMapping(value = "/getapplicationstages", method = RequestMethod.GET)
	public @ResponseBody String getApplicationstages(Model model) {
		String jsonData="";
		ActionResponse<Lookup> response=new ActionResponse<>();
		List<Lookup> stages=lookupService.getAll("APPLICATION_FILE_STAGES");
		response.setModelList(stages);		
		jsonData=globalfunction.convert_to_json(response);		
		return jsonData;
	}
	@RequestMapping(value = "/gethighcourtjudges", method = RequestMethod.GET)
	public @ResponseBody String getHighcourtJudges(Model model) {
		String jsonData="";
		ActionResponse<Judge> response=new ActionResponse<>();
		List<Judge> stages=lookupService.getHighCourtJudges();
		response.setModelList(stages);		
		jsonData=globalfunction.convert_to_json(response);		
		return jsonData;
	}
	@RequestMapping(value = "/getlccasetypes", method = RequestMethod.GET)
	public @ResponseBody String getLCCaseTypes(HttpSession session) {
		String jsonData="";
		ActionResponse<LowerCourtCaseType> response=new ActionResponse<>();
		List<LowerCourtCaseType> caseTypes=new ArrayList<>();
		
		caseTypes=masterService.getLowerCourtCaseTypes();
		
		response.setModelList(caseTypes);
		
		jsonData=globalfunction.convert_to_json(response);
		
		return jsonData;

	}
	@RequestMapping(value = "/getdistricts", method = RequestMethod.GET)
	public @ResponseBody String getdistricts() {
		String jsonData="";
		ActionResponse<District> response=new ActionResponse<>();
		List<District> districts=masterService.getDistricts();
		response.setModelList(districts);		
		jsonData=globalfunction.convert_to_json(response);		
		return jsonData;
	}
}
