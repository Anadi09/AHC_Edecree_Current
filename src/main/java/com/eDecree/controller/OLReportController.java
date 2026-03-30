package com.eDecree.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eDecree.model.ActionResponse;
import com.eDecree.model.Lookup;
import com.eDecree.service.LookupService;
import com.eDecree.utility.GlobalFunction;
import com.efiling.model.OLReport;
import com.efiling.service.LookupServiceEfiling;
import com.efiling.service.OLReportService;
import com.itextpdf.text.pdf.PdfReader;

@Controller
@RequestMapping("/olreport")
public class OLReportController {
	@Autowired
	ServletContext context;
	
	@Autowired
	private LookupServiceEfiling lookupService;
	
	@Autowired
	private LookupService lookupServiceDMS;
	
	@Autowired
	private OLReportService olReportService;
		
	private GlobalFunction globalfunction;
	
	public OLReportController() {
		// registrationPartyValidation = new RegistrationpartyValidation();
		globalfunction = new GlobalFunction();
	}
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manage()
	{
		return "/olreport/manage";
	}
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public @ResponseBody String getall()
	{
		ActionResponse<OLReport> response = new ActionResponse<OLReport>();
		String jsonData="";
		List<OLReport> olReports=olReportService.getAll();
		response.setModelList(olReports);
		response.setResponse("TRUE");
		jsonData=globalfunction.convert_to_json(response);
		return jsonData;
	}
	
	@RequestMapping(value = "/searchOLR", method = RequestMethod.POST)
	public @ResponseBody String searchOLR(@RequestBody OLReport olrreport, HttpSession session ) 
	{
		ActionResponse<OLReport> response = new ActionResponse<OLReport>();
		String jsonData="";
		
		List<OLReport> olReports=olReportService.getByAppNoYearDate(olrreport);
		response.setModelList(olReports);
		response.setResponse("TRUE");
		jsonData=globalfunction.convert_to_json(response);
		return jsonData;
	}
	
	@RequestMapping(value = "/viewdocument/{id}", method = RequestMethod.GET)
	public void copysubdocument(@PathVariable("id") Long ol_id,HttpServletRequest request,
			HttpServletResponse response,Model model) throws IOException 
	{
		
		OLReport olReport = olReportService.getByPK(ol_id);
		Lookup lookup=lookupServiceDMS.getLookUpObject("EFILING_PATH");
		String filename=lookup.getLk_longname()+File.separator+"olReport"+File.separator+olReport.getOl_id()+"_"+olReport.getOl_year()+".pdf";
		System.out.println("filename="+filename);
		File file = new File(filename);
		response.setHeader("content-disposition", "inline" );
		response.setContentType("application/pdf");       
		response.setContentLength((int)file.length());
		  
		ServletOutputStream out = response.getOutputStream();
		FileInputStream stream = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(stream);
		BufferedOutputStream bos = new BufferedOutputStream(out);
		  
		byte[] buff = new byte[2048];
		int bytesRead;
		  
		while(-1 != (bytesRead = bis.read(buff, 0, buff.length))) 
		{
		bos.write(buff, 0, bytesRead);
		}
		
		
		/*Lookup lookupRepo = lookupServiceDMS.getLookUpObject("REPOSITORYPATH");
		File source = new File(lookup.getLk_longname()+File.separator+olReport.getOl_id()+"_"+olReport.getOl_year()+".pdf");
		File dest =new File ( lookupRepo.getLk_longname() + File.separator+ "OLR File" + File.separator
				+olReport.getOl_id()+"_"+olReport.getOl_year()+".pdf");
		
		System.out.println("dest--"+dest);
		
		model.addAttribute("document_name",file.getName());
		
		System.out.println("file.getName()----"+file.getName());
		
		try {
			FileUtils.copyFile(source, dest);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String uploadPath = context.getRealPath("");
		File uploaderdest = new File(uploadPath + File.separator + "uploads"
				+ File.separator+ "OLR File" + File.separator
				+olReport.getOl_id()+"_"+olReport.getOl_year()+".pdf");
		
		System.out.println("uploaderdest--"+uploaderdest);

		try {
			FileUtils.copyFile(source, uploaderdest);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		
		olReport.setRead_status(1);
		olReportService.save(olReport);
		bis.close();
		stream.close();
		bos.close();
		out.close();
		
	}
	
	@RequestMapping(value = "/resetStatus/{id}", method = RequestMethod.GET)
	public void resetStatus(@PathVariable("id") Long ol_id,HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		OLReport olReport = olReportService.getByPK(ol_id);
		olReport.setRead_status(1);
		olReportService.save(olReport);
		
	}
	
	
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public void remove(@PathVariable("id") Long ol_id,HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		OLReport olReport = olReportService.getByPK(ol_id);
		olReport.setRead_status(0);
		OLReport olr =olReportService.save(olReport);
		
		System.out.println("old ----------"+olr);
		
	}
}
