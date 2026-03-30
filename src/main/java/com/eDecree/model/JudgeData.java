package com.eDecree.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="judge_data")
public class JudgeData {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jd_seq")
	@SequenceGenerator(name = "jd_seq", sequenceName = "jd_seq", allocationSize = 1)
	@Column(name = "jd_id")
	private Long jd_id;

	@Column(name = "jd_name")
	private String jd_name;
	
	@Column(name = "jd_jg_mid")
	private Long jd_jg_mid;

	@Column(name = "jd_rec_status")
	private Integer jd_rec_status;

	@Column(name = "jd_fd_mid")
	private Long jd_fd_mid;
	
	@Column(name = "jd_sequence")
	private Integer jd_sequence;

	public Long getJd_id() {
		return jd_id;
	}

	public void setJd_id(Long jd_id) {
		this.jd_id = jd_id;
	}

	public String getJd_name() {
		return jd_name;
	}

	public void setJd_name(String jd_name) {
		this.jd_name = jd_name;
	}

	public Integer getJd_rec_status() {
		return jd_rec_status;
	}

	public void setJd_rec_status(Integer jd_rec_status) {
		this.jd_rec_status = jd_rec_status;
	}

	public Long getJd_fd_mid() {
		return jd_fd_mid;
	}

	public void setJd_fd_mid(Long jd_fd_mid) {
		this.jd_fd_mid = jd_fd_mid;
	}

	public Integer getJd_sequence() {
		return jd_sequence;
	}

	public void setJd_sequence(Integer jd_sequence) {
		this.jd_sequence = jd_sequence;
	}

	public Long getJd_jg_mid() {
		return jd_jg_mid;
	}

	public void setJd_jg_mid(Long jd_jg_mid) {
		this.jd_jg_mid = jd_jg_mid;
	}
	
	
	
	
}
