package com.eDecree.model;

import java.math.BigInteger;
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
@Table(name = "sub_benches")
public class Sub_Benches {

	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="sub_bench_seq")
	@SequenceGenerator(name="sub_bench_seq", sequenceName="sub_bench_seq", allocationSize=1)
	@Column(name = "sb_id")
	private Long sb_id;
	
	@Column(name = "sb_cm_mid")
	private Integer sb_cm_mid;
	
	@Column(name = "sb_bench_id")
	private Integer sb_bench_id;
	
	@Column(name = "sb_judge_name")
	private String sb_judge_name;
	
	@Column(name = "sb_rec_status")
	private Integer sb_rec_status;
	
	/*@Column(name = "sb_list_date")*/
	@Transient
	private Date sb_list_date;
	
	public Date getSb_list_date() {
		return sb_list_date;
	}



	public void setSb_list_date(Date sb_list_date) {
		this.sb_list_date = sb_list_date;
	}

	private transient boolean updateFlag =false;

	public boolean isUpdateFlag() {
		return updateFlag;
	}
	
	

	public Integer getSb_rec_status() {
		return sb_rec_status;
	}



	public void setSb_rec_status(Integer sb_rec_status) {
		this.sb_rec_status = sb_rec_status;
	}



	public void setUpdateFlag(boolean updateFlag) {
		this.updateFlag = updateFlag;
	}

	public Long getSb_id() {
		return sb_id;
	}

	public void setSb_id(Long sb_id) {
		this.sb_id = sb_id;
	}

	public Integer getSb_cm_mid() {
		return sb_cm_mid;
	}

	public void setSb_cm_mid(Integer sb_cm_mid) {
		this.sb_cm_mid = sb_cm_mid;
	}

	public Integer getSb_bench_id() {
		return sb_bench_id;
	}

	public void setSb_bench_id(Integer sb_bench_id) {
		this.sb_bench_id = sb_bench_id;
	}

	public String getSb_judge_name() {
		return sb_judge_name;
	}

	public void setSb_judge_name(String sb_judge_name) {
		this.sb_judge_name = sb_judge_name;
	}
	
	
	
}
