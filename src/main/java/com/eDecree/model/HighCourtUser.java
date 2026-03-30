package com.eDecree.model;

import java.beans.Transient;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "hc_user")
public class HighCourtUser {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="hc_user_hcu_id_seq")
	@SequenceGenerator(name="hc_user_hcu_id_seq", sequenceName="hc_user_hcu_id_seq", allocationSize=1)
	@Column(name = "hcu_id")
	private Long hcu_id;
	
	
	@Column(name ="hcu_office_id")
	private String hcu_office_id;
	

	@Column(name ="hcu_senior_id")
	private Long hcu_senior_id;
	
	@Column(name ="hcu_username")
	private String hcu_username;
	
	
	
	
	
	
	@Column(name ="hcu_hcs_mid")
	private Long hcu_hcs_mid;
	
	@Column(name ="hcu_hcd_mid")
	private Long hcu_hcd_mid;
	
	@Column(name ="hc_um_mid")
	private Long hc_um_mid;
	
	@Column(name ="hcu_rec_status")
	private Integer hcu_rec_status;
	
	@Column(name ="hcu_approved_status")
	private String hcu_approved_status;
	
	
	@Column(name ="hcu_contact_number")
	private String hcu_contact_number;
	
	@Column(name ="hcu_address")
	private String hcu_address;
	
	@Column(name = "hcu_cr_by")
	private Long hcu_cr_by;
	
	@Column (name ="hcu_gender")
	private String hcu_gender;
	
	@Column(name ="hcu_email")
	private String hcu_email;

	@Column(name = "hcu_cr_date")
	private Date hcu_cr_date;
	
	@Column(name = "hcu_mod_by")
	private Long hcu_mod_by;
	
	@Column(name = "hcu_mod_date")
	private Date hcu_mod_date;
	
	@Column(name ="hcu_password")
	private String hcu_password;
	
	
	@javax.persistence.Transient
	private String hcu_confirm_password;
	
	@javax.persistence.Transient
	private String type;

	
	
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hcu_hcs_mid",insertable = false, updatable = false)
	private HighCourtSection hcs;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hcu_hcd_mid",insertable = false, updatable = false)
	private HighCourtDesignation hcd;
	
	
	
	

	public HighCourtSection getHcs() {
		return hcs;
	}

	public void setHcs(HighCourtSection hcs) {
		this.hcs = hcs;
	}

	public HighCourtDesignation getHcd() {
		return hcd;
	}

	public void setHcd(HighCourtDesignation hcd) {
		this.hcd = hcd;
	}

	public Long getHc_um_mid() {
		return hc_um_mid;
	}

	public void setHc_um_mid(Long hc_um_mid) {
		this.hc_um_mid = hc_um_mid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHcu_password() {
		return hcu_password;
	}

	public void setHcu_password(String hcu_password) {
		this.hcu_password = hcu_password;
	}

	public String getHcu_confirm_password() {
		return hcu_confirm_password;
	}

	public void setHcu_confirm_password(String hcu_confirm_password) {
		this.hcu_confirm_password = hcu_confirm_password;
	}

	public String getHcu_gender() {
		return hcu_gender;
	}

	public void setHcu_gender(String hcu_gender) {
		this.hcu_gender = hcu_gender;
	}

	public String getHcu_email() {
		return hcu_email;
	}

	public void setHcu_email(String hcu_email) {
		this.hcu_email = hcu_email;
	}

	public Long getHcu_id() {
		return hcu_id;
	}

	public void setHcu_id(Long hcu_id) {
		this.hcu_id = hcu_id;
	}

	
	public String getHcu_office_id() {
		return hcu_office_id;
	}

	public void setHcu_office_id(String hcu_office_id) {
		this.hcu_office_id = hcu_office_id;
	}

	public Long getHcu_senior_id() {
		return hcu_senior_id;
	}

	public void setHcu_senior_id(Long hcu_senior_id) {
		this.hcu_senior_id = hcu_senior_id;
	}

	public String getHcu_username() {
		return hcu_username;
	}

	public void setHcu_username(String hcu_username) {
		this.hcu_username = hcu_username;
	}

	public Long getHcu_hcs_mid() {
		return hcu_hcs_mid;
	}

	public void setHcu_hcs_mid(Long hcu_hcs_mid) {
		this.hcu_hcs_mid = hcu_hcs_mid;
	}

	public Long getHcu_hcd_mid() {
		return hcu_hcd_mid;
	}

	public void setHcu_hcd_mid(Long hcu_hcd_mid) {
		this.hcu_hcd_mid = hcu_hcd_mid;
	}

	public Integer getHcu_rec_status() {
		return hcu_rec_status;
	}

	public void setHcu_rec_status(Integer hcu_rec_status) {
		this.hcu_rec_status = hcu_rec_status;
	}

	public String getHcu_approved_status() {
		return hcu_approved_status;
	}

	public void setHcu_approved_status(String hcu_approved_status) {
		this.hcu_approved_status = hcu_approved_status;
	}

	public String getHcu_contact_number() {
		return hcu_contact_number;
	}

	public void setHcu_contact_number(String hcu_contact_number) {
		this.hcu_contact_number = hcu_contact_number;
	}

	public String getHcu_address() {
		return hcu_address;
	}

	public void setHcu_address(String hcu_address) {
		this.hcu_address = hcu_address;
	}

	public Long getHcu_cr_by() {
		return hcu_cr_by;
	}

	public void setHcu_cr_by(Long hcu_cr_by) {
		this.hcu_cr_by = hcu_cr_by;
	}

	public Date getHcu_cr_date() {
		return hcu_cr_date;
	}

	public void setHcu_cr_date(Date hcu_cr_date) {
		this.hcu_cr_date = hcu_cr_date;
	}

	public Long getHcu_mod_by() {
		return hcu_mod_by;
	}

	public void setHcu_mod_by(Long hcu_mod_by) {
		this.hcu_mod_by = hcu_mod_by;
	}

	public Date getHcu_mod_date() {
		return hcu_mod_date;
	}

	public void setHcu_mod_date(Date hcu_mod_date) {
		this.hcu_mod_date = hcu_mod_date;
	}

	@Override
	public String toString() {
		return "HighCourtUser [hcu_id=" + hcu_id + ", hcu_office_id=" + hcu_office_id + ", hcu_senior_id="
				+ hcu_senior_id + ", hcu_username=" + hcu_username + ", hcu_hcs_mid=" + hcu_hcs_mid + ", hcu_hcd_mid="
				+ hcu_hcd_mid + ", hc_um_mid=" + hc_um_mid + ", hcu_rec_status=" + hcu_rec_status
				+ ", hcu_approved_status=" + hcu_approved_status + ", hcu_contact_number=" + hcu_contact_number
				+ ", hcu_address=" + hcu_address + ", hcu_cr_by=" + hcu_cr_by + ", hcu_gender=" + hcu_gender
				+ ", hcu_email=" + hcu_email + ", hcu_cr_date=" + hcu_cr_date + ", hcu_mod_by=" + hcu_mod_by
				+ ", hcu_mod_date=" + hcu_mod_date + ", hcu_password=" + hcu_password + ", hcu_confirm_password="
				+ hcu_confirm_password + ", type=" + type + ", hcs=" + hcs + ", hcd=" + hcd + "]";
	}

	

	

	

	

	 

	
	
	
	

}
