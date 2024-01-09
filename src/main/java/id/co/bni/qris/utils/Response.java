package id.co.bni.qris.utils;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import id.co.bni.qris.domain.common.ApiResponse;

import java.util.List;

public class Response {
    private Response(){}

    public static <T> ResponseEntity<Object> build(String responseMessage, T data, List<String> errors, HttpStatusCode httpStatus){
        ApiResponse<T> apiResponse = ApiResponse.<T>builder()
                .message(responseMessage)
                .data(data)
                .errors(errors)
                .build();

        return new ResponseEntity<>(apiResponse, httpStatus);
    }
}

