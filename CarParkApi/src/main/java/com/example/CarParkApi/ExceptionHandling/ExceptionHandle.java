package com.example.CarParkApi.ExceptionHandling;

import com.example.CarParkApi.Model.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletRequest;

@RestControllerAdvice
public class ExceptionHandle {

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResponseObject> requestNotSupportException(){
        return new ResponseEntity<ResponseObject>(
                new ResponseObject(
                        HttpStatus.METHOD_NOT_ALLOWED,
                        "Method Not Supported",
                        ""
                ),
                HttpStatus.METHOD_NOT_ALLOWED
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseObject> requestNotSupportException(ServletRequest req){
        return new ResponseEntity<ResponseObject>(
                new ResponseObject(
                        HttpStatus.BAD_REQUEST,
                        "Fail To Map Data",
                        req.getParameterMap()
                ),
                HttpStatus.BAD_REQUEST
        );
    }
}
