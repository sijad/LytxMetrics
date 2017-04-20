package com.lytx.common.newrelic;

import java.util.Arrays;
import java.util.List;
import java.util.HashMap;

public class MetricConstants {
	public static final String FLEET_KEY = "fleet";
	public static final String API_KEY = "x-api-key";
	public static final String POD1_API_KEY_VALUE = "78c536080b9464f24aa4d27a8f934a987a0cae73e498121";
	public static final String POD2_API_KEY_VALUE = "503f9dafc8e91aa3e1321e50521bb27785d0c6a05c31e5c";
//	public static final String POD5_API_KEY_VALUE = "";
//	public static final String POD6_API_KEY_VALUE = "";
//	public static final String POD7_API_KEY_VALUE = "a6980e577f6586ff0c1313cb2be71e00e94431c6f9ab69e";
	
	public static final String POD1_HBS_APPLICATION_ID = "19855889";
	public static final String POD1_ARC_APPLICATION_ID = "21540176";
	public static final String POD1_VIDEO_APPLICATION_ID="19855910";
	public static final String POD2_APPLICATION_ID = "23579749";
	public static final String POD5_APPLICATION_ID = "";
	public static final String POD6_APPLICATION_ID = "";
	public static final String POD7_APPLICATION_ID = "";
	public static final String POD1_INSIGHTS_APP_ID = "828400";
	public static final String POD2_INSIGHTS_APP_ID = "1295755";
	public static final String POD5_INSIGHTS_APP_ID = "";
	public static final String POD6_INSIGHTS_APP_ID = "";
	public static final String POD7_INSIGHTS_APP_ID = "";
	public static final int [] DURATION_GROUP_START = {0,1,2,3,5,30};
	public static final int [] DURATION_GROUP_END = {1,2,3,5,30,999};
	public static final String WEB_TRANSACTION_WCF_TYPE = "WebTransaction/WCF";
	public static final String WEB_TRANSACTION_ASP_TYPE = "WebTransaction/ASP";
	public static final String WEB_TRANSACTION_JS_TYPE = "WebTransaction/Expressjs";
	public static final String WEB_TRANSACTION_TYPE = "WebTransaction";
	public static final String METRIC_STAGE_6H_TBL_KEY = "metricsStage6H";
	public static final String METRIC_STAGE_H_TBL_KEY = "metricsStageH";
	public static final String PROD_ENVIRONMENT = "prod";
	public static final String TEST_ENVIRONMENT = "test";
	public static final String METRIC_STAGE_6H_TBL_NAME_TEST = "insightMetricsStageTest";
	public static final String METRIC_STAGE_6H_TBL_NAME_PROD = "insightMetricsStage";
	public static final String METRIC_STAGE_H_TBL_NAME_TEST = "insightHourlyMetricsStageTest";
	public static final String METRIC_STAGE_H_TBL_NAME_PROD = "insightHourlyMetricsStage";

	public static final String OVERALL_COUNT = "overallCount";
	public static final String DASHBOARD_COUNT = "dashboardCount";
	public static final String FLEET_TRACKING_COUNT = "fleetTrackingCount";
	public static final String GET_VIDEO_COUNT = "getVideoCount";
	public static final String PLAY_VIDEO_COUNT = "playVideoCount";
	public static final String EVENTS_COUNT = "eventsCount";
	public static final String GPS_BULK_API_COUNT = "gpsBulkApiCount";
	public static final String SUBMISSION_API_COUNT = "submissionApiCount";
	public static final String OVERALL_AVG = "overallAvg";
	public static final String DASHBOARD_AVG = "dashboardAvg";
	public static final String FLEET_TRACKING_AVG = "fleetTrackingAvg";
	public static final String GET_VIDEO_AVG = "getVideoAvg";
	public static final String PLAY_VIDEO_AVG = "playVideoAvg";
	public static final String EVENTS_AVG = "eventsAvg";
	public static final String GPS_BULK_API_AVG = "gpsBulkApiAvg";
	public static final String SUBMISSION_API_AVG = "submissionApiAvg";
	public static final String OVERALL_95TH = "overall95th";
	public static final String DASHBOARD_95TH = "dashboard95th";
	public static final String FLEET_TRACKING_95TH = "fleetTracking95th";
	public static final String GET_VIDEO_95TH = "getVideo95th";
	public static final String PLAY_VIDEO_95TH = "playVideo95th";
	public static final String EVENTS_95TH = "events95th";
	public static final String GPS_BULK_API_95TH = "gpsBulkApi95th";
	public static final String SUBMISSION_API_95TH = "submissionApi95th";

