package com.compassuol.sp.challenge.ecommerce.web.exception;


import com.compassuol.sp.challenge.ecommerce.exception.DuplicateProductNameException;
import com.compassuol.sp.challenge.ecommerce.exception.EntityNotFoundException;
import com.compassuol.sp.challenge.ecommerce.exception.ProductValidationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                        HttpServletRequest request,
                                                                        BindingResult result){

        log.error("Api Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request,HttpStatus.UNPROCESSABLE_ENTITY,"Campo(s) invalidos",result));
    }

    @ExceptionHandler({ProductValidationException.class, DuplicateProductNameException.class})
    public ResponseEntity<ErrorMessage> dataIntegrityViolationException(RuntimeException ex,
                                                                        HttpServletRequest request
                                                                        ){

        log.error("Api Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
<<<<<<< HEAD
                .body(new ErrorMessage(request,HttpStatus.CONFLICT, ex.getMessage()));
=======
                .body(new ErrorMessage(request,HttpStatus.CONFLICT,"Produto já existente"));
>>>>>>> documentation

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFoundException(RuntimeException ex,
                                                                        HttpServletRequest request
    ){

        log.error("Api Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request,HttpStatus.NOT_FOUND, ex.getMessage()));

    }











}
