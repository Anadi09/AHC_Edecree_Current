package com.eDecree.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eDecree.model.Notes;
import com.eDecree.model.OrderReport;
import com.eDecree.model.SubApplication;
import com.eDecree.model.SubDocument;
import com.eDecree.model.SubDocumentApplicationStatus;

@Service
public class SubDocumentService
{
	@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	private EntityManager em;
	
	@PersistenceContext(unitName="persistenceUnitLKODMS")
	@Qualifier(value = "entityManagerFactoryLKODMS")
	private EntityManager lko;
	
	@Transactional
	public SubDocument save(SubDocument s) {
		SubDocument master = null;
		try {
			master = em.merge(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}
	
	@Transactional
	public List<OrderReport> getOrderReport(Long fd_id, Date currentDate, Date nextDate) {
		
		System.out.println("currentDate"+currentDate);
		System.out.println("nextDate"+nextDate);
		// TODO Auto-generated method stub
		List<OrderReport> result = em.createQuery("SELECT o from OrderReport o where o.ord_created > :currentDate and o.ord_created < :nextDate and o.ord_fd_mid=:ord_fd_mid order by o.ord_created").setParameter("ord_fd_mid", fd_id).setParameter("currentDate", currentDate).setParameter("nextDate", nextDate).getResultList();

		return result;
	}
	
	@Transactional
	public List<OrderReport> getOrderReport1(Long fd_id, Date currentDate, Date nextDate,Long um_id) {
		
		System.out.println("currentDate"+currentDate);
		System.out.println("nextDate"+nextDate);
		// TODO Auto-generated method stub
		List<OrderReport> result = em.createQuery("SELECT o from OrderReport o where o.ord_created > :currentDate and o.ord_created < :nextDate and o.ord_fd_mid=:ord_fd_mid and o.ord_created_by =:um_id order by o.ord_created ").setParameter("ord_fd_mid", fd_id).setParameter("currentDate", currentDate).setParameter("nextDate", nextDate).setParameter("um_id", um_id).getResultList();

		return result;
	}
	
	@Transactional
	public List<SubDocument> getSubDocumentOrdersForTransfer(Long fd_id,Date date) {
		// TODO Auto-generated method stub
		List<SubDocument> result =null;
		try {
 result = em.createQuery("SELECT d FROM SubDocument d where d.sd_fd_mid=:sd_fd_mid and  d.sd_submitted_date > :date and   d.sd_document_id = 100002 and d.sd_rec_status =4").setParameter("sd_fd_mid", fd_id).setParameter("date", date).getResultList();
		}
		catch(Exception e) {
			
		}

		return result;
	}
	
	@Transactional
	public Integer getCount(Long fd_id) {
		
		
		Integer count=0;
		try {
			Query query=em.createQuery("SELECT Count(d) FROM SubDocument d where d.sd_fd_mid=:sd_fd_mid").setParameter("sd_fd_mid",fd_id);
			count= Integer.parseInt(query.getSingleResult().toString());
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
		}		
		return count;
	}
	
	public List<SubDocument> getApplicationByFDdmid(Long fd_id) {
		
		List<SubDocument> result = em.createQuery("SELECT d FROM SubDocument d where d.sd_fd_mid=:sd_fd_mid and d.sd_rec_status=1 and d.sd_if_mid=14 and d.sd_document_no IS NOT NULL and d.sd_document_year IS NOT NULL order by d.sd_submitted_date desc")
				.setParameter("sd_fd_mid", fd_id).getResultList();

		return result;
	}
	
	@Transactional
	public SubDocument getSubDocument(Long fd_id) {
		
		SubDocument subDocument=null;
		try {
			Query query=em.createQuery("SELECT d FROM SubDocument d where d.sd_fd_mid=:sd_fd_mid order by sd_id asc").setParameter("sd_fd_mid",fd_id);
			subDocument= (SubDocument) query.setMaxResults(1).getSingleResult();
		} catch (Exception e) {
			
		}finally{
			return subDocument;	
		}
	}
	
	// for orders from eliglix
	
	public List<SubDocument> getSubDocuments(Long fd_id) {
		// TODO Auto-generated method stub
		List<SubDocument> result = em.createQuery("SELECT d FROM SubDocument d where d.sd_fd_mid=:sd_fd_mid and d.sd_rec_status in(1,9,7,8) and (d.sd_document_id != 100002 or d.sd_document_id is null) and  d.sd_id not in (select ord.ord_sd_mid from OrderReport ord where ord.ord_fd_mid=:ord_fd_mid and ord.ord_sd_mid is not null)order by sd_submitted_date ASC").setParameter("sd_fd_mid", fd_id).setParameter("ord_fd_mid", fd_id).getResultList();

		return result;
	}
	
	public List<SubDocument> getSubDocumentOrders(Long fd_id,Date date) {
		// TODO Auto-generated method stub
		List<SubDocument> result =null;
		try {
 result = em.createQuery("SELECT d FROM SubDocument d where d.sd_fd_mid=:sd_fd_mid and  d.sd_submitted_date < :date and   d.sd_document_id = 100002 and d.sd_rec_status =1").setParameter("sd_fd_mid", fd_id).setParameter("date", date).getResultList();
		}
		catch(Exception e) {
			
		}

		return result;
	}
	
	/*public List<SubDocument> getSubDocuments(Long fd_id) {
		// TODO Auto-generated method stub
		List<SubDocument> result = em.createQuery("SELECT d FROM SubDocument d where d.sd_fd_mid=:sd_fd_mid and d.sd_rec_status in(1,9,7)  and  d.sd_id not in (select ord.ord_sd_mid from OrderReport ord where ord.ord_fd_mid=:ord_fd_mid and ord.ord_sd_mid is not null)order by sd_submitted_date ASC").setParameter("sd_fd_mid", fd_id).setParameter("ord_fd_mid", fd_id).getResultList();

		return result;
	}*/
	
	public List<SubDocument> getSubDocumentsLKO(Long fd_id) {
		// TODO Auto-generated method stub
		List<SubDocument> result = lko.createQuery("SELECT d FROM SubDocument d where d.sd_fd_mid=:sd_fd_mid and d.sd_rec_status=1 and d.sd_id not in (select ord.ord_sd_mid from OrderReport ord where ord.ord_fd_mid=:ord_fd_mid and ord.ord_sd_mid is not null)order by sd_submitted_date").setParameter("sd_fd_mid", fd_id).setParameter("ord_fd_mid", fd_id).getResultList();

		return result;
	}
	
	public Integer getApplicationId() {
		int id =0;
		
		return id;
	}
	public SubDocument getByJudgementID(Long judgement_id) {
		// TODO Auto-generated method stub
		SubDocument result=null;
		
		try {
			result = (SubDocument) em.createQuery("SELECT d FROM SubDocument d where d.sd_judgement_id=:judgement_id")
					.setParameter("judgement_id", judgement_id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	public SubDocument getByPKLKO(Long sd_id) {
		// TODO Auto-generated method stub
		SubDocument subDocument=null;
		try {
			Query query=lko.createQuery("SELECT d FROM SubDocument d where d.sd_id=:sd_id").setParameter("sd_id",sd_id);
			subDocument= (SubDocument) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}finally{
			return subDocument;	
		}

	}
	
	public SubDocument getByPK(Long sd_id) {
		// TODO Auto-generated method stub
		SubDocument subDocument=null;
		try {
			Query query=em.createQuery("SELECT d FROM SubDocument d where d.sd_id=:sd_id").setParameter("sd_id",sd_id);
			subDocument= (SubDocument) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}finally{
			return subDocument;	
		}

	}
	public SubDocument getApplication(Integer app_no, Integer app_year) {
		// TODO Auto-generated method stub
		SubDocument subDocument=null;
		try {
			Query query=em.createQuery("SELECT d FROM SubDocument d where d.sd_document_no=:app_no and d.sd_document_year=:app_year  and d.sd_if_mid=14").setParameter("app_no",app_no).setParameter("app_year", app_year);
			subDocument= (SubDocument) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}finally{
			return subDocument;	
		}		
				
		
	}
	public List<SubDocument> getAllSubDocuments(Long fd_id) {
		// TODO Auto-generated method stub
		List<SubDocument> result = em.createQuery("SELECT d FROM SubDocument d where d.sd_fd_mid=:sd_fd_mid and d.sd_rec_status in(1,7,9) order by d.sd_submitted_date")
				.setParameter("sd_fd_mid", fd_id).getResultList();

		return result;
	}
	
	public List<SubDocument> getAllSubDocumentsLKO(Long fd_id) {
		// TODO Auto-generated method stub
		List<SubDocument> result = lko.createQuery("SELECT d FROM SubDocument d where d.sd_fd_mid=:sd_fd_mid and d.sd_rec_status=1 order by d.sd_submitted_date")
				.setParameter("sd_fd_mid", fd_id).getResultList();

		return result;
	}
	
	@Transactional
	public SubDocument getPetitionSubDocumentLKO(Long fd_id, Integer rec_status) {
		// TODO Auto-generated method stub
		SubDocument subDocument=null;
		try {
			Query query=lko.createQuery("SELECT d FROM SubDocument d where d.sd_fd_mid=:sd_fd_mid and d.sd_if_mid=1 and d.sd_rec_status=:rec_status order by sd_id asc").setParameter("sd_fd_mid",fd_id).setParameter("rec_status", rec_status);
			subDocument= (SubDocument) query.setMaxResults(1).getSingleResult();
		} catch (Exception e) {
		}finally{
			return subDocument;	
		}
	}
	
	
	@Transactional
	public SubDocument getPetitionSubDocument(Long fd_id, Integer rec_status) {
		// TODO Auto-generated method stub
		SubDocument subDocument=null;
		try {
			Query query=em.createQuery("SELECT d FROM SubDocument d where d.sd_fd_mid=:sd_fd_mid and d.sd_if_mid=1 and d.sd_rec_status=:rec_status order by sd_id asc").setParameter("sd_fd_mid",fd_id).setParameter("rec_status", rec_status);
			subDocument= (SubDocument) query.setMaxResults(1).getSingleResult();
		} catch (Exception e) {
		}finally{
			return subDocument;	
		}
	}
	
	public List<SubDocument> getByFieldLKO(Long fd_id, Long field_id) {
		// TODO Auto-generated method stub
		List<SubDocument> result = lko.createQuery("SELECT d FROM SubDocument d where d.sd_fd_mid=:sd_fd_mid and d.sd_if_mid=:sd_if_mid and d.sd_rec_status=1 order by d.sd_submitted_date")
				.setParameter("sd_fd_mid", fd_id).setParameter("sd_if_mid", field_id).getResultList();

		return result;
	}
	
	public List<SubDocument> getByField(Long fd_id, Long field_id) {
		// TODO Auto-generated method stub
		List<SubDocument> result = em.createQuery("SELECT d FROM SubDocument d where d.sd_fd_mid=:sd_fd_mid and d.sd_if_mid=:sd_if_mid and d.sd_rec_status=1 order by d.sd_submitted_date")
				.setParameter("sd_fd_mid", fd_id).setParameter("sd_if_mid", field_id).getResultList();

		return result;
	}
	
	
	public SubDocument getApplicationByCase(Integer app_no, Integer app_year,Long case_id) {
		// TODO Auto-generated method stub
		SubDocument subDocument=null;
		try {
			Query query=em.createQuery("SELECT d FROM SubDocument d where d.sd_document_no=:app_no and d.sd_document_year=:app_year  and d.sd_fd_mid=:case_id and d.sd_if_mid=14").setParameter("app_no",app_no).setParameter("app_year", app_year).setParameter("case_id", case_id);
			subDocument= (SubDocument) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch blocko
			//e.printStackTrace();
		}finally{
			return subDocument;	
		}
	}
	
	@Transactional
	public Notes saveNoteLKO(Notes nt) {
		Notes master = null;
		try {
			master = lko.merge(nt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}
	
	
	@Transactional
	public Notes saveNote(Notes nt) {
		Notes master = null;
		try {
			master = em.merge(nt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}
	
	public Notes getNoteLKO(Long fd_id) {
		Notes result =null;
		System.out.println("== --------");
		try
		{
		result = (Notes)lko.createQuery("SELECT nt FROM Notes nt where nt.nt_fd_mid=:fd_id").setParameter("fd_id", fd_id).getSingleResult();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public Notes getNote(Long fd_id, Long userId) {
		Notes result =null;
		System.out.println("== --------");
		try
		{
		result = (Notes)em.createQuery("SELECT nt FROM Notes nt where nt.nt_fd_mid=:fd_id and nt.nt_cr_by="+userId+"")
				.setParameter("fd_id", fd_id).getSingleResult();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return result;
	}
	
	@Transactional
	public int updateNote(Notes nt,Long jgid) {
		int result =0;
		System.out.println("== --------");
		try
		{
		result = em.createNativeQuery("UPDATE Notes set nt_notes=:nt_notes,nt_mod_date=:nt_mod_date where nt_fd_mid=:fd_id and nt_cr_by="+jgid+"").setParameter("fd_id", nt.getNt_fd_mid()).setParameter("nt_notes",nt.getNt_notes()).setParameter("nt_mod_date",new Date()).executeUpdate();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return result;
	}
	
	@Transactional
	public int updateNoteLKO(Notes nt) {
		int result =0;
		System.out.println("== --------");
		try
		{
		result = lko.createNativeQuery("UPDATE Notes set nt_notes=:nt_notes,nt_mod_date=:nt_mod_date where nt_fd_mid=:fd_id").setParameter("fd_id", nt.getNt_fd_mid()).setParameter("nt_notes",nt.getNt_notes()).setParameter("nt_mod_date",new Date()).executeUpdate();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return result;
	}
	public SubApplication getSubApplication(Integer app_no,Integer app_year,Long case_id) {

		SubApplication result = null;
		try {
			result = (SubApplication) em.createQuery(
					"SELECT sbap FROM SubApplication sbap where sbap.sb_ap_no=:app_no and sbap.sb_ap_year =:app_year and sbap.sb_ap_fd_mid = :case_id and sbap.sb_ap_rec_status=1")
					.setParameter("app_no", app_no).setParameter("app_year",app_year).setParameter("case_id",case_id).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
	public SubDocument getLastOrderOfPendingCase(Long sd_fd_mid) {
		SubDocument subDocument=null;
		try {
			Query query=lko.createQuery("SELECT d FROM SubDocument d where d.sd_fd_mid=:sd_fd_mid and d.sd_document_id = 100002 order by d.sd_submitted_date desc").setParameter("sd_fd_mid",sd_fd_mid).setMaxResults(1);
			subDocument= (SubDocument) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}finally{
			return subDocument;	
		}
	}
	public List<SubDocument> getSubDocumentsIncludingOrder(Long fd_id) {
		List<SubDocument> result = em.createQuery("SELECT d FROM SubDocument d where d.sd_fd_mid=:sd_fd_mid and d.sd_rec_status in(1,9,7)  and  d.sd_id not in (select ord.ord_sd_mid from OrderReport ord where ord.ord_fd_mid=:ord_fd_mid and ord.ord_sd_mid is not null)order by sd_submitted_date ASC").setParameter("sd_fd_mid", fd_id).setParameter("ord_fd_mid", fd_id).getResultList();

		return result;
	}

	
	@Transactional
	public void saveSubDocumentApplicationStatus(SubDocumentApplicationStatus sdas) {
		SubDocumentApplicationStatus master = null;
		try {
			master = em.merge(sdas);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
	

