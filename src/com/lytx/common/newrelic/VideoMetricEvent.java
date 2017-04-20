package com.lytx.common.newrelic;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VideoMetricEvent {
	private double timestamp;

	@JsonProperty("response_flush_time_ms")
	private String responseFlushTimeMs;
	
	@JsonProperty("directory_name")
	private String directoryName;
	
	private float duration;
	
	public double getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(double timestamp) {
		this.timestamp = timestamp;
	}

	public String getResponseFlushTimeMs() {
		return responseFlushTimeMs;
	}

	public void setResponseFlushTimeMs(String responseFlushTimeMs) {
		this.responseFlushTimeMs = responseFlushTimeMs;
	}

	public String getDirectoryName() {
		return directoryName;
	}

	public void setDirectoryName(String directoryName) {
		this.directoryName = directoryName;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	

}
