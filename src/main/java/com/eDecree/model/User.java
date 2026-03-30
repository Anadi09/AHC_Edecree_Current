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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "user_master")
public class User {

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

	@Column(name="um_ipaddress")
	private String  um_ipaddress;
	
	@Column(name = "um_pass_validity_date")
	private Date um_pass_validity_date;
	
	@Column(name = "um_rec_status")
	private Integer um_rec_status;
	
	@Column(name = "um_department_id")
	private Long um_department_id;
	

	@Transient
	private  List<Long> um_ct_id;
	
	
	public List<Long> getUm_ct_id() {
		return um_ct_id;
	}

	public void setUm_ct_id(List<Long> um_ct_id) {
		this.um_ct_id = um_ct_id;
	}

	@Transient
	private String old_password;
	
	
	
	@Transient 
	private Long um_role_id;
	
	@Transient 
	private Integer court_id;
	
	@Transient
	private String confirmpassword;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, orphanRemoval=true)
	@JoinColumn(name = "ur_um_mid",referencedColumnName="um_id",insertable = false, updatable = false)	
	private List<UserRole> userroles;
		
	@Transient
	private CourtMaster courtMaster;

	
	
	

	

	public CourtMaster getCourtMaster() {
		return courtMaster;
	}

	public void setCourtMaster(CourtMaster courtMaster) {
		this.courtMaster = courtMaster;
	}

	public String getOld_password() {
		return old_password;
	}

	public void setOld_password(String old_password) {
		this.old_password = old_password;
	}

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


	public List<UserRole> getUserroles() {
		return userroles;
	}

	public void setUserroles(List<UserRole> userroles) {
		this.userroles = userroles;
	}

	public Long getUm_role_id() {
		return um_role_id;
	}

	public void setUm_role_id(Long um_role_id) {
		this.um_role_id = um_role_id;
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

	public Date getUm_pass_validity_date() {
		return um_pass_validity_date;
	}

	public void setUm_pass_validity_date(Date um_pass_validity_date) {
		this.um_pass_validity_date = um_pass_validity_date;
	}

	public Integer getUm_rec_status() {
		return um_rec_status;
	}

	public void setUm_rec_status(Integer um_rec_status) {
		this.um_rec_status = um_rec_status;
	}

	public Long getUm_department_id() {
		return um_department_id;
	}

	public void setUm_department_id(Long um_department_id) {
		this.um_department_id = um_department_id;
	}

	public Integer getCourt_id() {
		return court_id;
	}

	public void setCourt_id(Integer court_id) {
		this.court_id = court_id;
	}

	

	

	
	
	
	
	
}
