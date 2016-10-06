package com.sfl.cronofy.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/4/16
 * Time: 6:17 PM
 */
public enum EventStatusModel {
    @JsonProperty("tentative")
    TENTATIVE("tentative"),
    @JsonProperty("confirmed")
    CONFIRMED("confirmed"),
    @JsonProperty("cancelled")
    CANCELLED("cancelled"),
    @JsonProperty("unknown")
    UNKNOWN("unknown");

    String status;

    EventStatusModel(final String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
