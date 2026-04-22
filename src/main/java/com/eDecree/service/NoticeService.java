package com.eDecree.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eDecree.model.ApplicationNotice;
import com.eDecree.model.CaseNotice;
import com.eDecree.model.CourtMaster;
import com.eDecree.model.DecreeExamDTO;
import com.eDecree.model.DecreeFileUploaded;
import com.eDecree.model.DecreeForm;
import com.eDecree.model.DecreeStage;
import com.eDecree.model.SubDocument;
import com.eDecree.model.User;
import com.eDecree.model.UserRole;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

@Service
public class NoticeService {

	@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	private EntityManager em;
	
	
	@PersistenceContext(unitName="persistenceUnitCIS")
	@Qualifier(value = "entityManagerFactoryCIS")
	private EntityManager emCIS;
	
	@Transactional
	public DecreeForm saveDecree(DecreeForm cm) {

		DecreeForm cmnew = null;
    	try {	
    		cmnew= em.merge(cm);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();	    	
		}
		return cmnew;
	}
	
	
	@Transactional
	public DecreeFileUploaded saveDecreeFile(DecreeFileUploaded cm) {

		DecreeFileUploaded cmnew = null;
    	try {	
    		cmnew= em.merge(cm);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();	    	
		}
		return cmnew;
	}
	
	@Transactional
	public DecreeStage saveDecreeStage(DecreeStage cm) {

		DecreeStage cmnew = null;
    	try {	
    		cmnew= em.merge(cm);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();	    	
		}
		return cmnew;
	}
	
	
	
	@Transactional
	public ApplicationNotice save(ApplicationNotice applicationNotice) {

		ApplicationNotice an = null;
    	try {	
    		an= em.merge(applicationNotice);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();	    	
		}
		return an;
	}
	
	@Transactional("transactionManager")
	public  List<Object[]>   getPetCivic(String label) {
		
		 List<Object[]>  data =null;
		 List<Map<String,Object>> res=null;
		
		String q= "select party_no,name from civ_address_t where type=1 and cino='"+label+"' order by party_no";
		
		try {
		Query query =emCIS.createNativeQuery(q);
		
		data = query.getResultList();
		
		if(data.size()==0) {
			 q= "select party_no,name from civ_address_t_a where type=1 and cino='"+label+"' order by party_no";
			 query =emCIS.createNativeQuery(q);
				
				data = query.getResultList();
		}
		}
		catch (Exception e) {
			System.out.println("ggggggggggggggggggggggg"+e);
			
		}
		
		return data;
	}
	
	@Transactional("transactionManager")
	public  List<Object[]>   getLowerTrial(String label) {
		
		 List<Object[]>  data =null;
		 List<Map<String,Object>> res=null;
		 
		 String q="select (select type_name from lcase_type_t where lcase_type =CAST(substring(lower_court, 2, 3) as smallint)),"
		 		+ "CAST(TRIM(substring(lower_court, 5, 7)) as integer),substring(lower_court, 12, 15)," + 
		 		"lower_judge_name,lower_court,(select dist_name from district_t  where dist_code=lower_dist_code),lower_court_dec_dt " + 
		 		"from trial_lower_court  where  cino='"+label+"' and length(lower_court)=15";
		
		/*String q= "select party_no,name from civ_address_t where type=1 and cino='"+label+"' order by party_no";*/
		
		try {
		Query query =emCIS.createNativeQuery(q);
		
		data = query.getResultList();
		
		/*if(data.size()==0) {
			 q= "select party_no,name from civ_address_t_a where type=1 and cino='"+label+"' order by party_no";
			 query =emCIS.createNativeQuery(q);
				
				data = query.getResultList();
		}*/
		}
		catch (Exception e) {
			System.out.println("ggggggggggggggggggggggg"+e);
			
		}
		
		return data;
	}
	
