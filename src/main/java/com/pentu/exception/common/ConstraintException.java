package com.pentu.exception.common;

import com.pentu.constants.ErrorConstants;
import com.pentu.exception.BaseException;

/**
 * 外键约束异常
 */
public class ConstraintException extends BaseException {

    public ConstraintException() {
        super(ErrorConstants.CONSTRAINT_ERROR_CODE, ErrorConstants.CONSTRAINT_ERROR_MSG);
    }
}