package org.biacode.jcronofy.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.biacode.jcronofy.api.model.ChannelModel;
import org.biacode.jcronofy.api.model.PushNotificationModel;
import org.biacode.jcronofy.api.model.common.AbstractCronofyRequest;

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
    public PushNotificationRequest() {
    }

    public PushNotificationRequest(final PushNotificationModel notification, final ChannelModel channel) {
        this.notification = notification;
        this.channel = channel;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PushNotificationRequest)) {
            return false;
        }
        final PushNotificationRequest that = (PushNotificationRequest) o;
        return new EqualsBuilder()
                .append(notification, that.notification)
                .append(channel, that.channel)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(notification)
                .append(channel)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("notification", notification)
                .append("channel", channel)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public PushNotificationModel getNotification() {
        return notification;
    }

    public void setNotification(final PushNotificationModel notification) {
        this.notification = notification;
    }

    public ChannelModel getChannel() {
        return channel;
    }

    public void setChannel(final ChannelModel channel) {
        this.channel = channel;
    }
    //endregion
}
