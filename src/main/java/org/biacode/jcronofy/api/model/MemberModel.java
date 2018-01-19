package org.biacode.jcronofy.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * User: Syuzanna Eprikyan
 * Company: SFL LLC
 * Date: 1/12/18
 * Time: 5:07 PM
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberModel implements Serializable {
    private static final long serialVersionUID = -2553825956881477678L;

    //region Properties
    @JsonProperty("sub")
    private String sub;

    @JsonProperty("available_periods")
    private List<AvailablePeriodModel> availablePeriods;

    @JsonProperty("calendar_ids")
    private String[] calendarIds;
    //endregion

    //region Constructors
    public MemberModel() {
        // default constructor
    }

    public MemberModel(final String sub) {
        this.sub = sub;
    }

    public MemberModel(final String sub, final List<AvailablePeriodModel> availablePeriods) {
        this.sub = sub;
        this.availablePeriods = availablePeriods;
    }

    public MemberModel(final String sub, final String[] calendarIds) {
        this.sub = sub;
        this.calendarIds = calendarIds;
    }

    public MemberModel(final String sub, final List<AvailablePeriodModel> availablePeriods, final String[] calendarIds) {
        this.sub = sub;
        this.availablePeriods = availablePeriods;
        this.calendarIds = calendarIds;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MemberModel)) {
            return false;
        }
        final MemberModel that = (MemberModel) o;
        return new EqualsBuilder()
                .append(sub, that.sub)
                .append(availablePeriods, that.availablePeriods)
                .append(calendarIds, that.calendarIds)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(sub)
                .append(availablePeriods)
                .append(calendarIds)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("sub", sub)
                .append("availablePeriods", availablePeriods)
                .append("calendarIds", calendarIds)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public String getSub() {
        return sub;
    }

    public void setSub(final String sub) {
        this.sub = sub;
    }

    public List<AvailablePeriodModel> getAvailablePeriods() {
        return availablePeriods;
    }

    public void setAvailablePeriods(final List<AvailablePeriodModel> availablePeriods) {
        this.availablePeriods = availablePeriods;
    }

    public String[] getCalendarIds() {
        return calendarIds;
    }

    public void setCalendarIds(final String[] calendarIds) {
        this.calendarIds = calendarIds;
    }
    //endregion
}
