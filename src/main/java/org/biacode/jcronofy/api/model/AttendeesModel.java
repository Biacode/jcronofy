package org.biacode.jcronofy.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * User: Arthur Asatryan
 * Date: 10/4/16
 * Time: 6:21 PM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttendeesModel implements Serializable {
    private static final long serialVersionUID = 7074092078187307730L;

    //region Properties
    @JsonProperty("email")
    private String email;

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("status")
    private ParticipationStatusModel status;
    //endregion

    //region Constructors
    public AttendeesModel() {
    }

    public AttendeesModel(final String email,
                          final String displayName,
                          final ParticipationStatusModel status) {
        this.email = email;
        this.displayName = displayName;
        this.status = status;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AttendeesModel)) {
            return false;
        }
        final AttendeesModel that = (AttendeesModel) o;
        return new EqualsBuilder()
                .append(email, that.email)
                .append(displayName, that.displayName)
                .append(status, that.status)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(email)
                .append(displayName)
                .append(status)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("email", email)
                .append("displayName", displayName)
                .append("status", status)
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

    public ParticipationStatusModel getStatus() {
        return status;
    }

    public void setStatus(final ParticipationStatusModel status) {
        this.status = status;
    }
    //endregion
}
