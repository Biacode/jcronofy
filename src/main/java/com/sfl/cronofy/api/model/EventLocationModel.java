package com.sfl.cronofy.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/4/16
 * Time: 4:52 PM
 */
public class EventLocationModel implements Serializable {
    private static final long serialVersionUID = -1713440242581317732L;

    //region Properties
    @JsonProperty("description")
    private String description;
    //endregion

    //region Constructors
    public EventLocationModel() {
    }

    public EventLocationModel(final String description) {
        this.description = description;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EventLocationModel)) {
            return false;
        }
        final EventLocationModel that = (EventLocationModel) o;
        return new EqualsBuilder()
                .append(description, that.description)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(description)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("description", description)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
    //endregion
}
