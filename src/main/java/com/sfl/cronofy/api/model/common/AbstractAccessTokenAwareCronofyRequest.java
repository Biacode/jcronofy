package com.sfl.cronofy.api.model.common;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/5/16
 * Time: 2:49 PM
 */
public class AbstractAccessTokenAwareCronofyRequest extends AbstractCronofyRequest {
    private static final long serialVersionUID = 5013510822502829091L;

    //region Properties
    private String accessToken;
    //endregion

    //region Constructors
    public AbstractAccessTokenAwareCronofyRequest() {
    }

    public AbstractAccessTokenAwareCronofyRequest(final String accessToken) {
        this.accessToken = accessToken;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractAccessTokenAwareCronofyRequest)) {
            return false;
        }
        final AbstractAccessTokenAwareCronofyRequest that = (AbstractAccessTokenAwareCronofyRequest) o;
        return new EqualsBuilder()
                .append(accessToken, that.accessToken)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(accessToken)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("accessToken", accessToken)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }
    //endregion
}
