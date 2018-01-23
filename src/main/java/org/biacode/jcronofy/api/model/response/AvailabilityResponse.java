package org.biacode.jcronofy.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.biacode.jcronofy.api.model.AvailablePeriodModel;
import org.biacode.jcronofy.api.model.common.AbstractCronofyResponse;

import java.util.List;

/**
 * User: Syuzanna Eprikyan
 * Date: 1/12/18
 * Time: 4:53 PM
 */
public class AvailabilityResponse extends AbstractCronofyResponse {
    private static final long serialVersionUID = 8453322717395593474L;

    //region Properties
    @JsonProperty("available_periods")
    private List<AvailablePeriodModel> availablePeriods;
    //endregion

    //region Constructors
    public AvailabilityResponse() {
        // default constructor
    }

    public AvailabilityResponse(final List<AvailablePeriodModel> availablePeriods) {
        this.availablePeriods = availablePeriods;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AvailabilityResponse)) {
            return false;
        }
        final AvailabilityResponse that = (AvailabilityResponse) o;
        return new EqualsBuilder()
                .append(availablePeriods, that.availablePeriods)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(availablePeriods)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("availablePeriods", availablePeriods)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public List<AvailablePeriodModel> getAvailablePeriods() {
        return availablePeriods;
    }

    public void setAvailablePeriods(final List<AvailablePeriodModel> availablePeriods) {
        this.availablePeriods = availablePeriods;
    }
    //endregion
}
