
package us.kbase.expressionservices;

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
 * <p>Original spec-file type: GseObject</p>
 * <pre>
 * GSE OBJECT
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "gse_id",
    "gse_title",
    "gse_summary",
    "gse_design",
    "gse_submission_date",
    "pub_med_id",
    "gse_samples",
    "gse_warnings",
    "gse_errors"
})
public class GseObject {

    @JsonProperty("gse_id")
    private java.lang.String gseId;
    @JsonProperty("gse_title")
    private java.lang.String gseTitle;
    @JsonProperty("gse_summary")
    private java.lang.String gseSummary;
    @JsonProperty("gse_design")
    private java.lang.String gseDesign;
    @JsonProperty("gse_submission_date")
    private java.lang.String gseSubmissionDate;
    @JsonProperty("pub_med_id")
    private java.lang.String pubMedId;
    @JsonProperty("gse_samples")
    private Map<String, GsmObject> gseSamples;
    @JsonProperty("gse_warnings")
    private List<String> gseWarnings;
    @JsonProperty("gse_errors")
    private List<String> gseErrors;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("gse_id")
    public java.lang.String getGseId() {
        return gseId;
    }

    @JsonProperty("gse_id")
    public void setGseId(java.lang.String gseId) {
        this.gseId = gseId;
    }

    public GseObject withGseId(java.lang.String gseId) {
        this.gseId = gseId;
        return this;
    }

    @JsonProperty("gse_title")
    public java.lang.String getGseTitle() {
        return gseTitle;
    }

    @JsonProperty("gse_title")
    public void setGseTitle(java.lang.String gseTitle) {
        this.gseTitle = gseTitle;
    }

    public GseObject withGseTitle(java.lang.String gseTitle) {
        this.gseTitle = gseTitle;
        return this;
    }

    @JsonProperty("gse_summary")
    public java.lang.String getGseSummary() {
        return gseSummary;
    }

    @JsonProperty("gse_summary")
    public void setGseSummary(java.lang.String gseSummary) {
        this.gseSummary = gseSummary;
    }

    public GseObject withGseSummary(java.lang.String gseSummary) {
        this.gseSummary = gseSummary;
        return this;
    }

    @JsonProperty("gse_design")
    public java.lang.String getGseDesign() {
        return gseDesign;
    }

    @JsonProperty("gse_design")
    public void setGseDesign(java.lang.String gseDesign) {
        this.gseDesign = gseDesign;
    }

    public GseObject withGseDesign(java.lang.String gseDesign) {
        this.gseDesign = gseDesign;
        return this;
    }

    @JsonProperty("gse_submission_date")
    public java.lang.String getGseSubmissionDate() {
        return gseSubmissionDate;
    }

    @JsonProperty("gse_submission_date")
    public void setGseSubmissionDate(java.lang.String gseSubmissionDate) {
        this.gseSubmissionDate = gseSubmissionDate;
    }

    public GseObject withGseSubmissionDate(java.lang.String gseSubmissionDate) {
        this.gseSubmissionDate = gseSubmissionDate;
        return this;
    }

    @JsonProperty("pub_med_id")
    public java.lang.String getPubMedId() {
        return pubMedId;
    }

    @JsonProperty("pub_med_id")
    public void setPubMedId(java.lang.String pubMedId) {
        this.pubMedId = pubMedId;
    }

    public GseObject withPubMedId(java.lang.String pubMedId) {
        this.pubMedId = pubMedId;
        return this;
    }

    @JsonProperty("gse_samples")
    public Map<String, GsmObject> getGseSamples() {
        return gseSamples;
    }

    @JsonProperty("gse_samples")
    public void setGseSamples(Map<String, GsmObject> gseSamples) {
        this.gseSamples = gseSamples;
    }

    public GseObject withGseSamples(Map<String, GsmObject> gseSamples) {
        this.gseSamples = gseSamples;
        return this;
    }

    @JsonProperty("gse_warnings")
    public List<String> getGseWarnings() {
        return gseWarnings;
    }

    @JsonProperty("gse_warnings")
    public void setGseWarnings(List<String> gseWarnings) {
        this.gseWarnings = gseWarnings;
    }

    public GseObject withGseWarnings(List<String> gseWarnings) {
        this.gseWarnings = gseWarnings;
        return this;
    }

    @JsonProperty("gse_errors")
    public List<String> getGseErrors() {
        return gseErrors;
    }

    @JsonProperty("gse_errors")
    public void setGseErrors(List<String> gseErrors) {
        this.gseErrors = gseErrors;
    }

    public GseObject withGseErrors(List<String> gseErrors) {
        this.gseErrors = gseErrors;
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
        return ((((((((((((((((((((("GseObject"+" [gseId=")+ gseId)+", gseTitle=")+ gseTitle)+", gseSummary=")+ gseSummary)+", gseDesign=")+ gseDesign)+", gseSubmissionDate=")+ gseSubmissionDate)+", pubMedId=")+ pubMedId)+", gseSamples=")+ gseSamples)+", gseWarnings=")+ gseWarnings)+", gseErrors=")+ gseErrors)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
