package org.biacode.jcronofy.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * User: Don Holly 
 * Date: 6/2/2020 
 * Time: 11:23 PM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ElementTokenModel implements Serializable {

    // region Properties
    @JsonProperty("permissions")
    private List<String> permissions;

    @JsonProperty("subs")
    private List<String> subs;

    @JsonProperty("origin")
    private String origin;

    @JsonProperty("token")
    private String token;

    @JsonProperty("expires_in")
    private Integer expiresIn;
    // endregion

    // region Constructors
    public ElementTokenModel() {
    }

    public ElementTokenModel(final List<String> permissions, final List<String> subs, final String origin, final String token, final Integer expiresIn) {
        this.permissions = permissions;
        this.subs = subs;
        this.origin = origin;
        this.token = token;
    }
    // endregion

    // region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ElementTokenModel)) {
            return false;
        }
        final ElementTokenModel that = (ElementTokenModel) o;
        return new EqualsBuilder()
                .append(permissions, that.permissions)
                .append(subs, that.subs)
                .append(origin, that.origin)
                .append(token, that.token)
                .append(expiresIn, that.expiresIn)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(permissions)
                .append(subs)
                .append(origin)
                .append(token)
                .append(expiresIn)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("permissions", permissions)
                .append("subs", subs)
                .append("origin", origin)
                .append("token", token)
                .append("expiresIn", expiresIn)
                .toString();
    }
    // endregion

    // region Properties getters and setters
    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public List<String> getSubs() {
        return subs;
    }

    public void setSubs(List<String> subs) {
        this.subs = subs;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }
    // endregion
}