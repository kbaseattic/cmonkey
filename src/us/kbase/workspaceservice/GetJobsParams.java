
package us.kbase.workspaceservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: get_jobs_params</p>
 * <pre>
 * Input parameters for the "get_jobs" function.
 * list<string> jobids - list of specific jobs to be retrieved (an optional argument; default is an empty list)
 * string status - Status of all jobs to be retrieved; accepted values are 'queued', 'running', and 'done' (an essential argument)
 * string auth - the authentication token of the KBase account accessing job list; only owned jobs will be returned (an optional argument)
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "jobids",
    "type",
    "status",
    "auth"
})
public class GetJobsParams {

    @JsonProperty("jobids")
    private List<String> jobids;
    @JsonProperty("type")
    private java.lang.String type;
    @JsonProperty("status")
    private java.lang.String status;
    @JsonProperty("auth")
    private java.lang.String auth;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("jobids")
    public List<String> getJobids() {
        return jobids;
    }

    @JsonProperty("jobids")
    public void setJobids(List<String> jobids) {
        this.jobids = jobids;
    }

    public GetJobsParams withJobids(List<String> jobids) {
        this.jobids = jobids;
        return this;
    }

    @JsonProperty("type")
    public java.lang.String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(java.lang.String type) {
        this.type = type;
    }

    public GetJobsParams withType(java.lang.String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("status")
    public java.lang.String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(java.lang.String status) {
        this.status = status;
    }

    public GetJobsParams withStatus(java.lang.String status) {
        this.status = status;
        return this;
    }

    @JsonProperty("auth")
    public java.lang.String getAuth() {
        return auth;
    }

    @JsonProperty("auth")
    public void setAuth(java.lang.String auth) {
        this.auth = auth;
    }

    public GetJobsParams withAuth(java.lang.String auth) {
        this.auth = auth;
        return this;
    }

    @JsonAnyGetter
    public Map<java.lang.String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperties(java.lang.String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public java.lang.String toString() {
        return ((((((((((("GetJobsParams"+" [jobids=")+ jobids)+", type=")+ type)+", status=")+ status)+", auth=")+ auth)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
