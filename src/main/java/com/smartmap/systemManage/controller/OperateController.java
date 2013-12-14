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

import com.smartmap.systemManage.dao.OperateDao;
import com.smartmap.systemManage.model.Operate;


@Controller
@RequestMapping("/operate")
public class OperateController {
	
	private static final Logger logger = LoggerFactory.getLogger(OperateController.class);

	
	@Autowired
	private OperateDao operateDao;
	
	/**
	 * 
	 * @param pageNo
	 * @param countPerPage
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(method=RequestMethod.GET,value="queryAllOperates",produces="text/plain;charset=UTF-8")
    @ResponseBody  
    public String queryAllOperates(
    		@RequestParam(value="page",required=false) Integer pageNo,    		
    		@RequestParam(value="limit",required=false) Integer countPerPage)
    				throws UnsupportedEncodingException{
		//
		logger.info("page="+pageNo.toString());
		logger.info("limit="+countPerPage.toString());
		//		
  		String resultJson="";  		
  		List<Operate> operateList = operateDao.getAllOperates(pageNo, countPerPage);
  		Iterator<Operate> iteratorOperate = operateList.iterator();
  		JSONObject jsonObjectResult = new JSONObject();
  		jsonObjectResult.put("totalCount", 15);
  		//  		
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		while (iteratorOperate.hasNext()) {
  			Operate operate = iteratorOperate.next();
  			jsonObject = new JSONObject();
  	  		jsonObject.put("id", operate.getId());
  	  		jsonObject.put("code", operate.getCode());
  	  		jsonObject.put("operateName", operate.getOperateName());
  	  		jsonObject.put("createTime", operate.getCreateTime());
	  		jsonObject.put("lastUpdate", operate.getLastUpdate());
  	  		jsonObject.put("description", operate.getDescription());
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
	@RequestMapping(method=RequestMethod.GET,value="queryOperatesByOperateName",produces="text/plain;charset=UTF-8")
    @ResponseBody  
    public String queryOperatesByOperateName(
    		@RequestParam(value="page",required=false) Integer pageNo,    		
    		@RequestParam(value="limit",required=false) Integer countPerPage,
    		@RequestParam(value="limit",required=false) String operateName)
    				throws UnsupportedEncodingException{
		//
		logger.info("page="+pageNo.toString());
		logger.info("limit="+countPerPage.toString());
		logger.info("limit="+operateName);
		//		
  		String resultJson="";  		
  		List<Operate> operateList = operateDao.getRolesByRoleName(pageNo, countPerPage, operateName);
  		Iterator<Operate> iteratorOperate = operateList.iterator();
  		JSONObject jsonObjectResult = new JSONObject();
  		jsonObjectResult.put("totalCount", 15);
  		//  		
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		while (iteratorOperate.hasNext()) {
  			Operate operate = iteratorOperate.next();
  			jsonObject = new JSONObject();
  	  		jsonObject.put("id", operate.getId());
  	  		jsonObject.put("code", operate.getCode());
  	  		jsonObject.put("operateName", operate.getOperateName());
  	  		jsonObject.put("createTime", operate.getCreateTime());
  	  		jsonObject.put("lastUpdate", operate.getLastUpdate());
  	  		jsonObject.put("description", operate.getDescription());
  	  		jsonArray.add(jsonObject);
  		}
  		jsonObjectResult.put("data", jsonArray);
  		//
  		resultJson = jsonArray.toString();
  		logger.info(resultJson);
  		return resultJson;
    }
	
}
