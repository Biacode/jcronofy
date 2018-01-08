package org.biacode.jcronofy.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * User: Arthur Asatryan
 * Date: 10/4/16
 * Time: 5:38 PM
 */
public class EventsPagesModel implements Serializable {
    private static final long serialVersionUID = -1579042640454730457L;

    //region Properties
    @JsonProperty("current")
    private int current;

    @JsonProperty("total")
    private int total;

    @JsonProperty("next_page")
    private String nextPage;
    //endregion

    //region Constructors
    public EventsPagesModel() {
    }

    public EventsPagesModel(final int current,
                            final int total,
                            final String nextPage) {
        this.current = current;
        this.total = total;
        this.nextPage = nextPage;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EventsPagesModel)) {
            return false;
        }
        final EventsPagesModel that = (EventsPagesModel) o;
        return new EqualsBuilder()
                .append(current, that.current)
                .append(total, that.total)
                .append(nextPage, that.nextPage)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(current)
                .append(total)
                .append(nextPage)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("current", current)
                .append("total", total)
                .append("nextPage", nextPage)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public int getCurrent() {
        return current;
    }

    public void setCurrent(final int current) {
        this.current = current;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(final int total) {
        this.total = total;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(final String nextPage) {
        this.nextPage = nextPage;
    }
    //endregion
}
