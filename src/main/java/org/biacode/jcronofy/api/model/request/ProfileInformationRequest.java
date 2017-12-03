package org.biacode.jcronofy.api.model.request;

import org.biacode.jcronofy.api.model.common.AbstractAccessTokenAwareCronofyRequest;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/4/16
 * Time: 9:37 PM
 */
public class ProfileInformationRequest extends AbstractAccessTokenAwareCronofyRequest {
    private static final long serialVersionUID = -410840732946038823L;

    //region Properties
    //endregion

    //region Constructors
    public ProfileInformationRequest() {
    }

    public ProfileInformationRequest(final String accessToken) {
        super(accessToken);
    }
    //endregion

    //region Equals, HashCode and ToString
    //endregion

    //region Properties getters and setters
    //endregion
}
