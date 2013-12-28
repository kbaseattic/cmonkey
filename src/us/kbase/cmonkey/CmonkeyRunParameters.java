
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
 * int networks_scoring = <0|1> - if 1, Network scoring will be used
 * int motifs_scoring = <0|1> - if 1, Motif scoring will be used
 * expression_series_ws_ref series_id - workspace id of expression data series;
 * genome_ws_ref genome_id - workspace id of genome;
 * operons_ws_ref operome_id - workspace id of operome;
 * network_ws_ref network_id - workspace id of network;
 * @optional genome_ref operome_ref network_ref
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "series_ref",
    "genome_ref",
    "operome_ref",
    "network_ref",
    "networks_scoring",
    "motifs_scoring"
})
public class CmonkeyRunParameters {

    @JsonProperty("series_ref")
    private String seriesRef;
    @JsonProperty("genome_ref")
    private String genomeRef;
    @JsonProperty("operome_ref")
    private String operomeRef;
    @JsonProperty("network_ref")
    private String networkRef;
    @JsonProperty("networks_scoring")
    private Long networksScoring;
    @JsonProperty("motifs_scoring")
    private Long motifsScoring;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("series_ref")
    public String getSeriesRef() {
        return seriesRef;
    }

    @JsonProperty("series_ref")
    public void setSeriesRef(String seriesRef) {
        this.seriesRef = seriesRef;
    }

    public CmonkeyRunParameters withSeriesRef(String seriesRef) {
        this.seriesRef = seriesRef;
        return this;
    }

    @JsonProperty("genome_ref")
    public String getGenomeRef() {
        return genomeRef;
    }

    @JsonProperty("genome_ref")
    public void setGenomeRef(String genomeRef) {
        this.genomeRef = genomeRef;
    }

    public CmonkeyRunParameters withGenomeRef(String genomeRef) {
        this.genomeRef = genomeRef;
        return this;
    }

    @JsonProperty("operome_ref")
    public String getOperomeRef() {
        return operomeRef;
    }

    @JsonProperty("operome_ref")
    public void setOperomeRef(String operomeRef) {
        this.operomeRef = operomeRef;
    }

    public CmonkeyRunParameters withOperomeRef(String operomeRef) {
        this.operomeRef = operomeRef;
        return this;
    }

    @JsonProperty("network_ref")
    public String getNetworkRef() {
        return networkRef;
    }

    @JsonProperty("network_ref")
    public void setNetworkRef(String networkRef) {
        this.networkRef = networkRef;
    }

    public CmonkeyRunParameters withNetworkRef(String networkRef) {
        this.networkRef = networkRef;
        return this;
    }

    @JsonProperty("networks_scoring")
    public Long getNetworksScoring() {
        return networksScoring;
    }

    @JsonProperty("networks_scoring")
    public void setNetworksScoring(Long networksScoring) {
        this.networksScoring = networksScoring;
    }

    public CmonkeyRunParameters withNetworksScoring(Long networksScoring) {
        this.networksScoring = networksScoring;
        return this;
    }

    @JsonProperty("motifs_scoring")
    public Long getMotifsScoring() {
        return motifsScoring;
    }

    @JsonProperty("motifs_scoring")
    public void setMotifsScoring(Long motifsScoring) {
        this.motifsScoring = motifsScoring;
    }

    public CmonkeyRunParameters withMotifsScoring(Long motifsScoring) {
        this.motifsScoring = motifsScoring;
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
        return ((((((((((((((("CmonkeyRunParameters"+" [seriesRef=")+ seriesRef)+", genomeRef=")+ genomeRef)+", operomeRef=")+ operomeRef)+", networkRef=")+ networkRef)+", networksScoring=")+ networksScoring)+", motifsScoring=")+ motifsScoring)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
