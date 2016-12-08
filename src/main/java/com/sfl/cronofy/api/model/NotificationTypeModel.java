package com.sfl.cronofy.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/4/16
 * Time: 8:01 PM
 */
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
