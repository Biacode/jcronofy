package com.sfl.cronofy.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sfl.cronofy.api.model.ProfileModel;
import com.sfl.cronofy.api.model.common.AbstractCronofyResponse;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/4/16
 * Time: 9:37 PM
 */
public class ProfileInformationResponse extends AbstractCronofyResponse {
    private static final long serialVersionUID = -4874798491410855310L;

    //region Properties
    @JsonProperty("profiles")
    private List<ProfileModel> profiles;
    //endregion

    //region Constructors
    public ProfileInformationResponse() {
    }

    public ProfileInformationResponse(final List<ProfileModel> profiles) {
        this.profiles = profiles;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProfileInformationResponse)) {
            return false;
        }
        final ProfileInformationResponse that = (ProfileInformationResponse) o;
        return new EqualsBuilder()
                .append(profiles, that.profiles)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(profiles)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("profiles", profiles)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public List<ProfileModel> getProfiles() {
        return profiles;
    }

    public void setProfiles(final List<ProfileModel> profiles) {
        this.profiles = profiles;
    }
    //endregion
}
