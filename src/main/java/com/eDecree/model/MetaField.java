package com.eDecree.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "meta_fields")
public class MetaField {

	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="MstrSeq")
	@SequenceGenerator(name="MstrSeq", sequenceName="MstrSeq", allocationSize=1)
	@Column(name = "mf_id")
	private Long mf_id;
	
	@Column(name = "mf_name")
	private String mf_name;
	
	@Column(name = "mf_lable")
	private String mf_lable;
	
	@Column(name = "mf_type")
	private String mf_type;
	
	@Column(name="mf_cr_by")
	private Long mf_cr_by;
	
	@Column(name="mf_mod_by")
	private Long mf_mod_by;
	
	@Column(name="mf_mod_date")
	private Date mf_mod_date;
	
	@Column(name = "mf_rec_status")
	private Integer mf_rec_status;
	
	@Column(name="mf_cr_date")
	private Date mf_cr_date;
	
	@Column(name = "mf_required_status")
	private Integer mf_required_status;
	
	
	@Column(name="mf_table_name")
	private String mf_table_name;
	
	@Column(name="mf_table_key_column")
	private String mf_table_key_column;
	
	@Column(name="mf_table_display_column")
	private String mf_table_display_column;
	
	@Column(name = "mf_add_multiple")
	private Integer mf_add_multiple;

	@Column(name = "mf_rec_deleted")
	private Integer mf_rec_deleted;	

	@Column(name = "mf_regex_validation")
	private String mf_regex_validation;
	
	@Column(name = "mf_mt_mid")
	private Long mf_mt_mid;
	
	@Column(name="mf_sequence")
	private Integer mf_sequence;
	
	@Transient
	private List<DropDown> dropDownList;
	
	public Long getId() {
		return mf_id;
	}

	public void setId(Long id) {
		this.mf_id = id;
	}

	public Long getMf_id() {
		return mf_id;
	}

	public void setMf_id(Long mf_id) {
		this.mf_id = mf_id;
	}

	public String getMf_name() {
		return mf_name;
	}

	public void setMf_name(String mf_name) {
		this.mf_name = mf_name;
	}

	public String getMf_lable() {
		return mf_lable;
	}

	public void setMf_lable(String mf_lable) {
		this.mf_lable = mf_lable;
	}

	public String getMf_type() {
		return mf_type;
	}

	public void setMf_type(String mf_type) {
		this.mf_type = mf_type;
	}

	public Long getMf_cr_by() {
		return mf_cr_by;
	}

	public void setMf_cr_by(Long mf_cr_by) {
		this.mf_cr_by = mf_cr_by;
	}

	public Long getMf_mod_by() {
		return mf_mod_by;
	}

	public void setMf_mod_by(Long mf_mod_by) {
		this.mf_mod_by = mf_mod_by;
	}

	public Date getMf_mod_date() {
		return mf_mod_date;
	}

	public void setMf_mod_date(Date mf_mod_date) {
		this.mf_mod_date = mf_mod_date;
	}

	public Integer getMf_rec_status() {
		return mf_rec_status;
	}

	public void setMf_rec_status(Integer mf_rec_status) {
		this.mf_rec_status = mf_rec_status;
	}

	public Date getMf_cr_date() {
		return mf_cr_date;
	}

	public void setMf_cr_date(Date mf_cr_date) {
		this.mf_cr_date = mf_cr_date;
	}

	public Integer getMf_required_status() {
		return mf_required_status;
	}

	public void setMf_required_status(Integer mf_required_status) {
		this.mf_required_status = mf_required_status;
	}

	public String getMf_table_name() {
		return mf_table_name;
	}

	public void setMf_table_name(String mf_table_name) {
		this.mf_table_name = mf_table_name;
	}

	public String getMf_table_key_column() {
		return mf_table_key_column;
	}

	public void setMf_table_key_column(String mf_table_key_column) {
		this.mf_table_key_column = mf_table_key_column;
	}

	public String getMf_table_display_column() {
		return mf_table_display_column;
	}

	public void setMf_table_display_column(String mf_table_display_column) {
		this.mf_table_display_column = mf_table_display_column;
	}

	public Integer getMf_add_multiple() {
		return mf_add_multiple;
	}

	public void setMf_add_multiple(Integer mf_add_multiple) {
		this.mf_add_multiple = mf_add_multiple;
	}

	public Integer getMf_rec_deleted() {
		return mf_rec_deleted;
	}

	public void setMf_rec_deleted(Integer mf_rec_deleted) {
		this.mf_rec_deleted = mf_rec_deleted;
	}

	public String getMf_regex_validation() {
		return mf_regex_validation;
	}

	public void setMf_regex_validation(String mf_regex_validation) {
		this.mf_regex_validation = mf_regex_validation;
	}

	public Long getMf_mt_mid() {
		return mf_mt_mid;
	}

	public void setMf_mt_mid(Long mf_mt_mid) {
		this.mf_mt_mid = mf_mt_mid;
	}

	public Integer getMf_sequence() {
		return mf_sequence;
	}

	public void setMf_sequence(Integer mf_sequence) {
		this.mf_sequence = mf_sequence;
	}

	public List<DropDown> getDropDownList() {
		return dropDownList;
	}

	public void setDropDownList(List<DropDown> dropDownList) {
		this.dropDownList = dropDownList;
	}

	
	
	
}
