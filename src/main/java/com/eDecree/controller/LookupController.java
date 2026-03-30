package com.eDecree.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eDecree.model.ActionResponse;
import com.eDecree.model.Lookup;
import com.eDecree.model.User;
import com.eDecree.service.LookupService;
import com.eDecree.utility.GlobalFunction;
import com.eDecree.validation.LookupValidation;

@Controller
public class LookupController {
	
	public static final String lookup_master_view = "lookupmaster/lookupMaster";

	@Autowired
	LookupService lookUpService;
	
	@Autowired
	private LookupValidation lookupValidation;
	
	
	public LookupValidation getLookupValidation() {
		return lookupValidation;
	}



	public void setLookupValidation(LookupValidation lookupValidation) {
		this.lookupValidation = lookupValidation;
	}



	private GlobalFunction globalfunction;
	
	public LookupController()
	{
		globalfunction = new GlobalFunction();
	}
	
	

	@RequestMapping(value = "lookup/manage",method=RequestMethod.GET)
	public String index(Model model)
	{
		
		return lookup_master_view;
	}
	
	@RequestMapping(value="/lookup/getlookupdata",method=RequestMethod.GET)
	public  @ResponseBody String getData(Model model,HttpSession session)
	{
		String result = null;
		User user = (User) session.getAttribute("USER");
		ActionResponse<Lookup> response = new ActionResponse();
		
		//List<Lookup> lkdata=lookUpService.getAllByPriority(user.getPriority());
		List<Lookup> lkdata=new ArrayList<>();
		response.setModelList(lkdata);
		
		if (lkdata != null) {
			response.setResponse("TRUE");
			result = globalfunction.convert_to_json(response);
		} else {
			response.setResponse("FALSE");
		}

		return result;
		
	}
	
	@RequestMapping(value="/lookup/getsetNameList",method=RequestMethod.GET)
	public @ResponseBody String getsetName(Model model,HttpSession session)
	{
		String jsonData=null;
		User user = (User) session.getAttribute("USER");
		//List<Lookup> getsetName=lookUpService.getAllSetName(user.getPriority());
		List<Lookup> getsetName=new ArrayList<>();
		if(getsetName!=null)
		{
			jsonData=globalfunction.convert_to_json(getsetName);
		}
		System.out.println(jsonData);
		return jsonData;

	}
	
	@RequestMapping(value="/lookup/getparentList",method=RequestMethod.GET)
	public @ResponseBody String getparent(Model model)
	{
		String jsonData=null;
		List<Lookup> getparent=lookUpService.getAllParentName();
		
		if(getparent!=null)
		{
			jsonData=globalfunction.convert_to_json(getparent);
		}
		System.out.println(jsonData);
		return jsonData;

	}	
	@RequestMapping(value = "/lookup/save", method = RequestMethod.POST)	
	public @ResponseBody String save(@RequestBody Lookup lookup,HttpSession session)
	{	
		System.out.println("== USER SAVE ==");
		ActionResponse<Lookup> response = lookupValidation.doValidation(lookup);

		String jsonData = null;	
		if(response.getResponse() == "FALSE")
		{
			jsonData = globalfunction.convert_to_json(response);	
		}
		else
		{
			User user = (User) session.getAttribute("USER");
			
			if(lookup.getLk_parent()==null)
			{
				lookup.setLk_parent(0L);
			}
			if(lookup.getCr_by()==null){
			lookup.setCr_by(user.getUm_id());
			lookup.setCr_date(new Date());}
			else
			{
				lookup.setMod_by(user.getUm_id());
				lookup.setMod_date(new Date());
			}
			lookUpService.save(lookup);
		}	
		jsonData = globalfunction.convert_to_json(response);
		return jsonData;
	}

}
