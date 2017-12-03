package org.biacode.jcronofy.api.model.response;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.biacode.jcronofy.api.model.ChannelModel;
import org.biacode.jcronofy.api.model.common.AbstractCronofyResponse;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/4/16
 * Time: 9:18 PM
 */
public class CreateNotificationChannelResponse extends AbstractCronofyResponse {
    private static final long serialVersionUID = -6821995995544755246L;

    //region Properties
    @JsonUnwrapped
    private ChannelModel channel;
    //endregion

    //region Constructors
    public CreateNotificationChannelResponse() {
    }

    public CreateNotificationChannelResponse(final ChannelModel channel) {
        this.channel = channel;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreateNotificationChannelResponse)) {
            return false;
        }
        final CreateNotificationChannelResponse that = (CreateNotificationChannelResponse) o;
        return new EqualsBuilder()
                .append(channel, that.channel)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(channel)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("channel", channel)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public ChannelModel getChannel() {
        return channel;
    }

    public void setChannel(final ChannelModel channel) {
        this.channel = channel;
    }
    //endregion
}
