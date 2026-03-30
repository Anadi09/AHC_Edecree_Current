package com.eDecree.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="decree_file_uploaded")
public class DecreeFileUploaded {

	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="dfu_seq")
	@SequenceGenerator(name="dfu_seq", sequenceName="dfu_seq", allocationSize=1)
	@Column(name="dfu_id")
	private Long dfu_id;
	
	@Column(name="dfu_fd_mid")
	private Long dfu_fd_mid;
	
	@Column(name="dfu_df_mid")
	private Long dfu_df_mid;
	
	@Column(name="dfu_file_name")
	private String dfu_file_name;
	
	@Column(name="dfu_rec_status")
	private Integer dfu_rec_status;
	
	
	@Column(name="dfu_uploaded_date")
	private Date dfu_uploaded_date;
	
	@Column(name="dfu_uploaded_by")
	private Long dfu_uploaded_by;

	public Long getDfu_id() {
		return dfu_id;
	}

	public void setDfu_id(Long dfu_id) {
		this.dfu_id = dfu_id;
	}

	public Long getDfu_fd_mid() {
		return dfu_fd_mid;
	}

	public void setDfu_fd_mid(Long dfu_fd_mid) {
		this.dfu_fd_mid = dfu_fd_mid;
	}

	public Long getDfu_df_mid() {
		return dfu_df_mid;
	}

	public void setDfu_df_mid(Long dfu_df_mid) {
		this.dfu_df_mid = dfu_df_mid;
	}

	public String getDfu_file_name() {
		return dfu_file_name;
	}

	public void setDfu_file_name(String dfu_file_name) {
		this.dfu_file_name = dfu_file_name;
	}

	public Integer getDfu_rec_status() {
		return dfu_rec_status;
	}

	public void setDfu_rec_status(Integer dfu_rec_status) {
		this.dfu_rec_status = dfu_rec_status;
	}

	public Date getDfu_uploaded_date() {
		return dfu_uploaded_date;
	}

	public void setDfu_uploaded_date(Date dfu_uploaded_date) {
		this.dfu_uploaded_date = dfu_uploaded_date;
	}

	public Long getDfu_uploaded_by() {
		return dfu_uploaded_by;
	}

	public void setDfu_uploaded_by(Long dfu_uploaded_by) {
		this.dfu_uploaded_by = dfu_uploaded_by;
	}
	
	
	
	
}
