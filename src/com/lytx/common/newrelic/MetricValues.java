package com.lytx.common.newrelic;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MetricValues {
	@JsonProperty("average_call_time")
	private int averageCallTime;
	@JsonProperty("average_response_time")
	private int averageResponseTime;
	@JsonProperty("requests_per_minute")
	private float requestsPerMinute;
	@JsonProperty("call_count")
	private int callCount;
	@JsonProperty("min_call_time")
	private int minCallTime;
	@JsonProperty("max_call_time")
	private int maxCallTime;
	@JsonProperty("total_call_time")
	private int totalCallTime;
	private float throughput;
	@JsonProperty("standard_deviation")
	private int standardDeviation;
	
	public int getAverageCallTime() {
		return averageCallTime;
	}
	public void setAverageCallTime(int averageCallTime) {
		this.averageCallTime = averageCallTime;
	}
	public int getAverageResponseTime() {
		return averageResponseTime;
	}
	public void setAverageResponseTime(int averageResponseTime) {
		this.averageResponseTime = averageResponseTime;
	}
	public float getRequestsPerMinute() {
		return requestsPerMinute;
	}
	public void setRequestsPerMinute(float requestsPerMinute) {
		this.requestsPerMinute = requestsPerMinute;
	}
	public int getCallCount() {
		return callCount;
	}
	public void setCallCount(int callCount) {
		this.callCount = callCount;
	}
	public int getMinCallTime() {
		return minCallTime;
	}
	public void setMinCallTime(int minCallTime) {
		this.minCallTime = minCallTime;
	}
	public int getMaxCallTime() {
		return maxCallTime;
	}
	public void setMaxCallTime(int maxCallTime) {
		this.maxCallTime = maxCallTime;
	}
	public int getTotalCallTime() {
		return totalCallTime;
	}
	public void setTotalCallTime(int totalCallTime) {
		this.totalCallTime = totalCallTime;
	}
	public float getThroughput() {
		return throughput;
	}
	public void setThroughput(float throughput) {
		this.throughput = throughput;
	}
	public int getStandardDeviation() {
		return standardDeviation;
	}
	public void setStandardDeviation(int standardDeviation) {
		this.standardDeviation = standardDeviation;
	}
	
	
}
