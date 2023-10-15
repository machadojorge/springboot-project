package com.miguel.spring.project.springbootproject.services.exceptions;

public class DataBaseException extends RuntimeException {

    private static final long serialVersionID = 1L;

    public DataBaseException(String msg)
    {
        super(msg);
    }
    
}
