package com.smartmap.systemManage.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.smartmap.systemManage.dao.UserDao;
import com.smartmap.systemManage.model.User;

@Controller
@RequestMapping("/login")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserDao userDao;
	
	@RequestMapping(method=RequestMethod.POST,value="login",produces="text/plain;charset=UTF-8")
    @ResponseBody
    public ModelAndView login(
    		@RequestParam(value="loginUsername",required=false) String loginUsername,    		
    		@RequestParam(value="loginPassword",required=false) String loginPassword) 
    				throws UnsupportedEncodingException{
		//取出参数
		logger.info("loginUsername="+loginUsername);
		logger.info("loginPassword="+loginPassword);
		User user = null;
  		String returnUrl=null;
  		//查询数据库
  		List<User> userList = userDao.getUsersByLoginUsername(null, null, loginPassword);  		
  		//
  		if(userList==null || userList.size()==0)
  		{
  			returnUrl = "../../login.jsp?userId=noUser";
  		}
  		else
  		{
  			user = userList.get(0);
  			if(!user.getLoginPassword().equals(loginPassword))
  			{
  				returnUrl = "../../login.jsp";
  			}
  			else
  			{
  				returnUrl = "../../index.jsp";
  			}
  		}  
  		RedirectView redirectView = new RedirectView(returnUrl);
  		ModelAndView modelAndView = new ModelAndView(redirectView);
  		if(user != null)
  		{
  			modelAndView.addObject("userId", user.getId().toString());
  		}
  		logger.info(redirectView.getUrl());
		return  modelAndView;
	}
}
