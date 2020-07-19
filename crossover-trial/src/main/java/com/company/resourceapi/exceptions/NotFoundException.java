package com.company.resourceapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{


    private static final long serialVersionUID = 1L;

    public NotFoundException(final Class<?> class1, final long id) {
        super("Cannot find an instance of " +class1.getName()+"with id: " +id);
        
	}


}