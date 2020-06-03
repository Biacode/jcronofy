package org.biacode.jcronofy.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.biacode.jcronofy.api.model.ElementTokenModel;
import org.biacode.jcronofy.api.model.common.AbstractCronofyResponse;

/**
 * User: Don Holly Date: 6/2/20 Time: 11:23 PM
 */
public class ElementTokenResponse extends AbstractCronofyResponse {

    // region Properties
    @JsonProperty("element_token")
    private ElementTokenModel elementToken;
    // endregion

    // region Constructors
    public ElementTokenResponse() {
    }

    public ElementTokenResponse(final ElementTokenModel pages) {
        this.elementToken = pages;
    }
    // endregion

    // region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ElementTokenResponse)) {
            return false;
        }
        final ElementTokenResponse that = (ElementTokenResponse) o;
        return new EqualsBuilder()
                .append(elementToken, that.elementToken)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(elementToken)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("elementToken", elementToken)
                .toString();
    }
    // endregion

    // region Properties getters and setters
    public ElementTokenModel getElementToken() {
        return elementToken;
    }

    public void setElementToken(ElementTokenModel elementToken) {
        this.elementToken = elementToken;
    }
    // endregion
}
