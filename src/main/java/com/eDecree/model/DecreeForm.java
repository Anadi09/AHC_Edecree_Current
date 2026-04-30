package com.eDecree.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

@Entity
@Table(name="decree_form")
public class DecreeForm {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="decree_field_seq")
	@SequenceGenerator(name="decree_field_seq", sequenceName="decree_field_seq", allocationSize=1)
	@Column(name="df_id")
	private Long df_id;
	
	@Column(name="df_fd_mid")
	private Long df_fd_mid;
	
	@Column(name="df_assign_to")
	private Long df_assign_to;
	
	@Column(name="df_first_div")
	private String df_first_div;
	
	@Column(name="df_file_name")
	private String df_file_name;
	
	
	@Column(name="df_final_file")
	private String df_final_file;

	@Column(name="df_2nd_div")
	private String df_2nd_div;
	

	@Column(name="df_3rd_div")
	private String df_3rd_div;
	

	@Column(name="df_4th_div")
	private String df_4th_div;
	

	@Column(name="df_5th_div")
	private String df_5th_div;
	

	public String getDf_5th_div() {
		return df_5th_div;
	}

	public void setDf_5th_div(String df_5th_div) {
		this.df_5th_div = df_5th_div;
	}

	@Column(name="df_editor")
	private String df_editor;
	
	@Column(name="df_cr_by")
	private Long df_cr_by;
	
	@Column(name="df_mod_by")
	private Long df_mod_by;
	
	@Column(name="df_cr_date")
	private Date df_cr_date;
	
	@Column(name="df_exam_by")
	private Long df_exam_by;
	
	@Column(name="df_exam_date")
	private Date df_exam_date;
	
	@Column(name="df_exam2_by")
	private Long df_exam2_by;
	
	@Column(name="df_exam2_date")
	private Date df_exam2_date;
	
	
	@Column(name="df_exam3_by")
	private Long df_exam3_by;
	
	@Column(name="df_exam3_date")
	private Date df_exam3_date;
	
	
	
	@Column(name="df_aprrove_by")
	private Long df_aprrove_by;
	
	@Column(name="df_approve_date")
	private Date df_approve_date;
	
	@Column(name="df_is_approved")
	private Boolean isApproved;
	
	
	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	@Column(name="df_stage_lid")
	private Long df_stage_lid;
	
	
	@Column(name="df_mod_date")
	private Date df_mod_date;
	
	
	@Column(name="df_locked")
	private Boolean df_locked =false;
	
		
	@Column(name="df_remark")
	private String df_remark;
	
	@Column(name="df_rec_status")
	private Integer df_rec_status;
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "dfu_df_mid")
	@Where(clause="dfu_rec_status=1")
	private List<DecreeFileUploaded> decreeFileUploaded;
	
	

	
	
	public Integer getDf_rec_status() {
		return df_rec_status;
	}

	public void setDf_rec_status(Integer df_rec_status) {
		this.df_rec_status = df_rec_status;
	}

	public List<DecreeFileUploaded> getDecreeFileUploaded() {
		return decreeFileUploaded;
	}

	public void setDecreeFileUploaded(List<DecreeFileUploaded> decreeFileUploaded) {
		this.decreeFileUploaded = decreeFileUploaded;
	}

	public String getDf_remark() {
		return df_remark;
	}

	public void setDf_remark(String df_remark) {
		this.df_remark = df_remark;
	}

	public String getDf_final_file() {
		return df_final_file;
	}

	public void setDf_final_file(String df_final_file) {
		this.df_final_file = df_final_file;
	}

	public String getDf_file_name() {
		return df_file_name;
	}

	public void setDf_file_name(String df_file_name) {
		this.df_file_name = df_file_name;
	}

	public Long getDf_assign_to() {
		return df_assign_to;
	}

	public void setDf_assign_to(Long df_assign_to) {
		this.df_assign_to = df_assign_to;
	}

	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "df_cr_by",insertable = false, updatable = false)
	private User crBy;
	
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "df_assign_to",insertable = false, updatable = false)
	private User currentUsr;
	
	
	public User getCurrentUsr() {
		return currentUsr;
	}

	public void setCurrentUsr(User currentUsr) {
		this.currentUsr = currentUsr;
	}

	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "df_exam_by",insertable = false, updatable = false)
	private User exBy;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "df_exam2_by",insertable = false, updatable = false)
	private User ex2By;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "df_exam3_by",insertable = false, updatable = false)
	private User ex3By;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "df_aprrove_by",insertable = false, updatable = false)
	private User aprBy;
	
	
	
	
	


	public Long getDf_exam2_by() {
		return df_exam2_by;
	}

	public void setDf_exam2_by(Long df_exam2_by) {
		this.df_exam2_by = df_exam2_by;
	}

	public Date getDf_exam2_date() {
		return df_exam2_date;
	}

	public void setDf_exam2_date(Date df_exam2_date) {
		this.df_exam2_date = df_exam2_date;
	}

	public Long getDf_exam3_by() {
		return df_exam3_by;
	}

	public void setDf_exam3_by(Long df_exam3_by) {
		this.df_exam3_by = df_exam3_by;
	}

	public Date getDf_exam3_date() {
		return df_exam3_date;
	}

	public void setDf_exam3_date(Date df_exam3_date) {
		this.df_exam3_date = df_exam3_date;
	}

	public User getExBy() {
		return exBy;
	}

	public void setExBy(User exBy) {
		this.exBy = exBy;
	}

	public User getEx2By() {
		return ex2By;
	}

	public void setEx2By(User ex2By) {
		this.ex2By = ex2By;
	}

	public User getEx3By() {
		return ex3By;
	}

	public void setEx3By(User ex3By) {
		this.ex3By = ex3By;
	}

	public User getAprBy() {
		return aprBy;
	}

	public void setAprBy(User aprBy) {
		this.aprBy = aprBy;
	}

	public User getCrBy() {
		return crBy;
	}

	public void setCrBy(User crBy) {
		this.crBy = crBy;
	}

	public Boolean getDf_locked() {
		return df_locked;
	}

	public void setDf_locked(Boolean df_locked) {
		this.df_locked = df_locked;
	}

	@Transient
	private String pdfStrem;
	
	public String getPdfStrem() {
		return pdfStrem;
	}

	public void setPdfStrem(String pdfStrem) {
		this.pdfStrem = pdfStrem;
	}

	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "df_fd_mid",insertable = false, updatable = false)
	private CaseFileDetail caseFileDetail;
	
	
	
	
	

	public Long getDf_exam_by() {
		return df_exam_by;
	}

	public void setDf_exam_by(Long df_exam_by) {
		this.df_exam_by = df_exam_by;
	}

	public Date getDf_exam_date() {
		return df_exam_date;
	}

	public void setDf_exam_date(Date df_exam_date) {
		this.df_exam_date = df_exam_date;
	}

	public Long getDf_aprrove_by() {
		return df_aprrove_by;
	}

	public void setDf_aprrove_by(Long df_aprrove_by) {
		this.df_aprrove_by = df_aprrove_by;
	}

	public Date getDf_approve_date() {
		return df_approve_date;
	}

	public void setDf_approve_date(Date df_approve_date) {
		this.df_approve_date = df_approve_date;
	}

	public Long getDf_stage_lid() {
		return df_stage_lid;
	}

	public void setDf_stage_lid(Long df_stage_lid) {
		this.df_stage_lid = df_stage_lid;
	}

	public CaseFileDetail getCaseFileDetail() {
		return caseFileDetail;
	}

	public void setCaseFileDetail(CaseFileDetail caseFileDetail) {
		this.caseFileDetail = caseFileDetail;
	}

	public Long getDf_cr_by() {
		return df_cr_by;
	}

	public void setDf_cr_by(Long df_cr_by) {
		this.df_cr_by = df_cr_by;
	}

	public Long getDf_mod_by() {
		return df_mod_by;
	}

	public void setDf_mod_by(Long df_mod_by) {
		this.df_mod_by = df_mod_by;
	}

	public Date getDf_cr_date() {
		return df_cr_date;
	}

	public void setDf_cr_date(Date df_cr_date) {
		this.df_cr_date = df_cr_date;
	}

	public Date getDf_mod_date() {
		return df_mod_date;
	}

	public void setDf_mod_date(Date df_mod_date) {
		this.df_mod_date = df_mod_date;
	}

	public String getDf_editor() {
		return df_editor;
	}

	public void setDf_editor(String df_editor) {
		this.df_editor = df_editor;
	}

	public String getDf_2nd_div() {
		return df_2nd_div;
	}

	public void setDf_2nd_div(String df_2nd_div) {
		this.df_2nd_div = df_2nd_div;
	}

	public String getDf_3rd_div() {
		return df_3rd_div;
	}

	public void setDf_3rd_div(String df_3rd_div) {
		this.df_3rd_div = df_3rd_div;
	}

	public String getDf_4th_div() {
		return df_4th_div;
	}

	public void setDf_4th_div(String df_4th_div) {
		this.df_4th_div = df_4th_div;
	}

	public Long getDf_id() {
		return df_id;
	}

	public void setDf_id(Long df_id) {
		this.df_id = df_id;
	}

	public Long getDf_fd_mid() {
		return df_fd_mid;
	}

	public void setDf_fd_mid(Long df_fd_mid) {
		this.df_fd_mid = df_fd_mid;
	}

	public String getDf_first_div() {
		return df_first_div;
	}

	public void setDf_first_div(String df_first_div) {
		this.df_first_div = df_first_div;
	}
	
	

}
