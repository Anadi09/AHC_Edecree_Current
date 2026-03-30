package com.efiling.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "registered_case_details")
public class RegisteredCaseDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "registeredcase_seq")
	@SequenceGenerator(name = "registeredcase_seq", sequenceName = "registeredcase_seq", allocationSize = 1)
	@Column(name = "rcd_id")
	private Long rcd_id;


	@Column(name = "rcd_specail_category")
	private Long rcd_specail_category;

	
	@Column(name="rcd_dt_id")
	private Long rcd_dt_id;

	
	@Column(name = "rcd_cr_by")
	private Long rcd_cr_by;

	@Column(name = "rcd_cr_date")
	private Date rcd_cr_date;

	@Column(name = "rcd_mod_by")
	private Long rcd_mod_by;

	@Column(name = "rcd_mod_date")
	private Date rcd_mod_date;

	@Column(name = "rcd_bench_code_lid")
	private Long rcd_bench_code_lid;

	@Column(name = "rcd_stage_lid")
	private Long rcd_stage_lid;
	
	@Column(name = "rcd_assign_to")
	private Long rcd_assign_to;

	@Column(name = "rcd_sequence")
	private Integer rcd_sequence;


	/*@Column(name = "rcd_accused_status")
	private Integer rcd_accused_status;*/

	@Column(name = "rcd_fir_no")
	private String rcd_fir_no;

	@Column(name = "rcd_fir_date")
	private Date rcd_fir_date;

	@Column(name = "rcd_police_station")
	private String rcd_police_station;

	@Column(name = "rcd_sen_award")
	private String rcd_sen_award;

	@Column(name = "rcd_sen_under")
	private String rcd_sen_under;


	@Column(name = "rcd_draft_no")
	private String rcd_draft_no;
	
	@Column(name = "rcd_diary_no")
	private String rcd_diary_no;
	
	@Column(name="rcd_case_year")
	private Integer rcd_case_year;

	@Column(name="rcd_sen_ctz")
    private 	boolean  rcd_sen_ctz;
	
	@Column(name="rcd_sc_st")
    private	boolean rcd_sc_st;
	
	@Column(name="rcd_wm_ch")
    private boolean  rcd_wm_ch;
	
	@Column(name="rcd_dvg")
	private boolean  rcd_dvg;
	
	@Column(name="rcd_lg_ad")
	private boolean  rcd_lg_ad;
	
	@Column(name="rcd_cust")
	private boolean  rcd_cust; 
	
	@Column(name="rcd_ct_id")
	private Long rcd_ct_id;
	
	@Column(name="rcd_case_no")
	private Integer rcd_case_no;
	
	/*@OneToOne
	@JoinColumn(name="rcd_ct_id",referencedColumnName="_id",insertable=false,updatable=false)
	private CaseType caseType;
	*/
		
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "rcd_stage_lid",insertable = false, updatable = false)
	private EfilingLookup caseStage;
	
	public Long getRcd_id() {
		return rcd_id;
	}

	public void setRcd_id(Long rcd_id) {
		this.rcd_id = rcd_id;
	}

	public Long getRcd_specail_category() {
		return rcd_specail_category;
	}

	public void setRcd_specail_category(Long rcd_specail_category) {
		this.rcd_specail_category = rcd_specail_category;
	}


	public Long getRcd_cr_by() {
		return rcd_cr_by;
	}

	public void setRcd_cr_by(Long rcd_cr_by) {
		this.rcd_cr_by = rcd_cr_by;
	}

	public Date getRcd_cr_date() {
		return rcd_cr_date;
	}

	public void setRcd_cr_date(Date rcd_cr_date) {
		this.rcd_cr_date = rcd_cr_date;
	}

	public Long getRcd_mod_by() {
		return rcd_mod_by;
	}

	public void setRcd_mod_by(Long rcd_mod_by) {
		this.rcd_mod_by = rcd_mod_by;
	}

	public Date getRcd_mod_date() {
		return rcd_mod_date;
	}

	public void setRcd_mod_date(Date rcd_mod_date) {
		this.rcd_mod_date = rcd_mod_date;
	}

	public Long getRcd_bench_code_lid() {
		return rcd_bench_code_lid;
	}

	public void setRcd_bench_code_lid(Long rcd_bench_code_lid) {
		this.rcd_bench_code_lid = rcd_bench_code_lid;
	}

	public Long getRcd_stage_lid() {
		return rcd_stage_lid;
	}

	public void setRcd_stage_lid(Long rcd_stage_lid) {
		this.rcd_stage_lid = rcd_stage_lid;
	}

	public Integer getRcd_sequence() {
		return rcd_sequence;
	}

	public void setRcd_sequence(Integer rcd_sequence) {
		this.rcd_sequence = rcd_sequence;
	}


	public String getRcd_fir_no() {
		return rcd_fir_no;
	}

	public void setRcd_fir_no(String rcd_fir_no) {
		this.rcd_fir_no = rcd_fir_no;
	}

	public Date getRcd_fir_date() {
		return rcd_fir_date;
	}

	public void setRcd_fir_date(Date rcd_fir_date) {
		this.rcd_fir_date = rcd_fir_date;
	}

	public String getRcd_police_station() {
		return rcd_police_station;
	}

	public void setRcd_police_station(String rcd_police_station) {
		this.rcd_police_station = rcd_police_station;
	}

	public String getRcd_sen_award() {
		return rcd_sen_award;
	}

	public void setRcd_sen_award(String rcd_sen_award) {
		this.rcd_sen_award = rcd_sen_award;
	}

	public String getRcd_sen_under() {
		return rcd_sen_under;
	}

	public void setRcd_sen_under(String rcd_sen_under) {
		this.rcd_sen_under = rcd_sen_under;
	}

	public String getRcd_draft_no() {
		return rcd_draft_no;
	}

	public void setRcd_draft_no(String rcd_draft_no) {
		this.rcd_draft_no = rcd_draft_no;
	}


	public Integer getRcd_case_year() {
		return rcd_case_year;
	}

	public void setRcd_case_year(Integer rcd_case_year) {
		this.rcd_case_year = rcd_case_year;
	}

	public boolean isRcd_sen_ctz() {
		return rcd_sen_ctz;
	}

	public void setRcd_sen_ctz(boolean rcd_sen_ctz) {
		this.rcd_sen_ctz = rcd_sen_ctz;
	}

	public boolean isRcd_sc_st() {
		return rcd_sc_st;
	}

	public void setRcd_sc_st(boolean rcd_sc_st) {
		this.rcd_sc_st = rcd_sc_st;
	}

	public boolean isRcd_wm_ch() {
		return rcd_wm_ch;
	}

	public void setRcd_wm_ch(boolean rcd_wm_ch) {
		this.rcd_wm_ch = rcd_wm_ch;
	}

	public boolean isRcd_dvg() {
		return rcd_dvg;
	}

	public void setRcd_dvg(boolean rcd_dvg) {
		this.rcd_dvg = rcd_dvg;
	}
	
	public boolean isRcd_lg_ad() {
		return rcd_lg_ad;
	}

	public void setRcd_lg_ad(boolean rcd_lg_ad) {
		this.rcd_lg_ad = rcd_lg_ad;
	}

	public boolean isRcd_cust() {
		return rcd_cust;
	}

	public void setRcd_cust(boolean rcd_cust) {
		this.rcd_cust = rcd_cust;
	}

	public Long getRcd_dt_id() {
		return rcd_dt_id;
	}

	public void setRcd_dt_id(Long rcd_dt_id) {
		this.rcd_dt_id = rcd_dt_id;
	}

	public Long getRcd_ct_id() {
		return rcd_ct_id;
	}

	public void setRcd_ct_id(Long rcd_ct_id) {
		this.rcd_ct_id = rcd_ct_id;
	}

	public Long getRcd_assign_to() {
		return rcd_assign_to;
	}

	public void setRcd_assign_to(Long rcd_assign_to) {
		this.rcd_assign_to = rcd_assign_to;
	}

	public String getRcd_diary_no() {
		return rcd_diary_no;
	}

	public void setRcd_diary_no(String rcd_diary_no) {
		this.rcd_diary_no = rcd_diary_no;
	}

	public EfilingLookup getCaseStage() {
		return caseStage;
	}

	public void setCaseStage(EfilingLookup caseStage) {
		this.caseStage = caseStage;
	}

	public Integer getRcd_case_no() {
		return rcd_case_no;
	}

	public void setRcd_case_no(Integer rcd_case_no) {
		this.rcd_case_no = rcd_case_no;
	}
	
	
}
