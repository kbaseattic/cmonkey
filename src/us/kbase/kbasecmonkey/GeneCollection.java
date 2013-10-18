
package us.kbase.kbasecmonkey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;


/**
 * <p>Original spec-file type: GeneCollection</p>
 * <pre>
 * Represents collection of genes
 * string GeneCollectionId - identifier of the collection
 * string GeneCollectionDescription - description of the collection
 * list<string> geneIds - list of genes
 * </pre>
 * 
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "GeneCollectionId",
    "GeneCollectionDescription",
    "geneIds"
})
public class GeneCollection {

    @JsonProperty("GeneCollectionId")
    private String GeneCollectionId;
    @JsonProperty("GeneCollectionDescription")
    private String GeneCollectionDescription;
    @JsonProperty("geneIds")
    private List<String> geneIds = new ArrayList<String>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("GeneCollectionId")
    public String getGeneCollectionId() {
        return GeneCollectionId;
    }

    @JsonProperty("GeneCollectionId")
    public void setGeneCollectionId(String GeneCollectionId) {
        this.GeneCollectionId = GeneCollectionId;
    }

    public GeneCollection withGeneCollectionId(String GeneCollectionId) {
        this.GeneCollectionId = GeneCollectionId;
        return this;
    }

    @JsonProperty("GeneCollectionDescription")
    public String getGeneCollectionDescription() {
        return GeneCollectionDescription;
    }

    @JsonProperty("GeneCollectionDescription")
    public void setGeneCollectionDescription(String GeneCollectionDescription) {
        this.GeneCollectionDescription = GeneCollectionDescription;
    }

    public GeneCollection withGeneCollectionDescription(String GeneCollectionDescription) {
        this.GeneCollectionDescription = GeneCollectionDescription;
        return this;
    }

    @JsonProperty("geneIds")
    public List<String> getGeneIds() {
        return geneIds;
    }

    @JsonProperty("geneIds")
    public void setGeneIds(List<String> geneIds) {
        this.geneIds = geneIds;
    }

    public GeneCollection withGeneIds(List<String> geneIds) {
        this.geneIds = geneIds;
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

}
