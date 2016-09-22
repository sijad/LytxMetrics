package com.lytx.common.newrelic;

import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
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
import java.sql.*; 

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
//		Vector<String> metricList = metricDriver.getMetrics(client, MetricConstants.POD1_API_KEY_VALUE, MetricConstants.POD1_VIDEO_APPLICATION_ID, MetricConstants.WEB_TRANSACTION_ASP_TYPE);
//		metricDriver.outputToElastic(client, metricList, args[0], args[1]);

		metricList.addAll(metricDriver.getMetrics(client, MetricConstants.POD1_API_KEY_VALUE, MetricConstants.POD1_ARC_APPLICATION_ID, MetricConstants.WEB_TRANSACTION_JS_TYPE));
		metricList.addAll(metricDriver.getMetrics(client, MetricConstants.POD1_API_KEY_VALUE, MetricConstants.POD1_VIDEO_APPLICATION_ID, MetricConstants.WEB_TRANSACTION_ASP_TYPE));
		System.out.println("Metrics count: " + metricList.size());
		
		Vector<InsightTxnMetricSummary> txnMetricSummaryList = metricDriver.runInsightQuery(client, metricList, args[0], args[1]);

//		metricDriver.outputInsightMetrics(txnMetricSummaryList);
//		metricDriver.insertInsightMetrics(new Vector<InsightTxnMetricSummary>());
		metricDriver.insertInsightMetrics(txnMetricSummaryList);

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

	private Vector<InsightTxnMetricSummary> runInsightQuery(Client client, Vector<String> nameList, String from, String to) throws Exception {
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
	private void outputToElastic(Client client, Vector<String> nameList, String from, String to) throws Exception {
		Vector<InsightTxnMetricSummary> metricResponseList = new Vector<InsightTxnMetricSummary>();
		JerseyWithSSL jerseyWithSSL = new JerseyWithSSL();
		Client elasticClient = jerseyWithSSL.initClient();
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
					String stuff = response.readEntity(String.class);
					WebTarget elasticTarget = elasticClient.target("http://127.0.0.1:9200//metrics/lytx/");
					Invocation.Builder invoElas = elasticTarget.request(MediaType.APPLICATION_JSON);
					Response response2 = invoElas.post(Entity.entity(stuff, MediaType.APPLICATION_JSON),Response.class);
//					InsightMetricResponse insightMetricResponse = response.readEntity(new GenericType<InsightMetricResponse>(){});
//					insightTxnMetricSummary.setMetricRepesponse(insightMetricResponse);
//					metricResponseList.add(insightTxnMetricSummary);
				}
			}
			counter++;
		}
		//return metricResponseList;
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

	private void insertInsightMetrics(Vector<InsightTxnMetricSummary> responseList) {
	      String connectionUrl = "jdbc:sqlserver://engdb2\\sql2012;databaseName=Metrics;integratedSecurity=true;";  
	    	  
	    	      // Declare the JDBC objects.  
	    	      Connection con = null;  
	    	      PreparedStatement stmt = null;  
	    	      ResultSet rs = null;  
	    	  
	    	      try {  
	    	         // Establish the connection.  
	    	         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	    	         con = DriverManager.getConnection(connectionUrl);  
	    	         
	    	         for (InsightTxnMetricSummary summary : responseList) {
	    	 			for (InsightTimeSeries timeSeries : summary.getMetricRepesponse().getTimeSeries()) {
			    	         // Create and execute an SQL statement that returns some data.  
			    	         String SQL = "insert into insightMetrics (podName,transactionName,durationRange,fromDate,toDate, totalCount) values (?,?,?,?,?,?);";
			    	         stmt = con.prepareStatement(SQL);
			    	         stmt.setString(1, summary.getPodName());
			    	         stmt.setString(2, summary.getTransactionName());
			    	         stmt.setString(3, summary.getDurationRange());
			    	         stmt.setDate(4, new java.sql.Date(timeSeries.getBeginTimeSeconds()));
			    	         stmt.setDate(5, new java.sql.Date(timeSeries.getEndTimeSeconds()));
			    	         stmt.setLong(6, timeSeries.getInsightResults().get(0).getCount());
			    	         stmt.execute();  
			    	
	    	 			}
	    	 			con.commit();
	    	         }
	    	         // Iterate through the data in the result set and display it.  
//	    	         while (rs.next()) {  
//	    	            System.out.println(rs.getString(4) + "XXX " + rs.getString(6));  
//	    	         }  
	    	         System.out.println("Finished");
	    	      }  
	    	  
	    	      // Handle any errors that may have occurred.  
	    	      catch (Exception e) {  
	    	         e.printStackTrace();  
	    	      }  
	    	      finally {  
	    	         if (rs != null) try { rs.close(); } catch(Exception e) {}  
	    	         if (stmt != null) try { stmt.close(); } catch(Exception e) {}  
	    	         if (con != null) try { con.close(); } catch(Exception e) {}  
	    	      }  
		
	}
}
