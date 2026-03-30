package com.eDecree.model;

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
@Table(name = "court_user_mapping")
public class CourtUserMapping 
{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "court_user_mapping_seq")
	@SequenceGenerator(name = "court_user_mapping_seq", sequenceName = "court_user_mapping_seq", allocationSize = 1)
	@Column(name = "cum_id")
	private Long cum_id;

	@Column(name = "cum_court_mid")
	private Integer cum_court_mid;

	@Column(name = "cum_user_mid")
	private Long cum_user_mid;
	
	@Column(name = "cum_jg_mid")
	private Long cum_jg_mid;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cum_court_mid",insertable = false, updatable = false)
	private CourtMaster courtMaster;
		
	public Long getCum_id() {
		return cum_id;
	}

	public void setCum_id(Long cum_id) {
		this.cum_id = cum_id;
	}

	public Integer getCum_court_mid() {
		return cum_court_mid;
	}

	public void setCum_court_mid(Integer cum_court_mid) {
		this.cum_court_mid = cum_court_mid;
	}

	public Long getCum_user_mid() {
		return cum_user_mid;
	}

	public void setCum_user_mid(Long cum_user_mid) {
		this.cum_user_mid = cum_user_mid;
	}

	public CourtMaster getCourtMaster() {
		return courtMaster;
	}

	public void setCourtMaster(CourtMaster courtMaster) {
		this.courtMaster = courtMaster;
	}

	public Long getCum_jg_mid() {
		return cum_jg_mid;
	}

	public void setCum_jg_mid(Long cum_jg_mid) {
		this.cum_jg_mid = cum_jg_mid;
	}
	

}
