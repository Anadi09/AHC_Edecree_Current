package com.eDecree.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eDecree.model.ApplicationWithPetition;
import com.eDecree.model.CaseType;
import com.eDecree.model.CourtMaster;
import com.eDecree.model.Sub_Benches;

@Service
public class BenchService {
	
	
	@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	EntityManager em;
	
	@Transactional
	public List<CourtMaster> getAllCourts() 
	{		
		List<CourtMaster> cm =null;
		try {
			String query  ="SELECT ct from CourtMaster ct order by ct.cm_value";
			cm=   em.createQuery(query).getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cm;
	}
	
	@Transactional
	public CourtMaster updateCourt(CourtMaster cm) {

		CourtMaster cmnew = null;
    	try {	
    		cmnew= em.merge(cm);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();	    	
		}
		return cmnew;
	}
	
	
	@Transactional
	public Sub_Benches addBenches(Sub_Benches cm) {

		Sub_Benches cmnew = null;
    	try {	
    		cmnew= em.merge(cm);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();	    	
		}
		return cmnew;
	}

	@Transactional
	public CourtMaster getCourtByBenchId(Integer cm_bench_id) {
		CourtMaster cm =null;
		try {
			String query  ="SELECT ct from CourtMaster ct where ct.cm_bench_id =:cm_bench_id";
			cm=   (CourtMaster) em.createQuery(query).setParameter("cm_bench_id",cm_bench_id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cm;
	}
	
	@Transactional
	public Sub_Benches getBenches(Integer cm_bench_id) {
		Sub_Benches cm =null;
		try {
			String query  ="SELECT ct from Sub_Benches ct where ct.sb_bench_id =:cm_bench_id and ct.sb_rec_status=1";
			cm=   (Sub_Benches) em.createQuery(query).setParameter("cm_bench_id",cm_bench_id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cm;
	}
	
	
	  @Transactional
		public Sub_Benches deleteSubBeches(Long id) {
		  Sub_Benches oldSubBenches=null;
		  Sub_Benches subBenches=null;
		  oldSubBenches=em.find(Sub_Benches.class, id);
		  oldSubBenches.setSb_rec_status(2);
		    subBenches =em.merge(oldSubBenches);
			
			return subBenches;
		}
	
	
	@Transactional
	public CourtMaster getCourtByBenchName(String  cm_name) {
		CourtMaster cm =null;
		try {
			String query  ="SELECT ct from CourtMaster ct where ct.cm_name =:cm_name";
			cm=   (CourtMaster) em.createQuery(query).setParameter("cm_name",cm_name).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cm;
	}

}
