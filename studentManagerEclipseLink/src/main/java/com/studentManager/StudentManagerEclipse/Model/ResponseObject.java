package com.studentManager.StudentManagerEclipse.Model;

import org.springframework.http.HttpStatus;

public class ResponseObject {
    private HttpStatus status;
    private Object data;

    public ResponseObject(HttpStatus status, Object data) {
        this.status = status;
        this.data = data;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
