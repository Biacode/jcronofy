package com.sfl.cronofy.api.model.request;

import com.sfl.cronofy.api.model.common.AbstractCronofyRequest;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.ws.rs.QueryParam;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/5/16
 * Time: 1:18 PM
 */
public class RedirectResultRequest extends AbstractCronofyRequest {
    private static final long serialVersionUID = -6492682606302224597L;

    //region Properties
    @QueryParam("code")
    private String code;

    @QueryParam("state")
    private String state;
    //endregion

    //region Constructors
    public RedirectResultRequest() {
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RedirectResultRequest)) {
            return false;
        }
        final RedirectResultRequest that = (RedirectResultRequest) o;
        return new EqualsBuilder()
                .append(code, that.code)
                .append(state, that.state)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(code)
                .append(state)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("code", code)
                .append("state", state)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }
    //endregion
}
