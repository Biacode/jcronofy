package org.biacode.jcronofy.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.biacode.jcronofy.api.model.GrantTypeModel;
import org.biacode.jcronofy.api.model.common.AbstractCronofyRequest;

/**
 * User: Arthur Asatryan
 * Date: 10/4/16
 * Time: 3:53 PM
 */
public class UpdateAccessTokenRequest extends AbstractCronofyRequest {
    private static final long serialVersionUID = -543743419070452488L;

    //region Properties
    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("client_secret")
    private String clientSecret;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("grant_type")
    private GrantTypeModel grantTypeModel;
    //endregion

    //region Constructors

    /**
     * Default grant type is REFRESH_TOKEN
     */
    public UpdateAccessTokenRequest() {
        this.grantTypeModel = GrantTypeModel.REFRESH_TOKEN;
    }

    /**
     * Default grant type is REFRESH_TOKEN
     */
    public UpdateAccessTokenRequest(final String clientId,
                                    final String clientSecret,
                                    final String refreshToken) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.refreshToken = refreshToken;
        this.grantTypeModel = GrantTypeModel.REFRESH_TOKEN;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UpdateAccessTokenRequest)) {
            return false;
        }
        final UpdateAccessTokenRequest that = (UpdateAccessTokenRequest) o;
        return new EqualsBuilder()
                .append(clientId, that.clientId)
                .append(clientSecret, that.clientSecret)
                .append(refreshToken, that.refreshToken)
                .append(grantTypeModel, that.grantTypeModel)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(clientId)
                .append(clientSecret)
                .append(refreshToken)
                .append(grantTypeModel)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("clientId", clientId)
                .append("clientSecret", clientSecret)
                .append("refreshToken", refreshToken)
                .append("grantTypeModel", grantTypeModel)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public String getClientId() {
        return clientId;
    }

    public void setClientId(final String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(final String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public GrantTypeModel getGrantTypeModel() {
        return grantTypeModel;
    }

    public void setGrantTypeModel(final GrantTypeModel grantTypeModel) {
        this.grantTypeModel = grantTypeModel;
    }
    //endregion
}
