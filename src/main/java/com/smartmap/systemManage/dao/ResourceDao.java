package com.smartmap.systemManage.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.smartmap.systemManage.model.Resource;

@Repository
public class ResourceDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public Resource find(Long id) {
		return entityManager.find(Resource.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Resource> findByIdArray(List<Long> resourceIdList) {
		if(resourceIdList == null)return null;
		if(resourceIdList.size()==0)return new ArrayList<Resource>();
		String baseJQL = "SELECT a FROM Resource a where a.id in(:ids)";
		Query query = entityManager.createQuery(baseJQL);
		query.setParameter("ids", resourceIdList);
		//执行查询
		List<Resource> resourceList = query.getResultList();
		//返回结果
		return resourceList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Resource> getAllResources() {
		String baseJQL = "SELECT a FROM Resource a";
		baseJQL += " ORDER BY a.id";
		Query query = entityManager.createQuery(baseJQL);
		//
		List<Resource> resourceList = query.getResultList();
		return resourceList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Resource> getResourceByParentResourceId(Long parentId) {
		String baseJQL = "SELECT a FROM Resource a WHERE a.parent.id = :parentId";
		baseJQL += " ORDER BY a.sortOrder";
		Query query = entityManager.createQuery(baseJQL);
		if(parentId != null)
		{
			query.setParameter("parentId", parentId);
		}
		
		List<Resource> resourceList = query.getResultList();		
		return resourceList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Resource> getResourceByUserId(Integer pageNo, Integer countPerPage, Long userId) {
		String baseJQL = "SELECT DISTINCT a FROM Resource a RIGHT OUTER JOIN a.operators b LEFT OUTER JOIN b.roles c Left Outer join c.users d WHERE d.id = :userId";
		baseJQL += " ORDER BY a.parent, a.sortOrder, a.id";
		Query query = entityManager.createQuery(baseJQL);
		//
		if(userId != null)
		{
			query.setParameter("userId", userId);
		}
		//
		List<Resource> resourceList = query.getResultList();		
		return resourceList;
	}
}
