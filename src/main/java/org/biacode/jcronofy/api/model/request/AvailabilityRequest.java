package org.biacode.jcronofy.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.biacode.jcronofy.api.model.ParticipantModel;
import org.biacode.jcronofy.api.model.common.AbstractAccessTokenAwareCronofyRequest;

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
    private ParticipantModel[] participants;

    //endregion

    //region Constructors
    public AvailabilityRequest() {
    }

    public AvailabilityRequest(final String accessToken, final ParticipantModel[] participants) {
        super(accessToken);
        this.participants = participants;
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
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(participants)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("participants", participants)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public ParticipantModel[] getParticipants() {
        return participants;
    }

    public void setParticipants(final ParticipantModel[] participants) {
        this.participants = participants;
    }
    //endregion
}
