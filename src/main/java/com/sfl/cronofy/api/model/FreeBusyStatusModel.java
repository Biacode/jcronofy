package com.sfl.cronofy.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/4/16
 * Time: 6:41 PM
 */
public enum FreeBusyStatusModel {
    @JsonProperty("tentative")
    TENTATIVE("tentative"),
    @JsonProperty("busy")
    BUSY("busy"),
    @JsonProperty("free")
    FREE("free"),
    @JsonProperty("unknown")
    UNKNOWN("unknown");

    String status;

    FreeBusyStatusModel(final String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
