package org.biacode.jcronofy.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/4/16
 * Time: 8:04 PM
 */
public class ChannelModel implements Serializable {
    private static final long serialVersionUID = 3418604770585240554L;

    //region Properties
    @JsonProperty("channel_id")
    private String channelId;

    @JsonProperty("callback_url")
    private String callbackUrl;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("filters")
    private NotificationFilterModel filter;
    //endregion

    //region Constructors
    public ChannelModel() {
    }

    public ChannelModel(final String channelId, final String callbackUrl, final NotificationFilterModel filter) {
        this.channelId = channelId;
        this.callbackUrl = callbackUrl;
        this.filter = filter;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChannelModel)) {
            return false;
        }
        final ChannelModel that = (ChannelModel) o;
        return new EqualsBuilder()
                .append(channelId, that.channelId)
                .append(callbackUrl, that.callbackUrl)
                .append(filter, that.filter)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(channelId)
                .append(callbackUrl)
                .append(filter)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("channelId", channelId)
                .append("callbackUrl", callbackUrl)
                .append("filter", filter)
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

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(final String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public NotificationFilterModel getFilter() {
        return filter;
    }

    public void setFilter(final NotificationFilterModel filter) {
        this.filter = filter;
    }
    //endregion
}
