package com.eDecree.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "meta_data")
public class MetaData {

	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="meta_dataseq")
	@SequenceGenerator(name="meta_dataseq", sequenceName="meta_dataseq", allocationSize=1)
	@Column(name = "md_id")
	private Long md_id;
	
	@Column(name = "md_mf_mid")
	private Long md_mf_mid;
	
	@Column(name = "md_fd_mid")
	private Long md_fd_mid;
	
	@Column(name = "md_value")
	private String md_value;	
	
	@Column(name = "md_cr_by")
	private Long md_cr_by;
	
	@Column(name = "md_rec_status")
	private Integer md_rec_status;
	
	@Column(name = "md_sequence")
	private Integer md_sequence;
	
	@Transient
	private Long flag;
	
	@ManyToOne
    @JoinColumn(name="md_mf_mid",referencedColumnName="mf_id",insertable = false, updatable = false)
    private MetaField metaField;
	
	public Long getMd_id() {
		return md_id;
	}

	public void setMd_id(Long md_id) {
		this.md_id = md_id;
	}

	public Long getMd_mf_mid() {
		return md_mf_mid;
	}

	public void setMd_mf_mid(Long md_mf_mid) {
		this.md_mf_mid = md_mf_mid;
	}

	public Long getMd_fd_mid() {
		return md_fd_mid;
	}

	public void setMd_fd_mid(Long md_fd_mid) {
		this.md_fd_mid = md_fd_mid;
	}

	public String getMd_value() {
		return md_value;
	}

	public void setMd_value(String md_value) {
		this.md_value = md_value;
	}

	public MetaField getMetaField() {
		return metaField;
	}

	public void setMetaField(MetaField metaField) {
		this.metaField = metaField;
	}

	public Long getFlag() {
		return flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}

	public Long getMd_cr_by() {
		return md_cr_by;
	}

	public void setMd_cr_by(Long md_cr_by) {
		this.md_cr_by = md_cr_by;
	}

	public Integer getMd_rec_status() {
		return md_rec_status;
	}

	public void setMd_rec_status(Integer md_rec_status) {
		this.md_rec_status = md_rec_status;
	}

	public Integer getMd_sequence() {
		return md_sequence;
	}

	public void setMd_sequence(Integer md_sequence) {
		this.md_sequence = md_sequence;
	}
	
	
}
