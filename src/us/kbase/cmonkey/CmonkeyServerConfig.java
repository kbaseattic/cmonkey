package us.kbase.cmonkey;

public class CmonkeyServerConfig {
	//Deployment options
	protected static boolean DEPLOY_AWE = true;

	//Service URLs
	public static final String JOB_SERVICE_URL = "http://140.221.84.180:7083";
	public static final String AWE_SERVICE_URL = "http://140.221.85.171:7080/job";
	public static final String ID_SERVICE_URL = "http://kbase.us/services/idserver";
	public static final String WS_SERVICE_URL = "http://140.221.84.209:7058";
	
	//Paths
	protected static final String JOB_DIRECTORY = "/var/tmp/cmonkey/";
	protected static final String CMONKEY_DIRECTORY = "/kb/runtime/cmonkey-python/";
	protected static final String DATA_PATH = "/etc/cmonkey-python/KEGG_taxonomy";
	protected static final String CMONKEY_RUN_PATH = "/kb/runtime/cmonkey-python/cmonkey.py";


}
