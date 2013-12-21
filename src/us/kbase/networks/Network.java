
package us.kbase.networks;

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
 * <p>Original spec-file type: Network</p>
 * <pre>
 * Represents a network
 * string id - A unique  identifier of a network 
 *         string name - String representation of a network. It should be a concise but informative representation that is easy for a person to read.
 * list<Edge> edges - A list of all edges in a network
 * list<Node> nodes - A list of all nodes in a network
 * list<Dataset> datasets - A list of all datasets used to build a network
 * mapping<string,string> properties - Other properties of a network
 * mapping<string,string> user_annotations - User annotations of a network
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "edges",
    "nodes",
    "datasets",
    "properties",
    "user_annotations"
})
public class Network {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("edges")
    private List<Edge> edges;
    @JsonProperty("nodes")
    private List<Node> nodes;
    @JsonProperty("datasets")
    private List<Dataset> datasets;
    @JsonProperty("properties")
    private Map<String, String> properties;
    @JsonProperty("user_annotations")
    private Map<String, String> userAnnotations;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public Network withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("name")
    public java.lang.String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(java.lang.String name) {
        this.name = name;
    }

    public Network withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("edges")
    public List<Edge> getEdges() {
        return edges;
    }

    @JsonProperty("edges")
    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public Network withEdges(List<Edge> edges) {
        this.edges = edges;
        return this;
    }

    @JsonProperty("nodes")
    public List<Node> getNodes() {
        return nodes;
    }

    @JsonProperty("nodes")
    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public Network withNodes(List<Node> nodes) {
        this.nodes = nodes;
        return this;
    }

    @JsonProperty("datasets")
    public List<Dataset> getDatasets() {
        return datasets;
    }

    @JsonProperty("datasets")
    public void setDatasets(List<Dataset> datasets) {
        this.datasets = datasets;
    }

    public Network withDatasets(List<Dataset> datasets) {
        this.datasets = datasets;
        return this;
    }

    @JsonProperty("properties")
    public Map<String, String> getProperties() {
        return properties;
    }

    @JsonProperty("properties")
    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public Network withProperties(Map<String, String> properties) {
        this.properties = properties;
        return this;
    }

    @JsonProperty("user_annotations")
    public Map<String, String> getUserAnnotations() {
        return userAnnotations;
    }

    @JsonProperty("user_annotations")
    public void setUserAnnotations(Map<String, String> userAnnotations) {
        this.userAnnotations = userAnnotations;
    }

    public Network withUserAnnotations(Map<String, String> userAnnotations) {
        this.userAnnotations = userAnnotations;
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
        return ((((((((((((((((("Network"+" [id=")+ id)+", name=")+ name)+", edges=")+ edges)+", nodes=")+ nodes)+", datasets=")+ datasets)+", properties=")+ properties)+", userAnnotations=")+ userAnnotations)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
