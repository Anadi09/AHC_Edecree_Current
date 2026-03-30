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
@Table(name = "hc_section")
public class HighCourtSection {
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="hc_section_hcs_id_seq")
	@SequenceGenerator(name="hc_section_hcs_id_seq", sequenceName="hc_section_hcs_id_seq", allocationSize=1)
	@Column(name = "hcs_id")
	private Long hcs_id;
	
	
	@Column(name ="hcs_section_id")
	private String hcs_section_id;
	

	@Column(name ="hcs_rec_status")
	private Integer hcs_rec_status;
	
	@Column(name ="hcs_court_no")
	private Long hcs_court_no;
	
	@Column(name ="hcs_name")
	private String hcs_name;
	
	@Column(name = "hcs_cr_by")
	private Long hcs_cr_by;

	@Column(name = "hcs_cr_date")
	private Date hcs_cr_date;
	
	@Column(name = "hcs_mod_by")
	private Long hcs_mod_by;
	
	@Column(name = "hcs_mod_date")
	private Date hcs_mod_date;

	public Long getHcs_id() {
		return hcs_id;
	}

	public void setHcs_id(Long hcs_id) {
		this.hcs_id = hcs_id;
	}

	public String getHcs_section_id() {
		return hcs_section_id;
	}

	public void setHcs_section_id(String hcs_section_id) {
		this.hcs_section_id = hcs_section_id;
	}

	public Integer getHcs_rec_status() {
		return hcs_rec_status;
	}

	public void setHcs_rec_status(Integer hcs_rec_status) {
		this.hcs_rec_status = hcs_rec_status;
	}

	

	public Long getHcs_court_no() {
		return hcs_court_no;
	}

	public void setHcs_court_no(Long hcs_court_no) {
		this.hcs_court_no = hcs_court_no;
	}

	public String getHcs_name() {
		return hcs_name;
	}

	public void setHcs_name(String hcs_name) {
		this.hcs_name = hcs_name;
	}

	public Long getHcs_cr_by() {
		return hcs_cr_by;
	}

	public void setHcs_cr_by(Long hcs_cr_by) {
		this.hcs_cr_by = hcs_cr_by;
	}

	public Date getHcs_cr_date() {
		return hcs_cr_date;
	}

	public void setHcs_cr_date(Date hcs_cr_date) {
		this.hcs_cr_date = hcs_cr_date;
	}

	
	public Long getHcs_mod_by() {
		return hcs_mod_by;
	}

	public void setHcs_mod_by(Long hcs_mod_by) {
		this.hcs_mod_by = hcs_mod_by;
	}

	public Date getHcs_mod_date() {
		return hcs_mod_date;
	}

	public void setHcs_mod_date(Date hcs_mod_date) {
		this.hcs_mod_date = hcs_mod_date;
	}

	@Override
	public String toString() {
		return "HighCourtSection [hcs_id=" + hcs_id + ", hcs_section_id=" + hcs_section_id + ", hcs_rec_status="
				+ hcs_rec_status + ", hcs_court_no=" + hcs_court_no + ", hcs_name=" + hcs_name + ", hcs_cr_by="
				+ hcs_cr_by + ", hcs_cr_date=" + hcs_cr_date + ", hcs_mod_by=" + hcs_mod_by + ", hcs_mod_date="
				+ hcs_mod_date + "]";
	}

	
	
	
	
	
	
	
	
	

}
