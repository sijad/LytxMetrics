package com.lytx.common.newrelic;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class DOLMetricSummaryPercentiles {

	private String percentile;
	private float percentileStat;
	
	@JsonProperty("90")
	private float percentile90;

	@JsonProperty("95")
	private float percentile95;

	public float getPercentile90() {
		return percentile90;
	}

	public void setPercentile90(float percentile90) {
		this.percentile = "90th";
		this.percentileStat = percentile90;
	}

	public float getPercentile95() {
		return percentile95;
	}

	public void setPercentile95(float percentile95) {
		this.percentile = "95th";
		this.percentileStat = percentile95;
	}

	public String getPercentile() {
		return percentile;
	}

	public void setPercentile(String percentile) {
		this.percentile = percentile;
	}

	public float getPercentileStat() {
		return percentileStat;
	}

	public void setPercentileStat(float percentileStat) {
		this.percentileStat = percentileStat;
	}

	
}
