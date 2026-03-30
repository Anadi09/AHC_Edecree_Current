package com.eDecree.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eDecree.model.CaseNominated;

@Service
public class CaseNominatedService {
	
	@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	private EntityManager em;
	
	
	@Transactional
    public CaseNominated saveCN(CaseNominated caseNominated) {
    
		CaseNominated result = null;
    	try {	
    		result= em.merge(caseNominated);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return result;
    }
	
	@Transactional
	public CaseNominated getCaseNominatedByfdmid (Long cnfdmid) 
	{
		CaseNominated result=null;
	    Query query=null;
	    try {
	    	
	    	query = em.createQuery("SELECT cn from CaseNominated cn where cn.cn_fd_mid=:cn_fd_mid").setParameter("cn_fd_mid", cnfdmid);
			result=(CaseNominated) query.getSingleResult();
			
		} catch (Exception e) {

		}	
		return result;
	
	}
	
	@Transactional
	public CaseNominated getNominatedReport(Long id) {

		CaseNominated result=null;
	    Query query=null;
		query = em.createQuery("SELECT cn from CaseNominated cn where cn.cn_fd_mid=:id").setParameter("id", id);
		try {
			result=(CaseNominated) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
