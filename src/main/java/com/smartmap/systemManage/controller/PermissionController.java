package com.smartmap.systemManage.controller;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Query;

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

import com.smartmap.systemManage.controller.util.OperateUtil;
import com.smartmap.systemManage.controller.util.ResourceUtil;
import com.smartmap.systemManage.dao.OperateDao;
import com.smartmap.systemManage.dao.PermissionDao;
import com.smartmap.systemManage.model.Operate;
import com.smartmap.systemManage.model.Resource;


@Controller
@RequestMapping("/permission")
public class PermissionController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private PermissionDao permissionDao;
	
	@Autowired
	private OperateDao operateDao;
	
	/**
	 * 查询用户所拥有的资源
	 * @param pageNo
	 * @param countPerPage
	 * @param userId
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(method=RequestMethod.GET,value="queryResourcesByUserId",produces="text/plain;charset=UTF-8")
    @ResponseBody  
    public String queryResourcesByUserId(
    		@RequestParam(value="page",required=false) Integer pageNo,
    		@RequestParam(value="limit",required=false) Integer countPerPage,
    		@RequestParam(value="userId",required=false) Long userId) 
    				throws UnsupportedEncodingException{
		//
		//logger.info("page="+pageNo.toString());
		//logger.info("limit="+countPerPage.toString());
		//logger.info("userId="+userId.toString());
		//	
		String resultJson="";
  		List<Resource> resourceList = permissionDao.getResourceByUserId(pageNo, countPerPage, userId);
  		List<Operate> operateList = operateDao.getAllOperates(null, null);
  		//
  		List<Resource> resourceRootList = new LinkedList<Resource>();
  		ResourceUtil.listToTree(resourceList, resourceRootList);
  		//
  		Iterator<Resource> iteratorResource = resourceRootList.iterator();
  		JSONObject jsonObjectResult = new JSONObject();
  		jsonObjectResult.put("totalCount", 15);
  		//
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		while (iteratorResource.hasNext()) {
  			Resource resource = iteratorResource.next();
  			jsonObject = new JSONObject();
  			ResourceUtil.recursionToJsonObject(resource, operateList, jsonObject);
  			long operateCodes = resource.getOperateCodes();
  			OperateUtil.operateToJsonObject(operateCodes, operateList, jsonObject);
  			//
  	  		jsonArray.add(jsonObject);
  		}
  		jsonObjectResult.put("data", jsonArray);
  		//
  		resultJson = jsonArray.toString();
  		logger.info(resultJson);
  		//
  		return resultJson;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="grandRoleResources",produces="text/plain;charset=UTF-8")
    @ResponseBody  
	public int grandRoleResources(Long roleId, List<Long> resourceIdArray, List<Long> operateCodesArray) {
		//
		logger.info("roleId="+roleId.toString());
		logger.info("resourceIdArray="+resourceIdArray.toString());
		logger.info("operateCodesArray="+operateCodesArray.toString());
		//	
		int countRevoke = permissionDao.revokeResources(roleId, null);
		int countGrand = permissionDao.grandResources(roleId, resourceIdArray, operateCodesArray);	
		//
		logger.info("countRevoke="+countRevoke);
		logger.info("countGrand="+countGrand);
		return countRevoke+countGrand;		
	}
}
