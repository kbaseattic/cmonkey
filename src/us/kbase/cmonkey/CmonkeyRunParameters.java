
package us.kbase.cmonkey;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: CmonkeyRunParameters</p>
 * <pre>
 * Contains parameters of a cMonkey run
 * int no_operons = <0|1> - if 1, MicrobesOnline operons data will not be used
 * int no_string = <0|1> - if 1, STRING data will not be used
 * int no_networks = <0|1> - if 1, Network scoring will not be used
 * int no_motifs = <0|1> - if 1, Motif scoring will not be used
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "no_operons",
    "no_string",
    "no_networks",
    "no_motifs"
})
public class CmonkeyRunParameters {

    @JsonProperty("no_operons")
    private Long noOperons;
    @JsonProperty("no_string")
    private Long noString;
    @JsonProperty("no_networks")
    private Long noNetworks;
    @JsonProperty("no_motifs")
    private Long noMotifs;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("no_operons")
    public Long getNoOperons() {
        return noOperons;
    }

    @JsonProperty("no_operons")
    public void setNoOperons(Long noOperons) {
        this.noOperons = noOperons;
    }

    public CmonkeyRunParameters withNoOperons(Long noOperons) {
        this.noOperons = noOperons;
        return this;
    }

    @JsonProperty("no_string")
    public Long getNoString() {
        return noString;
    }

    @JsonProperty("no_string")
    public void setNoString(Long noString) {
        this.noString = noString;
    }

    public CmonkeyRunParameters withNoString(Long noString) {
        this.noString = noString;
        return this;
    }

    @JsonProperty("no_networks")
    public Long getNoNetworks() {
        return noNetworks;
    }

    @JsonProperty("no_networks")
    public void setNoNetworks(Long noNetworks) {
        this.noNetworks = noNetworks;
    }

    public CmonkeyRunParameters withNoNetworks(Long noNetworks) {
        this.noNetworks = noNetworks;
        return this;
    }

    @JsonProperty("no_motifs")
    public Long getNoMotifs() {
        return noMotifs;
    }

    @JsonProperty("no_motifs")
    public void setNoMotifs(Long noMotifs) {
        this.noMotifs = noMotifs;
    }

    public CmonkeyRunParameters withNoMotifs(Long noMotifs) {
        this.noMotifs = noMotifs;
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
        return ((((((((((("CmonkeyRunParameters"+" [noOperons=")+ noOperons)+", noString=")+ noString)+", noNetworks=")+ noNetworks)+", noMotifs=")+ noMotifs)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
