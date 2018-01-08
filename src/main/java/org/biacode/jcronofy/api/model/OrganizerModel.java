package org.biacode.jcronofy.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * User: Syuzanna Eprikyan
 * Date: 1/8/18
 * Time: 4:13 PM
 */
public class OrganizerModel implements Serializable {
    private static final long serialVersionUID = 8221314847860060536L;

    //region Properties
    @JsonProperty("email")
    private String email;

    @JsonProperty("display_name")
    private String displayName;
    //endregion

    //region Constructors
    public OrganizerModel() {
    }

    public OrganizerModel(final String email, final String displayName) {
        this.email = email;
        this.displayName = displayName;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrganizerModel)) {
            return false;
        }
        final OrganizerModel that = (OrganizerModel) o;
        return new EqualsBuilder()
                .append(email, that.email)
                .append(displayName, that.displayName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(email)
                .append(displayName)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("email", email)
                .append("displayName", displayName)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(final String displayName) {
        this.displayName = displayName;
    }
    //endregion
}
