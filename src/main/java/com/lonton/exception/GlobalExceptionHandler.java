package com.lonton.exception;

import com.alibaba.fastjson.JSONObject;
import com.lonton.helper.ServiceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JSONObject exceptionHandler(HttpServletRequest request, Exception e){
        ServiceContext context = new ServiceContext();
        Exception exc = e;
        if (e instanceof SQLException){
            exc = new BusinessException(ExceptionMessage.DB_EXCEPTION, ((SQLException) e).getSQLState(), ((SQLException) e).getErrorCode());
        } else if (e instanceof BusinessException){
            exc = e;
        } else {
            exc = new BusinessException(ExceptionMessage.SYS_EXCEPTION, e.getMessage());
        }       
        return context.handlerException(exc);
    }
}
