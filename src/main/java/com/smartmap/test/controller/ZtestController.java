package com.smartmap.test.controller;

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



import com.smartmap.systemManage.model.User;
import com.smartmap.test.dao.ZtestDao;

@Controller
@RequestMapping("/test")
public class ZtestController {
	private static final Logger logger = LoggerFactory.getLogger(ZtestController.class);

	@Autowired
	private ZtestDao ztestDao;
	
	/**
	 * 用于测试
	 */
	@RequestMapping(method=RequestMethod.GET,value="queryUserByRoleId",produces="text/plain;charset=UTF-8")
    @ResponseBody  
    public String queryUserByRoleId(@RequestParam(value="page",required=false) Integer pageNo,    		
    		@RequestParam(value="limit",required=false) Integer countPerPage,
    		@RequestParam(value="roleId",required=false) Long roleId) throws UnsupportedEncodingException{	
		//  http://127.0.0.1:8080/gis/spring/user/queryUserByGroupId?groupId=1&_dc=1372493386241&page=1&start=0&limit=2
		//
        //取出参数
  		System.out.println("roleId="+roleId);
  		//编号
  		String resultJson="";
  		//查询数据库  		
  		List<User> userList = ztestDao.getUsersByRoleId(pageNo, countPerPage, roleId);
  		//
  		//组织数据
  		
  		//数据输出
  		
  		//
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
  	  		jsonObject.put("account", user.getAccount());
  	  		jsonObject.put("password", user.getPassword());
  	  		jsonObject.put("userName", user.getName());
  	  		if(user.getOrganization()!=null)
  	  		{
  	  			jsonObject.put("departmentName", user.getOrganization().getName()); 
  	  		}
  	  		else
  	  		{
  	  			jsonObject.put("departmentName", ""); 
  	  		}
  	  		jsonArray.add(jsonObject);
  		}
  		jsonObjectResult.put("data", jsonArray);
  		//
  		resultJson = jsonArray.toString();
  		System.out.println(resultJson);
  		return resultJson;
    }
	
	
	
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
