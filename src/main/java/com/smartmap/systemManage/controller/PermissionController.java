package com.smartmap.systemManage.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
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
import com.smartmap.systemManage.dao.ResourceDao;
import com.smartmap.systemManage.model.Operate;
import com.smartmap.systemManage.model.Resource;
import com.smartmap.systemManage.controller.util.PermissionUtil;

@Controller
@RequestMapping("/permission")
public class PermissionController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private ResourceDao resourceDao;
	
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
	@RequestMapping(method=RequestMethod.GET,value="queryResourcesOfUserByUserId",produces="text/plain;charset=UTF-8")
    @ResponseBody  
    public String queryResourcesOfUserByUserId(
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
		List<Resource> resourceAllList = resourceDao.getAllResources();
  		List<Resource> resourceUserList = permissionDao.getResourceByUserId(pageNo, countPerPage, userId);
  		List<Operate> operateAllList = operateDao.getAllOperates(null, null);
  		//
  		List<Resource> resourceRootList = new LinkedList<Resource>();
  		ResourceUtil.listToTree(resourceUserList, resourceRootList);
  		//
  		HashMap<Long , Resource> resourceAllHashMap = new HashMap<Long , Resource>();   
  		for(int i=0; i<resourceAllList.size(); i++)
  		{
  			resourceAllHashMap.put(resourceAllList.get(i).getId(), resourceAllList.get(i));   
  		}
  		//
  		Iterator<Resource> iteratorResource = resourceRootList.iterator();  		
  		//
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		Resource resource = null;
  		while (iteratorResource.hasNext()) {
  			resource = iteratorResource.next();
  			jsonObject = new JSONObject();
  			PermissionUtil.recursionToJsonObjectNoCheck(resource, operateAllList, resourceAllHashMap, jsonObject);
  			//
  	  		jsonArray.add(jsonObject);
  		}
  		//
  		resultJson = jsonArray.toString();
  		logger.info(resultJson);
  		//
  		return resultJson;
	}
	
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
		List<Resource> resourceAllList = resourceDao.getAllResources();
  		List<Resource> resourceUserList = permissionDao.getResourceByUserId(pageNo, countPerPage, userId);
  		List<Operate> operateAllList = operateDao.getAllOperates(null, null);
  		//
  		List<Resource> resourceRootList = new LinkedList<Resource>();
  		ResourceUtil.listToTree(resourceAllList, resourceRootList);
  		//
  		HashMap<Long, Resource> resourceUserHashMap = new HashMap<Long , Resource>();   
  		for(int i=0; i<resourceUserList.size(); i++)
  		{
  			resourceUserHashMap.put(resourceUserList.get(i).getId() , resourceUserList.get(i));   
  		}
  		//
  		Iterator<Resource> iteratorResource = resourceRootList.iterator();  		
  		//
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		Resource resource = null;
  		while (iteratorResource.hasNext()) {
  			resource = iteratorResource.next();
  			jsonObject = new JSONObject();
  			PermissionUtil.recursionToJsonObject(resource, operateAllList, resourceUserHashMap, jsonObject);  			
  			//
  	  		jsonArray.add(jsonObject);
  		}
  		//
  		resultJson = jsonArray.toString();
  		logger.info(resultJson);
  		//
  		return resultJson;
	}
	
	

	/**
	 * 查询用户所拥有的资源
	 * @param pageNo
	 * @param countPerPage
	 * @param userId
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(method=RequestMethod.GET,value="queryResourcesOfRoleByRoleId",produces="text/plain;charset=UTF-8")
    @ResponseBody  
    public String queryResourcesOfRoleByRoleId(
    		@RequestParam(value="page",required=false) Integer pageNo,
    		@RequestParam(value="limit",required=false) Integer countPerPage,
    		@RequestParam(value="roleId",required=false) Long userId) 
    				throws UnsupportedEncodingException{
		//
		//logger.info("page="+pageNo.toString());
		//logger.info("limit="+countPerPage.toString());
		//logger.info("userId="+userId.toString());
		//	
		String resultJson="";
		List<Resource> resourceAllList = resourceDao.getAllResources();
  		List<Resource> resourceRoleList = permissionDao.getResourceByRoleId(pageNo, countPerPage, userId);
  		List<Operate> operateAllList = operateDao.getAllOperates(null, null);
  		//
  		List<Resource> resourceRootList = new LinkedList<Resource>();
  		ResourceUtil.listToTree(resourceRoleList, resourceRootList);
  		//
  		HashMap<Long , Resource> resourceAllHashMap = new HashMap<Long , Resource>();   
  		for(int i=0; i<resourceAllList.size(); i++)
  		{
  			resourceAllHashMap.put(resourceAllList.get(i).getId(), resourceAllList.get(i));   
  		}
  		//
  		Iterator<Resource> iteratorResource = resourceRootList.iterator();  		
  		//
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		Resource resource = null;
  		while (iteratorResource.hasNext()) {
  			resource = iteratorResource.next();
  			jsonObject = new JSONObject();
  			PermissionUtil.recursionToJsonObjectNoCheck(resource, operateAllList, resourceAllHashMap, jsonObject);
  			//
  	  		jsonArray.add(jsonObject);
  		}
  		//
  		resultJson = jsonArray.toString();
  		logger.info(resultJson);
  		//
  		return resultJson;
	}
	
	/**
	 * 查询用户所拥有的资源
	 * @param pageNo
	 * @param countPerPage
	 * @param userId
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(method=RequestMethod.GET,value="queryResourcesByRoleId",produces="text/plain;charset=UTF-8")
    @ResponseBody  
    public String queryResourcesByRoleId(
    		@RequestParam(value="page",required=false) Integer pageNo,
    		@RequestParam(value="limit",required=false) Integer countPerPage,
    		@RequestParam(value="roleId",required=false) Long roleId) 
    				throws UnsupportedEncodingException{
		//
		//logger.info("page="+pageNo.toString());
		//logger.info("limit="+countPerPage.toString());
		//logger.info("userId="+userId.toString());
		//	
		String resultJson="";
		List<Resource> resourceAllList = resourceDao.getAllResources();
  		List<Resource> resourceRoleList = permissionDao.getResourceByRoleId(pageNo, countPerPage, roleId);
  		List<Operate> operateAllList = operateDao.getAllOperates(null, null);
  		//
  		List<Resource> resourceRootList = new LinkedList<Resource>();
  		ResourceUtil.listToTree(resourceAllList, resourceRootList);
  		//
  		HashMap<Long, Resource> resourceRoleHashMap = new HashMap<Long , Resource>();   
  		for(int i=0; i<resourceRoleList.size(); i++)
  		{
  			resourceRoleHashMap.put(resourceRoleList.get(i).getId() , resourceRoleList.get(i));   
  		}
  		//
  		Iterator<Resource> iteratorResource = resourceRootList.iterator();  		
  		//
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		Resource resource = null;
  		while (iteratorResource.hasNext()) {
  			resource = iteratorResource.next();
  			jsonObject = new JSONObject();
  			PermissionUtil.recursionToJsonObject(resource, operateAllList, resourceRoleHashMap, jsonObject);  			
  			//
  	  		jsonArray.add(jsonObject);
  		}
  		//
  		resultJson = jsonArray.toString();
  		logger.info(resultJson);
  		//
  		return resultJson;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="grandRoleResources",produces="text/plain;charset=UTF-8")
    @ResponseBody  
	public int grandRoleResources(Long roleId, Long[] resourceIdArray, Long[] operateCodesArray) {
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
