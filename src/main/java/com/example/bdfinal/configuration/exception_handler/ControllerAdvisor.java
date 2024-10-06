package com.example.bdfinal.configuration.exception_handler;

import com.example.bdfinal.configuration.Constants;
import com.example.bdfinal.exception.DataCannotBeUpdatedException;
import com.example.bdfinal.exception.DataRepeatsItselfException;
import com.example.bdfinal.exception.NoDataFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor {
    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ExceptionResponse> handleGeneralException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(
                Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE, HttpStatus.NOT_FOUND.toString(), LocalDateTime.now()
        ));
    }

    @ExceptionHandler(DataRepeatsItselfException.class)
    public ResponseEntity<ExceptionResponse> handleDataRepeatsItselfException(DataRepeatsItselfException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(
            String.format(Constants.DATA_REPEATS_ITSELF_EXCEPTION_MESSAGE, ex.getMessage()), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()
        ));
    }

    @ExceptionHandler(DataCannotBeUpdatedException.class)
    public ResponseEntity<ExceptionResponse> handleDataCannotBeUpdatedException(DataCannotBeUpdatedException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(
            String.format(Constants.DATA_CANNOT_BE_UPDATED_EXCEPTION_MESSAGE, ex.getMessage()), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()
        ));
    }
}
