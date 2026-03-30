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
@Table(name="amendments")
public class Amendment {
		
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE,generator="am_seq")
	@SequenceGenerator(name="am_seq", sequenceName="am_seq", allocationSize=1)
	@Column(name = "am_id")
	private Long am_id;
	
	@Column(name="am_type")
	private String am_type;
	
	@Column(name="am_at_mid")
	private Long am_at_mid;
	
	@Column(name="am_created_by")
	private Long am_created_by;
	
	@Column(name="am_created")
	private Date am_created;
	
	@Column(name="am_fd_mid")
	private Long am_fd_mid;
	
	@Column(name="am_status")
	private Long am_status;
	
	@Column(name="am_um_mid")
	private Long am_um_mid;
	
	@Column(name="am_document_no")
	private Integer am_document_no;
	
	@Column(name="am_document_year")
	private Integer am_document_year;
	
	@Column(name="am_uploaded_date")
	private Date am_uploaded_date;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "am_at_mid",insertable = false, updatable = false)
	private EfilingApplicationTypes applicationType;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "am_status",insertable = false, updatable = false)
	private EfilingLookup lkStatus;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "am_um_mid",insertable = false, updatable = false)
	private EfilingUser userMaster;
	
	public Long getAm_id() {
		return am_id;
	}

	public void setAm_id(Long am_id) {
		this.am_id = am_id;
	}

	public String getAm_type() {
		return am_type;
	}

	public void setAm_type(String am_type) {
		this.am_type = am_type;
	}

	public Long getAm_at_mid() {
		return am_at_mid;
	}

	public void setAm_at_mid(Long am_at_mid) {
		this.am_at_mid = am_at_mid;
	}

	public Long getAm_created_by() {
		return am_created_by;
	}

	public void setAm_created_by(Long am_created_by) {
		this.am_created_by = am_created_by;
	}

	public Date getAm_created() {
		return am_created;
	}

	public void setAm_created(Date am_created) {
		this.am_created = am_created;
	}

	public Long getAm_fd_mid() {
		return am_fd_mid;
	}

	public void setAm_fd_mid(Long am_fd_mid) {
		this.am_fd_mid = am_fd_mid;
	}

	public Long getAm_status() {
		return am_status;
	}

	public void setAm_status(Long am_status) {
		this.am_status = am_status;
	}

	public EfilingApplicationTypes getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(EfilingApplicationTypes applicationType) {
		this.applicationType = applicationType;
	}

	public EfilingLookup getLkStatus() {
		return lkStatus;
	}

	public void setLkStatus(EfilingLookup lkStatus) {
		this.lkStatus = lkStatus;
	}

	public Long getAm_um_mid() {
		return am_um_mid;
	}

	public void setAm_um_mid(Long am_um_mid) {
		this.am_um_mid = am_um_mid;
	}

	public EfilingUser getUserMaster() {
		return userMaster;
	}

	public void setUserMaster(EfilingUser userMaster) {
		this.userMaster = userMaster;
	}

	public Integer getAm_document_no() {
		return am_document_no;
	}

	public void setAm_document_no(Integer am_document_no) {
		this.am_document_no = am_document_no;
	}

	public Integer getAm_document_year() {
		return am_document_year;
	}

	public void setAm_document_year(Integer am_document_year) {
		this.am_document_year = am_document_year;
	}

	public Date getAm_uploaded_date() {
		return am_uploaded_date;
	}

	public void setAm_uploaded_date(Date am_uploaded_date) {
		this.am_uploaded_date = am_uploaded_date;
	}

	@Override
	public String toString() {
		return "Amendment [am_id=" + am_id + ", am_type=" + am_type + ", am_at_mid=" + am_at_mid + ", am_created_by="
				+ am_created_by + ", am_created=" + am_created + ", am_fd_mid=" + am_fd_mid + ", am_status=" + am_status
				+ ", am_um_mid=" + am_um_mid + ", am_document_no=" + am_document_no + ", am_document_year="
				+ am_document_year + ", am_uploaded_date=" + am_uploaded_date + ", applicationType=" + applicationType
				+ ", lkStatus=" + lkStatus + ", userMaster=" + userMaster + "]";
	}	
	
	
}
