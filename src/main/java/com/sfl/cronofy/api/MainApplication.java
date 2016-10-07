package com.sfl.cronofy.api;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.sfl.cronofy.api.client.CronofyClient;
import com.sfl.cronofy.api.client.impl.CronofyClientImpl;

import javax.ws.rs.client.ClientBuilder;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/7/16
 * Time: 8:30 PM
 */
public class MainApplication {

    public static void main(String[] args) {
        final CronofyClient cronofyClient = new CronofyClientImpl(ClientBuilder.newBuilder().register(JacksonJsonProvider.class).build());

//        NMbGmqHu0T1kmGPsy0UPxdH7JQrZxVTC
    }

}
