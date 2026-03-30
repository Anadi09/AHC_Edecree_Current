package com.eDecree.model;



import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="record")
@XmlAccessorType(XmlAccessType.FIELD)
public class Record {

	List<Rec> rec;

	public List<Rec> getRec() {
		return rec;
	}

	public void setRec(List<Rec> rec) {
		this.rec = rec;
	}
	
	
}
