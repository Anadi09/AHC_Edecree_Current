package com.eDecree.model;

public class CaseRequestDTO {

    private String caseType;
    private String caseNo;
    private String caseYear;
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getCaseYear() {
		return caseYear;
	}
	public void setCaseYear(String caseYear) {
		this.caseYear = caseYear;
	}

	@Override
	public String toString() {
	    return "CaseRequestDTO{" +
	            "caseType='" + caseType + '\'' +
	            ", caseNo='" + caseNo + '\'' +
	            ", caseYear='" + caseYear + '\'' +
	            '}';
	}

    
}