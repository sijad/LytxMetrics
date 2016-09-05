package com.lytx.common.newrelic;

public class MetricsParameters {
	private String apiKey;
	private String applicationId;
	private String from;
	private String to;
	private String podName;
	
	public MetricsParameters(String podName, String from, String to) {
		this.apiKey = MetricConstants.POD_API_KEY_MAP.get(podName);
		this.applicationId = MetricConstants.POD_APPL_MAP.get(podName);
		this.podName = podName;
		this.from = from;
		this.to = to;
	}
	
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getApplicationId() {
		return this.applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
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

	public String getPodName() {
		return podName;
	}

	public void setPodName(String podName) {
		this.podName = podName;
	}
	
}
