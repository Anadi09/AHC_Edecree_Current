package com.eDecree.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.eDecree.utility.GlobalFunction;

@Controller
@RequestMapping("/court")
public class CourtDetailController {

	private GlobalFunction globalfunction;
	
	@Autowired
	CauseListService causeListService;
	
	@Autowired
	CourtMasterService courtMasterService;
	
	public CourtDetailController() {
		// TODO Auto-generated constructor stub
		globalfunction = new GlobalFunction();
	}

	
	@RequestMapping(value = "/courtDetail")
	public String Home(Model model) {
		return "/court/courtDetail";
	}
	
	
	@RequestMapping(value="/getAllCourtsCauselistReport",method = RequestMethod.GET)
	public  @ResponseBody String getReport(HttpSession session,HttpServletRequest request) throws ParseException{
	
		String result = null;
	
		ActionResponse<CauseList> response = new ActionResponse<CauseList>();
		
		String date = request.getParameter("causelist_date"); 
		
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(date);
		
		
		System.out.println("datadate: "+date);
		
		
		CauseList causeList=new CauseList();
		User user=(User) session.getAttribute("USER");
		List<UserRole> userroles=user.getUserroles();
		String userRole="";
		for(UserRole userrole:userroles){
			userRole=userrole.getLk().getLk_longname();
		}
		List list=new ArrayList();
		
	
	/*	if(userRole.equals("ECOURT") || userRole.equals("Private_Secretary") || userRole.equals("Bench Secretary") || userRole.equals("DMSAdmin"))*/
			if(userRole.equals("DMSAdmin"))
		{
			CourtUserMapping mapping=courtMasterService.getCourtMapping(user.getUm_id());
			
			causeList.setCl_dol(date1);
			causeList.setCl_court_no(mapping.getCum_court_mid());
			
			list=causeListService. getListByTypeForAllCourts(causeList);
		}else{
			list=causeListService. getListByTypeForAllCourts(causeList);	
		}
		List<CauseList> cList=new ArrayList<>();
		
		for(int i=0;i<list.size();i++){
			CauseList c=new CauseList();
			Object[] row = (Object[]) list.get(i);
			if(row[1].toString().equals("1")){
				c.setCl_list_type_mid(1L);
				c.setCl_court_no(Integer.parseInt(row[2].toString()));
				c.setCount(Integer.parseInt(row[0].toString()));
			}
			if(row[1].toString().equals("2")){
				c.setCl_list_type_mid(2L);
				c.setCount(Integer.parseInt(row[0].toString()));
				c.setCl_court_no(Integer.parseInt(row[2].toString()));
			}
			if(row[1].toString().equals("3")){
				c.setCl_list_type_mid(3L);
				c.setCount(Integer.parseInt(row[0].toString()));
				c.setCl_court_no(Integer.parseInt(row[2].toString()));
			}
			if(row[1].toString().equals("4")){
				c.setCl_list_type_mid(4L);
				c.setCount(Integer.parseInt(row[0].toString()));
				c.setCl_court_no(Integer.parseInt(row[2].toString()));
			}
			if(row[1].toString().equals("5")){
				c.setCl_list_type_mid(5L);
				c.setCount(Integer.parseInt(row[0].toString()));
				c.setCl_court_no(Integer.parseInt(row[2].toString()));
			}
			if(row[1].toString().equals("6")){
				c.setCl_list_type_mid(6L);
				c.setCount(Integer.parseInt(row[0].toString()));
				c.setCl_court_no(Integer.parseInt(row[2].toString()));
			}
			if(row[1].toString().equals("7")){
				c.setCl_list_type_mid(7L);
				c.setCount(Integer.parseInt(row[0].toString()));
				c.setCl_court_no(Integer.parseInt(row[2].toString()));
			}
			if(row[1].toString().equals("8")){
				c.setCl_list_type_mid(8L);
				c.setCount(Integer.parseInt(row[0].toString()));
				c.setCl_court_no(Integer.parseInt(row[2].toString()));
			}
			if(row[1].toString().equals("9")){
				c.setCl_list_type_mid(9L);
				c.setCount(Integer.parseInt(row[0].toString()));
				c.setCl_court_no(Integer.parseInt(row[2].toString()));
			}
			if(row[1].toString().equals("10")){
				c.setCl_list_type_mid(10L);
				c.setCount(Integer.parseInt(row[0].toString()));
				c.setCl_court_no(Integer.parseInt(row[2].toString()));
			}
			if(row[1].toString().equals("11")){
				c.setCl_list_type_mid(11L);
				c.setCount(Integer.parseInt(row[0].toString()));
				c.setCl_court_no(Integer.parseInt(row[2].toString()));
			}
			if(row[1].toString().equals("12")){
				c.setCl_list_type_mid(12L);
				c.setCount(Integer.parseInt(row[0].toString()));
				c.setCl_court_no(Integer.parseInt(row[2].toString()));
			}
			if(row[1].toString().equals("13")){
				c.setCl_list_type_mid(13L);
				c.setCount(Integer.parseInt(row[0].toString()));
				c.setCl_court_no(Integer.parseInt(row[2].toString()));
				
			}
			if(row[1].toString().equals("14")){
				c.setCl_list_type_mid(14L);
				c.setCount(Integer.parseInt(row[0].toString()));
				c.setCl_court_no(Integer.parseInt(row[2].toString()));
			}
			if(row[1].toString().equals("15")){
				c.setCl_list_type_mid(15L);
				c.setCount(Integer.parseInt(row[0].toString()));
				c.setCl_court_no(Integer.parseInt(row[2].toString()));
			}
			if(row[1].toString().equals("16")){
				c.setCl_list_type_mid(16L);
				c.setCount(Integer.parseInt(row[0].toString()));
				c.setCl_court_no(Integer.parseInt(row[2].toString()));
			}
			cList.add(c);
		}
		response.setModelList(cList);
		
		if (list != null) {
			response.setResponse("TRUE");
			result = globalfunction.convert_to_json(response);
		} else {
			response.setResponse("FALSE");
		}
	
		
		return result;
	
	}

}
