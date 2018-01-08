package org.biacode.jcronofy.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User: Arthur Asatryan
 * Date: 10/4/16
 * Time: 3:37 PM
 */
public enum GrantTypeModel {
    @JsonProperty("authorization_code")
    AUTHORIZATION_CODE("authorization_code"),
    @JsonProperty("refresh_token")
    REFRESH_TOKEN("refresh_token");

    final String grantType;

    GrantTypeModel(final String grantType) {
        this.grantType = grantType;
    }

    public String getGrantType() {
        return grantType;
    }
}
