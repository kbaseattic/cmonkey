
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
 * <p>Original spec-file type: JobObject</p>
 * <pre>
 * Data structures for a job object
 * job_id id - ID of the job object
 * string type - type of the job
 * string auth - authentication token of job owner
 * string status - current status of job
 * mapping<string,string> jobdata;
 * string queuetime - time when job was queued
 * string starttime - time when job started running
 * string completetime - time when the job was completed
 * string owner - owner of the job
 * string queuecommand - command used to queue job
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "type",
    "auth",
    "status",
    "jobdata",
    "queuetime",
    "starttime",
    "completetime",
    "owner",
    "queuecommand"
})
public class JobObject {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("type")
    private java.lang.String type;
    @JsonProperty("auth")
    private java.lang.String auth;
    @JsonProperty("status")
    private java.lang.String status;
    @JsonProperty("jobdata")
    private Map<String, String> jobdata;
    @JsonProperty("queuetime")
    private java.lang.String queuetime;
    @JsonProperty("starttime")
    private java.lang.String starttime;
    @JsonProperty("completetime")
    private java.lang.String completetime;
    @JsonProperty("owner")
    private java.lang.String owner;
    @JsonProperty("queuecommand")
    private java.lang.String queuecommand;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public JobObject withId(java.lang.String id) {
        this.id = id;
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

    public JobObject withType(java.lang.String type) {
        this.type = type;
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

    public JobObject withAuth(java.lang.String auth) {
        this.auth = auth;
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

    public JobObject withStatus(java.lang.String status) {
        this.status = status;
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

    public JobObject withJobdata(Map<String, String> jobdata) {
        this.jobdata = jobdata;
        return this;
    }

    @JsonProperty("queuetime")
    public java.lang.String getQueuetime() {
        return queuetime;
    }

    @JsonProperty("queuetime")
    public void setQueuetime(java.lang.String queuetime) {
        this.queuetime = queuetime;
    }

    public JobObject withQueuetime(java.lang.String queuetime) {
        this.queuetime = queuetime;
        return this;
    }

    @JsonProperty("starttime")
    public java.lang.String getStarttime() {
        return starttime;
    }

    @JsonProperty("starttime")
    public void setStarttime(java.lang.String starttime) {
        this.starttime = starttime;
    }

    public JobObject withStarttime(java.lang.String starttime) {
        this.starttime = starttime;
        return this;
    }

    @JsonProperty("completetime")
    public java.lang.String getCompletetime() {
        return completetime;
    }

    @JsonProperty("completetime")
    public void setCompletetime(java.lang.String completetime) {
        this.completetime = completetime;
    }

    public JobObject withCompletetime(java.lang.String completetime) {
        this.completetime = completetime;
        return this;
    }

    @JsonProperty("owner")
    public java.lang.String getOwner() {
        return owner;
    }

    @JsonProperty("owner")
    public void setOwner(java.lang.String owner) {
        this.owner = owner;
    }

    public JobObject withOwner(java.lang.String owner) {
        this.owner = owner;
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

    public JobObject withQueuecommand(java.lang.String queuecommand) {
        this.queuecommand = queuecommand;
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
        return ((((((((((((((((((((((("JobObject"+" [id=")+ id)+", type=")+ type)+", auth=")+ auth)+", status=")+ status)+", jobdata=")+ jobdata)+", queuetime=")+ queuetime)+", starttime=")+ starttime)+", completetime=")+ completetime)+", owner=")+ owner)+", queuecommand=")+ queuecommand)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
