package com.sfl.cronofy.api.configuration.impl;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.sfl.cronofy.api.configuration.CronofyJerseyClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/5/16
 * Time: 11:36 AM
 */
public class CronofyJerseyClientBuilderImpl implements CronofyJerseyClientBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(CronofyJerseyClientBuilderImpl.class);

    //region Constructors
    public CronofyJerseyClientBuilderImpl() {
        LOGGER.debug("Initializing cronofy jersey client build");
    }
    //endregion

    //region Public methods
    @Override
    public Client build() {
        return ClientBuilder.newBuilder().register(JacksonJsonProvider.class).build();
    }
    //endregion

}
