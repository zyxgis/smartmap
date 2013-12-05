package com.smartmap.systemManage.controller;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smartmap.systemManage.dao.RoleDao;
import com.smartmap.systemManage.dao.UserDao;
import com.smartmap.systemManage.model.Role;
import com.smartmap.systemManage.model.User;

@Controller
@RequestMapping("/role")
public class RoleController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private RoleDao roleDao;
	
	
	/**
	 * 
	 * @param pageNo
	 * @param countPerPage
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(method=RequestMethod.GET,value="queryAllRoles",produces="text/plain;charset=UTF-8")
    @ResponseBody  
    public String queryAllRoles(
    		@RequestParam(value="page",required=false) Integer pageNo,    		
    		@RequestParam(value="limit",required=false) Integer countPerPage)
    				throws UnsupportedEncodingException{
  		String resultJson="";  		
  		List<Role> roleList = roleDao.getAllRoles(pageNo, countPerPage);
  		Iterator<Role> iteratorRole = roleList.iterator();
  		JSONObject jsonObjectResult = new JSONObject();
  		jsonObjectResult.put("totalCount", 15);
  		//  		
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		while (iteratorRole.hasNext()) {
  			Role role = iteratorRole.next();
  			jsonObject = new JSONObject();
  	  		jsonObject.put("id", role.getId());
  	  		jsonObject.put("code", role.getCode());
  	  		jsonObject.put("name", role.getName());
  	  		jsonObject.put("superRole", role.getSuperRole());
  	  		jsonObject.put("description", role.getDescription());  	  		
  	  		jsonArray.add(jsonObject);
  		}
  		jsonObjectResult.put("data", jsonArray);
  		//
  		resultJson = jsonArray.toString();
  		System.out.println(resultJson);
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
	@RequestMapping(method=RequestMethod.GET,value="queryRolesByUserId",produces="text/plain;charset=UTF-8")
    @ResponseBody  
    public String queryRolesByUserId(
    		@RequestParam(value="page",required=false) Integer pageNo,    		
    		@RequestParam(value="limit",required=false) Integer countPerPage,
    		@RequestParam(value="userId",required=false) Long userId)
    				throws UnsupportedEncodingException{
  		String resultJson="";
  		List<Role> roleList = roleDao.getRolesByUserId(pageNo, countPerPage, userId);
  		Iterator<Role> iteratorRole = roleList.iterator();
  		JSONObject jsonObjectResult = new JSONObject();
  		jsonObjectResult.put("totalCount", 15);
  		//  		
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		while (iteratorRole.hasNext()) {
  			Role role = iteratorRole.next();
  			jsonObject = new JSONObject();
  	  		jsonObject.put("id", role.getId());
  	  		jsonObject.put("code", role.getCode());
  	  		jsonObject.put("name", role.getName());
  	  		jsonObject.put("superRole", role.getSuperRole());
  	  		jsonObject.put("description", role.getDescription());  	  		
  	  		jsonArray.add(jsonObject);
  		}
  		jsonObjectResult.put("data", jsonArray);
  		//
  		resultJson = jsonArray.toString();
  		System.out.println(resultJson);
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
	@RequestMapping(method=RequestMethod.GET,value="queryRolesByRoleName",produces="text/plain;charset=UTF-8")
    @ResponseBody  
    public String queryRolesByRoleName(
    		@RequestParam(value="page",required=false) Integer pageNo,    		
    		@RequestParam(value="limit",required=false) Integer countPerPage,
    		@RequestParam(value="roleName",required=false) String roleName)
    				throws UnsupportedEncodingException{
  		String resultJson="";
  		List<Role> roleList = roleDao.getRolesByRoleName(pageNo, countPerPage, roleName);
  		Iterator<Role> iteratorRole = roleList.iterator();
  		JSONObject jsonObjectResult = new JSONObject();
  		jsonObjectResult.put("totalCount", 15);
  		//  		
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		while (iteratorRole.hasNext()) {
  			Role role = iteratorRole.next();
  			jsonObject = new JSONObject();
  	  		jsonObject.put("id", role.getId());
  	  		jsonObject.put("code", role.getCode());
  	  		jsonObject.put("name", role.getName());
  	  		jsonObject.put("superRole", role.getSuperRole());
  	  		jsonObject.put("description", role.getDescription());  	  		
  	  		jsonArray.add(jsonObject);
  		}
  		jsonObjectResult.put("data", jsonArray);
  		//
  		resultJson = jsonArray.toString();
  		System.out.println(resultJson);
  		return resultJson;
    }
}
