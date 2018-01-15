package org.biacode.jcronofy.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * User: Syuzanna Eprikyan
 * Company: SFL LLC
 * Date: 1/12/18
 * Time: 5:37 PM
 */
public class RequiredDurationModel implements Serializable {
    private static final long serialVersionUID = 317389052507966285L;

    //region Properties
    @JsonProperty("minutes")
    private int minutes;
    //endregion

    //region Constructors
    public RequiredDurationModel() {
        // default constructor
    }

    public RequiredDurationModel(final int minutes) {
        this.minutes = minutes;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RequiredDurationModel)) {
            return false;
        }
        final RequiredDurationModel that = (RequiredDurationModel) o;
        return new EqualsBuilder()
                .append(minutes, that.minutes)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(minutes)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("minutes", minutes)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(final int minutes) {
        this.minutes = minutes;
    }
    //endregion
}
