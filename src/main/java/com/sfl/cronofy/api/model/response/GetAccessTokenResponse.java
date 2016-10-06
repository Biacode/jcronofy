package com.sfl.cronofy.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sfl.cronofy.api.model.TokenTypeModel;
import com.sfl.cronofy.api.model.common.AbstractCronofyResponse;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/4/16
 * Time: 3:02 PM
 * <p>
 * Read <a href="https://www.cronofy.com/developers/api/#token-issue">Requesting an Access Token</a>
 * </p>
 */
public class GetAccessTokenResponse extends AbstractCronofyResponse {
    private static final long serialVersionUID = -3802223800266935982L;

    //region Properties
    @JsonProperty("token_type")
    private TokenTypeModel tokenType;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("scope")
    private String scope;

    @JsonProperty("expires_in")
    private int expiresIn;
    //endregion

    //region Constructors
    public GetAccessTokenResponse() {
    }

    public GetAccessTokenResponse(final TokenTypeModel tokenType,
                                  final String accessToken,
                                  final String refreshToken,
                                  final String scope,
                                  final int expiresIn) {
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.scope = scope;
        this.expiresIn = expiresIn;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GetAccessTokenResponse)) {
            return false;
        }
        final GetAccessTokenResponse that = (GetAccessTokenResponse) o;
        return new EqualsBuilder()
                .append(expiresIn, that.expiresIn)
                .append(tokenType, that.tokenType)
                .append(accessToken, that.accessToken)
                .append(refreshToken, that.refreshToken)
                .append(scope, that.scope)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(tokenType)
                .append(accessToken)
                .append(refreshToken)
                .append(scope)
                .append(expiresIn)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("tokenType", tokenType)
                .append("accessToken", accessToken)
                .append("refreshToken", refreshToken)
                .append("scope", scope)
                .append("expiresIn", expiresIn)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public TokenTypeModel getTokenType() {
        return tokenType;
    }

    public void setTokenType(final TokenTypeModel tokenType) {
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(final String scope) {
        this.scope = scope;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(final int expiresIn) {
        this.expiresIn = expiresIn;
    }
    //endregion
}
