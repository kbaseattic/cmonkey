
package us.kbase.kbasegenomes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import us.kbase.common.service.Tuple7;


/**
 * <p>Original spec-file type: Genome</p>
 * <pre>
 * Genome object holds much of the data relevant for a genome in KBase
 *         Genome publications should be papers about the genome, not
 *         papers about certain features of the genome (which go into the
 *         Feature object)
 *         Should the Genome object have a list of feature ids? (in
 *         addition to having a list of feature_refs)
 *         Should the Genome object contain a list of contig_ids too?
 * @optional contig_ids publications md5 taxonomy gc_content complete dna_size num_contigs contig_lengths contigset_ref proteinset_ref transcriptset_ref
 * @searchable ws_subset features.[*].(md5,id,type,function,aliases) taxonomy num_contigs source_id source genetic_code id scientific_name domain contigset_ref proteinset_ref transcriptset_ref
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "scientific_name",
    "domain",
    "genetic_code",
    "dna_size",
    "num_contigs",
    "contig_lengths",
    "contig_ids",
    "source",
    "source_id",
    "md5",
    "taxonomy",
    "gc_content",
    "complete",
    "publications",
    "features",
    "contigset_ref",
    "proteinset_ref",
    "transcriptset_ref"
})
public class Genome {

    @JsonProperty("id")
    private java.lang.String id;
    @JsonProperty("scientific_name")
    private java.lang.String scientificName;
    @JsonProperty("domain")
    private java.lang.String domain;
    @JsonProperty("genetic_code")
    private java.lang.Long geneticCode;
    @JsonProperty("dna_size")
    private java.lang.Long dnaSize;
    @JsonProperty("num_contigs")
    private java.lang.Long numContigs;
    @JsonProperty("contig_lengths")
    private List<Long> contigLengths;
    @JsonProperty("contig_ids")
    private List<String> contigIds;
    @JsonProperty("source")
    private java.lang.String source;
    @JsonProperty("source_id")
    private java.lang.String sourceId;
    @JsonProperty("md5")
    private java.lang.String md5;
    @JsonProperty("taxonomy")
    private java.lang.String taxonomy;
    @JsonProperty("gc_content")
    private Double gcContent;
    @JsonProperty("complete")
    private java.lang.Long complete;
    @JsonProperty("publications")
    private List<Tuple7 <Long, String, String, String, String, String, String>> publications;
    @JsonProperty("features")
    private List<Feature> features;
    @JsonProperty("contigset_ref")
    private java.lang.String contigsetRef;
    @JsonProperty("proteinset_ref")
    private java.lang.String proteinsetRef;
    @JsonProperty("transcriptset_ref")
    private java.lang.String transcriptsetRef;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("id")
    public java.lang.String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public Genome withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("scientific_name")
    public java.lang.String getScientificName() {
        return scientificName;
    }

    @JsonProperty("scientific_name")
    public void setScientificName(java.lang.String scientificName) {
        this.scientificName = scientificName;
    }

    public Genome withScientificName(java.lang.String scientificName) {
        this.scientificName = scientificName;
        return this;
    }

    @JsonProperty("domain")
    public java.lang.String getDomain() {
        return domain;
    }

    @JsonProperty("domain")
    public void setDomain(java.lang.String domain) {
        this.domain = domain;
    }

    public Genome withDomain(java.lang.String domain) {
        this.domain = domain;
        return this;
    }

    @JsonProperty("genetic_code")
    public java.lang.Long getGeneticCode() {
        return geneticCode;
    }

    @JsonProperty("genetic_code")
    public void setGeneticCode(java.lang.Long geneticCode) {
        this.geneticCode = geneticCode;
    }

    public Genome withGeneticCode(java.lang.Long geneticCode) {
        this.geneticCode = geneticCode;
        return this;
    }

    @JsonProperty("dna_size")
    public java.lang.Long getDnaSize() {
        return dnaSize;
    }

    @JsonProperty("dna_size")
    public void setDnaSize(java.lang.Long dnaSize) {
        this.dnaSize = dnaSize;
    }

    public Genome withDnaSize(java.lang.Long dnaSize) {
        this.dnaSize = dnaSize;
        return this;
    }

    @JsonProperty("num_contigs")
    public java.lang.Long getNumContigs() {
        return numContigs;
    }

    @JsonProperty("num_contigs")
    public void setNumContigs(java.lang.Long numContigs) {
        this.numContigs = numContigs;
    }

    public Genome withNumContigs(java.lang.Long numContigs) {
        this.numContigs = numContigs;
        return this;
    }

    @JsonProperty("contig_lengths")
    public List<Long> getContigLengths() {
        return contigLengths;
    }

    @JsonProperty("contig_lengths")
    public void setContigLengths(List<Long> contigLengths) {
        this.contigLengths = contigLengths;
    }

    public Genome withContigLengths(List<Long> contigLengths) {
        this.contigLengths = contigLengths;
        return this;
    }

    @JsonProperty("contig_ids")
    public List<String> getContigIds() {
        return contigIds;
    }

    @JsonProperty("contig_ids")
    public void setContigIds(List<String> contigIds) {
        this.contigIds = contigIds;
    }

    public Genome withContigIds(List<String> contigIds) {
        this.contigIds = contigIds;
        return this;
    }

    @JsonProperty("source")
    public java.lang.String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(java.lang.String source) {
        this.source = source;
    }

    public Genome withSource(java.lang.String source) {
        this.source = source;
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

    public Genome withSourceId(java.lang.String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    @JsonProperty("md5")
    public java.lang.String getMd5() {
        return md5;
    }

    @JsonProperty("md5")
    public void setMd5(java.lang.String md5) {
        this.md5 = md5;
    }

    public Genome withMd5(java.lang.String md5) {
        this.md5 = md5;
        return this;
    }

    @JsonProperty("taxonomy")
    public java.lang.String getTaxonomy() {
        return taxonomy;
    }

    @JsonProperty("taxonomy")
    public void setTaxonomy(java.lang.String taxonomy) {
        this.taxonomy = taxonomy;
    }

    public Genome withTaxonomy(java.lang.String taxonomy) {
        this.taxonomy = taxonomy;
        return this;
    }

    @JsonProperty("gc_content")
    public Double getGcContent() {
        return gcContent;
    }

    @JsonProperty("gc_content")
    public void setGcContent(Double gcContent) {
        this.gcContent = gcContent;
    }

    public Genome withGcContent(Double gcContent) {
        this.gcContent = gcContent;
        return this;
    }

    @JsonProperty("complete")
    public java.lang.Long getComplete() {
        return complete;
    }

    @JsonProperty("complete")
    public void setComplete(java.lang.Long complete) {
        this.complete = complete;
    }

    public Genome withComplete(java.lang.Long complete) {
        this.complete = complete;
        return this;
    }

    @JsonProperty("publications")
    public List<Tuple7 <Long, String, String, String, String, String, String>> getPublications() {
        return publications;
    }

    @JsonProperty("publications")
    public void setPublications(List<Tuple7 <Long, String, String, String, String, String, String>> publications) {
        this.publications = publications;
    }

    public Genome withPublications(List<Tuple7 <Long, String, String, String, String, String, String>> publications) {
        this.publications = publications;
        return this;
    }

    @JsonProperty("features")
    public List<Feature> getFeatures() {
        return features;
    }

    @JsonProperty("features")
    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public Genome withFeatures(List<Feature> features) {
        this.features = features;
        return this;
    }

    @JsonProperty("contigset_ref")
    public java.lang.String getContigsetRef() {
        return contigsetRef;
    }

    @JsonProperty("contigset_ref")
    public void setContigsetRef(java.lang.String contigsetRef) {
        this.contigsetRef = contigsetRef;
    }

    public Genome withContigsetRef(java.lang.String contigsetRef) {
        this.contigsetRef = contigsetRef;
        return this;
    }

    @JsonProperty("proteinset_ref")
    public java.lang.String getProteinsetRef() {
        return proteinsetRef;
    }

    @JsonProperty("proteinset_ref")
    public void setProteinsetRef(java.lang.String proteinsetRef) {
        this.proteinsetRef = proteinsetRef;
    }

    public Genome withProteinsetRef(java.lang.String proteinsetRef) {
        this.proteinsetRef = proteinsetRef;
        return this;
    }

    @JsonProperty("transcriptset_ref")
    public java.lang.String getTranscriptsetRef() {
        return transcriptsetRef;
    }

    @JsonProperty("transcriptset_ref")
    public void setTranscriptsetRef(java.lang.String transcriptsetRef) {
        this.transcriptsetRef = transcriptsetRef;
    }

    public Genome withTranscriptsetRef(java.lang.String transcriptsetRef) {
        this.transcriptsetRef = transcriptsetRef;
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
        return ((((((((((((((((((((((((((((((((((((((((("Genome"+" [id=")+ id)+", scientificName=")+ scientificName)+", domain=")+ domain)+", geneticCode=")+ geneticCode)+", dnaSize=")+ dnaSize)+", numContigs=")+ numContigs)+", contigLengths=")+ contigLengths)+", contigIds=")+ contigIds)+", source=")+ source)+", sourceId=")+ sourceId)+", md5=")+ md5)+", taxonomy=")+ taxonomy)+", gcContent=")+ gcContent)+", complete=")+ complete)+", publications=")+ publications)+", features=")+ features)+", contigsetRef=")+ contigsetRef)+", proteinsetRef=")+ proteinsetRef)+", transcriptsetRef=")+ transcriptsetRef)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
