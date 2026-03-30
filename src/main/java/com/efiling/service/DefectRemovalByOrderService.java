package com.efiling.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.efiling.model.Amendment;
import com.efiling.model.DefectRemovalByOrder;
import com.efiling.model.EfilingLookup;

@Service
public class DefectRemovalByOrderService {

	@PersistenceContext(unitName="persistenceUnitEfiling")
	@Qualifier(value = "entityManagerFactoryEfiling")
	private EntityManager em2;
	
	@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	private EntityManager emDMS;
	
	
	@Transactional("transactionManagerEfiling")
	public DefectRemovalByOrder saveDefectRemovalByOrder(DefectRemovalByOrder drb) {
		// TODO Auto-generated method stub
		DefectRemovalByOrder master = null;
		try {
			master = em2.merge(drb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}


	public DefectRemovalByOrder getDefectByRemovalByOrder(Long drp_fd_mid ,List<EfilingLookup> lookupList) {
		
		List<Long> stages =new ArrayList<Long>();
		for(EfilingLookup lookup :lookupList) {
			stages.add(lookup.getLk_id());
		}
		
		System.out.println("Arrayyyy"+stages);
		
	
		
		DefectRemovalByOrder master = null;
	    try {
			String query1="SELECT a from DefectRemovalByOrder a where a.drp_fd_mid=:drp_fd_mid and   a.drp_stage_lid in(:stages)";
			master= (DefectRemovalByOrder) em2.createQuery(query1).setParameter("drp_fd_mid", drp_fd_mid).setParameter("stages", stages).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
	    return master;
	    
	
}
}
