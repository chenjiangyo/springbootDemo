package com.lonton.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lonton.vo.User;

@Repository
public interface UserMapper {

    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> selectSelective(User record);
    
    int deleteUsers(@Param("list")List<String> list,@Param("user")User user);
    
    int getCountByName(String loginName);
}