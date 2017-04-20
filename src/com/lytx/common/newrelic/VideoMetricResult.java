package com.lytx.common.newrelic;

import java.util.List;

public class VideoMetricResult {
	List<VideoMetricEvent> events;

	public List<VideoMetricEvent> getEvents() {
		return events;
	}

	public void setEvents(List<VideoMetricEvent> events) {
		this.events = events;
	}
	
}
