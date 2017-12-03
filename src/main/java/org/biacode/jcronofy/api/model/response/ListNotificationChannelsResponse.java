package org.biacode.jcronofy.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.biacode.jcronofy.api.model.ChannelModel;
import org.biacode.jcronofy.api.model.common.AbstractCronofyResponse;

import java.util.List;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/4/16
 * Time: 9:20 PM
 */
public class ListNotificationChannelsResponse extends AbstractCronofyResponse {
    private static final long serialVersionUID = -2083445641514129677L;

    //region Properties
    @JsonProperty("channels")
    private List<ChannelModel> channels;
    //endregion

    //region Constructors
    public ListNotificationChannelsResponse() {
    }

    public ListNotificationChannelsResponse(final List<ChannelModel> channels) {
        this.channels = channels;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ListNotificationChannelsResponse)) {
            return false;
        }
        final ListNotificationChannelsResponse that = (ListNotificationChannelsResponse) o;
        return new EqualsBuilder()
                .append(channels, that.channels)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(channels)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("channels", channels)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public List<ChannelModel> getChannels() {
        return channels;
    }

    public void setChannels(final List<ChannelModel> channels) {
        this.channels = channels;
    }
    //endregion
}
