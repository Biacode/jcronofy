package org.biacode.jcronofy.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * User: Syuzanna Eprikyan
 * Date: 1/15/18
 * Time: 3:25 PM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserModel implements Serializable {
    private static final long serialVersionUID = -8027890123766451125L;

    //region Properties
    @JsonProperty("sub")
    private String sub;

    @JsonProperty("cronofy.type")
    private String cronofyType;
    //endregion

    //region Constructors
    public UserModel() {
        // default constructor
    }

    public UserModel(final String sub, final String cronofyType) {
        this.sub = sub;
        this.cronofyType = cronofyType;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserModel)) {
            return false;
        }
        final UserModel userModel = (UserModel) o;
        return new EqualsBuilder()
                .append(sub, userModel.sub)
                .append(cronofyType, userModel.cronofyType)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(sub)
                .append(cronofyType)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("sub", sub)
                .append("cronofyType", cronofyType)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public String getSub() {
        return sub;
    }

    public void setSub(final String sub) {
        this.sub = sub;
    }

    public String getCronofyType() {
        return cronofyType;
    }

    public void setCronofyType(final String cronofyType) {
        this.cronofyType = cronofyType;
    }
    //endregion
}
