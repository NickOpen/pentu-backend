package com.pentu.exception.common;

import com.pentu.constants.ErrorConstants;
import com.pentu.exception.BaseException;

/**
 * 资源不存在异常
 */
public class NotFoundException extends BaseException {
    public NotFoundException(String resName) {
        super(ErrorConstants.RESOURCE_NOT_EXIST_CODE, ErrorConstants.RESOURCE_NOT_EXIST_MSG, resName);
    }
}