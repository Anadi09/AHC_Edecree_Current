package com.eDecree.service;

import java.io.IOException;
import java.util.List;


import org.springframework.stereotype.Service;

import com.eDecree.model.CauseList;
import com.eDecree.utility.WriteHtmlFile;

@Service
public class WriteCauseListHtmlService {

public StringBuilder getStringForCaseList(List<CauseList> clList) {
	  StringBuilder htmlStringBuilder=new StringBuilder();
	  
	
	for(CauseList cl : clList) {
	try {
		
		
        //define a HTML String Builder
      
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
        WriteHtmlFile.WriteToFile(htmlStringBuilder.toString(),"testfile.html");
    } catch (IOException e) {
        e.printStackTrace();
    }
	}
	
	return null;
}

public StringBuilder getIndexPageForCauseList(List<CauseList> clList) {
	  StringBuilder htmlStringBuilder=new StringBuilder();
	  
	
	for(CauseList cl : clList) {
	try {
		
		
      //define a HTML String Builder
    
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
      WriteHtmlFile.WriteToFile(htmlStringBuilder.toString(),"testfile.html");
  } catch (IOException e) {
      e.printStackTrace();
  }
	}
	
	return null;
}	
	
}
