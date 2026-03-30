package com.efiling.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.efiling.model.OLReport;
@Service
public class OLReportService {

	@PersistenceContext(unitName="persistenceUnitEfiling")
	@Qualifier(value = "entityManagerFactoryEfiling")
	private EntityManager em2;

	
	@Transactional("transactionManagerEfiling")
	public OLReport save(OLReport s) {

		OLReport master = null;
		try {
			master = em2.merge(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}
	
	public List<OLReport> getAll() {
		List<OLReport> result =  new ArrayList<OLReport>();
		Query query = em2.createQuery("select ol from OLReport ol where ol.read_status=1 order by ol.ol_id");		
		try {
			result = query.getResultList();
		} catch (Exception e) {
			throw new EntityNotFoundException("Entity does not exist.");
		} finally {
			return result;
		}
		
	}

	public OLReport getByPK(Long ol_id) {
		// TODO Auto-generated method stub
		OLReport olReport =  new OLReport();
		Query query = em2.createQuery("select ol from OLReport ol where ol.ol_id=:ol_id").setParameter("ol_id", ol_id);		
		try {
			olReport = (OLReport) query.getSingleResult();
		} catch (Exception e) {
			throw new EntityNotFoundException("Entity does not exist.");
		} 
		
			return olReport;
	}
	
	
	public List<OLReport> getByAppNoYearDate(OLReport olr) {
		// TODO Auto-generated method stub
		
		olr.getOl_no();
		olr.getOl_year();
		olr.getOl_created();
		System.out.println("olr.getOl_no()--"+olr.getOl_no()+ "---olr.getOl_year()"+olr.getOl_year()+"----olr.getOl_created() -"+olr.getOl_created() );
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String oldate=null;
		if(olr.getOl_created()!=null)
		{
			oldate = formatter.format(olr.getOl_created());
		}
		
		List<OLReport> result =  new ArrayList<OLReport>();
		if(olr.getOl_no()!=null && olr.getOl_year()!=null && olr.getOl_created()!=null)
		{
			Query query = em2.createQuery("select ol from OLReport ol where ol.ol_no="+olr.getOl_no()+" and  ol.ol_year="+olr.getOl_year()+" and ol.ol_created='"+oldate+"'");
			try {
				result = query.getResultList();
			} catch (Exception e) {
				throw new EntityNotFoundException("Entity does not exist.");
			} 
		}
		
		if(olr.getOl_no()==null && olr.getOl_year()!=null && olr.getOl_created()!=null)
		{
			Query query = em2.createQuery("select ol from OLReport ol where ol.ol_year="+olr.getOl_year()+" and ol.ol_created='"+oldate+"'");
			try {
				result = query.getResultList();
			} catch (Exception e) {
				throw new EntityNotFoundException("Entity does not exist.");
			} 
		}
		
		if(olr.getOl_no()==null && olr.getOl_year()==null && olr.getOl_created()!=null)
		{
			Query query = em2.createQuery("select ol from OLReport ol where ol.ol_created='"+oldate+"'");
			try {
				result = query.getResultList();
			} catch (Exception e) {
				throw new EntityNotFoundException("Entity does not exist.");
			} 
		}
		
		if(olr.getOl_no()==null && olr.getOl_year()!=null && olr.getOl_created()==null)
		{
			Query query = em2.createQuery("select ol from OLReport ol where ol.ol_year="+olr.getOl_year()+"");
			try {
				result = query.getResultList();
			} catch (Exception e) {
				throw new EntityNotFoundException("Entity does not exist.");
			} 
		}
		
		if(olr.getOl_no()!=null && olr.getOl_year()!=null && olr.getOl_created()==null)
		{
			Query query = em2.createQuery("select ol from OLReport ol where ol.ol_no ="+olr.getOl_no()+" and ol.ol_year="+olr.getOl_year()+"");
			try {
				result = query.getResultList();
			} catch (Exception e) {
				throw new EntityNotFoundException("Entity does not exist.");
			} 
		}
		if(olr.getOl_no()!=null && olr.getOl_year()==null && olr.getOl_created()==null)
		{
			Query query = em2.createQuery("select ol from OLReport ol where ol.ol_no ="+olr.getOl_no()+"");
			try {
				result = query.getResultList();
			} catch (Exception e) {
				throw new EntityNotFoundException("Entity does not exist.");
			} 
		}
			return result;
	}
}
