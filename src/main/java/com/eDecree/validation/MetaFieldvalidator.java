package com.eDecree.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.eDecree.model.ActionResponse;
import com.eDecree.model.MetaField;


@Component
public class MetaFieldvalidator {
	
	public ActionResponse doValidation(MetaField m){
		
		ActionResponse response = new ActionResponse();
		Validator validation=new Validator();
		

		List<String> errorList = new ArrayList<String>();
		Map<String, List> error = new HashMap<String, List>();
		String status = "TRUE";
		
		validation.isRequired("name", m.getMf_name());
		validation.alphabetsOnly("Name", m.getMf_name());
		validation.maxLength("name", m.getMf_name(), 20);
	
		
		validation.isRequired("Lable", m.getMf_lable());
		validation.alphabetsOnly("Lable",m.getMf_lable());
		validation.maxLength("Lable",m.getMf_lable(), 20);
		
		//validation.isRequiredDropDownList("Type", m.getMf_type());
		
	//	validation.isRequiredDropDownList("Table Name", m.getMf_table_name());
	//	validation.isRequired("Table Id", m.getMf_table_key_column());
		//validation.isRequiredDropDownList("Table Column", m.getMf_table_display_column());
		validation.isRequired("multiple ", m.getMf_add_multiple());
		
		//validation.isRequired("Serial", m.getMf_sequence());
		//validation.maxLength("Serial", m.getMf_sequence(), 3);
		
			
		error=validation.getError();
		if(!error.isEmpty())
		{
			status = "FALSE";
		}
				
		response.setResponse(status);
		response.setDataMapList(error);
		return response;
	}

}
