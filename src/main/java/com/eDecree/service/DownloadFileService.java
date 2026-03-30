package com.eDecree.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eDecree.model.CaseFileDetail;
import com.eDecree.model.DownloadFile;
import com.eDecree.model.DownloadReport;
import com.eDecree.model.OrderReport;
import com.eDecree.model.SubDocument;

@Service
public class DownloadFileService {
	@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	EntityManager em;
	
	@PersistenceContext(unitName="persistenceUnitLKODMS")
	@Qualifier(value = "entityManagerFactoryLKODMS")
	private EntityManager lko;
	
	
	@Transactional
    public DownloadReport saveReport(DownloadReport report) {
		DownloadReport dr=null;
		try {	
			dr= em.merge(report);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();	    	
		}
    	return dr;
	}
	
	@Transactional
    public DownloadFile saveFile(DownloadFile file) {
		DownloadFile df=null;
		try {	
			df= em.merge(file);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();	    	
		}
    	return df;
	}


	
	public List<DownloadReport> getDownloadReport(Long fd_id) {
		// TODO Auto-generated method stub
		List<DownloadReport> result = em.createQuery("SELECT d FROM DownloadReport d where d.dr_fd_mid=:fd_id and d.dr_rec_status=1 order by dr_cr_date")
				.setParameter("fd_id", fd_id).getResultList();
		return result;
	}
	
	public DownloadReport getById(Long dr_id) {
		DownloadReport result=new DownloadReport();
	    Query query=null;
		query = em.createQuery("SELECT d from DownloadReport d where d.dr_id=:id").setParameter("id", dr_id);
		try {
			result=(DownloadReport) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return result;
	}

	public List<DownloadFile> getFiles(Long dr_id) {
		// TODO Auto-generated method stub
		List<DownloadFile> result = em.createQuery("SELECT d FROM DownloadFile d where d.df_dr_mid=:dr_id order by df_submitted_date")
				.setParameter("dr_id", dr_id).getResultList();
		return result;
	}

	public List<DownloadReport> getDownloadReports(int itemsPerPage,int pagenumber) {
		// TODO Auto-generated method stub
		int pageSize=itemsPerPage;
		List<DownloadReport> result = em.createQuery("SELECT d FROM DownloadReport d order by dr_cr_date")
				.setFirstResult((pagenumber-1) * pageSize).setMaxResults(itemsPerPage).getResultList();
		return result;
	}

	public Integer getDownloadReportsCount() {
		// TODO Auto-generated method stub
		Integer count=0;
		try {			
			String query = "select count(d) from DownloadReport d";
			count= Integer.parseInt(em.createQuery(query).getSingleResult().toString());			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return count;
	}

	  @Transactional
	public boolean deleteDownloadHistory(Long dr_id) {
            
		boolean result=false;
		    DownloadReport oldreport= em.find(DownloadReport.class,dr_id);
		          oldreport.setDr_rec_status(2);
		          em.merge(oldreport);
                  result=true;           
		          
		return result;
	}
	   @Transactional
		public List<SubDocument> getSubFiles(Long fd_id) {
		  
	    	 List<SubDocument> subDoc=null;
	    		subDoc = em.createQuery("SELECT sd FROM SubDocument sd where sd.sd_fd_mid=:fd_id and sd.sd_rec_status in (1,7,9)  and (sd.sd_document_id != 100002 or sd.sd_document_id is null) order by  sd.sd_submitted_date  ").setParameter("fd_id", fd_id).getResultList();
			    try{
			    	
			    }catch(Exception e){
			    	e.printStackTrace();
			    }
	    		return subDoc;
			        
			        
		}
       @Transactional
	public OrderReport getOrderReport(Long sd_id) {
		OrderReport orderReport=null;
		orderReport = (OrderReport) em.createQuery("SELECT ord FROM OrderReport ord where ord.ord_sd_mid=:sd_id").setParameter("sd_id", sd_id).getSingleResult();
		    try{
		    	
		    }catch(Exception e){
		    	e.printStackTrace();
		    }
 		return orderReport;
}
}
