package org.biacode.jcronofy.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User: Arthur Asatryan
 * Date: 10/5/16
 * Time: 9:11 PM
 */
public enum AccountTypeModel {
    @JsonProperty("account")
    ACCOUNT("account");

    String type;

    AccountTypeModel(final String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
