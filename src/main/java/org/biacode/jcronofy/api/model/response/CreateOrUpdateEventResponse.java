package org.biacode.jcronofy.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.biacode.jcronofy.api.model.UserModel;
import org.biacode.jcronofy.api.model.common.AbstractCronofyResponse;

/**
 * User: Arthur Asatryan
 * Date: 10/4/16
 * Time: 4:54 PM
 */
public class CreateOrUpdateEventResponse extends AbstractCronofyResponse {
    private static final long serialVersionUID = -5806970375216708628L;

    //region Properties
    @JsonProperty("userinfo")
    private UserModel userInfo;
    //endregion

    //region Constructors
    public CreateOrUpdateEventResponse() {
    }

    public CreateOrUpdateEventResponse(UserModel userInfo) {
        this.userInfo = userInfo;
    }
    //endregion

    //region Equals, HashCode and ToString
    //endregion

    //region Properties getters and setters
    //endregion
}
