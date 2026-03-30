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
@Table(name="subdocument_applicationa_status")
public class SubDocumentApplicationStatus {
	
	
	@Id 
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="subdocument_applicationa_status_seq")
	@SequenceGenerator(name="subdocument_applicationa_status_seq", sequenceName="subdocument_applicationa_status_seq", allocationSize=1)
	@Column(name = "sdas_id") 
	private Long sdas_id;
	

	@Column(name = "sdas_sd_id") 
	private Long sdas_sd_id;
	
	
	@Column(name = "sdas_fd_id") 
	private Long sdas_fd_id;
	
	@Column (name="sdas_date")
	private Date sdas_date;
	
	@Column (name = "sdas_mod_by")
	private Long sdas_mod_by;
	
	
	@Column (name = "sdas_status")
	private String sdas_status ;
	
	
	

	public Long getSdas_fd_id() {
		return sdas_fd_id;
	}

	public void setSdas_fd_id(Long sdas_fd_id) {
		this.sdas_fd_id = sdas_fd_id;
	}

	public String getSdas_status() {
		return sdas_status;
	}

	public void setSdas_status(String sdas_status) {
		this.sdas_status = sdas_status;
	}

	public Long getSdas_id() {
		return sdas_id;
	}

	public void setSdas_id(Long sdas_id) {
		this.sdas_id = sdas_id;
	}

	public Long getSdas_sd_id() {
		return sdas_sd_id;
	}

	public void setSdas_sd_id(Long sdas_sd_id) {
		this.sdas_sd_id = sdas_sd_id;
	}

	public Date getSdas_date() {
		return sdas_date;
	}

	public void setSdas_date(Date sdas_date) {
		this.sdas_date = sdas_date;
	}

	public Long getSdas_mod_by() {
		return sdas_mod_by;
	}

	public void setSdas_mod_by(Long sdas_mod_by) {
		this.sdas_mod_by = sdas_mod_by;
	}
	
	
	
	

}
