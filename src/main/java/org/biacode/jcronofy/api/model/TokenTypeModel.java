package org.biacode.jcronofy.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User: Arthur Asatryan
 * Date: 10/4/16
 * Time: 3:43 PM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public enum TokenTypeModel {
    @JsonProperty("bearer")
    BEARER("bearer");

    String tokenType;

    TokenTypeModel(final String tokenType) {
        this.tokenType = tokenType;
    }

    public String getTokenType() {
        return tokenType;
    }
}
