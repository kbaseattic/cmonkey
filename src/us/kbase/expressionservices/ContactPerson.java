
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
 * <p>Original spec-file type: ContactPerson</p>
 * <pre>
 * Data structure for GSM ContactPerson
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "contact_first_name",
    "contact_last_name",
    "contact_institution"
})
public class ContactPerson {

    @JsonProperty("contact_first_name")
    private String contactFirstName;
    @JsonProperty("contact_last_name")
    private String contactLastName;
    @JsonProperty("contact_institution")
    private String contactInstitution;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("contact_first_name")
    public String getContactFirstName() {
        return contactFirstName;
    }

    @JsonProperty("contact_first_name")
    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    public ContactPerson withContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
        return this;
    }

    @JsonProperty("contact_last_name")
    public String getContactLastName() {
        return contactLastName;
    }

    @JsonProperty("contact_last_name")
    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public ContactPerson withContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
        return this;
    }

    @JsonProperty("contact_institution")
    public String getContactInstitution() {
        return contactInstitution;
    }

    @JsonProperty("contact_institution")
    public void setContactInstitution(String contactInstitution) {
        this.contactInstitution = contactInstitution;
    }

    public ContactPerson withContactInstitution(String contactInstitution) {
        this.contactInstitution = contactInstitution;
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
        return ((((((((("ContactPerson"+" [contactFirstName=")+ contactFirstName)+", contactLastName=")+ contactLastName)+", contactInstitution=")+ contactInstitution)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
