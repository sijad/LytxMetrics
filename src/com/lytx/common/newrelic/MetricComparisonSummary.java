package com.lytx.common.newrelic;

import java.util.HashMap;

public class MetricComparisonSummary {
	private String podA;
	private String podB;
	private HashMap<String,MetricSummary> metricList;

	public HashMap<String, MetricSummary> getMetricList() {
		return metricList;
	}

	public void setMetricList(HashMap<String, MetricSummary> metricList) {
		this.metricList = metricList;
	}

	public String getPodA() {
		return podA;
	}

	public void setPodA(String podA) {
		this.podA = podA;
	}

	public String getPodB() {
		return podB;
	}

	public void setPodB(String podB) {
		this.podB = podB;
	}
	
	

}
