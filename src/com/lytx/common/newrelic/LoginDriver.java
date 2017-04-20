package com.lytx.common.newrelic;

import java.io.FileWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class LoginDriver {
	public static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";

	public static void main(String [] args) throws Exception {
		
		LoginDriver loginDriver = new LoginDriver();
		
		JerseyWithSSL jerseyWithSSL = new JerseyWithSSL();
		Client client = jerseyWithSSL.initClient();
		Vector<String> metricList = new Vector<String>();
		HashMap<String,DOLMetricSummary> metricSummaryList = null;
		
//		metricSummaryList = dolSummaryDriver.runInsightQuery(client, MetricConstants.POD_LIST);
//		Vector<String> outputLines = dolSummaryDriver.formatMetricSummary(metricSummaryList);
//		dolSummaryDriver.outputMetricSummary(outputLines);
//		dolSummaryDriver.insertInsightMetrics(txnMetricSummaryList, args[0]);


		client.close();

	}
	

	private HashMap<String, DOLMetricSummary> runInsightQuery(Client client, List<String> podList) throws Exception {

		HashMap<String,DOLMetricSummary> metricSummary = new HashMap<String, DOLMetricSummary>();


		int counter = 1;
		Vector<DOLMetricSummaryResponse> dolMetricSummaryResponseList = new Vector<DOLMetricSummaryResponse>();
		for (String pod : podList) {
			HashMap<String,String> nrqlCommandMap = MetricConstants.NRQL_COMMAND_MAP.get(pod);
			for (String metricKey : nrqlCommandMap.keySet()) {
				String nrql = nrqlCommandMap.get(metricKey);
				/*
				InsightTxnMetricSummary insightTxnMetricSummary = new InsightTxnMetricSummary();
				insightTxnMetricSummary.setPodName(pod);
				*/
				String targetURL = "https://insights-api.newrelic.com/v1/accounts/" + MetricConstants.POD_INSIGHT_APPL_MAP.get(pod) + "/query?nrql=";
				
				try  {
					nrql = URLEncoder.encode(nrql,"ISO-8859-1");
				} catch (Exception e) {
					System.out.println("Could not encode: " + targetURL);
				} 

				targetURL = targetURL + nrql;
				WebTarget webTarget = client.target(targetURL);
				Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
				invocationBuilder.header("X-QUERY-KEY", MetricConstants.POD_INSIGHT_API_KEY_MAP.get(pod));
				Response response = invocationBuilder.get();
				boolean notFinished = true;
				if (response.getStatus() == 200) {
					notFinished = true;
				} else {
					notFinished = false;
				}

				DOLMetricSummaryResponse dolMetricSummaryResponse = null;
				while (notFinished) {
					String summaryMetricKey = null;
					try {
						summaryMetricKey = pod + MetricConstants.MEASURE_CATEGORY_MAP.get(metricKey);
						dolMetricSummaryResponse = response.readEntity(new GenericType<DOLMetricSummaryResponse>(){});
						if (metricSummary.containsKey(summaryMetricKey)) {
							if(metricKey.contains("Count")) {
								metricSummary.get(summaryMetricKey).getCurrentMeasure().setCount(dolMetricSummaryResponse.getCurrent().getResults().get(0).getCount());
								metricSummary.get(summaryMetricKey).getPreviousMeasure().setCount(dolMetricSummaryResponse.getPrevious().getResults().get(0).getCount());
							} else if (metricKey.contains("Avg")) {
								metricSummary.get(summaryMetricKey).getCurrentMeasure().setAverage(dolMetricSummaryResponse.getCurrent().getResults().get(0).getAverage());
								metricSummary.get(summaryMetricKey).getPreviousMeasure().setAverage(dolMetricSummaryResponse.getPrevious().getResults().get(0).getAverage());
							} else if (metricKey.contains("95")) {
								metricSummary.get(summaryMetricKey).getCurrentMeasure().setPercentiles(dolMetricSummaryResponse.getCurrent().getResults().get(0).getPercentiles());
								metricSummary.get(summaryMetricKey).getPreviousMeasure().setPercentiles(dolMetricSummaryResponse.getPrevious().getResults().get(0).getPercentiles());
							}
						} else {
							metricSummary.put(summaryMetricKey, new DOLMetricSummary(pod, metricKey, 
																					dolMetricSummaryResponse.getCurrent().getResults().get(0), 
																					dolMetricSummaryResponse.getPrevious().getResults().get(0)));
						}
						notFinished = false;
					} catch (Exception e) {
						e.printStackTrace();
						log("Cannot readEntity");
						throw new Exception();
					}
				}
				

//					insightTxnMetricSummary.setMetricRepesponse(insightMetricResponse);
//					metricResponseList.add(insightTxnMetricSummary);

			}
		}
		return metricSummary;
	}

	private String formatCountLine(String category, HashMap<String,DOLMetricSummary> metricSummary){
		StringBuffer line = new StringBuffer();
		line.append(category + ";");
		for (String pod : MetricConstants.POD_LIST) {
			line.append(metricSummary.get(pod+category).getCurrentMeasure().getCount()+ ";");
		}
		for (String pod : MetricConstants.POD_LIST) {
			String key = pod + category;
			float current =  metricSummary.get(key).getCurrentMeasure().getCount();
			float previous =  metricSummary.get(key).getPreviousMeasure().getCount();
			float percentChange = (current-previous)/previous;
			line.append(percentChange + ";");
		}
		return line.toString();
	}
	
	private String formatAvgLine(String category, HashMap<String,DOLMetricSummary> metricSummary){
		StringBuffer line = new StringBuffer();
		line.append(category + ";");
		for (String pod : MetricConstants.POD_LIST) {
			line.append(metricSummary.get(pod+category).getCurrentMeasure().getAverage()+ ";");
		}
		for (String pod : MetricConstants.POD_LIST) {
			String key = pod + category;
			float current =  metricSummary.get(key).getCurrentMeasure().getAverage();
			float previous =  metricSummary.get(key).getPreviousMeasure().getAverage();
			float percentChange = (current-previous)/previous;
			line.append(percentChange + ";");
		}
		return line.toString();
	}
	
	private String formatPercentileLine(String category, HashMap<String,DOLMetricSummary> metricSummary){
		StringBuffer line = new StringBuffer();
		line.append(category + ";");
		for (String pod : MetricConstants.POD_LIST) {
			line.append(metricSummary.get(pod+category).getCurrentMeasure().getPercentileState()+ ";");
		}
		for (String pod : MetricConstants.POD_LIST) {
			String key = pod + category;
			float current =  metricSummary.get(key).getCurrentMeasure().getPercentileState();
			float previous =  metricSummary.get(key).getPreviousMeasure().getPercentileState();
			float percentChange = (current-previous)/previous;
			line.append(percentChange + ";");
		}
		return line.toString();
	}
	
	private Vector<String> formatMetricSummary(HashMap<String,DOLMetricSummary> metricSummary)  {
		Vector<String> output = new Vector<String>();
		for (String category : MetricConstants.CATEGORY_ORDER) {
			output.add(formatCountLine(category, metricSummary));
			output.add(formatAvgLine(category,metricSummary));
			output.add(formatPercentileLine(category, metricSummary));
		}

		return output;
	}
	
	private void outputMetricSummary(Vector<String> output) throws Exception {
		FileWriter fw = new FileWriter("output/metrics.txt");
		log("created file");
		
		for (String line : output) {
			fw.write(line+"\n");
		}

		fw.close();
		
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

	private void insertInsightMetrics(Vector<InsightTxnMetricSummary> responseList, String environment) {
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
	    	        	try {
		    	 			for (InsightTimeSeries timeSeries : summary.getMetricRepesponse().getTimeSeries()) {
				    	         // Create and execute an SQL statement that returns some data.  
				    	         String SQL = "insert into " + MetricConstants.POD_DBCONFIG_MAP.get(environment).get(MetricConstants.METRIC_STAGE_6H_TBL_KEY) + " (podName,transactionName,durationRange,fromDate,toDate, totalCount) values (?,?,?,?,?,?);";
				    	         stmt = con.prepareStatement(SQL);
				    	         stmt.setString(1, summary.getPodName());
				    	         stmt.setString(2, summary.getTransactionName());
				    	         stmt.setString(3, summary.getDurationRange());
				    	         stmt.setTimestamp(4, new java.sql.Timestamp(timeSeries.getBeginTimeSeconds()*1000),Calendar.getInstance());
				    	         stmt.setTimestamp(5, new java.sql.Timestamp(timeSeries.getEndTimeSeconds()*1000), Calendar.getInstance());
	//			    	         stmt.setTime(4, new java.sql.Time(timeSeries.getBeginTimeSeconds()*1000));
	//			    	         stmt.setTime(5, new java.sql.Time(timeSeries.getEndTimeSeconds()*1000));
	//			    	         stmt.setDate(4, new java.sql.Date(timeSeries.getBeginTimeSeconds()*1000));
	//			    	         stmt.setDate(5, new java.sql.Date(timeSeries.getEndTimeSeconds()*1000));
				    	         stmt.setLong(6, timeSeries.getInsightResults().get(0).getCount());
				    	         stmt.execute();  
		    	 			}
	    	 			} catch (Exception e) {
	    	 				System.out.println("Couldn't insert " + summary.getTransactionName());
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
