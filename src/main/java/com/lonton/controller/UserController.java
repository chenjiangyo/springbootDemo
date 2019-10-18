package com.lonton.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.lonton.helper.ServiceContext;
import com.lonton.service.UserService;
import com.lonton.vo.User;


@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
    @RequestMapping("/login.json")
    public JSONObject login(@RequestParam Map<String, Object> jsonParam, HttpSession session) {
        ServiceContext context = new ServiceContext(jsonParam);
        User user = userService.login((User)context.getParam(User.class));
        context.putRspValue("userInfo", user);
        session.setAttribute("userInfo", user);
        return context.getJsonRsp();
    }
    
	@RequestMapping("/user/getUser.json")
	public JSONObject getUser(@RequestParam Map<String, Object> jsonParam) {
		ServiceContext context = new ServiceContext(jsonParam);
		PageInfo<User> userPageInfo = userService.fetchAllUser((User)context.getParam(User.class),context.getReqPage());
		context.putRpsPage(userPageInfo);
		return context.getJsonRsp();
	}
	
	@RequestMapping("/user/removeUser.json")
	public JSONObject removeUser(@RequestParam Map<String, Object> jsonParam,HttpSession session) {
		ServiceContext context = new ServiceContext(jsonParam);
		User loginUser = (User)session.getAttribute("userInfo");
		List<String> list = context.getListParam("dels", String.class);
		userService.removeUsers(list,loginUser);
		return context.getJsonRsp();
	}
	
	@RequestMapping("/user/addUser.json")
	public JSONObject addUser(@RequestParam Map<String, Object> jsonParam,HttpSession session) {
		ServiceContext context = new ServiceContext(jsonParam);
		User user = (User) context.getParam(User.class);
		User loginUser = (User)session.getAttribute("userInfo");
		user.setUpdateUser(loginUser.getLoginName());
		userService.insertUser(user);
		return context.getJsonRsp();
	}
	
	@RequestMapping("/user/updateUser.json")
	public JSONObject updateUser(@RequestParam Map<String, Object> jsonParam,HttpSession session) {
		ServiceContext context = new ServiceContext(jsonParam);
		User user = (User) context.getParam(User.class);
		User loginUser = (User)session.getAttribute("userInfo");
		user.setUpdateUser(loginUser.getLoginName());
		userService.updateUser(user);
		return context.getJsonRsp();
	}
	
	@RequestMapping("/user/checkUserName.json")
	public JSONObject checkUserName(@RequestParam Map<String, Object> jsonParam) {
		ServiceContext context = new ServiceContext(jsonParam);
		String checkValue = (String)context.getParam("checkValue");
		boolean result = userService.checkLoginName(checkValue);
		context.putRspValue("elements", result);
		return context.getJsonRsp();
	}
	
}
