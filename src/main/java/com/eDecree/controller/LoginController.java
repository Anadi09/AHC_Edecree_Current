package com.eDecree.controller;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eDecree.model.ActionResponse;
import com.eDecree.model.CourtMaster;
import com.eDecree.model.CourtUserMapping;
import com.eDecree.model.LoginLog;
import com.eDecree.model.ObjectMaster;
import com.eDecree.model.Sub_Benches;
import com.eDecree.model.User;
import com.eDecree.service.BenchService;
import com.eDecree.service.CourtMasterService;
import com.eDecree.service.LookupService;
import com.eDecree.service.UserRoleService;
import com.eDecree.service.UserService;
import com.eDecree.utility.GlobalFunction;


@Controller
public class LoginController extends HttpServlet {

	@Autowired 
	private UserService userService;
	
	
	
	@Autowired 
	private UserRoleService urService;
	
	@Autowired 
	private BenchService benchService;
	
	@Autowired 
	private LookupService lookupService;
	
	@Autowired  
	private MessageSource messageSource;
	
	@Autowired
	private CourtMasterService courtMasterService;
	
	
	@Autowired
	private UserRoleService userRoleService;
	GlobalFunction cm;
	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.
    private String filePath;
    public void init() throws ServletException {
        this.filePath = "/pics/latest";
    }
	public LoginController()
	{
		userService = new UserService();
		cm = new GlobalFunction();
	}
	
	
/*	@RequestMapping(value = "login")
	public String ecourt() {	    	
		
		return "/ecourt/login";
	}*/
	
	
	
	
	@RequestMapping(value = "/dms/login")
	public String login() {	    	
		
		return "/dms/login";
	}
	@RequestMapping(value = "/dms/access_denied")
	public String access_denied(Model model) {	    	
		model.addAttribute("message","You do not have access to this page");
		return "/content/access_denied";
	}

	@RequestMapping(value = "/dms/logout", method = {RequestMethod.GET,RequestMethod.POST})
	public String logout(HttpSession session) {	 
		session.removeAttribute("USER");
		return "redirect:/";
	}
	
	
	@RequestMapping(value = "/getLinkecourt/{id}", method = RequestMethod.GET)
	public String getLinkecourt(Model model,@PathVariable("id") Integer benchId,
			HttpSession session) {
		String result="";
		
		User u=(User) session.getAttribute("USER");
		User user = userService.getByuserid(u.getUm_id());
		
		
	
		if (user.getUm_id() != null && user.getUm_rec_status()==1)
		{
			
				Date date1=new Date();
				
				//String date = cm.dateToString(date1,"dd-MM-yyyy");
				
				user = userService.getByuserid(user.getUm_id());
				List<ObjectMaster> ob_list = userService.getUserObjects(user.getUm_id());
				
				if(user.getUserroles().get(0).getLk().getLk_longname().equals("ECOURT")) {
					CourtUserMapping cum =courtMasterService.getCourtMappingForUser(user.getUm_id());
				//	session.setAttribute("USER", user);
					if(cum != null) {
						CourtMaster cm =courtMasterService.getCourtMaster(cum.getCum_court_mid());
						if(cm != null) {
							if(!cm.getCm_bench_id().equals(benchId)) {
								Sub_Benches sb=benchService.getBenches(benchId);
								if(sb !=null) {
								cm.setCm_bench_id(benchId);
								cm.setCm_judges_name(sb.getSb_judge_name());
								}
								
							}
							user.setUm_fullname(cm.getCm_judges_name());
							user.setCourtMaster(cm);
							session.setAttribute("USER", user);
							System.out.println("USer Info "+user);
							
						}
					}
				}
				else {
				session.setAttribute("USER", user);
				}
				
				session.setAttribute("ob_list", ob_list);
				System.out.println("objectlist="+cm.convert_to_json(ob_list));
			
		 if(user.getUserroles().get(0).getLk().getLk_longname().equals("ECOURT"))
				
			 result="ecourt/home";			

		}
		else if (user.getUm_rec_status()==2)
		{
			
		}
		else
		{
			
		}

		 //System.out.println("objectlist="+cm.convert_to_json(session));
		return result;
	
	}
	
	
	
