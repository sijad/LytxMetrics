package com.lytx.common.newrelic;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
public class MetricData {
	private String from;
	private String to;
	
	@JsonProperty("metrics_not_found")
	List<String> metricsNotFound;
	@JsonProperty("metrics_found")
	List<String> metricsFound;
	List<Metrics> metrics;
	
	public MetricData() {
		
	}
	
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
	public List<String> getMetricsNotFound() {
		return metricsNotFound;
	}
	public void setMetricsNotFound(List<String> metricsNotFound) {
		this.metricsNotFound = metricsNotFound;
	}
	public List<String> getMetricsFound() {
		return metricsFound;
	}
	public void setMetricsFound(List<String> metricsFound) {
		this.metricsFound = metricsFound;
	}
	public List<Metrics> getMetrics() {
		return metrics;
	}
	public void setMetrics(List<Metrics> metrics) {
		this.metrics = metrics;
	}
	
}
