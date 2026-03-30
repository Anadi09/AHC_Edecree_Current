package com.eDecree.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.eDecree.model.CaseFileDetail;
import com.eDecree.model.Search;

@Service
public class SearchService {
	@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	private EntityManager em;
	
	public List<CaseFileDetail> getByCaseDetail(Search search) {
		String query="";
		if(search.getCaseNo()!=null && !search.getCaseNo().equals("")){
			query+=" AND c.fd_case_no='"+search.getCaseNo()+"'";
		}
		if(search.getCaseTypeId()!=null){
			query+=" AND c.fd_case_type="+search.getCaseTypeId();
		}
		if(search.getCaseYear()!=null && !search.getCaseYear().toString().equals("")){
			query+=" AND c.fd_case_year="+search.getCaseYear();
		}
		List<CaseFileDetail> result = em.createQuery("SELECT c FROM CaseFileDetail c where c.fd_rec_status=1"+query)
				.setFirstResult((search.getPageNumber()-1) * search.getItemsPerPage()).setMaxResults(search.getItemsPerPage()).getResultList();

		return result;
	}
	public Integer getByCaseDetailCount(Search search) {
		// TODO Auto-generated method stub
		Integer count=0;
		String queryString="";
		if(search.getCaseNo()!=null && !search.getCaseNo().equals("")){
			queryString+=" AND c.fd_case_no='"+search.getCaseNo()+"'";
		}
		if(search.getCaseTypeId()!=null){
			queryString+=" AND c.fd_case_type="+search.getCaseTypeId();
		}
		if(search.getCaseYear()!=null && !search.getCaseYear().toString().equals("")){
			queryString+=" AND c.fd_case_year="+search.getCaseYear();
		}
		try {			
			String query = "select count(c) from CaseFileDetail c where c.fd_rec_status=1 "+queryString;
			count= Integer.parseInt(em.createQuery(query).getSingleResult().toString());			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return count;
	}
	public List<CaseFileDetail> getByParty(Search search) {
		String query="";
		List<CaseFileDetail> result=new ArrayList<>();
		if(search.getCaseNo()!=null && !search.getCaseNo().equals("")){
			query+=" AND c.fd_case_no='"+search.getCaseNo()+"'";
		}
		if(search.getCaseTypeId()!=null){
			query+=" AND c.fd_case_type="+search.getCaseTypeId();
		}
		if(search.getCaseYear()!=null && !search.getCaseYear().toString().equals("")){
			query+=" AND c.fd_case_year="+search.getCaseYear();
		}
		if(search.getPartyType().equals("pr")){
			query+=" AND ( "
					+ "c.fd_id IN (select pt.pt_fd_mid from Petitioner pt where lower(pt.pt_name) like '%"+search.getPartyName().toLowerCase()+"%')"
					+ " OR c.fd_id IN (select rt.rt_fd_mid from Respondent rt where lower(rt.rt_name) like '%"+search.getPartyName().toLowerCase()+"%') )";
		}
		else if(search.getPartyType().equals("p")){
			query+=" AND c.fd_id IN (select pt.pt_fd_mid from Petitioner pt where lower(pt.pt_name) like '%"+search.getPartyName().toLowerCase()+"%')";
		}else{
			query+=" AND c.fd_id IN (select rt.rt_fd_mid from Respondent rt where lower(rt.rt_name) like '%"+search.getPartyName().toLowerCase()+"%')";
		}
		result = em.createQuery("SELECT c FROM CaseFileDetail c where c.fd_rec_status=1"+query)
				.setFirstResult((search.getPageNumber()-1) * search.getItemsPerPage()).setMaxResults(search.getItemsPerPage()).getResultList();
		

		return result;
	}
	public Integer getByPartyCount(Search search) {
		// TODO Auto-generated method stub
		Integer count=0;
		String queryString="";
		if(search.getCaseNo()!=null && !search.getCaseNo().equals("")){
			queryString+=" AND c.fd_case_no='"+search.getCaseNo()+"'";
		}
		if(search.getCaseTypeId()!=null){
			queryString+=" AND c.fd_case_type="+search.getCaseTypeId();
		}
		if(search.getCaseYear()!=null && !search.getCaseYear().toString().equals("")){
			queryString+=" AND c.fd_case_year="+search.getCaseYear();
		}
		if(search.getPartyType().equals("pr")){
			queryString+=" AND ( "
					+ "c.fd_id IN (select pt.pt_fd_mid from Petitioner pt where lower(pt.pt_name) like '%"+search.getPartyName().toLowerCase()+"%')"
					+ " OR c.fd_id IN (select rt.rt_fd_mid from Respondent rt where lower(rt.rt_name) like '%"+search.getPartyName().toLowerCase()+"%') )";
		}
		else if(search.getPartyType().equals("p")){
			queryString+=" AND c.fd_id IN (select pt.pt_fd_mid from Petitioner pt where lower(pt.pt_name) like '%"+search.getPartyName().toLowerCase()+"%')";
		}else{
			queryString+=" AND c.fd_id IN (select rt.rt_fd_mid from Respondent rt where lower(rt.rt_name) like '%"+search.getPartyName().toLowerCase()+"%')";
		}
		try {			
			String query = "select count(c) from CaseFileDetail c where c.fd_rec_status=1 "+queryString;
			count= Integer.parseInt(em.createQuery(query).getSingleResult().toString());			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return count;
	}
	public List<CaseFileDetail> getByCounsel(Search search) {
		String query="";
		List<CaseFileDetail> result=new ArrayList<>();
		if(search.getCaseNo()!=null && !search.getCaseNo().equals("")){
			query+=" AND c.fd_case_no='"+search.getCaseNo()+"'";
		}
		if(search.getCaseTypeId()!=null){
			query+=" AND c.fd_case_type="+search.getCaseTypeId();
		}
		if(search.getCaseYear()!=null && !search.getCaseYear().toString().equals("")){
			query+=" AND c.fd_case_year="+search.getCaseYear();
		}
		if(search.getCounselType().equals("pr")){
			query+=" AND ( "
					+ "c.fd_id IN (select pc.pc_fd_mid from PetitionerCounsel pc where lower(pc.pc_name) like '%"+search.getCounselName().toLowerCase()+"%')"
					+ " OR c.fd_id IN (select rc.rc_fd_mid from RespondentCounsel rc where lower(rc.rc_name) like '%"+search.getCounselName().toLowerCase()+"%') )";
		}
		else if(search.getCounselType().equals("p")){
			query+=" AND c.fd_id IN (select pc.pc_fd_mid from PetitionerCounsel pc where lower(pc.pc_name) like '%"+search.getCounselName().toLowerCase()+"%')";
		}else{
			query+=" AND c.fd_id IN (select rc.rc_fd_mid from RespondentCounsel rc where lower(rc.rc_name) like '%"+search.getCounselName().toLowerCase()+"%')";
		}
		result = em.createQuery("SELECT c FROM CaseFileDetail c where c.fd_rec_status=1"+query)
				.setFirstResult((search.getPageNumber()-1) * search.getItemsPerPage()).setMaxResults(search.getItemsPerPage()).getResultList();
		

		return result;
	}
	public Integer getByCounselCount(Search search) {
		// TODO Auto-generated method stub
		Integer count=0;
		String queryString="";
		if(search.getCaseNo()!=null && !search.getCaseNo().equals("")){
			queryString+=" AND c.fd_case_no='"+search.getCaseNo()+"'";
		}
		if(search.getCaseTypeId()!=null){
			queryString+=" AND c.fd_case_type="+search.getCaseTypeId();
		}
		if(search.getCaseYear()!=null && !search.getCaseYear().toString().equals("")){
			queryString+=" AND c.fd_case_year="+search.getCaseYear();
		}
		if(search.getCounselType().equals("pr")){
			queryString+=" AND ( "
					+ "c.fd_id IN (select pc.pc_fd_mid from PetitionerCounsel pc where lower(pc.pc_name) like '%"+search.getCounselName().toLowerCase()+"%')"
					+ " OR c.fd_id IN (select rc.rc_fd_mid from RespondentCounsel rc where lower(rc.rc_name) like '%"+search.getCounselName().toLowerCase()+"%') )";
		}
		else if(search.getCounselType().equals("p")){
			queryString+=" AND c.fd_id IN (select pc.pc_fd_mid from PetitionerCounsel pc where lower(pc.pc_name) like '%"+search.getCounselName().toLowerCase()+"%')";
		}else{
			queryString+=" AND c.fd_id IN (select rc.rc_fd_mid from RespondentCounsel rc where lower(rc.rc_name) like '%"+search.getCounselName().toLowerCase()+"%')";
		}
		try {			
			String query = "select count(c) from CaseFileDetail c where c.fd_rec_status=1 "+queryString;
			count= Integer.parseInt(em.createQuery(query).getSingleResult().toString());			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return count;
	}
	public List<CaseFileDetail> getByJudge(Search search) {
		// TODO Auto-generated method stub
		String query="";
		if(search.getCaseNo()!=null && !search.getCaseNo().equals("")){
			query+=" AND c.fd_case_no='"+search.getCaseNo()+"'";
		}
		if(search.getCaseTypeId()!=null){
			query+=" AND c.fd_case_type="+search.getCaseTypeId();
		}
		if(search.getCaseYear()!=null && !search.getCaseYear().toString().equals("")){
			query+=" AND c.fd_case_year="+search.getCaseYear();
		}
		query+=" AND c.fd_id IN (select jd.jd_fd_mid from JudgeData jd where jd.jd_jg_mid ="+search.getJudgeId()+" )";
		List<CaseFileDetail> result = em.createQuery("SELECT c FROM CaseFileDetail c where c.fd_rec_status=1"+query)
				.setFirstResult((search.getPageNumber()-1) * search.getItemsPerPage()).setMaxResults(search.getItemsPerPage()).getResultList();

		return result;
	}
	public Integer getByJudgeCount(Search search) {
		// TODO Auto-generated method stub
		Integer count=0;
		String queryString="";
		if(search.getCaseNo()!=null && !search.getCaseNo().equals("")){
			queryString+=" AND c.fd_case_no='"+search.getCaseNo()+"'";
		}
		if(search.getCaseTypeId()!=null){
			queryString+=" AND c.fd_case_type="+search.getCaseTypeId();
		}
		if(search.getCaseYear()!=null && !search.getCaseYear().toString().equals("")){
			queryString+=" AND c.fd_case_year="+search.getCaseYear();
		}
		queryString+=" AND c.fd_id IN (select jd.jd_fd_mid from JudgeData jd where jd.jd_jg_mid ="+search.getJudgeId()+" )";
		try {			
			String query = "select count(c) from CaseFileDetail c where c.fd_rec_status=1 "+queryString;
			count= Integer.parseInt(em.createQuery(query).getSingleResult().toString());			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return count;
	}
	public List<CaseFileDetail> getByImpugnedDetail(Search search) {
		// TODO Auto-generated method stub
		String query="";
		if(search.getCnrNo()!=null && !search.getCnrNo().equals("")){
			query+=" AND io.io_cnr_no='"+search.getCnrNo()+"'";
		}
		if(search.getCaseNo()!=null && !search.getCaseNo().equals("")){
			query+=" AND io.io_case_no='"+search.getCaseNo()+"'";
		}
		if(search.getCaseTypeId()!=null && search.getCourtType().equals("h")){
			query+=" AND io.io_hc_case_type="+search.getCaseTypeId();
		}
		if(search.getCaseTypeId()!=null && search.getCourtType().equals("l")){
			query+=" AND io.io_lc_case_type="+search.getCaseTypeId();
		}
		if(search.getCaseYear()!=null && !search.getCaseYear().toString().equals("")){
			query+=" AND io.io_case_year='"+search.getCaseYear()+"'";
		}
		if(search.getDistrict()!=null){
			query+=" AND io.io_district="+search.getDistrict();
		}
		String subquery=" AND c.fd_id IN (select io.io_fd_mid from ImpugnedOrder io where io.io_rec_status=1 "+query+" )";
		List<CaseFileDetail> result = em.createQuery("SELECT c FROM CaseFileDetail c where c.fd_rec_status=1"+subquery)
				.setFirstResult((search.getPageNumber()-1) * search.getItemsPerPage()).setMaxResults(search.getItemsPerPage()).getResultList();

		return result;
	}
	public Integer getByImpugnedDetailCount(Search search) {
		// TODO Auto-generated method stub
		Integer count=0;
		String queryString="";
		if(search.getCnrNo()!=null && !search.getCnrNo().equals("")){
			queryString+=" AND io.io_cnr_no='"+search.getCnrNo()+"'";
		}
		if(search.getCaseNo()!=null && !search.getCaseNo().equals("")){
			queryString+=" AND io.io_case_no='"+search.getCaseNo()+"'";
		}
		if(search.getCaseTypeId()!=null && search.getCourtType().equals("h")){
			queryString+=" AND io.io_hc_case_type="+search.getCaseTypeId();
		}
		if(search.getCaseTypeId()!=null && search.getCourtType().equals("l")){
			queryString+=" AND io.io_lc_case_type="+search.getCaseTypeId();
		}
		if(search.getCaseYear()!=null && !search.getCaseYear().toString().equals("")){
			queryString+=" AND io.io_case_year='"+search.getCaseYear()+"'";
		}
		if(search.getDistrict()!=null){
			queryString+=" AND io.io_district="+search.getDistrict();
		}
		String subquery=" AND c.fd_id IN (select io.io_fd_mid from ImpugnedOrder io where io.io_rec_status=1 "+queryString+" )";		try {			
			String query = "select count(c) from CaseFileDetail c where c.fd_rec_status=1 "+subquery;
			count= Integer.parseInt(em.createQuery(query).getSingleResult().toString());			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return count;
	}
}
