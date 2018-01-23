package org.biacode.jcronofy.api.model.request;

import org.biacode.jcronofy.api.model.common.AbstractAccessTokenAwareCronofyRequest;

/**
 * User: Syuzanna Eprikyan
 * Date: 1/15/18
 * Time: 3:23 PM
 */
public class UserInfoRequest extends AbstractAccessTokenAwareCronofyRequest {
    private static final long serialVersionUID = 5043917780641979851L;

    //region Properties
    //endregion

    //region Constructors
    public UserInfoRequest() {
        // default constructor
    }

    public UserInfoRequest(final String accessToken) {
        super(accessToken);
    }
    //endregion

    //region Equals, HashCode and ToString
    //endregion

    //region Properties getters and setters
    //endregion
}
