package com.eDecree.model;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="allow_efiling")
public class AllowEfiling {

@Id
@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="allow_efiling_seq")
@SequenceGenerator(name="allow_efiling_seq", sequenceName="allow_efiling_seq", allocationSize=1)
@Column(name="ae_id")
private Long ae_id;

@Column(name="ae_reference_mid")
private Long ae_reference_mid;

@Column(name="ae_fd_case_type")
private Long ae_fd_case_type;

@Column(name="ae_fd_mid")
private Long ae_fd_mid;

@Column(name="ae_code")
private Integer ae_code;

@Column(name="ae_status")
private Integer ae_status;

@Column(name="ae_allow_for")
private Character ae_allow_for;

public Character getAe_allow_for() {
return ae_allow_for;
}

public void setAe_allow_for(Character ae_allow_for) {
this.ae_allow_for = ae_allow_for;
}

public Long getAe_id() {
return ae_id;
}

public void setAe_id(Long ae_id) {
this.ae_id = ae_id;
}

public Long getAe_reference_mid() {
return ae_reference_mid;
}

public void setAe_reference_mid(Long ae_reference_mid) {
this.ae_reference_mid = ae_reference_mid;
}

public Long getAe_fd_mid() {
return ae_fd_mid;
}

public void setAe_fd_mid(Long ae_fd_mid) {
this.ae_fd_mid = ae_fd_mid;
}

public Integer getAe_code() {
return ae_code;
}

public void setAe_code(Integer ae_code) {
this.ae_code = ae_code;
}

public Integer getAe_status() {
return ae_status;
}

public void setAe_status(Integer ae_status) {
this.ae_status = ae_status;
}

public Long getAe_fd_case_type() {
	return ae_fd_case_type;
}

public void setAe_fd_case_type(Long ae_fd_case_type) {
	this.ae_fd_case_type = ae_fd_case_type;
}

@Override
public String toString() {
	return "AllowEfiling [ae_id=" + ae_id + ", ae_reference_mid=" + ae_reference_mid + ", ae_fd_case_type="
			+ ae_fd_case_type + ", ae_fd_mid=" + ae_fd_mid + ", ae_code=" + ae_code + ", ae_status=" + ae_status
			+ ", ae_allow_for=" + ae_allow_for + "]";
}






}