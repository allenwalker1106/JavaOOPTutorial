package com.example.CarParkApi.Model;

import org.springframework.http.HttpStatus;

public class ResponseObject {
    private HttpStatus status;
    private String notification;
    private Object data;

    public ResponseObject(HttpStatus status, String notification, Object data) {
        this.status = status;
        this.notification = notification;
        this.data = data;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseObject{" +
                "status=" + status +
                ", notification='" + notification + '\'' +
                ", data=" + data +
                '}';
    }
}
