package org.biacode.jcronofy.api.model.common;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/6/16
 * Time: 3:28 PM
 */
public enum ErrorTypeModel {
    BAD_REQUEST(400),
    NOT_AUTHORIZED(401),
    FORBIDDEN(403),
    UNPROCESSABLE(422),
    LOCKED(423);

    final int code;

    ErrorTypeModel(final int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
