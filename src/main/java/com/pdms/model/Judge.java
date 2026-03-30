package com.pdms.model;

import java.util.Date;


public class Judge {

	private Long jg_id;

	private String jg_name;

	private String jg_code;

	private Long jg_type;

	private Long jg_rec_status;

	private Long cr_by;

	private Date cr_date;

	private Long mod_by;

	private Date mod_date;

	private Long jg_bench_code;

	private Long jg_joining_year;

	private Long jg_retirement_year;

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

	public Long getJg_bench_code() {
		return jg_bench_code;
	}

	public void setJg_bench_code(Long jg_bench_code) {
		this.jg_bench_code = jg_bench_code;
	}

	public Long getJg_id() {
		return jg_id;
	}

	public void setJg_id(Long jg_id) {
		this.jg_id = jg_id;
	}

	public String getJg_name() {
		return jg_name;
	}

	public void setJg_name(String jg_name) {
		this.jg_name = jg_name;
	}

	public Long getJg_type() {
		return jg_type;
	}

	public void setJg_type(Long jg_type) {
		this.jg_type = jg_type;
	}

	public Long getJg_rec_status() {
		return jg_rec_status;
	}

	public void setJg_rec_status(Long jg_rec_status) {
		this.jg_rec_status = jg_rec_status;
	}

	public String getJg_code() {
		return jg_code;
	}

	public void setJg_code(String jg_code) {
		this.jg_code = jg_code;
	}

	public Long getJg_joining_year() {
		return jg_joining_year;
	}

	public void setJg_joining_year(Long jg_joining_year) {
		this.jg_joining_year = jg_joining_year;
	}

	public Long getJg_retirement_year() {
		return jg_retirement_year;
	}

	public void setJg_retirement_year(Long jg_retirement_year) {
		this.jg_retirement_year = jg_retirement_year;
	}
}
