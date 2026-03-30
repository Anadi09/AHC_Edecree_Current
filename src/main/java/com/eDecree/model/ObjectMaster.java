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
@Table(name = "object_master")
public class ObjectMaster {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="object_master_seq")
	@SequenceGenerator(name="object_master_seq", sequenceName="object_master_seq", allocationSize=1)
	@Column(name = "om_id")
	private Long om_id;
	
	@Column(name = "om_object_name")
	private String om_object_name;
	
	@Column(name = "om_object_link")
	private String om_object_link;
	
	@Column(name = "om_rec_status")
	private Integer om_rec_status;
	
	@Column(name = "om_cr_by")
	private Long om_cr_by;

	@Column(name = "om_cr_date")
	private Date om_cr_date;
	
	@Column(name = "om_mod_by")
	private Long om_mod_by;
	
	@Column(name = "om_mod_date")
	private Date om_mod_date;
	
	@Column(name = "om_parent_id")
	private Long om_parent_id;
	
	@Column(name = "om_object_stages")
	private Long om_object_stages;
	
	public Long getOm_id() {
		return om_id;
	}

	public void setOm_id(Long om_id) {
		this.om_id = om_id;
	}

	public String getOm_object_name() {
		return om_object_name;
	}

	public void setOm_object_name(String om_object_name) {
		this.om_object_name = om_object_name;
	}

	public Integer getOm_rec_status() {
		return om_rec_status;
	}

	public void setOm_rec_status(Integer om_rec_status) {
		this.om_rec_status = om_rec_status;
	}

	public Long getOm_cr_by() {
		return om_cr_by;
	}

	public void setOm_cr_by(Long om_cr_by) {
		this.om_cr_by = om_cr_by;
	}

	public Date getOm_cr_date() {
		return om_cr_date;
	}

	public void setOm_cr_date(Date om_cr_date) {
		this.om_cr_date = om_cr_date;
	}

	public Long getOm_mod_by() {
		return om_mod_by;
	}

	public void setOm_mod_by(Long om_mod_by) {
		this.om_mod_by = om_mod_by;
	}

	public Date getOm_mod_date() {
		return om_mod_date;
	}

	public void setOm_mod_date(Date om_mod_date) {
		this.om_mod_date = om_mod_date;
	}

	public String getOm_object_link() {
		return om_object_link;
	}

	public void setOm_object_link(String om_object_link) {
		this.om_object_link = om_object_link;
	}

	public Long getOm_parent_id() {
		return om_parent_id;
	}

	public void setOm_parent_id(Long om_parent_id) {
		this.om_parent_id = om_parent_id;
	}

	public Long getOm_object_stages() {
		return om_object_stages;
	}

	public void setOm_object_stages(Long om_object_stages) {
		this.om_object_stages = om_object_stages;
	}
	
}
