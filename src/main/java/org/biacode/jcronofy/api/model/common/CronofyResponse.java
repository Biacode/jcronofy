package org.biacode.jcronofy.api.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: Arthur Asatryan
 * Date: 10/4/16
 * Time: 3:55 PM
 */
public class CronofyResponse<T extends AbstractCronofyResponse> {
    private static final long serialVersionUID = -679286511899959879L;

    //region Properties
    @JsonProperty("error")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private ErrorTypeModel error;

    @JsonUnwrapped
    @JsonIgnoreProperties(ignoreUnknown = true)
    private T response;
    //endregion

    //region Constructors
    public CronofyResponse() {
    }

    public CronofyResponse(final T response) {
        this.response = response;
    }

    public CronofyResponse(final ErrorTypeModel error) {
        this.error = error;
    }
    //endregion

    //region Public methods
    public boolean hasError() {
        return error != null;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CronofyResponse)) {
            return false;
        }
        final CronofyResponse<?> that = (CronofyResponse<?>) o;
        return new EqualsBuilder()
                .append(error, that.error)
                .append(response, that.response)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(error)
                .append(response)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("error", error)
                .append("response", response)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public ErrorTypeModel getError() {
        return error;
    }

    public void setError(final ErrorTypeModel error) {
        this.error = error;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(final T response) {
        this.response = response;
    }
    //endregion
}
