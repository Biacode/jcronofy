package org.biacode.jcronofy.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.biacode.jcronofy.api.model.EventModel;
import org.biacode.jcronofy.api.model.EventsPagesModel;
import org.biacode.jcronofy.api.model.common.AbstractCronofyResponse;

import java.util.List;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/4/16
 * Time: 5:37 PM
 */
public class ReadEventsResponse extends AbstractCronofyResponse {
    private static final long serialVersionUID = -674824981870613766L;

    //region Properties
    @JsonProperty("pages")
    private EventsPagesModel pages;

    @JsonProperty("events")
    private List<EventModel> events;
    //endregion

    //region Constructors
    public ReadEventsResponse() {
    }

    public ReadEventsResponse(final List<EventModel> events) {
        this.events = events;
    }

    public ReadEventsResponse(final EventsPagesModel pages,
                              final List<EventModel> events) {
        this.pages = pages;
        this.events = events;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReadEventsResponse)) {
            return false;
        }
        final ReadEventsResponse that = (ReadEventsResponse) o;
        return new EqualsBuilder()
                .append(pages, that.pages)
                .append(events, that.events)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(pages)
                .append(events)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("pages", pages)
                .append("events", events)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public EventsPagesModel getPages() {
        return pages;
    }

    public void setPages(final EventsPagesModel pages) {
        this.pages = pages;
    }

    public List<EventModel> getEvents() {
        return events;
    }

    public void setEvents(final List<EventModel> events) {
        this.events = events;
    }
    //endregion
}
