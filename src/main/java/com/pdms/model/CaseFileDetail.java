package com.pdms.model;


import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class CaseFileDetail {

	private BigInteger fd_id;

	private BigInteger fd_case_type;

	private String fd_case_no;

	private BigInteger fd_case_year;

	private String fd_document_name;

	private String fd_file_source;

	private int fd_rec_status;

	private Date fd_cr_date;

	private BigInteger fd_bench_code;

	private String fd_file_bar_code = "";

	private BigInteger fd_bench_code_lid;

	private BigInteger fd_scaned_page_cnt;

	private String case_type;

	private String judgement_date;

	private String bench_code;

	private BigInteger judgement_id;

	private String first_petitioner;

	private String first_respondent;

	private CaseType caseType;
	
	private Long fd_district;

	

	private BigInteger fd_stage_lid;

	private String fd_final_path;

	private String fd_repository_path;

	private BigInteger fd_case_status;

	private String message;

	private List<String> dfd_barcode;

	private List<Judgement> judgement;

	private List<MetaData> metaData;

	private String createdDate;

	private List<SubDocument> subDocuments;

	private List<PdmsMetaData> pdmsMetaDatas;

	private List<String> petitioners;

	private List<String> respondents;

	private List<String> petitionerCounsels;

	private List<String> respondentCounsels;

	private String district;

	private List<String> judgesList;

	private String fd_judgement_date;
	
	private Long fileSize;
	
	private String fd_stage_name;
	
	private  Boolean fd_verify_stage;
	
	
	private com.eDecree.model.MetaData dmsMetaData;

	public CaseFileDetail() {

	}
	
	

	public Long getFd_district() {
		return fd_district;
	}



	public void setFd_district(Long fd_district) {
		this.fd_district = fd_district;
	}



	public CaseFileDetail(BigInteger fd_id, BigInteger fd_case_type, String fd_case_no, BigInteger fd_case_year,
			BigInteger fd_bench_code) {
		super();
		this.fd_id = fd_id;
		this.fd_case_type = fd_case_type;
		this.fd_case_no = fd_case_no;
		this.fd_case_year = fd_case_year;
		this.fd_bench_code = fd_bench_code;
	}
	
	



	


	

	public CaseFileDetail(BigInteger fd_id, BigInteger fd_case_type, String fd_case_no, BigInteger fd_case_year,
			String fd_document_name, String fd_file_source, int fd_rec_status, Date fd_cr_date,
			BigInteger fd_bench_code, String fd_file_bar_code, BigInteger fd_bench_code_lid,
			BigInteger fd_scaned_page_cnt, String case_type, String judgement_date, String bench_code,
			BigInteger judgement_id, String first_petitioner, String first_respondent, CaseType caseType,
			BigInteger fd_stage_lid, String fd_final_path, String fd_repository_path, BigInteger fd_case_status,
			String message, List<String> dfd_barcode, List<Judgement> judgement, List<MetaData> metaData,
			String createdDate, List<SubDocument> subDocuments, List<PdmsMetaData> pdmsMetaDatas,
			List<String> petitioners, List<String> respondents, List<String> petitionerCounsels,
			List<String> respondentCounsels, String district, List<String> judgesList, String fd_judgement_date,
			Long fileSize, String fd_stage_name, Boolean fd_verify_stage) {
		super();
		this.fd_id = fd_id;
		this.fd_case_type = fd_case_type;
		this.fd_case_no = fd_case_no;
		this.fd_case_year = fd_case_year;
		this.fd_document_name = fd_document_name;
		this.fd_file_source = fd_file_source;
		this.fd_rec_status = fd_rec_status;
		this.fd_cr_date = fd_cr_date;
		this.fd_bench_code = fd_bench_code;
		this.fd_file_bar_code = fd_file_bar_code;
		this.fd_bench_code_lid = fd_bench_code_lid;
		this.fd_scaned_page_cnt = fd_scaned_page_cnt;
		this.case_type = case_type;
		this.judgement_date = judgement_date;
		this.bench_code = bench_code;
		this.judgement_id = judgement_id;
		this.first_petitioner = first_petitioner;
		this.first_respondent = first_respondent;
		this.caseType = caseType;
		this.fd_stage_lid = fd_stage_lid;
		this.fd_final_path = fd_final_path;
		this.fd_repository_path = fd_repository_path;
		this.fd_case_status = fd_case_status;
		this.message = message;
		this.dfd_barcode = dfd_barcode;
		this.judgement = judgement;
		this.metaData = metaData;
		this.createdDate = createdDate;
		this.subDocuments = subDocuments;
		this.pdmsMetaDatas = pdmsMetaDatas;
		this.petitioners = petitioners;
		this.respondents = respondents;
		this.petitionerCounsels = petitionerCounsels;
		this.respondentCounsels = respondentCounsels;
		this.district = district;
		this.judgesList = judgesList;
		this.fd_judgement_date = fd_judgement_date;
		this.fileSize = fileSize;
		this.fd_stage_name = fd_stage_name;
		this.fd_verify_stage = fd_verify_stage;
	}

	
	public com.eDecree.model.MetaData getDmsMetaData() {
		return dmsMetaData;
	}



	public void setDmsMetaData(com.eDecree.model.MetaData dmsMetaData) {
		this.dmsMetaData = dmsMetaData;
	}



	public BigInteger getFd_id() {
		return fd_id;
	}

	public void setFd_id(BigInteger fd_id) {
		this.fd_id = fd_id;
	}

	public BigInteger getFd_case_type() {
		return fd_case_type;
	}

	public void setFd_case_type(BigInteger fd_case_type) {
		this.fd_case_type = fd_case_type;
	}

	public String getFd_case_no() {
		return fd_case_no;
	}

	public void setFd_case_no(String fd_case_no) {
		this.fd_case_no = fd_case_no;
	}

	public BigInteger getFd_case_year() {
		return fd_case_year;
	}

	public void setFd_case_year(BigInteger fd_case_year) {
		this.fd_case_year = fd_case_year;
	}

	public String getFd_document_name() {
		return fd_document_name;
	}

	public void setFd_document_name(String fd_document_name) {
		this.fd_document_name = fd_document_name;
	}

	public String getFd_file_source() {
		return fd_file_source;
	}

	public void setFd_file_source(String fd_file_source) {
		this.fd_file_source = fd_file_source;
	}

	public int getFd_rec_status() {
		return fd_rec_status;
	}

	public void setFd_rec_status(int fd_rec_status) {
		this.fd_rec_status = fd_rec_status;
	}

	public Date getFd_cr_date() {
		return fd_cr_date;
	}

	public void setFd_cr_date(Date fd_cr_date) {
		this.fd_cr_date = fd_cr_date;
	}

	public BigInteger getFd_bench_code() {
		return fd_bench_code;
	}

	public void setFd_bench_code(BigInteger fd_bench_code) {
		this.fd_bench_code = fd_bench_code;
	}

	public String getFd_file_bar_code() {
		return fd_file_bar_code;
	}

	public void setFd_file_bar_code(String fd_file_bar_code) {
		this.fd_file_bar_code = fd_file_bar_code;
	}

	public BigInteger getFd_bench_code_lid() {
		return fd_bench_code_lid;
	}

	public void setFd_bench_code_lid(BigInteger fd_bench_code_lid) {
		this.fd_bench_code_lid = fd_bench_code_lid;
	}

	public BigInteger getFd_scaned_page_cnt() {
		return fd_scaned_page_cnt;
	}

	public void setFd_scaned_page_cnt(BigInteger fd_scaned_page_cnt) {
		this.fd_scaned_page_cnt = fd_scaned_page_cnt;
	}

	public String getCase_type() {
		return case_type;
	}

	public void setCase_type(String case_type) {
		this.case_type = case_type;
	}

	public String getJudgement_date() {
		return judgement_date;
	}

	public void setJudgement_date(String judgement_date) {
		this.judgement_date = judgement_date;
	}

	public String getBench_code() {
		return bench_code;
	}

	public void setBench_code(String bench_code) {
		this.bench_code = bench_code;
	}

	public BigInteger getJudgement_id() {
		return judgement_id;
	}

	public void setJudgement_id(BigInteger judgement_id) {
		this.judgement_id = judgement_id;
	}

	public String getFirst_petitioner() {
		return first_petitioner;
	}

	public void setFirst_petitioner(String first_petitioner) {
		this.first_petitioner = first_petitioner;
	}

	public String getFirst_respondent() {
		return first_respondent;
	}

	public void setFirst_respondent(String first_respondent) {
		this.first_respondent = first_respondent;
	}

	public CaseType getCaseType() {
		return caseType;
	}

	public void setCaseType(CaseType caseType) {
		this.caseType = caseType;
	}

	

	public BigInteger getFd_stage_lid() {
		return fd_stage_lid;
	}

	public void setFd_stage_lid(BigInteger fd_stage_lid) {
		this.fd_stage_lid = fd_stage_lid;
	}

	public String getFd_final_path() {
		return fd_final_path;
	}

	public void setFd_final_path(String fd_final_path) {
		this.fd_final_path = fd_final_path;
	}

	public String getFd_repository_path() {
		return fd_repository_path;
	}

	public void setFd_repository_path(String fd_repository_path) {
		this.fd_repository_path = fd_repository_path;
	}

	public BigInteger getFd_case_status() {
		return fd_case_status;
	}

	public void setFd_case_status(BigInteger fd_case_status) {
		this.fd_case_status = fd_case_status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDfd_barcode() {
		return dfd_barcode;
	}

	public void setDfd_barcode(List<String> dfd_barcode) {
		this.dfd_barcode = dfd_barcode;
	}

	public List<Judgement> getJudgement() {
		return judgement;
	}

	public void setJudgement(List<Judgement> judgement) {
		this.judgement = judgement;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public List<SubDocument> getSubDocuments() {
		return subDocuments;
	}

	public void setSubDocuments(List<SubDocument> subDocuments) {
		this.subDocuments = subDocuments;
	}

	public List<MetaData> getMetaData() {
		return metaData;
	}

	public void setMetaData(List<MetaData> metaData) {
		this.metaData = metaData;
	}

	public List<PdmsMetaData> getPdmsMetaDatas() {
		return pdmsMetaDatas;
	}

	public void setPdmsMetaDatas(List<PdmsMetaData> pdmsMetaDatas) {
		this.pdmsMetaDatas = pdmsMetaDatas;
	}

	public List<String> getPetitioners() {
		return petitioners;
	}

	public void setPetitioners(List<String> petitioners) {
		this.petitioners = petitioners;
	}

	public List<String> getRespondents() {
		return respondents;
	}

	public void setRespondents(List<String> respondents) {
		this.respondents = respondents;
	}

	public List<String> getPetitionerCounsels() {
		return petitionerCounsels;
	}

	public void setPetitionerCounsels(List<String> petitionerCounsels) {
		this.petitionerCounsels = petitionerCounsels;
	}

	public List<String> getRespondentCounsels() {
		return respondentCounsels;
	}

	public void setRespondentCounsels(List<String> respondentCounsels) {
		this.respondentCounsels = respondentCounsels;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public List<String> getJudgesList() {
		return judgesList;
	}

	public void setJudgesList(List<String> judgesList) {
		this.judgesList = judgesList;
	}

	public String getFd_judgement_date() {
		return fd_judgement_date;
	}

	public void setFd_judgement_date(String fd_judgement_date) {
		this.fd_judgement_date = fd_judgement_date;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	
	

	
	public String getFd_stage_name() {
		return fd_stage_name;
	}

	public void setFd_stage_name(String fd_stage_name) {
		this.fd_stage_name = fd_stage_name;
	}
	
	

	public Boolean getFd_verify_stage() {
		return fd_verify_stage;
	}

	public void setFd_verify_stage(Boolean fd_verify_stage) {
		this.fd_verify_stage = fd_verify_stage;
	}

	@Override
	public String toString() {
		return "CaseFileDetail [fd_id=" + fd_id + ", fd_case_type=" + fd_case_type + ", fd_case_no=" + fd_case_no
				+ ", fd_case_year=" + fd_case_year + ", fd_document_name=" + fd_document_name + ", fd_file_source="
				+ fd_file_source + ", fd_rec_status=" + fd_rec_status + ", fd_cr_date=" + fd_cr_date
				+ ", fd_bench_code=" + fd_bench_code + ", fd_file_bar_code=" + fd_file_bar_code + ", fd_bench_code_lid="
				+ fd_bench_code_lid + ", fd_scaned_page_cnt=" + fd_scaned_page_cnt + ", case_type=" + case_type
				+ ", judgement_date=" + judgement_date + ", bench_code=" + bench_code + ", judgement_id=" + judgement_id
				+ ", first_petitioner=" + first_petitioner + ", first_respondent=" + first_respondent + ", caseType="
				+ caseType + ", fd_stage_lid=" + fd_stage_lid + ", fd_final_path=" + fd_final_path
				+ ", fd_repository_path=" + fd_repository_path + ", fd_case_status=" + fd_case_status + ", message="
				+ message + ", dfd_barcode=" + dfd_barcode + ", judgement=" + judgement + ", metaData=" + metaData
				+ ", createdDate=" + createdDate + ", subDocuments=" + subDocuments + ", pdmsMetaDatas=" + pdmsMetaDatas
				+ ", petitioners=" + petitioners + ", respondents=" + respondents + ", petitionerCounsels="
				+ petitionerCounsels + ", respondentCounsels=" + respondentCounsels + ", district=" + district
				+ ", judgesList=" + judgesList + ", fd_judgement_date=" + fd_judgement_date + ", fileSize=" + fileSize
				+ ", fd_stage_name=" + fd_stage_name + ", fd_verify_stage=" + fd_verify_stage + "]";
	}

	

	
	

	
}