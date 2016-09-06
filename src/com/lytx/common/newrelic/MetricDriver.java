package com.lytx.common.newrelic;

import javax.net.ssl.SSLContext;
import java.util.HashMap;
import java.util.Vector;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.net.URLEncoder;
import java.text.DateFormat;


import org.glassfish.jersey.client.JerseyClientBuilder;

public class MetricDriver {
	public static final String WEB_TRANSACTION_TYPE = "WebTransaction/WCF";
	public static final String GET_SAFETY_SUMMARY_SVC = "%2FWCF%2FDriveCam.HindSight.Services.Contracts.IDashboardService.GetSafetySummary";
	public static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";
	MetricsParameters podAMetricsParameters;
	MetricsParameters podBMetricsParameters;
	HashMap<String, MetricResponse> metricResponseListPodA;
	HashMap<String, MetricResponse> metricResponseListPodB;
	InsightMetricResponse insightMetricResponse;
	
	public MetricDriver(MetricsParameters podAMetricsParameters, MetricsParameters podBMetricsParameters) {
		this.podAMetricsParameters = podAMetricsParameters;
		this.podBMetricsParameters = podBMetricsParameters;
	}
	
	public MetricsParameters getPodAMetricsParameters() {
		return podAMetricsParameters;
	}

	public void setPodAMetricsParameters(MetricsParameters podAMetricsParameters) {
		this.podAMetricsParameters = podAMetricsParameters;
	}

	public MetricsParameters getPodBMetricsParameters() {
		return podBMetricsParameters;
	}

	public void setPodBMetricsParameters(MetricsParameters podBMetricsParameters) {
		this.podBMetricsParameters = podBMetricsParameters;
	}

	public HashMap<String, MetricResponse> getMetricResponseListPodA() {
		return metricResponseListPodA;
	}

	public void setMetricResponseListPodA(HashMap<String,MetricResponse> metricResponseListPodA) {
		this.metricResponseListPodA = metricResponseListPodA;
	}

	public HashMap<String, MetricResponse> getMetricResponseListPodB() {
		return metricResponseListPodB;
	}

	public void setMetricResponseListPodB(HashMap<String,MetricResponse> metricResponseListPodB) {
		this.metricResponseListPodB = metricResponseListPodB;
	}

