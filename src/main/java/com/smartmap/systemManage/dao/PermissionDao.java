package com.smartmap.systemManage.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.smartmap.systemManage.model.Resource;
import com.smartmap.systemManage.model.User;

@Repository
public class PermissionDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public User find(Long id) {
		return entityManager.find(User.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Resource> getResourceByRoleId(Integer pageNo, Integer countPerPage, Long roleId) {
		String baseJQL = "SELECT DISTINCT a.id, a.code, a.createTime, a.description, a.icon, a.lastUpdate, b.operateCodes, a.options, a.parentId, a.resourceName, a.sortOrder, a.target, a.url FROM SysResource a INNER JOIN SysPermission b ON a.id = b.resourceID WHERE b.roleID = :roleId";
		baseJQL += " ORDER BY a.parentId, a.sortOrder";		
		Query query = entityManager.createNativeQuery(baseJQL, Resource.class);
		//
		if(roleId != null)
		{
			query.setParameter("roleId", roleId);
		}
		//
		List<Resource> resourceList = query.getResultList();		
		return resourceList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Resource> getResourceByUserId(Integer pageNo, Integer countPerPage, Long userId) {
		String baseJQL = "SELECT DISTINCT a.id, a.code, a.createTime, a.description, a.icon, a.lastUpdate, b.operateCodes, a.options, a.parentId, a.resourceName, a.sortOrder, a.target, a.url FROM SysResource a INNER JOIN SysPermission b ON a.id = b.resourceID  INNER JOIN SysUserRole c ON b.roleID=c.roleID WHERE c.userID = :userId";
		baseJQL += " ORDER BY a.parentId, a.sortOrder, a.id";
		Query query = entityManager.createNativeQuery(baseJQL, Resource.class);
		//
		if(userId != null)
		{
			query.setParameter("userId", userId);
		}
		//
		List<Resource> resourceList = query.getResultList();
		return resourceList;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Integer revokeResources(Long roleId, List<Long> resourceIdList) {
		if(roleId == null)return 0;
		String baseJQL = "DELETE FROM SysPermission WHERE roleID = :roleID ";	
		if(resourceIdList != null && resourceIdList.size()!=0)
		{
			baseJQL = " AND resourceID IN (:resourceIDs)";	
		}
		//
		Query query = entityManager.createNativeQuery(baseJQL);
		query.setParameter("roleID", roleId);
		if(resourceIdList != null && resourceIdList.size()!=0)
		{
			query.setParameter("roleIDs", resourceIdList);
		}
		//执行查询
		Integer count = query.executeUpdate();
		//返回结果
		return count;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Integer grandResources(Long roleId, Long[] resourceIdList, Long[] operateCodesList) {
		if(roleId == null || resourceIdList == null || resourceIdList.length==0)return 0;
		String baseJQL = "INSERT INTO SysPermission(roleID, resourceID, operateCodes) VALUES(:roleID, :resourceID, :operateCodes)";	
		//
		FlushModeType flushModeType = entityManager.getFlushMode();
		entityManager.setFlushMode(FlushModeType.COMMIT); 
		//EntityTransaction entityTransaction = entityManager.getTransaction();
		//entityTransaction.begin();
		int batchSize = 100;
		int countAll = 0;
		Query query = entityManager.createNativeQuery(baseJQL);
		for(int i=0; i<resourceIdList.length; i++)
		{
			query.setParameter("roleID", roleId);
			query.setParameter("resourceID", resourceIdList[i]);
			query.setParameter("operateCodes", operateCodesList[i]);
			//执行查询
			int count = query.executeUpdate();
			countAll += count;
			if(i % batchSize == 0){
				entityManager.flush();
				entityManager.clear();
			} 
		}
		//entityTransaction.commit(); 
		entityManager.setFlushMode(flushModeType); 
		//返回结果
		return countAll;
	}
}
