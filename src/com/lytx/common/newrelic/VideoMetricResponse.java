package com.lytx.common.newrelic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

public class VideoMetricResponse {
	private List<VideoMetricResult> results;

	@JsonIgnore
	private VideoMetricMetadata metadata;
	
	@JsonIgnore
	private String performanceStats;

	public List<VideoMetricResult> getResults() {
		return results;
	}

	public void setResults(List<VideoMetricResult> videoMetricResult) {
		this.results = videoMetricResult;
	}

	public VideoMetricMetadata getMetadata() {
		return metadata;
	}

	public void setMetadata(VideoMetricMetadata videoMetricMetadata) {
		this.metadata = videoMetricMetadata;
	}

	public String getPerformanceStats() {
		return performanceStats;
	}

	public void setPerformanceStats(String performanceStats) {
		this.performanceStats = performanceStats;
	}
	
	
	

}
