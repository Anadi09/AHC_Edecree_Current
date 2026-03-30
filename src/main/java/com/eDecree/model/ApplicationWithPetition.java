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

@Entity
@Table(name ="application_with_petition")
public class ApplicationWithPetition {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "awp_seq")
	@SequenceGenerator(name = "awp_seq", sequenceName = "awp_seq", allocationSize = 1)
	@Column(name = "awp_id")
	private Long awp_id;
	
	@Column(name="awp_sd_mid")
	private Long awp_sd_mid;
	
	@Column(name="awp_ap_no")
	private Long awp_ap_no;
	
	@Column(name="awp_ap_year")
	private Integer awp_ap_year;
	
	@Column(name="awp_ap_at_mid")
	private Long awp_ap_at_mid;
	
	@Column(name ="awp_rec_status")
	private Integer awp_rec_status;
	
	@Column(name="awp_cr_date")
	private Date awp_cr_date;
	
	@Column(name="awp_submitted_by")
	private Long awp_submitted_by;
	
	@Column(name="awp_fd_mid")
	private Long awp_fd_mid;
	
	@OneToOne
	@JoinColumn(name="awp_ap_at_mid",referencedColumnName="at_id",insertable=false,updatable=false)
	private ApplicationTypes applicationType;
	
	
	public ApplicationTypes getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(ApplicationTypes applicationType) {
		this.applicationType = applicationType;
	}

	public Long getAwp_id() {
		return awp_id;
	}

	public void setAwp_id(Long awp_id) {
		this.awp_id = awp_id;
	}

	public Long getAwp_sd_mid() {
		return awp_sd_mid;
	}

	public void setAwp_sd_mid(Long awp_sd_mid) {
		this.awp_sd_mid = awp_sd_mid;
	}

	public Long getAwp_ap_no() {
		return awp_ap_no;
	}

	public void setAwp_ap_no(Long awp_ap_no) {
		this.awp_ap_no = awp_ap_no;
	}

	public Integer getAwp_ap_year() {
		return awp_ap_year;
	}

	public void setAwp_ap_year(Integer awp_ap_year) {
		this.awp_ap_year = awp_ap_year;
	}

	public Long getAwp_ap_at_mid() {
		return awp_ap_at_mid;
	}

	public void setAwp_ap_at_mid(Long awp_ap_at_mid) {
		this.awp_ap_at_mid = awp_ap_at_mid;
	}

	public Integer getAwp_rec_status() {
		return awp_rec_status;
	}

	public void setAwp_rec_status(Integer awp_rec_status) {
		this.awp_rec_status = awp_rec_status;
	}

	public Date getAwp_cr_date() {
		return awp_cr_date;
	}

	public void setAwp_cr_date(Date awp_cr_date) {
		this.awp_cr_date = awp_cr_date;
	}
	
	

	public Long getAwp_submitted_by() {
		return awp_submitted_by;
	}

	public void setAwp_submitted_by(Long awp_submitted_by) {
		this.awp_submitted_by = awp_submitted_by;
	}

	public Long getAwp_fd_mid() {
		return awp_fd_mid;
	}

	public void setAwp_fd_mid(Long awp_fd_mid) {
		this.awp_fd_mid = awp_fd_mid;
	}

	@Override
	public String toString() {
		return "ApplicationWithPetition [awp_id=" + awp_id + ", awp_sd_mid=" + awp_sd_mid + ", awp_ap_no=" + awp_ap_no
				+ ", awp_ap_year=" + awp_ap_year + ", awp_ap_at_mid=" + awp_ap_at_mid + ", awp_rec_status="
				+ awp_rec_status + ", awp_cr_date=" + awp_cr_date + ", awp_submitted_by=" + awp_submitted_by
				+ ", awp_fd_mid=" + awp_fd_mid + ", applicationType=" + applicationType + "]";
	}

	

}
