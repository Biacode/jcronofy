package org.biacode.jcronofy.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.biacode.jcronofy.api.model.GrantTypeModel;
import org.biacode.jcronofy.api.model.common.AbstractCronofyRequest;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/4/16
 * Time: 3:02 PM
 */
public class GetAccessTokenRequest extends AbstractCronofyRequest {
    private static final long serialVersionUID = -6003501047921956814L;

    //region Properties
    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("client_secret")
    private String clientSecret;

    @JsonProperty("grant_type")
    private GrantTypeModel grantType;

    @JsonProperty("code")
    private String code;

    @JsonProperty("redirect_uri")
    private String redirectUri;
    //endregion

    //region Constructors

    /**
     * Default grant type is AUTHORIZATION_CODE
     */
    public GetAccessTokenRequest() {
        this.grantType = GrantTypeModel.AUTHORIZATION_CODE;
    }

    /**
     * Default grant type is AUTHORIZATION_CODE
     */
    public GetAccessTokenRequest(final String clientId,
                                 final String clientSecret,
                                 final String code,
                                 final String redirectUri) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.grantType = GrantTypeModel.AUTHORIZATION_CODE;
        this.code = code;
        this.redirectUri = redirectUri;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GetAccessTokenRequest)) {
            return false;
        }
        final GetAccessTokenRequest that = (GetAccessTokenRequest) o;
        return new EqualsBuilder()
                .append(clientId, that.clientId)
                .append(clientSecret, that.clientSecret)
                .append(grantType, that.grantType)
                .append(code, that.code)
                .append(redirectUri, that.redirectUri)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(clientId)
                .append(clientSecret)
                .append(grantType)
                .append(code)
                .append(redirectUri)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("clientId", clientId)
                .append("clientSecret", clientSecret)
                .append("grantType", grantType)
                .append("code", code)
                .append("redirectUri", redirectUri)
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

    public GrantTypeModel getGrantType() {
        return grantType;
    }

    public void setGrantType(final GrantTypeModel grantType) {
        this.grantType = grantType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(final String redirectUri) {
        this.redirectUri = redirectUri;
    }
    //endregion
}
