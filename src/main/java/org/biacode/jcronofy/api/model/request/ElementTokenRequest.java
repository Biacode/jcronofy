package org.biacode.jcronofy.api.model.request;

import java.util.List;

import javax.ws.rs.QueryParam;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.biacode.jcronofy.api.model.common.AbstractAccessTokenAwareCronofyRequest;

/***
 * User: Don Holly 
 * Date: 6/2/20 
 * Time: 11:23 PM
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ElementTokenRequest extends AbstractAccessTokenAwareCronofyRequest {

    // region Properties
    @QueryParam("version")
    private String version = "1";

    @QueryParam("permissions")
    private List<String> permissions;

    @QueryParam("subs")
    private List<String> subs;

    @QueryParam("origin")
    private String origin;
    // endregion

    // region Constructors
    public ElementTokenRequest() {
    }

    public ElementTokenRequest(final String accessToken, final List<String> permissions, final List<String> subs, final String origin) {
        super(accessToken);
        this.permissions = permissions;
        this.subs = subs;
        this.origin = origin;
    }

    public ElementTokenRequest(final String accessToken, final String version, final List<String> permissions, final List<String> subs, final String origin) {
        super(accessToken);
        this.version = version;
        this.permissions = permissions;
        this.subs = subs;
        this.origin = origin;
    }
    // endregion

    // region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ElementTokenRequest)) {
            return false;
        }
        final ElementTokenRequest request = (ElementTokenRequest) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(version, request.version)
                .append(permissions, request.permissions)
                .append(subs, request.subs)
                .append(origin, request.origin)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(version)
                .append(permissions)
                .append(subs)
                .append(origin)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("version", version)
                .append("permissions", permissions)
                .append("subs", subs)
                .append("origin", origin)
                .toString();
    }

    // endregion
    // region Properties getters and setters
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

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
    // endregion
}