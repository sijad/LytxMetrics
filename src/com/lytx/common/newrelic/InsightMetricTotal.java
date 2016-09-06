package com.lytx.common.newrelic;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InsightMetricTotal {

	@JsonProperty("results")
	private List<InsightResults> insightTotalResults;

	public List<InsightResults> getInsightTotalResults() {
		return this.insightTotalResults;
	}
	
	public void setInsightTotalResults(List<InsightResults> insightTotalResults) {
		this.insightTotalResults = insightTotalResults;
	}

	
	
}
