package com.xuecheng.framework.exception;

import com.google.common.collect.ImmutableMap;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionCatcher {
    private  static  Logger logger = LoggerFactory.getLogger(ExceptionCatcher.class);
    private  static  ImmutableMap<Class<? extends Throwable>,ResultCode> EXCEPTIONS;
    protected static ImmutableMap.Builder<Class<? extends Throwable>,ResultCode> builder = new ImmutableMap.Builder<>();
    @ResponseBody
    @ExceptionHandler(CustomerException.class)
    public ResponseResult CatchCustomerException(CustomerException e){
        logger.error("CustomerException：{}\r\n   excpetion:",e.getMessage(),e);
        ResultCode resultCode = e.getResultCode();
        ResponseResult responseResult = new ResponseResult(resultCode);

        return  responseResult;
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseResult CatchException(Exception e){
        logger.error("CustomerException：{}\r\n   excpetion:",e.getMessage(),e);
        if(EXCEPTIONS ==null){
            EXCEPTIONS = builder.build();
        }
        final  ResponseResult responseResult;
        final  ResultCode resultCode = EXCEPTIONS.get(e.getClass());
        if(resultCode != null){
            responseResult = new ResponseResult(resultCode);
        }else {
            responseResult = new ResponseResult(CommonCode.SERVER_ERROR);
        }
        return  responseResult;

    }


    {
        builder.put(HttpMessageNotReadableException.class, CommonCode.INVALID_PARAM);
    }

}
