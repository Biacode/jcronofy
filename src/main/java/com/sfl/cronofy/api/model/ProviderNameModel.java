package com.sfl.cronofy.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/4/16
 * Time: 4:37 PM
 */
public enum ProviderNameModel {
    @JsonProperty("apple")
    APPLE("apple"),
    @JsonProperty("cronofy")
    CRONOFY("cronofy"),
    @JsonProperty("exchange")
    EXCHANGE("exchange"),
    @JsonProperty("google")
    GOOGLE("google"),
    @JsonProperty("live_connect")
    LIVE_CONNECT("live_connect");

    String name;

    ProviderNameModel(final String provider) {
        this.name = provider;
    }

    public String getName() {
        return name;
    }
}
