package com.eDecree.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "judge")
public class Judge {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "judgeseq")
	@SequenceGenerator(name = "judgeseq", sequenceName = "judgeseq", allocationSize = 1)
	@Column(name = "jg_id")
	private Long jg_id;	

	@Column(name = "jg_name")
	private String jg_name;

	@Column(name = "jg_code")
	private String jg_code;

	@Column(name = "jg_type")
	private Long jg_type;

	@Column(name = "jg_rec_status")
	private Long jg_rec_status;

	@Column(name = "jg_cr_by")
	private Long cr_by;

	@Column(name = "jg_cr_date")
	private Date cr_date;

	@Column(name = "jg_mod_by")
	private Long mod_by;

	@Column(name = "jg_mod_date")
	private Date mod_date;

	@Column(name = "jg_bench_code")
	private Long jg_bench_code;

	@ManyToOne
    @JoinColumn(name="jg_type",referencedColumnName="lk_id",insertable = false, updatable = false)
    private Lookup lk_jgtype;
	
	@Column(name = "jg_joining_year")
	private Long jg_joining_year;
	
	@Column(name = "jg_retirement_year")
	private Long jg_retirement_year;
	
	public Lookup getLk_jgtype() {
		return lk_jgtype;
	}

	public void setLk_jgtype(Lookup lk_jgtype) {
		this.lk_jgtype = lk_jgtype;
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

	@Override
	public String toString() {
		return "Judge [jg_id=" + jg_id + ",  jg_name=" + jg_name + ", jg_code=" + jg_code
				+ ", jg_type=" + jg_type + ", jg_rec_status=" + jg_rec_status + ", cr_by=" + cr_by + ", cr_date="
				+ cr_date + ", mod_by=" + mod_by + ", mod_date=" + mod_date + ", jg_bench_code=" + jg_bench_code
				+ ", lk_jgtype=" + lk_jgtype + ", jg_joining_year=" + jg_joining_year + ", jg_retirement_year="
				+ jg_retirement_year + "]";
	}
	
	
}
