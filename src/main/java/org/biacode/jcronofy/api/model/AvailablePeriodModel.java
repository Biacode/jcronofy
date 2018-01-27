package org.biacode.jcronofy.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * User: Syuzanna Eprikyan
 * Date: 1/12/18
 * Time: 4:46 PM
 */
public class AvailablePeriodModel implements Serializable {
    private static final long serialVersionUID = -2515145833874002461L;

    //region Properties
    @JsonProperty("start")
    private String start;

    @JsonProperty("end")
    private String end;

    @JsonProperty("participants")
    private List<ParticipantModel> participants;
    //endregion

    //region Constructors
    public AvailablePeriodModel() {
        // default constructor
    }

    public AvailablePeriodModel(final String start, final String end) {
        this.start = start;
        this.end = end;
    }

    public AvailablePeriodModel(final String start, final String end, final List<ParticipantModel> participants) {
        this.start = start;
        this.end = end;
        this.participants = participants;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AvailablePeriodModel)) {
            return false;
        }
        final AvailablePeriodModel that = (AvailablePeriodModel) o;
        return new EqualsBuilder()
                .append(start, that.start)
                .append(end, that.end)
                .append(participants, that.participants)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(start)
                .append(end)
                .append(participants)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("start", start)
                .append("end", end)
                .append("participants", participants)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public String getStart() {
        return start;
    }

    public void setStart(final String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(final String end) {
        this.end = end;
    }

    public List<ParticipantModel> getParticipants() {
        return participants;
    }

    public void setParticipants(final List<ParticipantModel> participants) {
        this.participants = participants;
    }
    //endregion
}
