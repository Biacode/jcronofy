package org.biacode.jcronofy.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User: Arthur Asatryan
 * Date: 10/4/16
 * Time: 6:17 PM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
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
