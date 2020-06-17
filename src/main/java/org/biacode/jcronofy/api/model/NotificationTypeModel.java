package org.biacode.jcronofy.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User: Arthur Asatryan
 * Date: 10/4/16
 * Time: 8:01 PM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public enum NotificationTypeModel {
    @JsonProperty("verification")
    VERIFICATION("verification"),
    @JsonProperty("change")
    CHANGE("change"),
    @JsonProperty("profile_disconnected")
    PROFILE_DISCONNECTED("profile_disconnected");

    String type;

    NotificationTypeModel(final String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
