package org.biacode.jcronofy.api.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.biacode.jcronofy.api.model.NotificationFilterModel;
import org.biacode.jcronofy.api.model.common.AbstractAccessTokenAwareCronofyRequest;

/**
 * User: Arthur Asatryan
 * Date: 10/4/16
 * Time: 9:10 PM
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateNotificationChannelRequest extends AbstractAccessTokenAwareCronofyRequest {
    private static final long serialVersionUID = 9212303319890058259L;

    //region Properties
    @JsonProperty("callback_url")
    private String callbackUrl;

    @JsonProperty("filters")
    private NotificationFilterModel filter;
    //endregion

    //region Constructors
    public CreateNotificationChannelRequest() {
    }

    public CreateNotificationChannelRequest(final String accessToken,
                                            final String callbackUrl) {
        super(accessToken);
        this.callbackUrl = callbackUrl;
    }

    public CreateNotificationChannelRequest(final String accessToken,
                                            final String callbackUrl,
                                            final NotificationFilterModel filter) {
        super(accessToken);
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
        if (!(o instanceof CreateNotificationChannelRequest)) {
            return false;
        }
        final CreateNotificationChannelRequest that = (CreateNotificationChannelRequest) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(callbackUrl, that.callbackUrl)
                .append(filter, that.filter)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(callbackUrl)
                .append(filter)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("callbackUrl", callbackUrl)
                .append("filter", filter)
                .toString();
    }
    //endregion

    //region Properties getters and setters
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
