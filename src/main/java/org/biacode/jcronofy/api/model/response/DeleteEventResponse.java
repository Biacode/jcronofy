package org.biacode.jcronofy.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.biacode.jcronofy.api.model.UserModel;
import org.biacode.jcronofy.api.model.common.AbstractCronofyResponse;

/**
 * User: Arthur Asatryan
 * Date: 10/4/16
 * Time: 5:00 PM
 */
public class DeleteEventResponse extends AbstractCronofyResponse {
    private static final long serialVersionUID = -2638554425639994540L;

    //region Properties
    @JsonProperty("userinfo")
    private UserModel userInfo;
    //endregion

    //region Constructors
    public DeleteEventResponse() {
    }
    //endregion

    //region Equals, HashCode and ToString
    //endregion

    //region Properties getters and setters
    public void setUserInfo(UserModel userInfo) {
        this.userInfo = userInfo;
    }
    //endregion
}
