package com.eDecree.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.eDecree.model.ObjectMaster;
import com.eDecree.model.RoleObject;
import com.eDecree.model.User;
import com.eDecree.service.ObjectMasterService;
import com.eDecree.service.RoleMappingService;

public class RequestProcessingTimeInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	ObjectMasterService objectMasterService;
	
	@Autowired
	RoleMappingService roleMappingService;
	//private static final Logger logger = LoggerFactory.getLogger(RequestProcessingTimeInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		long startTime = System.currentTimeMillis();
		//logger.info("Request URL::" + request.getRequestURL().toString()+ ":: Start Time=" + System.currentTimeMillis());
		request.setAttribute("startTime", startTime);
		
		System.out.println("Pre-handle"+request.getContextPath());
		User user=new User();
		user=(User) request.getSession().getAttribute("USER");
		String url=request.getRequestURI();
		System.out.println("URL="+url);
 		
		user=(User) request.getSession().getAttribute("USER");
		
		if(user!=null || url.equals(request.getContextPath()+"/dms/userlogin")  || url.equals(request.getContextPath()+"/user/register")
				|| url.equals(request.getContextPath()+"/user/validateAdvocate") || url.equals(request.getContextPath()+"/judgement/upload") 
				|| url.equals(request.getContextPath()+"/judgement/create") || url.contains(request.getContextPath()+"/judgement/delete") 
				|| url.contains(request.getContextPath()+"/judgement/test") || url.contains(request.getContextPath()+"/user/getmasters")
				|| url.contains(request.getContextPath()+"/user/createDecree")){
			String array[]=url.split("/eDecree");
			String newurl=array[1];
			ObjectMaster om=objectMasterService.getByLink(newurl);
			
			if(om.getOm_object_link()!=null){
			if(newurl.contains(om.getOm_object_link())){
				Long role_id=user.getUserroles().get(0).getUr_role_id();
				RoleObject ro=roleMappingService.getByRoleAndObject(role_id, om.getOm_id());
				if(user.getUserroles().get(0).getLk().getLk_longname().contains("DECREE CREATOR")) {
					System.out.println("in decfee");
					return true;
				}
				else {
				if(ro.getRo_role_id()==null){
					/*if(!user.getUserroles().get(0).getUr_role_id().equals(351424L)) {
					response.sendRedirect(request.getContextPath()+"/dms/access_denied");
					return false;
					}*/
					
					response.sendRedirect(request.getContextPath()+"/dms/access_denied");
					return false;
				}
			}
			}
			}
			return true;
			
		}else
		{
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		
		//if returned false, we need to make sure 'response' is sent
		//return true;
	}

	/*@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("Request URL::" + request.getRequestURL().toString()
				+ " Sent to Handler :: Current Time=" + System.currentTimeMillis());
		//we can add attributes in the modelAndView and use that in the view page
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long startTime = (Long) request.getAttribute("startTime");
		logger.info("Request URL::" + request.getRequestURL().toString()
				+ ":: End Time=" + System.currentTimeMillis());
		logger.info("Request URL::" + request.getRequestURL().toString()
				+ ":: Time Taken=" + (System.currentTimeMillis() - startTime));
	}
*/
}