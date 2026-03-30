package com.eDecree.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CaseFile {

	Long id;
	String caseNo;
	Long caseYear;
	String barCode;	
	String firstPetitioner;	
	String firstRespondent;	
	String judgementDate;	
	
	public String getCaseNo() {
		return caseNo;
	}
	@XmlElement
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public Long getCaseYear() {
		return caseYear;
	}
	@XmlElement
	public void setCaseYear(Long caseYear) {
		this.caseYear = caseYear;
	}
	public String getBarCode() {
		return barCode;
	}
	@XmlElement
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getFirstPetitioner() {
		return firstPetitioner;
	}
	@XmlElement
	public void setFirstPetitioner(String firstPetitioner) {
		this.firstPetitioner = firstPetitioner;
	}
	public String getFirstRespondent() {
		return firstRespondent;
	}
	@XmlElement
	public void setFirstRespondent(String firstRespondent) {
		this.firstRespondent = firstRespondent;
	}
	public String getJudgementDate() {
		return judgementDate;
	}
	@XmlElement
	public void setJudgementDate(String judgementDate) {
		this.judgementDate = judgementDate;
	}
	public Long getId() {
		return id;
	}
	@XmlAttribute
	public void setId(Long id) {
		this.id = id;
	}

}
