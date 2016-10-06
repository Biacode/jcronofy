package com.sfl.cronofy.api.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sfl.cronofy.api.model.EventLocationModel;
import com.sfl.cronofy.api.model.common.AbstractAccessTokenAwareCronofyRequest;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/4/16
 * Time: 4:49 PM
 */
public class CreateOrUpdateEventRequest extends AbstractAccessTokenAwareCronofyRequest {
    private static final long serialVersionUID = 7933781677905060792L;

    //region Properties
    private String calendarId;

    @JsonProperty("event_id")
    private String eventId;

    @JsonProperty("summary")
    private String summary;

    @JsonProperty("description")
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "GMT")
    @JsonProperty("start")
    private Date start;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "GMT")
    @JsonProperty("end")
    private Date end;

    @JsonProperty("location")
    private EventLocationModel location;
    //endregion

    //region Constructors
    public CreateOrUpdateEventRequest() {
    }

    public CreateOrUpdateEventRequest(final String accessToken,
                                      final String calendarId,
                                      final String eventId,
                                      final String summary,
                                      final String description,
                                      final Date start,
                                      final Date end,
                                      final EventLocationModel location) {
        super(accessToken);
        this.calendarId = calendarId;
        this.eventId = eventId;
        this.summary = summary;
        this.description = description;
        this.start = ObjectUtils.clone(start);
        this.end = ObjectUtils.clone(end);
        this.location = location;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreateOrUpdateEventRequest)) {
            return false;
        }
        final CreateOrUpdateEventRequest that = (CreateOrUpdateEventRequest) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(calendarId, that.calendarId)
                .append(eventId, that.eventId)
                .append(summary, that.summary)
                .append(description, that.description)
                .append(start, that.start)
                .append(end, that.end)
                .append(location, that.location)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(calendarId)
                .append(eventId)
                .append(summary)
                .append(description)
                .append(start)
                .append(end)
                .append(location)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("calendarId", calendarId)
                .append("eventId", eventId)
                .append("summary", summary)
                .append("description", description)
                .append("start", start)
                .append("end", end)
                .append("location", location)
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

    public String getEventId() {
        return eventId;
    }

    public void setEventId(final String eventId) {
        this.eventId = eventId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(final String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
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

    public EventLocationModel getLocation() {
        return location;
    }

    public void setLocation(final EventLocationModel location) {
        this.location = location;
    }
    //endregion
}
