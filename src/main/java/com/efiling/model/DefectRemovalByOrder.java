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
@Table(name="defect_removal_petition")
public class DefectRemovalByOrder {
		
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE,generator="defect_removal_petition_seq")
	@SequenceGenerator(name="defect_removal_petition_seq", sequenceName="defect_removal_petition_seq", allocationSize=1)
	@Column(name = "drp_id")
	private Long drp_id;	
	
	@Column(name="drp_cr_by")
	private Long drp_cr_by;
	
	@Column(name="drp_cr_date")
	private Date drp_cr_date;
	
	@Column(name="drp_fd_mid")
	private Long drp_fd_mid;
	
	@Column(name="drp_stage_lid")
	private Long drp_stage_lid;
	
	@Column(name="drp_assign_to")
	private Long drp_assign_to;
	
	@Column(name="drp_uploaded_date")
	private Date drp_uploaded_date;
	
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "drp_stage_lid",insertable = false, updatable = false)
	private EfilingLookup lkStatus;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "drp_assign_to",insertable = false, updatable = false)
	private EfilingUser userMaster;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "drp_fd_mid",insertable = false, updatable = false)
	private EfilingCaseFileDetail caseFileDetail;
	
	@Column(name="drp_advum_mid")
	private Long drp_advUm_mid;
	
	

	public Long getDrp_advUm_mid() {
		return drp_advUm_mid;
	}

	public void setDrp_advUm_mid(Long drp_advUm_mid) {
		this.drp_advUm_mid = drp_advUm_mid;
	}

	public Long getDrp_id() {
		return drp_id;
	}

	public void setDrp_id(Long drp_id) {
		this.drp_id = drp_id;
	}

	public Long getDrp_cr_by() {
		return drp_cr_by;
	}

	public void setDrp_cr_by(Long drp_cr_by) {
		this.drp_cr_by = drp_cr_by;
	}

	public Date getDrp_cr_date() {
		return drp_cr_date;
	}

	public void setDrp_cr_date(Date drp_cr_date) {
		this.drp_cr_date = drp_cr_date;
	}

	public Long getDrp_fd_mid() {
		return drp_fd_mid;
	}

	public void setDrp_fd_mid(Long drp_fd_mid) {
		this.drp_fd_mid = drp_fd_mid;
	}

	public Long getDrp_stage_lid() {
		return drp_stage_lid;
	}

	public void setDrp_stage_lid(Long drp_stage_lid) {
		this.drp_stage_lid = drp_stage_lid;
	}

	public Long getDrp_assign_to() {
		return drp_assign_to;
	}

	public void setDrp_assign_to(Long drp_assign_to) {
		this.drp_assign_to = drp_assign_to;
	}

	public Date getDrp_uploaded_date() {
		return drp_uploaded_date;
	}

	public void setDrp_uploaded_date(Date drp_uploaded_date) {
		this.drp_uploaded_date = drp_uploaded_date;
	}

	public EfilingLookup getLkStatus() {
		return lkStatus;
	}

	public void setLkStatus(EfilingLookup lkStatus) {
		this.lkStatus = lkStatus;
	}

	public EfilingUser getUserMaster() {
		return userMaster;
	}

	public void setUserMaster(EfilingUser userMaster) {
		this.userMaster = userMaster;
	}

	public EfilingCaseFileDetail getCaseFileDetail() {
		return caseFileDetail;
	}

	public void setCaseFileDetail(EfilingCaseFileDetail caseFileDetail) {
		this.caseFileDetail = caseFileDetail;
	}

	@Override
	public String toString() {
		return "DefectRemovalByOrder [drp_id=" + drp_id + ", drp_cr_by=" + drp_cr_by + ", drp_cr_date=" + drp_cr_date
				+ ", drp_fd_mid=" + drp_fd_mid + ", drp_stage_lid=" + drp_stage_lid + ", drp_assign_to=" + drp_assign_to
				+ ", drp_uploaded_date=" + drp_uploaded_date + ", lkStatus=" + lkStatus + ", userMaster=" + userMaster
				+ ", caseFileDetail=" + caseFileDetail + ", drp_advUm_mid=" + drp_advUm_mid + "]";
	}

	
	
	


	
}

