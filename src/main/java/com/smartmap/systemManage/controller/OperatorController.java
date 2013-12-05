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

import com.smartmap.systemManage.dao.OperatorDao;
import com.smartmap.systemManage.dao.ResourceDao;
import com.smartmap.systemManage.model.Operator;
import com.smartmap.systemManage.model.Role;

@Controller
@RequestMapping("/operator")
public class OperatorController {
	private static final Logger logger = LoggerFactory.getLogger(OperatorController.class);

	@Autowired
	private OperatorDao operatorDao;
	
	/**
	 * 查询所有资源
	 * @param pageNo
	 * @param countPerPage
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(method=RequestMethod.GET,value="queryAllOperators",produces="text/plain;charset=UTF-8")
    @ResponseBody  
    public String queryAllOperators(
    		@RequestParam(value="page",required=false) Integer pageNo,    		
    		@RequestParam(value="limit",required=false) Integer countPerPage) 
    				throws UnsupportedEncodingException{		
        
		String resultJson="";
  		List<Operator> operatorList = operatorDao.getAllOperators();
  		Iterator<Operator> iteratorOperator = operatorList.iterator();
  		JSONObject jsonObjectResult = new JSONObject();
  		jsonObjectResult.put("totalCount", 15);
  		//  		
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		while (iteratorOperator.hasNext()) {
  			Operator operator = iteratorOperator.next();
  			jsonObject = new JSONObject();
  	  		jsonObject.put("id", operator.getId());
  	  		jsonObject.put("resource", operator.getResource().getName());
  	  		jsonObject.put("function", operator.getFunction().getName());
  	  		jsonObject.put("description", operator.getDescription());	  		
  	  		jsonArray.add(jsonObject);
  		}
  		jsonObjectResult.put("data", jsonArray);
  		//
  		resultJson = jsonArray.toString();
  		System.out.println(resultJson);
  		return resultJson;
	}
	
	/**
	 * 查询所有资源
	 * @param pageNo
	 * @param countPerPage
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(method=RequestMethod.GET,value="queryOperatorsByResourceId",produces="text/plain;charset=UTF-8")
    @ResponseBody  
    public String queryOperatorsByResourceId(
    		@RequestParam(value="page",required=false) Integer pageNo,    		
    		@RequestParam(value="limit",required=false) Integer countPerPage,
    		@RequestParam(value="resourceId",required=false) Long resourceId) 
    				throws UnsupportedEncodingException{		
        
		String resultJson="";
  		List<Operator> operatorList = operatorDao.getOperatorByResourceId(resourceId);
  		Iterator<Operator> iteratorOperator = operatorList.iterator();
  		JSONObject jsonObjectResult = new JSONObject();
  		jsonObjectResult.put("totalCount", 15);
  		//  		
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		while (iteratorOperator.hasNext()) {
  			Operator operator = iteratorOperator.next();
  			jsonObject = new JSONObject();
  	  		jsonObject.put("id", operator.getId());
  	  		jsonObject.put("resource", operator.getResource().getName());
  	  		jsonObject.put("function", operator.getFunction().getName());
  	  		jsonObject.put("description", operator.getDescription());	  		
  	  		jsonArray.add(jsonObject);
  		}
  		jsonObjectResult.put("data", jsonArray);
  		//
  		resultJson = jsonArray.toString();
  		System.out.println(resultJson);
  		return resultJson;
	}
	
	/**
	 * 查询所有资源
	 * @param pageNo
	 * @param countPerPage
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(method=RequestMethod.GET,value="queryOperatorsByFunctionId",produces="text/plain;charset=UTF-8")
    @ResponseBody  
    public String queryOperatorsByFunctionId(
    		@RequestParam(value="page",required=false) Integer pageNo,    		
    		@RequestParam(value="limit",required=false) Integer countPerPage,
    		@RequestParam(value="functionId",required=false) Long functionId) 
    				throws UnsupportedEncodingException{		
        
		String resultJson="";
  		List<Operator> operatorList = operatorDao.getOperatorByFunctionId(functionId);
  		Iterator<Operator> iteratorOperator = operatorList.iterator();
  		JSONObject jsonObjectResult = new JSONObject();
  		jsonObjectResult.put("totalCount", 15);
  		//  		
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		while (iteratorOperator.hasNext()) {
  			Operator operator = iteratorOperator.next();
  			jsonObject = new JSONObject();
  	  		jsonObject.put("id", operator.getId());
  	  		jsonObject.put("resource", operator.getResource().getName());
  	  		jsonObject.put("function", operator.getFunction().getName());
  	  		jsonObject.put("description", operator.getDescription());	  		
  	  		jsonArray.add(jsonObject);
  		}
  		jsonObjectResult.put("data", jsonArray);
  		//
  		resultJson = jsonArray.toString();
  		System.out.println(resultJson);
  		return resultJson;
	}
	
	/**
	 * 查询所有资源
	 * @param pageNo
	 * @param countPerPage
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(method=RequestMethod.GET,value="queryOperatorsByUserId",produces="text/plain;charset=UTF-8")
    @ResponseBody  
    public String queryOperatorsByUserId(
    		@RequestParam(value="page",required=false) Integer pageNo,    		
    		@RequestParam(value="limit",required=false) Integer countPerPage,
    		@RequestParam(value="userId",required=false) Long userId) 
    				throws UnsupportedEncodingException{		
        
		String resultJson="";
  		List<Operator> operatorList = operatorDao.getOperatorByUserId(pageNo,countPerPage,userId);
  		Iterator<Operator> iteratorOperator = operatorList.iterator();
  		JSONObject jsonObjectResult = new JSONObject();
  		jsonObjectResult.put("totalCount", 15);
  		//  		
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		while (iteratorOperator.hasNext()) {
  			Operator operator = iteratorOperator.next();
  			jsonObject = new JSONObject();
  	  		jsonObject.put("id", operator.getId());
  	  		jsonObject.put("resource", operator.getResource().getName());
  	  		jsonObject.put("function", operator.getFunction().getName());
  	  		jsonObject.put("description", operator.getDescription());	  		
  	  		jsonArray.add(jsonObject);
  		}
  		jsonObjectResult.put("data", jsonArray);
  		//
  		resultJson = jsonArray.toString();
  		System.out.println(resultJson);
  		return resultJson;
	}
	

	/**
	 * 查询所有资源
	 * @param pageNo
	 * @param countPerPage
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(method=RequestMethod.GET,value="queryOperatorOfResourceByUserId",produces="text/plain;charset=UTF-8")
    @ResponseBody  
    public String queryOperatorOfResourceByUserId(
    		@RequestParam(value="page",required=false) Integer pageNo,    		
    		@RequestParam(value="limit",required=false) Integer countPerPage,
    		@RequestParam(value="userId",required=false) Long userId,
    		@RequestParam(value="resourceId",required=false) Long resourceId) 
    				throws UnsupportedEncodingException{
        
		String resultJson="";
  		List<Operator> operatorList = operatorDao.getOperatorOfResourceByUserId(pageNo, countPerPage, userId, resourceId);
  		Iterator<Operator> iteratorOperator = operatorList.iterator();
  		JSONObject jsonObjectResult = new JSONObject();
  		jsonObjectResult.put("totalCount", 15);
  		//  		
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		while (iteratorOperator.hasNext()) {
  			Operator operator = iteratorOperator.next();
  			jsonObject = new JSONObject();
  	  		jsonObject.put("id", operator.getId());
  	  		jsonObject.put("resource", operator.getResource().getName());
  	  		jsonObject.put("function", operator.getFunction().getName());
  	  		jsonObject.put("description", operator.getDescription());	  		
  	  		jsonArray.add(jsonObject);
  		}
  		jsonObjectResult.put("data", jsonArray);
  		//
  		resultJson = jsonArray.toString();
  		System.out.println(resultJson);
  		return resultJson;
	}
}
