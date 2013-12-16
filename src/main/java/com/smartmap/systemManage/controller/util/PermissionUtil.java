package com.smartmap.systemManage.controller.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.smartmap.systemManage.model.Operate;
import com.smartmap.systemManage.model.Resource;

public class PermissionUtil {

	public static void recursionToJsonObject(Resource resourceModule, List<Operate> operateAllList, HashMap<Long , Resource> resourceHashMap, JSONObject jsonObject)
	{
		Long id = resourceModule.getId();
  		jsonObject.put("id", resourceModule.getId());
  		jsonObject.put("code", resourceModule.getCode());
  		jsonObject.put("name", resourceModule.getResourceName());
  		jsonObject.put("text", resourceModule.getResourceName());
  		jsonObject.put("sortOrder", resourceModule.getSortOrder());  	
  		jsonObject.put("description", resourceModule.getDescription());
  		jsonObject.put("category", resourceModule.getParent()==null?"":resourceModule.getParent().getResourceName());  		
  		jsonObject.put("leaf", resourceModule.getChildren().size()==0?true:false); 
  		jsonObject.put("checked", resourceHashMap.get(id) != null);
  		jsonObject.put("expanded", true);
  		//
  		
  		long operateModuleCodes = resourceModule.getOperateCodes();
  		long operateUserCodes = resourceHashMap.get(id) == null?0:resourceHashMap.get(id).getOperateCodes();
  		operateToJsonObject(operateModuleCodes, operateAllList, operateUserCodes, jsonObject);
  		Set<Resource> resourceList = resourceModule.getChildren();
  		if(resourceList.size()>0)
  		{
	  		JSONArray jsonArray = new JSONArray();
	  		Iterator<Resource> iteratorResource = resourceList.iterator();
	  		while (iteratorResource.hasNext()) {
	  			Resource resourceChild = iteratorResource.next();
	  			JSONObject jsonObjectChild = new JSONObject();
	  			recursionToJsonObject(resourceChild, operateAllList, resourceHashMap, jsonObjectChild);
	  			jsonArray.add(jsonObjectChild);
	  		}
	  		jsonObject.put("children", jsonArray);
  		}
	}
	
	public static void recursionToJsonObjectNoCheck(Resource resource, List<Operate> operateAllList, HashMap<Long , Resource> resourceModuleHashMap, JSONObject jsonObject)
	{
		Long id = resource.getId();
  		jsonObject.put("id", resource.getId());
  		jsonObject.put("code", resource.getCode());
  		jsonObject.put("name", resource.getResourceName());
  		jsonObject.put("text", resource.getResourceName());  		
  		jsonObject.put("leaf", resource.getChildren().size()==0?true:false); 
  		jsonObject.put("expanded", true);
  		
  		long operateCodes = resource.getOperateCodes();
  		long operateModuleCodes = resourceModuleHashMap.get(id).getOperateCodes();
  		operateToJsonObject(operateModuleCodes, operateAllList, operateCodes, jsonObject);
  		Set<Resource> resourceList = resource.getChildren();
  		if(resourceList.size()>0)
  		{
	  		JSONArray jsonArray = new JSONArray();
	  		Iterator<Resource> iteratorResource = resourceList.iterator();
	  		while (iteratorResource.hasNext()) {
	  			Resource resourceChild = iteratorResource.next();
	  			JSONObject jsonObjectChild = new JSONObject();
	  			recursionToJsonObjectNoCheck(resourceChild, operateAllList, resourceModuleHashMap, jsonObjectChild);
	  			jsonArray.add(jsonObjectChild);
	  		}
	  		jsonObject.put("children", jsonArray);
  		}
	}
	
	public static void operateToJsonObject(long operateModuleCodes, List<Operate> operateAllList, long operateCodes, JSONObject jsonObject)
	{
		JSONArray operateArray = new JSONArray();
  		Operate operateAll = null;
  		for(int i=0; i<operateAllList.size(); i++)
  		{
  			operateAll = operateAllList.get(i);
  			if((operateAll.getCode() & operateModuleCodes) == operateAll.getCode())
  			{
  				JSONObject jsonObjectOperate = new  JSONObject();
  				jsonObjectOperate.put("code", operateAll.getCode());
  				jsonObjectOperate.put("inputValue", operateAll.getCode());  				
  				jsonObjectOperate.put("boxLabel", operateAll.getOperateName());
  				jsonObjectOperate.put("name", operateAll.getOperateName());
  				jsonObjectOperate.put("checked", (operateAll.getCode() & operateCodes) == operateAll.getCode());  				
  				operateArray.add(jsonObjectOperate);  	  				
  			}
  		}
  		jsonObject.put("operate", operateArray);
	}
}
