package com.eDecree.model;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="Case")
@XmlAccessorType(XmlAccessType.FIELD)
public class Case {

	
	 @XmlElement(name="type")
	 private String type;
	 
	 
	 @XmlElement(name="no")
	 private String no;
	 
	 @XmlElement(name="year")
	 private Integer year;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
    
	 
}
