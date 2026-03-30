package com.efiling.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "lookup")
public class EfilingLookup {

	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="lookupseq")
	@SequenceGenerator(name="lookupseq", sequenceName="lookupseq", allocationSize=1)
	@Column(name = "lk_id")
	private Long lk_id;
	
	@Column(name = "lk_longname")
	private String lk_longname;
	
	@Column(name = "lk_setname")
	private String lk_setname;
	
	@Column(name = "lk_value")
	private String lk_value;
	
	@Column(name = "lk_parent")
	private Long lk_parent;
	
	@Column(name="lk_serial_no")
	private Long lk_serial_no;
	
	@Column(name = "lk_cr_by")
	private Long cr_by;

	@Column(name = "lk_cr_date")
	private Date cr_date;

	@Column(name = "lk_mod_by")
	private Long mod_by;
	
	@Column(name = "lk_mod_date")
	private Date mod_date;
	
	@Column(name = "lk_rec_status")
	private Integer lk_rec_status;
	
	@Column(name = "lk_priority")
	private Integer lk_priority;
	

	public Long getLk_serial_no() {
		return lk_serial_no;
	}

	public void setLk_serial_no(Long lk_serial_no) {
		this.lk_serial_no = lk_serial_no;
	}

	public Long getCr_by() {
		return cr_by;
	}

	public void setCr_by(Long cr_by) {
		this.cr_by = cr_by;
	}

	public Date getCr_date() {
		return cr_date;
	}

	public void setCr_date(Date cr_date) {
		this.cr_date = cr_date;
	}

	public Long getMod_by() {
		return mod_by;
	}

	public void setMod_by(Long mod_by) {
		this.mod_by = mod_by;
	}

	public Date getMod_date() {
		return mod_date;
	}

	public void setMod_date(Date mod_date) {
		this.mod_date = mod_date;
	}

	
	public Long getLk_id() {
		return lk_id;
	}

	public void setLk_id(Long lk_id) {
		this.lk_id = lk_id;
	}

	public String getLk_longname() {
		return lk_longname;
	}

	public void setLk_longname(String lk_longname) {
		this.lk_longname = lk_longname;
	}

	public String getLk_setname() {
		return lk_setname;
	}

	public void setLk_setname(String lk_setname) {
		this.lk_setname = lk_setname;
	}

	public String getLk_value() {
		return lk_value;
	}

	public void setLk_value(String lk_value) {
		this.lk_value = lk_value;
	}

	public Long getLk_parent() {
		return lk_parent;
	}

	public void setLk_parent(Long lk_parent) {
		this.lk_parent = lk_parent;
	}

	public Integer getLk_rec_status() {
		return lk_rec_status;
	}

	public void setLk_rec_status(Integer lk_rec_status) {
		this.lk_rec_status = lk_rec_status;
	}

	public Integer getLk_priority() {
		return lk_priority;
	}

	public void setLk_priority(Integer lk_priority) {
		this.lk_priority = lk_priority;
	}
	
	
}
