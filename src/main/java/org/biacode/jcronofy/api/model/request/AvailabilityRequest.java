package org.biacode.jcronofy.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.biacode.jcronofy.api.model.AvailablePeriodModel;
import org.biacode.jcronofy.api.model.ParticipantModel;
import org.biacode.jcronofy.api.model.RequiredDurationModel;
import org.biacode.jcronofy.api.model.common.AbstractAccessTokenAwareCronofyRequest;

import java.util.List;

/**
 * User: Syuzanna Eprikyan
 * Company: SFL LLC
 * Date: 1/12/18
 * Time: 5:03 PM
 */
public class AvailabilityRequest extends AbstractAccessTokenAwareCronofyRequest {
    private static final long serialVersionUID = -6939300839527935872L;

    //region Properties
    @JsonProperty("participants")
    private List<ParticipantModel> participants;

    @JsonProperty("required_duration")
    private RequiredDurationModel requiredDuration;

    @JsonProperty("available_periods")
    private List<AvailablePeriodModel> availablePeriods;
    //endregion

    //region Constructors
    public AvailabilityRequest() {
    }

    public AvailabilityRequest(final String accessToken, final List<ParticipantModel> participants) {
        super(accessToken);
        this.participants = participants;
    }

    public AvailabilityRequest(final String accessToken, final List<ParticipantModel> participants, final RequiredDurationModel requiredDuration, final List<AvailablePeriodModel> availablePeriods) {
        super(accessToken);
        this.participants = participants;
        this.requiredDuration = requiredDuration;
        this.availablePeriods = availablePeriods;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AvailabilityRequest)) {
            return false;
        }
        final AvailabilityRequest that = (AvailabilityRequest) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(participants, that.participants)
                .append(requiredDuration, that.requiredDuration)
                .append(availablePeriods, that.availablePeriods)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(participants)
                .append(requiredDuration)
                .append(availablePeriods)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("participants", participants)
                .append("requiredDuration", requiredDuration)
                .append("availablePeriods", availablePeriods)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public RequiredDurationModel getRequiredDuration() {
        return requiredDuration;
    }

    public void setRequiredDuration(final RequiredDurationModel requiredDuration) {
        this.requiredDuration = requiredDuration;
    }

    public void setParticipants(final List<ParticipantModel> participants) {
        this.participants = participants;
    }

    public List<AvailablePeriodModel> getAvailablePeriods() {
        return availablePeriods;
    }

    public void setAvailablePeriods(final List<AvailablePeriodModel> availablePeriods) {
        this.availablePeriods = availablePeriods;
    }
    //endregion
}
