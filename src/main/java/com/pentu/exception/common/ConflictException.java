package com.pentu.exception.common;

import com.pentu.constants.ErrorConstants;
import com.pentu.exception.BaseException;

public class ConflictException extends BaseException {

    public ConflictException(String msg) {
        super(ErrorConstants.CONFLICT_ERROR_CODE, ErrorConstants.CONFLICT_ERROR_MSG, msg);
    }

    public ConflictException() {
        super(ErrorConstants.CONFLICT_ERROR_CODE, ErrorConstants.CONFLICT_ERROR_MSG);
    }
}