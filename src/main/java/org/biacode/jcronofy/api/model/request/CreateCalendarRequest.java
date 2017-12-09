package org.biacode.jcronofy.api.model.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.biacode.jcronofy.api.model.common.AbstractAccessTokenAwareCronofyRequest;

/**
 * User: Syuzanna Eprikyan
 * Company: SFL LLC
 * Date: 05/12/17
 * Time: 10:25 PM
 */
public class CreateCalendarRequest extends AbstractAccessTokenAwareCronofyRequest {
    private static final long serialVersionUID = 1006516451715729209L;

    //region Properties
    @JsonProperty("profile_id")
    private String profileId;

    @JsonProperty("name")
    private String name;
    //endregion

    //region Constructors
    public CreateCalendarRequest() {
    }

    public CreateCalendarRequest(final String accessToken, final String profileId, final String name) {
        super(accessToken);
        this.profileId = profileId;
        this.name = name;
    }
    //endregion

    //region Equals, Hashcode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreateCalendarRequest)) {
            return false;
        }
        final CreateCalendarRequest that = (CreateCalendarRequest) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(profileId, that.profileId)
                .append(name, that.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(profileId)
                .append(name)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("profileId", profileId)
                .append("name", name)
                .toString();
    }
    //endregion

    //region Getters and Setters
    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(final String profileId) {
        this.profileId = profileId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
    //endregion
}
