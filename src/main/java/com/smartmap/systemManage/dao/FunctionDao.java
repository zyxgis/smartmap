package com.smartmap.systemManage.dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.smartmap.systemManage.model.Function;
import com.smartmap.systemManage.model.Operator;

@Repository
public class FunctionDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public Function find(Long id) {
		return entityManager.find(Function.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Function> findByIdArray(List<Long> functionIdList) {
		if(functionIdList == null)return null;
		if(functionIdList.size()==0)return new ArrayList<Function>();
		String baseJQL = "SELECT a FROM Function a where a.id in(:ids)";
		Query query = entityManager.createQuery(baseJQL);
		query.setParameter("ids", functionIdList);
		//执行查询
		List<Function> functionList = query.getResultList();
		//返回结果
		return functionList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Function> getAllFunction() {
		String baseJQL = "SELECT a FROM Function a ";
		baseJQL += " ORDER BY a.id";		
		Query query = entityManager.createQuery(baseJQL);		
		List<Function> functionList = query.getResultList();
		return functionList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Function> getFunctionByName(String name) {
		String baseJQL = "SELECT a FROM Function a WHERE a.name = :name";
		baseJQL += " ORDER BY a.id";		
		Query query = entityManager.createQuery(baseJQL);
		//
		if(name != null)
		{
			query.setParameter("name", name);
		}
		//
		List<Function> functionList = query.getResultList();
		return functionList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Function> getFunctionOfResourceByUserId(Integer pageNo, Integer countPerPage, Long userId, Long resourceId) {
		String baseJQL = "SELECT DISTINCT a FROM Operator a Left Outer join a.roles b Left Outer join b.users c WHERE c.id = :userId AND a.resource.id = :resourceId";
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
		List<Operator> operatorList = query.getResultList();
		List<Function> functionList = new LinkedList<Function>();
		for(int i=0; i<operatorList.size(); i++)
		{
			Function function = operatorList.get(0).getFunction();
			functionList.add(function);
		}		
		return functionList;
	}
}