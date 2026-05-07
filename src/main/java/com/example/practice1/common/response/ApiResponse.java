package com.example.practice1.common.response;




public class ApiResponse<T> {

    private final String message;
    private final T data;

    private ApiResponse(String message, T data){
        this.message = message;
        this.data = data;
    }






}
