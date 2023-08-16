package com.laoayu.parking.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: LaoAyu
 * @date: 2023/03/07
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> success(){
        return new Result<>(200,"success",null);
    }

    public static <T> Result<T> success(T data){
        return new Result<>(200,"success",data);
    }

    public static <T> Result<T> success(T data,String message){
        return new Result<>(200,message,data);
    }

    public static <T> Result<T> success(String message){
        return new Result<>(200,message,null);
    }

    public static<T>  Result<T> fail(){
        return new Result<>(201,"fail",null);
    }

    public static<T>  Result<T> fail(Integer code){
        return new Result<>(code,"fail",null);
    }

    public static<T>  Result<T> fail(Integer code, String message){
        return new Result<>(code,message,null);
    }

    public static<T>  Result<T> fail( String message){
        return new Result<>(201,message,null);
    }
}
