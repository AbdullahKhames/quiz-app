package com.quiz.question.utils;

import com.quiz.question.globals.ResponseDto;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;

public class ResponseBuilder {
    public static <T> ResponseDto<T> saved(@NotNull T obj){
        ResponseDto<T> responseDto = new ResponseDto<>();
        responseDto.setCode(201);
        responseDto.setStatus(true);
        responseDto.setMessage(obj.getClass().getSimpleName() + " saved successfully");
        responseDto.setData(obj);
        return responseDto;
    }
    public static <T> ResponseDto<T> updated(@NotNull T obj){
        ResponseDto<T> responseDto = new ResponseDto<>();
        responseDto.setCode(202);
        responseDto.setStatus(true);
        responseDto.setMessage(obj.getClass().getSimpleName() + " updated successfully");
        responseDto.setData(obj);
        return responseDto;
    }
    public static <T> ResponseDto<T> retrieved(@NotNull T obj){
        ResponseDto<T> responseDto = new ResponseDto<>();
        responseDto.setCode(200);
        responseDto.setStatus(true);
        responseDto.setMessage("retrieved successfully");
        responseDto.setData(obj);
        return responseDto;
    }
    public static <T> ResponseDto<Collection<T>> retrievedAll(@NotNull Collection<T> objs){
        ResponseDto<Collection<T>> responseDto = new ResponseDto<>();
        responseDto.setCode(200);
        responseDto.setStatus(true);
        responseDto.setMessage("retrieved All successfully");
        responseDto.setData(objs);
        return responseDto;
    }
    public static <T> ResponseDto<T> deleted(){
        ResponseDto<T> responseDto = new ResponseDto<>();
        responseDto.setCode(204);
        responseDto.setStatus(true);
        responseDto.setMessage("deleted successfully");
        responseDto.setData(null);
        return responseDto;
    }
    public static <T> ResponseDto<T> notFound(){
        ResponseDto<T> responseDto = new ResponseDto<>();
        responseDto.setCode(404);
        responseDto.setStatus(false);
        responseDto.setMessage("not found");
        responseDto.setData(null);
        return responseDto;
    }
    public static <T> ResponseDto<T> notFound(Exception ex){
        ResponseDto<T> responseDto = new ResponseDto<>();
        responseDto.setCode(404);
        responseDto.setStatus(false);
        responseDto.setMessage(ex.getMessage());
        responseDto.setData(null);
        return responseDto;
    }
    public static <T> ResponseDto<T> badRequest(Object ex){
        ResponseDto<T> responseDto = new ResponseDto<>();
        responseDto.setCode(400);
        responseDto.setStatus(false);
        responseDto.setMessage(ex.toString());
        responseDto.setData(null);
        return responseDto;
    }
}
