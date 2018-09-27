package com.rich.rest.exception;

import com.rich.rest.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler{

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<CommonResponse> entityNotFound(DataNotFoundException ex) {
        CommonResponse response = new CommonResponse(HttpStatus.NOT_FOUND.value(), Constants.ERROR_MSG_404);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
}
