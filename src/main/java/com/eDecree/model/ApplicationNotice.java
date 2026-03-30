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
@Table(name = "application_notice")
public class ApplicationNotice {
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="application_notice_seq")
	@SequenceGenerator(name="application_notice_seq", sequenceName="application_notice_seq", allocationSize=1)
	@Column(name = "an_id")
	private Long an_id;
	@Column(name = "an_fd_mid")
	private Long an_fd_mid; 
	
	@Column(name = "an_nf_mid")
	private Long an_nf_mid; 
	
	@Column(name="an_cr_date")
	private Date an_cr_date;
	
	@Column(name="an_mod_date")
	private Date an_mod_date;
	
	@Column(name="an_rec_status")
	private int an_rec_status;
	
	@Column(name = "an_a1",columnDefinition="TEXT")
	private String a1;
	@Column(name = "an_a2",columnDefinition="TEXT")
	private String a2;
	@Column(name = "an_a3",columnDefinition="TEXT")
	private String a3;
	@Column(name = "an_a4",columnDefinition="TEXT")
	private String a4;
	@Column(name = "an_a5",columnDefinition="TEXT")
	private String a5;
	@Column(name = "an_a6",columnDefinition="TEXT")
	private String a6;
	@Column(name = "an_a7",columnDefinition="TEXT")
	private String a7;
	@Column(name = "an_a8",columnDefinition="TEXT")
	private String a8;
	@Column(name = "an_a9",columnDefinition="TEXT")
	private String a9;
	@Column(name = "an_a10",columnDefinition="TEXT")
	private String a10;
	@Column(name = "an_a11",columnDefinition="TEXT")
	private String a11;
	@Column(name = "an_a12",columnDefinition="TEXT")
	private String a12;
	@Column(name = "an_a13",columnDefinition="TEXT")
	private String a13;
	@Column(name = "an_a14",columnDefinition="TEXT")
	private String a14;
	@Column(name = "an_a15",columnDefinition="TEXT")
	private String a15;
	@Column(name = "an_a16",columnDefinition="TEXT")
	private String a16;
	@Column(name = "an_a17",columnDefinition="TEXT")
	private String a17;
	@Column(name = "an_a18",columnDefinition="TEXT")
	private String a18;
	
	@Column(name="an_cr_by")
	private Long an_cr_by;
	
	
	
	
	public Long getAn_cr_by() {
		return an_cr_by;
	}
	public void setAn_cr_by(Long an_cr_by) {
		this.an_cr_by = an_cr_by;
	}
	public Date getAn_cr_date() {
		return an_cr_date;
	}
	public void setAn_cr_date(Date an_cr_date) {
		this.an_cr_date = an_cr_date;
	}
	public Date getAn_mod_date() {
		return an_mod_date;
	}
	public void setAn_mod_date(Date an_mod_date) {
		this.an_mod_date = an_mod_date;
	}
	public int getAn_rec_status() {
		return an_rec_status;
	}
	public void setAn_rec_status(int an_rec_status) {
		this.an_rec_status = an_rec_status;
	}
	public Long getAn_id() {
		return an_id;
	}
	public void setAn_id(Long an_id) {
		this.an_id = an_id;
	}
	public String getA1() {
		return a1;
	}
	public void setA1(String a1) {
		this.a1 = a1;
	}
	public String getA2() {
		return a2;
	}
	public void setA2(String a2) {
		this.a2 = a2;
	}
	public String getA3() {
		return a3;
	}
	public void setA3(String a3) {
		this.a3 = a3;
	}
	public String getA4() {
		return a4;
	}
	public void setA4(String a4) {
		this.a4 = a4;
	}
	public String getA5() {
		return a5;
	}
	public void setA5(String a5) {
		this.a5 = a5;
	}
	public String getA6() {
		return a6;
	}
	public void setA6(String a6) {
		this.a6 = a6;
	}
	public String getA7() {
		return a7;
	}
	public void setA7(String a7) {
		this.a7 = a7;
	}
	public String getA8() {
		return a8;
	}
	public void setA8(String a8) {
		this.a8 = a8;
	}
	public String getA9() {
		return a9;
	}
	public void setA9(String a9) {
		this.a9 = a9;
	}
	public String getA10() {
		return a10;
	}
	public void setA10(String a10) {
		this.a10 = a10;
	}
	public String getA11() {
		return a11;
	}
	public void setA11(String a11) {
		this.a11 = a11;
	}
	public String getA12() {
		return a12;
	}
	public void setA12(String a12) {
		this.a12 = a12;
	}
	public String getA13() {
		return a13;
	}
	public void setA13(String a13) {
		this.a13 = a13;
	}
	public String getA14() {
		return a14;
	}
	public void setA14(String a14) {
		this.a14 = a14;
	}
	public String getA15() {
		return a15;
	}
	public void setA15(String a15) {
		this.a15 = a15;
	}
	public String getA16() {
		return a16;
	}
	public void setA16(String a16) {
		this.a16 = a16;
	}
	public String getA17() {
		return a17;
	}
	public void setA17(String a17) {
		this.a17 = a17;
	}
	public String getA18() {
		return a18;
	}
	public void setA18(String a18) {
		this.a18 = a18;
	}
	public Long getAn_fd_mid() {
		return an_fd_mid;
	}
	public void setAn_fd_mid(Long an_fd_mid) {
		this.an_fd_mid = an_fd_mid;
	}
	public Long getAn_nf_mid() {
		return an_nf_mid;
	}
	public void setAn_nf_mid(Long an_nf_mid) {
		this.an_nf_mid = an_nf_mid;
	}
	
	
	
}
