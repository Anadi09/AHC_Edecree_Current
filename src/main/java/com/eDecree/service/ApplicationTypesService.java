package com.eDecree.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class ApplicationTypesService {
	
	
	@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	private EntityManager em;
	
	
	
	
	@Transactional
	public Integer getApplicationTypes(Integer id) {

		Integer result=null;
	    Query query=null;
	    try
	    {
			query = em.createQuery("SELECT a.at_id from ApplicationTypes a where a.at_ald_lko_mapping=:id").setParameter("id", id);
			result=(Integer)query.getSingleResult();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
		
		return result;
	}

}
