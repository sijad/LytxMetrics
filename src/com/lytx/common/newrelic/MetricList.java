package com.lytx.common.newrelic;

import java.util.List;

public class MetricList {
	List<Metric> metrics;

	public MetricList() {
		
	}

	public List<Metric> getMetrics() {
		return metrics;
	}

	public void setMetrics(List<Metric> metricList) {
		this.metrics = metricList;
	}
	
	

}
