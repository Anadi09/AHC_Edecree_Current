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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="registered_case_stages")
public class CaseFileStage {
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="rcs_seq")
	@SequenceGenerator(name="rcs_seq", sequenceName="rcs_seq", allocationSize=1)
	@Column(name="rcs_id")
	private Long rcs_id;
	
	@Column(name="rcs_rcd_mid")
	private Long rcs_rcd_mid;
	 
	@Column(name="rcs_stage_lid")
	private Long rcs_stage_lid;
	
	@Column(name="rcs_cr_by")
	private Long rcs_cr_by;
	
	@Column(name="rcs_cr_date")
	private Date rcs_cr_date;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "rcs_stage_lid",insertable = false, updatable = false)
	private Lookup lkStage;

	public Long getRcs_id() {
		return rcs_id;
	}

	public void setRcs_id(Long rcs_id) {
		this.rcs_id = rcs_id;
	}

	public Long getRcs_rcd_mid() {
		return rcs_rcd_mid;
	}

	public void setRcs_rcd_mid(Long rcs_rcd_mid) {
		this.rcs_rcd_mid = rcs_rcd_mid;
	}

	public Long getRcs_stage_lid() {
		return rcs_stage_lid;
	}

	public void setRcs_stage_lid(Long rcs_stage_lid) {
		this.rcs_stage_lid = rcs_stage_lid;
	}

	public Long getRcs_cr_by() {
		return rcs_cr_by;
	}

	public void setRcs_cr_by(Long rcs_cr_by) {
		this.rcs_cr_by = rcs_cr_by;
	}

	public Date getRcs_cr_date() {
		return rcs_cr_date;
	}

	public void setRcs_cr_date(Date rcs_cr_date) {
		this.rcs_cr_date = rcs_cr_date;
	}

	public Lookup getLkStage() {
		return lkStage;
	}

	public void setLkStage(Lookup lkStage) {
		this.lkStage = lkStage;
	}
	
	
}
