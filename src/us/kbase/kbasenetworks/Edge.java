
package us.kbase.kbasenetworks;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: Edge</p>
 * <pre>
 * Represents an edge in a network.
 * string id - A unique  identifier of an edge 
 *      string name - String representation of an edge. It should be a concise but informative representation that is easy for a person to read.
 *      string node_id1 - Identifier of the first node (source node, if the edge is directed) connected by a given edge 
 *      string node_id2 - Identifier of the second node (target node, if the edge is directed) connected by a given edge
 *      boolean        directed - Specify whether the edge is directed or not. 1 if it is directed, 0 if it is not directed
 *      float confidence - Value from 0 to 1 representing a probability that the interaction represented by a given edge is a true interaction
 *      float strength - Value from 0 to 1 representing a strength of an interaction represented by a given edge
 *      string dataset_id - The identifier of a dataset that provided an interaction represented by a given edge
 *                 mapping<string,string> properties - Other edge properties
 *      mapping<string,string> user_annotations - User annotations of an edge
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "node_id1",
    "node_id2",
    "directed",
    "confidence",
    "strength",
    "dataset_id",
    "properties",
    "user_annotations"
})
public class Edge {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("node_id1")
    private java.lang.String nodeId1;
    @JsonProperty("node_id2")
    private java.lang.String nodeId2;
    @JsonProperty("directed")
    private java.lang.String directed;
    @JsonProperty("confidence")
    private Double confidence;
    @JsonProperty("strength")
    private Double strength;
    @JsonProperty("dataset_id")
    private java.lang.String datasetId;
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

    public Edge withId(java.lang.String id) {
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

    public Edge withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("node_id1")
    public java.lang.String getNodeId1() {
        return nodeId1;
    }

    @JsonProperty("node_id1")
    public void setNodeId1(java.lang.String nodeId1) {
        this.nodeId1 = nodeId1;
    }

    public Edge withNodeId1(java.lang.String nodeId1) {
        this.nodeId1 = nodeId1;
        return this;
    }

    @JsonProperty("node_id2")
    public java.lang.String getNodeId2() {
        return nodeId2;
    }

    @JsonProperty("node_id2")
    public void setNodeId2(java.lang.String nodeId2) {
        this.nodeId2 = nodeId2;
    }

    public Edge withNodeId2(java.lang.String nodeId2) {
        this.nodeId2 = nodeId2;
        return this;
    }

    @JsonProperty("directed")
    public java.lang.String getDirected() {
        return directed;
    }

    @JsonProperty("directed")
    public void setDirected(java.lang.String directed) {
        this.directed = directed;
    }

    public Edge withDirected(java.lang.String directed) {
        this.directed = directed;
        return this;
    }

    @JsonProperty("confidence")
    public Double getConfidence() {
        return confidence;
    }

    @JsonProperty("confidence")
    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public Edge withConfidence(Double confidence) {
        this.confidence = confidence;
        return this;
    }

    @JsonProperty("strength")
    public Double getStrength() {
        return strength;
    }

    @JsonProperty("strength")
    public void setStrength(Double strength) {
        this.strength = strength;
    }

    public Edge withStrength(Double strength) {
        this.strength = strength;
        return this;
    }

    @JsonProperty("dataset_id")
    public java.lang.String getDatasetId() {
        return datasetId;
    }

    @JsonProperty("dataset_id")
    public void setDatasetId(java.lang.String datasetId) {
        this.datasetId = datasetId;
    }

    public Edge withDatasetId(java.lang.String datasetId) {
        this.datasetId = datasetId;
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

    public Edge withProperties(Map<String, String> properties) {
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

    public Edge withUserAnnotations(Map<String, String> userAnnotations) {
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
        return ((((((((((((((((((((((("Edge"+" [id=")+ id)+", name=")+ name)+", nodeId1=")+ nodeId1)+", nodeId2=")+ nodeId2)+", directed=")+ directed)+", confidence=")+ confidence)+", strength=")+ strength)+", datasetId=")+ datasetId)+", properties=")+ properties)+", userAnnotations=")+ userAnnotations)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
