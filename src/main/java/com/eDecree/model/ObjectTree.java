package com.eDecree.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "object_master")
public class ObjectTree {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MstrSeq")
	@SequenceGenerator(name = "MstrSeq", sequenceName = "MstrSeq", allocationSize = 1)
	@Column(name = "om_id")
	private Long om_id;

	@Column(name = "om_object_name")
	private String om_object_name;

	@Column(name = "om_parent_id")
	private Long om_parent_id;

	@Column(name = "om_object_link")
	private String om_object_link;

	@Column(name = "om_object_stages")
	private Long om_object_stages;

	@Column(name = "om_rec_status")
	private Long om_rec_status;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "om_parent_id")
	private List<ObjectTree> children;

	public Long getOm_id() {
		return om_id;
	}

	public void setOm_id(Long om_id) {
		this.om_id = om_id;
	}

	public String getOm_object_name() {
		return om_object_name;
	}

	public void setOm_object_name(String om_object_name) {
		this.om_object_name = om_object_name;
	}

	public Long getOm_parent_id() {
		return om_parent_id;
	}

	public void setOm_parent_id(Long om_parent_id) {
		this.om_parent_id = om_parent_id;
	}

	public String getOm_object_link() {
		return om_object_link;
	}

	public void setOm_object_link(String om_object_link) {
		this.om_object_link = om_object_link;
	}

	public Long getOm_object_stages() {
		return om_object_stages;
	}

	public void setOm_object_stages(Long om_object_stages) {
		this.om_object_stages = om_object_stages;
	}

	public Long getOm_rec_status() {
		return om_rec_status;
	}

	public void setOm_rec_status(Long om_rec_status) {
		this.om_rec_status = om_rec_status;
	}

	public List<ObjectTree> getChildren() {
		return children;
	}

	public void setChildren(List<ObjectTree> children) {
		this.children = children;
	}


	
	/*@ManyToOne
    @JoinColumn(name="om_parent_id",referencedColumnName="om_id",insertable = false, updatable = false)
    private ObjectMaster objectMaster;

	public ObjectMaster getObjectMaster() {
		return objectMaster;
	}

	public void setObjectMaster(ObjectMaster objectMaster) {
		this.objectMaster = objectMaster;
	}
*/


	
}
