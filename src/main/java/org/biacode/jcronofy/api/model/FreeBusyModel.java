package org.biacode.jcronofy.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.biacode.jcronofy.api.model.converter.FreeBusyDateDeserializer;

import java.io.Serializable;

/**
 * User: Arthur Asatryan
 * Date: 10/4/16
 * Time: 6:38 PM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FreeBusyModel implements Serializable {
    private static final long serialVersionUID = -9149242550146191351L;

    //region Properties
    @JsonProperty("calendar_id")
    private String calendarId;

    @JsonProperty("start")
    @JsonDeserialize(using = FreeBusyDateDeserializer.class)
    private FreeBusyDateModel start;

    @JsonProperty("end")
    @JsonDeserialize(using = FreeBusyDateDeserializer.class)
    private FreeBusyDateModel end;

    @JsonProperty("free_busy_status")
    private FreeBusyStatusModel status;
    //endregion

    //region Constructors
    public FreeBusyModel() {
    }

    public FreeBusyModel(final String calendarId,
            final FreeBusyDateModel start,
             final FreeBusyDateModel end,
            final FreeBusyStatusModel status) {
        this.calendarId = calendarId;
        this.start = ObjectUtils.clone(start);
        this.end = ObjectUtils.clone(end);
        this.status = status;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FreeBusyModel)) {
            return false;
        }
        final FreeBusyModel that = (FreeBusyModel) o;
        return new EqualsBuilder()
                .append(calendarId, that.calendarId)
                .append(start, that.start)
                .append(end, that.end)
                .append(status, that.status)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(calendarId)
                .append(start)
                .append(end)
                .append(status)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("calendarId", calendarId)
                .append("start", start)
                .append("end", end)
                .append("status", status)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public String getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(final String calendarId) {
        this.calendarId = calendarId;
    }

    public FreeBusyDateModel getStart() {
        return ObjectUtils.clone(start);
    }

    public void setStart(final FreeBusyDateModel start) {
        this.start = start;
    }

    public FreeBusyDateModel getEnd() {
        return ObjectUtils.clone(end);
    }

    public void setEnd(final FreeBusyDateModel end) {
        this.end = end;
    }

    public FreeBusyStatusModel getStatus() {
        return status;
    }

    public void setStatus(final FreeBusyStatusModel status) {
        this.status = status;
    }
    //endregion
}
