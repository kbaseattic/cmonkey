
package us.kbase.kbaseexpression;

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
 * publication should need to eventually have ws objects, will not include it for now.
 * @optional title summary design publication_id 
 * @searchable ws_subset id source_id publication_id title summary design genome_expression_sample_ids_map
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "source_id",
    "genome_expression_sample_ids_map",
    "title",
    "summary",
    "design",
    "publication_id",
    "external_source_date"
})
public class ExpressionSeries {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("source_id")
    private java.lang.String sourceId;
    @JsonProperty("genome_expression_sample_ids_map")
    private Map<String, List<String>> genomeExpressionSampleIdsMap;
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

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public ExpressionSeries withId(java.lang.String id) {
        this.id = id;
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

    @JsonProperty("genome_expression_sample_ids_map")
    public Map<String, List<String>> getGenomeExpressionSampleIdsMap() {
        return genomeExpressionSampleIdsMap;
    }

    @JsonProperty("genome_expression_sample_ids_map")
    public void setGenomeExpressionSampleIdsMap(Map<String, List<String>> genomeExpressionSampleIdsMap) {
        this.genomeExpressionSampleIdsMap = genomeExpressionSampleIdsMap;
    }

    public ExpressionSeries withGenomeExpressionSampleIdsMap(Map<String, List<String>> genomeExpressionSampleIdsMap) {
        this.genomeExpressionSampleIdsMap = genomeExpressionSampleIdsMap;
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
        return ((((((((((((((((((("ExpressionSeries"+" [id=")+ id)+", sourceId=")+ sourceId)+", genomeExpressionSampleIdsMap=")+ genomeExpressionSampleIdsMap)+", title=")+ title)+", summary=")+ summary)+", design=")+ design)+", publicationId=")+ publicationId)+", externalSourceDate=")+ externalSourceDate)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
