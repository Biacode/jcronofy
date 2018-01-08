package org.biacode.jcronofy.api.client.exception;

import org.biacode.jcronofy.api.model.common.AbstractCronofyRequest;

/**
 * User: Arthur Asatryan
 * Date: 10/7/16
 * Time: 9:23 PM
 */
public class UnknownStatusCodeException extends RuntimeException {
    private static final long serialVersionUID = 6484963847484081436L;

    private final AbstractCronofyRequest request;

    //region Constructors
    public UnknownStatusCodeException(final String message, final AbstractCronofyRequest request) {
        super(message);
        this.request = request;
    }
    //endregion

    //region Public methods
    public AbstractCronofyRequest getRequest() {
        return request;
    }
    //endregion
}
