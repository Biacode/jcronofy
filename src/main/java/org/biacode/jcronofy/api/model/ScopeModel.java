package org.biacode.jcronofy.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
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
    READ_FREE_BUSY("read_free_busy");

    final String scope;

    ScopeModel(final String scope) {
        this.scope = scope;
    }

    public String getScope() {
        return scope;
    }
}
