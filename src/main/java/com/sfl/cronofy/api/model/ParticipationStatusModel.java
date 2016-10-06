package com.sfl.cronofy.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/4/16
 * Time: 6:11 PM
 */
public enum ParticipationStatusModel {

    @JsonProperty("needs_action")
    NEEDS_ACTION("needs_action"),
    @JsonProperty("accepted")
    ACCEPTED("accepted"),
    @JsonProperty("declined")
    DECLINED("declined"),
    @JsonProperty("tentative")
    TENTATIVE("tentative"),
    @JsonProperty("unknown")
    UNKNOWM("unknown");

    String status;

    ParticipationStatusModel(final String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
