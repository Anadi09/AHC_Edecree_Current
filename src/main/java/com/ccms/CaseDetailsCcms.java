package com.ccms;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
public class CaseDetailsCcms {
	
	
	
	@JsonProperty("CaseType")
	 String CaseType;
	@JsonProperty("DisplayCaseno")
	 String DisplayCaseno;
	@JsonProperty("CreatedDT")
		String CreatedDT;	
	@JsonProperty("PetName")
		String PetName;
	@JsonProperty("ResName")
		String ResName;
	@JsonProperty("DisposedDate")
		String DisposedDate;
	@JsonProperty("CaseId")
		String CaseId;
	@JsonProperty("District")
		String District;
	
	@JsonProperty("DisposedJudgeName")
	String DisposedJudgeName;
	
	/*@JsonProperty("CustomMessage")
		String CustomMessage;*/
	
	
	
	
	
	public String getCaseType() {
		return CaseType;
	}
	public String getDisposedJudgeName() {
		return DisposedJudgeName;
	}
	public void setDisposedJudgeName(String disposedJudgeName) {
		DisposedJudgeName = disposedJudgeName;
	}
	public String getDistrict() {
		return District;
	}
	public void setDistrict(String district) {
		District = district;
	}
	public void setCaseType(String caseType) {
		CaseType = caseType;
	}
	public String getDisplayCaseno() {
		return DisplayCaseno;
	}
	public void setDisplayCaseno(String displayCaseno) {
		DisplayCaseno = displayCaseno;
	}
	public String getCreatedDT() {
		return CreatedDT;
	}
	public void setCreatedDT(String createdDT) {
		CreatedDT = createdDT;
	}
	public String getPetName() {
		return PetName;
	}
	public void setPetName(String petName) {
		PetName = petName;
	}
	public String getResName() {
		return ResName;
	}
	public void setResName(String resName) {
		ResName = resName;
	}
	public String getDisposedDate() {
		return DisposedDate;
	}
	public void setDisposedDate(String disposedDate) {
		DisposedDate = disposedDate;
	}
	public String getCaseId() {
		return CaseId;
	}
	public void setCaseId(String caseId) {
		CaseId = caseId;
	}
	
	

}
