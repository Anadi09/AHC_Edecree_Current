package com.eDecree.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eDecree.model.CaseFileDetail;
import com.eDecree.model.ImpugnedOrder;
import com.eDecree.model.MetaData;
import com.eDecree.model.Petitioner;
import com.eDecree.model.PetitionerCounsel;
import com.eDecree.model.RegularToDefective;
import com.eDecree.model.Respondent;
import com.eDecree.model.RespondentCounsel;
import com.efiling.model.EfilingCaseFileDetail;

@Service
public class LKOCaseFileDetailService {

	@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	private EntityManager em;
	
	@PersistenceContext(unitName="persistenceUnitEfiling")
	@Qualifier(value = "entityManagerFactoryEfiling")
	private EntityManager em2;
	
	@PersistenceContext(unitName="persistenceUnitLKODMS")
	@Qualifier(value = "entityManagerFactoryLKODMS")
	private EntityManager lko2;
	
	@Transactional
	public CaseFileDetail getCaseFileDetail(Long id) {

		CaseFileDetail result=new CaseFileDetail();
	    Query query=null;
		query = em.createQuery("SELECT c from CaseFileDetail c where c.fd_id=:id").setParameter("id", id);
		result=(CaseFileDetail) query.getSingleResult();
		
		return result;
	}
	@Transactional
	public CaseFileDetail save(CaseFileDetail casefile) {

		CaseFileDetail cfd = null;
    	try {	
    		cfd= em.merge(casefile);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();	    	
		}
		return cfd;
	}
	@Transactional("transactionManagerEfiling")
	public EfilingCaseFileDetail saveCaseFile(EfilingCaseFileDetail casefile) {
		EfilingCaseFileDetail cfd = null;
    	try {	
    		cfd= em2.merge(casefile);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();	    	
		}
		return cfd;
	}
	@Transactional
	public EfilingCaseFileDetail getEfilingCaseFileDetail(Long casetypeId,String caseNo,Integer caseYear) {
		EfilingCaseFileDetail cfd = null;
		try {
			cfd = (EfilingCaseFileDetail) em2.createQuery("SELECT c FROM EfilingCaseFileDetail c where c.fd_case_type=:fd_case_type and c.fd_case_no=:fd_case_no and c.fd_case_year=:fd_case_year")
					.setParameter("fd_case_type", casetypeId).setParameter("fd_case_no", caseNo).setParameter("fd_case_year", caseYear)
					.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return cfd;
	}
	
	// Session session=em.unwrap(Session.class);
	

	
	// Session session=em.unwrap(Session.class);
	public List<CaseFileDetail> getCaseFilesListLKO(CaseFileDetail casefile) {
		// TODO Auto-generated method stub
		List<CaseFileDetail> result = lko2.createQuery("SELECT c FROM CaseFileDetail c where c.fd_case_type=:fd_case_type and c.fd_case_no=:fd_case_no and c.fd_case_year=:fd_case_year")
				.setParameter("fd_case_type", casefile.getFd_case_type()).setParameter("fd_case_no", casefile.getFd_case_no()).setParameter("fd_case_year", casefile.getFd_case_year())
				.getResultList();

		return result;
	}
	
	public CaseFileDetail getCaseFilelko(CaseFileDetail casefile) {
		// TODO Auto-generated method stub
		CaseFileDetail result = null;
		try {
			result = (CaseFileDetail) lko2.createQuery("SELECT c FROM CaseFileDetail c where c.fd_case_type=:fd_case_type and c.fd_case_no=:fd_case_no and c.fd_case_year=:fd_case_year")
					.setParameter("fd_case_type", casefile.getFd_case_type()).setParameter("fd_case_no", casefile.getFd_case_no()).setParameter("fd_case_year", casefile.getFd_case_year())
					.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}

		return result;
	}
	@Transactional
	public List<MetaData> getMetadata(Long fd_id) {
		// TODO Auto-generated method stub
		List<MetaData> result =new ArrayList<MetaData>();
		
		try {
			result=em.createQuery("select md from MetaData md where md_fd_mid=:md_fd_mid AND md_rec_status=1 order by md_sequence asc").setParameter("md_fd_mid", fd_id).getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	public ImpugnedOrder getImpugnedOrder(Long ioId) {
		// TODO Auto-generated method stub
		ImpugnedOrder result=new ImpugnedOrder();
	    Query query=null;
		try {
			query = em.createQuery("SELECT i from ImpugnedOrder i where i.io_id=:id").setParameter("id", ioId);
			result=(ImpugnedOrder) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		
		return result;
	}
	public CaseFileDetail getCaseFileDetail(Long io_hc_case_type, String io_case_no, Integer io_case_year) {
		// TODO Auto-generated method stub
		CaseFileDetail result = new CaseFileDetail();
		try {
			result = (CaseFileDetail) em.createQuery("SELECT c FROM CaseFileDetail c where c.fd_case_type=:fd_case_type and c.fd_case_no=:fd_case_no and c.fd_case_year=:fd_case_year")
					.setParameter("fd_case_type", io_hc_case_type).setParameter("fd_case_no", io_case_no).setParameter("fd_case_year", io_case_year)
					.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return result;
	}
	public Petitioner getPetitioner(Long id) {
		// TODO Auto-generated method stub
		Petitioner pet=null;
		try {
			pet=(Petitioner) em.createQuery("SELECT pet from Petitioner pet where pet.pt_id=:id").setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pet;
	}
	@Transactional
	public Petitioner save(Petitioner pet) {
		// TODO Auto-generated method stub
		Petitioner petitioner = null;
    	try {	
    		petitioner= em.merge(pet);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();	    	
		}
		return petitioner;
	}
	public Respondent getRespondent(Long id) {
		// TODO Auto-generated method stub
		Respondent ret=null;
		try {
			ret=(Respondent) em.createQuery("SELECT ret from Respondent ret where ret.rt_id=:id").setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	@Transactional
	public Respondent save(Respondent ret) {
		// TODO Auto-generated method stub
		Respondent respondent = null;
    	try {	
    		respondent= em.merge(ret);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();	    	
		}
		return respondent;
	}
	public PetitionerCounsel getPetitionerCounsel(Long id) {
		// TODO Auto-generated method stub
		PetitionerCounsel pet=null;
		try {
			pet=(PetitionerCounsel) em.createQuery("SELECT pc from PetitionerCounsel pc where pc.pc_id=:id").setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pet;
	}
	@Transactional
	public PetitionerCounsel save(PetitionerCounsel pc) {
		// TODO Auto-generated method stub
		PetitionerCounsel counsel = null;
    	try {	
    		counsel= em.merge(pc);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();	    	
		}
		return counsel;
	}
	public RespondentCounsel getRespondentCounsel(Long id) {
		// TODO Auto-generated method stub
		RespondentCounsel rc=null;
		try {
			rc=(RespondentCounsel) em.createQuery("SELECT rc from RespondentCounsel rc where rc.rc_id=:id").setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rc;
	}
	
	@Transactional
	public RespondentCounsel save(RespondentCounsel rc) {
		// TODO Auto-generated method stub
		RespondentCounsel counsel = null;
    	try {	
    		counsel= em.merge(counsel);	    	
	    }catch (Exception e) {		
	    	e.printStackTrace();	    	
		}
		return counsel;
	}
	public List<Petitioner> getPetitionerList(Long fd_id) {
		// TODO Auto-generated method stub
		List<Petitioner> result =new ArrayList<Petitioner>();
		
		try {
			result=em.createQuery("select p from Petitioner p where pt_fd_mid=:pt_fd_mid AND pt_rec_status=1").setParameter("pt_fd_mid", fd_id).getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;

	}
	public List<Respondent> getRespondentList(Long fd_id) {
		// TODO Auto-generated method stub
		List<Respondent> result =new ArrayList<Respondent>();
		
		try {
			result=em.createQuery("select rt from Respondent rt where rt_fd_mid=:rt_fd_mid AND rt_rec_status=1").setParameter("rt_fd_mid", fd_id).getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;

	}
	public List<PetitionerCounsel> getPetitionerCouselList(Long fd_id) {
		// TODO Auto-generated method stub
		List<PetitionerCounsel> result =new ArrayList<PetitionerCounsel>();
		
		try {
			result=em.createQuery("select pc from PetitionerCounsel pc where pc_fd_mid=:pc_fd_mid AND pc_rec_status=1").setParameter("pc_fd_mid", fd_id).getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;

	}
	public List<RespondentCounsel> getRespondentCouselList(Long fd_id) {
		// TODO Auto-generated method stub
		List<RespondentCounsel> result =new ArrayList<RespondentCounsel>();
		
		try {
			result=em.createQuery("select rc from RespondentCounsel rc where rc_fd_mid=:rc_fd_mid AND rc_rec_status=1 ").setParameter("rc_fd_mid", fd_id).getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;

	}
	public List<ImpugnedOrder> getImpungedList(Long fd_id) {
		// TODO Auto-generated method stub
		List<ImpugnedOrder> result =new ArrayList<ImpugnedOrder>();
		
		try {
			result=em.createQuery("select io from ImpugnedOrder io where io_fd_mid=:io_fd_mid AND io_rec_status=1").setParameter("io_fd_mid", fd_id).getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;

	}
	@Transactional
	public void saveRegularToDefective(RegularToDefective rdh) {
		
		try{
		em.merge(rdh);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	@Transactional
	public Petitioner getPetitionerBycase(Long fd_id) {

		Petitioner pet=null;
		try {
			pet=(Petitioner) em.createQuery("SELECT pet from Petitioner pet where pet.pt_fd_mid=:id and pet.pt_rec_status=1 and pet.pt_sequence=1").setParameter("id", fd_id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pet;
	}
	@Transactional
	public Respondent getRespondentBycase(Long fd_id) {

		Respondent ret=null;
		try {
			ret=(Respondent) em.createQuery("SELECT ret from Respondent ret where ret.rt_fd_mid=:id and ret.rt_rec_status=1 and ret.rt_sequence=1").setParameter("id", fd_id).getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;

		
		
		
	}

		
	
	
	
	
	}
	