	@RequestMapping(value = "/dms/userlogin", method = RequestMethod.POST, consumes = { "application/json;charset=UTF-8" }, produces = { "application/json;charset=UTF-8" })
	public @ResponseBody String userlogin(@RequestBody User user, HttpSession session,HttpServletRequest request) throws ParseException {
		// System.out.println("call user login");
		String result = " ";
		
		//UserRole ur=userRoleService.getByUserRole("Scrutiny");
		//System.out.println("User Role"+ur.getUser().getUsername());
		
		user = userService.validateLogin(user.getUsername(), user.getPassword());
		ActionResponse<User> response = new ActionResponse();
		ActionResponse<ObjectMaster> response2 = new ActionResponse();
		
		CourtMaster cm1=null;
	
		if(user.getUserroles().get(0).getLk().getLk_longname().equals("DECREE CREATOR") || user.getUserroles().get(0).getLk().getLk_longname().equals("DECREE EXAMINER") 
				||  user.getUserroles().get(0).getLk().getLk_longname().equals("Deputy Registrar(Decree)") ) {
			
	
		
		if (user.getUm_id() != null && user.getUm_rec_status()==1)
		{
			
				Date date1=new Date();
				
				String ipaddress = request.getRemoteAddr();
				
				//String date = cm.dateToString(date1,"dd-MM-yyyy");
				LoginLog loginLog=new LoginLog();
				 
				loginLog.setLl_login_time(date1);
				loginLog.setLl_user_mid(user.getUm_id());
				loginLog.setLl_ip_address(ipaddress);
				
				userService.saveLog(loginLog);
				
				user = userService.getByuserid(user.getUm_id());
				List<ObjectMaster> ob_list = userService.getUserObjects(user.getUm_id());
				
				if(user.getUserroles().get(0).getLk().getLk_longname().equals("ECOURT")) {
					CourtUserMapping cum =courtMasterService.getCourtMappingForUser(user.getUm_id());
				//	session.setAttribute("USER", user);
					if(cum != null) {
						CourtMaster cm =courtMasterService.getCourtMaster(cum.getCum_court_mid());
						cm1=cm;
						/*String after = before.trim().replaceAll(" +", " ");*/
						if(cm != null) {
							/*user.setUm_fullname(cm.getCm_judges_name().trim().replaceAll(" +", " "));*/
							
							user.setCourtMaster(cm);
						
							session.setAttribute("USER", user);
							System.out.println("USer Info "+user);
							
						}
					}
				}
				else {
				session.setAttribute("USER", user);
				}				
				response.setResponse("TRUE");
				response.setModelData(user);
				response2.setModelList(ob_list);
				session.setAttribute("ob_list", ob_list);
				System.out.println("objectlist="+cm.convert_to_json(ob_list));
			if(user.getUserroles().get(0).getLk().getLk_longname().equals("DMSAdmin") )
				response.setData("admin/home");
			else if(user.getUserroles().get(0).getLk().getLk_longname().equals("Review_Officer") ||  user.getUserroles().get(0).getLk().getLk_longname().equals("Deputy Registrar(Decree)")
					 ||  user.getUserroles().get(0).getLk().getLk_longname().equals("DECREE EXAMINER")
					||  user.getUserroles().get(0).getLk().getLk_longname().equals("SECTION_OFFICER")  ||  user.getUserroles().get(0).getLk().getLk_longname().equals("DECREE CREATOR")
				    ||  user.getUserroles().get(0).getLk().getLk_longname().equals("Assistant Review Officer") ||  user.getUserroles().get(0).getLk().getLk_longname().equals("Stamp_Reporter")
				    ||  user.getUserroles().get(0).getLk().getLk_longname().equals("REGISTRAR") ||  user.getUserroles().get(0).getLk().getLk_longname().equals("REGISTRAR (J)")
				    || user.getUserroles().get(0).getLk().getLk_longname().equals("JOINT REGISTRAR") || user.getUserroles().get(0).getLk().getLk_longname().equals("JOINT REGISTRAR (J)"))
				response.setData("user/home");
			
			else if(user.getUserroles().get(0).getLk().getLk_longname().equals("Chief Justice"))
				response.setData("nomination/nominated");
			else if(user.getUserroles().get(0).getLk().getLk_longname().equals("Judge"))
				response.setData("casefile/manage");
			else if(user.getUserroles().get(0).getLk().getLk_longname().equals("REGISTRAR"))
				response.setData("nomination/nominated");
			else if(user.getUserroles().get(0).getLk().getLk_longname().equals("ECOURT")) {
				if(cm1.getSubBenches()!=null) {
					response.setData("ecourt/ecourtHome");
				}
				else {
					response.setData("ecourt/home");
				}
			}
			else if(user.getUserroles().get(0).getLk().getLk_longname().equals("CaueList_Uploader"))
				response.setData("causelist/home");
			else if(user.getUserroles().get(0).getLk().getLk_longname().equals("Private_Secretary"))
				response.setData("ecourt/home");
			else if(user.getUserroles().get(0).getLk().getLk_longname().equals("Bench Secretary"))
				response.setData("ecourt/home");
			else if(user.getUserroles().get(0).getLk().getLk_longname().equals("Download Copy"))
				response.setData("casefile/download_manage");
			else if(user.getUserroles().get(0).getLk().getLk_longname().equals("Stemp Reporter"))
				response.setData("casefile/case_stempreport");

		}
		else if (user.getUm_rec_status()==2)
		{
			response.setResponse("FALSE");
			response.setData("InActive User");
		}
		else
		{
			response.setResponse("FALSE");
			response.setData("Username or Password is invalid");
		}
		}

		result = cm.convert_to_json(response);
		 //System.out.println("objectlist="+cm.convert_to_json(session));
		return result;
	}
	
	
	@RequestMapping(value = "/views/landingPage")
	public String index(Model model, HttpSession session) {
		User user = new User();
		return "/views/dashboard";
	}
	
