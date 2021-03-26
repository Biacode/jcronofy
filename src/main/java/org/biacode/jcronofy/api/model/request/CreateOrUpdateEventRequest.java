package org.biacode.jcronofy.api.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.biacode.jcronofy.api.model.EventAttendeesModel;
import org.biacode.jcronofy.api.model.EventLocationModel;
import org.biacode.jcronofy.api.model.EventReminderModel;
import org.biacode.jcronofy.api.model.TransparencyModel;
import org.biacode.jcronofy.api.model.common.AbstractAccessTokenAwareCronofyRequest;

import java.util.Date;
import java.util.List;

/**
 * User: Arthur Asatryan
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "GMT")
    @JsonProperty("start")
    private Date start;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "GMT")
    @JsonProperty("end")
    private Date end;

    @JsonProperty("tzid")
    private String tzid;

    @JsonProperty("location")
    private EventLocationModel location;

    @JsonProperty("url")
    private String url;

    @JsonProperty("transparency")
    private TransparencyModel transparency;

    @JsonProperty("color")
    private String color;

    @JsonProperty("reminders")
    private List<EventReminderModel> reminders;

    @JsonProperty("reminders_create_only")
    private Boolean remindersCreateOnly;

    @JsonProperty("attendees")
    private EventAttendeesModel attendees;

    @JsonProperty("event_private")
    private boolean eventPrivate = false;

    @JsonProperty("include_userinfo")
    private Boolean includeUserInfo;
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
                                      final String tzid,
                                      final EventLocationModel location) {
        super(accessToken);
        this.calendarId = calendarId;
        this.eventId = eventId;
        this.summary = summary;
        this.description = description;
        this.start = ObjectUtils.clone(start);
        this.end = ObjectUtils.clone(end);
        this.tzid = tzid;
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
                .append(tzid, that.tzid)
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
                .append(tzid)
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
                .append("tzid", tzid)
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

    public String getTzid() {
        return tzid;
    }

    public void setTzid(final String tzid) {
        this.tzid = tzid;
    }

    public EventLocationModel getLocation() {
        return location;
    }

    public void setLocation(final EventLocationModel location) {
        this.location = location;
    }

    public String getUrl() {
        return url;
    }

    public TransparencyModel getTransparency() {
        return transparency;
    }

    public String getColor() {
        return color;
    }

    public List<EventReminderModel> getReminders() {
        return reminders;
    }

    public Boolean getRemindersCreateOnly() {
        return remindersCreateOnly;
    }

    public EventAttendeesModel getAttendees() {
        return attendees;
    }

    public boolean isEventPrivate() {
        return eventPrivate;
    }

    public Boolean getIncludeUserInfo() {
        return includeUserInfo;
    }

    public void setTransparency(TransparencyModel transparency) {
        this.transparency = transparency;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setReminders(List<EventReminderModel> reminders) {
        this.reminders = reminders;
    }

    public void setRemindersCreateOnly(Boolean remindersCreateOnly) {
        this.remindersCreateOnly = remindersCreateOnly;
    }

    public void setAttendees(EventAttendeesModel attendees) {
        this.attendees = attendees;
    }

    public void setEventPrivate(boolean eventPrivate) {
        this.eventPrivate = eventPrivate;
    }

    public void setIncludeUserInfo(Boolean includeUserInfo) {
        this.includeUserInfo = includeUserInfo;
    }
    //endregion
}
