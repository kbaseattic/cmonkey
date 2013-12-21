
package us.kbase.networks;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: Node</p>
 * <pre>
 * Represents a node in a network.
 * string id - A unique  identifier of a node 
 *                 string name - String representation of a node. It should be a concise but informative representation that is easy for a person to read.
 *      string entity_id - The identifier of a  entity represented by a given node 
 *                 node_type type - The type of a node
 *      mapping<string,string> properties - Other properties of a node
 *      mapping<string,string> user_annotations - User annotations of a node
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "entity_id",
    "type",
    "properties",
    "user_annotations"
})
public class Node {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("name")
    private java.lang.String name;
    @JsonProperty("entity_id")
    private java.lang.String entityId;
    @JsonProperty("type")
    private java.lang.String type;
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

    public Node withId(java.lang.String id) {
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

    public Node withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("entity_id")
    public java.lang.String getEntityId() {
        return entityId;
    }

    @JsonProperty("entity_id")
    public void setEntityId(java.lang.String entityId) {
        this.entityId = entityId;
    }

    public Node withEntityId(java.lang.String entityId) {
        this.entityId = entityId;
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

    public Node withType(java.lang.String type) {
        this.type = type;
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

    public Node withProperties(Map<String, String> properties) {
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

    public Node withUserAnnotations(Map<String, String> userAnnotations) {
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
        return ((((((((((((((("Node"+" [id=")+ id)+", name=")+ name)+", entityId=")+ entityId)+", type=")+ type)+", properties=")+ properties)+", userAnnotations=")+ userAnnotations)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
