package com.lonton.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lonton.exception.BusinessException;
import com.lonton.exception.ExceptionMessage;
import com.lonton.helper.ReqPageInfo;
import com.lonton.mapper.UserMapper;
import com.lonton.vo.User;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public User login(User loginUser) {
		List<User> user = userMapper.selectSelective(loginUser);
		if(user.size() != 1) {
			throw new BusinessException(ExceptionMessage.LOGIN_EXCEPTION);
		}
		return user.get(0);
	}

	public PageInfo<User> fetchAllUser(User user, ReqPageInfo reqPage) {
		PageHelper.startPage(reqPage.getPageNum(), reqPage.getPageSize(),"LOGIN_NAME asc");
		return new PageInfo<User>(userMapper.selectSelective(user));
	}

	public void removeUsers(List<String> list, User loginUser) {
		User user = new User();
		user.setStatus("0000");
		user.setUpdateTime(new Date());
		user.setUpdateUser(loginUser.getLoginName());
		userMapper.deleteUsers(list,user);
	}

	public void insertUser(User user) {
		 user.setUserId(UUID.randomUUID().toString().replace("-", ""));
		 user.setStatus("0001");
		 user.setUpdateTime(new Date());
		 userMapper.insertSelective(user);
	}

	public void updateUser(User user) {
		user.setUpdateTime(new Date());
		userMapper.updateByPrimaryKeySelective(user);
	}

	public boolean checkLoginName(String loginName) {
		int result = userMapper.getCountByName(loginName);
		return result > 0 ;
	}
}
