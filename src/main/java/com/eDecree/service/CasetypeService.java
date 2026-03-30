package com.eDecree.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eDecree.model.CaseType;
import com.eDecree.model.CaseTypeLko;
import com.eDecree.model.CauseListType;



@Service
public class CasetypeService 
{
	@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	EntityManager em;
	
	@Transactional
	public CaseType getById(Long ct_id) 
	{		
		CaseType ct=new CaseType();
		try {
			String query  ="SELECT ct from CaseType ct WHERE ct.ct_id =:ct_id";
			ct=  (CaseType) em.createQuery(query).setParameter("ct_id", ct_id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ct;
	}
	
	// case types search by ct_ccms_id
	@Transactional
	public CaseType getByIdCcms(Integer ct_ccms_id) 
	{		
		CaseType ct=new CaseType();
		try {
			String query  ="SELECT ct from CaseType ct WHERE ct.ct_ccms_id =:ct_ccms_id";
			ct=  (CaseType) em.createQuery(query).setParameter("ct_ccms_id", ct_ccms_id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ct;
	}
	
	
	@Transactional
	public CaseTypeLko getByIdLko(Long ct_id) 
	{		
		CaseTypeLko ct=new CaseTypeLko();
		try {
			String query  ="SELECT ct from CaseTypeLko ct WHERE ct.ct_id =:ct_id";
			ct=  (CaseTypeLko) em.createQuery(query).setParameter("ct_id", ct_id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ct;
	}
}