	public static final String OVERALL = "overall";
	public static final String DASHBOARD = "dashboard";
	public static final String FLEET_TRACKING = "fleetTracking";
	public static final String GET_VIDEO = "getVideo";
	public static final String PLAY_VIDEO = "playVideo";
	public static final String EVENTS = "events";
	public static final String GPS_BULK_API = "gpsBulkApi";
	public static final String SUBMISSION_API = "submissionApi";

	private static String pod1OverallCountNrql = "SELECT count(*) FROM Transaction  SINCE 1 WEEK AGO COMPARE WITH 1 WEEK AGO  WHERE appName = 'COMM (Services) - DOL 3 SL, iPad and Mobile traffic'";
	private static String overallCountNrql = "select count(*) FROM Transaction  SINCE 1 WEEK AGO COMPARE WITH 1 WEEK AGO  WHERE appName = 'Production IIS HBS.Services'";
	private static String dashboardCountNrql = "SELECT count(*) FROM Transaction SINCE 1 week AGO COMPARE WITH 1 week AGO WHERE name = 'WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IDashboardService.GetSafetySummary'";
	private static String fleetTrackingCountNrql = "SELECT count(*) FROM Transaction WHERE name in ('WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IGpsService.GetVehicleRealtimeGpsActivityList','WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IGeoFenceService.GetGeoFence','WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IGeoFenceService.GetGeoFenceGeographies','WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IGeoFenceService.GetGeoFences','WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IGeoFenceService.AddGeoFence','WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IGeoFenceService.EditGeoFence','WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IGeoFenceService.DeleteGeoFence','WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IGpsService.GetVehicleHistoricalGpsActivityList','WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IVehicleService.GetGpsVehicleList','WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IGpsService.GetGpsPlotData') SINCE 1 week AGO COMPARE WITH 1 WEEK AGO";
	private static String getVideoCountNrql = "SELECT count(*) FROM Transaction WHERE name in ('WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IEventService.GetEventVideoResource') SINCE 1 week AGO COMPARE WITH 1 WEEK AGO";
	private static String playVideoCountNrql = "SELECT count(*) FROM Transaction WHERE name in ('WebTransaction/ASP/VideoResourceHandler', 'WebTransaction/ASP/VideoResourceHttpHandler') SINCE 1 week AGO COMPARE WITH 1 WEEK AGO";
	private static String eventsCountNrql = "SELECT count(*) FROM Transaction WHERE name in ('WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IEventService.GetEventNote', 'WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IEventService.GetEventVideoResource', 'WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IEventService.GetEventDetails', 'WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IEventRecorderService.GetEventRecorderDetails', 'WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IEventService.GetEventList', 'WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IEventService.GetEventSnapshotResource', 'WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IEventService.GetAvailableEventActions') SINCE 1 week AGO COMPARE WITH 1 WEEK AGO";
	private static String gpsBulkApiCountNrql = "SELECT  count(*) FROM Transaction SINCE 1 week AGO compare with 1 week ago WHERE name like 'WebTransaction/WCF/DriveCam.HindSight.Messaging.Interface.IGPSBulkData%'";
	private static String submissionApiCountNrql = "SELECT  count(*) FROM Transaction SINCE 1 week AGO compare with 1 week ago WHERE name like 'WebTransaction/WCF/WCFSubmissionService%'";
	
