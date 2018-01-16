package org.biacode.jcronofy.api.model.response;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.biacode.jcronofy.api.model.UserModel;
import org.biacode.jcronofy.api.model.common.AbstractCronofyResponse;

/**
 * User: Syuzanna Eprikyan
 * Company: SFL LLC
 * Date: 1/15/18
 * Time: 3:23 PM
 */
public class UserInfoResponse extends AbstractCronofyResponse {
    private static final long serialVersionUID = 5024658854488122230L;

    //region Properties
    @JsonUnwrapped
    private UserModel user;
    //endregion

    //region Constructors
    public UserInfoResponse() {
        // default constructor
    }

    public UserInfoResponse(final UserModel user) {
        this.user = user;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserInfoResponse)) {
            return false;
        }
        final UserInfoResponse that = (UserInfoResponse) o;
        return new EqualsBuilder()
                .append(user, that.user)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(user)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("user", user)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public UserModel getUser() {
        return user;
    }

    public void setUser(final UserModel user) {
        this.user = user;
    }
    //endregion
}
