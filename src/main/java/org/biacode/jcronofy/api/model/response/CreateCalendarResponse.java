package org.biacode.jcronofy.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.biacode.jcronofy.api.model.CalendarModel;
import org.biacode.jcronofy.api.model.common.AbstractCronofyResponse;

/**
 * User: Syuzanna Eprikyan
 * Date: 05/12/17
 * Time: 10:30 PM
 */
public class CreateCalendarResponse extends AbstractCronofyResponse {
    private static final long serialVersionUID = 2642628786379016106L;

    //region Properties
    @JsonProperty("calendar")
    private CalendarModel calendar;
    //endregion

    //region Constructors
    public CreateCalendarResponse() {
    }

    public CreateCalendarResponse(final CalendarModel calendar) {
        this.calendar = calendar;
    }
    //endregion

    //region Equals, Hashcode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreateCalendarResponse)) {
            return false;
        }
        final CreateCalendarResponse that = (CreateCalendarResponse) o;
        return new EqualsBuilder()
                .append(calendar, that.calendar)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(calendar)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("calendar", calendar)
                .toString();
    }
    //endregion

    //region Getters and Setters
    public CalendarModel getCalendar() {
        return calendar;
    }

    public void setCalendar(final CalendarModel calendar) {
        this.calendar = calendar;
    }
    //endregion
}
