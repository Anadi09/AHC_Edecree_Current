package com.eDecree.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eDecree.model.CauseListHistory;
import com.eDecree.model.OrderReport;

@Service
public class OrderReportService {
	
	@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	private EntityManager em;
	
	@PersistenceContext(unitName="persistenceUnitLKODMS")
	@Qualifier(value = "entityManagerFactoryLKODMS")
	private EntityManager lko;
	
	
	@PersistenceContext(unitName="persistenceUnitEfiling")
	@Qualifier(value = "entityManagerFactoryEfiling")
	private EntityManager em2;
	
	
	
	
	public List<OrderReport> getOfficeReportsedit(Long fd_id, Long user,CauseListHistory causthistory) {

		if (causthistory==null) {
			List<OrderReport> result = (List<OrderReport>)em.createQuery("SELECT o FROM OrderReport o where o.ord_fd_mid= :fd_id and ord_created_by=:user and  o.ord_rec_status=1").setParameter("fd_id", fd_id).setParameter("user", user).getResultList();
			return result;
		} else {
			List<OrderReport> result = (List<OrderReport>)em.createQuery("SELECT o FROM OrderReport o where o.ord_fd_mid= :fd_id and ord_created_by=:user and o.ord_created > :ord_created and o.ord_rec_status=1").setParameter("fd_id", fd_id).setParameter("user", user).setParameter("ord_created", causthistory.getClh_dol()).getResultList();
			return result;
		}
			
		}
		
	
	@Transactional
    public OrderReport save(OrderReport report) {
    
		OrderReport o = null;
    	try {	
    		o= em.merge(report);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return o;
    }
	
	
	public List<Object> getOrderReports(Long fd_id) {
			
		List<Object> result = em.createQuery("SELECT o,um FROM OrderReport o,User um where o.ord_fd_mid=:fd_id  and um_id=o.ord_created_by  and o.ord_rec_status=1").setParameter("fd_id", fd_id).getResultList();
		return result;
	}
	
	public List<Object> getOrderReports1(Long fd_id) {
		List<Object> result =null;
		try {
		 result = em.createQuery("SELECT o,um FROM OrderReport o,User um where o.ord_fd_mid=:fd_id  and um_id=o.ord_created_by  and o.ord_rec_status=1").setParameter("fd_id", fd_id).getResultList();
		}
		catch (Exception e){
			
		}
		return result;
	}
	
	public List<OrderReport> getOfficeReports(Long fd_id) {
		
		List<OrderReport> result = em.createQuery("SELECT o FROM OrderReport o where o.ord_fd_mid=:fd_id  and o.ord_rec_status=1").setParameter("fd_id", fd_id).getResultList();
		return result;
	}
	
	public List<Object> getOrderReportsLKO2(Long fd_id) {
		
		List<Object> result = lko.createQuery("SELECT o,um FROM OrderReport o,User um where o.ord_fd_mid=:fd_id  and um_id=o.ord_created_by  and o.ord_rec_status=1").setParameter("fd_id", fd_id).getResultList();
		return result;
	}
	public List<OrderReport> getOrderReportsLKO(Long fd_id) {
		
		List<OrderReport> result=null;
		result = lko.createQuery("SELECT o FROM OrderReport o where o.ord_fd_mid=:fd_id and o.ord_rec_status=1").setParameter("fd_id", fd_id).getResultList();
		return result;
	}
	
	@Transactional
    public OrderReport getOrderReport(Long ord_id) {
    
		OrderReport or = null;
    	try {	
    		or= (OrderReport) em.createQuery("SELECT o from OrderReport o where o.ord_id=:ord_id").setParameter("ord_id", ord_id).getSingleResult();	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return or;
    }
	
	public List<OrderReport> getOfficeReport(Date last,Date current,Long fd_id) {
		List<OrderReport> result = em.createQuery("SELECT o FROM OrderReport o where o.ord_fd_mid=:fd_id  and o.ord_created>:last and o.ord_created<:current and o.ord_rec_status=1 order by  o.ord_created ").setParameter("last", last).setParameter("current", current).setParameter("fd_id", fd_id).getResultList();
		return result;
	}
	
	// change by afnan start
	@Transactional
	public Integer getORCount(Long or_f_id) {
		// TODO Auto-generated method stub
		Integer count=0;
		try {
			Query query=em.createQuery("SELECT Count(odr) FROM OrderReport odr where odr.ord_fd_mid=:ord_fd_mid").setParameter("ord_fd_mid",or_f_id);
			count= Integer.parseInt(query.getSingleResult().toString());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return count;
	}
	// change by afnan end
	
	@Transactional
    public OrderReport getBySdId(Long sd_id) {
    
		OrderReport or = null;
    	try {	
    		or= (OrderReport) em.createQuery("SELECT o from OrderReport o where o.ord_sd_mid=:sd_id").setParameter("sd_id", sd_id).getSingleResult();	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return or;
    }


	public OrderReport getOrderReportById(Long ord_id, Date d1, Date d2,Long um_id) {
		OrderReport or = null;
    	try {	
    		or= (OrderReport) em.createQuery("SELECT o from OrderReport o where o.ord_id=:ord_id and o.ord_created > :d1 and o.ord_created < :d2 and o.ord_created_by =:um_id ").setParameter("ord_id", ord_id).setParameter("d1",d1).setParameter("d2",d2).setParameter("um_id", um_id).getSingleResult();	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return or;
	
	}
	
}
