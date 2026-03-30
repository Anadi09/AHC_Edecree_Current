package com.eDecree.service;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eDecree.model.CauseList;
import com.eDecree.model.CauseListType;
import com.eDecree.model.CourtMaster;
import com.eDecree.model.CourtOrder;
import com.eDecree.model.PetitionerAdvocate;
import com.eDecree.model.PetitionerCounsel;
import com.eDecree.model.RespondentAdvocate;
import com.eDecree.model.RespondentCounsel;



@Service
public class CauseListService 
{
	@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	EntityManager em;
	
	String cl_dol=null;
	
	@Transactional
    public CauseList save(CauseList cl) {
    
		CauseList causeList = null;
    	try {	
    		causeList= em.merge(cl);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();	    	
		}
    	return causeList;
    }
	

	
	@Transactional
	public List<CauseListType> getCauseListTypes() {
		List<CauseListType> result = em.createQuery("SELECT c FROM CauseListType c").getResultList();
		return result;
	}
	
	@Transactional
	public CauseListType getCauseListTypesById(Long id) {
		
		
	CauseListType result = (CauseListType) em.createQuery("SELECT c FROM CauseListType c where c.clt_id =:id").setParameter("id", id).getSingleResult();
	
		return result;
	}
	
	@Transactional
	public Long findCaseType(String case_abbrivation,Long bench_code) 
	{
		
		Long case_type=0L;
		try {
			String query  ="SELECT ct.ct_id from CaseType ct WHERE ct.ct_label ='"+case_abbrivation+"' and ct.ct_bench_code= "+bench_code;
			case_type= (Long) em.createQuery(query).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return case_type;
	}
	
	@Transactional
	public Long findCauseListType(String list_name) 
	{
		
		Long list_id=0L;
		try {
			String query  ="SELECT clt.clt_id from CauseListType clt WHERE clt.clt_name ='"+list_name+"')";
			list_id=  (Long) em.createQuery(query).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list_id;
	}
	@Transactional
	public CauseListType findCauseListType(Long clt_id) 
	{
		
		CauseListType c=new CauseListType();
		try {
			String query  ="SELECT clt from CauseListType clt WHERE clt.clt_id =:clt_id";
			c=  (CauseListType) em.createQuery(query).setParameter("clt_id", clt_id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	
	@Transactional
	public Long findDocument(Long bench_code,Long case_type,Long case_no,Long case_year) 
	{
		
		Long document_id=0L;
		try {
			//Query query  =  em.createQuery("SELECT ct.ct_lk_mid from CaseType ct WHERE ct.ct_label =:case_abbrivation and ct.ct_bench_code= :bench_code ");
			//query.setParameter("case_abbrivation", case_abbrivation).setParameter("bench_code", bench_code);
			//document_id= (Long) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document_id;
	}
	
	@Transactional
	public List<CauseList> getList(CauseList causeList) {
		// TODO Auto-generated method stub
	
		List<CauseList> list=new ArrayList<CauseList>();
		String querystr="";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String cl_dol = formatter.format(causeList.getCl_dol());
		if(causeList.getCl_court_no()!=null)
		{
			querystr+=" AND c.cl_court_no="+causeList.getCl_court_no()+ " AND c.cl_bench_number="+causeList.getCl_bench_number();
		}
		if(causeList.getCl_list_type_mid()!=null)
		{	
			querystr+=" AND c.cl_list_type_mid="+causeList.getCl_list_type_mid();			
		}
		else {
			querystr+=" AND c.cl_list_type_mid in(5,6,7)";	
		}
		
		try {
			Query query  =  em.createQuery("SELECT c from CauseList c WHERE c.cl_dol ='"+cl_dol +"'"+querystr +" AND c.cl_rec_status=1 order by c.cl_court_no,c.cl_serial_no,c.cl_id");
			list= query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
		return list;
	}
	
	public List<CauseList> getCauseListForDownload(CauseList causeList) {
		// TODO Auto-generated method stub
	
		List<CauseList> list=new ArrayList<CauseList>();
		String querystr="";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String cl_dol = formatter.format(causeList.getCl_dol());
		if(causeList.getCl_court_no()!=null)
		{
			querystr+=" AND c.cl_court_no="+causeList.getCl_court_no();
		}
		if(causeList.getCl_list_type_mid()!=null)
		{	
			querystr+=" AND c.cl_list_type_mid="+causeList.getCl_list_type_mid();			
		}
		
		try {
			Query query  =  em.createQuery("SELECT c from CauseList c WHERE c.cl_dol ='"+cl_dol +"'"+querystr +" AND c.cl_rec_status=1 order by c.cl_list_type_mid,cl_serial_no,c.cl_id");
			list= query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
		return list;
	}
	
	@Transactional
	public Object getListReportCourtWise(CauseList causeList) {
		// TODO Auto-generated method stub
	
		CauseList clnew =new CauseList();
		
		Object clObject =null;
		String querystr="";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String cl_dol = formatter.format(causeList.getCl_dol());
		
		
		try {
		//	Query query  =  em.createQuery("SELECT c from CauseList c WHERE c.cl_dol ='"+cl_dol +"'"+querystr +" AND c.cl_rec_status=1 order by c.cl_court_no,c.cl_serial_no,c.cl_id");
			  
					
					Query query  =  em.createNativeQuery("select (select cm_name from court_master where cm_id = cl_court_no),(select clt_description from cause_list_type\r\n" + 
							"					where clt_id =cl_list_type_mid ), count(*),(select cm_judges_name from court_master where cm_id = cl_court_no) from cause_list  where cl_dol ='"+cl_dol +"' and cl_rec_status in(1,3) group by cl_court_no,cl_list_type_mid order by  \r\n" + 
							"					cl_court_no,cl_list_type_mid");
					clObject= query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
		return clObject;
	}
	
	
	
	
	@Transactional
	public List<CauseList> getListOnSearchForTransfer(CauseList causeList) {
		// TODO Auto-generated method stub
	
		List<CauseList> list=new ArrayList<CauseList>();
		String querystr="";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String cl_dol = formatter.format(causeList.getCl_dol());
		if(causeList.getCl_transfer_to() != null)
		{
			querystr+=" AND c.cl_transfer_to="+causeList.getCl_transfer_to();
		}
		if(causeList.getCl_list_type_mid()!=null)
		{	
			querystr+=" AND c.cl_list_type_mid="+causeList.getCl_list_type_mid();			
		}
		
		try {
			Query query  =  em.createQuery("SELECT c from CauseList c WHERE c.cl_dol ='"+cl_dol +"'"+querystr +" AND c.cl_rec_status=3 order by c.cl_court_no,c.cl_serial_no,c.cl_id");
			list= query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
		return list;
	}
	
	@Transactional
	public List<CauseList> getListForTransfer(CauseList causeList) {
		// TODO Auto-generated method stub
	
		List<CauseList> list=new ArrayList<CauseList>();
		String querystr="";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String cl_dol = formatter.format(causeList.getCl_dol());
		if(causeList.getCl_court_no()!=null)
		{
			querystr+=" AND c.cl_court_no="+causeList.getCl_court_no();
		}
		if(causeList.getCl_list_type_mid()!=null)
		{	
			querystr+=" AND c.cl_list_type_mid="+causeList.getCl_list_type_mid();			
		}
		
		try {
			Query query  =  em.createQuery("SELECT c from CauseList c WHERE c.cl_dol ='"+cl_dol +"'"+querystr +" AND c.cl_rec_status in (1,3) order by c.cl_serial_no,c.cl_id");
			list= query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
		return list;
	}
	
	@Transactional
	public List<CauseList> getList1(CauseList causeList) {
		// TODO Auto-generated method stub
		
		List<CauseList> list=new ArrayList<CauseList>();
		String querystr="";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String cl_dol = formatter.format(causeList.getCl_dol());
		if(causeList.getCl_court_no() != null) {
			querystr+=" AND c.cl_court_no="+causeList.getCl_court_no();
			
		}
		if(causeList.getCl_transfer_to()!=null)
		{
			querystr+=" AND c.cl_transfer_to="+causeList.getCl_transfer_to();
		}
		if(causeList.getCl_list_type_mid()!=null)
		{	
			querystr+=" AND c.cl_list_type_mid="+causeList.getCl_list_type_mid();			
		}
		
		try {
			Query query  =  em.createQuery("SELECT c from CauseList c WHERE c.cl_dol ='"+cl_dol +"'"+querystr +" AND c.cl_rec_status=3 order by c.cl_serial_no,c.cl_id");
			list= query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
		return list;
	}
	
	@Transactional
    public Integer updateCauseList(CauseList cl) {
		Integer causeList=0;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String cl_dol=formatter.format(cl.getCl_dol());
		try 
		{
			if(cl.getCl_list_type_mid()==null)
			{
			Query query=em.createQuery("update CauseList set cl_court_no='"+cl.getCl_court_no()+"',cl_mod_by='"+cl.getCl_mod_by()+"',cl_mod_date='"+cl.getCl_mod_date()+"' WHERE cl_dol ='"+cl_dol +"'");
			causeList = query.executeUpdate();
			}
			if(cl.getCl_list_type_mid()!=null)
			{
				Query query=em.createQuery("update CauseList set cl_court_no='"+cl.getCl_court_no()+"',cl_mod_by='"+cl.getCl_mod_by()+"',cl_mod_date='"+cl.getCl_mod_date()+"' WHERE cl_dol ='"+cl_dol +"' and cl_list_type_mid='"+cl.getCl_list_type_mid()+"'");
				causeList = query.executeUpdate();
			}
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return causeList;
    }
	
	
	@Transactional
	public List getListByType(CauseList causeList) throws ParseException {
		// TODO Auto-generated method stub
		List<Object> list = new  ArrayList<Object>();
		String querystr="";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		String cl_dol_date = formatter.format(new Date());
		String cl_dol =null;
		
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(cl_dol_date);
		
		if (causeList.getCl_dol() != date1) {
			cl_dol=formatter.format(causeList.getCl_dol());
		}
		else {
			cl_dol= formatter.format(new Date());
		}
		
		
		if(causeList.getCl_court_no()!=null)
		{
			querystr+=" AND c.cl_court_no="+causeList.getCl_court_no()+ " AND c.cl_bench_number="+causeList.getCl_bench_number();
		}
		try {
			Query query  =  em.createQuery("SELECT count(distinct cl_serial_no)as count,cl_list_type_mid from CauseList c WHERE c.cl_dol ='"+cl_dol +"'"+querystr +" and c.cl_rec_status=1 group by c.cl_list_type_mid");	
			list= query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;	
	}
	
	
	@Transactional
	public List getListByTypeForTransferCases(CauseList causeList) throws ParseException {
		// TODO Auto-generated method stub
		List<Object> list = new  ArrayList<Object>();
		String querystr="";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		String cl_dol_date = formatter.format(new Date());
		String cl_dol =null;
		
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(cl_dol_date);
		
		if (causeList.getCl_dol() != date1) {
			cl_dol=formatter.format(causeList.getCl_dol());
		}
		else {
			cl_dol= formatter.format(new Date());
		}
		if(causeList.getCl_transfer_to() != null) {
			querystr+=" AND c.cl_transfer_to="+causeList.getCl_transfer_to();
		}
		
		if(causeList.getCl_court_no()!=null)
		{
			querystr+=" AND c.cl_court_no="+causeList.getCl_court_no();
		}
		try {
			Query query  =  em.createQuery("SELECT count(cl_serial_no)as count,cl_list_type_mid ,c.cl_court_no from CauseList c WHERE c.cl_dol ='"+cl_dol +"'"+querystr +" and c.cl_rec_status=3 group by c.cl_list_type_mid , c.cl_court_no");	
			list= query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;	
	}
	
	public List getTransferCases(CauseList causeList) throws ParseException {
		// TODO Auto-generated method stub
		List<Object> list = new  ArrayList<Object>();
		String querystr="";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		String cl_dol_date = formatter.format(new Date());
		String cl_dol =null;
		
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(cl_dol_date);
		
		if (causeList.getCl_dol() != date1) {
			cl_dol=formatter.format(causeList.getCl_dol());
		}
		else {
			cl_dol= formatter.format(new Date());
		}
		
		
		if(causeList.getCl_court_no()!=null)
		{
			querystr+=" AND c.cl_transfer_to="+causeList.getCl_court_no();
		}
		try {
			Query query  =  em.createQuery("SELECT count(cl_serial_no)as count,cl_list_type_mid , cl_court_no from CauseList c WHERE c.cl_dol ='"+cl_dol +"'"+querystr +" and c.cl_rec_status=3 group by c.cl_court_no ,c.cl_list_type_mid");	
			list= query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;	
	}
	
	public List getTransferCasesCount(CauseList causeList) throws ParseException {
		// TODO Auto-generated method stub
		List<Object> list = new  ArrayList<Object>();
		String querystr="";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		String cl_dol_date = formatter.format(new Date());
		String cl_dol =null;
		
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(cl_dol_date);
		
		if (causeList.getCl_dol() != date1) {
			cl_dol=formatter.format(causeList.getCl_dol());
		}
		else {
			cl_dol= formatter.format(new Date());
		}
		
		
		if(causeList.getCl_court_no()!=null)
		{
			querystr+=" AND c.cl_transfer_to="+causeList.getCl_court_no();
		}
		try {
			Query query  =  em.createQuery("SELECT count(cl_serial_no)as count from CauseList c WHERE c.cl_dol ='"+cl_dol +"'"+querystr +" and c.cl_rec_status=3 ");	
			list= query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;	
	}
	
	
	@Transactional
	public List getListByTypeForAllCourts(CauseList causeList) throws ParseException {
		// TODO Auto-generated method stub
		List<Object> list = new  ArrayList<Object>();
		String querystr="";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		String cl_dol_date = formatter.format(new Date());
		String cl_dol =null;
		
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(cl_dol_date);
		
		if (causeList.getCl_dol() != date1) {
			cl_dol=formatter.format(causeList.getCl_dol());
		}
		else {
			cl_dol= formatter.format(new Date());
		}
		
		
		if(causeList.getCl_court_no()!=null)
		{
			querystr+=" AND c.cl_court_no="+causeList.getCl_court_no();
		}
		try {
			Query query  =  em.createQuery("SELECT count(cl_serial_no)as count,cl_list_type_mid , cl_court_no from CauseList c WHERE c.cl_dol ='"+cl_dol +"'"+querystr +" and c.cl_rec_status=1 group by c.cl_court_no , c.cl_list_type_mid");	
			list= query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;	
	}
	
	@Transactional
	public List getListByTypeCourtWise(CauseList causeList) throws ParseException {
		// TODO Auto-generated method stub
		List<Object> list = new  ArrayList<Object>();
		String querystr="";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		String cl_dol_date = formatter.format(new Date());
		String cl_dol =null;
		
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(cl_dol_date);
		
		if (causeList.getCl_dol() != date1) {
			cl_dol=formatter.format(causeList.getCl_dol());
		}
		else {
			cl_dol= formatter.format(new Date());
		}
		
		
		if(causeList.getCl_court_no()!=null)
		{
			querystr+=" AND c.cl_court_no="+causeList.getCl_court_no();
		}
		try {
			Query query  =  em.createQuery("SELECT count(cl_serial_no)as count,cl_list_type_mid from CauseList c WHERE c.cl_dol ='"+cl_dol +"'"+querystr +" and c.cl_rec_status=1 group by c.cl_list_type_mid");	
			list= query.getResultList();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		return list;	
	}
	
	public CauseList getByPk(Long cl_id) {
		// TODO Auto-generated method stub
		CauseList causelist=null;
		try {
			Query query  =  em.createQuery("SELECT c from CauseList c WHERE c.cl_id =:cl_id").setParameter("cl_id", cl_id);
			causelist= (CauseList) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return causelist;

	}
	
	public CauseList getByFDmid(Long cl_fd_mid) {
		// TODO Auto-generated method stub
		CauseList causelist=null;
		try {
			Query query  =  em.createQuery("SELECT c from CauseList c WHERE c.cl_fd_mid =:cl_fd_mid").setParameter("cl_fd_mid", cl_fd_mid);
			causelist= (CauseList) query.setMaxResults(1).getSingleResult();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return causelist;

	}
	
	public CauseList getByFDmidAndDate(Long cl_fd_mid, Date cl_dol) {
	
		CauseList causelist=null;
		try {
			Query query  =  em.createQuery("SELECT c from CauseList c WHERE c.cl_fd_mid =:cl_fd_mid and c.cl_dol =:cl_dol and c.cl_rec_status in(1,3)").setParameter("cl_fd_mid", cl_fd_mid).setParameter("cl_dol",cl_dol);
			causelist= (CauseList) query.setMaxResults(1).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return causelist;

	}
	
	public CauseList getNextCase(CauseList cl) {
		// TODO Auto-generated method stub
		
		System.out.println("Cause List "+cl);
		
		cl.setCl_serial_no(cl.getCl_serial_no()+1);
		
		System.out.println("Cause List "+cl);
		CauseList causelist=null;
		try {
			Query query  =  em.createQuery("SELECT c from CauseList c WHERE  c.cl_dol =:cl_dol and  c.cl_court_no=:cl_court_no and c.cl_rec_status=:cl_rec_status and c.cl_list_type_mid =:cl_list_type_mid and c.cl_serial_no=:cl_serial_no order by cl_id)").setParameter("cl_dol",cl.getCl_dol()).setParameter("cl_court_no",cl.getCl_court_no()).setParameter("cl_rec_status", cl.getCl_rec_status()).setParameter("cl_list_type_mid",cl.getCl_list_type_mid()).setParameter("cl_serial_no", cl.getCl_serial_no());
			causelist= (CauseList) query.setMaxResults(1).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return causelist;

	}
	
	
	public CauseList getPreviousCase(CauseList cl) {
		// TODO Auto-generated method stub
		
		System.out.println("Cause List "+cl);
		
		cl.setCl_serial_no(cl.getCl_serial_no()-1);
		
		System.out.println("Cause List "+cl);
		CauseList causelist=null;
		try {
			Query query  =  em.createQuery("SELECT c from CauseList c WHERE  c.cl_dol =:cl_dol and  c.cl_court_no=:cl_court_no and c.cl_rec_status=:cl_rec_status and c.cl_list_type_mid =:cl_list_type_mid and c.cl_serial_no=:cl_serial_no order by cl_id)").setParameter("cl_dol",cl.getCl_dol()).setParameter("cl_court_no",cl.getCl_court_no()).setParameter("cl_rec_status", cl.getCl_rec_status()).setParameter("cl_list_type_mid",cl.getCl_list_type_mid()).setParameter("cl_serial_no", cl.getCl_serial_no());
			causelist= (CauseList) query.setMaxResults(1).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return causelist;

	}
	
	public List<CauseList> getListForNextCase(CauseList cl) {
		// TODO Auto-generated method stub
		
		System.out.println("Cause List "+cl);
		
		//cl.setCl_serial_no(cl.getCl_serial_no()+1);
		List<CauseList> causelist=null;
		try {
			Query query  =  em.createQuery("SELECT c from CauseList c WHERE c.cl_serial_no > :cl_serial_no and c.cl_dol =:cl_dol and  c.cl_court_no=:cl_court_no and c.cl_rec_status=:cl_rec_status and c.cl_list_type_mid=:cl_list_type_mid order by c.cl_serial_no)").setParameter("cl_dol",cl.getCl_dol()).setParameter("cl_court_no",cl.getCl_court_no()).setParameter("cl_rec_status", cl.getCl_rec_status()).setParameter("cl_list_type_mid",cl.getCl_list_type_mid()).setParameter("cl_serial_no",cl.getCl_serial_no());
			causelist= (List<CauseList>) query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return causelist;

	}
	
	
	public List<CauseList> getListConnectedForNextCase(CauseList cl) {
		// TODO Auto-generated method stub
		
		System.out.println("Cause List "+cl);
		
		//cl.setCl_serial_no(cl.getCl_serial_no()+1);
		List<CauseList> causelist=null;
		try {
			Query query  =  em.createQuery("SELECT c from CauseList c WHERE c.cl_serial_no =:cl_serial_no and c.cl_dol =:cl_dol and  c.cl_court_no=:cl_court_no and c.cl_rec_status=:cl_rec_status and c.cl_list_type_mid=:cl_list_type_mid order by c.cl_id)").setParameter("cl_dol",cl.getCl_dol()).setParameter("cl_court_no",cl.getCl_court_no()).setParameter("cl_rec_status", cl.getCl_rec_status()).setParameter("cl_list_type_mid",cl.getCl_list_type_mid()).setParameter("cl_serial_no",cl.getCl_serial_no());
			causelist= (List<CauseList>) query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return causelist;

	}
	
	public List<CauseList> getListForPreviousCase(CauseList cl) {
		// TODO Auto-generated method stub
		
		System.out.println("Cause List "+cl);
		
		//cl.setCl_serial_no(cl.getCl_serial_no()+1);
		List<CauseList> causelist=null;
		try {
			Query query  =  em.createQuery("SELECT c from CauseList c WHERE c.cl_serial_no < :cl_serial_no and c.cl_dol =:cl_dol and  c.cl_court_no=:cl_court_no and c.cl_rec_status=:cl_rec_status and c.cl_list_type_mid=:cl_list_type_mid order by c.cl_serial_no desc)").setParameter("cl_dol",cl.getCl_dol()).setParameter("cl_court_no",cl.getCl_court_no()).setParameter("cl_rec_status", cl.getCl_rec_status()).setParameter("cl_list_type_mid",cl.getCl_list_type_mid()).setParameter("cl_serial_no",cl.getCl_serial_no());
			causelist= (List<CauseList>) query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return causelist;

	}
	
	public List<CauseList> getListConnectedForPriviousCase(CauseList cl) {
		// TODO Auto-generated method stub
		
		System.out.println("Cause List "+cl);
		
		//cl.setCl_serial_no(cl.getCl_serial_no()+1);
		List<CauseList> causelist=null;
		try {
			Query query  =  em.createQuery("SELECT c from CauseList c WHERE c.cl_serial_no = :cl_serial_no and c.cl_dol =:cl_dol and  c.cl_court_no=:cl_court_no and c.cl_rec_status=:cl_rec_status and c.cl_list_type_mid=:cl_list_type_mid order by c.cl_id)").setParameter("cl_dol",cl.getCl_dol()).setParameter("cl_court_no",cl.getCl_court_no()).setParameter("cl_rec_status", cl.getCl_rec_status()).setParameter("cl_list_type_mid",cl.getCl_list_type_mid()).setParameter("cl_serial_no",cl.getCl_serial_no());
			causelist= (List<CauseList>) query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return causelist;

	}
	
	
	
	
	public CauseList getNextCase1(CauseList cl ,String cl_dol1) {
		// TODO Auto-generated method stub
		
		System.out.println("Cause List "+cl);
		
		cl.setCl_serial_no(cl.getCl_serial_no()+1);
		CauseList causelist=null;
		try {
			Query query  =  em.createQuery("SELECT c from CauseList c WHERE c.cl_fd_mid =:cl_fd_mid and c.cl_dol ='"+cl_dol1 +"' and  c.cl_court_no=:cl_court_no and c.cl_rec_status=:cl_rec_status)").setParameter("cl_fd_mid",cl.getCl_fd_mid()).setParameter("cl_court_no",cl.getCl_court_no()).setParameter("cl_rec_status", cl.getCl_rec_status());
			causelist= (CauseList) query.setMaxResults(1).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return causelist;

	}
	
	@Transactional
	public List<CauseList> getSerialNoList2(CauseList causeList) {
		// TODO Auto-generated method stub
		List<CauseList> serialno = new  ArrayList<CauseList>();
		//List<BigInteger> serialno=null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String cl_dol = formatter.format(causeList.getCl_dol());
		Long list_type_mid= causeList.getCl_list_type_mid();
		int court_no=causeList.getCl_court_no();
		if(causeList.getCl_court_no()!=null)
		{
		}
		try {
			Query query  = em.createQuery("SELECT c from CauseList c  WHERE c.cl_list_type_mid='"+list_type_mid+"' and c.cl_dol ='"+cl_dol +"' and c.cl_court_no="+court_no+"");	
			 serialno = query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return serialno;	
	}
	
	
	@Transactional
	public Integer getSerialNoByListType(CauseList causeList) {
		// TODO Auto-generated method stub
		Integer serialno=null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String cl_dol = formatter.format(causeList.getCl_dol());
		Long list_type_mid= causeList.getCl_list_type_mid();
		int court_no=causeList.getCl_court_no();
		if(causeList.getCl_court_no()!=null)
		{
		}
		try {
			String query  =  "SELECT max(cl_serial_no) as cl_serial_no from cause_list  WHERE cl_list_type_mid='"+list_type_mid+"' and cl_dol ='"+cl_dol +"' and cl_court_no='"+court_no+"' group by cl_list_type_mid";	
			 serialno = (Integer)em.createNativeQuery(query).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return serialno;	
	}
	
	@Transactional
	public CauseList getListByAppYear(CauseList causeList) {
		// TODO Auto-generated method stub
		CauseList cllistobj=new CauseList();
		String querystr="";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String cl_dol = formatter.format(causeList.getCl_dol());
		if(causeList.getCl_court_no()!=null)
		{
			querystr+=" AND c.cl_court_no="+causeList.getCl_court_no();
		}
		if(causeList.getCl_list_type_mid()!=null)
		{	
			querystr+=" AND c.cl_list_type_mid="+causeList.getCl_list_type_mid();			
		}
		
		try {
				Query query  =  em.createQuery("SELECT c from CauseList c WHERE c.cl_dol ='"+cl_dol +"'"+querystr +" AND c.cl_ano="+causeList.getCl_ano() +" AND c.cl_ayr="+causeList.getCl_ayr() +" AND c.cl_fd_mid="+causeList.getCl_fd_mid() +"  AND c.cl_rec_status=1 order by c.cl_serial_no,c.cl_id");
				cllistobj= (CauseList) query.getSingleResult();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
		return cllistobj;
	}
	
	public Integer getPrioritywise(Long cl_fd_mid) {
		// TODO Auto-generated method stub
		Integer sequence=0;
		try {
			String query  ="SELECT c.cl_sequence from CauseList c order by c.cl_sequence desc";
			sequence= (Integer) em.createQuery(query).setMaxResults(1).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sequence;
	}
	
	@Transactional
    public PetitionerAdvocate savePetAdvocate(PetitionerAdvocate a) {    
		PetitionerAdvocate pa = null;
    	try {	
    		pa= em.merge(a);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return pa;
    }
	
	@Transactional
    public RespondentAdvocate saveResAdvocate(RespondentAdvocate a) {    
		RespondentAdvocate ra = null;
    	try {	
    		ra= em.merge(a);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();
		}
    	return ra;
    }
	@Transactional
	public void deleteCauseList(Date date) {
		// TODO Auto-generated method stub
		try {
			Query query  =  em.createQuery("DELETE  from CauseList c WHERE c.cl_dol < :cDate").setParameter("cDate", date);
			query.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Transactional
	public void deletePetitionerAdvocates() {
		// TODO Auto-generated method stub
		try {
			Query query  =  em.createQuery("DELETE  from PetitionerAdvocate");
			query.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Transactional
	public void deleteRespondentAdvocates() {
		// TODO Auto-generated method stub
		try {
			Query query  =  em.createQuery("DELETE  from RespondentAdvocate");
			query.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Transactional
	public CourtOrder saveCourtOrder(CourtOrder co) {
		// TODO Auto-generated method stub
		CourtOrder order = null;
    	try {	
    		order= em.merge(co);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();	    	
		}
    	return order;
	}

	public List<CourtOrder> getOrdersList(Integer cum_court_mid) {
		// TODO Auto-generated method stub
		List<CourtOrder> orders=new ArrayList<>();
		try {
			String query  ="SELECT co from CourtOrder co WHERE co.co_court_no =:co_court_no";
			orders=em.createQuery(query).setParameter("co_court_no", cum_court_mid).getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}
	
	public CourtOrder getCourtOrder(Long co_id) {
		// TODO Auto-generated method stub
		CourtOrder co=new CourtOrder();
		try {
			String query  ="SELECT c from CourtOrder c where c.co_id=:co_id";
			co=(CourtOrder) em.createQuery(query).setParameter("co_id", co_id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return co;
	}

	@Transactional
	public RespondentCounsel getRespondentCounsel(Long fd_id) {
		RespondentCounsel rc=null;
		
		try{
			String query  ="SELECT rc from RespondentCounsel rc where rc.rc_fd_mid=:rc_fd_mid";
			rc= (RespondentCounsel) em.createQuery(query).setParameter("rc_fd_mid", fd_id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rc;	
			
	}
	
	@Transactional
	public PetitionerCounsel getPetionerCounsel(Long fd_id) {
		PetitionerCounsel rc=null;
		
		try{
			String query  ="SELECT pc from PetitionerCounsel pc where pc.pc_fd_mid=:pc_fd_mid";
			rc= (PetitionerCounsel) em.createQuery(query).setParameter("pc_fd_mid", fd_id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rc;	
			
	}

	@Transactional
	public boolean delete(Date date) {
    		// TODO Auto-generated method stub
		boolean result=false;
  		try {
  			Query query  =  em.createQuery("DELETE  from CauseList c WHERE c.cl_dol =:cDate").setParameter("cDate", date);
  			query.executeUpdate();
  			result=true;
  		} catch (Exception e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
 
            return result;
}



	public CourtMaster getCourMaster(int parseInt) {
		Integer cm_id =Integer.valueOf(parseInt);
		CourtMaster co=null;
		try {
			String query  ="SELECT c from CourtMaster c where c.cm_id=:cm_id";
			co=(CourtMaster) em.createQuery(query).setParameter("cm_id", cm_id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return co;
	}



	

}