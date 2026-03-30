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
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "court_master")
public class CourtMaster {

	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="court_master_seq")
	@SequenceGenerator(name="court_master_seq", sequenceName="court_master_seq", allocationSize=1)
	@Column(name = "cm_id")
	private Integer cm_id;
	
	@Column(name = "cm_name")
	private String cm_name;
	
	@Column(name = "cm_value")
	private Integer cm_value;
	
	@Column(name = "cm_rec_status")
	private Integer cm_rec_status;
	
	@Column(name = "cm_bench_id")
	private Integer cm_bench_id;
	
	/*@Column(name = "cm_bench2_id")
	private Integer cm_bench2_id;*/
	
	@Column(name = "cm_cr_by")
	private Long cm_cr_by;

	@Column(name = "cm_cr_date")
	private Date cm_cr_date;

	@Column(name = "cm_mod_by")
	private Long cm_mod_by;
	
	@Column(name = "cm_mod_date")
	private Date cm_mod_date;
	
	@Column(name = "cm_judges_name")
	private String cm_judges_name;
	
	/*@Column(name = "cm_judges_name2")
	private String cm_judges_name2;*/
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "sb_cm_mid")
	@Where(clause="sb_rec_status=1")
	private List<Sub_Benches> subBenches;
	
	
	
	@Transient
	private Sub_Benches subBeches;	
	
	
	
	
	public List<Sub_Benches> getSubBenches() {
		return subBenches;
	}

	public void setSubBenches(List<Sub_Benches> subBenches) {
		this.subBenches = subBenches;
	}

	public Sub_Benches getSubBeches() {
		return subBeches;
	}

	public void setSubBeches(Sub_Benches subBeches) {
		this.subBeches = subBeches;
	}

/*	public String getCm_judges_name2() {
		return cm_judges_name2;
	}

	public void setCm_judges_name2(String cm_judges_name2) {
		this.cm_judges_name2 = cm_judges_name2;
	}*/

	public String getCm_judges_name() {
		return cm_judges_name;
	}

	public void setCm_judges_name(String cm_judges_name) {
		this.cm_judges_name = cm_judges_name;
	}

	private transient boolean updateFlag =false;

	
	
	/*public Integer getCm_bench2_id() {
		return cm_bench2_id;
	}

	public void setCm_bench2_id(Integer cm_bench2_id) {
		this.cm_bench2_id = cm_bench2_id;
	}*/

	public boolean isUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(boolean updateFlag) {
		this.updateFlag = updateFlag;
	}

	public Integer getCm_bench_id() {
		return cm_bench_id;
	}

	public void setCm_bench_id(Integer cm_bench_id) {
		this.cm_bench_id = cm_bench_id;
	}

	public Integer getCm_id() {
		return cm_id;
	}

	public void setCm_id(Integer cm_id) {
		this.cm_id = cm_id;
	}

	public String getCm_name() {
		return cm_name;
	}

	public void setCm_name(String cm_name) {
		this.cm_name = cm_name;
	}

	public Integer getCm_value() {
		return cm_value;
	}

	public void setCm_value(Integer cm_value) {
		this.cm_value = cm_value;
	}

	public Integer getCm_rec_status() {
		return cm_rec_status;
	}

	public void setCm_rec_status(Integer cm_rec_status) {
		this.cm_rec_status = cm_rec_status;
	}

	public Long getCm_cr_by() {
		return cm_cr_by;
	}

	public void setCm_cr_by(Long cm_cr_by) {
		this.cm_cr_by = cm_cr_by;
	}

	public Date getCm_cr_date() {
		return cm_cr_date;
	}

	public void setCm_cr_date(Date cm_cr_date) {
		this.cm_cr_date = cm_cr_date;
	}

	public Long getCm_mod_by() {
		return cm_mod_by;
	}

	public void setCm_mod_by(Long cm_mod_by) {
		this.cm_mod_by = cm_mod_by;
	}

	public Date getCm_mod_date() {
		return cm_mod_date;
	}

	public void setCm_mod_date(Date cm_mod_date) {
		this.cm_mod_date = cm_mod_date;
	}

	@Override
	public String toString() {
		return "CourtMaster [cm_id=" + cm_id + ", cm_name=" + cm_name + ", cm_value=" + cm_value + ", cm_rec_status="
				+ cm_rec_status + ", cm_bench_id=" + cm_bench_id + ", cm_cr_by=" + cm_cr_by + ", cm_cr_date="
				+ cm_cr_date + ", cm_mod_by=" + cm_mod_by + ", cm_mod_date=" + cm_mod_date + "]";
	}
	
	
	
}
