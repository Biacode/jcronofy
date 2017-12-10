package org.biacode.jcronofy.api.model.request;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.biacode.jcronofy.api.model.common.AbstractAccessTokenAwareCronofyRequest;

import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * User: Syuzanna Eprikyan
 * Date: 12/9/2017
 * Time: 7:51 PM
 */
public class BulkDeleteEventsRequest extends AbstractAccessTokenAwareCronofyRequest {
    private static final long serialVersionUID = -2967847668252027615L;

    //region Properties
    @QueryParam("delete_all")
    private Boolean deleteAll;

    @QueryParam("calendar_ids")
    private List<String> calendarIds;
    //endregion

    //region Constructors
    public BulkDeleteEventsRequest() {
    }

    public BulkDeleteEventsRequest(final String accessToken, final Boolean deleteAll) {
        super(accessToken);
        this.deleteAll = deleteAll;
    }

    public BulkDeleteEventsRequest(final String accessToken, final List<String> calendarIds) {
        super(accessToken);
        this.calendarIds = calendarIds;
    }
    //endregion

    //region Equals, Hashcode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BulkDeleteEventsRequest)) {
            return false;
        }
        final BulkDeleteEventsRequest that = (BulkDeleteEventsRequest) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(deleteAll, that.deleteAll)
                .append(calendarIds, that.calendarIds)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(deleteAll)
                .append(calendarIds)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("deleteAll", deleteAll)
                .append("calendarIds", calendarIds)
                .toString();
    }
    //endregion

    //region Getters and Setters
    public Boolean getDeleteAll() {
        return deleteAll;
    }

    public void setDeleteAll(final Boolean deleteAll) {
        this.deleteAll = deleteAll;
    }

    public List<String> getCalendarIds() {
        return calendarIds;
    }

    public void setCalendarIds(final List<String> calendarIds) {
        this.calendarIds = calendarIds;
    }
    //endregion
}
