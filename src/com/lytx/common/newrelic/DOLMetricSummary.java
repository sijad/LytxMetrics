package com.lytx.common.newrelic;

import java.util.HashMap;

public class DOLMetricSummary {
	private String pod;
	private String measureType;
	
	private DOLMetricSummaryAttributes currentMeasure;
	private DOLMetricSummaryAttributes previousMeasure;

	public DOLMetricSummary (String pod, String measureType, DOLMetricSummaryAttributes currentMeasure, DOLMetricSummaryAttributes previousMeasure) {
		this.pod = pod;
		this.measureType = measureType;
		this.currentMeasure = currentMeasure;
		this.previousMeasure = previousMeasure;
	}

	public String getPod() {
		return pod;
	}
	public void setPod(String pod) {
		this.pod = pod;
	}
	public String getMeasureType() {
		return measureType;
	}
	public void setMeasureType(String measureType) {
		this.measureType = measureType;
	}
	public DOLMetricSummaryAttributes getCurrentMeasure() {
		return currentMeasure;
	}
	public void setCurrentMeasure(DOLMetricSummaryAttributes currentMeasure) {
		this.currentMeasure = currentMeasure;
	}
	public DOLMetricSummaryAttributes getPreviousMeasure() {
		return previousMeasure;
	}
	public void setPreviousMeasure(DOLMetricSummaryAttributes previousMeasure) {
		this.previousMeasure = previousMeasure;
	}

	
}
