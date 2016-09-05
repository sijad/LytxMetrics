package com.lytx.common.newrelic;

import java.util.List;

public class Metric_Data {

		private String from;
		private String to;
		List<String> metrics_not_found;
		List<String> metrics_found;
		List<Metrics> metrics;

		public Metric_Data() {
			
		}
		
		public Metric_Data(String from, String to, List<String> metrics_not_found, List<String> metrics_found, List<Metrics> metrics) {
			this.from = from;
			this.to= to;
			this.metrics_not_found = metrics_not_found;
			this.metrics_found = metrics_found;
			this.metrics = metrics;
		}
		
		public String getFrom() {
			return from;
		}
		public void setFrom(String from) {
			this.from = from;
		}
		public String getTo() {
			return to;
		}
		public void setTo(String to) {
			this.to = to;
		}
		public List<String> getMetrics_not_found() {
			return metrics_not_found;
		}
		public void setMetrics_not_found(List<String> metrics_not_found) {
			this.metrics_not_found = metrics_not_found;
		}
		public List<String> getMetrics_found() {
			return metrics_found;
		}
		public void setMetrics_found(List<String> metrics_found) {
			this.metrics_found = metrics_found;
		}
		public List<Metrics> getMetrics() {
			return metrics;
		}
		public void setMetrics(List<Metrics> metrics) {
			this.metrics = metrics;
		}
		

}
