package com.smartmap.systemManage.controller.util;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;




import com.smartmap.systemManage.model.Organization;

public class OrganizationUtil {
	public static void recursionToJsonObject(Organization organization, JSONObject jsonObject)
	{
  		jsonObject.put("id", organization.getId());
  		jsonObject.put("code", organization.getCode());  	  		
  		jsonObject.put("name", organization.getOrganizationName());
  		jsonObject.put("text", organization.getOrganizationName());
  		jsonObject.put("leaf", organization.getChildren().size()==0?true:false); 
  		jsonObject.put("category", organization.getCategory());
  		jsonObject.put("parentId", organization.getParentId()==null?"root":organization.getParentId());
  		jsonObject.put("parentName", organization.getParent()==null?"":organization.getParent().getOrganizationName());
  		jsonObject.put("description", organization.getDescription());
  		jsonObject.put("createTime", organization.getCreateTime());
  		jsonObject.put("lastUpdate", organization.getLastUpdate());
  		jsonObject.put("expanded", true);
			
  		Set<Organization> organizationList = organization.getChildren();
  		if(organizationList != null && organizationList.size()>0)
  		{
	  		JSONArray jsonArray = new JSONArray();
	  		Iterator<Organization> iteratorOrganization = organizationList.iterator();
	  		while (iteratorOrganization.hasNext()) {
	  			Organization organizationChild = iteratorOrganization.next();
	  			JSONObject jsonObjectChild = new JSONObject();
	  			recursionToJsonObject(organizationChild, jsonObjectChild);
	  			jsonArray.add(jsonObjectChild);
	  		}
	  		jsonObject.put("children", jsonArray);
  		}
	}
	
	public static void recursionToJsonObjectWithLevel(Organization organization, JSONObject jsonObject, long maxLevel, long currentLevel)
	{
		if(currentLevel > maxLevel)return;
  		jsonObject.put("id", organization.getId());
  		jsonObject.put("code", organization.getCode());  	  		
  		jsonObject.put("name", organization.getOrganizationName());
  		jsonObject.put("text", organization.getOrganizationName());
  		jsonObject.put("leaf", (organization.getChildren().size()==0 || currentLevel == maxLevel)?true:false); 
  		jsonObject.put("category", organization.getCategory());
  		jsonObject.put("parentId", organization.getParentId()==null?"root":organization.getParentId());
  		jsonObject.put("parentName", organization.getParent()==null?"":organization.getParent().getOrganizationName());
  		jsonObject.put("description", organization.getDescription());
  		jsonObject.put("createTime", organization.getCreateTime());
  		jsonObject.put("lastUpdate", organization.getLastUpdate());
  		jsonObject.put("expanded", true);
  		currentLevel++;
  		Set<Organization> organizationList = organization.getChildren();
  		if(organizationList != null && organizationList.size()>0)
  		{
	  		JSONArray jsonArray = new JSONArray();
	  		Iterator<Organization> iteratorOrganization = organizationList.iterator();
	  		while (iteratorOrganization.hasNext()) {
	  			Organization organizationChild = iteratorOrganization.next();
	  			JSONObject jsonObjectChild = new JSONObject();
	  			recursionToJsonObjectWithLevel(organizationChild, jsonObjectChild, maxLevel, currentLevel);
	  			jsonArray.add(jsonObjectChild);
	  		}
	  		jsonObject.put("children", jsonArray);
  		}
	}
	
	public static void listToTree(List<Organization> organizationList, List<Organization> organizationRootList)
	{
		for(int i=0; i<organizationList.size(); i++)
  		{
			Organization organization = organizationList.get(i);
			organization.setChildren(new LinkedHashSet<Organization>());
  		}
  		for(int i=0; i<organizationList.size(); i++)
  		{
  			Organization organization = organizationList.get(i);
  			Long parentId = organization.getParentId();
  			if(parentId==null)
  			{
  				organizationRootList.add(organization);
  			}
  			else
  			{  				
  				for(int j=0; j<organizationList.size(); j++)
  		  		{
  					Organization organizationOther = organizationList.get(j);
  					if(organizationOther.getId().equals(parentId) && organizationOther.getId()!=null)
  					{
  						organizationOther.getChildren().add(organization);
  						organization.setParent(organizationOther);
  					}
  		  		}
  			}
  		}
	}
	
	public static void treeToList(Organization organization, List<Organization> organizationList)
	{
		organizationList.add(organization);
		if(organization.getChildren() != null && organization.getChildren().size() > 0)
		{			
			Iterator<Organization> organizationIterator = organization.getChildren().iterator();
			 while(organizationIterator.hasNext())
			 {
				 Organization organizationChild = organizationIterator.next();
				 treeToList(organizationChild, organizationList);
			 }			
		}		
	}
	
	public static Organization treeFindById(Organization organization, Long id)
	{
		Organization organizationReturn = null;
		if(organization.getId() == id)
		{
			return organization;
		}
		else if(organization.getChildren() != null && organization.getChildren().size()>0)
		{
			 Iterator<Organization> organizationIterator = organization.getChildren().iterator();
			 while(organizationIterator.hasNext())
			 {
				 Organization organizationChild = organizationIterator.next();
				 organizationReturn = treeFindById(organizationChild, id);
				 if(organizationReturn != null)
				 {
					 return organizationReturn;
				 }
			 }
		}
		return organizationReturn;
	}
	
	public static Organization treeFindById(List<Organization> organizationList, Long id)
	{
		long idValue = id.longValue();
		Organization organization = null;
		for(int i=0; i<organizationList.size(); i++)
		{
			organization = organizationList.get(i);
			if(organization.getId()==idValue)
			{
				return organization;
			}
			else if(organization.getChildren() != null && organization.getChildren().size()>0)
			{
				List<Organization> organizationChildrenList = new LinkedList<Organization>();
				organizationChildrenList.addAll(organization.getChildren());
				organization = treeFindById(organizationChildrenList, id);
				if(organization != null && organization.getId()==idValue)
				{
					return organization;
				}
			}
		}
		return null;
	}
	
	public static List<Organization> upTrace(List<Organization> organizationRootList, Long id)
	{
		List<Organization> organizationReverseList = new LinkedList<Organization>();
		List<Organization> organizationList = new LinkedList<Organization>();
		Organization organization = treeFindById(organizationRootList, id);
		if(organization != null)
		{
			Organization organizationCurrent = organization;
			organizationList.add(organizationCurrent);
			while(organizationCurrent.getParent() != null)
			{
				organizationCurrent = organizationCurrent.getParent();
				organizationList.add(organizationCurrent);
			}
			ListIterator<Organization> organizationIterator = organizationList.listIterator();			
			if(organizationIterator.hasPrevious())
			{
				organizationReverseList.add(organizationIterator.previous());
			}	
		}
			
		return organizationReverseList;
	}
	
	public static List<Organization> downTrace(List<Organization> organizationRootList, Long id)
	{
		Organization organization = treeFindById(organizationRootList, id);
		List<Organization> organizationList = new LinkedList<Organization>();
		treeToList(organization, organizationList);		
		return organizationList;
	}
	
	
}
