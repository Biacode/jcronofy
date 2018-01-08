package org.biacode.jcronofy.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.biacode.jcronofy.api.model.EventsPagesModel;
import org.biacode.jcronofy.api.model.FreeBusyModel;
import org.biacode.jcronofy.api.model.common.AbstractCronofyResponse;

import java.util.List;

/**
 * User: Arthur Asatryan
 * Date: 10/4/16
 * Time: 6:38 PM
 */
public class FreeBusyResponse extends AbstractCronofyResponse {
    private static final long serialVersionUID = 2670439355123857913L;

    //region Properties
    @JsonProperty("pages")
    private EventsPagesModel pages;

    @JsonProperty("free_busy")
    private List<FreeBusyModel> freeBusies;
    //endregion

    //region Constructors
    public FreeBusyResponse() {
    }

    public FreeBusyResponse(final EventsPagesModel pages, final List<FreeBusyModel> freeBusies) {
        this.pages = pages;
        this.freeBusies = freeBusies;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FreeBusyResponse)) {
            return false;
        }
        final FreeBusyResponse that = (FreeBusyResponse) o;
        return new EqualsBuilder()
                .append(pages, that.pages)
                .append(freeBusies, that.freeBusies)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(pages)
                .append(freeBusies)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("pages", pages)
                .append("freeBusies", freeBusies)
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

    public List<FreeBusyModel> getFreeBusies() {
        return freeBusies;
    }

    public void setFreeBusies(final List<FreeBusyModel> freeBusies) {
        this.freeBusies = freeBusies;
    }
    //endregion
}
