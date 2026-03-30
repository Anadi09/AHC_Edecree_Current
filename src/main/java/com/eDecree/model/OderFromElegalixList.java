package com.eDecree.model;

import java.util.ArrayList;
import java.util.List;

public class OderFromElegalixList {
	
	private List<OrderFromElegalix> orderFromElegalixList;
	
	
	public OderFromElegalixList() {
		orderFromElegalixList =new ArrayList<>();;
	}


	public List<OrderFromElegalix> getOrderFromElegalixList() {
		return orderFromElegalixList;
	}


	public void setOrderFromElegalixList(List<OrderFromElegalix> orderFromElegalixList) {
		this.orderFromElegalixList = orderFromElegalixList;
	}

	
	
}
