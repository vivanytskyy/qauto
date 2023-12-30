package com.gmail.ivanytskyy.vitaliy.api.exceptions;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 28/12/2023
 */
public class UnexpectedHttpStatusCodeException extends RuntimeException{
    public UnexpectedHttpStatusCodeException(int statusCode) {
        super("Unexpected code " + statusCode);
    }
}