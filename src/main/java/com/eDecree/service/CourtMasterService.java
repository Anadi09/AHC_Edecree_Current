package com.eDecree.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eDecree.model.CourtMaster;
import com.eDecree.model.CourtUserMapping;
import com.eDecree.model.Judge;



@Service
public class CourtMasterService 
{
	@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	EntityManager em;
	
	@Transactional
    public CourtMaster save(CourtMaster c) {
    
		CourtMaster courtMaster = null;
    	try {	
    		courtMaster= em.merge(c);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return courtMaster;
    }
	
	@Transactional
    public CourtUserMapping saveMapping(CourtUserMapping c) {
    
		CourtUserMapping cumapping = null;
    	try {	
    		cumapping= em.merge(c);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return cumapping;
    }
	
	@Transactional
	public CourtMaster getCourt(Integer Id) 
	{		
		CourtMaster cm=new CourtMaster();
		try {
			Query query  =  em.createQuery("SELECT c from CourtMaster c WHERE c.id =:id");
			query.setParameter("id", Id);
			cm= (CourtMaster) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cm;
	}
	
	
	@Transactional
	public CourtMaster getCourtMaster(Integer Id) 
	{		
		CourtMaster cm=null;
		try {
			Query query  =  em.createQuery("SELECT c from CourtMaster c WHERE c.cm_id =:id");
			query.setParameter("id", Id);
			cm= (CourtMaster) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cm;
	}
	@Transactional
	public List<CourtMaster> getCourtLists() {
		List<CourtMaster> result = em.createQuery("SELECT c FROM CourtMaster c").getResultList();
		return result;
	}
	
	@Transactional
	public CourtUserMapping getCourtMapping(Long userId) {
		CourtUserMapping cm=new CourtUserMapping();
		try {
			Query query  =  em.createQuery("SELECT c from CourtUserMapping c WHERE c.cum_user_mid =:userId");
			query.setParameter("userId", userId);
			cm= (CourtUserMapping) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cm;
	}
	
	@Transactional
	public CourtUserMapping getCourtMappingForUser(Long userId) {
		CourtUserMapping cm=null;
		try {
			Query query  =  em.createQuery("SELECT c from CourtUserMapping c WHERE c.cum_user_mid =:userId");
			query.setParameter("userId", userId);
			cm= (CourtUserMapping) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cm;
	}
	@Transactional
	public CourtUserMapping getCourtMappingByUserId(Long userId) {
		CourtUserMapping cm=new CourtUserMapping();
		try {
			Query query  =  em.createQuery("SELECT c from CourtUserMapping c WHERE c.cum_user_mid =:userId");
			query.setParameter("userId", userId);
			cm= (CourtUserMapping) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cm;
	}
	
	@Transactional
	public Judge getJudgeDetailsByCourtMapping(Integer cmid) {
		Judge jg=null;
		try {
			Query query  =  em.createQuery("select j from Judge j where j.jg_id  = (select cum_jg_mid  from CourtUserMapping where cum_court_mid =:courtId)");
			query.setParameter("courtId", cmid);
			jg= (Judge) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jg;
	}
	
	@Transactional
	public Judge getJudgeByCourtMappingUmid(Long umid) {
		Judge jg=null;
		try {
			Query query  =  em.createQuery("select j from Judge j where j.jg_id  = (select cum_jg_mid  from CourtUserMapping where cum_user_mid =:userId)");
			query.setParameter("userId", umid);
			jg= (Judge) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jg;
	}
	

}
