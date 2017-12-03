package org.biacode.jcronofy.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.biacode.jcronofy.api.model.TokenTypeModel;
import org.biacode.jcronofy.api.model.common.AbstractCronofyResponse;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/4/16
 * Time: 3:53 PM
 */
public class UpdateAccessTokenResponse extends AbstractCronofyResponse {
    private static final long serialVersionUID = 5140527545212307302L;

    //region Properties
    @JsonProperty("token_type")
    private TokenTypeModel tokenType;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("expires_in")
    private int expiresIn;

    @JsonProperty("scope")
    private String scope;
    //endregion

    //region Constructors
    public UpdateAccessTokenResponse() {
    }

    public UpdateAccessTokenResponse(final TokenTypeModel tokenType,
                                     final String accessToken,
                                     final String refreshToken,
                                     final int expiresIn,
                                     final String scope) {
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
        this.scope = scope;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UpdateAccessTokenResponse)) {
            return false;
        }
        final UpdateAccessTokenResponse that = (UpdateAccessTokenResponse) o;
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
                .append(expiresIn)
                .append(scope)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("tokenType", tokenType)
                .append("accessToken", accessToken)
                .append("refreshToken", refreshToken)
                .append("expiresIn", expiresIn)
                .append("scope", scope)
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

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(final int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(final String scope) {
        this.scope = scope;
    }
    //endregion
}
