package com.lonton.vo;

import java.util.Date;

import lombok.Data;

@Data
public class User {
    private String userId;

    private String userName;

    private String loginName;

    private String password;

    private String status;

    private String birthday;

    private String sex;

    private String phone;

    private String email;

    private String address;

    private String updateUser;

    private Date updateTime;

}