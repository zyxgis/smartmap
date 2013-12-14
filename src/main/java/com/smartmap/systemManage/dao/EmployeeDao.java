package com.smartmap.systemManage.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.smartmap.systemManage.model.Employee;
import com.smartmap.systemManage.model.Resource;

@Repository
public class EmployeeDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public Employee find(Long id) {
		return entityManager.find(Employee.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> findByIdArray(List<Long> employeeIdList) {
		if(employeeIdList == null)return null;
		if(employeeIdList.size()==0)return new ArrayList<Employee>();
		String baseJQL = "SELECT a FROM Employee a where a.id in(:ids)";
		Query query = entityManager.createQuery(baseJQL);
		query.setParameter("ids", employeeIdList);
		//执行查询
		List<Employee> employeeList = query.getResultList();
		//返回结果
		return employeeList;
	}
	
	@Transactional
	public Employee save(Employee employee) {
		Employee employeeReturn = null;
		Date date = new Date(java.lang.System.currentTimeMillis());
		employee.setLastUpdate(date);
		//entityManager.getTransaction().begin();
		try {
			if (employee.getId() == null) {				
				employee.setCreateTime(date);				
				entityManager.persist(employee);
				employeeReturn = employee;				
			} else {
				employeeReturn = entityManager.merge(employee);
			}
			//entityManager.getTransaction().commit();
			return employee;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> getAll() {
		String baseJQL = "SELECT a FROM Employee a";
		baseJQL += " ORDER BY a.id";
		Query query = entityManager.createQuery(baseJQL);
		//
		List<Employee> employeeList = query.getResultList();
		return employeeList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> getByOrganizationId(Long organizationId) {
		String baseJQL = "SELECT a FROM Employee a WHERE a.organizationId = :organizationId";
		baseJQL += " ORDER BY a.id";
		Query query = entityManager.createQuery(baseJQL);
		if(organizationId != null)
		{
			query.setParameter("organizationId", organizationId);
		}
		
		List<Employee> employeeList = query.getResultList();		
		return employeeList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> getByOrganizationIds(List<Long> organizationIdList) {		
		String baseJQL = "SELECT a FROM Employee a where a.organizationId in(:organizationIds)";
		baseJQL += " ORDER BY a.id";
		Query query = entityManager.createQuery(baseJQL);
		if(organizationIdList != null)
		{
			query.setParameter("organizationIds", organizationIdList);
		}		
		List<Employee> employeeList = query.getResultList();		
		return employeeList;
	}
}
