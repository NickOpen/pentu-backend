package com.pentu.exception;

import com.pentu.constants.ErrorConstants;
import com.pentu.controller.base.RequestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 使用该注解可以完成以下三部分功能
 * 1：全局异常处理
 * 2：全局数据绑定
 * 3：全局数据预处理
 */
@ControllerAdvice
public class GlobalRestExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GlobalRestExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<RequestResult> handleError500(Exception ex) {
        printError(ex);
        RequestResult requestResult;
        HttpStatus status;
        if (ex.getCause() instanceof BaseException) {
            BaseException exCause = (BaseException) ex.getCause();
            requestResult = constructErrorObject(exCause.getErrorCode(), exCause.getErrorMsg(), exCause.getParameters());
            status = HttpStatus.OK;
        } else {
            requestResult = constructErrorObject(ErrorConstants.INTERNAL_SERVER_ERROR_CODE,
                    ErrorConstants.INTERNAL_SERVER_ERROR_CODE_MSG);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(requestResult, status);
    }


    /**
     * 打印异常信息
     *
     * @param ex
     */
    private void printError(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter printWriter = new PrintWriter(sw);
        ex.printStackTrace(printWriter);
        printWriter.flush();
        logger.error(sw.toString());
    }

    /**
     * 构建错误对象
     *
     * @param errorCode
     * @param errorMessage
     * @param parameters
     * @return
     */
    private RequestResult constructErrorObject(Integer errorCode, String errorMessage, String... parameters) {
        if (null != parameters && parameters.length == 0) {
            parameters = new String[]{""};
        }
        errorMessage = String.format(errorMessage, parameters);
        return new RequestResult(errorCode, errorMessage);
    }
}
