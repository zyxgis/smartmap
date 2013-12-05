package com.smartmap.gis.dao.evaluate;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

import com.smartmap.gis.model.evaluate.Pjjg;

@Repository
public class PjjgDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Pjjg find(Long id) {
		return entityManager.find(Pjjg.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Pjjg> getPjjg() {
		return entityManager.createQuery("SELECT p FROM Pjjg p ORDER BY p.jcdmbm").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Pjjg> getPjjgByJcsj(String jcsj) {
		Query query = entityManager.createQuery("SELECT t FROM Pjjg t WHERE t.jcsj = :jcsj ORDER BY t.jcdmbm");
		query.setParameter("jcsj", jcsj);
		List<Pjjg> pjjgList = query.getResultList();
		return pjjgList;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
