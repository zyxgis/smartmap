package com.smartmap.systemManage.controller;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.LinkedList;
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

import com.smartmap.systemManage.controller.util.OrganizationUtil;
import com.smartmap.systemManage.dao.EmployeeDao;
import com.smartmap.systemManage.dao.OrganizationDao;
import com.smartmap.systemManage.model.Employee;
import com.smartmap.systemManage.model.Operate;
import com.smartmap.systemManage.model.Organization;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private OrganizationDao organizationDao;
	
	/**
	 * 
	 * @param pageNo
	 * @param countPerPage
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(method=RequestMethod.GET,value="queryById",produces="text/plain;charset=UTF-8")
    @ResponseBody  
    public String queryById(
    		@RequestParam(value="id",required=false) Long id)
    				throws UnsupportedEncodingException{		
		//		
  		String resultJson="";  		
  		Employee employee = employeeDao.find(id);  		
  		
  		JSONObject jsonObject = null;
  		if(employee != null)
  		{
  			jsonObject = new JSONObject();
  	  		jsonObject.put("id", employee.getId());
  	  		jsonObject.put("code", employee.getCode());
  	  		jsonObject.put("operateName", employee.getEmployeeName());
  	  		jsonObject.put("createTime", employee.getCreateTime());
  	  		jsonObject.put("lastUpdate", employee.getLastUpdate());
  	  		jsonObject.put("description", employee.getDescription());  	  		
  		}
  		//
  		resultJson = jsonObject.toString();
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
  		List<Employee> employeeList = employeeDao.getAll();
  		Iterator<Employee> iteratorEmployee = employeeList.iterator();
  		JSONObject jsonObjectResult = new JSONObject();
  		jsonObjectResult.put("totalCount", 15);
  		//
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		while (iteratorEmployee.hasNext()) {
  			Employee employee = iteratorEmployee.next();
  			jsonObject = new JSONObject();
  	  		jsonObject.put("id", employee.getId());
  	  		jsonObject.put("code", employee.getCode());
  	  		jsonObject.put("name", employee.getEmployeeName());
  	  		jsonObject.put("createTime", employee.getCreateTime());
  	  		jsonObject.put("lastUpdate", employee.getLastUpdate());
  	  		jsonObject.put("description", employee.getDescription());
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
	@RequestMapping(method=RequestMethod.GET,value="queryByOrganizationId",produces="text/plain;charset=UTF-8")
    @ResponseBody  
    public String queryByOrganizationId(
    		@RequestParam(value="page",required=false) Integer pageNo,    		
    		@RequestParam(value="limit",required=false) Integer countPerPage,
    		@RequestParam(value="organizationId",required=false) Long organizationId)
    				throws UnsupportedEncodingException{
		//
		logger.info("page="+pageNo.toString());
		logger.info("limit="+countPerPage.toString());
		logger.info("limit="+organizationId);
		//		
  		String resultJson="";  		
  		List<Organization> organizationAllList = organizationDao.getAllOrganizations();
  		List<Organization> organizationRootList = new LinkedList<Organization>();
  		OrganizationUtil.listToTree(organizationAllList, organizationRootList);
  		List<Organization> organizationList = OrganizationUtil.downTrace(organizationRootList, organizationId);
  		List<Long> organizationIdList = new LinkedList<Long>();
  		for(int i=0; i<organizationList.size(); i++)
  		{
  			organizationIdList.add(organizationList.get(i).getId());
  		}
  		List<Employee> employeeList = employeeDao.getByOrganizationIds(organizationIdList);
  		Iterator<Employee> iteratorEmployee = employeeList.iterator();
  		JSONObject jsonObjectResult = new JSONObject();
  		jsonObjectResult.put("totalCount", 15);
  		//
  		JSONArray jsonArray = new JSONArray();
  		JSONObject jsonObject = null;
  		while (iteratorEmployee.hasNext()) {
  			Employee employee = iteratorEmployee.next();
  			jsonObject = new JSONObject();
  	  		jsonObject.put("id", employee.getId());
  	  		jsonObject.put("code", employee.getCode());
  	  		jsonObject.put("name", employee.getEmployeeName());
  	  		jsonObject.put("createTime", employee.getCreateTime());
  	  		jsonObject.put("lastUpdate", employee.getLastUpdate());
  	  		jsonObject.put("description", employee.getDescription());
  	  		jsonArray.add(jsonObject);
  		}
  		jsonObjectResult.put("data", jsonArray);
  		//
  		resultJson = jsonArray.toString();
  		logger.info(resultJson);
  		return resultJson;
    }
	
	
	
}
