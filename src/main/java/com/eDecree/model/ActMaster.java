package com.eDecree.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="act_master")
public class ActMaster {
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE,generator="act_master_seq")
	@SequenceGenerator(name="act_master_seq", sequenceName="act_master_seq", allocationSize=1)
	  @Column(name = "act_id")
	   private Long  act_id;
	   
	  @Column(name = "act_code")
	  private Long act_code;
	  
	  @Column(name="act_type")
	  private String act_type;	  
	
	  @Column(name = "act_name")
	  private String act_name;

	public Long getAct_id() {
		return act_id;
	}

	public void setAct_id(Long act_id) {
		this.act_id = act_id;
	}

	public Long getAct_code() {
		return act_code;
	}

	public void setAct_code(Long act_code) {
		this.act_code = act_code;
	}

	public String getAct_type() {
		return act_type;
	}

	public void setAct_type(String act_type) {
		this.act_type = act_type;
	}

	public String getAct_name() {
		return act_name;
	}

	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}
}
	  
	  
