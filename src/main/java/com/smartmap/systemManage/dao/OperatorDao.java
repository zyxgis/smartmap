package com.smartmap.systemManage.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.smartmap.systemManage.model.Operate;

@Repository
public class OperatorDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public Operate find(Long id) {
		return entityManager.find(Operate.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Operate> findByIdArray(List<Long> operatorIdList) {
		if(operatorIdList == null)return null;
		if(operatorIdList.size()==0)return new ArrayList<Operate>();
		String baseJQL = "SELECT a FROM Operator a where a.id in(:ids)";
		Query query = entityManager.createQuery(baseJQL);
		query.setParameter("ids", operatorIdList);
		//执行查询
		List<Operate> operatorList = query.getResultList();
		//返回结果
		return operatorList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Operate> getAllOperators() {
		String baseJQL = "SELECT a FROM Operator a";
		baseJQL += " ORDER BY a.resource.id, a.function.id";
		Query query = entityManager.createQuery(baseJQL);
		//
		List<Operate> operatorList = query.getResultList();
		return operatorList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Operate> getOperatorByResourceId(Long resourceId) {
		String baseJQL = "SELECT a FROM Operator a WHERE a.resource.id = :resourceId";
		baseJQL += " ORDER BY a.resource.id, a.function.id";
		Query query = entityManager.createQuery(baseJQL);
		if(resourceId != null)
		{
			query.setParameter("resourceId", resourceId);
		}
		//
		List<Operate> operatorList = query.getResultList();		
		return operatorList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Operate> getOperatorByFunctionId(Long functionId) {
		String baseJQL = "SELECT a FROM Operator a WHERE a.function.id = :functionId";
		baseJQL += " ORDER BY a.resource.id, a.function.id";
		Query query = entityManager.createQuery(baseJQL);
		if(functionId != null)
		{
			query.setParameter("functionId", functionId);
		}
		//
		List<Operate> operatorList = query.getResultList();		
		return operatorList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Operate> getOperatorByRoleId(Integer pageNo, Integer countPerPage, Long roleId) {
		String baseJQL = "SELECT a FROM Operator a Left Outer join a.roles b WHERE b.id = :roleId";
		baseJQL += " ORDER BY a.resource.id, a.function.id";
		Query query = entityManager.createQuery(baseJQL);
		if(roleId != null)
		{
			query.setParameter("roleId", roleId);
		}
		//设置分页
		if(pageNo != null && countPerPage != null)
		{
			query.setFirstResult(countPerPage * (pageNo - 1));
			query.setMaxResults(countPerPage);
		}
		//执行查询		
		List<Operate> operatorList = query.getResultList();		
		return operatorList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Operate> getOperatorByUserId(Integer pageNo, Integer countPerPage, Long userId) {
		String baseJQL = "SELECT a FROM Operator a Left Outer join a.roles b Left Outer join b.users c WHERE c.id = :userId";
		baseJQL += " ORDER BY a.resource.id, a.function.id";
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
		List<Operate> operatorList = query.getResultList();		
		return operatorList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Operate> getOperatorOfResourceByUserId(Integer pageNo, Integer countPerPage, Long userId, Long resourceId) {
		String baseJQL = "SELECT a FROM Operator a Left Outer join a.roles b Left Outer join b.users c WHERE c.id = :userId AND a.resource.id = :resourceId";
		baseJQL += " ORDER BY a.resource.id, a.function.id";
		Query query = entityManager.createQuery(baseJQL);
		if(userId != null)
		{
			query.setParameter("userId", userId);
		}
		if(resourceId != null)
		{
			query.setParameter("resourceId", resourceId);
		}
		//设置分页
		if(pageNo != null && countPerPage != null)
		{
			query.setFirstResult(countPerPage * (pageNo - 1));
			query.setMaxResults(countPerPage);
		}
		//执行查询		
		List<Operate> operatorList = query.getResultList();		
		return operatorList;
	}
}