	@Transactional("transactionManager")
	public SubDocument getDecreePdf(Long id) {
		SubDocument decFile=null;
		
		String q="select sd from SubDocument sd where sd.sd_if_mid=44 and sd.sd_fd_mid="+id;
		
		try {
			Query query =em.createQuery(q);
			
			decFile = (SubDocument) query.getSingleResult();
			
			/*if(data.size()==0) {
				 q= "select party_no,name from civ_address_t_a where type=1 and cino='"+label+"' order by party_no";
				 query =emCIS.createNativeQuery(q);
					
					data = query.getResultList();
			}*/
			}
			catch (Exception e) {
				System.out.println("ggggggggggggggggggggggg"+e);
				
			}
		
		return decFile;
	}
	
	
	@Transactional("transactionManager")
	public  List<DecreeForm>   getDecreeExam(Long stage) {
		
		 List<DecreeForm>  data =null;
		
		
		
		try {
			String query  ="SELECT ct from DecreeForm ct where ct.df_stage_lid ="+stage;
			data=   (List<DecreeForm>) em.createQuery(query).getResultList();
		}
		catch (Exception e) {
			System.out.println("ggggggggggggggggggggggg"+e);
			
		}
		
		return data;
	}
	
	
	@Transactional("transactionManager")
	public List<DecreeExamDTO> getDecreeForExam(Long stage) {

	    List<DecreeExamDTO> data = new ArrayList<>();

	    try {
	        String query = "SELECT new com.eDecree.model.DecreeExamDTO(" +
	                "d.df_fd_mid, " +
	                "c.caseType.ct_label, " +
	                "c.fd_case_no, " +
	                "c.fd_case_year, " +
	                "d.df_remark, " +
	                "d.df_cr_date) " +
	                "FROM DecreeForm d " +
	                "JOIN d.caseFileDetail c " +
	                "WHERE d.df_assign_to = :stage  and d.df_rec_status = 1 " ;

	        data = em.createQuery(query, DecreeExamDTO.class)
	                .setParameter("stage", stage)
	                .getResultList();

	    } catch (Exception e) {
	        e.printStackTrace(); //  replace with logger if available
	    }

	    return data;
	}
	
