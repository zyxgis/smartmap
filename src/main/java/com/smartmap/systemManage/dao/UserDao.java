package com.smartmap.systemManage.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.smartmap.systemManage.model.Employee;
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
		User userReturn = null;
		Date date = new Date(java.lang.System.currentTimeMillis());
		user.setLastUpdate(date);
		if (user.getId() == null) {
			user.setCreateTime(date);	
			entityManager.persist(user);
			userReturn = user;
		} else {
			userReturn = entityManager.merge(user);
		}
		return userReturn;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers(Integer pageNo, Integer countPerPage) {
		String baseJQL = "SELECT p FROM User p order by p.id desc";
		Query query = entityManager.createQuery(baseJQL);
		if(pageNo != null && countPerPage != null)
		{
			query.setFirstResult(countPerPage * (pageNo - 1));
			query.setMaxResults(countPerPage);
		}
		List<User> userList = query.getResultList();
		return userList;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getUsersByLoginUsername(Integer pageNo, Integer countPerPage, String loginUsername) {		
		String sql = "SELECT p FROM User p ";
		if(loginUsername != null && !loginUsername.equals(""))
		{
			sql += " WHERE p.loginUsername = :loginUsername ";
		}
		sql += " order by p.id desc";
		//
		Query query = entityManager.createQuery(sql);		
		if(loginUsername != null && !loginUsername.equals(""))
		{
			query.setParameter("loginUsername", loginUsername);
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
		String baseJQL = "SELECT * FROM SysUser a INNER JOIN SysUserRole b ON a.id = b.userID ";
		if(roleId != null && !roleId.equals(""))
		{
			baseJQL += " WHERE b.roleID = :roleId";
		}
		baseJQL += " ORDER BY a.id";
		Query query = entityManager.createNativeQuery(baseJQL, User.class);
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
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Integer revokeRoles(Long userId, List<Long> roleIdList) {		
		if(userId == null)return 0;
		String baseJQL = "DELETE FROM SysUserRole WHERE userID = :userID ";	
		if(roleIdList != null && roleIdList.size()!=0)
		{
			baseJQL = " AND roleID IN (:roleIDs)";	
		}
		//
		Query query = entityManager.createNativeQuery(baseJQL);
		query.setParameter("userID", userId);
		if(roleIdList != null && roleIdList.size()!=0)
		{
			query.setParameter("roleIDs", roleIdList);
		}
		//执行查询
		Integer count = query.executeUpdate();
		//返回结果
		return count;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Integer grandRoles(Long userId, Long[] roleIdList) {		
		if(userId == null || roleIdList == null || roleIdList.length==0)return 0;
		String baseJQL = "INSERT INTO SysUserRole(userID, roleID) VALUES(:userID, :roleID)";	
		//
		FlushModeType flushModeType = entityManager.getFlushMode();
		entityManager.setFlushMode(FlushModeType.COMMIT);
		int batchSize = 100;
		int countAll = 0;	
		Query query = entityManager.createNativeQuery(baseJQL);
		for(int i=0; i<roleIdList.length; i++)
		{
			query.setParameter("userID", userId);
			query.setParameter("roleID", roleIdList[i]);
			//执行查询
			int count = query.executeUpdate();
			countAll += count;
			if( i % batchSize  == 0 ){
				entityManager.flush();
				entityManager.clear();
			} 
		}
		entityManager.setFlushMode(flushModeType); 
		//返回结果
		return countAll;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getByOrganizationId(Long organizationId) {
		String baseJQL = "SELECT a FROM User a WHERE a.organizationId = :organizationId";
		baseJQL += " ORDER BY a.id";
		Query query = entityManager.createQuery(baseJQL);
		if(organizationId != null)
		{
			query.setParameter("organizationId", organizationId);
		}
		
		List<User> userList = query.getResultList();		
		return userList;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getByOrganizationIds(List<Long> organizationIdList) {		
		String baseJQL = "SELECT a FROM User a where a.organizationId in(:organizationIds)";
		baseJQL += " ORDER BY a.id";
		Query query = entityManager.createQuery(baseJQL);
		if(organizationIdList != null)
		{
			query.setParameter("organizationIds", organizationIdList);
		}		
		List<User> userList = query.getResultList();		
		return userList;
	}
}
