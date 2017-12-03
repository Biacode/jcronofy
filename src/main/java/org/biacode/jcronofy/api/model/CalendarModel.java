package org.biacode.jcronofy.api.model;

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
 * Time: 4:36 PM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CalendarModel implements Serializable {
    private static final long serialVersionUID = -8213123410490665962L;

    //region Properties
    @JsonProperty("provider_name")
    private ProviderNameModel providerName;

    @JsonProperty("profile_id")
    private String profileId;

    @JsonProperty("profile_name")
    private String profileName;

    @JsonProperty("calendar_id")
    private String calendarId;

    @JsonProperty("calendar_name")
    private String calendarName;

    @JsonProperty("calendar_readonly")
    private boolean calendarReadOnly;

    @JsonProperty("calendar_deleted")
    private boolean calendarDeleted;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("calendar_primary")
    private boolean calendarPrimary;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("permission_level")
    private String permissionLevel;
    //endregion

    //region Constructors
    public CalendarModel() {
    }

    public CalendarModel(final ProviderNameModel providerName,
                         final String profileId,
                         final String profileName,
                         final String calendarId,
                         final String calendarName,
                         final boolean calendarReadOnly,
                         final boolean calendarDeleted,
                         final boolean calendarPrimary,
                         final String permissionLevel) {
        this.providerName = providerName;
        this.profileId = profileId;
        this.profileName = profileName;
        this.calendarId = calendarId;
        this.calendarName = calendarName;
        this.calendarReadOnly = calendarReadOnly;
        this.calendarDeleted = calendarDeleted;
        this.calendarPrimary = calendarPrimary;
        this.permissionLevel = permissionLevel;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CalendarModel)) {
            return false;
        }
        final CalendarModel that = (CalendarModel) o;
        return new EqualsBuilder()
                .append(calendarReadOnly, that.calendarReadOnly)
                .append(calendarDeleted, that.calendarDeleted)
                .append(providerName, that.providerName)
                .append(profileId, that.profileId)
                .append(profileName, that.profileName)
                .append(calendarId, that.calendarId)
                .append(calendarName, that.calendarName)
                .append(calendarPrimary, that.calendarPrimary)
                .append(permissionLevel, that.permissionLevel)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(providerName)
                .append(profileId)
                .append(profileName)
                .append(calendarId)
                .append(calendarName)
                .append(calendarReadOnly)
                .append(calendarDeleted)
                .append(calendarPrimary)
                .append(permissionLevel)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("provider", providerName)
                .append("profileId", profileId)
                .append("profileName", profileName)
                .append("calendarId", calendarId)
                .append("calendarName", calendarName)
                .append("calendarReadOnly", calendarReadOnly)
                .append("calendarDeleted", calendarDeleted)
                .append("calendarPrimary", calendarPrimary)
                .append("permissionLevel", permissionLevel)
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

    public String getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(final String calendarId) {
        this.calendarId = calendarId;
    }

    public String getCalendarName() {
        return calendarName;
    }

    public void setCalendarName(final String calendarName) {
        this.calendarName = calendarName;
    }

    public boolean isCalendarReadOnly() {
        return calendarReadOnly;
    }

    public void setCalendarReadOnly(final boolean calendarReadOnly) {
        this.calendarReadOnly = calendarReadOnly;
    }

    public boolean isCalendarDeleted() {
        return calendarDeleted;
    }

    public void setCalendarDeleted(final boolean calendarDeleted) {
        this.calendarDeleted = calendarDeleted;
    }

    public boolean isCalendarPrimary() {
        return calendarPrimary;
    }

    public void setCalendarPrimary(final boolean calendarPrimary) {
        this.calendarPrimary = calendarPrimary;
    }

    public String getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(final String permissionLevel) {
        this.permissionLevel = permissionLevel;
    }
    //endregion
}
