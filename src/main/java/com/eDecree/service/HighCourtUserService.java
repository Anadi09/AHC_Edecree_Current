package com.eDecree.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eDecree.model.CaseFileDetail;
import com.eDecree.model.HighCourtDesignation;
import com.eDecree.model.HighCourtSection;
import com.eDecree.model.HighCourtUser;
import com.efiling.model.EfilingCaseFileDetail;

@Service
public class HighCourtUserService {
	
	
	@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	private EntityManager em;
	
	@PersistenceContext(unitName="persistenceUnitEfiling")
	@Qualifier(value = "entityManagerFactoryEfiling")
	private EntityManager em2;
	
	@PersistenceContext(unitName="persistenceUnitLKODMS")
	@Qualifier(value = "entityManagerFactoryLKODMS")
	private EntityManager lko;
	
	
	@Transactional
	public HighCourtUser saveHighCourtUser(HighCourtUser u ) {
		
		
		
		
	
		HighCourtUser u1=null;
		
		
		try {
	u1=	em.merge(u);
	//em.persist(u1);
	
		}
		catch(Exception e) {
			System.out.println("bbbbbbbbbbbbbbbbb"+e);
			
		}
//u2 =	em.find(HighCourtUser.class,1L);
	System.out.println("data entered"+u1);
		
		return u1;
		
		
	}
	
	
	public List<HighCourtDesignation> getUserDesignation() {
		
		List<HighCourtDesignation> hcd =null;
		
		try {
		hcd =(List<HighCourtDesignation>)em.createQuery("SELECT hcd FROM HighCourtDesignation hcd").getResultList();
		}
		catch(Exception e) {
			System.out.println("exception in getUserDesignation"+e);
		}
		
		
		
		return hcd;
		
	}
	
	public List<HighCourtSection> getHighCourtSetion(){
		
		List<HighCourtSection> hcs =null;
		try {
			
			hcs =(List<HighCourtSection>)em.createQuery("SELECT hcs FROM HighCourtSection hcs").getResultList();
		}
		catch(Exception e) {
			
			System.out.println("exception in getHighCourtSetion"+e);
		}
		
		return hcs;
	}


	public HighCourtUser findSectionOfficer(Long hcu_hcs_mid, Long sectionOfficerLevel) {
	
		System.out.println("bilalkhannnnnnnnn"+hcu_hcs_mid+"------------"+sectionOfficerLevel);
		HighCourtUser u1=null;
		try {
			
			u1 =(HighCourtUser)em.createQuery("SELECT hcu FROM HighCourtUser hcu WHERE hcu.hcu_hcs_mid=:hcu_hcs_mid AND hcu.hcu_hcd_mid=:sectionOfficerLevel").
					
					setParameter("hcu_hcs_mid",hcu_hcs_mid).
					setParameter("sectionOfficerLevel",sectionOfficerLevel).getSingleResult();
		}
		catch(Exception e){
			
			System.out.println("eeeeeeeeeeeeeeeeeeeee"+e);
			return null;
			
		}
		return u1;
	}


	public HighCourtUser getHighCourtUser(Long um_id) {
		HighCourtUser u =null;
	
		try {
			u =(HighCourtUser)em.createQuery("SELECT hcu FROM HighCourtUser hcu WHERE hcu.hc_um_mid=:hc_um_mid").
					setParameter("hc_um_mid",um_id).getSingleResult();
		}
		catch(Exception e) {
			System.out.println("ex n getting user"+e);
			return  null;
		}
		return u;
	}


	public List<HighCourtUser> getUsersForApproval(Long hcu_senior_id) {
		List<HighCourtUser> hculist =null;
		try {
			hculist =(List<HighCourtUser>)em.createQuery("SELECT hcu FROM HighCourtUser hcu WHERE hcu.hcu_senior_id=:hcu_senior_id").
					setParameter("hcu_senior_id",hcu_senior_id).getResultList();
		}
		catch(Exception e) {
			System.out.println("ex n getting user"+e);
			return  null;
		}
		return hculist;
	}
	

}
