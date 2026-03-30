package com.efiling.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eDecree.model.AllowEfiling;
import com.efiling.model.EfilingCaseFileDetail;

@Service
public class AllowToEfilingService {
	
	@PersistenceContext(unitName="persistenceUnitEfiling")
	@Qualifier(value = "entityManagerFactoryEfiling")
	private EntityManager emEfling;
	
    @Transactional("transactionManagerEfiling")
	public EfilingCaseFileDetail saveEfiling(EfilingCaseFileDetail cfdEfling) {
    	
    	EfilingCaseFileDetail cfd=null;
    	 try {
			cfd=emEfling.merge(cfdEfling);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return cfd;
	}
    
    @Transactional("transactionManagerEfiling")
	public AllowEfiling saveAllowCase(AllowEfiling  a ) {
    	
    	AllowEfiling  ae=null;
    	 try {
			ae=emEfling.merge(a);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return ae;
	}
    
    @Transactional("transactionManagerEfiling")
	public Integer getAeCode(Integer ae_code) {

		Integer result=null;
	    Query query=null;
	    try
	    {
			query = emEfling.createQuery("SELECT a.ae_code from AllowEfiling a where a.ae_code=:ae_code").setParameter("ae_code", ae_code);
			result=(Integer)query.getSingleResult();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
		
		return result;
	}


}