	private static String pod1OverallAvgNrql = "SELECT average(duration) FROM Transaction  SINCE 1 WEEK AGO COMPARE WITH 1 WEEK AGO  WHERE appName = 'COMM (Services) - DOL 3 SL, iPad and Mobile traffic'";
	private static String overallAvgNrql = "select average(duration) FROM Transaction  SINCE 1 WEEK AGO COMPARE WITH 1 WEEK AGO  WHERE appName = 'Production IIS HBS.Services'";
	private static String dashboardAvgNrql = "SELECT average(duration) FROM Transaction SINCE 1 week AGO COMPARE WITH 1 week AGO WHERE name = 'WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IDashboardService.GetSafetySummary'";
	private static String fleetTrackingAvgNrql = "SELECT average(duration) FROM Transaction WHERE name in ('WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IGpsService.GetVehicleRealtimeGpsActivityList','WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IGeoFenceService.GetGeoFence','WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IGeoFenceService.GetGeoFenceGeographies','WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IGeoFenceService.GetGeoFences','WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IGeoFenceService.AddGeoFence','WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IGeoFenceService.EditGeoFence','WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IGeoFenceService.DeleteGeoFence','WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IGpsService.GetVehicleHistoricalGpsActivityList','WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IVehicleService.GetGpsVehicleList','WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IGpsService.GetGpsPlotData') SINCE 1 week AGO COMPARE WITH 1 WEEK AGO";
	private static String getVideoAvgNrql = "SELECT average(duration) FROM Transaction WHERE name in ('WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IEventService.GetEventVideoResource') SINCE 1 week AGO COMPARE WITH 1 WEEK AGO";
	private static String playVideoAvgNrql = "SELECT average(duration) FROM Transaction WHERE name in ('WebTransaction/ASP/VideoResourceHandler', 'WebTransaction/ASP/VideoResourceHttpHandler') SINCE 1 week AGO COMPARE WITH 1 WEEK AGO";
	private static String eventsAvgNrql = "SELECT average(duration) FROM Transaction WHERE name in ('WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IEventService.GetEventNote', 'WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IEventService.GetEventVideoResource', 'WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IEventService.GetEventDetails', 'WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IEventRecorderService.GetEventRecorderDetails', 'WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IEventService.GetEventList', 'WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IEventService.GetEventSnapshotResource', 'WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IEventService.GetAvailableEventActions') SINCE 1 week AGO COMPARE WITH 1 WEEK AGO";
	private static String gpsBulkApiAvgNrql = "SELECT average(duration) FROM Transaction SINCE 1 week AGO compare with 1 week ago WHERE name like 'WebTransaction/WCF/DriveCam.HindSight.Messaging.Interface.IGPSBulkData%'";
	private static String submissionApiAvgNrql = "SELECT  average(duration) FROM Transaction SINCE 1 week AGO compare with 1 week ago WHERE name like 'WebTransaction/WCF/WCFSubmissionService%'";

