package org.biacode.jcronofy.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.biacode.jcronofy.api.model.CalendarModel;
import org.biacode.jcronofy.api.model.common.AbstractCronofyResponse;

import java.util.List;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/4/16
 * Time: 4:34 PM
 */
public class ListCalendarsResponse extends AbstractCronofyResponse {
    private static final long serialVersionUID = 1993123984904747773L;

    //region Properties
    @JsonProperty("calendars")
    private List<CalendarModel> calendars;
    //endregion

    //region Constructors
    public ListCalendarsResponse() {
    }

    public ListCalendarsResponse(final List<CalendarModel> calendars) {
        this.calendars = calendars;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ListCalendarsResponse)) {
            return false;
        }
        final ListCalendarsResponse that = (ListCalendarsResponse) o;
        return new EqualsBuilder()
                .append(calendars, that.calendars)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(calendars)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("calendars", calendars)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public List<CalendarModel> getCalendars() {
        return calendars;
    }

    public void setCalendars(final List<CalendarModel> calendars) {
        this.calendars = calendars;
    }
    //endregion
}
