package com.eDecree.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "sub_applications")
public class SubApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sub_applications_seq")
	@SequenceGenerator(name = "sub_applications_seq", sequenceName = "sub_applications_seq", allocationSize = 1)
	@Column(name = "sb_ap_id")
	private Long sb_ap_id;

	@Column(name = "sb_ap_sd_mid")
	private Long sb_ap_sd_mid;

	@Column(name = "sb_ap_no")
	private Integer sb_ap_no;

	@Column(name = "sb_ap_year")
	private Integer sb_ap_year;

	@Column(name = "sb_ap_at_mid")
	private Long sb_ap_at_mid;

	@Column(name = "sb_ap_rec_status")
	private Integer sb_ap_rec_status;

	@Column(name = "sb_ap_cr_date")
	private Date sb_ap_cr_date;

	@Column(name = "sb_ap_from_page")
	private Long sb_ap_from_page;

	@Column(name = "sb_ap_to_page")
	private Long sb_ap_to_page;
	
	@Column(name="sb_ap_fd_mid")
	private Long sb_ap_fd_mid;
	
	
	
	@OneToOne
	@JoinColumn(name="sb_ap_at_mid",referencedColumnName="at_id",insertable=false,updatable=false)
	private ApplicationTypes applicationType;
	
	
	public Long getSb_ap_id() {
		return sb_ap_id;
	}

	public void setSb_ap_id(Long sb_ap_id) {
		this.sb_ap_id = sb_ap_id;
	}

	

	public Long getSb_ap_sd_mid() {
		return sb_ap_sd_mid;
	}

	public void setSb_ap_sd_mid(Long sb_ap_sd_mid) {
		this.sb_ap_sd_mid = sb_ap_sd_mid;
	}

	public Long getSb_ap_fd_mid() {
		return sb_ap_fd_mid;
	}

	public void setSb_ap_fd_mid(Long sb_ap_fd_mid) {
		this.sb_ap_fd_mid = sb_ap_fd_mid;
	}

	public Integer getSb_ap_no() {
		return sb_ap_no;
	}

	public void setSb_ap_no(Integer sb_ap_no) {
		this.sb_ap_no = sb_ap_no;
	}

	public Integer getSb_ap_year() {
		return sb_ap_year;
	}

	public void setSb_ap_year(Integer sb_ap_year) {
		this.sb_ap_year = sb_ap_year;
	}

	public Integer getSb_ap_rec_status() {
		return sb_ap_rec_status;
	}

	public void setSb_ap_rec_status(Integer sb_ap_rec_status) {
		this.sb_ap_rec_status = sb_ap_rec_status;
	}

	public Date getSb_ap_cr_date() {
		return sb_ap_cr_date;
	}

	public void setSb_ap_cr_date(Date sb_ap_cr_date) {
		this.sb_ap_cr_date = sb_ap_cr_date;
	}

	public Long getSb_ap_at_mid() {
		return sb_ap_at_mid;
	}

	public void setSb_ap_at_mid(Long sb_ap_at_mid) {
		this.sb_ap_at_mid = sb_ap_at_mid;
	}

	public Long getSb_ap_from_page() {
		return sb_ap_from_page;
	}

	public void setSb_ap_from_page(Long sb_ap_from_page) {
		this.sb_ap_from_page = sb_ap_from_page;
	}

	public Long getSb_ap_to_page() {
		return sb_ap_to_page;
	}

	public void setSb_ap_to_page(Long sb_ap_to_page) {
		this.sb_ap_to_page = sb_ap_to_page;
	}
	
	

	public ApplicationTypes getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(ApplicationTypes applicationType) {
		this.applicationType = applicationType;
	}

	@Override
	public String toString() {
		return "SubApplication [sb_ap_id=" + sb_ap_id + ", sb_ap_sd_mid=" + sb_ap_sd_mid + ", sb_ap_no=" + sb_ap_no
				+ ", sb_ap_year=" + sb_ap_year + ", sb_ap_at_mid=" + sb_ap_at_mid + ", sb_ap_rec_status="
				+ sb_ap_rec_status + ", sb_ap_cr_date=" + sb_ap_cr_date + ", sb_ap_from_page=" + sb_ap_from_page
				+ ", sb_ap_to_page=" + sb_ap_to_page + ", sb_ap_fd_mid=" + sb_ap_fd_mid + ", applicationType="
				+ applicationType + "]";
	}

	

}
