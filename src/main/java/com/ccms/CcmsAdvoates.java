package com.ccms;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
public class CcmsAdvoates {
	
	
	@JsonProperty("PetAdvocate")
	 String PetAdvocate;
	@JsonProperty("ResAdvocate")
	 String ResAdvocate;
	public String getPetAdvocate() {
		return PetAdvocate;
	}
	public void setPetAdvocate(String petAdvocate) {
		PetAdvocate = petAdvocate;
	}
	public String getResAdvocate() {
		return ResAdvocate;
	}
	public void setResAdvocate(String resAdvocate) {
		ResAdvocate = resAdvocate;
	}
	
	
	

}
