package com.ccms;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
public class CcmsPartyDetails {
	
	@JsonProperty("PetitionerList")
	 String [] PetitionerList;
	@JsonProperty("RespondentList")
	 String [] RespondentList;
	public String[] getPetitionerList() {
		return PetitionerList;
	}
	public void setPetitionerList(String[] petitionerList) {
		PetitionerList = petitionerList;
	}
	public String[] getRespondentList() {
		return RespondentList;
	}
	public void setRespondentList(String[] respondentList) {
		RespondentList = respondentList;
	}
	
	

}
