package com.eDecree.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eDecree.model.RoleObject;
import com.eDecree.model.Tree;

@Service
public class RoleMappingService {
 
	@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	public EntityManager em;
	
	

	@SuppressWarnings({ "unchecked", "finally" })
	@Transactional
	public List<Tree> getRoleTree() {
		List<Tree> result =  new ArrayList<Tree>();
		Query query = em
				.createQuery("select ob  from Tree ob WHERE ob.parentid =0  AND ob.om_rec_status=1");		
		try {
			result = query.getResultList();
			System.out.println("role List"+result.size());
		} catch (Exception e) {
			throw new EntityNotFoundException("Entity does not exist.");
		} finally {
			return result;
		}
		
	}
	
	@Transactional
	public List<Long> getDataByRoleID(Long roleID) {
		List<Long> result =  new ArrayList<Long>();
		Query query = em
				.createQuery("select ro.ro_om_mid from RoleObject ro WHERE ro.ro_role_id =:roleID AND ro.ro_rec_status=1");		
		try {
			result = query.setParameter("roleID", roleID).getResultList();
			System.out.println("role id List"+result.size());
		} catch (Exception e) {
			throw new EntityNotFoundException("Entity does not exist.");
		} finally {
			return result;
		}
		
	}
	
	
	@Transactional
	public RoleObject save(RoleObject s) {

		RoleObject master = null;
		try {
			master = em.merge(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}
	
	
	@Transactional
	public List<RoleObject> updateRecStatus(Long roleID) {
		List<RoleObject> result =  new ArrayList<RoleObject>();
		Query query = em
				.createQuery("UPDATE RoleObject SET  ro_rec_status=2  WHERE ro_role_id =:roleID");
		int updateCount = query.setParameter("roleID", roleID).executeUpdate();
		System.out.println("updateCount "+updateCount);
		return result;
	}
/*	
	@Transactional
	public List<RoleObject> CheckRecStatus(Long roleID,Long om_mid) {
		List<RoleObject> result =  new ArrayList<RoleObject>();
		Query query = em
				.createQuery("SELECT r FROM RoleObject r  WHERE  r.ro_role_id =:roleID  AND  r.ro_om_mid="+om_mid+" ");
		result=query.setParameter("roleID", roleID).getResultList();
		System.out.println("get checked status "+result);
		return result;
	}*/

	@Transactional
	public RoleObject getByRoleAndObject(Long role_id, Long ro_om_mid) {
	
		RoleObject roleObject=  new RoleObject();
		
		try {
			Query query = em.createQuery("SELECT r FROM RoleObject r  WHERE  r.ro_role_id =:roleID  AND  r.ro_om_mid=:ro_om_mid and r.ro_rec_status = 1");
			roleObject=(RoleObject) query.setParameter("roleID", role_id).setParameter("ro_om_mid", ro_om_mid).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("No record found "+e);
		}
		return roleObject;
	}
	
}
