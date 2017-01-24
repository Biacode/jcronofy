package com.sfl.cronofy.api.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sfl.cronofy.api.model.common.AbstractAccessTokenAwareCronofyRequest;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.ws.rs.QueryParam;
import java.util.Date;
import java.util.List;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/4/16
 * Time: 5:32 PM
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReadEventsRequest extends AbstractAccessTokenAwareCronofyRequest {
    private static final long serialVersionUID = 8805784760126104471L;

    //region Properties
    @QueryParam("from")
    private Date from;

    @QueryParam("to")
    private Date to;

    @QueryParam("tzid")
    private String tzId;

    @QueryParam("include_deleted")
    private Boolean includeDeleted;

    @QueryParam("include_moved")
    private Boolean includeMoved;

    @QueryParam("last_modified")
    private Date lastModified;

    @QueryParam("include_managed")
    private Boolean includeManaged;

    @QueryParam("only_managed")
    private Boolean onlyManaged;

    @QueryParam("calendar_ids")
    private List<String> calendarIds;

    @QueryParam("localized_times")
    private Boolean localizedTimes;
    //endregion

    //region Constructors
    public ReadEventsRequest() {
    }

    public ReadEventsRequest(final String accessToken,
                             final String tzId) {
        super(accessToken);
        this.tzId = tzId;
    }

    public ReadEventsRequest(final String accessToken,
                             final Date from,
                             final Date to,
                             final String tzId) {
        super(accessToken);
        this.from = ObjectUtils.clone(from);
        this.to = ObjectUtils.clone(to);
        this.tzId = tzId;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReadEventsRequest)) {
            return false;
        }
        final ReadEventsRequest that = (ReadEventsRequest) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(from, that.from)
                .append(to, that.to)
                .append(tzId, that.tzId)
                .append(includeDeleted, that.includeDeleted)
                .append(includeMoved, that.includeMoved)
                .append(lastModified, that.lastModified)
                .append(includeManaged, that.includeManaged)
                .append(onlyManaged, that.onlyManaged)
                .append(calendarIds, that.calendarIds)
                .append(localizedTimes, that.localizedTimes)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(from)
                .append(to)
                .append(tzId)
                .append(includeDeleted)
                .append(includeMoved)
                .append(lastModified)
                .append(includeManaged)
                .append(onlyManaged)
                .append(calendarIds)
                .append(localizedTimes)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("from", from)
                .append("to", to)
                .append("tzId", tzId)
                .append("includeDeleted", includeDeleted)
                .append("includeMoved", includeMoved)
                .append("lastModified", lastModified)
                .append("includeManaged", includeManaged)
                .append("onlyManaged", onlyManaged)
                .append("calendarIds", calendarIds)
                .append("localizedTimes", localizedTimes)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public Date getFrom() {
        return ObjectUtils.clone(from);
    }

    public void setFrom(final Date from) {
        this.from = ObjectUtils.clone(from);
    }

    public Date getTo() {
        return ObjectUtils.clone(to);
    }

    public void setTo(final Date to) {
        this.to = ObjectUtils.clone(to);
    }

    public String getTzId() {
        return tzId;
    }

    public void setTzId(final String tzId) {
        this.tzId = tzId;
    }

    public Boolean isIncludeDeleted() {
        return includeDeleted;
    }

    public void setIncludeDeleted(final Boolean includeDeleted) {
        this.includeDeleted = includeDeleted;
    }

    public Boolean isIncludeMoved() {
        return includeMoved;
    }

    public void setIncludeMoved(final Boolean includeMoved) {
        this.includeMoved = includeMoved;
    }

    public Date getLastModified() {
        return ObjectUtils.clone(lastModified);
    }

    public void setLastModified(final Date lastModified) {
        this.lastModified = ObjectUtils.clone(lastModified);
    }

    public Boolean isIncludeManaged() {
        return includeManaged;
    }

    public void setIncludeManaged(final Boolean includeManaged) {
        this.includeManaged = includeManaged;
    }

    public Boolean isOnlyManaged() {
        return onlyManaged;
    }

    public void setOnlyManaged(final Boolean onlyManaged) {
        this.onlyManaged = onlyManaged;
    }

    public List<String> getCalendarIds() {
        return calendarIds;
    }

    public void setCalendarIds(final List<String> calendarIds) {
        this.calendarIds = calendarIds;
    }

    public Boolean isLocalizedTimes() {
        return localizedTimes;
    }

    public void setLocalizedTimes(final Boolean localizedTimes) {
        this.localizedTimes = localizedTimes;
    }
    //endregion
}
