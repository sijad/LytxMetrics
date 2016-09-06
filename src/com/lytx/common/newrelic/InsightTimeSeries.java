package com.lytx.common.newrelic;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InsightTimeSeries {

	@JsonProperty("results")
	private List<InsightResults> insightResults;
	long beginTimeSeconds;
	long endTimeSeconds;

	public List<InsightResults> getInsightResults() {
		return insightResults;
	}

	public void setInsightResults(List<InsightResults> insightResults) {
		this.insightResults = insightResults;
	}

	public long getBeginTimeSeconds() {
		return beginTimeSeconds;
	}

	public void setBeginTimeSeconds(long beginTimeSeconds) {
		this.beginTimeSeconds = beginTimeSeconds;
	}

	public long getEndTimeSeconds() {
		return endTimeSeconds;
	}

	public void setEndTimeSeconds(long endTimeSeconds) {
		this.endTimeSeconds = endTimeSeconds;
	}

	
}
