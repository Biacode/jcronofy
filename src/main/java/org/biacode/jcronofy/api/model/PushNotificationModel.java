package org.biacode.jcronofy.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/4/16
 * Time: 7:59 PM
 */
public class PushNotificationModel implements Serializable {
    private static final long serialVersionUID = 1150678866702190273L;

    //region Properties
    @JsonProperty("changes_since")
    private Date changesSince;

    @JsonProperty("type")
    private NotificationTypeModel type;
    //endregion

    //region Constructors
    public PushNotificationModel() {
    }

    public PushNotificationModel(final Date changesSince,
                                 final NotificationTypeModel type) {
        this.changesSince = ObjectUtils.clone(changesSince);
        this.type = type;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PushNotificationModel)) {
            return false;
        }
        final PushNotificationModel that = (PushNotificationModel) o;
        return new EqualsBuilder()
                .append(changesSince, that.changesSince)
                .append(type, that.type)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(changesSince)
                .append(type)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("changesSince", changesSince)
                .append("type", type)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public Date getChangesSince() {
        return ObjectUtils.clone(changesSince);
    }

    public void setChangesSince(final Date changesSince) {
        this.changesSince = ObjectUtils.clone(changesSince);
    }

    public NotificationTypeModel getType() {
        return type;
    }

    public void setType(final NotificationTypeModel type) {
        this.type = type;
    }
    //endregion
}
