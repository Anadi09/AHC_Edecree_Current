package com.eDecree.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eDecree.model.ApplicationTypes;
import com.eDecree.model.CaseType;
import com.eDecree.model.District;
import com.eDecree.model.IndexField;
import com.eDecree.model.LowerCourtCaseType;

@Service
public class LKOMasterService {
	
	@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	private EntityManager em;
	
	
	@PersistenceContext(unitName="persistenceUnitLKODMS")
	@Qualifier(value = "entityManagerFactoryLKODMS")
	private EntityManager lko;
	
	public List<CaseType> getCaseTypes() {
		// TODO Auto-generated method stub
		List<CaseType> result = em.createQuery("SELECT c FROM CaseType c")
				.getResultList();

		return result;
	}
	
	public List<CaseType> getCaseTypesLKO() {
		// TODO Auto-generated method stub
		List<CaseType> result = lko.createQuery("SELECT c FROM CaseType c")
				.getResultList();

		return result;
	}
	
	
	public CaseType getCaseTypesLKOById(Long id) {
		// TODO Auto-generated method stub
		CaseType result = (CaseType) lko.createQuery("SELECT c FROM CaseType c where c.ct_id="+id).getSingleResult();

		return result;
	}
	
	public List<IndexField> getIndexFields() {
		// TODO Auto-generated method stub
		List<IndexField> result = em.createQuery("SELECT i FROM IndexField i where i.if_rec_status=1")
				.getResultList();

		return result;
	}
	
	public List<IndexField> getSelectecdIndexFields() {
		// TODO Auto-generated method stub
		List<IndexField> result = em.createQuery("SELECT i FROM IndexField i where i.if_id in (39,14)")
				.getResultList();

		return result;
	}
	
	@Transactional
	public IndexField getIndexField(Long id) {
		IndexField result = new IndexField() ;
		try{			
			String sql = "SELECT i FROM IndexField i WHERE i.if_id= :id ";
			result = (IndexField) em.createQuery(sql).setParameter("id", id).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
		}return result;
	}
	@Transactional
	public IndexField getIndexFieldByName(String name) {
		IndexField result = new IndexField() ;
		try{			
			String sql = "SELECT i FROM IndexField i WHERE i.if_name= :name";
			result = (IndexField) em.createQuery(sql).setParameter("name", name).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
		
	public CaseType getCaseTypeByLabel(String name) {
		// TODO Auto-generated method stub
		CaseType result = new CaseType() ;
		try{			
			String sql = "SELECT c FROM CaseType c WHERE c.ct_label= :name";
			result =  (CaseType) em.createQuery(sql).setParameter("name", name).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public List<CaseType> getCaseTypesByUser(Long userId) {
		// TODO Auto-generated method stub
		List<CaseType> result = em.createQuery("SELECT c FROM CaseType c where c.ct_id in(select uc.ucm_ct_mid from UserCaseType uc where uc.ucm_um_mid=:userId)").setParameter("userId", userId)
				.getResultList();

		return result;
	}
	public List<ApplicationTypes> getApplicationsByType(String at_type) {
		List<ApplicationTypes> result = em.createQuery("SELECT a FROM ApplicationTypes a where a.at_type=:at_type and a.at_rec_status=1").setParameter("at_type",at_type)
		.getResultList();
		return result;
	}
	public List<LowerCourtCaseType> getLowerCourtCaseTypes() {
		// TODO Auto-generated method stub
		List<LowerCourtCaseType> result = em.createQuery("SELECT c FROM LowerCourtCaseType c")
				.getResultList();

		return result;
	}
	public List<District> getDistricts() {
		// TODO Auto-generated method stub
		List<District> result = em.createQuery("SELECT d FROM District d")
				.getResultList();
		return result;
	}
} 
