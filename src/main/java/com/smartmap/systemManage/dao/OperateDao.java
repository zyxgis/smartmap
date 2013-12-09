package com.smartmap.systemManage.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.smartmap.systemManage.model.Operate;

@Repository
public class OperateDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public Operate find(Long id) {
		return entityManager.find(Operate.class, id);
	}
	
	@Transactional
	public Operate save(Operate operate) {
		if (operate.getId() == null) {
			entityManager.persist(operate);
			return operate;
		} else {
			return entityManager.merge(operate);
		}		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Operate> getAllOperates(Integer pageNo, Integer countPerPage) {
		String baseJQL = "SELECT p FROM Operate p order by p.code desc";
		Query query = entityManager.createQuery(baseJQL);
		if(pageNo != null && countPerPage != null)
		{
			query.setFirstResult(countPerPage * (pageNo - 1));
			query.setMaxResults(countPerPage);
		}
		List<Operate> operateList = query.getResultList();
		return operateList;
	}
	
}
