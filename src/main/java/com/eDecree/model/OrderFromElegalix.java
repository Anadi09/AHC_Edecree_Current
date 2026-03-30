package com.eDecree.model;

import java.util.Date;

public class OrderFromElegalix {
	
	private Long judgmentID;
	
	private String caseTypeCode;
	private String caseNumber;
	private Integer caseYear;
	private Date judgmentDate;
	private String highCourtBenchCode;
	private  String judgmentType;
	public Long getJudgmentID() {
		return judgmentID;
	}
	public void setJudgmentID(Long judgmentID) {
		this.judgmentID = judgmentID;
	}
	public String getCaseTypeCode() {
		return caseTypeCode;
	}
	public void setCaseTypeCode(String caseTypeCode) {
		this.caseTypeCode = caseTypeCode;
	}
	public String getCaseNumber() {
		return caseNumber;
	}
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	public Integer getCaseYear() {
		return caseYear;
	}
	public void setCaseYear(Integer caseYear) {
		this.caseYear = caseYear;
	}
	public Date getJudgmentDate() {
		return judgmentDate;
	}
	public void setJudgmentDate(Date judgmentDate) {
		this.judgmentDate = judgmentDate;
	}
	public String getHighCourtBenchCode() {
		return highCourtBenchCode;
	}
	public void setHighCourtBenchCode(String highCourtBenchCode) {
		this.highCourtBenchCode = highCourtBenchCode;
	}
	public String getJudgmentType() {
		return judgmentType;
	}
	public void setJudgmentType(String judgmentType) {
		this.judgmentType = judgmentType;
	}
	@Override
	public String toString() {
		return "OrderFromElegalix [judgmentID=" + judgmentID + ", caseTypeCode=" + caseTypeCode + ", caseNumber="
				+ caseNumber + ", caseYear=" + caseYear + ", judgmentDate=" + judgmentDate + ", highCourtBenchCode="
				+ highCourtBenchCode + ", judgmentType=" + judgmentType + "]";
	}
	
	
	
	

}
