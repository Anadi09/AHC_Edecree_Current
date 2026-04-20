package com.eDecree.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eDecree.model.AdvocateDTO;
import com.eDecree.model.CaseRequestDTO;
import com.eDecree.model.DecreeSmsLog;
import com.eDecree.model.ExtraAdvocateForSms;
import com.eDecree.model.Lookup;
import com.eDecree.model.User;
import com.eDecree.service.LookupService;
import com.eDecree.service.SendSmsService;

@Controller
@RequestMapping("/sms")
public class SmsController {
	
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private SendSmsService sendSmsService;
	
	@Autowired
	private LookupService lookupService;
	
	
//	***************************** Vijay Chaurasiya *************************************************
	@RequestMapping(value = "/sendAdvSingleSms", method = RequestMethod.POST)
	 @ResponseBody
	public String sendAdvSingleSms(@RequestBody AdvocateDTO advocateDto ,HttpSession session) {
	
		
		Lookup lookup=new Lookup();
		lookup=lookupService.getLookUpObject("SMS_URL");
		System.out.println("***********************"+lookup.getLk_longname());
		String otpTmpId="";
		InetAddress ip;
		String hostname;
		String extraLko = "";
		
		String tillDate = "";

		Date date = advocateDto.getTillDate();

		if (date != null) {
		    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
		    tillDate = sdf.format(date);
		} else {
		    tillDate = "N/A"; // or handle error
		}
		
		System.out.println("Advocate details for sending sms************************"+advocateDto);
		
		try {
				ip = InetAddress.getLocalHost();
			hostname = ip.getHostAddress();
			System.out.println("Your current IP address : " + ip);
			System.out.println("Your current Hostname : " + hostname);

			if (hostname.equals("172.16.0.6")) {
				otpTmpId = "1107177019834490428";
			} else if (hostname.equals("127.0.0.1")) {
				/*
				 * otpTmpId ="1107160793982323688"; extraLko="-Lko. Bench ";
				 */
				otpTmpId = "1107177019834490428";
			} else {
				System.out.println("In Local");
				otpTmpId = "1107177019834490428";
				/* extraLko="-Lko. Bench "; */
			}

		} catch (UnknownHostException e) {

			e.printStackTrace();
		}
		String otp=" ";
		
		// get logged in user
		User user=(User) session.getAttribute("USER");
		
		
		String sms_url=lookup.getLk_longname();
		
		String smstext="Decree has been drawn up for "+advocateDto.getCaseType()+"No."+advocateDto.getCaseNo()+"/"+advocateDto.getCaseYear()+", You are requested to visit Decree Section on or before "+tillDate+ ".";
		
		String otpresponse = sendSmsService.sendBSNLSMS(sms_url,  advocateDto.getMobile(), smstext + " -AHC", otpTmpId);
//		String otprespons="1";
		if(otpresponse.equals("1")) {
			DecreeSmsLog dsl=new DecreeSmsLog();
			dsl.setDslAor(advocateDto.getAor());
			dsl.setDslMobile(advocateDto.getMobile());
			dsl.setDslSendDate(new Date());
			dsl.setDslTillDate(advocateDto.getTillDate());
			dsl.setDslSendBy(user.getUm_id());
			dsl.setDslCaseType(advocateDto.getCaseType());
			dsl.setDslCaseNo(advocateDto.getCaseNo());
			dsl.setSmsText(smstext);
			dsl.setDslSmsStatus(true);
			try {
			    dsl.setDslCaseYear(Integer.parseInt(advocateDto.getCaseYear()));
			} catch (NumberFormatException e) {
			    throw new IllegalArgumentException("Invalid case year: " + advocateDto.getCaseYear());
			}
			
			 DecreeSmsLog dslResult=sendSmsService.saveDecreeSmsLog(dsl); 
			 return otpresponse;
		}else if(otpresponse.equals("0")) {
			
			return otpresponse;
		}
		else {
			return null;
		}
		
	}
	
	                       
//	*****************************  ************************************** Vijay Chaurasiya Java Developer *************************************************
	@RequestMapping(value = "/sendAdvAllSms", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> sendSmsToAllAdv(
	        @RequestBody List<AdvocateDTO> advList,
	        HttpSession session) {

	    Map<String, Object> response = new HashMap<String, Object>();
	    Map<String, String> smsStatusMap = new LinkedHashMap<String, String>();

	    // Session check
	    User user = (User) session.getAttribute("USER");
	    if (user == null) {
	        response.put("status", "SESSION_EXPIRED");
	        response.put("message", "User session expired. Please login again.");
	        return response;
	    }

	    //  Validate list
	    if (advList == null || advList.isEmpty()) {
	        response.put("status", "NO_DATA");
	        response.put("message", "Advocate list is empty.");
	        return response;
	    }

	    AdvocateDTO firstAdv = advList.get(0);

	    String caseType = firstAdv.getCaseType();
	    String caseNo = firstAdv.getCaseNo();
	    String caseYear = firstAdv.getCaseYear();

	    // Date formatting (Java 1.7)
	    String tillDate = "";
	    Date date = firstAdv.getTillDate();
	    if (date != null) {
	        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	        tillDate = sdf.format(date);
	    } else {
	        tillDate = "N/A";
	    }

	    //  SMS config
	    Lookup lookup = lookupService.getLookUpObject("SMS_URL");
	    String smsUrl = lookup.getLk_longname();

	    String otpTmpId = "1107177019834490428";

	    String smstext = "Decree has been drawn up for "
	            + caseType + " No." + caseNo + "/" + caseYear
	            + ", You are requested to visit Decree Section on or before "
	            + tillDate + ".";

	    int successCount = 0;
	    int failureCount = 0;

	    //  Loop all advocates
	    for (AdvocateDTO adv : advList) {

	        String mobile = adv.getMobile();

	        //  Mobile validation
	        if (mobile == null || mobile.trim().length() != 10) {
	            smsStatusMap.put(mobile, "INVALID_MOBILE");
	            failureCount++;
	            continue;
	        }

	        String smsResponse = sendSmsService.sendBSNLSMS(
	                smsUrl,
	                mobile,
	                smstext + " -AHC",
	                otpTmpId
	        );

	        if ("1".equals(smsResponse)) {

	            DecreeSmsLog dsl = new DecreeSmsLog();
	            dsl.setDslAor(adv.getAor());
	            dsl.setDslMobile(mobile);
	            dsl.setDslSendDate(new Date());
	            dsl.setDslTillDate(adv.getTillDate());
	            dsl.setDslSendBy(user.getUm_id());
	            dsl.setDslCaseType(adv.getCaseType());
	            dsl.setDslCaseNo(adv.getCaseNo());
	            dsl.setSmsText(smstext);
	            dsl.setDslSmsStatus(true);

	            try {
	                dsl.setDslCaseYear(Integer.parseInt(adv.getCaseYear()));
	            } catch (NumberFormatException e) {
	                smsStatusMap.put(mobile, "INVALID_CASE_YEAR");
	                failureCount++;
	                continue; //  skip instead of throwing exception
	            }

	            sendSmsService.saveDecreeSmsLog(dsl);

	            smsStatusMap.put(mobile, "SUCCESS");
	            successCount++;

	        } else {
	            smsStatusMap.put(mobile, "FAILED");
	            failureCount++;
	        }
	    }

	    //  Final response
	    response.put("status", "COMPLETED");
	    response.put("successCount", successCount);
	    response.put("failureCount", failureCount);
	    response.put("details", smsStatusMap);

	    return response;
	}
	

	
	@RequestMapping(value = "/sent", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public List<DecreeSmsLog> getSentSmsByCaseDetails(
	        @RequestBody CaseRequestDTO request) {
		
		String hello="vijay";

	    return sendSmsService.getSentSmsByCaseDetails(
	            request.getCaseType(),
	            request.getCaseNo(),
	            Integer.parseInt(request.getCaseYear()));
	}
	
	
	//  ================= Vijay Chaurasiya Java Developer ============================
	
	@RequestMapping(value = "/saveExtraAdvocate", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveExtraAdvocate(@RequestBody Map<String, Object> payload, HttpSession session) {

	    Map<String, Object> response = new HashMap<>();
	    
	    System.out.println("Received payload for saving Extra Advocate: " + payload);

	    try {
	        // Get logged-in user from session
	        User user = (User) session.getAttribute("USER");

	        // Create entity
	        ExtraAdvocateForSms adv = new ExtraAdvocateForSms();

	        adv.setAor((String) payload.get("aor"));
	        adv.setName((String) payload.get("name"));
	        adv.setMobile((String) payload.get("mobile"));

	        adv.setCase_type((String) payload.get("caseType"));
	        adv.setCase_no((String) payload.get("caseNo"));
	        adv.setCase_year((String) payload.get("caseYear"));
	        adv.setCr_by(user != null ? user.getUm_id() : null);
	        adv.setCr_date(new Date());

	        // Set created by from session 
	        if (user != null) {
	            adv.setCr_by(user.getUm_id());
	        }

	        adv.setCr_date(new Date());
	        
	        
	     // CHECK DUPLICATE
	        boolean exists = sendSmsService.isMobileExists(
	                adv.getMobile(),
	                adv.getCase_type(),
	                adv.getCase_no(),
	                adv.getCase_year()
	        );

	        if (exists) {
	            response.put("status", "error");
	            response.put("message", "Mobile number already exists for this case");
	            return response;
	        }
	        
	      

	        // Save using service
	        ExtraAdvocateForSms saved = sendSmsService.saveExtraAdvocate(adv);

	        response.put("status", "success");
//	        response.put("data", saved);

	    } catch (Exception e) {
	        e.printStackTrace();
	        response.put("status", "error");
	        response.put("message", "Failed to save Extra Advocate");
	    }

	    return response;
	}

	
	// ================== Vijay Chaurasiya Java Developer ============================
	
	@RequestMapping(value = "/getExtraAdvocate", method = RequestMethod.POST)
	@ResponseBody
	public List<ExtraAdvocateForSms> getExtraAdvocates(@RequestBody Map<String, String> req) {

	    System.out.println("Fetching Extra Advocates for: " + req);

	    return sendSmsService.getExtraAdvocates(
	            req.get("caseType"),
	            req.get("caseNo"),
	            req.get("caseYear")
	    );
	}
	
	
	@RequestMapping(value = "/deleteExtraAdvocate", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteExtraAdvocate(@RequestBody Map<String, Long> req) {

	    Map<String, Object> response = new HashMap<>();

	    try {
	        Long id = req.get("id");

	        sendSmsService.deleteExtraAdvocate(id);

	        response.put("status", "success");

	    } catch (Exception e) {
	        e.printStackTrace();
	        response.put("status", "error");
	        response.put("message", "Delete failed");
	    }

	    return response;
	}




}
