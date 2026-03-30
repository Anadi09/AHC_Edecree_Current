package com.efiling.model;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "user_master")
public class EfilingUser {

	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="user_master_seq")
	@SequenceGenerator(name="user_master_seq", sequenceName="user_master_seq", allocationSize=1)
	@Column(name = "um_id")
	private Long um_id;
	
	@Column(name = "um_username")
	private String username;
	
	@Column(name = "um_password")
	private String password;
	
	@Column(name = "um_last_login")
	private Date last_login;
	
	@Column(name = "um_cr_by")
	private Long cr_by;

	@Column(name = "um_cr_date")
	private Date cr_date;
	
	@Column(name = "um_mod_by")
	private Long mod_by;
	
	@Column(name = "um_mod_date")
	private Date mod_date;
	
	@Column(name = "um_fullname")
	private String um_fullname;

	@Column(name="um_bench_code")
	private Long  um_bench_code ;
	
	@Column(name="um_ipaddress")
	private String  um_ipaddress;
	
	@Column(name="um_email")
	private String  um_email;
	
	@Column(name="um_mobile")
	private String  um_mobile;
	
	@Column(name="um_gender")
	private String  um_gender;
	
	@Column(name="um_otp")
	private Integer  um_otp;
		
	@Transient 
	private Long um_role_id;
	
	@Transient
	private String confirmpassword;
	
	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	@Transient
	private String newpassword;
	

	public Long getUm_id() {
		return um_id;
	}

	public void setUm_id(Long um_id) {
		this.um_id = um_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
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
	
	public String getUm_fullname() {
		return um_fullname;
	}

	public void setUm_fullname(String um_fullname) {
		this.um_fullname = um_fullname;
	}


	public Long getUm_role_id() {
		return um_role_id;
	}

	public void setUm_role_id(Long um_role_id) {
		this.um_role_id = um_role_id;
	}

	public Long getUm_bench_code() {
		return um_bench_code;
	}

	public void setUm_bench_code(Long um_bench_code) {
		this.um_bench_code = um_bench_code;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public String getUm_ipaddress() {
		return um_ipaddress;
	}

	public void setUm_ipaddress(String um_ipaddress) {
		this.um_ipaddress = um_ipaddress;
	}

	public String getUm_email() {
		return um_email;
	}

	public void setUm_email(String um_email) {
		this.um_email = um_email;
	}

	public String getUm_mobile() {
		return um_mobile;
	}

	public void setUm_mobile(String um_mobile) {
		this.um_mobile = um_mobile;
	}

	public String getUm_gender() {
		return um_gender;
	}

	public void setUm_gender(String um_gender) {
		this.um_gender = um_gender;
	}

	public Integer getUm_otp() {
		return um_otp;
	}

	public void setUm_otp(Integer um_otp) {
		this.um_otp = um_otp;
	}
	

}
