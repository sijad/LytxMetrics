package com.lytx.common.newrelic;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MetricResponse {
	
	@JsonProperty("metric_data")
	private MetricData metricData;
	
	public MetricResponse() {
		
	}

	public MetricData getMetricData() {
		return metricData;
	}

	public void setMetricData(MetricData metricData) {
		this.metricData = metricData;
	}
	
}
