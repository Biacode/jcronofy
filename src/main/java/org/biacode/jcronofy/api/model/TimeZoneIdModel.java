package org.biacode.jcronofy.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User: Arthur Asatryan
 * Date: 10/7/16
 * Time: 9:20 PM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
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
