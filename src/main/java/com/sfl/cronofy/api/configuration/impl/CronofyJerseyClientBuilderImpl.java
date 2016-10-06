package com.sfl.cronofy.api.configuration.impl;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.sfl.cronofy.api.configuration.CronofyJerseyClientBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/5/16
 * Time: 11:36 AM
 */
public class CronofyJerseyClientBuilderImpl implements CronofyJerseyClientBuilder {

    //region Constructors
    public CronofyJerseyClientBuilderImpl() {
    }
    //endregion

    //region Public methods
    public Client build() {
        return ClientBuilder.newBuilder().register(JacksonJsonProvider.class).build();
    }
    //endregion

}
