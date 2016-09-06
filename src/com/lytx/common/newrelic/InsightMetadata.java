package com.lytx.common.newrelic;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class InsightMetadata {

	@JsonProperty("eventTypes")
	private List<String> insightEventTypes;
	private String eventType;
	private boolean openEnded;
	private String beginTime;
	private String endTime;
	private String beginTimeMillis;
	private String endTimeMillis;
	private String rawSince;
	private String rawUntil;
	private String rawCompareWith;
	private String guid;
	private String routerGuid;
	
	@JsonIgnore
	private InsightTimeSeries timeSeries;
	
	private List<String> messages;
	private List<InsightContent> content;
	

	public List<String> getInsightEventTypes() {
		return insightEventTypes;
	}

	public void setInsightEventTypes(List<String> insightEventTypes) {
		this.insightEventTypes = insightEventTypes;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public boolean isOpenEnded() {
		return openEnded;
	}

	public void setOpenEnded(boolean openEnded) {
		this.openEnded = openEnded;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getBeginTimeMillis() {
		return beginTimeMillis;
	}

	public void setBeginTimeMillis(String beginTimeMillis) {
		this.beginTimeMillis = beginTimeMillis;
	}

	public String getEndTimeMillis() {
		return endTimeMillis;
	}

	public void setEndTimeMillis(String endTimeMillis) {
		this.endTimeMillis = endTimeMillis;
	}

	public String getRawSince() {
		return rawSince;
	}

	public void setRawSince(String rawSince) {
		this.rawSince = rawSince;
	}

	public String getRawUntil() {
		return rawUntil;
	}

	public void setRawUntil(String rawUntil) {
		this.rawUntil = rawUntil;
	}

	public String getRawCompareWith() {
		return rawCompareWith;
	}

	public void setRawCompareWith(String rawCompareWith) {
		this.rawCompareWith = rawCompareWith;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getRouterGuid() {
		return routerGuid;
	}

	public void setRouterGuid(String routerGuid) {
		this.routerGuid = routerGuid;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public List<InsightContent> getContent() {
		return content;
	}

	public void setContent(List<InsightContent> content) {
		this.content = content;
	}
	
	
}
