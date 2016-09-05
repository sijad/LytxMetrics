package com.lytx.common.newrelic;

public class Timeslices {
	private String from;
	private String to;
	private MetricValues metricValues;
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public MetricValues getMetricValues() {
		return metricValues;
	}
	public void setValues(MetricValues metricValues) {
		this.metricValues = metricValues;
	}
	
}
