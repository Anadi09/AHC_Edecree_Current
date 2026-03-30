package com.eDecree.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eDecree.model.CaseFileDetail;

@Service
public class SearchFileService 
{
	@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	private EntityManager em;
	
	@Transactional
	public List<CaseFileDetail> getCaseFile(Long caseyear,Long casetype,String caseno) {
		List<CaseFileDetail> result=null;
	    String query=" SELECT cfd from CaseFileDetail cfd where cfd.fd_case_year="+caseyear+" and cfd.fd_case_type="+casetype+" and cfd.fd_case_no='"+caseno+"' ";
		result= em.createQuery(query).getResultList();
		
		return result;
	}
	
	@Transactional
	public CaseFileDetail getCaseFileDetails(Long caseyear,Long casetype,String caseno) {
		
		CaseFileDetail result=null;
		try 
		{
			 String query=" SELECT cfd from CaseFileDetail cfd where cfd.fd_case_year="+caseyear+" and cfd.fd_case_type="+casetype+" and cfd.fd_case_no='"+caseno+"' ";
			result= (CaseFileDetail) em.createQuery(query).getSingleResult();
				
		} catch (Exception e) {
			// TODO: handle exception
		}
	   
		
		return result;
	}

}
