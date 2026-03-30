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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="media_file_stage")
public class MediaFileStage {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="mediafilestageseq")
	@SequenceGenerator(name="mediafilestageseq", sequenceName="mediafilestageseq", allocationSize=1)
	@Column(name="mfs_id")
	private Long mfs_id;
	
	@Column(name="mfs_mf_mid")
	private Long mfs_mf_mid;
	 
	@Column(name="mfs_cr_date")
	private Date mfs_cr_date;
	
	@Column(name="mfs_cr_by")
	private Long mfs_cr_by;
	
	@Column(name="mfs_remark")
	private String mfs_remark;

	@Column(name="mfs_reject_status")
	private String mfs_reject_status;
	
	@Column(name="mfs_ip_address")
	private String mfs_ip_address;

	public Long getMfs_id() {
		return mfs_id;
	}

	public void setMfs_id(Long mfs_id) {
		this.mfs_id = mfs_id;
	}

	public Long getMfs_mf_mid() {
		return mfs_mf_mid;
	}

	public void setMfs_mf_mid(Long mfs_mf_mid) {
		this.mfs_mf_mid = mfs_mf_mid;
	}

	public Date getMfs_cr_date() {
		return mfs_cr_date;
	}

	public void setMfs_cr_date(Date mfs_cr_date) {
		this.mfs_cr_date = mfs_cr_date;
	}

	public Long getMfs_cr_by() {
		return mfs_cr_by;
	}

	public void setMfs_cr_by(Long mfs_cr_by) {
		this.mfs_cr_by = mfs_cr_by;
	}

	public String getMfs_remark() {
		return mfs_remark;
	}

	public void setMfs_remark(String mfs_remark) {
		this.mfs_remark = mfs_remark;
	}

	public String getMfs_reject_status() {
		return mfs_reject_status;
	}

	public void setMfs_reject_status(String mfs_reject_status) {
		this.mfs_reject_status = mfs_reject_status;
	}

	public String getMfs_ip_address() {
		return mfs_ip_address;
	}

	public void setMfs_ip_address(String mfs_ip_address) {
		this.mfs_ip_address = mfs_ip_address;
	}
	
}
