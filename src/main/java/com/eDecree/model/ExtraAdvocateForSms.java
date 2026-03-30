package com.eDecree.model;



import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "extra_advocate_for_sms")
public class ExtraAdvocateForSms {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "extra_adv_seq")
    @SequenceGenerator(name = "extra_adv_seq", sequenceName = "extra_adv_seq", allocationSize = 1)
    @Column(name = "ea_id")
    private Long ea_id;

    @Column(name = "ea_aor")
    private String aor;

    @Column(name = "ea_name")
    private String name;

    @Column(name = "ea_mobile")
    private String mobile;

    @Column(name = "ea_case_type")
    private String case_type;

    @Column(name = "ea_case_no")
    private String case_no;

    @Column(name = "ea_case_year")
    private String case_year;

    @Column(name = "ea_cr_by")
    private Long cr_by;

    @Column(name = "ea_cr_date")
    private Date cr_date;

    @Column(name = "ea_mod_by")
    private Long mod_by;

    @Column(name = "ea_mod_date")
    private Date mod_date;

    // Getters & Setters

    public Long getEa_id() { return ea_id; }
    public void setEa_id(Long ea_id) { this.ea_id = ea_id; }

    public String getAor() { return aor; }
    public void setAor(String aor) { this.aor = aor; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getCase_type() { return case_type; }
    public void setCase_type(String case_type) { this.case_type = case_type; }

    public String getCase_no() { return case_no; }
    public void setCase_no(String case_no) { this.case_no = case_no; }

    public String getCase_year() { return case_year; }
    public void setCase_year(String case_year) { this.case_year = case_year; }

    public Long getCr_by() { return cr_by; }
    public void setCr_by(Long cr_by) { this.cr_by = cr_by; }

    public Date getCr_date() { return cr_date; }
    public void setCr_date(Date cr_date) { this.cr_date = cr_date; }

    public Long getMod_by() { return mod_by; }
    public void setMod_by(Long mod_by) { this.mod_by = mod_by; }

    public Date getMod_date() { return mod_date; }
    public void setMod_date(Date mod_date) { this.mod_date = mod_date; }
    
    
    
    @Override
    public String toString() {
        return "ExtraAdvocateForSms {" +
                "ea_id=" + ea_id +
                ", aor='" + aor + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", case_type='" + case_type + '\'' +
                ", case_no='" + case_no + '\'' +
                ", case_year='" + case_year + '\'' +
                ", cr_by=" + cr_by +
                ", cr_date=" + cr_date +
                ", mod_by=" + mod_by +
                ", mod_date=" + mod_date +
                '}';
    }
    
}