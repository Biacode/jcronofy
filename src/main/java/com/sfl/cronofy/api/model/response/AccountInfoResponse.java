package com.sfl.cronofy.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sfl.cronofy.api.model.AccountModel;
import com.sfl.cronofy.api.model.common.AbstractCronofyResponse;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/4/16
 * Time: 9:23 PM
 */
public class AccountInfoResponse extends AbstractCronofyResponse {
    private static final long serialVersionUID = 940764064618466217L;

    //region Properties
    @JsonProperty("account")
    private AccountModel account;
    //endregion

    //region Constructors
    public AccountInfoResponse() {
    }

    public AccountInfoResponse(final AccountModel account) {
        this.account = account;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountInfoResponse)) {
            return false;
        }
        final AccountInfoResponse that = (AccountInfoResponse) o;
        return new EqualsBuilder()
                .append(account, that.account)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(account)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("account", account)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public AccountModel getAccount() {
        return account;
    }

    public void setAccount(final AccountModel account) {
        this.account = account;
    }
    //endregion
}
