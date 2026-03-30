package com.pdms.model;

import java.util.Date;
import java.util.List;

/*import javax.persistence.CascadeType;
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
import javax.persistence.Transient;*/

//@Entity
//@Table(name = "index_fields")
public class IndexField {

	/*@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="index_fieldsseq")
	@SequenceGenerator(name="index_fieldsseq", sequenceName="index_fieldsseq", allocationSize=1)
	@Column(name = "if_id")*/
	public Long if_id;
	
//	@Column(name = "if_name")
	public String if_name;
	
//	@Column(name = "if_label")
	public String if_label;
	
//	@Column(name = "if_type")
	public String if_type;
	
//	@Column(name="if_cr_by")
	public Long if_cr_by;
	
//	@Column(name="if_cr_date")
	public Date if_cr_date;	
	
//	@Column(name="if_mod_by")
	public Long if_mod_by;	
	
//	@Column(name="if_mod_date")
	public Date if_mod_date;
	
//	@Column(name = "if_rec_status")
	public Integer if_rec_status;
	
//	@Column(name = "if_required_status")
	public Integer if_required_status;
	
//	@Column(name = "if_add_multiple")
	public Integer if_add_multiple;
	
//	@Column(name = "if_parent")
	public Long if_parent;
	
//	@Column(name = "if_sequence")
	public Integer if_sequence;
	
//	@Column(name="if_level")
	public Long if_level;
	
//	@Column(name = "if_type_code")
	public String if_type_code;
	
//	@Column(name = "if_extr_info")
	public String if_extr_info;

	public Long getIf_id() {
		return if_id;
	}

	public void setIf_id(Long if_id) {
		this.if_id = if_id;
	}

	public String getIf_name() {
		return if_name;
	}

	public void setIf_name(String if_name) {
		this.if_name = if_name;
	}

	public String getIf_label() {
		return if_label;
	}

	public void setIf_lable(String if_label) {
		this.if_label = if_label;
	}

	public String getIf_type() {
		return if_type;
	}

	public void setIf_type(String if_type) {
		this.if_type = if_type;
	}

	public Long getIf_cr_by() {
		return if_cr_by;
	}

	public void setIf_cr_by(Long if_cr_by) {
		this.if_cr_by = if_cr_by;
	}

	public Date getIf_cr_date() {
		return if_cr_date;
	}

	public void setIf_cr_date(Date if_cr_date) {
		this.if_cr_date = if_cr_date;
	}

	public Long getIf_mod_by() {
		return if_mod_by;
	}

	public void setIf_mod_by(Long if_mod_by) {
		this.if_mod_by = if_mod_by;
	}

	public Date getIf_mod_date() {
		return if_mod_date;
	}

	public void setIf_mod_date(Date if_mod_date) {
		this.if_mod_date = if_mod_date;
	}

	public Integer getIf_rec_status() {
		return if_rec_status;
	}

	public void setIf_rec_status(Integer if_rec_status) {
		this.if_rec_status = if_rec_status;
	}

	public Integer getIf_required_status() {
		return if_required_status;
	}

	public void setIf_required_status(Integer if_required_status) {
		this.if_required_status = if_required_status;
	}

	public Integer getIf_add_multiple() {
		return if_add_multiple;
	}

	public void setIf_add_multiple(Integer if_add_multiple) {
		this.if_add_multiple = if_add_multiple;
	}

	public Long getIf_parent() {
		return if_parent;
	}

	public void setIf_parent(Long if_parent) {
		this.if_parent = if_parent;
	}

	public Integer getIf_sequence() {
		return if_sequence;
	}

	public void setIf_sequence(Integer if_sequence) {
		this.if_sequence = if_sequence;
	}

	public Long getIf_level() {
		return if_level;
	}

	public void setIf_level(Long if_level) {
		this.if_level = if_level;
	}

	public String getIf_type_code() {
		return if_type_code;
	}

	public void setIf_type_code(String if_type_code) {
		this.if_type_code = if_type_code;
	}

	public String getIf_extr_info() {
		return if_extr_info;
	}

	public void setIf_extr_info(String if_extr_info) {
		this.if_extr_info = if_extr_info;
	}

	
	
}