	@RequestMapping(value = "/views/securityQuestion")
	public String index2(Model model, HttpSession session) {
	//	User user = new User();

		return "/views/securityQuestion";
	}
	@RequestMapping(value = "/dms/userFagPass", method = RequestMethod.POST, consumes = { "application/json;charset=UTF-8" }, produces = { "application/json;charset=UTF-8" })	
	public @ResponseBody String userFagPass(@RequestBody User user,HttpSession session) {
		//System.out.println("call user login");
		String result = " ";

		user = userService.validateUser(user.getUsername());	
		ActionResponse <User> response = new ActionResponse<User>();		
		ActionResponse <ObjectMaster> response2 = new ActionResponse<ObjectMaster>();
		if(user.getUm_id() != null){	
			user = userService.getByuserid(user.getUm_id());
			
		 List<ObjectMaster>	ob_list = userService.getUserObjects(user.getUm_id());
			
			session.setAttribute("USER", user);	
			response.setResponse("TRUE");
			response.setModelData(user);
			response2.setModelList(ob_list);
			session.setAttribute("ob_list", ob_list);
			response.setData("User logged in successfully");
		}else{
			response.setResponse("FALSE");
			response.setData("Please Enter correct Username");
		}		
		result = cm.convert_to_json(response);
		//System.out.println(result);
		return result;
	}
	
	@RequestMapping(value = "/views/forgetPassword")
	public String index3(Model model, HttpSession session) {
	//	User user = new User();

		return "/views/forgetPassword";
	}
	
	
	//ecourt
	@RequestMapping(value="/ecourt/login")
	public String ecourtlogin() {	    	
		
		return "/ecourt/login";
	}

	
	@RequestMapping(value="/ecourt/createaccountinperson")
	public String ecourtcreateinpersonaccount() {	    	
		
		return "/ecourt/createaccountinperson";
	}
}
