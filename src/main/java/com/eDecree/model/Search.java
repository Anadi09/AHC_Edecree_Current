package com.eDecree.model;

public class Search {
	
	private Long caseTypeId;
	private String caseNo;
	private Integer caseYear;
	private Integer itemsPerPage;
	private Integer pageNumber;
	private String partyType;
	private String partyName;
	private String counselType;
	private String counselName;
	private Long judgeId;
	private String courtType;
	private String cnrNo;
	private Long district;
	
	public Long getCaseTypeId() {
		return caseTypeId;
	}
	public void setCaseTypeId(Long caseTypeId) {
		this.caseTypeId = caseTypeId;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public Integer getCaseYear() {
		return caseYear;
	}
	public void setCaseYear(Integer caseYear) {
		this.caseYear = caseYear;
	}
	public Integer getItemsPerPage() {
		return itemsPerPage;
	}
	public void setItemsPerPage(Integer itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public String getPartyType() {
		return partyType;
	}
	public void setPartyType(String partyType) {
		this.partyType = partyType;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getCounselType() {
		return counselType;
	}
	public void setCounselType(String counselType) {
		this.counselType = counselType;
	}
	public String getCounselName() {
		return counselName;
	}
	public void setCounselName(String counselName) {
		this.counselName = counselName;
	}
	public Long getJudgeId() {
		return judgeId;
	}
	public void setJudgeId(Long judgeId) {
		this.judgeId = judgeId;
	}
	public String getCourtType() {
		return courtType;
	}
	public void setCourtType(String courtType) {
		this.courtType = courtType;
	}
	public String getCnrNo() {
		return cnrNo;
	}
	public void setCnrNo(String cnrNo) {
		this.cnrNo = cnrNo;
	}
	public Long getDistrict() {
		return district;
	}
	public void setDistrict(Long district) {
		this.district = district;
	}
	
}
