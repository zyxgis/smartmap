package com.smartmap.systemManage.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.smartmap.systemManage.model.Employee;
import com.smartmap.systemManage.model.Role;
import com.smartmap.systemManage.model.User;


@Repository
public class RoleDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public Role find(Long id) {
		return entityManager.find(Role.class, id);		
	}
	
	@SuppressWarnings("unchecked")
	public List<Role> findByIdArray(List<Long> roleIdList) {
		if(roleIdList == null)return null;
		if(roleIdList.size()==0)return new ArrayList<Role>();
		String baseJQL = "SELECT a FROM Role a where a.id in(:ids)";		
		Query query = entityManager.createQuery(baseJQL);
		query.setParameter("ids", roleIdList);
		//执行查询
		List<Role> roleList = query.getResultList();
		//返回结果
		return roleList;
	}
	
	@Transactional
	public Role save(Role role) {
		if (role.getId() == null) {
			entityManager.persist(role);
			return role;
		} else {
			return entityManager.merge(role);
		}		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Role> getAllRoles(Integer pageNo, Integer countPerPage) {
		Query query = entityManager.createQuery("SELECT p FROM Role p");
		if(pageNo != null && countPerPage != null)
		{
			query.setFirstResult(countPerPage * (pageNo - 1));
			query.setMaxResults(countPerPage);
		}
		List<Role> roleList = query.getResultList();
		return roleList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Role> getRolesByRoleName(Integer pageNo, Integer countPerPage, String roleName) {
		String baseJQL = "SELECT p FROM Role p ";
		if(roleName != null)
		{
			baseJQL += " WHERE p.name = :roleName ";
		}
		Query query = entityManager.createQuery(baseJQL);
		if(roleName != null)
		{
			query.setParameter("roleName", roleName);
		}
		//设置分页
		if(pageNo != null && countPerPage != null)
		{
			query.setFirstResult(countPerPage * (pageNo - 1));
			query.setMaxResults(countPerPage);
		}
		List<Role> roleList = query.getResultList();
		return roleList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Role> getRolesByUserId(Integer pageNo, Integer countPerPage, Long userId) {
		String baseJQL = "SELECT a FROM Role a Left Outer join a.users b ";
		if(userId != null)
		{
			baseJQL += " WHERE b.id = :userId ";
		}
		baseJQL += " ORDER BY a.id";
		Query query = entityManager.createQuery(baseJQL);
		if(userId != null)
		{
			query.setParameter("userId", userId);
		}
		//设置分页
		if(pageNo != null && countPerPage != null)
		{
			query.setFirstResult(countPerPage * (pageNo - 1));
			query.setMaxResults(countPerPage);
		}
		//执行查询
		List<Role> roleList = query.getResultList();
		//返回结果
		return roleList;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Role> getByOrganizationId(Long organizationId) {
		String baseJQL = "SELECT a FROM Role a WHERE a.organizationId = :organizationId";
		baseJQL += " ORDER BY a.id";
		Query query = entityManager.createQuery(baseJQL);
		if(organizationId != null)
		{
			query.setParameter("organizationId", organizationId);
		}
		
		List<Role> roleList = query.getResultList();		
		return roleList;
	}
}
