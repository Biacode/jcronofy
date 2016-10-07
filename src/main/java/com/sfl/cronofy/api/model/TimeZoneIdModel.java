package com.sfl.cronofy.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/7/16
 * Time: 9:20 PM
 */
public enum TimeZoneIdModel {
    @JsonProperty("Etc/UTC")
    UTC("Etc/UTC");

    final String tzId;

    TimeZoneIdModel(final String tzId) {
        this.tzId = tzId;
    }

    public String getTzId() {
        return tzId;
    }
}
