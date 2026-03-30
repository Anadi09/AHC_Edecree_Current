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
@Table(name = "hc_designation")
public class HighCourtDesignation {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="hc_designation_hcd_id_seq")
	@SequenceGenerator(name="hc_designation_hcd_id_seq", sequenceName="hc_designation_hcd_id_seq", allocationSize=1)
	@Column(name = "hcd_id")
	private Long hcd_id;
	
	
	@Column(name ="hcd_name")
	private String hcd_name;
	


	@Column(name ="hcd_level")
	private Long hcd_level;

	
	
	@Column(name ="hcd_rec_status")
	private Integer hcd_rec_status;
	

	public Long getHcd_id() {
		return hcd_id;
	}

	public void setHcd_id(Long hcd_id) {
		this.hcd_id = hcd_id;
	}

	public String getHcd_name() {
		return hcd_name;
	}

	public void setHcd_name(String hcd_name) {
		this.hcd_name = hcd_name;
	}

	public Long getHcd_level() {
		return hcd_level;
	}

	public void setHcd_level(Long hcd_level) {
		this.hcd_level = hcd_level;
	}

	public Integer getHcd_rec_status() {
		return hcd_rec_status;
	}

	public void setHcd_rec_status(Integer hcd_rec_status) {
		this.hcd_rec_status = hcd_rec_status;
	}

	@Override
	public String toString() {
		return "HighCourtDesignation [hcd_id=" + hcd_id + ", hcd_name=" + hcd_name + ", hcd_level=" + hcd_level
				+ ", hcd_rec_status=" + hcd_rec_status + "]";
	}

	

	
	
	

}
