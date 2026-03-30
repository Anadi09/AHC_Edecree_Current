package com.eDecree.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name="regular_defective_history")
public class RegularToDefective {
	
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE,generator="rdh_seq")
	@SequenceGenerator(name="rdh_seq", sequenceName="rdh_seq", allocationSize=1)
	@Column(name = "rdh_id")
	private Long rdh_id;
	

	  private Long  rdh_fd_mid_old;

	  private Long  rdh_fd_mid_new;

	  private String rdh_case_type_old;
	  
	  private String rdh_case_no_old;
	  
	  
	  private String rdh_case_year_old;
	  

	  private String rdh_case_type;
	  
	  private String rdh_case_no;

	  private String rdh_case_year;

	  private Long rdh_cr_by;

	  private Date rdh_cr_date;
	  
      private Long rdh_mod_by;
	  
     private Date  rdh_mod_date;

	public Long getRdh_id() {
		return rdh_id;
	}

	public void setRdh_id(Long rdh_id) {
		this.rdh_id = rdh_id;
	}

	public Long getRdh_fd_mid_old() {
		return rdh_fd_mid_old;
	}

	public void setRdh_fd_mid_old(Long rdh_fd_mid_old) {
		this.rdh_fd_mid_old = rdh_fd_mid_old;
	}

	public Long getRdh_fd_mid_new() {
		return rdh_fd_mid_new;
	}

	public void setRdh_fd_mid_new(Long rdh_fd_mid_new) {
		this.rdh_fd_mid_new = rdh_fd_mid_new;
	}

	public String getRdh_case_type_old() {
		return rdh_case_type_old;
	}

	public void setRdh_case_type_old(String rdh_case_type_old) {
		this.rdh_case_type_old = rdh_case_type_old;
	}

	public String getRdh_case_no_old() {
		return rdh_case_no_old;
	}

	public void setRdh_case_no_old(String rdh_case_no_old) {
		this.rdh_case_no_old = rdh_case_no_old;
	}

	public String getRdh_case_year_old() {
		return rdh_case_year_old;
	}

	public void setRdh_case_year_old(String rdh_case_year_old) {
		this.rdh_case_year_old = rdh_case_year_old;
	}

	public String getRdh_case_type() {
		return rdh_case_type;
	}

	public void setRdh_case_type(String rdh_case_type) {
		this.rdh_case_type = rdh_case_type;
	}

	public String getRdh_case_no() {
		return rdh_case_no;
	}

	public void setRdh_case_no(String rdh_case_no) {
		this.rdh_case_no = rdh_case_no;
	}

	public String getRdh_case_year() {
		return rdh_case_year;
	}

	public void setRdh_case_year(String rdh_case_year) {
		this.rdh_case_year = rdh_case_year;
	}

	public Long getRdh_cr_by() {
		return rdh_cr_by;
	}

	public void setRdh_cr_by(Long rdh_cr_by) {
		this.rdh_cr_by = rdh_cr_by;
	}

	public Date getRdh_cr_date() {
		return rdh_cr_date;
	}

	public void setRdh_cr_date(Date rdh_cr_date) {
		this.rdh_cr_date = rdh_cr_date;
	}

	public Long getRdh_mod_by() {
		return rdh_mod_by;
	}

	public void setRdh_mod_by(Long rdh_mod_by) {
		this.rdh_mod_by = rdh_mod_by;
	}

	public Date getRdh_mod_date() {
		return rdh_mod_date;
	}

	public void setRdh_mod_date(Date rdh_mod_date) {
		this.rdh_mod_date = rdh_mod_date;
	} 
      
	
	
	
	

}
