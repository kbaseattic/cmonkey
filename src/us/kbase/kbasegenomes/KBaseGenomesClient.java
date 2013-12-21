package us.kbase.kbasegenomes;

import java.net.URL;
import us.kbase.common.service.JsonClientCaller;

/**
 * <p>Original spec-file module name: KBaseGenomes</p>
 * <pre>
 * @author chenry,kkeller
 * </pre>
 */
public class KBaseGenomesClient {
    private JsonClientCaller caller;

    public KBaseGenomesClient(URL url) {
        caller = new JsonClientCaller(url);
    }

	public void setConnectionReadTimeOut(Integer milliseconds) {
		this.caller.setConnectionReadTimeOut(milliseconds);
	}
}
