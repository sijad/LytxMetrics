package com.lytx.common.newrelic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

public class InsightMetricResponse {

//	@JsonProperty("total")
	private InsightMetricTotal total;
	private List<InsightTimeSeries> timeSeries;
//	@JsonProperty("metadata")
	@JsonIgnore
	private InsightMetadata metadata;
	private long bucketSizeMillis;
//	@JsonProperty("performanceStats")
	@JsonIgnore
	private InsightPerformanceStats performanceStats;
	
	
	public InsightMetricTotal getTotal() {
		return total;
	}

	public void setTotal(InsightMetricTotal total) {
		this.total = total;
	}

	public List<InsightTimeSeries> getTimeSeries() {
		return timeSeries;
	}

	public void setTimeSeries(List<InsightTimeSeries> timeSeries) {
		this.timeSeries = timeSeries;
	}

	public InsightMetadata geMetadata() {
		return metadata;
	}

	public void setMetadata(InsightMetadata insightMetadata) {
		this.metadata = insightMetadata;
	}

	public long getBucketSizeMillis() {
		return bucketSizeMillis;
	}

	public void setBucketSizeMillis(long bucketSizeMillis) {
		this.bucketSizeMillis = bucketSizeMillis;
	}

	public InsightPerformanceStats getPerformanceStats() {
		return performanceStats;
	}

	public void setPerformanceStats(InsightPerformanceStats performanceStats) {
		this.performanceStats = performanceStats;
	}


}
