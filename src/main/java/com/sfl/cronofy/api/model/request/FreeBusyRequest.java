package com.sfl.cronofy.api.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * Time: 6:33 PM
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FreeBusyRequest extends AbstractAccessTokenAwareCronofyRequest {
    private static final long serialVersionUID = -4034197136448785187L;

    //region Properties
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "GMT")
    @QueryParam("from")
    private Date from;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "GMT")
    @QueryParam("to")
    private Date to;

    @QueryParam("tzid")
    private String tzId;

    @QueryParam("include_managed")
    private Boolean includeManaged;

    @QueryParam("calendar_ids")
    private List<String> calendarIds;

    @QueryParam("localized_times")
    private Boolean localizedTimes;
    //endregion

    //region Constructors
    public FreeBusyRequest() {
    }

    public FreeBusyRequest(final String accessToken, final String tzId) {
        super(accessToken);
        this.tzId = tzId;
    }

    public FreeBusyRequest(final String accessToken,
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
        if (!(o instanceof FreeBusyRequest)) {
            return false;
        }
        final FreeBusyRequest request = (FreeBusyRequest) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(from, request.from)
                .append(to, request.to)
                .append(tzId, request.tzId)
                .append(includeManaged, request.includeManaged)
                .append(calendarIds, request.calendarIds)
                .append(localizedTimes, request.localizedTimes)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(from)
                .append(to)
                .append(tzId)
                .append(includeManaged)
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
                .append("includeManaged", includeManaged)
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

    public Boolean getIncludeManaged() {
        return includeManaged;
    }

    public void setIncludeManaged(final Boolean includeManaged) {
        this.includeManaged = includeManaged;
    }

    public Object[] getCalendarIds() {
        if (calendarIds == null) {
            return new Object[0];
        }

        return calendarIds.toArray();
    }

    public void setCalendarIds(final List<String> calendarIds) {
        this.calendarIds = calendarIds;
    }

    public Boolean getLocalizedTimes() {
        return localizedTimes;
    }

    public void setLocalizedTimes(final Boolean localizedTimes) {
        this.localizedTimes = localizedTimes;
    }
    //endregion
}
