package com.pentu.controller.base;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RestApiResp extends ResponseEntity {

    public RestApiResp() {
        super(new RequestResult(), HttpStatus.OK);
    }

    public RestApiResp(Object obj) {
        super(new RequestResult(obj), HttpStatus.OK);

    }

    public RestApiResp(int status, String msg) {
        super(new RequestResult(status, msg), HttpStatus.OK);
    }
}
