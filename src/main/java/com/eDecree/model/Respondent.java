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

import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
@Entity
@Table(name="respondent_details")
public class Respondent {

	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE,generator="respondentdetails_seq")
	@SequenceGenerator(name="respondentdetails_seq", sequenceName="respondentdetails_seq", allocationSize=1)
	@Column(name = "rt_id")
	private Long rt_id;
	
	@Column(name="rt_fd_mid")
	private Long rt_fd_mid;

	@Column(name = "rt_name")
	private String rt_name;
		
	@Column(name = "rt_email")
	private String rt_email;
	
	@Column(name = "rt_mobile")
	private Long  rt_mobile;
	
	@Column(name = "rt_address")
	private String rt_address;
	
	@Column(name = "rt_pincode")
	private Long  rt_pincode;
	
	@Column(name = "rt_other_contact")
	private String rt_other_contact;
	
	@Column(name="rt_cr_by")
	private Long rt_cr_by;
	
	@Column(name="rt_cr_date")
	private Date rt_cr_date;	
	
	@Column(name="rt_mod_by")
	private Long rt_mod_by;	
	
	@Column(name="rt_mod_date")
	private Date rt_mod_date;
	
	@Column(name = "rt_rec_status")
	private Integer rt_rec_status;
	
	@Column(name = "rt_sequence")
	private Integer rt_sequence;

	@Column(name="rt_s_mid")
	private Long rt_s_mid;	
	
	@Column(name="rt_city")
	private String rt_city;
	
	
	/*@ManyToOne
	@JoinColumn(name="rt_rcd_mid",referencedColumnName="rcd_id",insertable=false,updatable=false)
	private RegisteredCaseDetails registeredCase;
	*/
	
	
	
	
	
	
	
	
	
	public Long getRt_id() {
		return rt_id;
	}

	public void setRt_id(Long rt_id) {
		this.rt_id = rt_id;
	}

	public String getRt_name() {
		return rt_name;
	}

	public void setRt_name(String rt_name) {
		this.rt_name = rt_name;
	}

	public String getRt_email() {
		return rt_email;
	}

	public void setRt_email(String rt_email) {
		this.rt_email = rt_email;
	}

	public Long getRt_mobile() {
		return rt_mobile;
	}

	public void setRt_mobile(Long rt_mobile) {
		this.rt_mobile = rt_mobile;
	}

	public String getRt_address() {
		return rt_address;
	}

	public void setRt_address(String rt_address) {
		this.rt_address = rt_address;
	}

	public Long getRt_pincode() {
		return rt_pincode;
	}

	public void setRt_pincode(Long rt_pincode) {
		this.rt_pincode = rt_pincode;
	}

	public String getRt_other_contact() {
		return rt_other_contact;
	}

	public void setRt_other_contact(String rt_other_contact) {
		this.rt_other_contact = rt_other_contact;
	}

	public Long getRt_cr_by() {
		return rt_cr_by;
	}

	public void setRt_cr_by(Long rt_cr_by) {
		this.rt_cr_by = rt_cr_by;
	}

	public Date getRt_cr_date() {
		return rt_cr_date;
	}

	public void setRt_cr_date(Date rt_cr_date) {
		this.rt_cr_date = rt_cr_date;
	}

	public Long getRt_mod_by() {
		return rt_mod_by;
	}

	public void setRt_mod_by(Long rt_mod_by) {
		this.rt_mod_by = rt_mod_by;
	}

	public Date getRt_mod_date() {
		return rt_mod_date;
	}

	public void setRt_mod_date(Date rt_mod_date) {
		this.rt_mod_date = rt_mod_date;
	}

	public Integer getRt_rec_status() {
		return rt_rec_status;
	}

	public void setRt_rec_status(Integer rt_rec_status) {
		this.rt_rec_status = rt_rec_status;
	}



	
	
	

	
	
	
	public Integer getRt_sequence() {
		return rt_sequence;
	}

	public void setRt_sequence(Integer rt_sequence) {
		this.rt_sequence = rt_sequence;
	}

	/*public RegisteredCaseDetails getRegisteredCase() {
		return registeredCase;
	}

	public void setRegisteredCase(RegisteredCaseDetails registeredCase) {
		this.registeredCase = registeredCase;
	}
	*/
	public Long getRt_fd_mid() {
		return rt_fd_mid;
	}

	public void setRt_fd_mid(Long rt_fd_mid) {
		this.rt_fd_mid = rt_fd_mid;
	}

	public Long getRt_s_mid() {
		return rt_s_mid;
	}

	public void setRt_s_mid(Long rt_s_mid) {
		this.rt_s_mid = rt_s_mid;
	}

	public String getRt_city() {
		return rt_city;
	}

	public void setRt_city(String rt_city) {
		this.rt_city = rt_city;
	}

	
	
	
	
	
}
