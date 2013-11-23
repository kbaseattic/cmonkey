
package us.kbase.cmonkey;

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
 * <p>Original spec-file type: ExpressionDataSample</p>
 * <pre>
 * ExpressionDataSample represents set of expression data
 * string id - identifier of data set
 * string description - description of data set`
 * list<ExpressionDataPoint> points - data points
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "description",
    "points"
})
public class ExpressionDataSample {

    @JsonProperty("id")
    private String id;
    @JsonProperty("description")
    private String description;
    @JsonProperty("points")
    private List<ExpressionDataPoint> points;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public ExpressionDataSample withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public ExpressionDataSample withDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("points")
    public List<ExpressionDataPoint> getPoints() {
        return points;
    }

    @JsonProperty("points")
    public void setPoints(List<ExpressionDataPoint> points) {
        this.points = points;
    }

    public ExpressionDataSample withPoints(List<ExpressionDataPoint> points) {
        this.points = points;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return ((((((((("ExpressionDataSample"+" [id=")+ id)+", description=")+ description)+", points=")+ points)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
