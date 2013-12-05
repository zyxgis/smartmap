package com.smartmap.systemManage.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.smartmap.systemManage.model.User;

@Repository
public class UserDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public User find(Long id) {
		return entityManager.find(User.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findByIdArray(List<Long> userIdList) {
		if(userIdList == null)return null;
		if(userIdList.size()==0)return new ArrayList<User>();
		String baseJQL = "SELECT a FROM User a where a.id in(:ids)";		
		Query query = entityManager.createQuery(baseJQL);
		query.setParameter("ids", userIdList);
		//执行查询
		List<User> userList = query.getResultList();
		//返回结果
		return userList;
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
	public List<User> getAllUsers(Integer pageNo, Integer countPerPage) {
		Query query = entityManager.createQuery("SELECT p FROM User p");
		if(pageNo != null && countPerPage != null)
		{
			query.setFirstResult(countPerPage * (pageNo - 1));
			query.setMaxResults(countPerPage);
		}
		List<User> userList = query.getResultList();
		return userList;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getUsersByUsername(Integer pageNo, Integer countPerPage, String username) {		
		String sql = "SELECT p FROM User p ";
		if(username != null && !username.equals(""))
		{
			sql += "WHERE p.account = :username ";
		}
		Query query = entityManager.createQuery(sql);		
		if(username != null && !username.equals(""))
		{
			query.setParameter("username", username);
		}
		//设置分页
		if(pageNo != null && countPerPage != null)
		{
			query.setFirstResult(countPerPage * (pageNo - 1));
			query.setMaxResults(countPerPage);
		}
		//执行查询
		List<User> userList = query.getResultList();
		return userList;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getUsersByRoleId(Integer pageNo, Integer countPerPage, Long roleId) {
		String baseJQL = "SELECT a FROM User a Left Outer join a.roles b ";
		if(roleId != null && !roleId.equals(""))
		{
			baseJQL += " WHERE b.id = :roleId";
		}
		baseJQL += " ORDER BY a.id";
		Query query = entityManager.createQuery(baseJQL);
		if(roleId != null && !roleId.equals(""))
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
		List<User> userList = query.getResultList();
		//返回结果
		return userList;
	}
	
	
	

}