	public static void main(String [] args) throws Exception {
		MetricsParameters podAMetricsParameters= new MetricsParameters(args[0], args[1], args[2]);
		MetricsParameters podBMetricsParameters= new MetricsParameters(args[3], args[4], args[5]);
		
		MetricDriver metricDriver = new MetricDriver(podAMetricsParameters, podBMetricsParameters);
		
		JerseyWithSSL jerseyWithSSL = new JerseyWithSSL();
		Client client = jerseyWithSSL.initClient();
/*		
		for (String metricName : metricDriver.getMetrics(client, podAMetricsParameters, WEB_TRANSACTION_TYPE)) {
			System.out.println(metricName);
		}
		
		metricDriver.log("Start: " + podAMetricsParameters.getPodName());
		metricDriver.setMetricResponseListPodA(metricDriver.getMetricData(client, metricDriver.getMetrics(client, metricDriver.getPodAMetricsParameters(), WEB_TRANSACTION_TYPE), metricDriver.getPodAMetricsParameters()));
		metricDriver.log("End: " + podAMetricsParameters.getPodName());
		metricDriver.log("Start: " + podBMetricsParameters.getPodName());
		metricDriver.setMetricResponseListPodB(metricDriver.getMetricData(client, metricDriver.getMetrics(client, metricDriver.getPodBMetricsParameters(), WEB_TRANSACTION_TYPE), metricDriver.getPodBMetricsParameters()));
		metricDriver.log("End: " + podBMetricsParameters.getPodName());
		HashMap<String,MetricSummary> metricList = metricDriver.getMetricSummary(metricDriver.metricResponseListPodA);
		metricDriver.printMetricList(metricList);
*/		
		metricDriver.runInsightQuery(client, podAMetricsParameters);
		client.close();
//		SSLContext ctx = SSLContext.getInstance("SSL");
//		ctx.init(null,new TrustManager[]{new X509ExtendedTrustManager(null)},null);
//		ctx.init(null, myTrustManager, null);
		
//		Client client = JerseyClientBuilder.newClient();
/* Get metric names
*/
//		String myString = response.readEntity(String.class);
//		System.out.println("JSON: " + myString);
//		System.out.println("JSON: " + response.readEntity(String.class));
	}
	private void log(String name) {
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		Date date = new Date();
		System.out.println(name + ": " + dateFormat.format(date));
	}
	private void printMetricList(HashMap<String, MetricSummary> metricList) {
		for (String key : metricList.keySet()) {
			System.out.println("Metric: " + key);
			System.out.println(metricList.get(key).getAverageCallTimeDiff());
			System.out.println(metricList.get(key).getAverageResponseTimeDiff());
			System.out.println(metricList.get(key).getCallCountDiff());
			System.out.println(metricList.get(key).getAverageCallTimeDiff());
			System.out.println(metricList.get(key).getAverageCallTimeDiff());
			System.out.println(metricList.get(key).getAverageCallTimeDiff());
			System.out.println(metricList.get(key).getAverageCallTimeDiff());
			System.out.println(metricList.get(key).getAverageCallTimeDiff());
			System.out.println(metricList.get(key).getAverageCallTimeDiff());
		}
	}
	private  Vector<String> getMetrics(Client client, MetricsParameters metricsParameters, String transactionType) {
		Vector<String> metricCol = new Vector<String>();
		String targetURL = "https://api.newrelic.com/v2/applications/" + metricsParameters.getApplicationId() + "/metrics.json";
//		WebTarget webTarget = client.target("https://api.newrelic.com/v2/applications/19855889/metrics.json");
		WebTarget webTarget = client.target(targetURL);
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		invocationBuilder.header(MetricConstants.API_KEY, metricsParameters.getApiKey());
		Response response = invocationBuilder.get();
		System.out.println("Status: " + response.getStatus() + response.getStatusInfo());
		MetricList metricList = response.readEntity(new GenericType<MetricList>(){});

		for (Metric metric : metricList.getMetrics()) {
			if (metric.getName().startsWith(transactionType)) {
				metricCol.add(metric.getName());
			}
		}
	
		response.close();
		return metricCol;
	}
	private void runInsightQuery(Client client, MetricsParameters metricsParameters) {
		
		String nrql = "SELECT count(*) FROM Transaction where name = 'WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IDashboardService.GetSafetySummary' and duration > 30 SINCE '2016-08-31 00:00:00-0500' until '2016-09-01 00:00:00-0500' TIMESERIES";
		String targetURL = "https://insights-api.newrelic.com/v1/accounts/828400/query?nrql=";
//		WebTarget webTarget = client.target("https://api.newrelic.com/v2/applications/19855889/metrics.json");
		System.out.println("Request before encoding: " + nrql);
		try  {
//		targetURL = URLEncoder.encode(targetURL,"UTF-8");
//		targetURL = URLEncoder.encode(targetURL,"US-ASCII");
		nrql = URLEncoder.encode(nrql,"ISO-8859-1");
		} catch (Exception e) {
			System.out.println("Could not encode");
		} 
		System.out.println("Request after encoding: " + nrql);
		targetURL = targetURL + nrql;
		WebTarget webTarget = client.target(targetURL);
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		invocationBuilder.header("X-QUERY-KEY", "QTCpvoztYPFi1cz7r946E6QNaQLOlEuP");
		Response response = invocationBuilder.get();
		System.out.println("Insight API Status: " + response.getStatus() + response.getStatusInfo());
	//	String stringResult = response.readEntity(String.class);
	//	System.out.println("JSON: " + stringResult);
		insightMetricResponse = response.readEntity(new GenericType<InsightMetricResponse>(){});

	}
	
	private HashMap<String,MetricSummary> getMetricSummary(HashMap<String,MetricResponse> list) {
		HashMap<String,MetricSummary> differenceList = new HashMap<String,MetricSummary>();
		float averageCallTime = 0;
		float averageResponseTime = 0;
		int callCount = 0;
		int maxCallTime = 0;
		int minCallTime = 0;
		float requestsPerMinute = 0;
		float throughput = 0;
		int totalCallTime = 0;
		
		for (String key : list.keySet())  {
				for (Timeslices timeslices : list.get(key).getMetricData().getMetrics().get(0).getTimeslices()) {
					averageCallTime = averageCallTime + timeslices.getMetricValues().getAverageCallTime()/timeslices.getMetricValues().getThroughput();
					averageResponseTime = averageResponseTime + timeslices.getMetricValues().getAverageResponseTime()/timeslices.getMetricValues().getThroughput();
					callCount = callCount + timeslices.getMetricValues().getCallCount();
					if (maxCallTime < timeslices.getMetricValues().getMaxCallTime()) {
						maxCallTime = timeslices.getMetricValues().getMaxCallTime();
					}
					if (minCallTime > timeslices.getMetricValues().getMinCallTime()) {
						minCallTime = timeslices.getMetricValues().getMinCallTime();
					}
					requestsPerMinute = requestsPerMinute + timeslices.getMetricValues().getRequestsPerMinute();
					throughput = throughput + timeslices.getMetricValues().getThroughput();
					totalCallTime = totalCallTime + timeslices.getMetricValues().getTotalCallTime();
				}
			
			differenceList.put(key, new MetricSummary(	averageCallTime,
															averageResponseTime,
															requestsPerMinute,
															callCount,
															minCallTime,
															maxCallTime,
															totalCallTime,
															throughput,
															0));
		}
		return differenceList;
	}
	
