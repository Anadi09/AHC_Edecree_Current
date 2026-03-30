package com.eDecree.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eDecree.model.ApplicationNotice;
import com.eDecree.model.CaseFileDetail;
import com.eDecree.model.ManualCaveat;
import com.efiling.model.EfilingCaseFileDetail;

@Service
public class CaveatService {
	
	@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	private EntityManager em;
	
	@Transactional
	public ManualCaveat save(ManualCaveat manualCaveat) {

		ManualCaveat mc = null;
    	try {	
    		mc= em.merge(manualCaveat);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();	    	
		}
		return mc;
	}
	@Transactional
	public ManualCaveat getCaveatDetail(Integer mcav_year, Integer mcav_no) {
		ManualCaveat result=null;
		try {
			String query=" SELECT mc from ManualCaveat mc where mc.mcav_year="+mcav_year+" and mc.mcav_no='"+mcav_no+"' ";
			result= (ManualCaveat) em.createQuery(query).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<ManualCaveat> getCaveatList(ManualCaveat manualCaveat) {
		List<ManualCaveat> result = em.createQuery("SELECT c FROM ManualCaveat c where c.mcav_no=:mcav_no and c.mcav_year=:mcav_year")
				.setParameter("mcav_no", manualCaveat.getMcav_no()).setParameter("mcav_year", manualCaveat.getMcav_year())
				.getResultList();

		return result;
	}

}
