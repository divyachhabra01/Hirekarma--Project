package com.springboot.excel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class FileUploadException extends ResponseEntityExceptionHandler {
@ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity maxUploadSizeExceededException(MaxUploadSizeExceededException e){
     return  ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMaxUploadSize()+ "Size Of File exceeds maximum file size");
}
}
