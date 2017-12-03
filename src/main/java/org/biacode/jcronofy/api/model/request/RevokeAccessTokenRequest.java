package org.biacode.jcronofy.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.biacode.jcronofy.api.model.common.AbstractCronofyRequest;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/4/16
 * Time: 4:30 PM
 */
public class RevokeAccessTokenRequest extends AbstractCronofyRequest {
    private static final long serialVersionUID = 6951953066705450267L;

    //region Properties
    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("client_secret")
    private String clientSecret;

    @JsonProperty("token")
    private String token;
    //endregion

    //region Constructors
    public RevokeAccessTokenRequest() {
    }

    public RevokeAccessTokenRequest(final String clientId,
                                    final String clientSecret,
                                    final String token) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.token = token;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RevokeAccessTokenRequest)) {
            return false;
        }
        final RevokeAccessTokenRequest that = (RevokeAccessTokenRequest) o;
        return new EqualsBuilder()
                .append(clientId, that.clientId)
                .append(clientSecret, that.clientSecret)
                .append(token, that.token)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(clientId)
                .append(clientSecret)
                .append(token)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("clientId", clientId)
                .append("clientSecret", clientSecret)
                .append("token", token)
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

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }
    //endregion
}
