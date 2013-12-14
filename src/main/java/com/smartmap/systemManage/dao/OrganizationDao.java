package com.smartmap.systemManage.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.smartmap.systemManage.model.Organization;
import com.smartmap.systemManage.model.Resource;

@Repository
public class OrganizationDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public Organization find(Long id) {
		return entityManager.find(Organization.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Organization> findByIdArray(List<Long> organizationIdList) {
		if(organizationIdList == null)return null;
		if(organizationIdList.size()==0)return new ArrayList<Organization>();
		String baseJQL = "SELECT a FROM Organization a where a.id in(:ids)";
		Query query = entityManager.createQuery(baseJQL);
		query.setParameter("ids", organizationIdList);
		//执行查询
		List<Organization> organizationList = query.getResultList();
		//返回结果
		return organizationList;
	}
	
	@Transactional
	public Organization save(Organization organization) {
		Organization organizationReturn = null;
		Date date = new Date(java.lang.System.currentTimeMillis());
		organization.setLastUpdate(date);
		//entityManager.getTransaction().begin();
		try {
			if (organization.getId() == null) {				
				organization.setCreateTime(date);				
				entityManager.persist(organization);
				organizationReturn = organization;				
			} else {
				organizationReturn = entityManager.merge(organization);
			}
			//entityManager.getTransaction().commit();
			return organization;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Organization> getAllOrganizations() {
		String baseJQL = "SELECT a FROM Organization a";
		baseJQL += " ORDER BY a.id";
		Query query = entityManager.createQuery(baseJQL);
		//
		List<Organization> organizationList = query.getResultList();
		return organizationList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Organization> getOrganizationByParentId(Long parentId) {
		String baseJQL = "SELECT a FROM Organization a WHERE a.parentId = :parentId";
		baseJQL += " ORDER BY a.id";
		Query query = entityManager.createQuery(baseJQL);
		if(parentId != null)
		{
			query.setParameter("parentId", parentId);
		}
		
		List<Organization> organizationList = query.getResultList();		
		return organizationList;
	}
	
	
}
