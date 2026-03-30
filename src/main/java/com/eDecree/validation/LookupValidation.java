package com.eDecree.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eDecree.model.ActionResponse;
import com.eDecree.model.Lookup;
import com.eDecree.service.LookupService;

@Component
public class LookupValidation {

	@Autowired
	LookupService lookupService;

	private boolean checkLk_setname = true;

	private boolean checkLk_longname = true;

	public void setCheckLk_longname(boolean checkLk_longname) {
		this.checkLk_longname = checkLk_longname;
	}

	public boolean checkLk_setname() {
		return checkLk_setname;

	}

	public void setCheckLk_setname(boolean checkLk_setname) {
		this.checkLk_setname = checkLk_setname;
	}

	public ActionResponse doValidation(Lookup lookup) {
		ActionResponse response = new ActionResponse();
		Validator validation = new Validator();
		Map<String, List> error = new HashMap<String, List>();
		String status = "TRUE";

		validation.isRequired("Lookup Name", lookup.getLk_longname());
	
		error = validation.getError();
		if(lookup.getLk_setname() == null || lookup.getLk_setname().equals(""))
		 {
			if (this.checkLk_setname) {
				List<String> errorList = new ArrayList<String>();
				errorList = error.get("setname");
				if (errorList == null) {
					errorList = new ArrayList<String>();
					errorList.add("Please Select Lookup setname ");
					error.put("setname", errorList);
				}
			}
		}
		if (lookup.getLk_longname() != null
				&& !lookup.getLk_longname().isEmpty()) {
			Lookup lukup = lookupService.getLookup(lookup.getLk_setname(),lookup.getLk_longname());
			if (lukup.getLk_id() != null) {
				if (lookup.getLk_id() != null) {
					if (lookup.getLk_id() == lukup.getLk_id()) {
						this.checkLk_longname = true;
					}
					this.checkLk_longname=false;
				} else {
					this.checkLk_longname = false;
				}

				if (!this.checkLk_longname) {

					List<String> errorList = new ArrayList<String>();
					errorList = error.get("LongName");
					if (errorList == null) {
						errorList = new ArrayList<String>();
					}
					if (lukup.getLk_longname() != null) {
						errorList.add("This LookupName already exist");
						error.put("LongName", errorList);
					}

				}
			}
		}
		
		if(lookup.getLk_setname().equals( "PASSWORD_EXPIRY_DAYS") || lookup.getLk_setname().equals("REGEX_COMPLEXITY"))
		 {	
			List<Lookup> lukup= lookupService.getAll("PASSWORD_EXPIRY_DAYS");
			List<Lookup> lukup1= lookupService.CheckRegex("REGEX_COMPLEXITY");
			if(lukup.size()!=0 || lukup.size()!=0)
			{
				List<String> errorList = new ArrayList<String>();
				errorList = error.get("setname");
				if (errorList == null) {
					errorList = new ArrayList<String>();
					errorList.add("Duplicate values for  "+lukup.get(0).getLk_setname() +" and "+lukup1.get(0).getLk_setname()+" are not allowed.");
					error.put("setname", errorList);
				}
			 
			}
		 }
		if (!error.isEmpty()) {
			status = "FALSE";
		}

		response.setResponse(status);
		response.setDataMapList(error);
		return response;
	}
}
