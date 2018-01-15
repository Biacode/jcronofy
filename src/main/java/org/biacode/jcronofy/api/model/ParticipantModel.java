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

    @JsonProperty("required_duration")
    private RequiredDurationModel requiredDuration;
    //endregion

    //region Constructors
    public ParticipantModel() {
        // default constructor
    }

    public ParticipantModel(final List<MemberModel> members, final Object required, final RequiredDurationModel requiredDuration) {
        this.members = members;
        this.required = required;
        this.requiredDuration = requiredDuration;
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
                .append(requiredDuration, that.requiredDuration)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(members)
                .append(required)
                .append(requiredDuration)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("members", members)
                .append("required", required)
                .append("requiredDuration", requiredDuration)
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

    public RequiredDurationModel getRequiredDuration() {
        return requiredDuration;
    }

    public void setRequiredDuration(final RequiredDurationModel requiredDuration) {
        this.requiredDuration = requiredDuration;
    }
    //endregion
}
