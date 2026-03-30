package com.ccms;



import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
public class CcmsPrimary {
	
	
	@JsonProperty("CINO")
	 String CINO;
	@JsonProperty("Case_id")
	 String Case_id;
	@JsonProperty("Case_number")
		String Case_number;	
	@JsonProperty("Party_Name")
		String Party_Name;
	@JsonProperty("Status")
		String Status;
	@JsonProperty("District")
		String District;
	@JsonProperty("Draftnumber")
		String Draftnumber;
	@JsonProperty("IsCaseConverted")
		String IsCaseConverted;
	@JsonProperty("CustomMessage")
		String CustomMessage;
	public String getCINO() {
		return CINO;
	}
	public void setCINO(String cINO) {
		CINO = cINO;
	}
	public String getCase_id() {
		return Case_id;
	}
	public void setCase_id(String case_id) {
		Case_id = case_id;
	}
	public String getCase_number() {
		return Case_number;
	}
	public void setCase_number(String case_number) {
		Case_number = case_number;
	}
	public String getParty_Name() {
		return Party_Name;
	}
	public void setParty_Name(String party_Name) {
		Party_Name = party_Name;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getDistrict() {
		return District;
	}
	public void setDistrict(String district) {
		District = district;
	}
	public String getDraftnumber() {
		return Draftnumber;
	}
	public void setDraftnumber(String draftnumber) {
		Draftnumber = draftnumber;
	}
	public String getIsCaseConverted() {
		return IsCaseConverted;
	}
	public void setIsCaseConverted(String isCaseConverted) {
		IsCaseConverted = isCaseConverted;
	}
	public String getCustomMessage() {
		return CustomMessage;
	}
	public void setCustomMessage(String customMessage) {
		CustomMessage = customMessage;
	}
	
	
	

}
