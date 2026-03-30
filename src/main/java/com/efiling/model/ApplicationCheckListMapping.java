package com.efiling.model;

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

@Entity
@Table(name="application_checklist_mapping")
public class ApplicationCheckListMapping 
{
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="application_checklist_seq")
	@SequenceGenerator(name="application_checklist_seq", sequenceName="application_checklist_seq", allocationSize=1)
	@Column(name="cm_id")
	private Long cm_id;
	
	@Column(name="cm_checklist_id")
	private Long cm_checklist_id;
	
	@Column(name="cm_ap_mid")
	private Long cm_ap_mid;
	
	@Column(name="cm_cr_date")
	private Date cm_cr_date;

	@Column(name="cm_cr_by")
	private Long cm_cr_by;
	
	@Column(name="cm_rec_status")
	private Integer cm_rec_status;
	
	@Column(name="cm_remark")
	private String cm_remark;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cm_checklist_id",referencedColumnName="c_id",insertable = false, updatable = false)
	private CheckList checklist;

	public Long getCm_id() {
		return cm_id;
	}

	public void setCm_id(Long cm_id) {
		this.cm_id = cm_id;
	}

	public Long getCm_checklist_id() {
		return cm_checklist_id;
	}

	public void setCm_checklist_id(Long cm_checklist_id) {
		this.cm_checklist_id = cm_checklist_id;
	}

	public Long getCm_ap_mid() {
		return cm_ap_mid;
	}

	public void setCm_ap_mid(Long cm_ap_mid) {
		this.cm_ap_mid = cm_ap_mid;
	}

	public Date getCm_cr_date() {
		return cm_cr_date;
	}

	public void setCm_cr_date(Date cm_cr_date) {
		this.cm_cr_date = cm_cr_date;
	}

	public Long getCm_cr_by() {
		return cm_cr_by;
	}

	public void setCm_cr_by(Long cm_cr_by) {
		this.cm_cr_by = cm_cr_by;
	}

	public Integer getCm_rec_status() {
		return cm_rec_status;
	}

	public void setCm_rec_status(Integer cm_rec_status) {
		this.cm_rec_status = cm_rec_status;
	}

	public String getCm_remark() {
		return cm_remark;
	}

	public void setCm_remark(String cm_remark) {
		this.cm_remark = cm_remark;
	}

	public CheckList getChecklist() {
		return checklist;
	}

	public void setChecklist(CheckList checklist) {
		this.checklist = checklist;
	}
	
	
	
}
