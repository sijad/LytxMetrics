package com.lytx.common.newrelic;

public class InsightTxnMetricSummary {
	private String transactionName;
	private String podName;
	private InsightMetricResponse metricRepesponse;
	private String durationRange;

	public String getTransactionName() {
		return transactionName;
	}

	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}
	
	public String getPodName() {
		return podName;
	}
	
	public void setPodName(String podName) {
		this.podName = podName;
	}
	
	public InsightMetricResponse getMetricRepesponse() {
		return metricRepesponse;
	}
	
	public void setMetricRepesponse(InsightMetricResponse metricRepesponse) {
		this.metricRepesponse = metricRepesponse;
	}

	public String getDurationRange() {
		return durationRange;
	}

	public void setDuration(String durationRange) {
		this.durationRange = durationRange;
	}
	

}
