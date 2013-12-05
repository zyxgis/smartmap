package com.smartmap.systemManage.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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

import com.smartmap.systemManage.dao.ResourceDao;
import com.smartmap.systemManage.model.Operator;
import com.smartmap.systemManage.model.Resource;


@Controller
@RequestMapping("/resource")
public class ResourceController {
	private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

	@Autowired
	private ResourceDao resourceDao;
	
	/**
	 * 查询所有资源
	 * @param pageNo
	 * @param countPerPage
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(method=RequestMethod.GET,value="queryAllResources",produces="text/plain;charset=UTF-8")
    @ResponseBody  
    public String queryAllResources(
    		@RequestParam(value="page",required=false) Integer pageNo,    		
    		@RequestParam(value="limit",required=false) Integer countPerPage) 
    				throws UnsupportedEncodingException{		
		String resultJson="";
  		List<Resource> resourceList = resourceDao.getAllResources();
  		Iterator<Resource> iteratorResource = resourceList.iterator();
  		JSONObject jsonObjectResult = new JSONObject();
  		jsonObjectResult.put("totalCount", 15);
  		//  		
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		while (iteratorResource.hasNext()) {
  			Resource resource = iteratorResource.next();
  			jsonObject = new JSONObject();
  	  		jsonObject.put("id", resource.getId());
  	  		jsonObject.put("code", resource.getCode());
  	  		jsonObject.put("name", resource.getName());
  	  		//jsonObject.put("text", resource.getName());
  	  		//jsonObject.put("leaf", resource.getChildren()==null?true:false); 
  	  		
  	  		jsonObject.put("url", resource.getUrl());
  	  		jsonObject.put("sortOrder", resource.getSortOrder());
  	  		jsonObject.put("parentName", resource.getParent()==null?"":resource.getParent().getName());
  	  		jsonObject.put("description", resource.getDescription());
  	  		jsonArray.add(jsonObject);
  		}
  		jsonObjectResult.put("data", jsonArray);
  		//
  		resultJson = jsonArray.toString();
  		System.out.println(resultJson);
  		//
  		return resultJson;
	}
	
	/**
	 * 查询所有资源
	 * @param pageNo
	 * @param countPerPage
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(method=RequestMethod.GET,value="queryAllResourcesToTree",produces="text/plain;charset=UTF-8")
    @ResponseBody  
    public String queryAllResourcesToTree() 
    				throws UnsupportedEncodingException{		
		String resultJson="";
  		List<Resource> resourceList = resourceDao.getAllResources();
  		List<Resource> resourceRootList = new LinkedList<Resource>();
  		for(int i=0; i<resourceList.size(); i++)
  		{
  			Resource resource = resourceList.get(i);
  			resource.setChildren(new LinkedHashSet<Resource>());
  		}
  		for(int i=0; i<resourceList.size(); i++)
  		{
  			Resource resource = resourceList.get(i);
  			Resource resourceParent = resource.getParent();
  			if(resourceParent==null)
  			{
  				resourceRootList.add(resource);
  			}
  			else
  			{
  				Long parentId = resourceParent.getId();
  				for(int j=0; j<resourceList.size(); j++)
  		  		{
  					Resource resourceOther = resourceList.get(j);
  					if(resourceOther.getId().equals(parentId) && parentId != null && resourceOther.getId()!=null)
  					{
  						resourceOther.getChildren().add(resource);
  						resource.setParent(resourceOther);
  					}
  		  		}
  			}
  		}
  		
  		
  		Iterator<Resource> iteratorResource = resourceRootList.iterator();  		
  		//  		
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		while (iteratorResource.hasNext()) {
  			Resource resource = iteratorResource.next();
  			jsonObject = new JSONObject();
  			recursionToJsonObject(resource, jsonObject);
  	  		jsonArray.add(jsonObject);
  		}
  		//
  		resultJson = jsonArray.toString();
  		logger.info(resultJson);
  		System.out.println(resultJson);
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
        
		String resultJson="";
  		List<Resource> resourceList = resourceDao.getResourceByUserId(pageNo, countPerPage, userId);
  		List<Resource> resourceRootList = new LinkedList<Resource>();
  		for(int i=0; i<resourceList.size(); i++)
  		{
  			Resource resource = resourceList.get(i);
  			resource.setChildren(new LinkedHashSet<Resource>());
  		}
  		for(int i=0; i<resourceList.size(); i++)
  		{
  			Resource resource = resourceList.get(i);
  			Resource resourceParent = resource.getParent();
  			if(resourceParent==null)
  			{
  				resourceRootList.add(resource);
  			}
  			else
  			{
  				Long parentId = resourceParent.getId();
  				for(int j=0; j<resourceList.size(); j++)
  		  		{
  					Resource resourceOther = resourceList.get(j);
  					if(resourceOther.getId().equals(parentId) && parentId != null && resourceOther.getId()!=null)
  					{
  						resourceOther.getChildren().add(resource);
  						resource.setParent(resourceOther);
  					}
  		  		}
  			}
  		}
  		
  		
  		Iterator<Resource> iteratorResource = resourceRootList.iterator();
  		JSONObject jsonObjectResult = new JSONObject();
  		jsonObjectResult.put("totalCount", 15);
  		//  		
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		while (iteratorResource.hasNext()) {
  			Resource resource = iteratorResource.next();
  			jsonObject = new JSONObject();
  			recursionToJsonObject(resource, jsonObject);
  	  		jsonArray.add(jsonObject);
  		}
  		jsonObjectResult.put("data", jsonArray);
  		//
  		resultJson = jsonArray.toString();
  		System.out.println(resultJson);
  		//
  		return resultJson;
	}

	public void recursionToJsonObject(Resource resource, JSONObject jsonObject)
	{
  		jsonObject.put("id", resource.getId());
  		jsonObject.put("code", resource.getCode());
  		jsonObject.put("name", resource.getName());
  		jsonObject.put("text", resource.getName());
	  	//jsonObject.put("leaf", resource.getChildren()==null?true:false);
	  	jsonObject.put("leaf", resource.getChildren().size()==0?true:false); 
  		//jsonObject.put("leaf", false);
  		jsonObject.put("url", resource.getUrl());
  		jsonObject.put("sortOrder", resource.getSortOrder());
  		//jsonObject.put("parentName", resource.getParent()==null?"":resource.getParent().getName());
  		jsonObject.put("description", resource.getDescription());
  		Set<Resource> resourceList = resource.getChildren();
  		if(resourceList.size()>0)
  		{
	  		JSONArray jsonArray = new JSONArray();
	  		Iterator<Resource> iteratorResource = resourceList.iterator();
	  		while (iteratorResource.hasNext()) {
	  			Resource resourceChild = iteratorResource.next();
	  			JSONObject jsonObjectChild = new JSONObject();
	  			recursionToJsonObject(resourceChild, jsonObjectChild);
	  			jsonArray.add(jsonObjectChild);
	  		}
	  		jsonObject.put("children", jsonArray);
  		}
	}
	
	/**
	 * 查询所有子资源
	 * @param pageNo
	 * @param countPerPage
	 * @param parentResourceId
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(method=RequestMethod.GET,value="queryResourcesByParentResourceId",produces="text/plain;charset=UTF-8")
    @ResponseBody  
    public String queryResourcesByParentResourceId(
    		@RequestParam(value="page",required=false) Integer pageNo,    		
    		@RequestParam(value="limit",required=false) Integer countPerPage,
    		@RequestParam(value="parentId",required=false) Long parentId) 
    				throws UnsupportedEncodingException{
		//
		String resultJson="";
  		List<Resource> resourceList = resourceDao.getResourceByParentResourceId(parentId);
  		Iterator<Resource> iteratorResource = resourceList.iterator();
  		JSONObject jsonObjectResult = new JSONObject();
  		jsonObjectResult.put("totalCount", 15);
  		//  		
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		while (iteratorResource.hasNext()) {
  			Resource resource = iteratorResource.next();
  			jsonObject = new JSONObject();
  	  		jsonObject.put("id", resource.getId());
  	  		jsonObject.put("code", resource.getCode());
  	  		jsonObject.put("name", resource.getName());
  	  		jsonObject.put("text", resource.getName());
  	  		//jsonObject.put("leaf", resource.getChildren()==null?true:false);  
  	  		//jsonObject.put("leaf", resource.getChildren().size()==0?true:false); 
  	  		jsonObject.put("url", resource.getUrl());
  	  		jsonObject.put("sortOrder", resource.getSortOrder());
  	  		jsonObject.put("parentName", resource.getParent()==null?"":resource.getParent().getName());
  	  		jsonObject.put("description", resource.getDescription());
  	  		jsonArray.add(jsonObject);
  		}
  		jsonObjectResult.put("data", jsonArray);
  		//
  		resultJson = jsonArray.toString();
  		System.out.println(resultJson);
  		//
  		return resultJson;
	}
	
	
	
}
