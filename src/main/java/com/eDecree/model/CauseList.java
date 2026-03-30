package com.eDecree.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "cause_list")
public class CauseList {

	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="cause_list_seq")
	@SequenceGenerator(name="cause_list_seq", sequenceName="cause_list_seq", allocationSize=1)
	@Column(name = "cl_id")
	private Long cl_id;
	
	
	@Column(name = "cl_case_type_mid")
	private Long cl_case_type_mid;
	
	@Column(name = "cl_case_no")
	private String cl_case_no;
	
	@Column(name = "cl_case_year")
	private Integer cl_case_year;
	
	@Column(name = "cl_court_no")
	private Integer cl_court_no;
	
	@Column(name = "cl_transfer_to")
	private Integer cl_transfer_to;
	
	@Column(name = "cl_serial_no")
	private Integer cl_serial_no;
	
	@Column(name = "cl_list_type_mid")
	private Long cl_list_type_mid;
	
	@Column(name = "cl_first_petitioner")
	private String cl_first_petitioner;
	
	@Column(name = "cl_first_respondent")
	private String cl_first_respondent;
	
	@Column(name = "cl_petitioner_council")
	private String cl_petitioner_council;
	
	@Column(name = "cl_respondent_council")
	private String cl_respondent_council;
	
	@Column(name = "cl_ano")
	private Integer cl_ano;
	
	@Column(name = "cl_bench_number")
	private Integer cl_bench_number;
	
	@Column(name = "cl_ayr")
	private Integer cl_ayr;
	
	@Column(name = "cl_applawp")
	private String cl_applawp;
	
	@Column(name = "cl_applawr")
	private String cl_applawr;
	
	@Column(name = "cl_stage")
	private Integer cl_stage;
	
	@Column(name = "cl_dol")
	private Date cl_dol;
	
	@Transient
	private  Date cl_dol1;
	
	@Column(name = "cl_fd_mid")
	private Long cl_fd_mid;
	
	@Column(name = "cl_sequence")
	private Integer cl_sequence;
	
	@Transient
	private Integer count;
	
	@Transient
	private CauseListType cType;
	
	@Transient
	private Date listing_date;
	
	
	@Column(name = "cl_rec_status")
	private Integer cl_rec_status;
	
	@Column(name = "cl_mod_by")
	private Long cl_mod_by;
	
	@Column(name = "cl_mod_date")
	private Date cl_mod_date;
	
	
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cl_case_type_mid",insertable = false, updatable = false)
	private CaseType caseType;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cl_list_type_mid",insertable = false, updatable = false)
	private CauseListType clType;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cl_stage_id",insertable = false, updatable = false)
	private Lookup applicationStage;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cl_court_no",insertable = false, updatable = false)
	private CourtMaster courtMaster;
	
	
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cl_transfer_to",insertable = false, updatable = false)
	private CourtMaster courtMasterForTransfer;
	
	
	
	@Transient
	private boolean petAvailable=false;
	
	
	
	
	

	public Integer getCl_bench_number() {
		return cl_bench_number;
	}



	public void setCl_bench_number(Integer cl_bench_number) {
		this.cl_bench_number = cl_bench_number;
	}



	public boolean isPetAvailable() {
		return petAvailable;
	}



	public void setPetAvailable(boolean petAvailable) {
		this.petAvailable = petAvailable;
	}



	public CauseListType getClType() {
		return clType;
	}



	public void setClType(CauseListType clType) {
		this.clType = clType;
	}



	public CourtMaster getCourtMasterForTransfer() {
		return courtMasterForTransfer;
	}



	public void setCourtMasterForTransfer(CourtMaster courtMasterForTransfer) {
		this.courtMasterForTransfer = courtMasterForTransfer;
	}

	@Column(name = "cl_stage_id")
	private Long cl_stage_id;
	
	@Column(name = "cl_date")
	private Date cl_date;
	
	@Transient
	private boolean checked;
	
	@Transient
	private Integer cl_new_court_no;
	
	
	
	
	
	public Integer getCl_transfer_to() {
		return cl_transfer_to;
	}
	
	

	public void setCl_transfer_to(Integer cl_transfer_to) {
		this.cl_transfer_to = cl_transfer_to;
	}

	public Date getCl_dol1() {
		return cl_dol1;
	}

	public void setCl_dol1(Date cl_dol1) {
		this.cl_dol1 = cl_dol1;
	}

	public CauseListType getcType() {
		return cType;
	}

	public void setcType(CauseListType cType) {
		this.cType = cType;
	}

	public Long getCl_id() {
		return cl_id;
	}

	public void setCl_id(Long cl_id) {
		this.cl_id = cl_id;
	}

	public Long getCl_case_type_mid() {
		return cl_case_type_mid;
	}

	public void setCl_case_type_mid(Long cl_case_type_mid) {
		this.cl_case_type_mid = cl_case_type_mid;
	}

	public String getCl_case_no() {
		return cl_case_no;
	}

	public void setCl_case_no(String cl_case_no) {
		this.cl_case_no = cl_case_no;
	}

	public Integer getCl_case_year() {
		return cl_case_year;
	}

	public void setCl_case_year(Integer cl_case_year) {
		this.cl_case_year = cl_case_year;
	}

	public Integer getCl_court_no() {
		return cl_court_no;
	}

	public void setCl_court_no(Integer cl_court_no) {
		this.cl_court_no = cl_court_no;
	}

	public Integer getCl_serial_no() {
		return cl_serial_no;
	}

	public void setCl_serial_no(Integer cl_serial_no) {
		this.cl_serial_no = cl_serial_no;
	}

	public Long getCl_list_type_mid() {
		return cl_list_type_mid;
	}

	public void setCl_list_type_mid(Long cl_list_type_mid) {
		this.cl_list_type_mid = cl_list_type_mid;
	}

	public String getCl_first_petitioner() {
		return cl_first_petitioner;
	}

	public void setCl_first_petitioner(String cl_first_petitioner) {
		this.cl_first_petitioner = cl_first_petitioner;
	}

	public String getCl_first_respondent() {
		return cl_first_respondent;
	}

	public void setCl_first_respondent(String cl_first_respondent) {
		this.cl_first_respondent = cl_first_respondent;
	}

	public String getCl_petitioner_council() {
		return cl_petitioner_council;
	}

	public void setCl_petitioner_council(String cl_petitioner_council) {
		this.cl_petitioner_council = cl_petitioner_council;
	}

	public String getCl_respondent_council() {
		return cl_respondent_council;
	}

	public void setCl_respondent_council(String cl_respondent_council) {
		this.cl_respondent_council = cl_respondent_council;
	}

	public Integer getCl_ano() {
		return cl_ano;
	}

	public void setCl_ano(Integer cl_ano) {
		this.cl_ano = cl_ano;
	}

	public Integer getCl_ayr() {
		return cl_ayr;
	}

	public void setCl_ayr(Integer cl_ayr) {
		this.cl_ayr = cl_ayr;
	}

	public String getCl_applawp() {
		return cl_applawp;
	}

	public void setCl_applawp(String cl_applawp) {
		this.cl_applawp = cl_applawp;
	}

	public String getCl_applawr() {
		return cl_applawr;
	}

	public void setCl_applawr(String cl_applawr) {
		this.cl_applawr = cl_applawr;
	}

	public Integer getCl_stage() {
		return cl_stage;
	}

	public void setCl_stage(Integer cl_stage) {
		this.cl_stage = cl_stage;
	}

	public Date getCl_dol() {
		return cl_dol;
	}

	public void setCl_dol(Date cl_dol) {
		this.cl_dol = cl_dol;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Long getCl_fd_mid() {
		return cl_fd_mid;
	}

	public void setCl_fd_mid(Long cl_fd_mid) {
		this.cl_fd_mid = cl_fd_mid;
	}

	public CaseType getCaseType() {
		return caseType;
	}

	public void setCaseType(CaseType caseType) {
		this.caseType = caseType;
	}

	public Integer getCl_sequence() {
		return cl_sequence;
	}

	public void setCl_sequence(Integer cl_sequence) {
		this.cl_sequence = cl_sequence;
	}

	public Long getCl_stage_id() {
		return cl_stage_id;
	}

	public void setCl_stage_id(Long cl_stage_id) {
		this.cl_stage_id = cl_stage_id;
	}

	public Date getCl_date() {
		return cl_date;
	}

	public void setCl_date(Date cl_date) {
		this.cl_date = cl_date;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setStatus(boolean checked) {
		this.checked = checked;
	}

	public Lookup getApplicationStage() {
		return applicationStage;
	}

	public void setApplicationStage(Lookup applicationStage) {
		this.applicationStage = applicationStage;
	}

	public Integer getCl_rec_status() {
		return cl_rec_status;
	}

	public void setCl_rec_status(Integer cl_rec_status) {
		this.cl_rec_status = cl_rec_status;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Long getCl_mod_by() {
		return cl_mod_by;
	}

	public void setCl_mod_by(Long cl_mod_by) {
		this.cl_mod_by = cl_mod_by;
	}

	public Date getCl_mod_date() {
		return cl_mod_date;
	}

	public void setCl_mod_date(Date cl_mod_date) {
		this.cl_mod_date = cl_mod_date;
	}

	public CourtMaster getCourtMaster() {
		return courtMaster;
	}

	public void setCourtMaster(CourtMaster courtMaster) {
		this.courtMaster = courtMaster;
	}

	public Integer getCl_new_court_no() {
		return cl_new_court_no;
	}

	public void setCl_new_court_no(Integer cl_new_court_no) {
		this.cl_new_court_no = cl_new_court_no;
	}

	public Date getListing_date() {
		return listing_date;
	}

	public void setListing_date(Date listing_date) {
		this.listing_date = listing_date;
	}



	@Override
	public String toString() {
		return "CauseList [cl_id=" + cl_id + ", cl_case_type_mid=" + cl_case_type_mid + ", cl_case_no=" + cl_case_no
				+ ", cl_case_year=" + cl_case_year + ", cl_court_no=" + cl_court_no + ", cl_transfer_to="
				+ cl_transfer_to + ", cl_serial_no=" + cl_serial_no + ", cl_list_type_mid=" + cl_list_type_mid
				+ ", cl_first_petitioner=" + cl_first_petitioner + ", cl_first_respondent=" + cl_first_respondent
				+ ", cl_petitioner_council=" + cl_petitioner_council + ", cl_respondent_council="
				+ cl_respondent_council + ", cl_ano=" + cl_ano + ", cl_ayr=" + cl_ayr + ", cl_applawp=" + cl_applawp
				+ ", cl_applawr=" + cl_applawr + ", cl_stage=" + cl_stage + ", cl_dol=" + cl_dol + ", cl_dol1="
				+ cl_dol1 + ", cl_fd_mid=" + cl_fd_mid + ", cl_sequence=" + cl_sequence + ", count=" + count
				+ ", cType=" + cType + ", listing_date=" + listing_date + ", cl_rec_status=" + cl_rec_status
				+ ", cl_mod_by=" + cl_mod_by + ", cl_mod_date=" + cl_mod_date + ", caseType=" + caseType
				+ ", applicationStage=" + applicationStage + ", courtMaster=" + courtMaster
				+ ", courtMasterForTransfer=" + courtMasterForTransfer + ", cl_stage_id=" + cl_stage_id + ", cl_date="
				+ cl_date + ", checked=" + checked + ", cl_new_court_no=" + cl_new_court_no + "]";
	}
	
	
	

}
