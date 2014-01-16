package us.kbase.cmonkey;

public class CmonkeyServerConfig {
	//Deployment options
	protected static boolean DEPLOY_AWE = true;
	
	//Service URLs
	public static final String JOB_SERVICE_URL = " https://kbase.us/services/userandjobstate";//dev:"http://140.221.84.180:7083";
	public static final String AWE_SERVICE_URL = "http://140.221.85.171:7080/job";
	public static final String ID_SERVICE_URL = "http://kbase.us/services/idserver";
	public static final String WS_SERVICE_URL = "https://kbase.us/services/ws";//dev: "http://140.221.84.209:7058";
	
	//Paths
	protected static final String JOB_DIRECTORY = "/var/tmp/cmonkey/";
	protected static final String CMONKEY_DIRECTORY = "/kb/runtime/cmonkey-python/";
	protected static final String CMONKEY_RUN_PATH = "/kb/runtime/cmonkey-python/cmonkey.py";
	protected static final String AWF_CONFIG_FILE = "/kb/deployment/services/cmonkey/cmonkey.awf";


	//Logging options
	//With LOG_AWE_CALLS = true, Cmonkey will write all JSON calls to AWE client and all AWE responses to /var/tmp/cmonkey/cmonkey-awe.log
	//This is a serious security threat because log will contain all auth tokens
	//SET IT TO FALSE ON PRODUCTION  
	public static final boolean LOG_AWE_CALLS = true;
	
	protected static final String CMONKEY_RUN_RESULT_TYPE = "Cmonkey.CmonkeyRunResult";
	
}
