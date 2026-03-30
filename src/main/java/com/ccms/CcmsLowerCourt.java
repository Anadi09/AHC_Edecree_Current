package com.ccms;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
public class CcmsLowerCourt {


	@JsonProperty("CaseTypeName")
	 String  CaseTypeName;
	@JsonProperty("Caseno")
	 String  Caseno;
	@JsonProperty("CaseYear")
	 String  CaseYear;
	@JsonProperty("DistName")
	 String  DistName;
	@JsonProperty("DecisionDate")
	 String DecisionDate;
	@JsonProperty("JudgeName")
	 String JudgeName;
	
	public String getCaseTypeName() {
		return CaseTypeName;
	}
	public String getJudgeName() {
		return JudgeName;
	}
	public void setJudgeName(String judgeName) {
		JudgeName = judgeName;
	}
	public void setCaseTypeName(String caseTypeName) {
		CaseTypeName = caseTypeName;
	}
	public String getCaseno() {
		return Caseno;
	}
	public void setCaseno(String caseno) {
		Caseno = caseno;
	}
	public String getCaseYear() {
		return CaseYear;
	}
	public void setCaseYear(String caseYear) {
		CaseYear = caseYear;
	}
	public String getDistName() {
		return DistName;
	}
	public void setDistName(String distName) {
		DistName = distName;
	}
	public String getDecisionDate() {
		return DecisionDate;
	}
	public void setDecisionDate(String decisionDate) {
		DecisionDate = decisionDate;
	}
	
	

	
	
}
