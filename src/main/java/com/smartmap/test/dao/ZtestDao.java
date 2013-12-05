package com.smartmap.test.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.smartmap.systemManage.model.User;

@Repository
public class ZtestDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public User find(Long id) {
		return entityManager.find(User.class, id);
	}
	
	@Transactional
	public User save(User user) {
		if (user.getId() == null) {
			entityManager.persist(user);
			return user;
		} else {
			return entityManager.merge(user);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getUser() {
		String baseJQL = "SELECT p FROM User p";
		Query query = entityManager.createQuery(baseJQL);
		List<User> userList = query.getResultList();
		return userList;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getUserByUsername(String username) {
		String baseJQL = "SELECT p FROM User p WHERE p.account = :username";
		Query query = entityManager.createQuery(baseJQL);
		if(username!=null)
		{
			query.setParameter("username", username);
		}
		List<User> userList = query.getResultList();
		return userList;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getUsersByRoleId(Integer pageNo, Integer countPerPage, Long roleId) {		
		//SQL语句
		// select new com.smartmap.gis.model.systemManage.Abc(a.imsi, a.sipss, b.mdn) from A a, B b where a.imsi=b.imsi 
		//String baseJQL = "SELECT new com.smartmap.gis.model.systemManage.User(a.id, a.name, a.password, a.fullName, a.note, a.forbidden, a.departmentId, c.name, a.dutyId, d.name, a.secretDegreeId, e.name) FROM User a, UserGroup b, Department c, Duty d, SecretDegree e WHERE a.id = b.userId AND a.departmentId = c.id AND a.dutyId = d.id  AND a.secretDegreeId = e.id ";
		
		//String baseJQL = "SELECT a FROM User a WHERE a.id = b.userId ";
		String baseJQL = "SELECT a FROM User a "; // , IN(a.groups) b ";  //join a.groups b ";  // JOIN a.groups b WHERE b.id = :id
		if(roleId != null)
		{
			baseJQL = "SELECT a FROM User a WHERE a MEMBER OF (SELECT b.users FROM Groups b WHERE b.id = :groupId) ";
			baseJQL = "SELECT a FROM User a Left Outer join a.roles b WHERE b.id = :roleId ";
		}
		//baseJQL += "order by a.id";
		//查询对象
		Query query = entityManager.createQuery(baseJQL);		
		//设置参数
		if(roleId != null)
		{
			query.setParameter("roleId", roleId);
		}
		//设置分页
		query.setFirstResult(countPerPage * (pageNo - 1));
		query.setMaxResults(countPerPage);
		//执行查询
		List<User> userList = query.getResultList();
		//返回结果
		return userList;
	}
}






