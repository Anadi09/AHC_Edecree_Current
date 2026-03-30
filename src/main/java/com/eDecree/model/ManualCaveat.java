package com.eDecree.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "manual_caveat")
public class ManualCaveat {

	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="manual_caveat_seq")
	@SequenceGenerator(name="manual_caveat_seq", sequenceName="manual_caveat_seq", allocationSize=1)
	@Column(name = "mcav_id")
	private Integer mcav_id;
	
	@Column(name = "mcav_document_name" , columnDefinition="TEXT")
	private String mcav_document_name;
	
	@Column(name = "mcav_no")
	private Integer mcav_no;
	
	@Column(name = "mcav_year")
	private Integer mcav_year;
	
	@Column(name = "cm_bench_id")
	private Integer cm_bench_id;
	
	@Column(name = "mcav_fd_mid")
	private Long mcav_fd_mid;

	@Column(name = "mcav_cr_date")
	private Date mcav_cr_date;
	
	@Column(name = "mcav_submitted_date")
	private Date mcav_submitted_date;
	
	@Column(name = "mcav_modify_date")
	private Date mcav_modify_date;
	
	@Column(name = "mcav_stage_lid")
	private Long mcav_stage_lid;
	
	@Transient
	private CaseFileDetail caseFileDetail;

	public Integer getMcav_id() {
		return mcav_id;
	}

	public void setMcav_id(Integer mcav_id) {
		this.mcav_id = mcav_id;
	}

	public CaseFileDetail getCaseFileDetail() {
		return caseFileDetail;
	}

	public void setCaseFileDetail(CaseFileDetail caseFileDetail) {
		this.caseFileDetail = caseFileDetail;
	}

	public String getMcav_document_name() {
		return mcav_document_name;
	}

	public void setMcav_document_name(String mcav_document_name) {
		this.mcav_document_name = mcav_document_name;
	}

	public Integer getMcav_no() {
		return mcav_no;
	}

	public void setMcav_no(Integer mcav_no) {
		this.mcav_no = mcav_no;
	}

	public Integer getMcav_year() {
		return mcav_year;
	}

	public void setMcav_year(Integer mcav_year) {
		this.mcav_year = mcav_year;
	}

	public Integer getCm_bench_id() {
		return cm_bench_id;
	}

	public void setCm_bench_id(Integer cm_bench_id) {
		this.cm_bench_id = cm_bench_id;
	}

	public Long getMcav_fd_mid() {
		return mcav_fd_mid;
	}

	public void setMcav_fd_mid(Long mcav_fd_mid) {
		this.mcav_fd_mid = mcav_fd_mid;
	}

	public Date getMcav_cr_date() {
		return mcav_cr_date;
	}

	public void setMcav_cr_date(Date mcav_cr_date) {
		this.mcav_cr_date = mcav_cr_date;
	}

	public Date getMcav_submitted_date() {
		return mcav_submitted_date;
	}

	public void setMcav_submitted_date(Date mcav_submitted_date) {
		this.mcav_submitted_date = mcav_submitted_date;
	}

	public Date getMcav_modify_date() {
		return mcav_modify_date;
	}

	public void setMcav_modify_date(Date mcav_modify_date) {
		this.mcav_modify_date = mcav_modify_date;
	}

	public Long getMcav_stage_lid() {
		return mcav_stage_lid;
	}

	public void setMcav_stage_lid(Long mcav_stage_lid) {
		this.mcav_stage_lid = mcav_stage_lid;
	}
	
	
	
	
}
