package org.biacode.jcronofy.api.client;

import javax.ws.rs.client.Client;

/**
 * User: Arthur Asatryan
 * Date: 10/5/16
 * Time: 11:43 AM
 */
public abstract class AbstractCronofyClient {

    //region Dependencies
    private Client client;
    //endregion

    //region Constructors
    public AbstractCronofyClient(final Client client) {
        this.client = client;
    }
    //endregion

    //region Public methods
    protected Client getClient() {
        return client;
    }
    //endregion
}
