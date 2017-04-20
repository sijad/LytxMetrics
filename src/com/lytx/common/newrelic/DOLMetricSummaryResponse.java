package com.lytx.common.newrelic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

public class DOLMetricSummaryResponse {

	private DOLMetricSummaryResult current;
	private DOLMetricSummaryResult previous;
	private String metricType;
	private String category;
	private String pod;
	
	@JsonIgnore
	private DOLMetricSummaryMetadata metadata;
	
	@JsonIgnore
	private DOLMetricSummaryPerformanceStats performanceStats;

	public DOLMetricSummaryResult getCurrent() {
		return current;
	}

	public void setCurrent(DOLMetricSummaryResult current) {
		this.current = current;
	}

	public DOLMetricSummaryResult getPrevious() {
		return previous;
	}

	public void setPrevious(DOLMetricSummaryResult previous) {
		this.previous = previous;
	}

	public DOLMetricSummaryMetadata getMetadata() {
		return metadata;
	}

	public void setMetadata(DOLMetricSummaryMetadata metadata) {
		this.metadata = metadata;
	}

	public DOLMetricSummaryPerformanceStats getPerformanceStats() {
		return performanceStats;
	}

	public void setPerformanceStats(DOLMetricSummaryPerformanceStats performanceStats) {
		this.performanceStats = performanceStats;
	}

	public String getMetricType() {
		return metricType;
	}

	public void setMetricType(String metricType) {
		this.metricType = metricType;
	}

	public String getPod() {
		return pod;
	}

	public void setPod(String pod) {
		this.pod = pod;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
