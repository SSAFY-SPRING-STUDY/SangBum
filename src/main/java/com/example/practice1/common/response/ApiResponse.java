package com.example.practice1.common.response;


import com.example.practice1.common.exception.ErrorCode;

public class ApiResponse<T> {

    private final String message;
    private final T data;

    private ApiResponse(String message, T data){
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data){
        return new ApiResponse<T>("요청이 성공했습니다", data);
    }

    public static ApiResponse<Void> success(){
        return new ApiResponse<>("요청이 성공했습니다.", null);
    }

    public static ApiResponse<Void> error(ErrorCode errorCode){
        return new ApiResponse<>(errorCode.getMessage(),null);
    }

    public String getMessage(){
        return message;
    }

    public T getData(){
        return data;
    }





}
