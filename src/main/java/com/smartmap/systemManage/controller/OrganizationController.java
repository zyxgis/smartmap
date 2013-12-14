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
import com.smartmap.systemManage.controller.util.OrganizationUtil;
import com.smartmap.systemManage.dao.OrganizationDao;
import com.smartmap.systemManage.model.Organization;


@Controller
@RequestMapping("/organization")
public class OrganizationController {
	private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

	@Autowired
	private OrganizationDao organizationDao;
	
		
	@RequestMapping(method=RequestMethod.GET,value="edit")
	public String edit(@RequestParam(value="id",required=false) Long id) {		
		logger.debug("Received request to edit organization id : "+id);				
		ModelAndView mav = new ModelAndView();		
 		mav.setViewName("edit");
 		Organization organization = null;
 		if (id == null) {
 			organization = new Organization();
 		} else {
 			organization = organizationDao.find(id);
 		}
 		
 		//
  		String resultJson = "{\"status\":\"successful\"}";
  		logger.info(resultJson);
  		//
  		return resultJson;
	}
	@Transactional
	@RequestMapping(method=RequestMethod.POST,value="save") 
	public String save(@ModelAttribute Organization organization) {		
		logger.info("Received postback on organization "+organization);		
		organization = organizationDao.save(organization);
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
	@RequestMapping(method=RequestMethod.GET,value="queryAllToList",produces="text/plain;charset=UTF-8")
    @ResponseBody  
    public String queryAllToList(
    		@RequestParam(value="page",required=false) Integer pageNo,    		
    		@RequestParam(value="limit",required=false) Integer countPerPage) 
    				throws UnsupportedEncodingException{
		//
		logger.info("page="+pageNo.toString());
		logger.info("limit="+countPerPage.toString());
		//
		String resultJson="";
  		List<Organization> organizationList = organizationDao.getAllOrganizations();
  		Iterator<Organization> iteratorOrganization = organizationList.iterator();
  		JSONObject jsonObjectResult = new JSONObject();
  		jsonObjectResult.put("totalCount", 15);
  		//  		
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		while (iteratorOrganization.hasNext()) {
  			Organization organization = iteratorOrganization.next();
  			jsonObject = new JSONObject();
  	  		jsonObject.put("id", organization.getId());
  	  		jsonObject.put("code", organization.getCode());  	  		
  	  		jsonObject.put("name", organization.getOrganizationName());
  	  		jsonObject.put("category", organization.getCategory());
  	  		jsonObject.put("parentName", organization.getParent()==null?"":organization.getParent().getOrganizationName());
  	  		jsonObject.put("description", organization.getDescription());
	  	  	jsonObject.put("createTime", organization.getCreateTime());
	  		jsonObject.put("lastUpdate", organization.getLastUpdate());
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
	@RequestMapping(method=RequestMethod.GET,value="queryAllToTree",produces="text/plain;charset=UTF-8")
    @ResponseBody  
    public String queryAllToTree() 
    				throws UnsupportedEncodingException{
		
		String resultJson="";
		List<Organization> organizationList = organizationDao.getAllOrganizations();  		
  		//
  		List<Organization> organizationRootList = new LinkedList<Organization>();
  		OrganizationUtil.listToTree(organizationList, organizationRootList);
  		Iterator<Organization> iteratorOrganization = organizationRootList.iterator();  		
  		//  		
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		while (iteratorOrganization.hasNext()) {
  			Organization organization = iteratorOrganization.next();
  			jsonObject = new JSONObject();
  			OrganizationUtil.recursionToJsonObject(organization, jsonObject);
  	  		//
  	  		jsonArray.add(jsonObject);
  		}
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
	@RequestMapping(method=RequestMethod.GET,value="queryToTreeWithLevel",produces="text/plain;charset=UTF-8")
    @ResponseBody  
    public String queryToTreeWithLevel(@RequestParam(value="maxLevel",required=false) Long maxLevel) 
    				throws UnsupportedEncodingException{
		
		String resultJson="";
		List<Organization> organizationList = organizationDao.getAllOrganizations();  		
  		//
  		List<Organization> organizationRootList = new LinkedList<Organization>();
  		OrganizationUtil.listToTree(organizationList, organizationRootList);
  		Iterator<Organization> iteratorOrganization = organizationRootList.iterator();  		
  		//  		
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		while (iteratorOrganization.hasNext()) {
  			Organization organization = iteratorOrganization.next();
  			jsonObject = new JSONObject();
  			OrganizationUtil.recursionToJsonObjectWithLevel(organization, jsonObject, maxLevel, 0);
  	  		//
  	  		jsonArray.add(jsonObject);
  		}
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
	@RequestMapping(method=RequestMethod.GET,value="queryByParentId",produces="text/plain;charset=UTF-8")
    @ResponseBody  
    public String queryByParentId(
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
  		List<Organization> organizationList = organizationDao.getOrganizationByParentId(parentId);
  		
  		Iterator<Organization> iteratorOrganization = organizationList.iterator();
  		JSONObject jsonObjectResult = new JSONObject();
  		jsonObjectResult.put("totalCount", 15);
  		//  		
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;  		
  		while (iteratorOrganization.hasNext()) {
  			Organization organization = iteratorOrganization.next();
  			jsonObject = new JSONObject();  	  		
  	  		jsonObject.put("id", organization.getId());
	  		jsonObject.put("code", organization.getCode());  	  		
	  		jsonObject.put("name", organization.getOrganizationName());
	  		jsonObject.put("text", organization.getOrganizationName());
	  		jsonObject.put("category", organization.getCategory());  	  		
	  		jsonObject.put("parentName", organization.getParent()==null?"":organization.getParent().getOrganizationName());
	  		jsonObject.put("description", organization.getDescription());
	  		jsonObject.put("createTime", organization.getCreateTime());
	  		jsonObject.put("lastUpdate", organization.getLastUpdate());
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
	
	
	
	
	
	
	
	
	
	
}
