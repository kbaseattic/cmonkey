package us.kbase.cmonkey;

public class CmonkeyServerConfig {
	//Deployment options
	protected static boolean DEPLOY_AWE = true;

	//Service credentials
	protected static final String SERVICE_LOGIN = "cmonkeyservice";
	protected static final String SERVICE_PASSWORD = "DomovenokKuzyaiNafanya1984";

	//Service URLs
	protected static String JOB_SERVICE_URL; // = " https://kbase.us/services/userandjobstate";//dev:"http://140.221.84.180:7083";
	protected static String AWE_SERVICE_URL; // = "http://140.221.85.171:7080/job";
	protected static String ID_SERVICE_URL; // = "http://kbase.us/services/idserver";
	protected static String WS_SERVICE_URL; // = "https://kbase.us/services/ws";//dev: "http://140.221.84.209:7058";
	
	//Paths
	protected static final String JOB_DIRECTORY = "/var/tmp/cmonkey/";
	protected static String CMONKEY_DIRECTORY; // = "/kb/runtime/cmonkey-python/";
	protected static String CMONKEY_RUN_PATH; // = "/kb/runtime/cmonkey-python/cmonkey.py";
	protected static String AWF_CONFIG_FILE; // = "/kb/deployment/services/cmonkey/cmonkey.awf";


	//Logging options
	//With LOG_AWE_CALLS = true, Cmonkey will write all JSON calls to AWE client and all AWE responses to /var/tmp/cmonkey/cmonkey-awe.log
	//This is a serious security threat because log will contain all auth tokens
	//SET IT TO FALSE ON PRODUCTION  
	public static final boolean LOG_AWE_CALLS = false;
	
	protected static final String CMONKEY_RUN_RESULT_TYPE = "Cmonkey.CmonkeyRunResult";
	
	public static String getWsUrl (){
		return WS_SERVICE_URL;
	}

	public static String getIdUrl (){
		return ID_SERVICE_URL;
	}
	
}
