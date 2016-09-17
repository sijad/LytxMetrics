package com.lytx.common.newrelic;

import java.util.HashMap;

public class MetricConstants {
	public static final String API_KEY = "x-api-key";
	public static final String POD1_API_KEY_VALUE = "78c536080b9464f24aa4d27a8f934a987a0cae73e498121";
	public static final String POD2_API_KEY_VALUE = "503f9dafc8e91aa3e1321e50521bb27785d0c6a05c31e5c";
	public static final String POD1_HBS_APPLICATION_ID = "19855889";
	public static final String POD1_ARC_APPLICATION_ID = "21540176";
	public static final String POD1_VIDEO_APPLICATION_ID="19855910";
	public static final String POD2_APPLICATION_ID = "23579749";
	public static final String POD1_INSIGHTS_APP_ID = "828400";
	public static final String POD2_INSIGHTS_APP_ID = "1295755";
	public static final int [] DURATION_GROUP_START = {0,1,2,3,5,30};
	public static final int [] DURATION_GROUP_END = {1,2,3,5,30,999};
	public static final String WEB_TRANSACTION_WCF_TYPE = "WebTransaction/WCF";
	public static final String WEB_TRANSACTION_ASP_TYPE = "WebTransaction/ASP";
	public static final String WEB_TRANSACTION_JS_TYPE = "WebTransaction/Expressjs";
	public static final String WEB_TRANSACTION_TYPE = "WebTransaction";

	public static final HashMap<String, String> POD_API_KEY_MAP = createPodAPMApiKeyMap();
	public static final HashMap<String, String> POD_APPL_MAP = createPodAPMApplMap();
	public static final HashMap<String, String> POD_INSIGHT_API_KEY_MAP = createPodInsightApiKeyMap();
	public static final HashMap<String, String> POD_INSIGHT_APPL_MAP = createPodInsightApplMap();
	
	private static HashMap<String,String> createPodAPMApiKeyMap() {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("pod1", "78c536080b9464f24aa4d27a8f934a987a0cae73e498121");
		map.put("pod2", "503f9dafc8e91aa3e1321e50521bb27785d0c6a05c31e5c");
		return map;
	}

	private static HashMap<String,String> createPodAPMApplMap() {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("pod1", "19855889");
		map.put("pod2", "23579749");
		
		return map;
	}
	private static HashMap<String,String> createPodInsightApiKeyMap() {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("pod1", "QTCpvoztYPFi1cz7r946E6QNaQLOlEuP");
		map.put("pod2", "l4ozEv4IcifmegILWuquV4oG1iVu1Bb3");
		return map;
	}

	private static HashMap<String,String> createPodInsightApplMap() {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("pod1", "828400");
		map.put("pod2", "1295755");
		
		return map;
	}
}
