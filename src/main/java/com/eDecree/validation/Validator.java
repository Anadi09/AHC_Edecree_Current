package com.eDecree.validation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validator {
	
	Map<String, List> error = new HashMap<String, List>();
	
	public void isRequired(String attribute,Object obj)
	{
		List<String> errorList = new ArrayList<String>();
		if(obj==null || obj=="")
		{
			errorList =  error.get(attribute);
			if(errorList == null ) {
				errorList = new ArrayList<String>();
			}
			errorList.add("Please enter "+attribute);
			error.put(attribute, errorList);		
		}
	}

	
	public void isRequiredDropDown(String attribute,Long value)
	{
		List<String> errorList = new ArrayList<String>();
		if(value==null)
		{
			errorList =  error.get(attribute);
			if(errorList == null ) {
				errorList = new ArrayList<String>();
			}
			errorList.add("Please Select "+attribute);
			error.put(attribute, errorList);		
		}
	}
	
	public void isRequiredDate(String attribute,Date date)
	{
		
		List<String> errorList = new ArrayList<String>();
		if(date==null)
		{
			errorList =  error.get(attribute);
			if(errorList == null ) {
				errorList = new ArrayList<String>();
			}
			errorList.add("Please enter "+attribute);
			error.put(attribute, errorList);		
		}else if(date.after(new Date())){
			errorList.add("Please enter correct"+attribute);
		}
	}
	
	public void maxLength(String attribute,Object obj,Integer length)
	{
		if(obj!=null){
			String str=obj.toString();
			List<String> errorList = new ArrayList<String>();
			if(str!=null){
				if(str.length() > length ){	
					errorList =  error.get(attribute);
					if(errorList == null ) {
						errorList = new ArrayList<String>();
					}
					errorList.add(attribute+" should not be greater than "+ length +" characters");
					error.put(attribute, errorList);
				}
			}
		}
	}
	
	public void minLength(String attribute,Object obj,Integer length)
	{
		if(obj!=null){
			String str=obj.toString();
			List<String> errorList = new ArrayList<String>();
			if(str!=null){
				if(str.length() < length ){	
					errorList =  error.get(attribute);
					if(errorList == null ) {
						errorList = new ArrayList<String>();
					}
					errorList.add(attribute+" should not be greater "+ length +" characters");
					error.put(attribute, errorList);
				}
			}
		}
	}
	
	public void numericOnly(String attribute,Object obj){
		if(obj!=null){
			String str=obj.toString();
			String regex = "[0-9]";
			List<String> errorList = new ArrayList<String>();
			if(str!=null){
				if(!str.matches("\\d+")){
					errorList =  error.get(attribute);
					if(errorList == null ) {
						errorList = new ArrayList<String>();
					}
					errorList.add(attribute+" should only contain numbers");
					error.put(attribute, errorList);
				}
			}
		}
	}
	
	public void alphabetsOnly(String attribute,String str)
	{
		if(str!=null){
			List<String> errorList = new ArrayList<String>();
			 if (!str.matches("[a-zA-Z ]+"))
			 {
				 errorList =  error.get(attribute);
				 if(errorList == null ) {
					errorList = new ArrayList<String>();
				 }
				errorList.add(attribute+" should only contain alphabets");
				error.put(attribute, errorList);
			 }
		}
	}
	
	public void alphabetNumeric(String attribute,String str)
	{
		if(str!=null){
			List<String> errorList = new ArrayList<String>();
			 if (!str.matches("[A-Za-z0-9 ]+"))
			 {
				 errorList =  error.get(attribute);
				 if(errorList == null ) {
						errorList = new ArrayList<String>();
				 }
				errorList.add(attribute+" is only contain alphanumeric characters");
				error.put(attribute, errorList);
			 }
		}
	}
	
	public void email(String attribute,String str) {
		if(str!=null){
			List<String> errorList = new ArrayList<String>();
            String emailregex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
            if (!str.matches(emailregex)) {
            	errorList =  error.get(attribute);
    			if(errorList == null ) {
    				errorList = new ArrayList<String>();
    			}
            	errorList.add(attribute+" is Invalid");
            	error.put(attribute, errorList);
                }
       }
	}
	
	public void checkRegEx(String attribute,String str,String regEx,String msg) {
		if(str!=null){
			List<String> errorList = new ArrayList<String>();
            //String emailregex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
            if (!str.matches(regEx)) {
            	errorList =  error.get(attribute);
    			if(errorList == null ) {
    				errorList = new ArrayList<String>();
    			}
    			errorList.add(msg);
            	error.put(attribute, errorList);
                }
       }
	}

	public void isRequiredDropDownList(String attribute,Object obj)
	{
		List<String> errorList = new ArrayList<String>();
		if(obj==null)
		{
			errorList =  error.get(attribute);
			if(errorList == null ) {
				errorList = new ArrayList<String>();
			}
			errorList.add("Please Select "+attribute);
			error.put(attribute, errorList);		
		}
	}
	public void isLessThan(String attribute,Long joining_year,Long retirement_year)
	{
		List<String> errorList = new ArrayList<String>();
		if(joining_year > retirement_year)
		{
			errorList =  error.get(attribute);
			if(errorList == null ) {
				errorList = new ArrayList<String>();
			}
			errorList.add("Joining Year should be less than Retirement Year");
			error.put(attribute, errorList);	
		}
	}
	public void isMatch(String attribute,String password,String passwordc)
	{
		List<String> errorList = new ArrayList<String>();
		if(!password.equals(passwordc))
		{
			errorList =  error.get(attribute);
			if(errorList == null ) {
				errorList = new ArrayList<String>();
			} 
			errorList.add("Password not match");
			error.put(attribute, errorList);	
		}
	}
	public Map<String, List> getError() {
		return error;
	}

	public void setError(Map<String, List> error) {
		this.error = error;
	}

}
