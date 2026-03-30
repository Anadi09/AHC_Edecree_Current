package com.efiling.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ol_report")
public class OLReport {
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="ol_sequence")
	@SequenceGenerator(name="ol_sequence", sequenceName="ol_sequence", allocationSize=1)
	@Column(name = "ol_id")
	private Long ol_id;
	
	@Column(name = "ol_no")
	private Integer ol_no;
	
	@Column(name = "ol_year")
	private Integer ol_year;
	
	@Column(name = "ol_created")
	private Date ol_created;
	
	@Column(name = "ol_created_by")
	private Long ol_created_by;
	
	@Column(name ="read_status")
	private Integer read_status;

	public Long getOl_id() {
		return ol_id;
	}

	public void setOl_id(Long ol_id) {
		this.ol_id = ol_id;
	}

	public Integer getOl_no() {
		return ol_no;
	}

	public void setOl_no(Integer ol_no) {
		this.ol_no = ol_no;
	}

	public Integer getOl_year() {
		return ol_year;
	}

	public void setOl_year(Integer ol_year) {
		this.ol_year = ol_year;
	}

	public Date getOl_created() {
		return ol_created;
	}

	public void setOl_created(Date ol_created) {
		this.ol_created = ol_created;
	}

	public Long getOl_created_by() {
		return ol_created_by;
	}

	public void setOl_created_by(Long ol_created_by) {
		this.ol_created_by = ol_created_by;
	}

	public Integer getRead_status() {
		return read_status;
	}

	public void setRead_status(Integer read_status) {
		this.read_status = read_status;
	}

	@Override
	public String toString() {
		return "OLReport [ol_id=" + ol_id + ", ol_no=" + ol_no + ", ol_year=" + ol_year + ", ol_created=" + ol_created
				+ ", ol_created_by=" + ol_created_by + ", read_status=" + read_status + "]";
	}

	
	
	

}
