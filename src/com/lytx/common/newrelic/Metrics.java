package com.lytx.common.newrelic;

import java.util.List;

public class Metrics {
	private String name;
	private List<Timeslices> timeslices;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Timeslices> getTimeslices() {
		return timeslices;
	}
	public void setTimeslices(List<Timeslices> timeslices) {
		this.timeslices = timeslices;
	}
	

}
