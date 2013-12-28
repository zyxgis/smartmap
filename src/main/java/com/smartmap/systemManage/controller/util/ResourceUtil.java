package com.smartmap.systemManage.controller.util;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.smartmap.systemManage.model.Operate;
import com.smartmap.systemManage.model.Resource;

public class ResourceUtil {
	public static void recursionToJsonObject(Resource resource, List<Operate> operateList, JSONObject jsonObject)
	{
  		jsonObject.put("id", resource.getId());
  		jsonObject.put("code", resource.getCode());
  		jsonObject.put("name", resource.getResourceName());
  		jsonObject.put("text", resource.getResourceName());
	  	//jsonObject.put("leaf", resource.getChildren()==null?true:false);
	  	jsonObject.put("leaf", resource.getChildren().size()==0?true:false); 
  		//jsonObject.put("leaf", false);
	  	jsonObject.put("target", resource.getTarget());
  		jsonObject.put("url", resource.getUrl());
  		jsonObject.put("sortOrder", resource.getSortOrder());
  		jsonObject.put("parentName", resource.getParent()==null?"":resource.getParent().getResourceName());
  		jsonObject.put("description", resource.getDescription());
  		jsonObject.put("expanded", true);
  		//
  		long operateCodes = resource.getOperateCodes();
		OperateUtil.operateToJsonObject(operateCodes, operateList, jsonObject);
			
  		Set<Resource> resourceList = resource.getChildren();
  		if(resourceList.size()>0)
  		{
	  		JSONArray jsonArray = new JSONArray();
	  		Iterator<Resource> iteratorResource = resourceList.iterator();
	  		while (iteratorResource.hasNext()) {
	  			Resource resourceChild = iteratorResource.next();
	  			JSONObject jsonObjectChild = new JSONObject();
	  			recursionToJsonObject(resourceChild, operateList, jsonObjectChild);
	  			jsonArray.add(jsonObjectChild);
	  		}
	  		jsonObject.put("children", jsonArray);
  		}
	}
	
	public static void listToTree(List<Resource> resourceList, List<Resource> resourceRootList)
	{
		for(int i=0; i<resourceList.size(); i++)
  		{
  			Resource resource = resourceList.get(i);
  			resource.setChildren(new LinkedHashSet<Resource>());
  		}
  		for(int i=0; i<resourceList.size(); i++)
  		{
  			Resource resource = resourceList.get(i);
  			Long parentId = resource.getParentId();
  			if(parentId==null)
  			{
  				resourceRootList.add(resource);
  			}
  			else
  			{  				
  				for(int j=0; j<resourceList.size(); j++)
  		  		{
  					Resource resourceOther = resourceList.get(j);
  					if(resourceOther.getId().equals(parentId) && resourceOther.getId()!=null)
  					{
  						resourceOther.getChildren().add(resource);
  						resource.setParent(resourceOther);
  					}
  		  		}
  			}
  		}
	}
}
