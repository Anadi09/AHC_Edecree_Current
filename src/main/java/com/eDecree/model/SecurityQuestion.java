package com.eDecree.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="pwd_retrival_answer")
public class SecurityQuestion 
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "pwd_retrival_answerseq")
    @SequenceGenerator(name="pwd_retrival_answerseq" , sequenceName = "pwd_retrival_answerseq" ,allocationSize=1)
    @Column(name ="pra_id")
	private Long pra_id;
	
    @Column(name ="pra_question_lid")
    private Long pra_question_lid;
    
    @Column(name="pra_answer")
    private String pra_answer;
    
    @Column(name="pra_user_mid")
    private Long pra_user_mid;
    
    @Transient
    private String password;
    
    @Transient
    private String passwordc;

	public String getPasswordc() {
		return passwordc;
	}

	public void setPasswordc(String passwordc) {
		this.passwordc = passwordc;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getPra_id() {
		return pra_id;
	}

	public void setPra_id(Long pra_id) {
		this.pra_id = pra_id;
	}

	public Long getPra_question_lid() {
		return pra_question_lid;
	}

	public void setPra_question_lid(Long pra_question_lid) {
		this.pra_question_lid = pra_question_lid;
	}

	public String getPra_answer() {
		return pra_answer;
	}

	public void setPra_answer(String pra_answer) {
		this.pra_answer = pra_answer;
	}

	public Long getPra_user_mid() {
		return pra_user_mid;
	}

	public void setPra_user_mid(Long pra_user_mid) {
		this.pra_user_mid = pra_user_mid;
	}


    
    
    
}
