package com.efiling.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.efiling.model.Amendment;
import com.efiling.model.EfilingApplicationTypes;
import com.efiling.model.EfilingCaseFileDetail;
import com.efiling.model.EfilingLookup;
import com.efiling.model.EfilingUser;
import com.efiling.model.RegisteredCaseDetails;

@Service
public class AmendmentService {
	
	
	@PersistenceContext(unitName="persistenceUnitEfiling")
	@Qualifier(value = "entityManagerFactoryEfiling")
	private EntityManager em2;
	
	@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	private EntityManager emDMS;

	public RegisteredCaseDetails getRegisterCase(Long fd_case_type, Integer fd_case_no, Integer fd_case_year) {
		RegisteredCaseDetails result=new RegisteredCaseDetails();
		try{
		Query query=null;
		query = em2.createQuery("SELECT rcd from RegisteredCaseDetails rcd where rcd.rcd_ct_id=:fd_case_type and rcd.rcd_case_no=:fd_case_no and rcd.rcd_case_year=:fd_case_year").setParameter("fd_case_type", fd_case_type).setParameter("fd_case_no", fd_case_no).setParameter("fd_case_year", fd_case_year);
		result=(RegisteredCaseDetails)query.setMaxResults(1).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;		
	}

	@SuppressWarnings("unchecked")
	public List<EfilingUser> getApplicationUsers(Long fd_id,Long ap_stage_lid) {
		// TODO Auto-generated method stub
		List<EfilingUser> result=new ArrayList<EfilingUser>();
		try{
		Query query=null;
		query = em2.createQuery("SELECT u from EfilingUser u where u.um_id IN (select distinct(ap_cr_by) from EfilingApplication where ap_fd_mid=:ap_fd_mid and ap_stage_lid=:ap_stage_lid)").setParameter("ap_fd_mid", fd_id).setParameter("ap_stage_lid", ap_stage_lid);
		result=query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public EfilingCaseFileDetail getCaseFile(Long fd_id) {
		EfilingCaseFileDetail result=null;
	    try {
			String query=" SELECT cfd from EfilingCaseFileDetail cfd where cfd.fd_id=:fd_id";
			result= (EfilingCaseFileDetail) em2.createQuery(query).setParameter("fd_id", fd_id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	/*public List<EfilingUser> searchUser(String name) {
		// TODO Auto-generated method stub
		List<EfilingUser> result=new ArrayList<>();
	    try {
			String query="SELECT u from EfilingUser u where lower(u.um_fullname) like '%"+name.toLowerCase()+"%' ";
			result= em2.createQuery(query).getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return result;
	}*/
	
	public EfilingUser searchUser(String username) {
		// TODO Auto-generated method stub
		EfilingUser result=new EfilingUser();
	    try {
			String query="SELECT u from EfilingUser u where u.username=:username";
			result= (EfilingUser) em2.createQuery(query).setParameter("username", username).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return result;
	}
	
	@Transactional("transactionManagerEfiling")
	public Amendment saveAmendment(Amendment amendment) {
		// TODO Auto-generated method stub
		Amendment master = null;
		try {
			master = em2.merge(amendment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}
	
	public Amendment getAmendment(Long am_id) {
		// TODO Auto-generated method stub
		Amendment master = null;
	    try {
			String query="SELECT a from Amendment a where a.am_id=:am_id";
			master= (Amendment) em2.createQuery(query).setParameter("am_id", am_id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return master;
	}

	public Amendment getAmendment(Long am_fd_mid, String am_type, Long am_createstatus ,Long am_uploadstatus) {
		// TODO Auto-generated method stub
		Amendment master = null;
	    try {
			String query="SELECT a from Amendment a where a.am_fd_mid=:am_fd_mid and a.am_type=:am_type and a.am_status=:am_createstatus or a.am_status=:am_uploadstatus";
			master= (Amendment) em2.createQuery(query).setParameter("am_fd_mid", am_fd_mid).setParameter("am_type", am_type).setParameter("am_createstatus", am_createstatus).setParameter("am_uploadstatus", am_uploadstatus).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return master;
	}
	
	public Amendment getAmendmentApplication(Long am_fd_mid,Long am_at_mid, Integer am_document_no, Integer am_document_year, Long am_status) {
		Amendment master = null;
	    try {
			String query="SELECT a from Amendment a where a.am_fd_mid=:am_fd_mid and a.am_at_mid=:am_at_mid and a.am_document_no=:am_document_no "
					+ "and a.am_document_year=:am_document_year and am_status=:am_status";
			master= (Amendment) em2.createQuery(query).setParameter("am_fd_mid", am_fd_mid).setParameter("am_at_mid", am_at_mid)
					.setParameter("am_document_no", am_document_no).setParameter("am_document_year", am_document_year)
					.setParameter("am_status", am_status).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return master;
	}
	

	public List<Amendment> getAmendments(Long am_fd_mid) {
		// TODO Auto-generated method stub
		List<Amendment> result=new ArrayList<>();
	    try {
			String query="SELECT a from Amendment a where a.am_fd_mid=:am_fd_mid";
			result= em2.createQuery(query).setParameter("am_fd_mid",am_fd_mid).getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return result;
	}

	public List<Amendment> getAmendmentsByUser(Long user_id, Long lk_id) {
		// TODO Auto-generated method stub
		List<Amendment> result=new ArrayList<>();
	    try {
			String query="SELECT a from Amendment a where a.am_um_mid=:am_um_mid and a.am_status=:am_status";
			result= em2.createQuery(query).setParameter("am_um_mid",user_id).setParameter("am_status", lk_id).getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return result;
	}
	public List<Object> getApplicationTypes(Long caseyear,Long casetype,String caseno) 
	{
		List<Object> result=null;
		Query query=null;
		query = emDMS.createNativeQuery(" select sd_id,(select at_name from application_types where at_id=sd_document_id),sd_document_id,sd_document_no,sd_document_year from sub_documents  where sd_if_mid  =14 and sd_fd_mid =(select fd_id from case_file_details  where fd_case_no  =:fd_case_no and fd_case_type=:fd_case_type and fd_case_year=:fd_case_year)" + 
				"").setParameter("fd_case_year", caseyear).setParameter("fd_case_type", casetype).setParameter("fd_case_no", caseno);
		result=query.getResultList();
		return result;
	}
	public EfilingLookup getLookup(String lk_setname,String lk_longname) {
		EfilingLookup lookup = new EfilingLookup();
		try
		{	
			Query query = em2.createQuery("SELECT u FROM EfilingLookup u WHERE (lk_setname=:lk_setname AND lk_longname =:lk_longname)");
			query.setParameter("lk_setname", lk_setname).setParameter("lk_longname", lk_longname);
			lookup = (EfilingLookup)query.getSingleResult();
		}catch(Exception e) {			
			e.printStackTrace();
			
		}
		finally{
			return lookup;
		}
	}
	public List<EfilingCaseFileDetail> getCaseFile(Long caseyear,Long casetype,String caseno) {
		List<EfilingCaseFileDetail> result=null;
	    String query=" SELECT cfd from EfilingCaseFileDetail cfd where cfd.fd_case_year="+caseyear+" and cfd.fd_case_type="+casetype+" and cfd.fd_case_no='"+caseno+"' ";
		result= em2.createQuery(query).getResultList();
		
		return result;
	}
	public EfilingCaseFileDetail getEilingCaseFileDetails(Long caseyear,Long casetype,String caseno) {
		EfilingCaseFileDetail result=null;
		try {
			String query=" SELECT cfd from EfilingCaseFileDetail cfd where cfd.fd_case_year="+caseyear+" and cfd.fd_case_type="+casetype+" and cfd.fd_case_no='"+caseno+"' ";
			result= (EfilingCaseFileDetail) em2.createQuery(query).getSingleResult();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
		
		return result;
	}
	
	public EfilingUser getUser(Long id) {
		EfilingUser r = em2.find(EfilingUser.class, id);
		return r;
	}
}
