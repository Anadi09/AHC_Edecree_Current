package com.eDecree.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eDecree.model.ActionResponse;
import com.eDecree.model.ObjectMaster;
import com.eDecree.service.ObjectMasterService;

@Component
public class ObjectMstrValidator {

	private boolean checkobj_name = true;

	@Autowired
	ObjectMasterService objectMasterService;

	public ActionResponse doValidation(ObjectMaster om) {

		ActionResponse response = new ActionResponse();
		Validator validation = new Validator();

		
		Map<String, List> error = new HashMap<String, List>();
		String status = "TRUE";

		validation.isRequired("Object Name", om.getOm_object_name());
		validation.isRequired("Objetc Link", om.getOm_object_link());
		validation.isRequiredDropDown("Object Parent", om.getOm_parent_id());
		validation.numericOnly("Object Stage", om.getOm_object_stages());

		error = validation.getError();

		/*if(om.getOm_object_name()== null || om.getOm_object_name().equals(""))
		 {
			if (this.checkobj_name ) {
				List<String> errorList = new ArrayList<String>();
				errorList = error.get("LongName");
				if (errorList == null) {
					errorList = new ArrayList<String>();
					errorList.add("Please Enter object name ");
					error.put("LongName", errorList);
				}
			}
		}*/
		if (om.getOm_object_name() != null && om.getOm_object_name().length()>0) {
			ObjectMaster objm = objectMasterService.checkExist(om.getOm_object_name());
			if (objm.getOm_id() != null) {
				if (om.getOm_id() != null) {
					if (om.getOm_id().longValue()  == objm.getOm_id().longValue() ) {
						this.checkobj_name = true;
					}
					
				} else {
					this.checkobj_name = false;
				}

				if (!this.checkobj_name) {

					List<String> errorList = new ArrayList<String>();
					errorList = error.get("LongName");
					if (errorList == null) {
						errorList = new ArrayList<String>();
					}
					if (objm.getOm_object_name() != null) {
						errorList.add("This ObjectName already exist");
						error.put("LongName", errorList);
					}

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
