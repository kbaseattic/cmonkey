
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
 * <p>Original spec-file type: ExpressionSeries</p>
 * <pre>
 * Data structure for the workspace expression series.  The ExpressionSeries typed object.
 * publication should need to eventually have ws objects, will not inclde it for now.
 * @optional title summary design publication_id 
 * @searchable ws_subset kb_id source_id publication_id title summary design expression_sample_ids
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "kb_id",
    "source_id",
    "expression_sample_ids",
    "title",
    "summary",
    "design",
    "publication_id",
    "external_source_date"
})
public class ExpressionSeries {

    @JsonProperty("kb_id")
    private java.lang.String kbId;
    @JsonProperty("source_id")
    private java.lang.String sourceId;
    @JsonProperty("expression_sample_ids")
    private List<String> expressionSampleIds;
    @JsonProperty("title")
    private java.lang.String title;
    @JsonProperty("summary")
    private java.lang.String summary;
    @JsonProperty("design")
    private java.lang.String design;
    @JsonProperty("publication_id")
    private java.lang.String publicationId;
    @JsonProperty("external_source_date")
    private java.lang.String externalSourceDate;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("kb_id")
    public java.lang.String getKbId() {
        return kbId;
    }

    @JsonProperty("kb_id")
    public void setKbId(java.lang.String kbId) {
        this.kbId = kbId;
    }

    public ExpressionSeries withKbId(java.lang.String kbId) {
        this.kbId = kbId;
        return this;
    }

    @JsonProperty("source_id")
    public java.lang.String getSourceId() {
        return sourceId;
    }

    @JsonProperty("source_id")
    public void setSourceId(java.lang.String sourceId) {
        this.sourceId = sourceId;
    }

    public ExpressionSeries withSourceId(java.lang.String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    @JsonProperty("expression_sample_ids")
    public List<String> getExpressionSampleIds() {
        return expressionSampleIds;
    }

    @JsonProperty("expression_sample_ids")
    public void setExpressionSampleIds(List<String> expressionSampleIds) {
        this.expressionSampleIds = expressionSampleIds;
    }

    public ExpressionSeries withExpressionSampleIds(List<String> expressionSampleIds) {
        this.expressionSampleIds = expressionSampleIds;
        return this;
    }

    @JsonProperty("title")
    public java.lang.String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(java.lang.String title) {
        this.title = title;
    }

    public ExpressionSeries withTitle(java.lang.String title) {
        this.title = title;
        return this;
    }

    @JsonProperty("summary")
    public java.lang.String getSummary() {
        return summary;
    }

    @JsonProperty("summary")
    public void setSummary(java.lang.String summary) {
        this.summary = summary;
    }

    public ExpressionSeries withSummary(java.lang.String summary) {
        this.summary = summary;
        return this;
    }

    @JsonProperty("design")
    public java.lang.String getDesign() {
        return design;
    }

    @JsonProperty("design")
    public void setDesign(java.lang.String design) {
        this.design = design;
    }

    public ExpressionSeries withDesign(java.lang.String design) {
        this.design = design;
        return this;
    }

    @JsonProperty("publication_id")
    public java.lang.String getPublicationId() {
        return publicationId;
    }

    @JsonProperty("publication_id")
    public void setPublicationId(java.lang.String publicationId) {
        this.publicationId = publicationId;
    }

    public ExpressionSeries withPublicationId(java.lang.String publicationId) {
        this.publicationId = publicationId;
        return this;
    }

    @JsonProperty("external_source_date")
    public java.lang.String getExternalSourceDate() {
        return externalSourceDate;
    }

    @JsonProperty("external_source_date")
    public void setExternalSourceDate(java.lang.String externalSourceDate) {
        this.externalSourceDate = externalSourceDate;
    }

    public ExpressionSeries withExternalSourceDate(java.lang.String externalSourceDate) {
        this.externalSourceDate = externalSourceDate;
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
        return ((((((((((((((((((("ExpressionSeries"+" [kbId=")+ kbId)+", sourceId=")+ sourceId)+", expressionSampleIds=")+ expressionSampleIds)+", title=")+ title)+", summary=")+ summary)+", design=")+ design)+", publicationId=")+ publicationId)+", externalSourceDate=")+ externalSourceDate)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
