package com.lytx.common.newrelic;

import java.util.HashMap;

public class MetricConstants {
	public static final String API_KEY = "x-api-key";
	public static final String POD1_API_KEY_VALUE = "78c536080b9464f24aa4d27a8f934a987a0cae73e498121";
	public static final String POD2_API_KEY_VALUE = "503f9dafc8e91aa3e1321e50521bb27785d0c6a05c31e5c";
	public static final String POD1_APPLICATION_ID = "19855889";
	public static final String POD2_APPLICATION_ID = "23579749";
	public static final HashMap<String, String> POD_API_KEY_MAP = createPodApiKeyMap();
	public static final HashMap<String, String> POD_APPL_MAP = createPodApplMap();
	
	private static HashMap<String,String> createPodApiKeyMap() {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("pod1", "78c536080b9464f24aa4d27a8f934a987a0cae73e498121");
		map.put("pod2", "503f9dafc8e91aa3e1321e50521bb27785d0c6a05c31e5c");
		return map;
	}

	private static HashMap<String,String> createPodApplMap() {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("pod1", "19855889");
		map.put("pod2", "23579749");
		
		return map;
	}
}
