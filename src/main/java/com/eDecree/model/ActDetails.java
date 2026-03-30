package com.eDecree.model;

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
@Table(name="act_details")
public class ActDetails {
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE,generator="actdetails_seq")
	@SequenceGenerator(name="actdetails_seq", sequenceName="actdetails_seq", allocationSize=1)
	  @Column(name = "act_id")
	   private Long  act_id;
	   
	  @Column(name = "act_code")
	  private Long act_code;
	  
	  @Column(name="act_title")
	  private String act_title;
	
	  @Column(name = "act_section")
	  private String act_section;
	  
	  @Column(name = "act_rule_no")
	  private String act_rule_no;
	  
	  @Column(name = "act_rule")
	  private String act_rule;
	  
	  @Column(name = "act_other")
	  private String act_other;
	  
	  @Column(name = "act_s_id")
	  private Long act_s_id;
	  
	  @Column(name = "act_cr_by")
	  private Long act_cr_by;
	  
	  
	  @Column(name = "act_cr_date")
	  private Date act_cr_date;
	  
	  @Column(name = "act_mod_by")
	  private Long act_mod_by;
	  
	  @Column(name = "act_mod_date")
	  private Date act_mod_date;
	  
	  @Column(name = "act_rec_status")
	 private  Integer act_rec_status;

	  @Column(name="act_type")
	  private String act_type;
	  
	  
	  @Column(name="act_rcd_mid")
	  private Long act_rcd_mid;
	  
	  @OneToOne(cascade = CascadeType.PERSIST)
	  @JoinColumn(name = "act_code",insertable = false, updatable = false)
	  private ActMaster actMaster;
	  
	  
	public Long getAct_id() {
		return act_id;
	}

	public void setAct_id(Long act_id) {
		this.act_id = act_id;
	}
	public String getAct_section() {
		return act_section;
	}

	public void setAct_section(String act_section) {
		this.act_section = act_section;
	}

	public String getAct_rule_no() {
		return act_rule_no;
	}

	public void setAct_rule_no(String act_rule_no) {
		this.act_rule_no = act_rule_no;
	}

	public String getAct_rule() {
		return act_rule;
	}

	public void setAct_rule(String act_rule) {
		this.act_rule = act_rule;
	}

	public String getAct_other() {
		return act_other;
	}

	public void setAct_other(String act_other) {
		this.act_other = act_other;
	}

	public Long getAct_s_id() {
		return act_s_id;
	}

	public void setAct_s_id(Long act_s_id) {
		this.act_s_id = act_s_id;
	}

	public Long getAct_cr_by() {
		return act_cr_by;
	}

	public void setAct_cr_by(Long act_cr_by) {
		this.act_cr_by = act_cr_by;
	}

	public Date getAct_cr_date() {
		return act_cr_date;
	}

	public void setAct_cr_date(Date act_cr_date) {
		this.act_cr_date = act_cr_date;
	}

	public Long getAct_mod_by() {
		return act_mod_by;
	}

	public void setAct_mod_by(Long act_mod_by) {
		this.act_mod_by = act_mod_by;
	}

	public Date getAct_mod_date() {
		return act_mod_date;
	}

	public void setAct_mod_date(Date act_mod_date) {
		this.act_mod_date = act_mod_date;
	}

	public Integer getAct_rec_status() {
		return act_rec_status;
	}

	public void setAct_rec_status(Integer act_rec_status) {
		this.act_rec_status = act_rec_status;
	}

	public String getAct_type() {
		return act_type;
	}

	public void setAct_type(String act_type) {
		this.act_type = act_type;
	}

	public String getAct_title() {
		return act_title;
	}

	public void setAct_title(String act_title) {
		this.act_title = act_title;
	}

	public Long getAct_rcd_mid() {
		return act_rcd_mid;
	}

	public void setAct_rcd_mid(Long act_rcd_mid) {
		this.act_rcd_mid = act_rcd_mid;
	}

	public Long getAct_code() {
		return act_code;
	}

	public void setAct_code(Long act_code) {
		this.act_code = act_code;
	}

	public ActMaster getActMaster() {
		return actMaster;
	}

	public void setActMaster(ActMaster actMaster) {
		this.actMaster = actMaster;
	}
	  
	  
	  
	
	
	

}
