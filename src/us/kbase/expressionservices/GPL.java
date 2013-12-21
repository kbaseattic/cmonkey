
package us.kbase.expressionservices;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: GPL</p>
 * <pre>
 * Data structure for a GEO Platform
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "gpl_id",
    "gpl_title",
    "gpl_technology",
    "gpl_tax_id",
    "gpl_organism"
})
public class GPL {

    @JsonProperty("gpl_id")
    private String gplId;
    @JsonProperty("gpl_title")
    private String gplTitle;
    @JsonProperty("gpl_technology")
    private String gplTechnology;
    @JsonProperty("gpl_tax_id")
    private String gplTaxId;
    @JsonProperty("gpl_organism")
    private String gplOrganism;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("gpl_id")
    public String getGplId() {
        return gplId;
    }

    @JsonProperty("gpl_id")
    public void setGplId(String gplId) {
        this.gplId = gplId;
    }

    public GPL withGplId(String gplId) {
        this.gplId = gplId;
        return this;
    }

    @JsonProperty("gpl_title")
    public String getGplTitle() {
        return gplTitle;
    }

    @JsonProperty("gpl_title")
    public void setGplTitle(String gplTitle) {
        this.gplTitle = gplTitle;
    }

    public GPL withGplTitle(String gplTitle) {
        this.gplTitle = gplTitle;
        return this;
    }

    @JsonProperty("gpl_technology")
    public String getGplTechnology() {
        return gplTechnology;
    }

    @JsonProperty("gpl_technology")
    public void setGplTechnology(String gplTechnology) {
        this.gplTechnology = gplTechnology;
    }

    public GPL withGplTechnology(String gplTechnology) {
        this.gplTechnology = gplTechnology;
        return this;
    }

    @JsonProperty("gpl_tax_id")
    public String getGplTaxId() {
        return gplTaxId;
    }

    @JsonProperty("gpl_tax_id")
    public void setGplTaxId(String gplTaxId) {
        this.gplTaxId = gplTaxId;
    }

    public GPL withGplTaxId(String gplTaxId) {
        this.gplTaxId = gplTaxId;
        return this;
    }

    @JsonProperty("gpl_organism")
    public String getGplOrganism() {
        return gplOrganism;
    }

    @JsonProperty("gpl_organism")
    public void setGplOrganism(String gplOrganism) {
        this.gplOrganism = gplOrganism;
    }

    public GPL withGplOrganism(String gplOrganism) {
        this.gplOrganism = gplOrganism;
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
        return ((((((((((((("GPL"+" [gplId=")+ gplId)+", gplTitle=")+ gplTitle)+", gplTechnology=")+ gplTechnology)+", gplTaxId=")+ gplTaxId)+", gplOrganism=")+ gplOrganism)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
