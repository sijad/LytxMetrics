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

public class VideoMetricDriver {
	public static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";

	public static void main(String [] args) throws Exception {
		
		VideoMetricDriver videoMetricDriver = new VideoMetricDriver();
		
		JerseyWithSSL jerseyWithSSL = new JerseyWithSSL();
		Client client = jerseyWithSSL.initClient();
		Vector<String> metricList = new Vector<String>();
		HashMap<String, VideoMetricResponse> videoMetricResponseMap  = null;
		
		videoMetricResponseMap = videoMetricDriver.runInsightQuery(client, MetricConstants.POD_LIST);
		log("responses: " + videoMetricResponseMap.size());
		videoMetricDriver.outputVideoMetrics(videoMetricResponseMap);
//		Vector<String> outputLines = videoMetricDriver.formatMetricSummary(metricSummaryList);
//		videoMetricDriver.outputMetricSummary(outputLines);
//		dolSummaryDriver.insertInsightMetrics(txnMetricSummaryList, args[0]);


		client.close();

	}
	

	private HashMap<String, VideoMetricResponse> runInsightQuery(Client client, List<String> podList) throws Exception {

		HashMap<String,VideoMetricResponse> videoMetricResponseMap = new HashMap<String, VideoMetricResponse>();


		int counter = 1;
		for (String pod : podList) {
				String nrql = "SELECT response_flush_time_ms, duration, directory_name  FROM Transaction SINCE 7 days AGO WHERE appName = 'Lytx.Video.VideoResourceHttpHandler' LIMIT 1000";
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

				VideoMetricResponse videoMetricResponse = null;
				while (notFinished) {
					try {
						videoMetricResponse = response.readEntity(new GenericType<VideoMetricResponse>(){});
						videoMetricResponseMap.put(pod, videoMetricResponse);
						notFinished = false;
					} catch (Exception e) {
						e.printStackTrace();
						log("Cannot readEntity");
						throw new Exception();
					}
				

//					insightTxnMetricSummary.setMetricRepesponse(insightMetricResponse);
//					metricResponseList.add(insightTxnMetricSummary);

			}
		}
		return videoMetricResponseMap;
	}

	
	private void outputVideoMetrics(HashMap<String, VideoMetricResponse> videoMetricResponseMap) throws Exception {
		FileWriter fw = new FileWriter("output/videoMetrics.txt");
		log("created file");
		for (String pod : videoMetricResponseMap.keySet()) {
			log("pod " + pod + " had " + videoMetricResponseMap.get(pod).getResults().get(0).getEvents().size() + " events ");
			for (VideoMetricEvent event : videoMetricResponseMap.get(pod).getResults().get(0).getEvents()) {
				fw.write(String.valueOf(event.getTimestamp()));
				fw.write(";");
				if (event.getResponseFlushTimeMs() == null) {
					fw.write("NA");
				} else {
					fw.write(event.getResponseFlushTimeMs());
				}
				fw.write(";");
				fw.write(String.valueOf(event.getDuration()));
				fw.write(";");
				fw.write(event.getDirectoryName());
				fw.write("\n");
			}
		}
		

		fw.close();
		
	}
	
	private static void log(String message) {
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		Date date = new Date();
		System.out.println(dateFormat.format(date) + ": " + message  );
	}

}
