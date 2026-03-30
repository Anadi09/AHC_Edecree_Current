package com.efiling.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.efiling.model.EfilingCaseFileDetail;

@Service
public class AddCaseEfilingService {
	
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
	public EfilingCaseFileDetail getCaseFromEfiling(Long fd_id) {
    	System.out.println("dataaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+fd_id);
    	
    	EfilingCaseFileDetail cfd=null;
    	 try {
			cfd=emEfling.find(EfilingCaseFileDetail.class, fd_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return cfd;
	}

}
