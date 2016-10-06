package com.sfl.cronofy.api.model.request;

import com.sfl.cronofy.api.model.common.AbstractAccessTokenAwareCronofyRequest;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/4/16
 * Time: 9:20 PM
 */
public class ListNotificationChannelsRequest extends AbstractAccessTokenAwareCronofyRequest {
    private static final long serialVersionUID = -8400799977008273804L;

    //region Properties
    //endregion

    //region Constructors
    public ListNotificationChannelsRequest() {
    }

    public ListNotificationChannelsRequest(final String accessToken) {
        super(accessToken);
    }
    //endregion

    //region Equals, HashCode and ToString
    //endregion

    //region Properties getters and setters
    //endregion
}
