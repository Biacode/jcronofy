package org.biacode.jcronofy.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/4/16
 * Time: 6:38 PM
 */
public class FreeBusyModel implements Serializable {
    private static final long serialVersionUID = -9149242550146191351L;

    //region Properties
    @JsonProperty("calendar_id")
    private String calendarId;

    @JsonProperty("start")
    private Date start;

    @JsonProperty("end")
    private Date end;

    @JsonProperty("free_busy_status")
    private FreeBusyStatusModel status;
    //endregion

    //region Constructors
    public FreeBusyModel() {
    }

    public FreeBusyModel(final String calendarId,
                         final Date start,
                         final Date end,
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

    public FreeBusyStatusModel getStatus() {
        return status;
    }

    public void setStatus(final FreeBusyStatusModel status) {
        this.status = status;
    }
    //endregion
}
