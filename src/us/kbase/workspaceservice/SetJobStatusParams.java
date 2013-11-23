
package us.kbase.workspaceservice;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: set_job_status_params</p>
 * <pre>
 * Input parameters for the "set_job_status" function.
 *         string jobid - ID of the job to be have status changed (an essential argument)
 *         string status - Status to which job should be changed; accepted values are 'queued', 'running', and 'done' (an essential argument)
 *         string auth - the authentication token of the KBase account requesting job status; only status for owned jobs can be retrieved (an optional argument)
 *         string currentStatus - Indicates the current statues of the selected job (an optional argument; default is "undef")
 *         mapping<string,string> jobdata - hash of data associated with job
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "jobid",
    "status",
    "auth",
    "currentStatus",
    "jobdata"
})
public class SetJobStatusParams {

    @JsonProperty("jobid")
    private java.lang.String jobid;
    @JsonProperty("status")
    private java.lang.String status;
    @JsonProperty("auth")
    private java.lang.String auth;
    @JsonProperty("currentStatus")
    private java.lang.String currentStatus;
    @JsonProperty("jobdata")
    private Map<String, String> jobdata;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("jobid")
    public java.lang.String getJobid() {
        return jobid;
    }

    @JsonProperty("jobid")
    public void setJobid(java.lang.String jobid) {
        this.jobid = jobid;
    }

    public SetJobStatusParams withJobid(java.lang.String jobid) {
        this.jobid = jobid;
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

    public SetJobStatusParams withStatus(java.lang.String status) {
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

    public SetJobStatusParams withAuth(java.lang.String auth) {
        this.auth = auth;
        return this;
    }

    @JsonProperty("currentStatus")
    public java.lang.String getCurrentStatus() {
        return currentStatus;
    }

    @JsonProperty("currentStatus")
    public void setCurrentStatus(java.lang.String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public SetJobStatusParams withCurrentStatus(java.lang.String currentStatus) {
        this.currentStatus = currentStatus;
        return this;
    }

    @JsonProperty("jobdata")
    public Map<String, String> getJobdata() {
        return jobdata;
    }

    @JsonProperty("jobdata")
    public void setJobdata(Map<String, String> jobdata) {
        this.jobdata = jobdata;
    }

    public SetJobStatusParams withJobdata(Map<String, String> jobdata) {
        this.jobdata = jobdata;
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
        return ((((((((((((("SetJobStatusParams"+" [jobid=")+ jobid)+", status=")+ status)+", auth=")+ auth)+", currentStatus=")+ currentStatus)+", jobdata=")+ jobdata)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