	private static String pod1Overall95thNrql = "SELECT percentile(duration, 95) FROM Transaction  SINCE 1 WEEK AGO COMPARE WITH 1 WEEK AGO  WHERE appName = 'COMM (Services) - DOL 3 SL, iPad and Mobile traffic'";
	private static String overall95thNrql = "select percentile(duration, 95) FROM Transaction  SINCE 1 WEEK AGO COMPARE WITH 1 WEEK AGO  WHERE appName = 'Production IIS HBS.Services'";
	private static String dashboard95thNrql = "SELECT percentile(duration, 95) FROM Transaction SINCE 1 week AGO COMPARE WITH 1 week AGO WHERE name = 'WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IDashboardService.GetSafetySummary'";
	private static String fleetTracking95thNrql = "SELECT percentile(duration, 95) FROM Transaction WHERE name in ('WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IGpsService.GetVehicleRealtimeGpsActivityList','WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IGeoFenceService.GetGeoFence','WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IGeoFenceService.GetGeoFenceGeographies','WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IGeoFenceService.GetGeoFences','WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IGeoFenceService.AddGeoFence','WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IGeoFenceService.EditGeoFence','WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IGeoFenceService.DeleteGeoFence','WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IGpsService.GetVehicleHistoricalGpsActivityList','WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IVehicleService.GetGpsVehicleList','WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IGpsService.GetGpsPlotData') SINCE 1 week AGO COMPARE WITH 1 WEEK AGO";
	private static String getVideo95thNrql = "SELECT percentile(duration, 95) FROM Transaction WHERE name in ('WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IEventService.GetEventVideoResource') SINCE 1 week AGO COMPARE WITH 1 WEEK AGO";
	private static String playVideo95thNrql = "SELECT percentile(duration, 95) FROM Transaction WHERE name in ('WebTransaction/ASP/VideoResourceHandler', 'WebTransaction/ASP/VideoResourceHttpHandler') SINCE 1 week AGO COMPARE WITH 1 WEEK AGO";
	private static String events95thNrql = "SELECT percentile(duration, 95) FROM Transaction WHERE name in ('WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IEventService.GetEventNote', 'WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IEventService.GetEventVideoResource', 'WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IEventService.GetEventDetails', 'WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IEventRecorderService.GetEventRecorderDetails', 'WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IEventService.GetEventList', 'WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IEventService.GetEventSnapshotResource', 'WebTransaction/WCF/DriveCam.HindSight.Services.Contracts.IEventService.GetAvailableEventActions') SINCE 1 week AGO COMPARE WITH 1 WEEK AGO";
	private static String gpsBulkApi95thNrql = "SELECT percentile(duration, 95) FROM Transaction SINCE 1 week AGO compare with 1 week ago WHERE name like 'WebTransaction/WCF/DriveCam.HindSight.Messaging.Interface.IGPSBulkData%'";
	private static String submissionApi95thNrql = "SELECT  percentile(duration, 95) FROM Transaction SINCE 1 week AGO compare with 1 week ago WHERE name like 'WebTransaction/WCF/WCFSubmissionService%'";
	public static final List<String> POD_LIST = Arrays.asList("pod1", "pod2", "pod5", "pod6", "pod7");
	public static final List<String> OUTPUT_POD_LIST = Arrays.asList("pod1", "pod2", "pod5", "pod6", "pod7", "fleet");
	public static final List<String> CATEGORY_ORDER = Arrays.asList(OVERALL, DASHBOARD, FLEET_TRACKING, GET_VIDEO, PLAY_VIDEO, EVENTS, GPS_BULK_API, SUBMISSION_API);

	private static String overallFleetCount = "SELECT count(*) FROM Transaction since 1 week ago compare with 1 week ago";
	private static String overallFleetAvg = "SELECT average(duration) FROM Transaction since 1 week ago compare with 1 week ago";
	private static String overallFleet95th = "SELECT percentile(duration, 95) FROM Transaction since 1 week ago compare with 1 week ago";
	
	private static String AUTHENTICATE_USER_NRQL = "";
	private static String GET_POST_LOGIN_NRQL = "";
	private static String INITIALIZE_SHELL_NRQL = "";
	private static String INITIALIZE_UI_NRQL = "";
	private static String LOGIN_BEFORE_TRANSITION_NRQL = "";
	private static String POST_LOGIN_NRQL = "";

	public static final HashMap<String, String> POD_API_KEY_MAP = createPodAPMApiKeyMap();
	public static final HashMap<String, String> POD_APPL_MAP = createPodAPMApplMap();
	public static final HashMap<String, String> POD_INSIGHT_API_KEY_MAP = createPodInsightApiKeyMap();
	public static final HashMap<String, String> POD_INSIGHT_APPL_MAP = createPodInsightApplMap();
	public static final HashMap<String, HashMap<String,String>> POD_DBCONFIG_MAP = createDatabaseEnvironmentMap();
	public static final HashMap<String, HashMap<String,String>> NRQL_COMMAND_MAP = createPodNrqlCommandMap();
	public static final HashMap<String, String> MEASURE_CATEGORY_MAP = createMeasureCategoryMap();
	
