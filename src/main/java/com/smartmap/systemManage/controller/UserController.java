package com.smartmap.systemManage.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smartmap.systemManage.dao.RoleDao;
import com.smartmap.systemManage.dao.UserDao;
import com.smartmap.systemManage.model.Role;
import com.smartmap.systemManage.model.User;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserDao userDao;
	
	/**
	 * 
	 * @param pageNo
	 * @param countPerPage
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(method=RequestMethod.GET,value="queryAllUsers",produces="text/plain;charset=UTF-8")
    @ResponseBody  
    public String queryAllUsers(
    		@RequestParam(value="page",required=false) Integer pageNo,    		
    		@RequestParam(value="limit",required=false) Integer countPerPage)
    				throws UnsupportedEncodingException{
		//
		logger.info("page="+pageNo.toString());
		logger.info("limit="+countPerPage.toString());
		//		
  		String resultJson="";  		
  		List<User> userList = userDao.getAllUsers(pageNo, countPerPage);
  		Iterator<User> iteratorUser = userList.iterator();
  		JSONObject jsonObjectResult = new JSONObject();
  		jsonObjectResult.put("totalCount", 15);
  		//  		
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		while (iteratorUser.hasNext()) {
  			User user = iteratorUser.next();
  			jsonObject = new JSONObject();
  	  		jsonObject.put("id", user.getId());
  	  		jsonObject.put("loginUsername", user.getLoginUsername());
  	  		jsonObject.put("loginPassword", user.getLoginPassword());
  	  		
  	  		jsonArray.add(jsonObject);
  		}
  		jsonObjectResult.put("data", jsonArray);
  		//
  		resultJson = jsonArray.toString();
  		logger.info(resultJson);
  		return resultJson;
    }
	
	/**
	 * 
	 * @param pageNo
	 * @param countPerPage
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(method=RequestMethod.GET,value="queryUsersByLoginUsername",produces="text/plain;charset=UTF-8")
    @ResponseBody  
    public String queryUsersByLoginUsername(
    		@RequestParam(value="page",required=false) Integer pageNo,    		
    		@RequestParam(value="limit",required=false) Integer countPerPage,
    		@RequestParam(value="loginUsername",required=false) String loginUsername)
    				throws UnsupportedEncodingException{
		//
		//logger.info("page="+pageNo.toString());
		//logger.info("limit="+countPerPage.toString());
		//logger.info("loginUsername="+loginUsername.toString());
		//
		
  		String resultJson="";  		
  		List<User> userList = userDao.getUsersByLoginUsername(pageNo, countPerPage, loginUsername);
  		Iterator<User> iteratorUser = userList.iterator();
  		JSONObject jsonObjectResult = new JSONObject();
  		jsonObjectResult.put("totalCount", 15);
  		//  		
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		while (iteratorUser.hasNext()) {
  			User user = iteratorUser.next();
  			jsonObject = new JSONObject();
  	  		jsonObject.put("id", user.getId());
  	  		jsonObject.put("loginUsername", user.getLoginUsername());
	  		jsonObject.put("loginPassword", user.getLoginPassword());
  	  		jsonArray.add(jsonObject);
  		}
  		jsonObjectResult.put("data", jsonArray);
  		//
  		resultJson = jsonArray.toString();
  		logger.info(resultJson);
  		return resultJson;
    }
	
	/**
	 * 
	 * @param pageNo
	 * @param countPerPage
	 * @param roleId
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(method=RequestMethod.GET,value="queryUsersByRoleId",produces="text/plain;charset=UTF-8")
    @ResponseBody  
    public String queryUsersByRoleId(
    		@RequestParam(value="page",required=false) Integer pageNo,    		
    		@RequestParam(value="limit",required=false) Integer countPerPage,
    		@RequestParam(value="roleId",required=false) Long roleId)
    				throws UnsupportedEncodingException{
		//
		logger.info("page="+pageNo.toString());
		logger.info("limit="+countPerPage.toString());
		logger.info("roleId="+roleId.toString());
		//
  		String resultJson="";  		
  		List<User> userList = userDao.getUsersByRoleId(pageNo, countPerPage, roleId);
  		Iterator<User> iteratorUser = userList.iterator();
  		JSONObject jsonObjectResult = new JSONObject();
  		jsonObjectResult.put("totalCount", 15);
  		//  		
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		while (iteratorUser.hasNext()) {
  			User user = iteratorUser.next();
  			jsonObject = new JSONObject();
  	  		jsonObject.put("id", user.getId());
	  	  	jsonObject.put("loginUsername", user.getLoginUsername());
	  		jsonObject.put("loginPassword", user.getLoginPassword());
  	  		jsonArray.add(jsonObject);
  		}
  		jsonObjectResult.put("data", jsonArray);
  		//
  		resultJson = jsonArray.toString();
  		logger.info(resultJson);
  		return resultJson;
    }
	
	@RequestMapping(method=RequestMethod.GET,value="grandUserRoles",produces="text/plain;charset=UTF-8")
    @ResponseBody  
	public int grandUserRoles(@RequestParam(value="userId",required=false) Long userId, @RequestParam(value="roleIdArray",required=false) Long[] roleIdArray) {
		//
		logger.info("userId="+userId.toString());
		logger.info("roleIdArray="+roleIdArray.toString());
		//
		int countRevoke = userDao.revokeRoles(userId, null);
		int countGrand = userDao.grandRoles(userId, roleIdArray);	
		logger.info("countRevoke="+countRevoke);
		logger.info("countGrand="+countGrand);
		return countRevoke+countGrand;		
	}
}
