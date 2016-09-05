package com.lytx.common.newrelic;

import java.util.List;

public class Metric {
	String name;
	List<Values> valuesList;

	public Metric() {
		
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public List<Values> getValues() {
		return valuesList;
	}
	
	public void setValues(List<Values> valuesList) {
		this.valuesList = valuesList;
	}

}
