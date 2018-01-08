package org.biacode.jcronofy.api.model;

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

    @JsonProperty("lat")
    private String latitude;

    @JsonProperty("long")
    private String longitude;
    //endregion

    //region Constructors
    public EventLocationModel() {
    }

    public EventLocationModel(final String description, final String latitude, final String longitude) {
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
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
                .append(latitude, that.latitude)
                .append(longitude, that.longitude)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(description)
                .append(latitude)
                .append(longitude)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("description", description)
                .append("latitude", latitude)
                .append("longitude", longitude)
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(final String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(final String longitude) {
        this.longitude = longitude;
    }
    //endregion
}
