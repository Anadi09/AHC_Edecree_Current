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
@Table(name="casemove_lkotoald_history")
public class CaseLkoToAldHistory {
	
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE,generator="lkotoald_seq")
	@SequenceGenerator(name="lkotoald_seq", sequenceName="lkotoald_seq", allocationSize=1)
	@Column(name = "lko_to_ald_id")
	private Long lko_to_ald_id;

	  private Long  old_fd_mid;
	  private Integer old_case_type;
	  private String old_case_no;
	  private Integer old_case_year;
	  
	  private Long  new_fd_mid;
	  private Integer new_case_type;
	  private String new_case_no;
	  private Integer new_case_year;
	  
	  private Long user_cr_by;
	  private Date lkotoald_cr_date;
      private Long user_mod_by;
      private Date  lkotoald_mod_date;
	public Long getLko_to_ald_id() {
		return lko_to_ald_id;
	}
	public void setLko_to_ald_id(Long lko_to_ald_id) {
		this.lko_to_ald_id = lko_to_ald_id;
	}
	public Long getOld_fd_mid() {
		return old_fd_mid;
	}
	public void setOld_fd_mid(Long old_fd_mid) {
		this.old_fd_mid = old_fd_mid;
	}
	public Integer getOld_case_type() {
		return old_case_type;
	}
	public void setOld_case_type(Integer old_case_type) {
		this.old_case_type = old_case_type;
	}
	public String getOld_case_no() {
		return old_case_no;
	}
	public void setOld_case_no(String old_case_no) {
		this.old_case_no = old_case_no;
	}
	public Integer getOld_case_year() {
		return old_case_year;
	}
	public void setOld_case_year(Integer old_case_year) {
		this.old_case_year = old_case_year;
	}
	public Long getNew_fd_mid() {
		return new_fd_mid;
	}
	public void setNew_fd_mid(Long new_fd_mid) {
		this.new_fd_mid = new_fd_mid;
	}
	public Integer getNew_case_type() {
		return new_case_type;
	}
	public void setNew_case_type(Integer new_case_type) {
		this.new_case_type = new_case_type;
	}
	public String getNew_case_no() {
		return new_case_no;
	}
	public void setNew_case_no(String new_case_no) {
		this.new_case_no = new_case_no;
	}
	public Integer getNew_case_year() {
		return new_case_year;
	}
	public void setNew_case_year(Integer new_case_year) {
		this.new_case_year = new_case_year;
	}
	public Long getUser_cr_by() {
		return user_cr_by;
	}
	public void setUser_cr_by(Long user_cr_by) {
		this.user_cr_by = user_cr_by;
	}
	public Date getLkotoald_cr_date() {
		return lkotoald_cr_date;
	}
	public void setLkotoald_cr_date(Date lkotoald_cr_date) {
		this.lkotoald_cr_date = lkotoald_cr_date;
	}
	public Long getUser_mod_by() {
		return user_mod_by;
	}
	public void setUser_mod_by(Long user_mod_by) {
		this.user_mod_by = user_mod_by;
	}
	public Date getLkotoald_mod_date() {
		return lkotoald_mod_date;
	}
	public void setLkotoald_mod_date(Date lkotoald_mod_date) {
		this.lkotoald_mod_date = lkotoald_mod_date;
	}
      
      
	
	

}
