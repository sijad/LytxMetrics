package com.lytx.common.newrelic;

import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Vector;
import java.util.Date;
import static java.nio.file.StandardOpenOption.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.io.*;
import java.util.*;
import java.nio.file.Path;

public class InsightMetricDriver {
	MetricsParameters metricsParameters;
	public static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";
	
	public InsightMetricDriver(MetricsParameters parameters) {
		this.metricsParameters = parameters;
	}
	
	public InsightMetricDriver() {
		
	}
	
	public static void main(String [] args) throws Exception {
		
		InsightMetricDriver metricDriver = new InsightMetricDriver();
		
		JerseyWithSSL jerseyWithSSL = new JerseyWithSSL();
		Client client = jerseyWithSSL.initClient();
		Vector<String> metricList = metricDriver.getMetrics(client, MetricConstants.POD1_API_KEY_VALUE, MetricConstants.POD1_HBS_APPLICATION_ID, MetricConstants.WEB_TRANSACTION_WCF_TYPE);
		metricList.addAll(metricDriver.getMetrics(client, MetricConstants.POD1_API_KEY_VALUE, MetricConstants.POD1_ARC_APPLICATION_ID, MetricConstants.WEB_TRANSACTION_JS_TYPE));
		metricList.addAll(metricDriver.getMetrics(client, MetricConstants.POD1_API_KEY_VALUE, MetricConstants.POD1_VIDEO_APPLICATION_ID, MetricConstants.WEB_TRANSACTION_ASP_TYPE));
		System.out.println("Metrics count: " + metricList.size());
		
		Vector<InsightTxnMetricSummary> txnMetricSummaryList = metricDriver.runInsightQuery(client, metricList, args[0], args[1]);
		metricDriver.outputInsightMetrics(txnMetricSummaryList);
		client.close();

	}
	
	private  Vector<String> getMetrics(Client client, String apiKey, String applicationId, String transactionType) {
		Vector<String> metricCol = new Vector<String>();
//		String targetURL = "https://api.newrelic.com/v2/applications/" + MetricConstants.ARC_APPLICATION_ID + "/metrics.json";
//		String targetURL = "https://api.newrelic.com/v2/applications/" + MetricConstants.POD1_VIDEO_APPLICATION_ID + "/metrics.json";
		String targetURL = "https://api.newrelic.com/v2/applications/" + applicationId + "/metrics.json";
		System.out.println("URL:" + targetURL);
//		WebTarget webTarget = client.target("https://api.newrelic.com/v2/applications/19855889/metrics.json");
		WebTarget webTarget = client.target(targetURL);
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		invocationBuilder.header(MetricConstants.API_KEY, apiKey);
		Response response = invocationBuilder.get();
		System.out.println("Status: " + response.getStatus() + response.getStatusInfo());
		MetricList metricList = response.readEntity(new GenericType<MetricList>(){});

		for (Metric metric : metricList.getMetrics()) {
			if (metric.getName().startsWith(transactionType)) {
	//			System.out.println("Found: " + metric.getName());
				metricCol.add(metric.getName());
			} else {
	//			System.out.println("Transaction type not found: " + metric.getName());
			}
		}
	
		response.close();
		return metricCol;
	}

	private Vector<InsightTxnMetricSummary> runInsightQuery(Client client, Vector<String> nameList, String from, String to) {
		Vector<InsightTxnMetricSummary> metricResponseList = new Vector<InsightTxnMetricSummary>();
		int counter = 1;
		for (String name : nameList) {
			log("Getting " + counter + ": " + name);
			for (int i = 0; i < MetricConstants.DURATION_GROUP_START.length; i++) {
				int durationStart = MetricConstants.DURATION_GROUP_START[i];
				int durationEnd = MetricConstants.DURATION_GROUP_END[i];
				for (String pod : MetricConstants.POD_INSIGHT_API_KEY_MAP.keySet()) {
					InsightTxnMetricSummary insightTxnMetricSummary = new InsightTxnMetricSummary();
					insightTxnMetricSummary.setPodName(pod);
					insightTxnMetricSummary.setTransactionName(name);
					insightTxnMetricSummary.setDuration(durationStart + "-" + durationEnd);
					String nrql = "SELECT count(*) FROM Transaction where name = '" + name + "' and duration > " + durationStart + " and duration <= " + durationEnd + " SINCE " + from +  " until " + to + " TIMESERIES";
					String targetURL = "https://insights-api.newrelic.com/v1/accounts/" + MetricConstants.POD_INSIGHT_APPL_MAP.get(pod) + "/query?nrql=";
		//			System.out.println("Request before encoding: " + nrql);
					try  {
						nrql = URLEncoder.encode(nrql,"ISO-8859-1");
						} catch (Exception e) {
							System.out.println("Could not encode");
						} 
		//			System.out.println("Request after encoding: " + nrql);
					targetURL = targetURL + nrql;
					WebTarget webTarget = client.target(targetURL);
					Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
					invocationBuilder.header("X-QUERY-KEY", MetricConstants.POD_INSIGHT_API_KEY_MAP.get(pod));
					Response response = invocationBuilder.get();
		//			System.out.println("Insight API Status: " + response.getStatus() + response.getStatusInfo());
					InsightMetricResponse insightMetricResponse = response.readEntity(new GenericType<InsightMetricResponse>(){});
					insightTxnMetricSummary.setMetricRepesponse(insightMetricResponse);
					metricResponseList.add(insightTxnMetricSummary);
				}
			}
			counter++;
		}
		return metricResponseList;
	}

	private  void outputInsightMetrics(Vector<InsightTxnMetricSummary> responseList) throws Exception {
		FileWriter fw = new FileWriter("output/metrics.txt");
		fw.write("Pod;Transaction;Duration Range;To;From;Count\n");
		
		for (InsightTxnMetricSummary summary : responseList) {
//			summary.getMetricRepesponse().getTotal().getInsightTotalResults().get(0).getCount();
//			System.out.println("Count: " + summary.getMetricRepesponse().getTotal().getInsightTotalResults().get(0).getCount());
			for (InsightTimeSeries timeSeries : summary.getMetricRepesponse().getTimeSeries()) {
				StringBuffer line = new StringBuffer();
				line.append(summary.getPodName() + ";");
				line.append(summary.getTransactionName() + ";");
				line.append(summary.getDurationRange() + " Seconds;");
				DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
				line.append(dateFormat.format(new Date(timeSeries.getBeginTimeSeconds()*1000)) + ";");
				line.append(dateFormat.format(new Date(timeSeries.getEndTimeSeconds()*1000)) + ";");
				line.append(timeSeries.getInsightResults().get(0).getCount());
				fw.write(line.toString()+"\n");
			}
		}
		fw.close();
	}

	private void log(String message) {
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		Date date = new Date();
		System.out.println(dateFormat.format(date) + ": " + message  );
	}

}
