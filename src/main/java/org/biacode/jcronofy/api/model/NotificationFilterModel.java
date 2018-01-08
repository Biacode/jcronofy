package org.biacode.jcronofy.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * User: Arthur Asatryan
 * Date: 10/4/16
 * Time: 9:11 PM
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationFilterModel implements Serializable {
    private static final long serialVersionUID = 1069972609483455989L;

    //region Properties
    @JsonProperty("calendar_ids")
    private List<String> calendarIds;

    @JsonProperty("only_managed")
    private Boolean onlyManaged;
    //endregion

    //region Constructors
    public NotificationFilterModel() {
    }

    public NotificationFilterModel(final List<String> calendarIds) {
        this.calendarIds = calendarIds;
    }

    public NotificationFilterModel(final Boolean onlyManaged) {
        this.onlyManaged = onlyManaged;
    }

    public NotificationFilterModel(final List<String> calendarIds,
                                   final Boolean onlyManaged) {
        this.calendarIds = calendarIds;
        this.onlyManaged = onlyManaged;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NotificationFilterModel)) {
            return false;
        }
        final NotificationFilterModel that = (NotificationFilterModel) o;
        return new EqualsBuilder()
                .append(calendarIds, that.calendarIds)
                .append(onlyManaged, that.onlyManaged)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(calendarIds)
                .append(onlyManaged)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("calendarIds", calendarIds)
                .append("onlyManaged", onlyManaged)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public List<String> getCalendarIds() {
        return calendarIds;
    }

    public void setCalendarIds(final List<String> calendarIds) {
        this.calendarIds = calendarIds;
    }

    public Boolean getOnlyManaged() {
        return onlyManaged;
    }

    public void setOnlyManaged(final Boolean onlyManaged) {
        this.onlyManaged = onlyManaged;
    }
    //endregion
}
