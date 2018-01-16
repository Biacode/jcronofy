package org.biacode.jcronofy.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User: Arthur Asatryan
 * Date: 10/5/16
 * Time: 3:25 PM
 */
public enum ScopeModel {
    @JsonProperty("read_events")
    READ_EVENTS("read_events"),
    @JsonProperty("create_event")
    CREATE_EVENT("create_event"),
    @JsonProperty("delete_event")
    DELETE_EVENT("delete_event"),
    @JsonProperty("read_free_busy")
    READ_FREE_BUSY("read_free_busy"),
    @JsonProperty("create_calendar")
    CREATE_CALENDAR("create_calendar"),
    @JsonProperty("event_reminders")
    EVENT_REMINDERS("event_reminders"),
    @JsonProperty("change_participation_status")
    CHANGE_PARTICIPATION_STATUS("change_participation_status"),
    @JsonProperty("full_access")
    FULL_ACCESS("full_access");

    final String scope;

    ScopeModel(final String scope) {
        this.scope = scope;
    }

    public String getScope() {
        return scope;
    }
}
