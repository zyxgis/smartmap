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

import com.smartmap.systemManage.dao.FunctionDao;
import com.smartmap.systemManage.model.Function;
import com.smartmap.systemManage.model.Operator;

@Controller
@RequestMapping("/function")
public class FunctionController {
	private static final Logger logger = LoggerFactory.getLogger(FunctionController.class);

	@Autowired
	private FunctionDao functionDao;
	
	@RequestMapping(method=RequestMethod.GET,value="queryAllFunctions",produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String queryAllFunctions(
    		@RequestParam(value="page",required=false) Integer pageNo,
    		@RequestParam(value="limit",required=false) Integer countPerPage)
    				throws UnsupportedEncodingException{
		//取出参数
  		String resultValue="";
  		//查询数据库  		
  		List<Function> functionList = functionDao.getAllFunction();  		
  		Iterator<Function> iteratorFunction = functionList.iterator();
  		JSONObject jsonObjectResult = new JSONObject();
  		jsonObjectResult.put("totalCount", 15);
  		//  		
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		while (iteratorFunction.hasNext()) {
  			Function function = iteratorFunction.next();
  			jsonObject = new JSONObject();
  	  		jsonObject.put("id", function.getId());
  	  		jsonObject.put("code", function.getCode());
  	  		jsonObject.put("name", function.getName());
  	  		jsonArray.add(jsonObject);
  		}
  		jsonObjectResult.put("data", jsonArray);
  		//
  		resultValue = jsonArray.toString();
  		System.out.println(resultValue);
  		return resultValue;
	}
	
	
	@RequestMapping(method=RequestMethod.GET,value="queryFunctionOfResourceByUserId",produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String queryFunctionOfResourceByUserId(
    		@RequestParam(value="page",required=false) Integer pageNo,
    		@RequestParam(value="limit",required=false) Integer countPerPage,
    		@RequestParam(value="userId",required=false) Long userId,
    		@RequestParam(value="resourceId",required=false) Long resourceId)
    				throws UnsupportedEncodingException{
		//取出参数
  		String resultValue="";
  		//查询数据库  		
  		List<Function> functionList = functionDao.getFunctionOfResourceByUserId(pageNo, countPerPage, userId, resourceId);
  		Iterator<Function> iteratorFunction = functionList.iterator();
  		JSONObject jsonObjectResult = new JSONObject();
  		jsonObjectResult.put("totalCount", 15);
  		//  		
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		while (iteratorFunction.hasNext()) {
  			Function function = iteratorFunction.next();
  			jsonObject = new JSONObject();
  	  		jsonObject.put("id", function.getId());
  	  		jsonObject.put("code", function.getCode());
  	  		jsonObject.put("name", function.getName());
  	  		jsonArray.add(jsonObject);
  		}
  		jsonObjectResult.put("data", jsonArray);
  		//
  		resultValue = jsonArray.toString();
  		System.out.println(resultValue);
  		return resultValue;
	}
}
