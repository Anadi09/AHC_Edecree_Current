package com.eDecree.utility;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.eDecree.model.ApplicationNotice;
import com.eDecree.model.CaseNotice;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class PDFGenerate {

	public static void createApplicationNoticeRptPdf(ApplicationNotice  applicationNotice1,String path ) throws IOException {
		Document document=new Document();
		ApplicationNotice  applicationNotice =new ApplicationNotice();
		applicationNotice.setA1(applicationNotice1.getA1());
		applicationNotice.setA2(applicationNotice1.getA2());
		applicationNotice.setA3(applicationNotice1.getA3());
		applicationNotice.setA4(applicationNotice1.getA4());
		applicationNotice.setA5(applicationNotice1.getA5());
		applicationNotice.setA6(applicationNotice1.getA6());
		applicationNotice.setA7(applicationNotice1.getA7());
		applicationNotice.setA8(applicationNotice1.getA8());
		applicationNotice.setA9(applicationNotice1.getA9());
		applicationNotice.setA10(applicationNotice1.getA10());
		applicationNotice.setA11(applicationNotice1.getA11());
		applicationNotice.setA12(applicationNotice1.getA12());
		applicationNotice.setA13(applicationNotice1.getA13());
		applicationNotice.setA14(applicationNotice1.getA14());
		applicationNotice.setA15(applicationNotice1.getA15());
		applicationNotice.setA16(applicationNotice1.getA16());
		applicationNotice.setA17(applicationNotice1.getA17());
		applicationNotice.setA18(applicationNotice1.getA18());
		
		
		
		if(applicationNotice.getA1()!=null)
		{
			/*applicationNotice.setA1("2325");*/
		}
		else {
			applicationNotice.setA1("_______________");
		}
		
		if(applicationNotice.getA2()!=null)
		{
			/*applicationNotice.setA2("2020");*/
		}
		else {
			applicationNotice.setA2("_______________");
		}
		
		if(applicationNotice.getA3()!=null)
		{
			/*applicationNotice.setA3("5256");*/
		}
		else {
			applicationNotice.setA3("_______________");
		}
		
		if(applicationNotice.getA4()!=null)
		{
			/*applicationNotice.setA4("2021");*/
		}
		else {
			applicationNotice.setA4("_______________");
		}
		
		if(applicationNotice.getA5()!=null)
		{
			/*applicationNotice.setA5("Aman")*/;
		}
		else {
			applicationNotice.setA5("_______________");
		}
		
		if(applicationNotice.getA6()!=null)
		{
			/*applicationNotice.setA6("Arjun");*/
		}
		else {
			applicationNotice.setA6("_______________");
		}
		
		if(applicationNotice.getA7()!=null)
		{
			/*applicationNotice.setA7("      The Judicature\\n      Ashok Nagar,\\n      High Court Allahabad");*/
		}
		else {
			applicationNotice.setA7("_______________");
		}

		if(applicationNotice.getA8()!=null)
		{
			/*applicationNotice.setA8("sdgdshsfdhf");*/
		}
		else {
			applicationNotice.setA8("_______________");
		}
		
		if(applicationNotice.getA9()!=null)
		{
			/*applicationNotice.setA9("fdhjfgjghkhg");*/
		}
		else {
			applicationNotice.setA9("_______________");
		}
		
		if(applicationNotice.getA10()!=null)
		{
			/*applicationNotice.setA10("regfdhjhkl");*/
		}
		else {
			applicationNotice.setA10("_______________");
		}
		
		if(applicationNotice.getA11()!=null)
		{
			/*applicationNotice.setA11("swdwdscfvf");*/
		}
		else {
			applicationNotice.setA11("_______________");
		}
		
		if(applicationNotice.getA12()!=null)
		{
			//applicationNotice.setA12("gmghjghfff");
		}
		else {
			applicationNotice.setA12("_______________");
		}
		
		if(applicationNotice.getA13()!=null)
		{
			//applicationNotice.setA13("sdhdjkffgf");
		}
		else {
			applicationNotice.setA13("_______________");
		}
		
		if(applicationNotice.getA14()!=null)
		{
			//applicationNotice.setA14("Arjun");
		}
		else {
			applicationNotice.setA14("_______________");
		}
	
		if(applicationNotice.getA15()!=null)
		{
			//applicationNotice.setA15("20/12/2020");
		}
		else {
			applicationNotice.setA15("_______________");
		}
		
		if(applicationNotice.getA16()!=null)
		{
			//applicationNotice.setA16("500");
		}
		else {
			applicationNotice.setA16("_______________");
		}
		
		if(applicationNotice.getA17()!=null)
		{
			//applicationNotice.setA17("20/1/2021");
		}
		else {
			applicationNotice.setA17("_______________");
		}
	
		HTMLWorker htmlWorker = new HTMLWorker(document);

		Font underline =new Font(Font.FontFamily.UNDEFINED  , 16, Font.BOLD);
		Font underline1 =new Font(Font.FontFamily.UNDEFINED  , 9, Font.BOLD);
		PdfWriter writer;
		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(path));

			document.open();

			Chunk title1=new Chunk("IN THE HIGH COURT OF JUDICATURE AT ALLAHABAD",underline);
			Chunk title2=new Chunk("                        Civil SIDE                                                                               (Chapter XII, Rules 1 and 7) ",underline1);
			/*title1.setUnderline(0.1f, -2f);*/

			Paragraph title=new Paragraph(title1);
			title.setAlignment(Element.ALIGN_CENTER);

			Paragraph par=new Paragraph(title2);
			par.setAlignment(Element.ALIGN_LEFT);

			document.add(title);			
			document.add(par);	
			document.add(Chunk.NEWLINE);
			
			Font underline0 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.NORMAL);
			Chunk title0=new Chunk("Miscellaneous Application",underline0);
			Paragraph para0=new Paragraph(title0);
			para0.setAlignment(Element.ALIGN_CENTER);
			document.add(para0);
			
			Font underline4 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.NORMAL);
			Chunk title4=new Chunk(" No."+"  "+applicationNotice.getA1()+"  of"+"  "+applicationNotice.getA2(),underline4);
			Paragraph para1=new Paragraph(title4);
			para1.setAlignment(Element.ALIGN_RIGHT);
			document.add(para1);

			Font underline5 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.NORMAL);
			Chunk title5=new Chunk(" No."+"  "+applicationNotice.getA3()+"  of"+"  "+applicationNotice.getA4(),underline5);
			Paragraph para2=new Paragraph(title5);
			para2.setAlignment(Element.ALIGN_RIGHT);
			document.add(para2);

			Font underline6 =new Font(Font.FontFamily.UNDEFINED  , 10, Font.NORMAL);
			Chunk title6=new Chunk(applicationNotice.getA5(),underline6);
			Paragraph para3=new Paragraph(title6);
			para3.setAlignment(Element.ALIGN_CENTER);
			document.add(para3);


			Font underline2 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.BOLD);
			Chunk title3=new Chunk("Versus",underline2);
			title3.setUnderline(0.1f, -2f);
			Paragraph para=new Paragraph(title3);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);


			Font underline7 =new Font(Font.FontFamily.UNDEFINED  , 10, Font.NORMAL);
			Chunk title7=new Chunk(applicationNotice.getA6(),underline7);
			Paragraph para4=new Paragraph(title7);
			para4.setAlignment(Element.ALIGN_CENTER);
			document.add(para4);
			document.add(new Chunk().NEWLINE);

			Font underline8 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.NORMAL);
			Chunk title8=new Chunk("To,",underline8);
			Paragraph para5=new Paragraph(title8);
			para5.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(para5);

			Font underline9 =new Font(Font.FontFamily.UNDEFINED  , 10, Font.BOLD);
			Chunk title9=new Chunk("     "+applicationNotice.getA7(),underline9);
			Paragraph para6=new Paragraph(title9);
			para6.setAlignment(Element.ALIGN_JUSTIFIED);
			document.add(para6);
			document.add(new Chunk().NEWLINE);

			Font underline10 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.NORMAL);
			Chunk title10=new Chunk("Whereas the above mentioned applicant has made an application to the court for "+applicationNotice.getA8()+" "+"in the above noted case you are hereby",underline10);
			Paragraph para7=new Paragraph(title10);
			para7.setAlignment(Element.ALIGN_RIGHT);
			document.add(para7);

			Font underline11 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.NORMAL);
			Chunk title11=new Chunk("called upon to enter appearance on or before the"+" "+applicationNotice.getA9()+" "+"day of"+" "+applicationNotice.getA10()+" "+"to show cause why the application be not granted. The said application will be on such day thereafter as may be subsequently notified  in accordance with the rules.",underline11);
			Paragraph para8=new Paragraph(title11);
			para8.setAlignment(Element.ALIGN_LEFT);
			document.add(para8);
			document.add(new Chunk().NEWLINE);
			Font underline12 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.NORMAL);
			Chunk title12=new Chunk("Take Notice that in default of appearance on or before the day before mentioned in person are by advocate or by some person by law ",underline12);
			Paragraph para9=new Paragraph(title12);
			para9.setAlignment(Element.ALIGN_CENTER);
			document.add(para9);

			Font underline13 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.NORMAL);
			Chunk title13=new Chunk("authorised to act on your behalf the application will be heard and determined in your absence.",underline13);
			Paragraph para10=new Paragraph(title13);
			para10.setAlignment(Element.ALIGN_LEFT);
			document.add(para10);
			document.add(new Chunk().NEWLINE);
			Font underline14 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.NORMAL);
			Chunk title14=new Chunk("A copy of the application together with a copy of the affidavit filed by the application is annexed here to.",underline14);
			Paragraph para11=new Paragraph(title14);
			para11.setAlignment(Element.ALIGN_CENTER);
			document.add(para11);

			Font underline15 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.NORMAL);
			Chunk title15=new Chunk("Given under my hand the seal of the Court this, the"+" "+applicationNotice.getA9()+" "+"day of"+" "+applicationNotice.getA12()+" "+applicationNotice.getA13(),underline15);
			Paragraph para12=new Paragraph(title15);
			para12.setAlignment(Element.ALIGN_LEFT);
			document.add(para12);
			document.add(new Chunk().NEWLINE);
			Font underline16 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.NORMAL);
			Chunk title16=new Chunk("Advocate for :"+" "+applicationNotice.getA14(),underline16);
			Paragraph para13=new Paragraph(title16);
			para13.setAlignment(Element.ALIGN_LEFT);
			document.add(para13);

			document.add(new Chunk().NEWLINE);
			Font underline17 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.NORMAL);
			Chunk title17=new Chunk("Date -"+" "+applicationNotice.getA15(),underline17);
			Paragraph para14=new Paragraph(title17);
			para14.setAlignment(Element.ALIGN_LEFT);
			document.add(para14);

			document.add(new Chunk().NEWLINE);
			Font underline18 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.BOLD);
			Chunk title18=new Chunk("Deputy Registrar",underline18);
			Paragraph para15=new Paragraph(title18);
			para15.setAlignment(Element.ALIGN_RIGHT);
			document.add(para15);

			Font underline19 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.BOLD);
			Chunk title19=new Chunk("Allahabad/Lucknow",underline19);
			Paragraph para16=new Paragraph(title19);
			para16.setAlignment(Element.ALIGN_RIGHT);
			document.add(para16);
			document.add(new Chunk().NEWLINE);
			document.add(new Chunk().NEWLINE);
			document.add(new Chunk().NEWLINE);
			document.add(new Chunk().NEWLINE);

			Font underline20 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.NORMAL);
			Chunk title20=new Chunk("Note- A process fee of Rs"+" "+applicationNotice.getA16()+" "+"Chargeable under",underline19);
			Paragraph para17=new Paragraph(title20);
			para17.setAlignment(Element.ALIGN_LEFT);
			document.add(para17);
			document.add(new Chunk().NEWLINE);

			Font underline21 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.NORMAL);
			Chunk title21=new Chunk("Date -"+" "+applicationNotice.getA17(),underline21);
			Paragraph para18=new Paragraph(title21);
			para18.setAlignment(Element.ALIGN_LEFT);
			document.add(para18);
			document.add(new Chunk().NEWLINE);

			Chunk titlee=new Chunk("______________________________________________________________________________");
			titlee.setUnderline(0.7f, -1.0f);
			document.add(titlee);
			document.add(new Chunk().NEWLINE);
			
			Font underline22 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.NORMAL);
			Chunk title22=new Chunk("Chapter XXXVII, Rule 2 of Rules of Court, author has been paid.",underline22);
			Paragraph para19=new Paragraph(title22);
			para19.setAlignment(Element.ALIGN_LEFT);
			document.add(para19);

			document.add(new Chunk().NEWLINE);
			document.add(new Chunk().NEWLINE);
			document.add(Chunk.NEWLINE);

			document.close();
			writer.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (com.itextpdf.text.DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		public static void createCaseNoticeRptPdf(CaseNotice  caseNotice1,String path ) throws IOException {
			Document document=new Document();
			
			CaseNotice  caseNotice =new CaseNotice();
			caseNotice.setA1(caseNotice1.getA1());
			caseNotice.setA2(caseNotice1.getA2());
			caseNotice.setA3(caseNotice1.getA3());
			caseNotice.setA4(caseNotice1.getA4());
			caseNotice.setA5(caseNotice1.getA5());
			caseNotice.setA6(caseNotice1.getA6());
			caseNotice.setA7(caseNotice1.getA7());
			caseNotice.setA8(caseNotice1.getA8());
			caseNotice.setA9(caseNotice1.getA9());
			caseNotice.setA10(caseNotice1.getA10());
			caseNotice.setA11(caseNotice1.getA11());
			caseNotice.setA12(caseNotice1.getA12());
			caseNotice.setA13(caseNotice1.getA13());
			caseNotice.setA14(caseNotice1.getA14());
			caseNotice.setA15(caseNotice1.getA15());
			caseNotice.setA16(caseNotice1.getA16());
			caseNotice.setA17(caseNotice1.getA17());
			caseNotice.setA18(caseNotice1.getA18());
			caseNotice.setA19(caseNotice1.getA19());
			caseNotice.setA20(caseNotice1.getA20());
			caseNotice.setA21(caseNotice1.getA21());
			caseNotice.setA22(caseNotice1.getA22());
			
			
			if(caseNotice.getA1()!=null)
			{
				//caseNotice.setA1("2019");
			}
			else {
				caseNotice.setA1("________________");
			}
			
			if(caseNotice.getA2()!=null)
			{
				//caseNotice.setA2("543225");
			}
			else {
				caseNotice.setA2("________________");
			}
			
			if(caseNotice.getA3()!=null)
			{
				//caseNotice.setA3("2020");
			}
			else {
				caseNotice.setA3("________________");
			}
			
			if(caseNotice.getA4()!=null)
			{
				//caseNotice.setA4("Aman");
			}
			else {
				caseNotice.setA4("________________");
			}
			
			if(caseNotice.getA5()!=null)
			{
				//caseNotice.setA5("Arjun");
			}
			else {
				caseNotice.setA5("________________");
			}
			
			if(caseNotice.getA6()!=null)
			{
				//caseNotice.setA6("jhjdhkjsdhgjfgjfgjgjgjfgjfgjgjfghdghdhghgg");
			}
			else {
				caseNotice.setA6("________________");
			}
			
			if(caseNotice.getA7()!=null)
			{
				//caseNotice.setA7("04/05/2019");
			}
			else {
				caseNotice.setA7("________________");
			}
			
			if(caseNotice.getA8()!=null)
			{
				//caseNotice.setA8("2019");
			}
			else {
				caseNotice.setA8("________________");
			}
			
			if(caseNotice.getA9()!=null)
			{
				//caseNotice.setA9("Kamal");
			}
			else {
				caseNotice.setA9("________________");
			}
			
			if(caseNotice.getA10()!=null)
			{
				//caseNotice.setA10("Allahabad");
			}
			else {
				caseNotice.setA10("________________");
			}
			
			if(caseNotice.getA11()!=null)
			{
				//caseNotice.setA11("02/02/2019");
			}
			else {
				caseNotice.setA11("________________");
			}
			
			if(caseNotice.getA12()!=null)
			{
				//caseNotice.setA12("wqdasdfgfgjdfgjgfdjfhdjdfhjfdhdghsdhsdhsdh");
			}
			else {
				caseNotice.setA12("________________");
			}
			
			if(caseNotice.getA13()!=null)
			{
				//caseNotice.setA13("      The Judicature\n      Ashok Nagar,\n      High Court Allahabad");
			}
			else {
				caseNotice.setA13("________________");
			}
		
			if(caseNotice.getA14()!=null)
			{
				//caseNotice.setA14("wetewyughgf");
			}
			else {
				caseNotice.setA14("________________");
			}
			
			if(caseNotice.getA15()!=null)
			{
				//caseNotice.setA15("22/11/2020");
			}
			else {
				caseNotice.setA15("________________");
			}
			
			if(caseNotice.getA16()!=null)
			{
				//caseNotice.setA16("20/12/2020");
			}
			else {
				caseNotice.setA16("________________");
			}

			if(caseNotice.getA17()!=null)
			{
				//caseNotice.setA17("2020");
			}
			else {
				caseNotice.setA17("________________");
			}
			
			if(caseNotice.getA18()!=null)
			{
				//caseNotice.setA18("22/12/2020");
			}
			else {
				caseNotice.setA18("________________");
			}
			
			if(caseNotice.getA19()!=null)
			{
				//caseNotice.setA19("2020");
			}
			else {
				caseNotice.setA19("________________");
			}
			
			if(caseNotice.getA20()!=null)
			{
				//caseNotice.setA20("Aman rai");
			}
			else {
				caseNotice.setA20("________________");
			}
			
			if(caseNotice.getA21()!=null)
			{
				//caseNotice.setA21("30/12/2020");
			}
			else {
				caseNotice.setA21("________________");
			}
			
			if(caseNotice.getA22()!=null)
			{
				//caseNotice.setA22("150");
			}
			else {
				caseNotice.setA22("________________");
			}
			

			HTMLWorker htmlWorker = new HTMLWorker(document);
			PdfWriter writer;
			
			try {
				writer = PdfWriter.getInstance(document, new FileOutputStream(path));

				document.open();

				Font underline1 =new Font(Font.FontFamily.UNDEFINED  , 16, Font.BOLD);
				Font underline2 =new Font(Font.FontFamily.UNDEFINED  , 9, Font.BOLD);
				Font underline3 =new Font(Font.FontFamily.UNDEFINED  , 9, Font.BOLD);
				Font underline4 =new Font(Font.FontFamily.UNDEFINED  , 9, Font.BOLD);
				Chunk chunk1=new Chunk("IN THE HIGH COURT OF JUDICATURE AT ALLAHABAD",underline1);
				Chunk chunk2=new Chunk("NOTICE OF REVISION",underline2);
				Chunk chunk3=new Chunk("(Chapter XII,Rules 1 and 7)",underline3);
				Chunk chunk4=new Chunk("Revisional Jurisdiction",underline4);
				Paragraph para1=new Paragraph(chunk1);
				Paragraph para2=new Paragraph(chunk2);
				Paragraph para3=new Paragraph(chunk3);
				Paragraph para4=new Paragraph(chunk4);
				para1.setAlignment(Element.ALIGN_CENTER);
				para2.setAlignment(Element.ALIGN_CENTER);
				para3.setAlignment(Element.ALIGN_CENTER);
				para4.setAlignment(Element.ALIGN_CENTER);
				document.add(para1);	
				document.add(para2);
				document.add(para3);
				document.add(para4);
				document.add(Chunk.NEWLINE);
				
				Font underline5 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.NORMAL);
				Chunk title5=new Chunk(caseNotice.getA1()+" "+"No."+" "+caseNotice.getA2()+" "+"of"+" "+caseNotice.getA3(),underline4);
				Paragraph para5=new Paragraph(title5);
				para5.setAlignment(Element.ALIGN_CENTER);
				document.add(para5);
				document.add(new Chunk().NEWLINE);
				
				Font underline6 =new Font(Font.FontFamily.UNDEFINED  , 10, Font.NORMAL);
				Chunk title6=new Chunk(caseNotice.getA4(),underline6);
				Paragraph para6=new Paragraph(title6);
				para6.setAlignment(Element.ALIGN_CENTER);
				document.add(para6);


				Font underline7 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.BOLD);
				Chunk title7=new Chunk("Versus",underline7);
				title7.setUnderline(0.1f, -2f);
				Paragraph para7=new Paragraph(title7);
				para7.setAlignment(Element.ALIGN_CENTER);
				document.add(para7);


				Font underline8 =new Font(Font.FontFamily.UNDEFINED  , 10, Font.NORMAL);
				Chunk title8=new Chunk(caseNotice.getA5(),underline8);
				Paragraph para8=new Paragraph(title8);
				para8.setAlignment(Element.ALIGN_CENTER);
				document.add(para8);
				document.add(new Chunk().NEWLINE);

				Font underline9 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.NORMAL);
				Chunk title9=new Chunk("Arision our of"+" "+caseNotice.getA6()+" "+"on"+" "+caseNotice.getA7()+" "+"of"+" "+caseNotice.getA8()+" "+"disposed of by"+" "
				+caseNotice.getA9()+" "+"of"+" "+caseNotice.getA10()+" "+"on the"+" "+caseNotice.getA11()+" "+"of the"+" "+caseNotice.getA12(),underline9);
				Paragraph para9=new Paragraph(title9);
				para9.setAlignment(Element.ALIGN_CENTER);
				document.add(para9);
				document.add(new Chunk().NEWLINE);
				
				Font underline10 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.NORMAL);
				Chunk title10=new Chunk("To,",underline10);
				Paragraph para10=new Paragraph(title10);
				para10.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(para10);
				
				Font underline11 =new Font(Font.FontFamily.UNDEFINED  , 10, Font.BOLD);
				Chunk title11=new Chunk("     "+caseNotice.getA13(),underline11);
				Paragraph para11=new Paragraph(title11);
				para11.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(para11);
				document.add(new Chunk().NEWLINE);
				
		    	Font underline12 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.NORMAL);
				Chunk title12=new Chunk(caseNotice.getA14()+" Opposite Party",underline12);
				Paragraph para12=new Paragraph(title12);
				para12.setAlignment(Element.ALIGN_RIGHT);
				document.add(para12);

				Font underline13 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.NORMAL);
				Chunk title13=new Chunk("Whereas the applicant abovementioned has made an application to this court that it may call for the record of above noted case and pass such",underline13);
				Paragraph para13=new Paragraph(title13);
				para13.setAlignment(Element.ALIGN_CENTER);
				document.add(para13);
				
				Font underline14 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.NORMAL);
				Chunk title14=new Chunk("order as it any think tit, you are hereby called upon to inter appearance on or before the"+" "+caseNotice.getA15()+" day of "+caseNotice.getA16()+" "+caseNotice.getA17()+" to answer the application."+"The said application will be heared by Court on such day thereafter as may subrequently notified in coordance with the rules.",underline14);
				Paragraph para14=new Paragraph(title14);
				para14.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(para14);
				document.add(new Chunk().NEWLINE);
				
				Font underline15 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.NORMAL);
				Chunk title15=new Chunk("Take Notice that in deafalut of appearance on or before the day before mentioned in person are by advocate or by some person by law authorised",underline15);
				Paragraph para15=new Paragraph(title15);
				para15.setAlignment(Element.ALIGN_CENTER);
				document.add(para15);
				
				Font underline16 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.NORMAL);
				Chunk title16=new Chunk("to act on your behalf the application will be heard and determined in your absence.",underline16);
				Paragraph para16=new Paragraph(title16);
				para16.setAlignment(Element.ALIGN_LEFT);
				document.add(para16);
				document.add(new Chunk().NEWLINE);

				Font underline17 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.NORMAL);
				Chunk title17=new Chunk("       Given under my hand and the seal of court this day of.",underline17);
				Paragraph para17=new Paragraph(title17);
				para17.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(para17);
				
				Font underline18 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.NORMAL);
				Chunk title18=new Chunk(caseNotice.getA18()+" "+caseNotice.getA19(),underline18);
				Paragraph para18=new Paragraph(title18);
				para18.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(para18);
				document.add(new Chunk().NEWLINE);
				
				Font underline19 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.NORMAL);
				Chunk title19=new Chunk("Advocate : "+caseNotice.getA20(),underline19);
				Paragraph para19=new Paragraph(title19);
				para19.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(para19);
				
				Font underline20 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.NORMAL);
				Chunk title20=new Chunk("Date - "+caseNotice.getA21(),underline20);
				Paragraph para20=new Paragraph(title20);
				para20.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(para20);
				
				Font underline21 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.BOLD);
				Chunk title21=new Chunk("Deputy Registrar",underline21);
				Paragraph para21=new Paragraph(title21);
				para21.setAlignment(Element.ALIGN_RIGHT);
				document.add(para21);

				Font underline22 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.BOLD);
				Chunk title22=new Chunk("Allahabad/Lucknow",underline22);
				Paragraph para22=new Paragraph(title22);
				para22.setAlignment(Element.ALIGN_RIGHT);
				document.add(para22);
				document.add(new Chunk().NEWLINE);
				document.add(new Chunk().NEWLINE);
				
				Chunk titlee=new Chunk("______________________________________________________________________________");
				titlee.setUnderline(0.7f, -1.0f);
				document.add(titlee);
				document.add(new Chunk().NEWLINE);
				
				Font underline23 =new Font(Font.FontFamily.UNDEFINED  , 8, Font.BOLD);
				Chunk title23=new Chunk("Note-A process fee of Rs "+caseNotice.getA22()+" chargeable under Chapter XXXVII, Rule 2 of Rules of Court, 1955 be paid.",underline23);
				Paragraph para23=new Paragraph(title23);
				para23.setAlignment(Element.ALIGN_JUSTIFIED);
				document.add(para23);

				document.add(new Chunk().NEWLINE);
				document.add(new Chunk().NEWLINE);
				document.add(Chunk.NEWLINE);

				document.close();
				writer.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (com.itextpdf.text.DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