	public static final HashMap<String, String> FLEET_COMMAND_MAP = createFleetCommandMap();

	
	private static HashMap<String,HashMap<String, String>> createDatabaseEnvironmentMap() {
		HashMap<String,String> tableNameMap = new HashMap<String,String>();
		tableNameMap.put(METRIC_STAGE_H_TBL_KEY, METRIC_STAGE_H_TBL_NAME_PROD);
		tableNameMap.put(METRIC_STAGE_6H_TBL_KEY, METRIC_STAGE_6H_TBL_NAME_PROD);
		HashMap<String,HashMap<String,String>> map = new HashMap<String,HashMap<String,String>>();
		map.put(PROD_ENVIRONMENT, tableNameMap);
		tableNameMap = new HashMap<String,String>();
		tableNameMap.put(METRIC_STAGE_H_TBL_KEY, METRIC_STAGE_H_TBL_NAME_TEST);
		tableNameMap.put(METRIC_STAGE_6H_TBL_KEY, METRIC_STAGE_6H_TBL_NAME_TEST);
		map.put(TEST_ENVIRONMENT, tableNameMap);
		return map;
		
	}
	
	private static HashMap<String,String> createPodAPMApiKeyMap() {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("pod1", "78c536080b9464f24aa4d27a8f934a987a0cae73e498121");
		map.put("pod2", "503f9dafc8e91aa3e1321e50521bb27785d0c6a05c31e5c");
//		map.put("pod5", "");
//		map.put("pod6", "");
//		map.put("pod7", "a6980e577f6586ff0c1313cb2be71e00e94431c6f9ab69e");
		return map;
	}

	private static HashMap<String,String> createPodAPMApplMap() {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("pod1", "19855889");
		map.put("pod2", "23579749");
//		map.put("pod5", "");
//		map.put("pod6", "");
//		map.put("pod7", "1450015");
		
		return map;
	}
	private static HashMap<String,String> createPodInsightApiKeyMap() {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("pod1", "QTCpvoztYPFi1cz7r946E6QNaQLOlEuP");
		map.put("pod2", "l4ozEv4IcifmegILWuquV4oG1iVu1Bb3");
		map.put("pod5", "NNh-SEka9QAxcSdI89_qayMKHEc5Dkpm");
		map.put("pod6", "r05lNe0AUqYMFIzouOA3u5cJBVJ_v-7y");
		map.put("pod7", "3H1L7IM7PgULGW87XtB8pzRvmx0fgZs7");
		map.put(FLEET_KEY,"");
		return map;
	}

	private static HashMap<String,String> createPodInsightApplMap() {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("pod1", "828400");
		map.put("pod2", "1295755");
		map.put("pod5", "1450013");
		map.put("pod6", "1450014");
		map.put("pod7", "1450015");
		
		return map;
	}
	
	private static HashMap<String,String> createFleetCommandMap() {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put(OVERALL_COUNT, overallFleetCount);
		map.put(OVERALL_AVG, overallFleetAvg);
		map.put(OVERALL_95TH, overallFleet95th);
		return map;
	}
	
