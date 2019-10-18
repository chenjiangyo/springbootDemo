package com.lonton.exception;

public enum ExceptionMessage {

    WEB_REQ_EXCEPTION("BS010001", "请求参数错误"),
    BASE_DB_EXCEPTION("BS020002", "DB error!"),

    LOGIN_EXCEPTION("BS010002", "用户名或密码错误"),

    LOGIN_NO_AUTH("BS010003", "没有登录权限"),
      
    SYS_EXCEPTION("SY010001", "系统异常！%s"),
    
    DB_EXCEPTION("DB010001", "数据库异常！SQLSTATUS[%s],ERRORCODE[%s]");

    private String code;

    private String message;

    private ExceptionMessage(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode(){
        return this.code;
    }

    public String getMessage(){
        return this.message;
    }

}
