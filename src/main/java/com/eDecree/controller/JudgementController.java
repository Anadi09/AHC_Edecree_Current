package com.eDecree.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.eDecree.model.CaseFileDetail;
import com.eDecree.model.CaseType;
import com.eDecree.model.IndexField;
import com.eDecree.model.Lookup;
import com.eDecree.model.Response;
import com.eDecree.model.SubDocument;
import com.eDecree.service.CaseFileDetailService;
import com.eDecree.service.LookupService;
import com.eDecree.service.MasterService;
import com.eDecree.service.SubDocumentService;
import com.eDecree.utility.GlobalFunction;
import com.itextpdf.text.pdf.PdfReader;

@Controller
@RequestMapping("/judgement")
public class JudgementController {
	
	@Autowired
	private LookupService lookupService;
	
	@Autowired
	private MasterService masterService;
	
	@Autowired
	private CaseFileDetailService caseFileDetailService;	
	
	@Autowired
	private SubDocumentService subDocumentService;
	
	private GlobalFunction globalfunction;	
	
	public JudgementController() {
		// registrationPartyValidation = new RegistrationpartyValidation();
		globalfunction = new GlobalFunction();
	}
	@RequestMapping(value="upload",method=RequestMethod.POST)
	  public  ResponseEntity<String> upload(@RequestParam("file") MultipartFile file,CaseFileDetail caseFileDetail,ModelAndView modelAndView) throws IOException
	  {
		boolean success=false;
		System.out.println("caseyear = "+caseFileDetail.getFd_case_year());
		String jsonData="";
		CaseType caseType=masterService.getCaseTypeByLabel(caseFileDetail.getCase_type());
		caseFileDetail.setFd_case_type(caseType.getCt_id());
		String expectedPattern = "yyyy-MM-dd";
   	 	SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
   	 	Date judgementDate = new Date();
		try {
			judgementDate = formatter.parse(caseFileDetail.getJudgement_date());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		caseFileDetail=caseFileDetailService.getCaseFile(caseFileDetail);
		Lookup lookup=lookupService.getLookUpObject("REPOSITORYPATH");
		IndexField indexField=masterService.getIndexFieldByName("order_sheet");
		Integer count=subDocumentService.getCount(caseFileDetail.getFd_id());
		count=count+1;
		String filename=caseFileDetail.getCaseType().getCt_label()+caseFileDetail.getFd_case_no()+caseFileDetail.getFd_case_year()+"_"+indexField.getIf_type_code()+"_"+count;
		String newfilepath=lookup.getLk_longname()+File.separator+caseFileDetail.getCaseType().getCt_label()+File.separator+indexField.getIf_name()+File.separator+filename+".pdf";
		
		try 
        {
        	 byte[] bytes = file.getBytes();	        	 
        	 FileOutputStream fos =new FileOutputStream(newfilepath);

        	 fos.write(bytes);
        	 fos.close();
        	 
        	 SubDocument subDocument=new SubDocument();
 			subDocument.setSd_cr_by(1L);
 			subDocument.setSd_cr_date(new Date());
 			subDocument.setSd_fd_mid(caseFileDetail.getFd_id());
 			subDocument.setSd_if_mid(indexField.getIf_id());
 			subDocument.setSd_version(1);
 			subDocument.setSd_submitted_date(judgementDate);
 			subDocument.setSd_document_name(filename);
 			subDocument.setSd_judgement_id(caseFileDetail.getJudgement_id());
 			File source=new File(newfilepath);
			PdfReader reader = new PdfReader(source.getAbsolutePath());
			Integer no_of_pages = reader.getNumberOfPages();
	       	subDocument.setSd_no_of_pages(no_of_pages);
			
	       	subDocumentService.save(subDocument);
	       	return new ResponseEntity<String>("true",  HttpStatus.OK);
        }
        catch (IOException ioe) 
        {
        	ioe.printStackTrace();
        	return new ResponseEntity<String>("false",HttpStatus.BAD_REQUEST);
        	
        }
		
    	
	}
	
	@RequestMapping(value="create",method=RequestMethod.POST)
	  public  ResponseEntity<String> create(@RequestParam("file") MultipartFile file,CaseFileDetail caseFileDetail,ModelAndView modelAndView)
	  {
		Response response=new Response();
		
		System.out.println("caseyear = "+caseFileDetail.getFd_case_year());
		String jsonData="";
		CaseType caseType=masterService.getCaseTypeByLabel(caseFileDetail.getCase_type());
		Long judgment_id=caseFileDetail.getJudgement_id();
		caseFileDetail.setFd_case_type(caseType.getCt_id());
		String expectedPattern = "yyyy-MM-dd";
 	 	SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
 	 	Date judgementDate = new Date();
		try {
			judgementDate = formatter.parse(caseFileDetail.getJudgement_date());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		caseFileDetail=caseFileDetailService.getCaseFile(caseFileDetail);
		if(caseFileDetail==null){
			response.setSuccess("False");
			response.setMessage("Case details not found");
			jsonData=globalfunction.convert_to_json(response);
			return new ResponseEntity<String>(jsonData,  headers,HttpStatus.OK);
		}
		Lookup lookup=lookupService.getLookUpObject("REPOSITORYPATH");
		IndexField indexField=masterService.getIndexFieldByName("order_sheet");
		Integer count=subDocumentService.getCount(caseFileDetail.getFd_id());
		count=count+1;
		String filename=caseFileDetail.getCaseType().getCt_label()+caseFileDetail.getFd_case_no()+caseFileDetail.getFd_case_year()+"_"+indexField.getIf_type_code()+"_"+count;
		String newfilepath=lookup.getLk_longname()+File.separator+caseFileDetail.getCaseType().getCt_label()+File.separator+indexField.getIf_name()+File.separator+filename+".pdf";
		
		try 
      {
      	 byte[] bytes = file.getBytes();	        	 
      	 FileOutputStream fos =new FileOutputStream(newfilepath);

      	 fos.write(bytes);
      	 fos.close();
      	 
      	 SubDocument subDocument=new SubDocument();
			subDocument.setSd_cr_by(1L);
			subDocument.setSd_cr_date(new Date());
			subDocument.setSd_fd_mid(caseFileDetail.getFd_id());
			subDocument.setSd_if_mid(indexField.getIf_id());
			subDocument.setSd_version(1);
			subDocument.setSd_document_id(100002);
			subDocument.setSd_submitted_date(judgementDate);
			subDocument.setSd_document_name(filename);
			subDocument.setSd_judgement_id(judgment_id);
			subDocument.setSd_rec_status(1);
			File source=new File(newfilepath);
			PdfReader reader = new PdfReader(source.getAbsolutePath());
			Integer no_of_pages = reader.getNumberOfPages();
	       	subDocument.setSd_no_of_pages(no_of_pages);
			
	       	subDocumentService.save(subDocument);
	       	response.setSuccess("True");
			response.setMessage("Judgment file uploaded");
			jsonData=globalfunction.convert_to_json(response);
	       	return new ResponseEntity<String>(jsonData,headers,  HttpStatus.OK);
      }
      catch (IOException ioe) 
      {
      	ioe.printStackTrace();
      	response.setSuccess("False");
		response.setMessage("Something went wrong");
		jsonData=globalfunction.convert_to_json(response);
      	
		return new ResponseEntity<String>(jsonData,headers,HttpStatus.BAD_REQUEST);      	
      }
		
  	
	}
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public  ResponseEntity<String> delete(@PathVariable("id") Long judgement_id)
	{
		Response response=new Response();
		String jsonData="";
		
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		SubDocument subDocument=subDocumentService.getByJudgementID(judgement_id);
		if(subDocument==null){
			response.setSuccess("False");
			response.setMessage("Judgement not found");
			jsonData=globalfunction.convert_to_json(response);
	      	
			return new ResponseEntity<String>(jsonData,headers,HttpStatus.BAD_REQUEST);   
		}else{
			subDocument.setSd_rec_status(2);
			subDocumentService.save(subDocument);
			response.setSuccess("True");
			response.setMessage("Judgement deleted successfully");
			jsonData=globalfunction.convert_to_json(response);	      	
			return new ResponseEntity<String>(jsonData,headers,HttpStatus.OK);
		}
		
		
		
	}
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public  @ResponseBody String test()
	{
		return "success";
	}
}