	private static HashMap<String,String> createDefaultNrqlCommandMap() {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put(OVERALL_COUNT,overallCountNrql);
		map.put(DASHBOARD_COUNT,dashboardCountNrql);
		map.put(FLEET_TRACKING_COUNT, fleetTrackingCountNrql);
		map.put(GET_VIDEO_COUNT, getVideoCountNrql);
		map.put(PLAY_VIDEO_COUNT, playVideoCountNrql);
		map.put(EVENTS_COUNT, eventsCountNrql);
		map.put(GPS_BULK_API_COUNT, gpsBulkApiCountNrql);
		map.put(SUBMISSION_API_COUNT, submissionApiCountNrql);
		map.put(OVERALL_AVG,overallAvgNrql);
		map.put(DASHBOARD_AVG,dashboardAvgNrql);
		map.put(FLEET_TRACKING_AVG, fleetTrackingAvgNrql);
		map.put(GET_VIDEO_AVG, getVideoAvgNrql);
		map.put(PLAY_VIDEO_AVG, playVideoAvgNrql);
		map.put(EVENTS_AVG, eventsAvgNrql);
		map.put(GPS_BULK_API_AVG, gpsBulkApiAvgNrql);
		map.put(SUBMISSION_API_AVG, submissionApiAvgNrql);
		map.put(OVERALL_95TH,overall95thNrql);
		map.put(DASHBOARD_95TH,dashboard95thNrql);
		map.put(FLEET_TRACKING_95TH, fleetTracking95thNrql);
		map.put(GET_VIDEO_95TH, getVideo95thNrql);
		map.put(PLAY_VIDEO_95TH, playVideo95thNrql);
		map.put(EVENTS_95TH, events95thNrql);
		map.put(GPS_BULK_API_95TH, gpsBulkApi95thNrql);
		map.put(SUBMISSION_API_95TH, submissionApi95thNrql);
		
		return map;
	}

	private static HashMap<String,HashMap<String,String>> createPodNrqlCommandMap() {
		HashMap<String,HashMap<String,String>> map = new HashMap<String, HashMap<String,String>>();
		
		for (String pod : MetricConstants.POD_LIST) {
			if (pod.equals("pod1")) {
				HashMap<String,String> tempMap = createDefaultNrqlCommandMap();
				tempMap.replace(OVERALL_COUNT, pod1OverallCountNrql);
				tempMap.replace(OVERALL_AVG, pod1OverallAvgNrql);
				tempMap.replace(OVERALL_95TH, pod1Overall95thNrql);
				map.put(pod, tempMap);
			} else {
				map.put(pod, createDefaultNrqlCommandMap());
			}
		}

		for (String podKey : map.keySet()) {

			HashMap<String,String> nrqlMap = map.get(podKey);
			
		}

		return map;

	}


	private static HashMap<String,String> createMeasureCategoryMap() {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put(OVERALL_COUNT,OVERALL);
		map.put(DASHBOARD_COUNT,DASHBOARD);
		map.put(FLEET_TRACKING_COUNT, FLEET_TRACKING);
		map.put(GET_VIDEO_COUNT, GET_VIDEO);
		map.put(PLAY_VIDEO_COUNT, PLAY_VIDEO);
		map.put(EVENTS_COUNT, EVENTS);
		map.put(GPS_BULK_API_COUNT, GPS_BULK_API);
		map.put(SUBMISSION_API_COUNT, SUBMISSION_API);
		map.put(OVERALL_AVG,OVERALL);
		map.put(DASHBOARD_AVG,DASHBOARD);
		map.put(FLEET_TRACKING_AVG, FLEET_TRACKING);
		map.put(GET_VIDEO_AVG, GET_VIDEO);
		map.put(PLAY_VIDEO_AVG, PLAY_VIDEO);
		map.put(EVENTS_AVG, EVENTS);
		map.put(GPS_BULK_API_AVG, GPS_BULK_API);
		map.put(SUBMISSION_API_AVG, SUBMISSION_API);
		map.put(OVERALL_95TH,OVERALL);
		map.put(DASHBOARD_95TH,DASHBOARD);
		map.put(FLEET_TRACKING_95TH, FLEET_TRACKING);
		map.put(GET_VIDEO_95TH, GET_VIDEO);
		map.put(PLAY_VIDEO_95TH, PLAY_VIDEO);
		map.put(EVENTS_95TH, EVENTS);
		map.put(GPS_BULK_API_95TH, GPS_BULK_API);
		map.put(SUBMISSION_API_95TH, SUBMISSION_API);
		
		return map;
	}

	
}
