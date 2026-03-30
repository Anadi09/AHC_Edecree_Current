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
@Table(name="decree_stage")
public class DecreeStage {
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="decree_stage_seq")
	@SequenceGenerator(name="decree_stage_seq", sequenceName="decree_stage_seq", allocationSize=1)
	@Column(name="ds_id")
	private Long ds_id;
	
	@Column(name="ds_df_mid")
	private Long ds_df_mid;
	
	@Column(name="ds_cr_by")
	private Long ds_cr_by;
	
	
	@Column(name="ds_stage_lid")
	private Long ds_stage_lid;
	
	

	@Column(name="ds_cr_date")
	private Date ds_cr_date;
	

	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ds_cr_by",insertable = false, updatable = false)
	private User crBy;
	
	@Column(name="ds_rec_status")
	private Integer ds_rec_status;

	
	
	
	public Integer getDs_rec_status() {
		return ds_rec_status;
	}



	public void setDs_rec_status(Integer ds_rec_status) {
		this.ds_rec_status = ds_rec_status;
	}



	public User getCrBy() {
		return crBy;
	}



	public void setCrBy(User crBy) {
		this.crBy = crBy;
	}



	public Long getDs_cr_by() {
		return ds_cr_by;
	}



	public void setDs_cr_by(Long ds_cr_by) {
		this.ds_cr_by = ds_cr_by;
	}



	public Long getDs_id() {
		return ds_id;
	}



	public void setDs_id(Long ds_id) {
		this.ds_id = ds_id;
	}



	public Long getDs_df_mid() {
		return ds_df_mid;
	}



	public void setDs_df_mid(Long ds_df_mid) {
		this.ds_df_mid = ds_df_mid;
	}



	



	public Long getDs_stage_lid() {
		return ds_stage_lid;
	}



	public void setDs_stage_lid(Long ds_stage_lid) {
		this.ds_stage_lid = ds_stage_lid;
	}



	public Date getDs_cr_date() {
		return ds_cr_date;
	}



	public void setDs_cr_date(Date ds_cr_date) {
		this.ds_cr_date = ds_cr_date;
	}
	
	

}
