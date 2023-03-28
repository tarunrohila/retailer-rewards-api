package com.demo.api.handler;

import com.demo.api.exception.CustomerException;
import com.demo.api.exception.RewardsException;
import com.demo.api.exception.TransactionException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Class which is used to handle exception response
 *
 * @author Tarun Rohila
 * @since Mar 25, 2023
 */
@ControllerAdvice
public class AppResponseExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * method to handle exception
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value
            = {RuntimeException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
     if(ex instanceof RewardsException || ex instanceof TransactionException || ex instanceof CustomerException) {
         return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(),HttpStatus.BAD_REQUEST);
     } else {
         return new ResponseEntity<>(String.format("Failed to process the request please try after some times errorMessage = [%s]", ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
     }
    }

}