	//================================================== VIJAY CHAURASIYA =======================================================
	//=============================================================================================================================
	
	
	@Transactional("transactionManager")
	public List<Object[]> getDecreeCaseStatus() {

	    List<Object[]> data = null;
	    String q = "SELECT DISTINCT " +
	            "d.df_id, d.df_fd_mid, ct.ct_label, c.fd_case_no, c.fd_case_year, " +
	            "d.df_remark, d.df_cr_date, d.df_stage_lid, s.ds_cr_by, um.um_fullname, s.ds_cr_date " +
	            "FROM public.decree_form d " +
	            "JOIN public.case_file_details c ON d.df_fd_mid = c.fd_id " +
	            "JOIN public.case_types ct ON c.fd_case_type = ct.ct_id " +
	            "JOIN public.decree_stage s ON s.ds_df_mid = d.df_id " +
	            "JOIN public.user_master um ON um.um_id = s.ds_cr_by " +
	            "WHERE d.df_rec_status = 1 " +
	            "ORDER BY s.ds_cr_date";
	    try {
	        Query query = em.createNativeQuery(q);
	        data = query.getResultList();
	    } catch (Exception e) {
	        System.out.println("Error in getDecreeData: " + e);
	    }

	    return data;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@Transactional("transactionManager")
	public  List<Object[]>   getResCivic(String label) {
		
		 List<Object[]>  data =null;
		 List<Map<String,Object>> res=null;
		
		String q= "select party_no,name from civ_address_t where type=2 and cino='"+label+"' order by party_no";
		
		try {
		Query query =emCIS.createNativeQuery(q);
		
		data = query.getResultList();
		
		
		}
		catch (Exception e) {
			System.out.println("ggggggggggggggggggggggg"+e);
			
		}
		
		return data;
	}
	@Transactional("transactionManager")
	public  Object[]   getCaseDetails(String label,Integer caseNo,Integer caseYear) {
		
		 Object[]  data =null;
		 List<Map<String,Object>> res=null;
		
		String q= "select c.cino,(select full_form from case_type_t where  case_type  = c.regcase_type) ,c.reg_no,c.reg_year,c.pet_name,c.res_name,c.date_of_filing,"
				+ "(select lower_court_dec_dt from trial_lower_court   where    cino=c.cino order by lower_trial desc limit 1),judge_code,pet_adv,"
				+ "(select adv_name from extra_adv_t  where cino=c.cino and type=2  order by sr_no desc limit 1),c.date_of_decision\r\n" + 
				"from civil_t_a c where c.reg_year=:caseYear and c.reg_no=:caseNo and c.regcase_type= (select case_type  from case_type_t where  type_name  =:caseType)";
		
		try {
		Query query =emCIS.createNativeQuery(q).setParameter("caseType", label).setParameter("caseNo", caseNo).setParameter("caseYear", caseYear);
		
		data = (Object[]) query.getSingleResult();
		
		
		}
		catch (Exception e) {
			System.out.println("ggggggggggggggggggggggg"+e);
			
		}
		
		return data;
	}
	
	
	@Transactional("transactionManager")
	public  List<Object>   getJudgeName(String code) {
		
		 List<Object>   data =null;
		 List<Map<String,Object>> res=null;
		
		String q= "select judge_name from judge_name_t where judge_code  in("+code+")";
		
		try {
		Query query =emCIS.createNativeQuery(q);
		
		data = query.getResultList();
		
		
		}
		catch (Exception e) {
			System.out.println("ggggggggggggggggggggggg"+e);
			
		}
		
		return data;
	}
	
	public DecreeForm getByFd(Long df_fd_mid) {
		// TODO Auto-generated method stub
		DecreeForm decreeForm=null;
		try {
			Query query=em.createQuery("SELECT d FROM DecreeForm d where d.df_fd_mid=:df_fd_mid").setParameter("df_fd_mid",df_fd_mid);
			decreeForm= (DecreeForm) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}finally{
			return decreeForm;	
		}

	}
	/*@Transactional
	public DecreeForm getDecreeForm2(Long cm_bench_id) {
		DecreeForm cm =null;
		try {
			String query  ="SELECT ct from DecreeForm ct where ct.df_fd_mid =:cm_bench_id and ct.df_rec_status";
			cm=   (DecreeForm) em.createQuery(query).setParameter("cm_bench_id",cm_bench_id).getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cm;
	}*/
	
	
	
	/*
	 * @Transactional public List<DecreeStage> getDecreeStage(Long id) {
	 * List<DecreeStage> cm =null; try { String query
	 * ="SELECT ct from DecreeStage ct where ct.ds_cr_by =:id"; cm=
	 * (List<DecreeStage>)
	 * em.createQuery(query).setParameter("id",id).getResultList();
	 * System.out.println("dddddddddddddddd stage---!!!!!!!!!!========" +cm);
	 * 
	 * } catch (Exception e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } return cm; }
	 */
	 
	
	/*
	 * @Transactional public List<DecreeStage> getDecreeStage(Long fdMid) {
	 * List<DecreeStage> result = null; try { String query =
	 * "SELECT u.um_fullname, ds.ds_stage_lid, ds.ds_cr_date " +
	 * "FROM decree_stage ds " + "JOIN user_master u ON u.um_id = ds.ds_cr_by " +
	 * "WHERE ds.ds_df_mid IN ( " +
	 * "SELECT df_id FROM decree_form WHERE df_fd_mid = :fdMid " + ") " +
	 * "AND ds.ds_stage_lid > 4000 " + "ORDER BY ds.ds_cr_date DESC "; // "LIMIT 1";
	 * 
	 * result = em.createQuery(query) .setParameter("fdMid", fdMid) //
	 * .setMaxResults(1) .getResultList();
	 * System.out.println("resulttttttt stage======" +result); } catch (Exception e)
	 * { e.printStackTrace(); }
	 * 
	 * return result; }
	 */
	 
	 @Transactional public List<DecreeStage> getDecreeStage(Long fdMid) {	  
		  List<DecreeStage> result = null;	  
		  try {	  
		  String query = "SELECT ds " +
	               "FROM DecreeStage ds, DecreeForm df " +
	               "WHERE df.df_id = ds.ds_df_mid " +
	               "AND df.df_fd_mid = :fdMid " +
	               "AND ds.ds_stage_lid > 4001 " +
	               "AND ds.ds_stage_lid > 4007 " +
	               "ORDER BY ds.ds_cr_date DESC";
		  
		  result = em.createQuery(query) .setParameter("fdMid", fdMid) 
		  .getResultList();
		  System.out.println("resulttttttt stage======" +result);
		  }
		  catch (Exception e)
		  {
			  e.printStackTrace();
			  }
		  
		  return result; 
		  }
	
	
	
	@Transactional
	public DecreeForm getDecreeForm(Long cm_bench_id) {
		DecreeForm cm =null;
		try {
			String query  ="SELECT ct from DecreeForm ct where ct.df_fd_mid =:cm_bench_id and ct.df_rec_status = 1";
			cm=   (DecreeForm) em.createQuery(query).setParameter("cm_bench_id",cm_bench_id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cm;
	}
	
	@Transactional
	public DecreeFileUploaded getDecreeFile(Long cm_bench_id) {
		DecreeFileUploaded cm =null;
		try {
			String query  ="SELECT ct from DecreeFileUploaded ct where ct.dfu_id =:cm_bench_id";
			cm=   (DecreeFileUploaded) em.createQuery(query).setParameter("cm_bench_id",cm_bench_id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cm;
	}
	
	@Transactional
	public List<DecreeFileUploaded> getDecreeFile1(Long fd_id) {
		List<DecreeFileUploaded> cm =null;
		try {
			String query  ="SELECT ct from DecreeFileUploaded ct where ct.dfu_fd_mid =:fd_id and ct.dfu_rec_status = 1";
			cm=   (List<DecreeFileUploaded>) em.createQuery(query).setParameter("fd_id",fd_id).getSingleResult();
			System.out.println("dddddddddddddddd!!!!!!!!!!========" +cm);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cm;
	}
	
	
	
	@Transactional
	public Integer getDecreefileCount(Long cm_bench_id) {
		Integer cm =0;
		Long count=0L;
		try {
			String query  ="SELECT count(dfu_rec_status) from DecreeFileUploaded where dfu_df_mid =:cm_bench_id and dfu_rec_status=1";
			count=    (Long) em.createQuery(query).setParameter("cm_bench_id",cm_bench_id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cm=count.intValue();
		return cm;
	}
	
	
	/*@Transactional
	public DecreeForm getDecreeUser(Long id) {
		DecreeForm cm =null;
		try {
			String query  ="SELECT ct from DecreeForm ct where ct.df_fd_mid =:id";
			cm=   (DecreeForm) em.createQuery(query).setParameter("id",id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cm;
	}*/
	
	@Transactional
	public DecreeForm getDecreeUser(Long id) {
		DecreeForm cm =null;
		try {
			String query  ="SELECT ct from DecreeForm ct where ct.df_fd_mid =:id and ct.df_rec_status=1";
			cm=   (DecreeForm) em.createQuery(query).setParameter("id",id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cm;
	}
	
	
	@Transactional
	public DecreeStage getReturnDecreeStage(Long id) {
		DecreeStage cm =null;
		try {
			String query  ="SELECT dc from DecreeStage dc where ds_df_mid in (select df_id from DecreeForm  where df_fd_mid = "+id+") " +"and ds_stage_lid = 4000L";
			cm=   (DecreeStage) em.createQuery(query).setMaxResults(1).getSingleResult();
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cm;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> getDecreeCreator(Long id) {
		List<User> cm =null;
		try {
			/*String query  ="SELECT ct from User ct where um_id in (select ur_um_mid from UserRole where ur_role_id in(351456L,351455L) "
					+ "and um_id not in(select ds_cr_by from DecreeStage where ds_df_mid ="+id+"))";*/
			
			String query  ="SELECT ct from User ct where um_id in (select ur_um_mid from UserRole where ur_role_id in(351456L,351455L) "
					+ ")";
			cm=   (List<User>) em.createQuery(query).getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cm;
	}
		
	
	@Transactional
	public CaseNotice save(CaseNotice caseNotice) {

		CaseNotice cn = null;
    	try {	
    		cn= em.merge(caseNotice);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();	    	
		}
		return cn;
	}
	
	/*@Transactional
	public DecreeForm updateDecreeRemark(Long id,String remark) {
		DecreeForm old=null;
		DecreeForm app =null;
   	       old=em.find(DecreeForm.class, id);
   		  old.setDf_remark(remark);
   		 
   	      app=em.merge(old);
		return app; 
	}*/
	
	@Transactional
	public DecreeForm updateDecreeForm(DecreeForm dfs) {
	
		DecreeForm app =null;
   	      
   	      app=em.merge(dfs);
		return app; 
	}
	

	
}
