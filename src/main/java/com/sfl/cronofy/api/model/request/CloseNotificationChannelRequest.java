package com.sfl.cronofy.api.model.request;

import com.sfl.cronofy.api.model.common.AbstractAccessTokenAwareCronofyRequest;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/4/16
 * Time: 9:21 PM
 */
public class CloseNotificationChannelRequest extends AbstractAccessTokenAwareCronofyRequest {
    private static final long serialVersionUID = -4482022559743800354L;

    //region Properties
    private String channelId;
    //endregion

    //region Constructors
    public CloseNotificationChannelRequest() {
    }

    public CloseNotificationChannelRequest(final String accessToken, final String channelId) {
        super(accessToken);
        this.channelId = channelId;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CloseNotificationChannelRequest)) {
            return false;
        }
        final CloseNotificationChannelRequest that = (CloseNotificationChannelRequest) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(channelId, that.channelId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(channelId)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("channelId", channelId)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(final String channelId) {
        this.channelId = channelId;
    }
    //endregion
}
