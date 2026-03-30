package com.eDecree.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eDecree.model.ActionResponse;
import com.eDecree.model.User;
import com.eDecree.utility.GlobalFunction;
import com.efiling.model.Amendment;
import com.efiling.model.EfilingCaseFileDetail;
import com.efiling.model.EfilingLookup;
import com.efiling.model.EfilingUser;
import com.efiling.model.RegisteredCaseDetails;
import com.efiling.service.AmendmentService;

@Controller
@RequestMapping("/amendment")
public class AmendmentController {
	private GlobalFunction cm;
	@Autowired
	ServletContext context;
	@Autowired
	private AmendmentService amendmentService;

	String case_year = null;
	String case_type = null;
	String caseno = null;

	public AmendmentController() {
		cm = new GlobalFunction();
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String addCaveat() {

		return "/amendment/searchcasefile";
	}

	@RequestMapping(value = "/manage/{id}", method = RequestMethod.GET)
	public String addApplication(Model model, @PathVariable Integer id) {

		model.addAttribute("fd_id", id);

		return "/amendment/manage";
	}

	@RequestMapping(value = "/searchCaseFile", method = RequestMethod.GET)
	@ResponseBody
	public String searchCaseFile(HttpServletRequest request) {

		case_year = request.getParameter("case_year");
		case_type = request.getParameter("case_type");
		caseno = request.getParameter("case_no");

		Long caseyear = new Long(case_year);
		Long casetype = new Long(case_type);

		List<EfilingCaseFileDetail> cfd = amendmentService.getCaseFile(caseyear, casetype, caseno);

		ActionResponse<EfilingCaseFileDetail> response = new ActionResponse<EfilingCaseFileDetail>();
		String jsonData = null;

		if (cfd != null) {
			response.setResponse("TRUE");
			response.setModelList(cfd);
			jsonData = cm.convert_to_json(response);

		} else {
			response.setResponse("FALSE");
			jsonData = cm.convert_to_json(response);

		}
		return jsonData;

	}

	@RequestMapping(value = "/getamendments/{id}", method = RequestMethod.GET)
	public @ResponseBody String getAmendmentHistory(@PathVariable Long id, HttpSession session) {
		String jsonData = null;

		ActionResponse<Amendment> response = new ActionResponse<Amendment>();
		List<Amendment> amendments = amendmentService.getAmendments(id);
		if (amendments.size() > 0) {
			response.setModelList(amendments);
			response.setResponse("TRUE");
		} else {
			response.setResponse("FALSE");
			response.setData("No Records found");
		}
		jsonData = cm.convert_to_json(response);
		return jsonData;
	}

	@RequestMapping(value = "/getadvocates/{id}", method = RequestMethod.GET)
	public @ResponseBody String getAdvocates(@PathVariable Long id, HttpSession session) {
		ActionResponse<EfilingUser> response = new ActionResponse<EfilingUser>();
		String jsonData = null;
		EfilingCaseFileDetail casefileDetail = amendmentService.getCaseFile(id);
		Integer caseNo = Integer.parseInt(casefileDetail.getFd_case_no());
		Integer caseYear = casefileDetail.getFd_case_year();
		RegisteredCaseDetails rcd = amendmentService.getRegisterCase(casefileDetail.getFd_case_type(), caseNo,
				caseYear);
		EfilingUser user = new EfilingUser();
		if (rcd.getRcd_cr_by() != null)
			user = amendmentService.getUser(rcd.getRcd_cr_by());

		List<EfilingUser> users = amendmentService.getApplicationUsers(casefileDetail.getFd_id(), 1000043L);

		if (user.getUm_id() != null) {
			users.add(user);
		}
		response.setModelList(users);

		if (users.isEmpty()) {
			response.setResponse("FALSE");
			response.setData("No Users Found");
		} else {
			response.setResponse("TRUE");
		}
		jsonData = cm.convert_to_json(response);

		return jsonData;
	}

	@RequestMapping(value = "/searchuser", method = RequestMethod.GET)
	@ResponseBody
	public String searchUser(HttpServletRequest request) {

		String uName = request.getParameter("username");

		EfilingUser user = amendmentService.searchUser(uName);

		ActionResponse<EfilingUser> response = new ActionResponse<EfilingUser>();
		String jsonData = null;

		if (user.getUsername() != null) {
			response.setResponse("TRUE");
			response.setModelData(user);
			jsonData = cm.convert_to_json(response);

		} else {
			response.setResponse("FALSE");
			jsonData = cm.convert_to_json(response);
		}
		return jsonData;

	}

	@RequestMapping(value = "/addamendment", method = RequestMethod.POST)
	@ResponseBody
	public String addAmendment(@RequestBody Amendment amendment, HttpSession session) {
		String jsonData = null;
		User u = (User) session.getAttribute("USER");

		ActionResponse<Amendment> response = new ActionResponse<>();
		EfilingLookup lkCreated = amendmentService.getLookup("AMENDMENT_STATUS", "Amendment created");
		EfilingLookup lkuploaded = amendmentService.getLookup("AMENDMENT_STATUS", "Amendment uploaded");
		EfilingLookup lkAmdDeleteStatus = amendmentService.getLookup("AMENDMENT_STATUS", "Amendment deleted");
		
		amendment.setAm_status(lkCreated.getLk_id());
		amendment.setAm_created(new Date());
		amendment.setAm_created_by(u.getUm_id());
		if (amendment.getAm_type().equals("P")) 
		{
			Amendment amendmentexist = amendmentService.getAmendment(amendment.getAm_fd_mid(), amendment.getAm_type(),lkCreated.getLk_id(),lkuploaded.getLk_id());
			if (amendmentexist == null) 
			{
				amendment = amendmentService.saveAmendment(amendment);
				amendment = amendmentService.getAmendment(amendment.getAm_id());
				if (amendment.getAm_id() != null) 
				{
					response.setResponse("TRUE");
					response.setData("Amendment for petition created successfully");
				} 
				else 
				{
					response.setResponse("FALSE");
					response.setData("Error occurred while adding amendment");
				}
			} 
			else if(amendmentexist.getAm_status().equals(1000053L)) 
			{
				response.setResponse("FALSE");
				response.setData("Amendment for petition already exist in the system");
			}
			else if(amendmentexist.getAm_status().equals(1000054L)) {
				response.setResponse("FALSE");
				response.setData("Amendment for petition already Uploaded by Advocate");
			}
		} 
		else 
			{
				Amendment amendmenteApplication = amendmentService.getAmendmentApplication(amendment.getAm_fd_mid(),amendment.getAm_at_mid(), amendment.getAm_document_no(),
					amendment.getAm_document_year(),lkCreated.getLk_id());
			
				if(amendmenteApplication==null)
				{
					amendmenteApplication = amendmentService.getAmendmentApplication(amendment.getAm_fd_mid(),amendment.getAm_at_mid(), amendment.getAm_document_no(),
							amendment.getAm_document_year(),lkAmdDeleteStatus.getLk_id());
				}
				if(amendmenteApplication==null)
				{
					amendment = amendmentService.saveAmendment(amendment);
					
					if (amendment.getAm_id() != null) 
					{
						response.setResponse("TRUE");
						response.setData("Amendment for application created successfully");
					} 
					else 
						{
						response.setResponse("FALSE");
						response.setData("Error occurred while adding amendment");
						}
				}
				else 
				{
						amendmenteApplication.setAm_created_by(amendment.getAm_created_by());
						amendmenteApplication.setAm_status((amendment.getAm_status()));
						amendmenteApplication.setAm_created(new Date());
						amendmenteApplication.setAm_um_mid(amendment.getAm_um_mid());
						
						amendment = amendmentService.saveAmendment(amendmenteApplication);
				
					if (amendment.getAm_id() != null) 
					{
						response.setResponse("TRUE");
						response.setData("Amendment for Application Updated");
					} 
					else 
						{
						response.setResponse("FALSE");
						response.setData("Error occurred while adding amendment");
						}
				}
		}
		response.setModelData(amendment);
		jsonData = cm.convert_to_json(response);
		return jsonData;
	}

	@RequestMapping(value = "/deleteamendment/{id}", method = RequestMethod.GET)
	public @ResponseBody String deleteAmendment(@PathVariable Long id, HttpSession session) {
		String jsonData = null;
		ActionResponse<Amendment> response = new ActionResponse<>();
		Amendment amendment = amendmentService.getAmendment(id);
		EfilingLookup lkuploaded = amendmentService.getLookup("AMENDMENT_STATUS", "Amendment uploaded");
		if (amendment.getAm_status().longValue() == lkuploaded.getLk_id().longValue()) 
		{
			response.setResponse("False");
			response.setData("Document for this amendment has been already uploaded");
		} else 
		{
			EfilingLookup lk = amendmentService.getLookup("AMENDMENT_STATUS", "Amendment deleted");
			amendment.setAm_status(lk.getLk_id());
			amendment = amendmentService.saveAmendment(amendment);
			response.setResponse("TRUE");
			response.setData("Amendment deleted successfully");
		}
		jsonData = cm.convert_to_json(response);
		return jsonData;
	}

	@RequestMapping(value = "/getApplicationTypes", method = RequestMethod.GET)
	@ResponseBody
	public String getApplicationTypes(HttpServletRequest request) {

		List<Object> app = null;

		ActionResponse<Object> response = new ActionResponse<Object>();
		String jsonData = null;

		Long caseyear = new Long(case_year);
		Long casetype = new Long(case_type);

		app = amendmentService.getApplicationTypes(caseyear, casetype, caseno);

		if (app != null) {
			response.setResponse("TRUE");
			response.setModelList(app);
			jsonData = cm.convert_to_json(response);

		}
		return jsonData;

	}

}
