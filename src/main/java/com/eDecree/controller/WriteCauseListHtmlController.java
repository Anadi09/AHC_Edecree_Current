package com.eDecree.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eDecree.model.ActionResponse;
import com.eDecree.model.CauseList;
import com.eDecree.model.CourtUserMapping;
import com.eDecree.model.User;
import com.eDecree.model.UserRole;
import com.eDecree.service.CauseListService;
import com.eDecree.service.CourtMasterService;
import com.eDecree.service.WriteCauseListHtmlService;
import com.eDecree.utility.GlobalFunction;
import com.eDecree.utility.WriteHtmlFile;


@Controller
@RequestMapping("/WriteCauseListHtml")
public class WriteCauseListHtmlController {
	
	
	@Autowired
	private WriteCauseListHtmlService writeCauseListHtmlService;
	
	@Autowired
	CourtMasterService courtMasterService;
	
	private GlobalFunction globalfunction;
	
	@Autowired
	CauseListService causeListService;
	
	public WriteCauseListHtmlController() {
		globalfunction = new GlobalFunction();
	}

	
	@RequestMapping(value = "/getCauseListCasesZip", method = RequestMethod.POST)
	public @ResponseBody String getCauseList(@RequestBody CauseList causeList, HttpSession session) {
		String jsonData = "";
		ActionResponse<CauseList> response = new ActionResponse<CauseList>();
		User user = (User) session.getAttribute("USER");
		List<UserRole> userroles = user.getUserroles();
		
		
		
		
		String userRole = "";
		for (UserRole userrole : userroles) {
			userRole = userrole.getLk().getLk_longname();
		}
		if (userRole.equals("ECOURT")) {
			CourtUserMapping mapping = courtMasterService.getCourtMapping(user.getUm_id());
			causeList.setCl_court_no(mapping.getCum_court_mid());
		}

		// code for generate html for cause list
		
		// 
		
		
		
		        try {
		            //define a HTML String Builder
		            StringBuilder htmlStringBuilder=new StringBuilder();
		            //append html header and title
		            htmlStringBuilder.append("<html><head><title>Cause List</title></head>");
		            //append body
		            htmlStringBuilder.append("<body>");
		            //append table
		            htmlStringBuilder.append("<table border=\"1\" bordercolor=\"#000000\">");
		            //append row
		            htmlStringBuilder.append("<tr><td><b>TestId</b></td><td><b>TestName</b></td><td><b>TestResult</b></td></tr>");
		            //append row
		            htmlStringBuilder.append("<tr><td>001</td><td>Login</td><td>Passed</td></tr>");
		            //append row
		            htmlStringBuilder.append("<tr><td>002</td><td>Logout</td><td>Passed</td></tr>");
		            //close html file
		            htmlStringBuilder.append("</table></body></html>");
		            
		            
		            //write html string content to a file
		            WriteHtmlFile.WriteToFile(htmlStringBuilder.toString(),"index.html");
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    
		    
		
		
		List<CauseList> list = causeListService.getList(causeList);
		response.setResponse("true");
		response.setModelList(list);
		jsonData = globalfunction.convert_to_json(response);
		return jsonData;
	}

}
