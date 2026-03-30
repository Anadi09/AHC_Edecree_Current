package com.eDecree.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eDecree.model.UserRole;

@Service
public class UserRoleService {

	@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional
	public List<UserRole> getAll() {
		List<UserRole> result = em.createQuery("Select ur from UserRole ur").getResultList();
		return result;
	}

	@Transactional
	public UserRole getByUserId(Long userId) {
		UserRole result = new UserRole();
		try {
			Query query = em
					.createQuery("Select ur from UserRole ur WHERE ur.ur_um_mid=:userId");
			query.setParameter("userId", userId);			
			result = (UserRole) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new EntityNotFoundException("Entity does not exist.");
		} finally {
			return result;
		}
		
	}
	
	@Transactional
	public UserRole getByUserRole(String role) {
		UserRole result = new UserRole();
		try {
			result= (UserRole) em.createQuery("select ur from UserRole ur where ur.ur_role_id In(Select lk.lk_id from Lookup lk where lk.lk_longname='"+role+"')").setMaxResults(1).getSingleResult();			
		} catch (Exception e) {
			e.printStackTrace();
			throw new EntityNotFoundException("Entity does not exist.");
		} finally {
			return result;
		}
		
	}
	
	public UserRole getUserrole(Long id) {
		UserRole r = em.find(UserRole.class, id);
		return r;
	}

	@Transactional
	public void update(UserRole u) {
		UserRole r1 = em.merge(u);
	}

	@Transactional
	public void add(UserRole u) {
		try {
			em.persist(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void delete(Long id) {
		UserRole r2 = em.find(UserRole.class, id);
		em.remove(r2);
	}

	@Transactional
	public UserRole save(UserRole s) {

		UserRole master = null;
		try {
			master = em.merge(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}
	
	@Transactional
	public List<Long> getSytemAdminUsers() {
		List<Long> usersids = em.createQuery("select ur.ur_um_mid from UserRole ur where ur.ur_role_id In(Select lk.lk_id from Lookup lk where lk.lk_longname='DMSAdmin')").getResultList();
		return usersids;
	}
	
}
