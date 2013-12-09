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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smartmap.gis.model.Person;
import com.smartmap.systemManage.controller.util.OperateUtil;
import com.smartmap.systemManage.controller.util.ResourceUtil;
import com.smartmap.systemManage.dao.OperateDao;
import com.smartmap.systemManage.dao.ResourceDao;
import com.smartmap.systemManage.model.Operate;
import com.smartmap.systemManage.model.Resource;


@Controller
@RequestMapping("/resource")
public class ResourceController {
	private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

	@Autowired
	private ResourceDao resourceDao;
	
	@Autowired
	private OperateDao operateDao;
	
	@RequestMapping(method=RequestMethod.GET,value="edit")
	public String editResource(@RequestParam(value="id",required=false) Long id) {		
		logger.debug("Received request to edit resource id : "+id);				
		ModelAndView mav = new ModelAndView();		
 		mav.setViewName("edit");
 		Resource resource = null;
 		if (id == null) {
 			resource = new Resource();
 		} else {
 			resource = resourceDao.find(id);
 		}
 		
 		//
  		String resultJson = "{\"status\":\"successful\"}";
  		logger.info(resultJson);
  		//
  		return resultJson;
	}
	@Transactional
	@RequestMapping(method=RequestMethod.POST,value="saveResource") 
	public String saveResource(@ModelAttribute Resource resource) {		
		logger.info("Received postback on resource "+resource);		
		resource = resourceDao.save(resource);
		//
  		String resultJson = "{\"status\":\"successful\"}";
  		logger.info(resultJson);
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
	@RequestMapping(method=RequestMethod.GET,value="queryAllResources",produces="text/plain;charset=UTF-8")
    @ResponseBody  
    public String queryAllResources(
    		@RequestParam(value="page",required=false) Integer pageNo,    		
    		@RequestParam(value="limit",required=false) Integer countPerPage) 
    				throws UnsupportedEncodingException{
		//
		logger.info("page="+pageNo.toString());
		logger.info("limit="+countPerPage.toString());
		//
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
  	  		
  	  		//jsonObject.put("text", resource.getName());
  	  		//jsonObject.put("leaf", resource.getChildren()==null?true:false); 
  	  		
  	  		jsonObject.put("url", resource.getUrl());
  	  		jsonObject.put("sortOrder", resource.getSortOrder());
  	  		jsonObject.put("parentName", resource.getParent()==null?"":resource.getParent().getResourceName());
  	  		jsonObject.put("description", resource.getDescription());
  	  		jsonArray.add(jsonObject);
  		}
  		jsonObjectResult.put("data", jsonArray);
  		//
  		resultJson = jsonArray.toString();
  		logger.info(resultJson);
  		//
  		return resultJson;
	}
	
	/**
	 * 查询所有子资源
	 * @param pageNo
	 * @param countPerPage
	 * @param parentResourceId
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(method=RequestMethod.GET,value="queryResourcesByParentId",produces="text/plain;charset=UTF-8")
    @ResponseBody  
    public String queryResourcesByParentId(
    		@RequestParam(value="page",required=false) Integer pageNo,    		
    		@RequestParam(value="limit",required=false) Integer countPerPage,
    		@RequestParam(value="parentId",required=false) Long parentId) 
    				throws UnsupportedEncodingException{
		//
		logger.info("page="+pageNo.toString());
		logger.info("limit="+countPerPage.toString());
		logger.info("parentId="+parentId.toString());
		//	
		String resultJson="";
  		List<Resource> resourceList = resourceDao.getResourceByParentId(parentId);
  		List<Operate> operateList = operateDao.getAllOperates(null, null);
  		Iterator<Resource> iteratorResource = resourceList.iterator();
  		JSONObject jsonObjectResult = new JSONObject();
  		jsonObjectResult.put("totalCount", 15);
  		//  		
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		String operateString = "";
  		while (iteratorResource.hasNext()) {
  			Resource resource = iteratorResource.next();
  			jsonObject = new JSONObject();
  	  		jsonObject.put("id", resource.getId());
  	  		jsonObject.put("code", resource.getCode());
  	  		jsonObject.put("name", resource.getResourceName());
  	  		jsonObject.put("text", resource.getResourceName());
  	  		//jsonObject.put("leaf", resource.getChildren()==null?true:false);  
  	  		//jsonObject.put("leaf", resource.getChildren().size()==0?true:false); 
  	  		jsonObject.put("url", resource.getUrl());
  	  		jsonObject.put("sortOrder", resource.getSortOrder());
  	  		jsonObject.put("parentName", resource.getParent()==null?"":resource.getParent().getResourceName());
  	  		jsonObject.put("description", resource.getDescription());
  	  		//
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
  		List<Operate> operateList = operateDao.getAllOperates(null, null); 		
  		//
  		List<Resource> resourceRootList = new LinkedList<Resource>();
  		ResourceUtil.listToTree(resourceList, resourceRootList);
  		Iterator<Resource> iteratorResource = resourceRootList.iterator();  		
  		//  		
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		while (iteratorResource.hasNext()) {
  			Resource resource = iteratorResource.next();
  			jsonObject = new JSONObject();
  			ResourceUtil.recursionToJsonObject(resource, operateList, jsonObject);
  			//
  			//long operateCodes = resource.getOperateCodes();
  	  		//OperateUtil.operateToJsonObject(operateCodes, operateList, jsonObject);
  	  		//
  	  		jsonArray.add(jsonObject);
  		}
  		//
  		resultJson = jsonArray.toString();
  		logger.info(resultJson);
  		//
  		return resultJson;
	}
	
	
	
	
	
	
	
	
}
