package com.smartmap.gis.dao.evolution;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.smartmap.gis.model.evolution.Mnjg;


@Repository
public class MnjgDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	public Mnjg find(Long id) {
		return entityManager.find(Mnjg.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Mnjg> getMnjg() {
		return entityManager.createQuery("select p from Mnjg p").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getMnjgTimeBySqId(String sqId) {		
		Query query = entityManager.createQuery("SELECT DISTINCT t.sj FROM yj_mnjg t WHERE t.sqId = :sqId order by t.sj");
		query.setParameter("sqId", sqId);
		List<String> mnjgList = query.getResultList();
		return mnjgList;
	}
	
	@SuppressWarnings("unchecked")
	public String getMnjgMinTimeBySqId(String sqId) {		
		Query query = entityManager.createQuery("SELECT MIN(sj) FROM Mnjg t WHERE t.sqId = :sqId");
		query.setParameter("sqId", sqId);
		Object result = query.getSingleResult(); 
		String minSj = result.toString();
		return minSj;
	}
	
	@SuppressWarnings("unchecked")
	public String getMnjgMaxTimeBySqId(String sqId) {		
		Query query = entityManager.createQuery("SELECT MAX(sj) FROM Mnjg t WHERE t.sqId = :sqId");
		query.setParameter("sqId", sqId);
		Object result = query.getSingleResult(); 
		String minSj = result.toString();
		return minSj;
	}
	
	@SuppressWarnings("unchecked")
	public List<Mnjg> getMnjgBySqIdAndTime(String sqId, String sj) {		
		Query query = entityManager.createQuery("SELECT t FROM Mnjg t WHERE t.sqId = :sqId AND t.sj = :sj ORDER BY t.dmlcz ASC");
		query.setParameter("sqId", sqId);
		query.setParameter("sj", sj);
		List<Mnjg> mnjgList = query.getResultList();		
		return mnjgList;
	}
	
	@Transactional
	public Mnjg save(Mnjg mnjg) {
		if (mnjg.getId() == null) {
			entityManager.persist(mnjg);
			return mnjg;
		} else {
			return entityManager.merge(mnjg);
		}		
	}	
}
