package org.biacode.jcronofy.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/4/16
 * Time: 3:43 PM
 */
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
