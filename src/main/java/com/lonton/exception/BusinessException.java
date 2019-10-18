package com.lonton.exception;

/**
 * 业务异常类
 */
public class BusinessException extends RuntimeException {

    private String errorCode;

    private String errorMessage;

    public BusinessException(ExceptionMessage message){
        super(String.format("[%s]%s",new String[]{message.getCode(), message.getMessage()}));
        this.errorCode = message.getCode();
        this.errorMessage = message.getMessage();
    }

    public BusinessException(ExceptionMessage message, Object... params){
        super(String.format("[%s]%s",new String[]{message.getCode(), String.format(message.getMessage(), params)}));
        this.errorCode = message.getCode();
        this.errorMessage = String.format(message.getMessage(),params);
    }

    public BusinessException(Throwable e, ExceptionMessage message){
        super(String.format("[%s]%s",new String[]{message.getCode(), message.getMessage()}), e);
        this.errorCode = message.getCode();
        this.errorMessage = message.getMessage();
    }

    public BusinessException(Throwable e, ExceptionMessage message, Object... params){
        super(String.format("[%s]%s",new String[]{message.getCode(), String.format(message.getMessage(), params)}), e);
        this.errorCode = message.getCode();
        this.errorMessage = message.getMessage();
        if(params != null){
            this.errorMessage = String.format(message.getMessage(),params);
        }
    }

    public BusinessException(Throwable e){
        super(e);
        this.errorMessage = e.getMessage();
    }


}
