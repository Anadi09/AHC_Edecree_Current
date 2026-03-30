package com.eDecree.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eDecree.model.SearchCriteria;
import com.eDecree.model.SearchQuery;

@Service
public class SearchQueryService {
	
	@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	private EntityManager em;
	 
	@Transactional
	public SearchQuery getSearchQueryById(Long id) {
		
		SearchQuery r= new SearchQuery();
		try {
			Query query  =  em.createQuery("SELECT r from SearchQuery r WHERE r.id =:id");
			query.setParameter("id", id);
			r= (SearchQuery) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
	@Transactional
	public List<SearchQuery> getAll() {
		List<SearchQuery> result = em.createQuery("SELECT r FROM SearchQuery r").getResultList();
		return result;
	}
	
	@Transactional
	public SearchQuery save(SearchQuery searchquery) {
		// TODO Auto-generated method stub

		SearchQuery master = null;
    	try {	
    		master= em.merge(searchquery);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return master;
	}
	
	@Transactional
	public void deleteQuery(Long id) {
		SearchQuery r2 = em.find(SearchQuery.class, id);		   
		  em.remove(r2);
	}
	
	@Transactional
	public void deleteCriteria(Long id) {
		SearchCriteria r2 = em.find(SearchCriteria.class, id);		   
		  em.remove(r2);
	}
	
	@Transactional
	public SearchCriteria  saveCriteria(SearchCriteria searchcriteria) {
		// TODO Auto-generated method stub
		SearchCriteria master = null;
    	try {	
    		master= em.merge(searchcriteria);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return master;
	}
	

}
