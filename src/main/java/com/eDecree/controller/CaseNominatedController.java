package com.eDecree.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eDecree.model.ActionResponse;
import com.eDecree.model.CaseFileDetail;
import com.eDecree.model.CaseNominated;
import com.eDecree.model.Judge;
import com.eDecree.model.User;
import com.eDecree.model.UserRole;
import com.eDecree.service.CaseFileDetailService;
import com.eDecree.service.CaseNominatedService;
import com.eDecree.service.CourtMasterService;
import com.eDecree.service.UserRoleService;
import com.eDecree.utility.GlobalFunction;

@Controller
@RequestMapping("/nomination")
public class CaseNominatedController {
	
	@Autowired
	private CaseFileDetailService caseFileDetailService;
	
	@Autowired
	private CourtMasterService courtMasterService;
	
	@Autowired
	private CaseNominatedService caseNominatedService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	private GlobalFunction globalfunction;

	public CaseNominatedController() {
		globalfunction = new GlobalFunction();
	}

	@RequestMapping(value = "/nominated", method = RequestMethod.GET)
	public String registrarListing() {

		return "/nomination/nominated";

	}

	// Nomination Case from one court to another court by CJI
	// created by Afnan
	@RequestMapping(value = "/saveRegistrarListing", method = RequestMethod.POST)
	@ResponseBody
	public String saveRegistrarListing(@RequestBody CaseFileDetail cfd,HttpSession session) {

		ActionResponse<CaseFileDetail> response = new ActionResponse<CaseFileDetail>();
		User u = (User) session.getAttribute("USER");
		UserRole ur=null;
		String jsonData = "";
	    Date date = new Date();
	    Date cnToDate = null;
	    
	    if(cfd.getCl_dol()!=null)
	    	{
	    		cnToDate =cfd.getCl_dol();
	    	}
		Long toJudgeId=null;
		Long fromCourtId=11l;
		Long fromJudgeId=366971l;
		
		CaseNominated savCN =new CaseNominated();
		Judge judge = courtMasterService.getJudgeDetailsByCourtMapping(cfd.getCl_court_no().intValue());
		CaseFileDetail casedetail = caseFileDetailService.getCaseFile(cfd);
		CaseNominated casenominated = caseNominatedService.getCaseNominatedByfdmid(casedetail.getFd_id());
		ur = userRoleService.getByUserRole("Chief Justice");
		
		if(judge!=null)
		{
			toJudgeId=judge.getJg_id();
		}
		else if(cfd.getCl_to_judge()!=null) 
		{
			toJudgeId=cfd.getCl_to_judge();
		}

		if(cfd.getCaseNominated().getCn_from_cm_mid()!=null || cfd.getCaseNominated().getCn_from_jg_mid()!=null)
		{
			fromCourtId=cfd.getCaseNominated().getCn_from_cm_mid();
			fromJudgeId=cfd.getCaseNominated().getCn_from_jg_mid();
		}
		else
		{
			casenominated.setCn_from_cm_mid(fromCourtId);
			casenominated.setCn_from_jg_mid(fromJudgeId);
		}
		
		if(casenominated!=null)
		{	
			casenominated.setCn_fd_mid(casedetail.getFd_id());
			casenominated.setCn_from_date(new Date());
			casenominated.setCn_from_cm_mid(fromCourtId);
			casenominated.setCn_from_jg_mid(fromJudgeId);
			casenominated.setCn_tocm_mid(cfd.getCl_court_no());
			casenominated.setCn_toclt_mid(cfd.getCl_list_type_mid());
			casenominated.setCn_tojg_mid(toJudgeId);
			casenominated.setCn_by_um_mid(u.getUm_id());
			casenominated.setCn_nominated_stage(1l);
			casenominated.setCn_rec_status(1);
			casenominated.setCn_todate(cnToDate);
			
			savCN = caseNominatedService.saveCN(casenominated);
		}
		else
		{
			
			savCN.setCn_fd_mid(cfd.getFd_id());
			savCN.setCn_from_date(new Date());
			savCN.setCn_from_cm_mid(fromCourtId);
			savCN.setCn_from_jg_mid(fromJudgeId);
			savCN.setCn_tocm_mid(cfd.getCl_court_no());
			savCN.setCn_toclt_mid(cfd.getCl_list_type_mid());
			savCN.setCn_tojg_mid(toJudgeId);
			savCN.setCn_by_um_mid(u.getUm_id());
			savCN.setCn_nominated_stage(1l);
			savCN.setCn_rec_status(1);
			savCN.setCn_todate(cnToDate);
			
			savCN = caseNominatedService.saveCN(savCN);
		}
		
		
		casedetail.setFd_assign_to(ur.getUr_um_mid());
		CaseFileDetail cd = caseFileDetailService.save(casedetail);
		if(savCN!=null && cd!=null)
		{
			response.setResponse("TRUE");
		}
		else
		{
			response.setResponse("false");
		}
		jsonData = globalfunction.convert_to_json(response);
		return jsonData;
	}
	
	@RequestMapping(value = "/getNominatedReport", method = RequestMethod.GET)
	@ResponseBody
	public String getCaseReport(HttpServletRequest request) 
	{
		ActionResponse<CaseNominated> response = new ActionResponse<CaseNominated>();
		String id = request.getParameter("docId");
		Long doc = new Long(id);
		String jsonData = null;
		CaseFileDetail casefileList = caseFileDetailService.getCaseFileDetail(doc);
		CaseNominated caseNominatedReport = caseNominatedService.getNominatedReport(doc);
		casefileList.setCaseNominated(caseNominatedReport);
		response.setData(casefileList);
		
		jsonData = globalfunction.convert_to_json(response);
		return jsonData;

	}

}
