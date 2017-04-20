package com.lytx.common.newrelic;

import java.util.List;

public class DOLMetricSummaryAttributes {
	private long count;
	private float average;
	private DOLMetricSummaryPercentiles percentiles;

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public float getAverage() {
		return average;
	}

	public void setAverage(float average) {
		this.average = average;
	}

	public DOLMetricSummaryPercentiles getPercentiles() {
		return percentiles;
	}

	public void setPercentiles(DOLMetricSummaryPercentiles percentiles) {
		this.percentiles = percentiles;
	}
	
	public String getPercentile() {
		return this.percentiles.getPercentile();
	}
	
	public float getPercentileState() {
		if (percentiles == null) {
			return 0;
		} else {
		return this.percentiles.getPercentileStat();
		}
	}
	
	
}
