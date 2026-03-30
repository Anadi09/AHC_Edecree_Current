package com.eDecree.model;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="rec")
@XmlAccessorType(XmlAccessType.FIELD)
public class Rec {
	
	 @XmlElement(name="courtno")
	 private Integer courtno;

	 @XmlElement(name="slno")
	 private Integer slno;

	 //Object of case
	 @XmlElement(name="case")
	 private Case cases; 
	 
	 @XmlElement(name="ltype")
	 private String ltype;
	 
	 @XmlElement(name="then")
	 private Integer then;
	 
	 @XmlElement(name="pet")
	 private String pet;
	 
	 @XmlElement(name="res")
	 private String res;
	 
	 @XmlElement(name="petadv")
	 private String petadv;

	 //object of extpadv
//	 @XmlElement(name="extpadv")
//	 @XmlJavaTypeAdapter(MapAdapter.class)
//	 private Map<String, String> extpadv;
//	 
//	 @XmlElement(name="extradv")
//	 @XmlJavaTypeAdapter(MapAdapter.class)
//	 private Map<String, String> extradv = new HashMap<>();
	 
	 @XmlElement(name="resadv")
	 private String resadv;
	 
	 @XmlElement(name="ano")
	 private Integer ano;
	 
	 @XmlElement(name="ayr")
	 private Integer ayr;
	 
	 @XmlElement(name="applawp")
	 private String applawp;
	 
	 @XmlElement(name="applawr")
	 private String applawr;

	 @XmlElement(name="stage")
	 private Integer stage;

	 @XmlElement(name="dol")
	 private String dol;

	public Integer getCourtno() {
		return courtno;
	}

	public void setCourtno(Integer courtno) {
		this.courtno = courtno;
	}

	public Integer getSlno() {
		return slno;
	}

	public void setSlno(Integer slno) {
		this.slno = slno;
	}


	public String getLtype() {
		return ltype;
	}

	public void setLtype(String ltype) {
		this.ltype = ltype;
	}

	public Integer getThen() {
		return then;
	}

	public void setThen(Integer then) {
		this.then = then;
	}

	public String getPet() {
		return pet;
	}

	public void setPet(String pet) {
		this.pet = pet;
	}

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	public String getPetadv() {
		return petadv;
	}

	public void setPetadv(String petadv) {
		this.petadv = petadv;
	}

	public String getResadv() {
		return resadv;
	}

	public void setResadv(String resadv) {
		this.resadv = resadv;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getAyr() {
		return ayr;
	}

	public void setAyr(Integer ayr) {
		this.ayr = ayr;
	}

	public String getApplawp() {
		return applawp;
	}

	public void setApplawp(String applawp) {
		this.applawp = applawp;
	}

	public String getApplawr() {
		return applawr;
	}

	public void setApplawr(String applawr) {
		this.applawr = applawr;
	}

	public Integer getStage() {
		return stage;
	}

	public void setStage(Integer stage) {
		this.stage = stage;
	}

	public String getDol() {
		return dol;
	}

	public void setDol(String dol) {
		this.dol = dol;
	}

	public Case getCases() {
		return cases;
	}

	public void setCases(Case cases) {
		this.cases = cases;
	}

//	public Map<String, String> getExtpadv() {
//		return extpadv;
//	}
//
//	public void setExtpadv(Map<String, String> extpadv) {
//		this.extpadv = extpadv;
//	}
//
//	public Map<String, String> getExtradv() {
//		return extradv;
//	}
//
//	public void setExtradv(Map<String, String> extradv) {
//		this.extradv = extradv;
//	}	 
}




