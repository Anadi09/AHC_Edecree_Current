package com.eDecree.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eDecree.model.Judge;
import com.eDecree.model.Lookup;

@Service
public class LookupService {

	@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	private EntityManager em;

	@PersistenceContext(unitName="persistenceUnitLKODMS")
	@Qualifier(value = "entityManagerFactoryLKODMS")
	private EntityManager lko;

	@Transactional
	public List<Lookup> getAll(){
		List<Lookup> result=em.createQuery("SELECT s FROM Lookup s ORDER BY s.lk_setname").getResultList();
		return result;
		
	}
	

	@Transactional
	public List<Lookup> getAllSetName(Integer priority){
		List<Lookup> result=em.createQuery("SELECT l FROM Lookup l where l.lk_priority <=:priority").setParameter("priority", priority).getResultList();
		return result;
		
	}
	

	@Transactional
	public List<Lookup> getAllParentName(){
		List<Lookup> result=em.createQuery("SELECT  l FROM Lookup l").getResultList();
		return result;
		
	}
	
	@Transactional
	public Lookup getLookUpById(Long id){
		Lookup m= (Lookup) em.createQuery("SELECT  l FROM Lookup l where lk_id=:id").setParameter("id", id).getSingleResult();
		return m;
	}
	
	@Transactional
	public Lookup getLookUp(Long id){
		Lookup m= em.find(Lookup.class, id);
		return m;
	}

	
	@Transactional
    public Lookup save(Lookup s) {
    
		Lookup lookUp = null;
    	try {	
    		lookUp= em.merge(s);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return lookUp;
    }
	
	@Transactional
    public void delete(Long id) {    
		Lookup m = em.find(Lookup.class, id);		   
		  em.remove(m);
    }
	
	@Transactional
	public List<Lookup> getAll(String setname) {
		List<Lookup> result = new ArrayList<Lookup>() ;
		try
		{		
			String sql = "SELECT l FROM Lookup l WHERE l.lk_setname= :val1 AND l.lk_rec_status=1 and l.lk_id in(351455,351456) ORDER BY l.lk_longname";
			result = em.createQuery(sql).setParameter("val1", setname).getResultList();	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	
	@Transactional
	public List<Lookup> getMasterSearch() {
		List<Lookup> result = new ArrayList<Lookup>() ;
		try
		{		
			String sql = "(Select ib,cfd FROM InwardBundle ib, CaseFileDetail cfd WHERE ib.ib_branch = 8"
					+ "AND fd_case_no LIKE '%CASE0%' AND ib.ib_id in (SELECT c.ib_mid FROM CaseFileDetail c"
					+ " WHERE c.fd_first_petitioner LIKE '%FIRSTP%') AND cfd.ib_mid = ib.ib_id"
					+ "AND ib.ib_id IN (select cf.ib_mid from CaseFileDetail cf where cf.fd_first_respondent like '%FIRSTR%') "
					+ "AND cfd.fd_file_bar_code LIKE '%BAR%' AND cfd.fd_judgement_date = to_date('2015-09-08','yyyy-mm-dd'))";
			
			result = em.createQuery(sql).getResultList();	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	

	
	@Transactional
	public Lookup getAllByLongname(String setname) {
		Lookup result = new Lookup();
		List<Lookup> result1 = new ArrayList<Lookup>();		
		try
		{
			System.out.println("get list "+setname);
			String sql = "SELECT l FROM Lookup l WHERE l.lk_longname= :val1 ORDER BY l.lk_id";   // spacificaly not included ==> l.lk_rec_status=1 
			
			result1 =  em.createQuery(sql).setParameter("val1", setname).getResultList();
			
			result = result1.get(0);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	// [END]To GET List of Total Bundle//
	
	@Transactional
	public List<Lookup> getLookUpMaster() {
		List<Lookup> result = new ArrayList<Lookup>() ;
		try
		{			
			String sql = "SELECT l FROM Lookup l WHERE l.lk_rec_status=1 ORDER BY l.lk_id";
			result = em.createQuery(sql).getResultList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	@Transactional
	public Lookup getLookUp(String setname,String value) {
		Lookup result = new Lookup() ;
		try{			
			String sql = "SELECT l FROM Lookup l WHERE l.lk_setname= :setname  AND l.lk_value= :value AND l.lk_rec_status=1";
			result = (Lookup) em.createQuery(sql).setParameter("setname", setname).setParameter("value", value).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
		}return result;
	}
	

	@Transactional
	public Lookup getLookUpObject(String setname) {
		Lookup result = new Lookup();
		try{			
			String sql = "SELECT l FROM Lookup l WHERE l.lk_setname= :setname  AND l.lk_rec_status=1";
			result = (Lookup) em.createQuery(sql).setParameter("setname", setname).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
		}return result;
	}

	@Transactional
	public Lookup getLookUpObjectLKO(String setname) {
		Lookup result = new Lookup();
		try{			
			String sql = "SELECT l FROM Lookup l WHERE l.lk_setname= :setname  AND l.lk_rec_status=1";
			result = (Lookup) em.createQuery(sql).setParameter("setname", setname).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
		}return result;
	}
	
		@Transactional
	public Lookup getLookup(String lk_setname,String lk_longname) {
		Lookup lookup = new Lookup();
		try
		{	
			Query query = em.createQuery("SELECT u FROM Lookup u WHERE (lk_setname=:lk_setname AND lk_longname =:lk_longname)");
			query.setParameter("lk_setname", lk_setname).setParameter("lk_longname", lk_longname);
			lookup = (Lookup)query.getSingleResult();
		}catch(Exception e) {			
			e.printStackTrace();
			
		}
		finally{
			return lookup;
		}
	}
	
	@Transactional
	public List<Lookup> getAllCaseType(String setname) {
		List<Lookup> result = new ArrayList<Lookup>() ;
		try
		{
			String sql = "SELECT l FROM Lookup l WHERE l.lk_setname= :val1 AND l.lk_rec_status=1 ORDER BY l.lk_id";
		result = em.createQuery(sql).setParameter("val1", setname).getResultList();
		System.out.println("get list size ==  "+result.size());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	@Transactional
	public List<Lookup> getAllByPriority(Integer priority) {
		// TODO Auto-generated method stub
		List<Lookup> result=em.createQuery("SELECT s FROM Lookup s where lk_priority <=:priority ORDER BY s.lk_setname").setParameter("priority", priority).getResultList();
		return result;
	}


	@Transactional
	public List<Lookup> CheckRegex(String setname) {
		List<Lookup> result = new ArrayList<Lookup>() ;
		try
		{		
			String sql = "SELECT l FROM Lookup l WHERE l.lk_setname=:setname AND l.lk_rec_status=1 ORDER BY l.lk_longname";
			result = em.createQuery(sql).setParameter("setname", setname).getResultList();	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}


	public List<Lookup> getRejectedStage() {
		// TODO Auto-generated method stub
		List<Lookup> result = new ArrayList<Lookup>() ;
		try
		{		
			String sql = "SELECT l FROM Lookup l WHERE l.lk_setname=:setname AND l.lk_rec_status=2 ORDER BY l.lk_longname";
			result = em.createQuery(sql).setParameter("setname", "DMS_STAGE").getResultList();	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}


	public List<Judge> getHighCourtJudges() {
		// TODO Auto-generated method stub
		List<Judge> result = new ArrayList<Judge>() ;
		try
		{		
			String sql = "SELECT j FROM Judge j WHERE j.jg_rec_status=1 AND j.jg_bench_code=8 "+ 
						" AND j.jg_type=(select lk_id from Lookup where lk_longname = 'High Court')";
			result = em.createQuery(sql).getResultList();	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;

	}
}

