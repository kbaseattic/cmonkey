
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
 * <p>Original spec-file type: queue_job_params</p>
 * <pre>
 * Input parameters for the "queue_job" function.
 *         string auth - the authentication token of the KBase account queuing the job; must have access to the job being queued (an optional argument)
 *         string state - the initial state to assign to the job being queued (an optional argument; default is "queued")
 *         string type - the type of the job being queued
 *         mapping<string,string> jobdata - hash of data associated with job
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "auth",
    "state",
    "type",
    "queuecommand",
    "jobdata"
})
public class QueueJobParams {

    @JsonProperty("auth")
    private java.lang.String auth;
    @JsonProperty("state")
    private java.lang.String state;
    @JsonProperty("type")
    private java.lang.String type;
    @JsonProperty("queuecommand")
    private java.lang.String queuecommand;
    @JsonProperty("jobdata")
    private Map<String, String> jobdata;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("auth")
    public java.lang.String getAuth() {
        return auth;
    }

    @JsonProperty("auth")
    public void setAuth(java.lang.String auth) {
        this.auth = auth;
    }

    public QueueJobParams withAuth(java.lang.String auth) {
        this.auth = auth;
        return this;
    }

    @JsonProperty("state")
    public java.lang.String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(java.lang.String state) {
        this.state = state;
    }

    public QueueJobParams withState(java.lang.String state) {
        this.state = state;
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

    public QueueJobParams withType(java.lang.String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("queuecommand")
    public java.lang.String getQueuecommand() {
        return queuecommand;
    }

    @JsonProperty("queuecommand")
    public void setQueuecommand(java.lang.String queuecommand) {
        this.queuecommand = queuecommand;
    }

    public QueueJobParams withQueuecommand(java.lang.String queuecommand) {
        this.queuecommand = queuecommand;
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

    public QueueJobParams withJobdata(Map<String, String> jobdata) {
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
        return ((((((((((((("QueueJobParams"+" [auth=")+ auth)+", state=")+ state)+", type=")+ type)+", queuecommand=")+ queuecommand)+", jobdata=")+ jobdata)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
