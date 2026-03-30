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
@Table(name="mediafiles")
public class MediaFile {
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="mediafileseq")
	@SequenceGenerator(name="mediafileseq", sequenceName="mediafileseq", allocationSize=1)
	@Column(name="mf_id")
	private Long mf_id;
	
	@Column(name="mf_fd_mid")
	private Long mf_fd_mid;
	
	@Column(name="mf_folder_id")
	private Long mf_folder_id;
	
	@Column(name="mf_extension")
	private String mf_extension;
	
	@Column(name="mf_file_name")
	private String mf_file_name;
	
	@Column(name="mf_encrypted_name")
	private String mf_encrypted_name;
	
	@Column(name="mf_created")
	private Date mf_created;
	
	@Column(name="mf_created_by")
	private Long mf_created_by;
	
	@Column(name="mf_updated")
	private Date mf_updated;
	
	@Column(name="mf_updated_by")
	private Long mf_updated_by;
	
	@Column(name="mf_rec_status")
	private Integer mf_rec_status;

	public Long getMf_id() {
		return mf_id;
	}

	public void setMf_id(Long mf_id) {
		this.mf_id = mf_id;
	}

	public Long getMf_fd_mid() {
		return mf_fd_mid;
	}

	public void setMf_fd_mid(Long mf_fd_mid) {
		this.mf_fd_mid = mf_fd_mid;
	}

	public String getMf_extension() {
		return mf_extension;
	}

	public void setMf_extension(String mf_extension) {
		this.mf_extension = mf_extension;
	}

	public String getMf_file_name() {
		return mf_file_name;
	}

	public void setMf_file_name(String mf_file_name) {
		this.mf_file_name = mf_file_name;
	}

	public Date getMf_created() {
		return mf_created;
	}

	public void setMf_created(Date mf_created) {
		this.mf_created = mf_created;
	}

	public Long getMf_created_by() {
		return mf_created_by;
	}

	public void setMf_created_by(Long mf_created_by) {
		this.mf_created_by = mf_created_by;
	}

	public Date getMf_updated() {
		return mf_updated;
	}

	public void setMf_updated(Date mf_updated) {
		this.mf_updated = mf_updated;
	}

	public Long getMf_updated_by() {
		return mf_updated_by;
	}

	public void setMf_updated_by(Long mf_updated_by) {
		this.mf_updated_by = mf_updated_by;
	}

	public Integer getMf_rec_status() {
		return mf_rec_status;
	}

	public void setMf_rec_status(Integer mf_rec_status) {
		this.mf_rec_status = mf_rec_status;
	}

	public String getMf_encrypted_name() {
		return mf_encrypted_name;
	}

	public void setMf_encrypted_name(String mf_encrypted_name) {
		this.mf_encrypted_name = mf_encrypted_name;
	}

	public Long getMf_folder_id() {
		return mf_folder_id;
	}

	public void setMf_folder_id(Long mf_folder_id) {
		this.mf_folder_id = mf_folder_id;
	}
	
	
}
