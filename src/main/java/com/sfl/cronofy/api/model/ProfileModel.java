package com.sfl.cronofy.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/4/16
 * Time: 9:38 PM
 */
public class ProfileModel implements Serializable {
    private static final long serialVersionUID = 5421215084583209065L;

    //region Properties
    @JsonProperty("provider_name")
    private ProviderNameModel providerName;

    @JsonProperty("profile_id")
    private String profileId;

    @JsonProperty("profile_name")
    private String profileName;

    @JsonProperty("profile_connected")
    private boolean profileConnected;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("profile_relink_url")
    private String profileRelinkUrl;
    //endregion

    //region Constructors
    public ProfileModel() {
    }

    public ProfileModel(final ProviderNameModel providerName,
                        final String profileId,
                        final String profileName,
                        final boolean profileConnected,
                        final String profileRelinkUrl) {
        this.providerName = providerName;
        this.profileId = profileId;
        this.profileName = profileName;
        this.profileConnected = profileConnected;
        this.profileRelinkUrl = profileRelinkUrl;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProfileModel)) {
            return false;
        }
        final ProfileModel that = (ProfileModel) o;
        return new EqualsBuilder()
                .append(providerName, that.providerName)
                .append(profileId, that.profileId)
                .append(profileName, that.profileName)
                .append(profileConnected, that.profileConnected)
                .append(profileRelinkUrl, that.profileRelinkUrl)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(providerName)
                .append(profileId)
                .append(profileName)
                .append(profileConnected)
                .append(profileRelinkUrl)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("providerName", providerName)
                .append("profileId", profileId)
                .append("profileName", profileName)
                .append("profileConnected", profileConnected)
                .append("profileRelinkUrl", profileRelinkUrl)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public ProviderNameModel getProviderName() {
        return providerName;
    }

    public void setProviderName(final ProviderNameModel providerName) {
        this.providerName = providerName;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(final String profileId) {
        this.profileId = profileId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(final String profileName) {
        this.profileName = profileName;
    }

    public boolean isProfileConnected() {
        return profileConnected;
    }

    public void setProfileConnected(final boolean profileConnected) {
        this.profileConnected = profileConnected;
    }

    public String getProfileRelinkUrl() {
        return profileRelinkUrl;
    }

    public void setProfileRelinkUrl(final String profileRelinkUrl) {
        this.profileRelinkUrl = profileRelinkUrl;
    }
    //endregion
}
