package com.lytx.common.newrelic;

public class InsightMetricConfiguration {
	String apiKey;
	String applicationId;
	String transactionType;
	
	public InsightMetricConfiguration(String pod, String application, String transactionType) {
		this.apiKey = MetricConstants.POD_INSIGHT_API_KEY_MAP.get(pod);
	}
}
