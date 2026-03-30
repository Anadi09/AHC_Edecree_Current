package com.eDecree.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_casetype_mapping")
public class UserCaseType {
	
	@Id
	@Column(name="ucm_id")
	private Long ucm_id;
	
	@Column(name="ucm_um_mid")
	private  Long ucm_um_mid;
	
	@Column(name="ucm_ct_mid")
	private Long ucm_ct_mid;

	public Long getUcm_id() {
		return ucm_id;
	}

	public void setUcm_id(Long ucm_id) {
		this.ucm_id = ucm_id;
	}

	public Long getUcm_um_mid() {
		return ucm_um_mid;
	}

	public void setUcm_um_mid(Long ucm_um_mid) {
		this.ucm_um_mid = ucm_um_mid;
	}

	public Long getUcm_ct_mid() {
		return ucm_ct_mid;
	}

	public void setUcm_ct_mid(Long ucm_ct_mid) {
		this.ucm_ct_mid = ucm_ct_mid;
	}
	
	

}
