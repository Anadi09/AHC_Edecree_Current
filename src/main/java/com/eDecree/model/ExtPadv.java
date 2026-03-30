package com.eDecree.model;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="extpadv")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExtPadv {

	 @XmlElement(name="p1")
	 private String p1;

	public String getP1() {
		return p1;
	}

	public void setP1(String p1) {
		this.p1 = p1;
	}
	 
	 
}
