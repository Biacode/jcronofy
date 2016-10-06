package com.sfl.cronofy.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/4/16
 * Time: 5:40 PM
 */
public class EventModel implements Serializable {
    private static final long serialVersionUID = -5787530243074397871L;

    //region Properties
    @JsonProperty("calendar_id")
    private String calendarId;

    @JsonProperty("event_uid")
    private String eventUid;

    @JsonProperty("summary")
    private String summary;

    @JsonProperty("description")
    private String description;

    @JsonProperty("start")
    private Date start;

    @JsonProperty("end")
    private Date end;

    @JsonProperty("deleted")
    private boolean deleted;

    @JsonProperty("location")
    private EventLocationModel location;

    @JsonProperty("event_id")
    private String eventId;

    @JsonProperty("participation_status")
    private ParticipationStatusModel participationStatus;

    @JsonProperty("transparency")
    private TransparencyModel transparency;

    @JsonProperty("status")
    private EventStatusModel status;

    @JsonProperty("categories")
    private List<String> categories;

    @JsonProperty("attendees")
    private List<AttendeesModel> attendees;

    @JsonProperty("created")
    private Date created;

    @JsonProperty("updated")
    private Date updated;

    @JsonProperty("recurring")
    private boolean recurring;

    @JsonProperty("options")
    private OptionModel options;
    //endregion

    //region Constructors
    public EventModel() {
    }

    public EventModel(final String calendarId,
                      final String eventUid,
                      final String summary,
                      final String description,
                      final Date start,
                      final Date end,
                      final boolean deleted,
                      final EventLocationModel location,
                      final String eventId,
                      final ParticipationStatusModel participationStatus,
                      final TransparencyModel transparency,
                      final EventStatusModel status,
                      final List<String> categories,
                      final List<AttendeesModel> attendees,
                      final Date created,
                      final Date updated,
                      final boolean recurring,
                      final OptionModel options) {
        this.calendarId = calendarId;
        this.eventUid = eventUid;
        this.summary = summary;
        this.description = description;
        this.start = ObjectUtils.clone(start);
        this.end = ObjectUtils.clone(end);
        this.deleted = deleted;
        this.location = location;
        this.eventId = eventId;
        this.participationStatus = participationStatus;
        this.transparency = transparency;
        this.status = status;
        this.categories = categories;
        this.attendees = attendees;
        this.created = ObjectUtils.clone(created);
        this.updated = ObjectUtils.clone(updated);
        this.recurring = recurring;
        this.options = options;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EventModel)) {
            return false;
        }
        final EventModel that = (EventModel) o;
        return new EqualsBuilder()
                .append(deleted, that.deleted)
                .append(calendarId, that.calendarId)
                .append(eventUid, that.eventUid)
                .append(summary, that.summary)
                .append(description, that.description)
                .append(start, that.start)
                .append(end, that.end)
                .append(location, that.location)
                .append(eventId, that.eventId)
                .append(participationStatus, that.participationStatus)
                .append(transparency, that.transparency)
                .append(status, that.status)
                .append(categories, that.categories)
                .append(attendees, that.attendees)
                .append(created, that.created)
                .append(updated, that.updated)
                .append(recurring, that.recurring)
                .append(options, that.options)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(calendarId)
                .append(eventUid)
                .append(summary)
                .append(description)
                .append(start)
                .append(end)
                .append(deleted)
                .append(location)
                .append(eventId)
                .append(participationStatus)
                .append(transparency)
                .append(status)
                .append(categories)
                .append(attendees)
                .append(created)
                .append(updated)
                .append(recurring)
                .append(options)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("calendarId", calendarId)
                .append("eventUid", eventUid)
                .append("summary", summary)
                .append("description", description)
                .append("start", start)
                .append("end", end)
                .append("deleted", deleted)
                .append("location", location)
                .append("eventId", eventId)
                .append("participationStatus", participationStatus)
                .append("transparency", transparency)
                .append("status", status)
                .append("categories", categories)
                .append("attendees", attendees)
                .append("created", created)
                .append("updated", updated)
                .append("recurring", recurring)
                .append("options", options)
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

    public String getEventUid() {
        return eventUid;
    }

    public void setEventUid(final String eventUid) {
        this.eventUid = eventUid;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(final boolean deleted) {
        this.deleted = deleted;
    }

    public EventLocationModel getLocation() {
        return location;
    }

    public void setLocation(final EventLocationModel location) {
        this.location = location;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(final String eventId) {
        this.eventId = eventId;
    }

    public ParticipationStatusModel getParticipationStatus() {
        return participationStatus;
    }

    public void setParticipationStatus(final ParticipationStatusModel participationStatus) {
        this.participationStatus = participationStatus;
    }

    public TransparencyModel getTransparency() {
        return transparency;
    }

    public void setTransparency(final TransparencyModel transparency) {
        this.transparency = transparency;
    }

    public EventStatusModel getStatus() {
        return status;
    }

    public void setStatus(final EventStatusModel status) {
        this.status = status;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(final List<String> categories) {
        this.categories = categories;
    }

    public List<AttendeesModel> getAttendees() {
        return attendees;
    }

    public void setAttendees(final List<AttendeesModel> attendees) {
        this.attendees = attendees;
    }

    public Date getCreated() {
        return ObjectUtils.clone(created);
    }

    public void setCreated(final Date created) {
        this.created = ObjectUtils.clone(created);
    }

    public Date getUpdated() {
        return ObjectUtils.clone(updated);
    }

    public void setUpdated(final Date updated) {
        this.updated = ObjectUtils.clone(updated);
    }

    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(final boolean recurring) {
        this.recurring = recurring;
    }

    public OptionModel getOptions() {
        return options;
    }

    public void setOptions(final OptionModel options) {
        this.options = options;
    }
    //endregion

}
