package com.gmail.ivanytskyy.vitaliy.api.exceptions;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 13/08/2023
 */
public class ApiResponseException extends RuntimeException{
    public ApiResponseException(int code, String message, String reason){
        super(String.format("HTTP status code: %1s; message: %2s;\ncaused by: %3s", code, message, reason));
    }
}