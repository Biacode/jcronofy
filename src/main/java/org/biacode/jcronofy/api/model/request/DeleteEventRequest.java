package org.biacode.jcronofy.api.model.request;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.biacode.jcronofy.api.model.common.AbstractAccessTokenAwareCronofyRequest;

import javax.ws.rs.QueryParam;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/4/16
 * Time: 4:57 PM
 */
public class DeleteEventRequest extends AbstractAccessTokenAwareCronofyRequest {
    private static final long serialVersionUID = 4693503341407285500L;

    //region Properties
    private String calendarId;

    @QueryParam("event_id")
    private String eventId;
    //endregion

    //region Constructors
    public DeleteEventRequest() {
    }

    public DeleteEventRequest(final String accessToken,
                              final String calendarId,
                              final String eventId) {
        super(accessToken);
        this.calendarId = calendarId;
        this.eventId = eventId;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeleteEventRequest)) {
            return false;
        }
        final DeleteEventRequest request = (DeleteEventRequest) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(calendarId, request.calendarId)
                .append(eventId, request.eventId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(calendarId)
                .append(eventId)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("calendarId", calendarId)
                .append("eventId", eventId)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public String getEventId() {
        return eventId;
    }

    public void setEventId(final String eventId) {
        this.eventId = eventId;
    }

    public String getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(final String calendarId) {
        this.calendarId = calendarId;
    }
    //endregion
}
