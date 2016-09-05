package com.lytx.common.newrelic;


public class MetricSummary {
	private float averageCallTimeDiff;
	private float averageResponseTimeDiff;
	private float requestsPerMinuteDiff;
	private int callCountDiff;
	private int minCallTimeDiff;
	private int maxCallTimeDiff;
	private int totalCallTimeDiff;
	private float throughputDiff;
	private int standardDeviationDiff;
	
	public MetricSummary(float averageCallTimeDiff,
							float averageResponseTimeDiff,
							float requestsPerMinuteDiff,
							int callCountDiff,
							int minCallTimeDiff,
							int maxCallTimeDiff,
							int totalCallTimeDiff,
							float throughputDiff,
							int standardDeviationDiff) {
		this.averageCallTimeDiff = averageCallTimeDiff;
		this.averageResponseTimeDiff = averageResponseTimeDiff;
		this.requestsPerMinuteDiff = requestsPerMinuteDiff;
		this.callCountDiff = callCountDiff;
		this.minCallTimeDiff = minCallTimeDiff;
		this.maxCallTimeDiff = maxCallTimeDiff;
		this.totalCallTimeDiff = totalCallTimeDiff;
		this.throughputDiff = throughputDiff;
		this.standardDeviationDiff = standardDeviationDiff;
	
	}
							
	public float getAverageCallTimeDiff() {
		return averageCallTimeDiff;
	}
	public void setAverageCallTimeDiff(int averageCallTimeDiff) {
		this.averageCallTimeDiff = averageCallTimeDiff;
	}
	public float getAverageResponseTimeDiff() {
		return averageResponseTimeDiff;
	}
	public void setAverageResponseTimeDiff(int averageResponseTimeDiff) {
		this.averageResponseTimeDiff = averageResponseTimeDiff;
	}
	public float getRequestsPerMinuteDiff() {
		return requestsPerMinuteDiff;
	}
	public void setRequestsPerMinuteDiff(float requestsPerMinuteDiff) {
		this.requestsPerMinuteDiff = requestsPerMinuteDiff;
	}
	public int getCallCountDiff() {
		return callCountDiff;
	}
	public void setCallCountDiff(int callCountDiff) {
		this.callCountDiff = callCountDiff;
	}
	public int getMinCallTimeDiff() {
		return minCallTimeDiff;
	}
	public void setMinCallTimeDiff(int minCallTimeDiff) {
		this.minCallTimeDiff = minCallTimeDiff;
	}
	public int getMaxCallTimeDiff() {
		return maxCallTimeDiff;
	}
	public void setMaxCallTimeDiff(int maxCallTimeDiff) {
		this.maxCallTimeDiff = maxCallTimeDiff;
	}
	public int getTotalCallTimeDiff() {
		return totalCallTimeDiff;
	}
	public void setTotalCallTimeDiff(int totalCallTimeDiff) {
		this.totalCallTimeDiff = totalCallTimeDiff;
	}
	public float getThroughputDiff() {
		return throughputDiff;
	}
	public void setThroughputDiff(float throughputDiff) {
		this.throughputDiff = throughputDiff;
	}
	public int getStandardDeviationDiff() {
		return standardDeviationDiff;
	}
	public void setStandardDeviationDiff(int standardDeviationDiff) {
		this.standardDeviationDiff = standardDeviationDiff;
	}

}
