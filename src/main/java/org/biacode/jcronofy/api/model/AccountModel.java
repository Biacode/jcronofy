package org.biacode.jcronofy.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * User: Arthur Asatryan
 * Date: 10/4/16
 * Time: 9:23 PM
 */
public class AccountModel implements Serializable {
    private static final long serialVersionUID = 5706963167207776200L;

    //region Properties
    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("email")
    private String email;

    @JsonProperty("name")
    private String name;

    @JsonProperty("default_tzid")
    private String defaultTzId;

    @JsonProperty("type")
    private AccountTypeModel type;

    @JsonProperty("scope")
    private ScopeModel scope;
    //endregion

    //region Constructors
    public AccountModel() {
    }

    public AccountModel(final String accountId,
                        final String email,
                        final String name,
                        final String defaultTzId,
                        final AccountTypeModel type,
                        final ScopeModel scope) {
        this.accountId = accountId;
        this.email = email;
        this.name = name;
        this.defaultTzId = defaultTzId;
        this.type = type;
        this.scope = scope;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountModel)) {
            return false;
        }
        final AccountModel that = (AccountModel) o;
        return new EqualsBuilder()
                .append(accountId, that.accountId)
                .append(email, that.email)
                .append(name, that.name)
                .append(defaultTzId, that.defaultTzId)
                .append(type, that.type)
                .append(scope, that.scope)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(accountId)
                .append(email)
                .append(name)
                .append(defaultTzId)
                .append(type)
                .append(scope)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("accountId", accountId)
                .append("email", email)
                .append("name", name)
                .append("defaultTzId", defaultTzId)
                .append("type", type)
                .append("scope", scope)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(final String accountId) {
        this.accountId = accountId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDefaultTzId() {
        return defaultTzId;
    }

    public void setDefaultTzId(final String defaultTzId) {
        this.defaultTzId = defaultTzId;
    }

    public AccountTypeModel getType() {
        return type;
    }

    public void setType(final AccountTypeModel type) {
        this.type = type;
    }

    public ScopeModel getScope() {
        return scope;
    }

    public void setScope(final ScopeModel scope) {
        this.scope = scope;
    }
    //endregion
}
