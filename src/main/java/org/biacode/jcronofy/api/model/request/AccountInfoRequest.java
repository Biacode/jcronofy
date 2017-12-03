package org.biacode.jcronofy.api.model.request;

import org.biacode.jcronofy.api.model.common.AbstractAccessTokenAwareCronofyRequest;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/4/16
 * Time: 9:23 PM
 */
public class AccountInfoRequest extends AbstractAccessTokenAwareCronofyRequest {
    private static final long serialVersionUID = -2951667001172541948L;

    //region Properties
    //endregion

    //region Constructors
    public AccountInfoRequest() {
    }

    public AccountInfoRequest(final String accessToken) {
        super(accessToken);
    }
    //endregion

    //region Equals, HashCode and ToString
    //endregion

    //region Properties getters and setters
    //endregion
}
