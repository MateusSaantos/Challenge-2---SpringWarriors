package com.compassuol.sp.challenge.ecommerce.web.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;


@Getter @ToString
public class ErrorMessage {



    private String status;
    private int code;
    private String message;



    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String,String> details;



    public ErrorMessage(){

    }

    public ErrorMessage(HttpServletRequest request, HttpStatus code, String message){
        this.code = code.value();
        this.status = code.getReasonPhrase();
        this.message= message;
    }
    public ErrorMessage(HttpServletRequest request, HttpStatus code, String message, BindingResult result){
        this.code = code.value();
        this.status = code.getReasonPhrase();
        this.message= message;
        addErrors(result);
    }

    private void addErrors(BindingResult result) {
        this.details = new HashMap<>();
        for(FieldError fieldError: result.getFieldErrors()){
            this.details.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
    }

    ;


}
