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

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "impugned_order")
public class ImpugnedOrder {
	
	
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE,generator="impugnedorder_seq")
	@SequenceGenerator(name="impugnedorder_seq", sequenceName="impugnedorder_seq", allocationSize=1)
	@Column(name = "io_id")
	private Long io_id;
	
	@Column(name = "io_court_type")
	private Integer io_court_type;
	   
	@Column(name = "io_lct_mid")
	private Integer  io_lct_mid;
	   
	@Column(name = "io_hc_case_type")
	private Long  io_hc_case_type;
	
	@Column(name = "io_lc_case_type")
	private Long  io_lc_case_type;
	   
	@Column(name = "io_cnr_no") 
	private String io_cnr_no;
	   
	@Column(name = "io_judge_name") 
	private  String io_judge_name;
	   
	@Column(name = "io_case_no")   
	private  String io_case_no;
	   
	@Column(name = "io_district")  
	private Long io_district;
	   
	@Column(name = "io_cr_by") 
	private  Long io_cr_by; 
	
	@Column(name = "io_s_id") 
	private Long  io_s_id;
	   
	@Column(name = "io_cr_date") 
	private  Date io_cr_date;
	   
	@Column(name = "io_mod_by") 
	private  Long io_mod_by;
	   
	@Column(name = "io_mod_date") 
	private  Date io_mod_date;
	   
	@Column(name = "io_decision_date")   
	private Date  io_decision_date;
	 
	@Column(name = "io_case_year") 
	private  String io_case_year;
	
	  
	@Column(name="io_fd_mid")
	private Long io_fd_mid;
	
	@Column(name = "io_rec_status") 
	private  Integer io_rec_status;
  
	/*@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "io_district",insertable = false, updatable = false)
	private District district;
	*/
	
	/*@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "io_hc_case_type",insertable = false, updatable = false)
	private CaseType hcCaseType;*/
	
/*	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "io_lc_case_type",insertable = false, updatable = false)
	private LowerCourtCaseType lcCaseType;*/
	  
	/*@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "io_lct_mid",insertable = false, updatable = false)
	private LowerCourtTypes courtType;*/
	  
	public Long getIo_id() {
		return io_id;
	}

	public void setIo_id(Long io_id) {
		this.io_id = io_id;
	}

	public Integer getIo_court_type() {
		return io_court_type;
	}

	public void setIo_court_type(Integer io_court_type) {
		this.io_court_type = io_court_type;
	}

	public Integer getIo_lct_mid() {
		return io_lct_mid;
	}

	public void setIo_lct_mid(Integer io_lct_mid) {
		this.io_lct_mid = io_lct_mid;
	}

	public Long getIo_hc_case_type() {
		return io_hc_case_type;
	}

	public void setIo_hc_case_type(Long io_hc_case_type) {
		this.io_hc_case_type = io_hc_case_type;
	}

	public Long getIo_lc_case_type() {
		return io_lc_case_type;
	}

	public void setIo_lc_case_type(Long io_lc_case_type) {
		this.io_lc_case_type = io_lc_case_type;
	}

	public String getIo_cnr_no() {
		return io_cnr_no;
	}

	public void setIo_cnr_no(String io_cnr_no) {
		this.io_cnr_no = io_cnr_no;
	}

	public String getIo_judge_name() {
		return io_judge_name;
	}

	public void setIo_judge_name(String io_judge_name) {
		this.io_judge_name = io_judge_name;
	}

	public String getIo_case_no() {
		return io_case_no;
	}

	public void setIo_case_no(String io_case_no) {
		this.io_case_no = io_case_no;
	}

	public Long getIo_district() {
		return io_district;
	}

	public void setIo_district(Long io_district) {
		this.io_district = io_district;
	}

	public Long getIo_cr_by() {
		return io_cr_by;
	}

	public void setIo_cr_by(Long io_cr_by) {
		this.io_cr_by = io_cr_by;
	}

	public Long getIo_s_id() {
		return io_s_id;
	}

	public void setIo_s_id(Long io_s_id) {
		this.io_s_id = io_s_id;
	}

	public Date getIo_cr_date() {
		return io_cr_date;
	}

	public void setIo_cr_date(Date io_cr_date) {
		this.io_cr_date = io_cr_date;
	}

	public Long getIo_mod_by() {
		return io_mod_by;
	}

	public void setIo_mod_by(Long io_mod_by) {
		this.io_mod_by = io_mod_by;
	}

	public Date getIo_mod_date() {
		return io_mod_date;
	}

	public void setIo_mod_date(Date io_mod_date) {
		this.io_mod_date = io_mod_date;
	}

	public Date getIo_decision_date() {
		return io_decision_date;
	}

	public void setIo_decision_date(Date io_decision_date) {
		this.io_decision_date = io_decision_date;
	}

	public String getIo_case_year() {
		return io_case_year;
	}

	public void setIo_case_year(String io_case_year) {
		this.io_case_year = io_case_year;
	}

	public Long getIo_fd_mid() {
		return io_fd_mid;
	}

	public void setIo_rcd_mid(Long io_fd_mid) {
		this.io_fd_mid = io_fd_mid;
	}

	/*public CaseType getHcCaseType() {
		return hcCaseType;
	}

	public void setHcCaseType(CaseType hcCaseType) {
		this.hcCaseType = hcCaseType;
	}*/

	/*public LowerCourtCaseType getLcCaseType() {
		return lcCaseType;
	}

	public void setLcCaseType(LowerCourtCaseType lcCaseType) {
		this.lcCaseType = lcCaseType;
	}*/

/*	public LowerCourtTypes getCourtType() {
		return courtType;
	}

	public void setCourtType(LowerCourtTypes courtType) {
		this.courtType = courtType;
	}*/

	public Integer getIo_rec_status() {
		return io_rec_status;
	}

	public void setIo_rec_status(Integer io_rec_status) {
		this.io_rec_status = io_rec_status;
	}

	public void setIo_fd_mid(Long io_fd_mid) {
		this.io_fd_mid = io_fd_mid;
	}

	/*public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}*/
	
	
}
