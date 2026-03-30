package com.eDecree.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "reportsview", query = "select m from ReportsView m ")
public class ReportsView {
	
	@Id
	@Column(name = "fileid")
	private Long fileId;
	
	@Column(name = "casetype")
	private String caseType;
	
	@Column(name = "caseno")
	private String caseNo;
	
	@Column(name = "caseyear")
	private String caseYear;
	
	@Column(name = "first_petitioner")
	private String firstPetitioner;
	
	@Column(name = "first_respondent")
	private String firstRespondent;
	
	@Column(name = "petitioner")
	private String petitioner;
	
	@Column(name = "respondent")
	private String respondent;
	
	@Column(name = "benchcode")
	private String benchCode;

	@Column(name = "judgement_date")
	private String judgementDate;
	
	@Column(name = "judge_id")
	private String judgeId;
	
	@Column(name = "petitioner_counsel")
	private String petitionerCounsel;
	
	@Column(name = "respondent_counsel")
	private String respondentCounsel;
		
	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

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

	public String getFirstPetitioner() {
		return firstPetitioner;
	}

	public void setFirstPetitioner(String firstPetitioner) {
		this.firstPetitioner = firstPetitioner;
	}

	public String getRespondent() {
		return respondent;
	}

	public String getFirstRespondent() {
		return firstRespondent;
	}

	public void setFirstRespondent(String firstRespondent) {
		this.firstRespondent = firstRespondent;
	}

	public String getPetitioner() {
		return petitioner;
	}

	public void setPetitioner(String petitioner) {
		this.petitioner = petitioner;
	}

	public void setRespondent(String respondent) {
		this.respondent = respondent;
	}

	public String getBenchCode() {
		return benchCode;
	}

	public void setBenchCode(String benchCode) {
		this.benchCode = benchCode;
	}

	public String getJudgementDate() {
		return judgementDate;
	}

	public void setJudgementDate(String judgementDate) {
		this.judgementDate = judgementDate;
	}

	public String getJudgeId() {
		return judgeId;
	}

	public void setJudgeId(String judgeId) {
		this.judgeId = judgeId;
	}

	public String getPetitionerCounsel() {
		return petitionerCounsel;
	}

	public void setPetitionerCounsel(String petitionerCounsel) {
		this.petitionerCounsel = petitionerCounsel;
	}

	public String getRespondentCounsel() {
		return respondentCounsel;
	}

	public void setRespondentCounsel(String respondentCounsel) {
		this.respondentCounsel = respondentCounsel;
	}
	
	
	
	
}
