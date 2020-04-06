package com.pentu.exception.auth;

import com.pentu.constants.error.ErrorConstants;
import com.pentu.exception.base.BaseException;

public class UnAuthorizedException extends BaseException {

    public UnAuthorizedException() {
        super(ErrorConstants.UNAUTHORIZED_CODE, ErrorConstants.UNAUTHORIZED_MSG);
    }

}