package org.biacode.jcronofy.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * User: Syuzanna Eprikyan
 * Company: SFL LLC
 * Date: 1/12/18
 * Time: 4:47 PM
 */
public class ParticipantModel implements Serializable {
    private static final long serialVersionUID = 3776986452668318408L;

    //region Properties
    @JsonProperty("members")
    private List<MemberModel> members;

    @JsonProperty("required")
    private Object required;

    @JsonProperty("sub")
    private String sub;
    //endregion

    //region Constructors
    public ParticipantModel() {
        // default constructor
    }

    public ParticipantModel(final String sub) {
        this.sub = sub;
    }

    public ParticipantModel(final List<MemberModel> members, final Object required) {
        this.members = members;
        this.required = required;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ParticipantModel)) {
            return false;
        }
        final ParticipantModel that = (ParticipantModel) o;
        return new EqualsBuilder()
                .append(members, that.members)
                .append(required, that.required)
                .append(sub, that.sub)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(members)
                .append(required)
                .append(sub)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("members", members)
                .append("required", required)
                .append("sub", sub)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public List<MemberModel> getMembers() {
        return members;
    }

    public void setMembers(final List<MemberModel> members) {
        this.members = members;
    }

    public Object getRequired() {
        return required;
    }

    public void setRequired(final Object required) {
        this.required = required;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(final String sub) {
        this.sub = sub;
    }
    //endregion
}
