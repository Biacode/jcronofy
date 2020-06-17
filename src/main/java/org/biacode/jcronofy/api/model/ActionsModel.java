package org.biacode.jcronofy.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * User: Arthur Asatryan
 * Date: 10/8/16
 * Time: 12:15 AM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActionsModel implements Serializable {
    private static final long serialVersionUID = 5861377591599028862L;

    //region Properties
    @JsonProperty("delete")
    private String delete;
    //endregion

    //region Constructors
    public ActionsModel() {
    }

    public ActionsModel(final String delete) {
        this.delete = delete;
    }
    //endregion

    //region Equals, HashCode and ToString\
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActionsModel)) {
            return false;
        }
        final ActionsModel that = (ActionsModel) o;
        return new EqualsBuilder()
                .append(delete, that.delete)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(delete)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("delete", delete)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public String getDelete() {
        return delete;
    }

    public void setDelete(final String delete) {
        this.delete = delete;
    }
    //endregion
}