	private  HashMap<String,MetricResponse> getMetricData(Client client, Vector<String> metricList, MetricsParameters metricsParameters) {
//		WebTarget webTarget = client.target("https://api.newrelic.com/v2/applications/19855889/metrics/data.json?names%5B%5D=WebTransaction%2FWCF%2FDriveCam.HindSight.Services.Contracts.IDashboardService.GetSafetySummary&from=2016-08-25T01%3A29%3A00%2B00%3A00&to=2016-08-25T01%3A59%3A00%2B00%3A00");
//		WebTarget webTarget = client.target("https://api.newrelic.com/v2/applications/19855889/metrics/data.json?names%5B%5D=WebTransaction%2FWCF%2FDriveCam.HindSight.Services.Contracts.IDashboardService.GetSafetySummary&from=2016-08-25T01%3A29%3A00%2B00%3A00&to=2016-08-25T01%3A59%3A00%2B00%3A00");
		HashMap<String,MetricResponse> metricResponseList = new HashMap<String, MetricResponse>();
		
		for (String metricName : metricList) {
			
			WebTarget webTarget = client.target(formatMetricsURL(metricName,metricsParameters.getApplicationId(), metricsParameters.getFrom(),metricsParameters.getTo()));
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			invocationBuilder.header(MetricConstants.API_KEY, metricsParameters.getApiKey());
			Response response = invocationBuilder.get();
//			MetricResponse metricResponse = response.readEntity(new GenericType<MetricResponse>(){});
			metricResponseList.put(metricName, response.readEntity(new GenericType<MetricResponse>(){}));
/*			
			for (Metrics metrics : metricResponse.getMetricData().getMetrics()) {
				System.out.println(metrics.getName());
				for (Timeslices timeslices : metrics.getTimeslices()) {
					System.out.println(timeslices.getFrom());
					System.out.println(timeslices.getTo());
					System.out.println(timeslices.getMetricValues().getAverageCallTime());
					System.out.println(timeslices.getMetricValues().getAverageResponseTime());
					System.out.println(timeslices.getMetricValues().getCallCount());
					System.out.println(timeslices.getMetricValues().getMaxCallTime());
					System.out.println(timeslices.getMetricValues().getMinCallTime());
					System.out.println(timeslices.getMetricValues().getRequestsPerMinute());
					System.out.println(timeslices.getMetricValues().getStandardDeviation());
					System.out.println(timeslices.getMetricValues().getThroughput());
					System.out.println(timeslices.getMetricValues().getTotalCallTime());
				}
			}
*/
			response.close();
		}
		/*
		for (String metricNotFound : metricResponse.getMetricData().getMetricsNotFound()) {
			System.out.println(metricNotFound);
		}
		System.out.println("Metrics Found");
		for (String metricFound : metricResponse.getMetricData().getMetricsFound()) {
			System.out.println(metricFound);
		}
		for (Metrics metrics : metricResponse.getMetricData().getMetrics()) {
			System.out.println(metrics.getName());
			for (Timeslices timeslices : metrics.getTimeslices()) {
				System.out.println(timeslices.getFrom());
				System.out.println(timeslices.getTo());
				System.out.println(timeslices.getMetricValues().getAverageCallTime());
				System.out.println(timeslices.getMetricValues().getAverageResponseTime());
				System.out.println(timeslices.getMetricValues().getCallCount());
				System.out.println(timeslices.getMetricValues().getMaxCallTime());
				System.out.println(timeslices.getMetricValues().getMinCallTime());
				System.out.println(timeslices.getMetricValues().getRequestsPerMinute());
				System.out.println(timeslices.getMetricValues().getStandardDeviation());
				System.out.println(timeslices.getMetricValues().getThroughput());
				System.out.println(timeslices.getMetricValues().getTotalCallTime());
			}
		}
		*/
		System.out.println("Got " + metricResponseList.size());
		return metricResponseList;
	}
	private static String formatMetricsURL(String serviceName, String applicationId, String from, String to) {
		StringBuffer url = new StringBuffer();
		url.append("https://api.newrelic.com/v2/applications/");
		url.append(applicationId);
		url.append("/metrics/data.json");
		url.append("?");
		url.append("names%5B%5D=");
//		url.append(transactionType);
		url.append(serviceName);
		url.append("&from=");
		url.append(from);
		url.append("&to=");
		url.append(to);
		
		return url.toString();
		
	}
}
