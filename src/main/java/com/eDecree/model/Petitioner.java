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

@Entity
@DynamicUpdate
@Table(name = "petitioner_details")
public class Petitioner {


		@Id
		@GeneratedValue (strategy = GenerationType.SEQUENCE,generator="petitionerdetails_seq")
		@SequenceGenerator(name="petitionerdetails_seq", sequenceName="petitionerdetails_seq", allocationSize=1)
		@Column(name = "pt_id")
		private Long pt_id;
		
		@Column(name="pt_fd_mid")
		private Long pt_fd_mid;
		
		@Column(name = "pt_name")
		private String pt_name;
		
		@Column(name = "pt_email")
		private String pt_email;
		
		@Column(name = "pt_mobile")
		private Long  pt_mobile;
		
		@Column(name = "pt_address")
		private String pt_address;
		
		@Column(name = "pt_pincode")
		private Long  pt_pincode;
		
		@Column(name = "pt_other_contact")
		private String pt_other_contact;
		
		@Column(name="pt_cr_by")
		private Long pt_cr_by;
		
		@Column(name="pt_cr_date")
		private Date pt_cr_date;	
		
		@Column(name="pt_mod_by")
		private Long pt_mod_by;	
		
		@Column(name="pt_mod_date")
		private Date pt_mod_date;
		
		@Column(name = "pt_rec_status")
		private Integer pt_rec_status;
		
		@Column(name = "pt_sequence")
		private Integer pt_sequence;
		
		@Column(name="pt_s_mid")
		private Long pt_s_mid;

		@Column(name="pt_city")
		private String pt_city;
		
		public Long getPt_id() {
			return pt_id;
		}

		public void setPt_id(Long pt_id) {
			this.pt_id = pt_id;
		}
		public Long getPt_fd_mid() {
			return pt_fd_mid;
		}

		public void setPt_fd_mid(Long pt_fd_mid) {
			this.pt_fd_mid = pt_fd_mid;
		}
		public String getPt_name() {
			return pt_name;
		}

		public void setPt_name(String pt_name) {
			this.pt_name = pt_name;
		}

		public String getPt_email() {
			return pt_email;
		}

		public void setPt_email(String pt_email) {
			this.pt_email = pt_email;
		}

		public Long getPt_mobile() {
			return pt_mobile;
		}

		public void setPt_mobile(Long pt_mobile) {
			this.pt_mobile = pt_mobile;
		}

		public String getPt_address() {
			return pt_address;
		}

		public void setPt_address(String pt_address) {
			this.pt_address = pt_address;
		}

		public Long getPt_pincode() {
			return pt_pincode;
		}

		public void setPt_pincode(Long pt_pincode) {
			this.pt_pincode = pt_pincode;
		}

		public String getPt_other_contact() {
			return pt_other_contact;
		}

		public void setPt_other_contact(String pt_other_contact) {
			this.pt_other_contact = pt_other_contact;
		}

		public Long getPt_cr_by() {
			return pt_cr_by;
		}

		public void setPt_cr_by(Long pt_cr_by) {
			this.pt_cr_by = pt_cr_by;
		}

		public Date getPt_cr_date() {
			return pt_cr_date;
		}

		public void setPt_cr_date(Date pt_cr_date) {
			this.pt_cr_date = pt_cr_date;
		}

		public Long getPt_mod_by() {
			return pt_mod_by;
		}

		public void setPt_mod_by(Long pt_mod_by) {
			this.pt_mod_by = pt_mod_by;
		}

		public Date getPt_mod_date() {
			return pt_mod_date;
		}

		public void setPt_mod_date(Date pt_mod_date) {
			this.pt_mod_date = pt_mod_date;
		}

		public Integer getPt_rec_status() {
			return pt_rec_status;
		}

		public void setPt_rec_status(Integer pt_rec_status) {
			this.pt_rec_status = pt_rec_status;
		}

	

	/*	public RegisteredCaseDetails getRegisteredCase() {
			return registeredCase;
		}

		public void setRegisteredCase(RegisteredCaseDetails registeredCase) {
			this.registeredCase = registeredCase;
		}*/

		public Long getPt_s_mid() {
			return pt_s_mid;
		}

		public void setPt_s_mid(Long pt_s_mid) {
			this.pt_s_mid = pt_s_mid;
		}

		public String getPt_city() {
			return pt_city;
		}

		public void setPt_city(String pt_city) {
			this.pt_city = pt_city;
		}

		public Integer getPt_sequence() {
			return pt_sequence;
		}

		public void setPt_sequence(Integer pt_sequence) {
			this.pt_sequence = pt_sequence;
		}

		
		
		
		
		
		
	
		

}
