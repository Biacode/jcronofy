package org.biacode.jcronofy.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * User: Syuzanna Eprikyan
 * Company: SFL LLC
 * Date: 1/12/18
 * Time: 4:46 PM
 */
public class AvailablePeriodModel implements Serializable {
    private static final long serialVersionUID = -2515145833874002461L;

    //region Properties
    @JsonProperty("start")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "GMT")
    private Date start;

    @JsonProperty("end")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "GMT")
    private Date end;

    @JsonProperty("participants")
    private ParticipantModel[] participants;
    //endregion

    //region Constructors
    public AvailablePeriodModel() {
        // default constructor
    }

    public AvailablePeriodModel(final Date start, final Date end, final ParticipantModel[] participants) {
        this.start = ObjectUtils.clone(start);
        this.end = ObjectUtils.clone(end);
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
    public Date getStart() {
        return ObjectUtils.clone(start);
    }

    public void setStart(final Date start) {
        this.start = ObjectUtils.clone(start);
    }

    public Date getEnd() {
        return ObjectUtils.clone(end);
    }

    public void setEnd(final Date end) {
        this.end = ObjectUtils.clone(end);
    }

    public ParticipantModel[] getParticipants() {
        return participants;
    }

    public void setParticipants(final ParticipantModel[] participants) {
        this.participants = participants;
    }
    //endregion
}
