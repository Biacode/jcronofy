package com.sfl.cronofy.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sfl.cronofy.api.model.ChannelModel;
import com.sfl.cronofy.api.model.PushNotificationModel;
import com.sfl.cronofy.api.model.common.AbstractCronofyRequest;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/4/16
 * Time: 8:00 PM
 */
public class PushNotificationRequest extends AbstractCronofyRequest {
    private static final long serialVersionUID = 662671907698170189L;

    //region Properties
    @JsonProperty("notification")
    private PushNotificationModel notification;

    @JsonProperty("channel")
    private ChannelModel channel;
    //endregion

    //region Constructors
    //endregion

    //region Equals, HashCode and ToString
    //endregion

    //region Properties getters and setters
    //endregion
}
