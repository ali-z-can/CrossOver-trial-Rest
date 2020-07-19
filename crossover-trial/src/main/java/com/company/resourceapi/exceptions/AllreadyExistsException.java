package com.company.resourceapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class AllreadyExistsException extends RuntimeException{

    
    private static final long serialVersionUID = 1L;

    public AllreadyExistsException(long id){
        super(""+id);
    }

}