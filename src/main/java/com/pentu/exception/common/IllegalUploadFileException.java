package com.pentu.exception.common;

import com.pentu.constants.ErrorConstants;
import com.pentu.exception.BaseException;

public class IllegalUploadFileException extends BaseException {

    public IllegalUploadFileException() {
        super(ErrorConstants.ILLEGAL_FILE_TYPE_ERROR, ErrorConstants.ILLEGAL_FILE_TYPE_ERROR_MSG);
    }
}

