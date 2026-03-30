package com.eDecree.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Controller;

@Entity
@Table(name="login_log")

public class LoginLog {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="login_logseq")
	@SequenceGenerator(name="login_logseq", sequenceName="login_logseq", allocationSize=1)
	@Column(name = "ll_id")
	private Long ll_id;
	
	@Column(name="ll_user_mid")
	private Long ll_user_mid;
	
	@Column(name="ll_login_time")
	private Date ll_login_time;
	
	@Column(name="ll_logout_time")
	private Date ll_logout_time;
	
	@Column(name="ll_ip_address")
	private String ll_ip_address;

	public Long getLl_id() {
		return ll_id;
	}

	public void setLl_id(Long ll_id) {
		this.ll_id = ll_id;
	}

	public Long getLl_user_mid() {
		return ll_user_mid;
	}

	public void setLl_user_mid(Long ll_user_mid) {
		this.ll_user_mid = ll_user_mid;
	}

	public Date getLl_login_time() {
		return ll_login_time;
	}

	public void setLl_login_time(Date ll_login_time) {
		this.ll_login_time = ll_login_time;
	}

	public Date getLl_logout_time() {
		return ll_logout_time;
	}

	public void setLl_logout_time(Date ll_logout_time) {
		this.ll_logout_time = ll_logout_time;
	}

	public String getLl_ip_address() {
		return ll_ip_address;
	}

	public void setLl_ip_address(String ll_ip_address) {
		this.ll_ip_address = ll_ip_address;
	}
	
	
}
