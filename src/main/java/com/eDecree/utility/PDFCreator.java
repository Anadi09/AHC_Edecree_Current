package com.eDecree.utility;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import com.eDecree.model.OrderReport;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFCreator {
	
	public static void generatePDF(List<OrderReport> orList, String pdfname){
		Document doc = new Document();
  	  PdfWriter docWriter = null;

  	  DecimalFormat df = new DecimalFormat("0.00");

  	   
  	   //special font sizes
  	   Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0)); 
  	   Font bf12 = new Font(FontFamily.TIMES_ROMAN, 12); 

  	   //file path
  	   String path = pdfname;
  	   try {
		docWriter = PdfWriter.getInstance(doc , new FileOutputStream(path));
		doc.addAuthor("Allahabad High Court");
	  	   doc.addCreationDate();
	  	   doc.addProducer();
	  	   doc.setPageSize(PageSize.A4);
	  	  
	  	   //open document
	  	   doc.open();
	  	   float[] columnWidths = {2f, 7f};
	  	   //create PDF table with the given widths
	  	   PdfPTable table = new PdfPTable(columnWidths);
	  	   Paragraph paragraph = new Paragraph();
	  	   // set table width a percentage of the page width
	  	   table.setWidthPercentage(90f);

	  	   //insert column headings
	  	   insertCell(table, "Date", Element.ALIGN_LEFT, 1, bfBold12);
	  	   insertCell(table, "Report Data", Element.ALIGN_LEFT, 1, bfBold12);
	  	   //table.setHeaderRows(1);
	  	   
	  	   //insertCell(table, "", Element.ALIGN_LEFT, 4, bfBold12);
	  	   for(OrderReport or:orList){
	  		 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	 		String date = formatter.format(or.getOrd_created());
	 		insertCell(table, date, Element.ALIGN_LEFT, 1, bf12);
	 		insertCell(table, or.getOrd_remark(), Element.ALIGN_LEFT, 1, bf12);
	  	   }
	  	   paragraph.add(table);
	  	   // add the paragraph to the document
	  	   doc.add(paragraph);
	  	   
	  	   doc.close();
	  	 docWriter.close();
  	   } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
	  	{
			   if (doc != null){
			    //close the document
			    doc.close();
			   }
			   if (docWriter != null){
			    //close the writer
			    docWriter.close();
			   }
		}
  	   
  	}
	 private static void insertCell(PdfPTable table, String text, int align, int colspan, Font font){
   	  
   	  //create a new cell with the specified Text and Font
   	  PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
   	  //set the cell alignment
   	  cell.setHorizontalAlignment(align);
   	  //set the cell column span in case you want to merge two or more cells
   	  cell.setColspan(colspan);
   	  //in case there is no text and you wan to create an empty row
   	  if(text.trim().equalsIgnoreCase("")){
   	   cell.setMinimumHeight(10f);
   	  }
   	  //add the call to the table
   	  table.addCell(cell);
   	  
   	 }
		public static void createPDF(OrderReport orderReport, String pdfname){
			Document doc = new Document();
	  	  PdfWriter docWriter = null;

	  	  DecimalFormat df = new DecimalFormat("0.00");

	  	   
	  	   //special font sizes
	  	   Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0)); 
	  	   Font bf12 = new Font(FontFamily.TIMES_ROMAN, 12); 

	  	   //file path
	  	   String path = pdfname;
	  	   try {
			docWriter = PdfWriter.getInstance(doc , new FileOutputStream(path));
			doc.addAuthor("Allahabad High Court");
		  	   doc.addCreationDate();
		  	   doc.addProducer();
		  	   doc.setPageSize(PageSize.A4);
		  	  
		  	   //open document
		  	   doc.open();
		  	   float[] columnWidths = {2f, 7f};
		  	   //create PDF table with the given widths
		  	   PdfPTable table = new PdfPTable(columnWidths);
		  	   Paragraph paragraph = new Paragraph();
		  	   // set table width a percentage of the page width
		  	   table.setWidthPercentage(90f);

		  	   //insert column headings
		  	   insertCell(table, "Date", Element.ALIGN_LEFT, 1, bfBold12);
		  	   insertCell(table, "Report Data", Element.ALIGN_LEFT, 1, bfBold12);
		  	   //table.setHeaderRows(1);
		  	   
		  	   //insertCell(table, "", Element.ALIGN_LEFT, 4, bfBold12);
		  		 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		 		String date = formatter.format(orderReport.getOrd_created());
		 		insertCell(table, date, Element.ALIGN_LEFT, 1, bf12);
		 		insertCell(table, orderReport.getOrd_remark(), Element.ALIGN_LEFT, 1, bf12);
		  	   paragraph.add(table);
		  	   // add the paragraph to the document
		  	   doc.add(paragraph);
		  	   
		  	   doc.close();
		  	 docWriter.close();
	  	   } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally
		  	{
				   if (doc != null){
				    //close the document
				    doc.close();
				   }
				   if (docWriter != null){
				    //close the writer
				    docWriter.close();
				   }
			}
	  	   
	  	}

}
