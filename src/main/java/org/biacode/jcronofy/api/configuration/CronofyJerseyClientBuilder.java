package org.biacode.jcronofy.api.configuration;

import javax.ws.rs.client.Client;

/**
 * User: Arthur Asatryan
 * Date: 10/5/16
 * Time: 11:35 AM
 */
public interface CronofyJerseyClientBuilder {
    Client build();
}
