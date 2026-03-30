package com.eDecree.service;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.eDecree.model.DecreeSmsLog;
import com.eDecree.model.ExtraAdvocateForSms;

@Service
public class SendSmsService {
	
	@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	private EntityManager em;
	
	
	
	public String sendBSNLSMS(String url,String mob_no,String smstext,String tmpid)	
	
	{
		String result=null;
	    RestTemplate restTemplate=new RestTemplate();
//	    String text=url+"?validmob="+mob_no+"&validmsg="+smstext+"&tmpid="+tmpid;
	   /* String text="https://vsms.minavo.in/api/singlesms.php?auth_key=92faefae-ae33-4921-a161-5c9bb103a7db&mobilenumber="+mob_no+"&message="+smstext+"- Allahabad High Court&sid=HCALLD&mtype=N&template_id="+tmpid;*/
	    String text="http://103.234.185.173/api/swsendnk.asp?username=HCALLD&pass=4yFqGgEwjbzC&sender=HCALLD&sendto="+mob_no+"&templateID="+tmpid+"&message="+smstext;
	    
	    System.out.println("url :"+text);
	    result=restTemplate.getForObject(url+"?validmob="+mob_no+"&validmsg="+smstext+"&tmpid="+tmpid, String.class);
	   /* result=restTemplate.getForObject("https://vsms.minavo.in/api/singlesms.php?auth_key=92faefae-ae33-4921-a161-5c9bb103a7db&mobilenumber="+mob_no+"&message="+smstext+"- Allahabad High Court&sid=HCALLD&mtype=N&template_id="+tmpid, String.class);
	   */  result=restTemplate.getForObject("http://103.234.185.173/api/swsendnk.asp?username=HCALLD&pass=4yFqGgEwjbzC&sender=HCALLD&sendto="+mob_no+"&templateID="+tmpid+"&message="+smstext, String.class);
	      if(result.contains("message sent successfully")) {
	    	result="1";
	    }
	    else {
	    	result="0";
	    }
	    return result;
	}
	
	@Transactional
	public DecreeSmsLog saveDecreeSmsLog(DecreeSmsLog dsl) {

	    System.out.println(">>> saveDecreeSmsLog() CALLED");
	    System.out.println("DSL Object = " + dsl);

	    DecreeSmsLog dslResult = null;
	    try {
	        dslResult = em.merge(dsl);
	        System.out.println(">>> SMS LOG SAVED"+dslResult);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return dslResult;
	}

	@Transactional
	public List<DecreeSmsLog> getAllSmsLog() {
	    List<DecreeSmsLog> dslResponse = new ArrayList<>();
	    try {
	        String query = "SELECT dl FROM DecreeSmsLog dl";
	        dslResponse = em.createQuery(query, DecreeSmsLog.class)
	                        .getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return dslResponse;
	}

	
	// Get send sms by  casetype , caseno and  caseyear
	/*
	 * @Transactional(readOnly = true) public List<DecreeSmsLog>
	 * getSentSmsByCaseDetails( String caseType, String caseNo, Integer caseYear) {
	 * 
	 * return em.createQuery( "SELECT d FROM DecreeSmsLog d " +
	 * "WHERE d.dslCaseType = :caseType " + "AND d.dslCaseNo = :caseNo " +
	 * "AND d.dslCaseYear = :caseYear " + "AND d.dslSmsStatus = true",
	 * DecreeSmsLog.class) .setParameter("caseType", caseType)
	 * .setParameter("caseNo", caseNo) .setParameter("caseYear", caseYear)
	 * .getResultList(); }
	 */

	
	// Get send sms by  casetype , caseno and  caseyear
		@Transactional(readOnly = true)
		public List<DecreeSmsLog> getSentSmsByCaseDetails(
		        String caseType,
		        String caseNo,
		        Integer caseYear) {

		    return em.createQuery(
		            "SELECT d FROM DecreeSmsLog d " +
		            "WHERE d.dslCaseType = :caseType " +
		            "AND d.dslCaseNo = :caseNo " +
		            "AND d.dslCaseYear = :caseYear " +
		            "AND d.dslSmsStatus = true",
		            DecreeSmsLog.class)
		        .setParameter("caseType", caseType)
		        .setParameter("caseNo", caseNo)
		        .setParameter("caseYear", caseYear)
		        .getResultList();
		}

		// ====================== method to save and get Extra Advocate For Sms ============================
		@Transactional
		public ExtraAdvocateForSms saveExtraAdvocate(ExtraAdvocateForSms adv) {

		    System.out.println(">>> saveExtraAdvocate() CALLED");
		    System.out.println("ExtraAdvocate Object = " + adv);

		    ExtraAdvocateForSms result = null;

		    try {
		        result = em.merge(adv);   //  handles both insert & update
		        System.out.println(">>> EXTRA ADVOCATE SAVED: " + result);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return result;
		}
		
		
		@Transactional
		public boolean isMobileExists(String mobile, String caseType, String caseNo, String caseYear) {

		    Long count = em.createQuery(
		            "SELECT COUNT(e) FROM ExtraAdvocateForSms e " +
		            "WHERE e.mobile = :mobile " +
		            "AND e.case_type = :caseType " +
		            "AND e.case_no = :caseNo " +
		            "AND e.case_year = :caseYear",
		            Long.class)
		        .setParameter("mobile", mobile)
		        .setParameter("caseType", caseType)
		        .setParameter("caseNo", caseNo)
		        .setParameter("caseYear", caseYear)
		        .getSingleResult();

		    return count > 0;
		}
		
		
		@Transactional
		public List<ExtraAdvocateForSms> getExtraAdvocates(
		        String caseType,
		        String caseNo,
		        String caseYear) {

		    return em.createQuery(
		            "SELECT e FROM ExtraAdvocateForSms e " +
		            "WHERE e.case_type = :caseType " +
		            "AND e.case_no = :caseNo " +
		            "AND e.case_year = :caseYear",
		            ExtraAdvocateForSms.class)
		        .setParameter("caseType", caseType)
		        .setParameter("caseNo", caseNo)
		        .setParameter("caseYear", caseYear)
		        .getResultList();
		}
		
		
		
		@Transactional
		public void deleteExtraAdvocate(Long id) {

		    ExtraAdvocateForSms adv = em.find(ExtraAdvocateForSms.class, id);

		    if (adv != null) {
		        em.remove(adv);
		        System.out.println("Deleted ExtraAdvocate ID: " + id);
		    }
		}
	
	
		
	

}
