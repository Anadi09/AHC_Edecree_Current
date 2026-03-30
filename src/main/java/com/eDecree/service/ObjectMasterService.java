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

import com.eDecree.model.ObjectMaster;
import com.eDecree.model.ObjectTree;
import com.eDecree.model.RoleObject;

@Service
public class ObjectMasterService {

	 
	@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	public EntityManager em;
	
	

	@SuppressWarnings({ "unchecked", "finally" })
	@Transactional
	public List<ObjectTree> getRoleTree() {
		List<ObjectTree> result =  new ArrayList<ObjectTree>();
		Query query = em
				.createQuery("select ob from ObjectTree ob WHERE om_parent_id =0 ");		
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
	public List<RoleObject> getDataByRoleID(Long roleID) {
		List<RoleObject> result =  new ArrayList<RoleObject>();
		Query query = em
				.createQuery("select ro from RoleObject ro WHERE ro_role_id =:roleID ");		
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
	public ObjectMaster save(ObjectMaster s) {

		ObjectMaster master = null;
		try {
			master = em.merge(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}
	
	@Transactional
	public ObjectMaster checkExist(String om_object_name) {
		ObjectMaster objectMaster = new ObjectMaster();
		try
		{	
			Query query = em.createQuery("SELECT u FROM ObjectMaster u WHERE (om_object_name=:om_object_name)");
			query.setParameter("om_object_name", om_object_name);
			objectMaster = (ObjectMaster)query.getSingleResult();
		}catch(Exception e) {			
			e.printStackTrace();
			
		}
		finally{
			return objectMaster;
		}
	}
	
	public ObjectMaster getByLink(String om_object_link) {
		ObjectMaster objectMaster = new ObjectMaster();
		try
		{	
			Query query = em.createQuery("SELECT u FROM ObjectMaster u WHERE (om_object_link=:om_object_link)");
			query.setParameter("om_object_link", om_object_link);
			objectMaster = (ObjectMaster)query.getSingleResult();
			
			
		}catch(Exception e) {			
			e.printStackTrace();
			
		}
		finally{
			return objectMaster;
		}
	}
	
	


}
