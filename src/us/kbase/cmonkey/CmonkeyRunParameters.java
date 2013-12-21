
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
 * expression_series_ws_id series_id - workspace id of expression data series;
 * genome_ws_id genome_id - workspace id of genome;
 * operome_ws_id operome_id - workspace id of operome;
 * network_ws_id network_id - workspace id of network;
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "series_id",
    "genome_id",
    "operome_id",
    "network_id",
    "networks_scoring",
    "motifs_scoring"
})
public class CmonkeyRunParameters {

    @JsonProperty("series_id")
    private String seriesId;
    @JsonProperty("genome_id")
    private String genomeId;
    @JsonProperty("operome_id")
    private String operomeId;
    @JsonProperty("network_id")
    private String networkId;
    @JsonProperty("networks_scoring")
    private Long networksScoring;
    @JsonProperty("motifs_scoring")
    private Long motifsScoring;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("series_id")
    public String getSeriesId() {
        return seriesId;
    }

    @JsonProperty("series_id")
    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public CmonkeyRunParameters withSeriesId(String seriesId) {
        this.seriesId = seriesId;
        return this;
    }

    @JsonProperty("genome_id")
    public String getGenomeId() {
        return genomeId;
    }

    @JsonProperty("genome_id")
    public void setGenomeId(String genomeId) {
        this.genomeId = genomeId;
    }

    public CmonkeyRunParameters withGenomeId(String genomeId) {
        this.genomeId = genomeId;
        return this;
    }

    @JsonProperty("operome_id")
    public String getOperomeId() {
        return operomeId;
    }

    @JsonProperty("operome_id")
    public void setOperomeId(String operomeId) {
        this.operomeId = operomeId;
    }

    public CmonkeyRunParameters withOperomeId(String operomeId) {
        this.operomeId = operomeId;
        return this;
    }

    @JsonProperty("network_id")
    public String getNetworkId() {
        return networkId;
    }

    @JsonProperty("network_id")
    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    public CmonkeyRunParameters withNetworkId(String networkId) {
        this.networkId = networkId;
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
        return ((((((((((((((("CmonkeyRunParameters"+" [seriesId=")+ seriesId)+", genomeId=")+ genomeId)+", operomeId=")+ operomeId)+", networkId=")+ networkId)+", networksScoring=")+ networksScoring)+", motifsScoring=")+ motifsScoring)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
