package com.eDecree.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.itextpdf.awt.geom.AffineTransform;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSmartCopy;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.SimpleBookmark;

public class PDFMerger {
// public static void main(String[] args) {
// List<InputStream> list = new ArrayList<InputStream>();
// try {
// // Source pdfs
// list.add(new FileInputStream(new File("f:/1.pdf")));
// list.add(new FileInputStream(new File("f:/2.pdf")));
//
// // Resulting pdf
// OutputStream out = new FileOutputStream(new File("f:/result.pdf"));
//
// doMerge(list, out);
//
// } catch (FileNotFoundException e) {
// e.printStackTrace();
// } catch (DocumentException e) {
// e.printStackTrace();
// } catch (IOException e) {
// e.printStackTrace();
// }
// }

	/**
	 * Merge multiple pdf into one pdf
	 * 
	 * @param list         of pdf input stream
	 * @param outputStream output file output stream
	 * @throws DocumentException
	 * @throws IOException
	 * 
	 * 
	 * 
	 */
	
	
	public static void doMerge(List<InputStream> list, OutputStream outputStream)
			throws DocumentException, IOException {
		Document document = new Document();

		
		PdfSmartCopy copy1 = new PdfSmartCopy(document, outputStream);

		copy1.setFullCompression();
		
		// BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

		document.open();
		int j = 0;

		for (InputStream in : list) {

			PdfReader reader = new PdfReader(in);

//pag=copy1.getPageNumber();
			
			List<HashMap<String, Object>> bookmarks = new ArrayList<HashMap<String, Object>>();
			List<HashMap<String, Object>> tempBookmarks;

			int pag = 0;

			int page_offset = 0;

			int totalPages = 0;

			pag = reader.getNumberOfPages();

			tempBookmarks = SimpleBookmark.getBookmark(reader);

			if (j == 0 && tempBookmarks != null) {

				SimpleBookmark.shiftPageNumbers(tempBookmarks, page_offset, null);

				page_offset += pag;
				if (tempBookmarks != null)
					bookmarks.addAll(tempBookmarks);
// MessageBox.Show(n.ToString());
				totalPages = pag;
			}

			else {
				SimpleBookmark.shiftPageNumbers(tempBookmarks, page_offset, null);
				if (tempBookmarks != null)
					bookmarks.addAll(tempBookmarks);

				page_offset += pag;
				totalPages += pag;
			}

			for (int i = 1; i <= reader.getNumberOfPages(); i++) {

				copy1.addPage(copy1.getImportedPage(reader, i));
			}
			
			copy1.setOutlines(bookmarks);
			copy1.freeReader(reader);
			reader.close();
			j++;
		}

		outputStream.flush();
		document.close();
		outputStream.close();
		copy1.close();

	}
	
	
	  public static void doMerge(List<InputStream> streamOfPDFFiles, OutputStream outputStream, boolean paginate) throws DocumentException {
		  Rectangle size=new Rectangle(612, 1040);
		 // PdfStamper stamper=null;
		    Document document = new Document(size);
		    List<HashMap<String, Object>> bookmarks = new ArrayList<HashMap<String, Object>>();
			List<HashMap<String, Object>> tempBookmarks;

			int pag = 0;
			
			int j = 0;

			int page_offset = 0;
		    try {
		      List<InputStream> pdfs = streamOfPDFFiles;
		      List<PdfReader> readers = new ArrayList<PdfReader>();
		      
		      int totalPages = 0;
		      Iterator<InputStream> iteratorPDFs = pdfs.iterator();

		      // Create Readers for the pdfs.
		      while (iteratorPDFs.hasNext()) {
		        InputStream pdf = iteratorPDFs.next();
		        PdfReader pdfReader = new PdfReader(pdf);
		        readers.add(pdfReader);
		        totalPages += pdfReader.getNumberOfPages();
		      }
		      // Create a writer for the outputstream
		      PdfWriter writer = PdfWriter.getInstance(document, outputStream);

		      document.open();
		      BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		      PdfContentByte cb = writer.getDirectContent(); // Holds the PDF
		      // data

		      PdfImportedPage page;
		     
		      int currentPageNumber = 0;
		      int pageOfCurrentReaderPDF = 0;
		      Iterator<PdfReader> iteratorPDFReader = readers.iterator();

		      // Loop through the PDF files and add to the output.
		      while (iteratorPDFReader.hasNext()) {
		          PdfReader pdfReader = iteratorPDFReader.next();
		         // stamper = new PdfStamper(pdfReader, outputStream);
		          
		          pag = pdfReader.getNumberOfPages();

					tempBookmarks = SimpleBookmark.getBookmark(pdfReader);

					if (j == 0 && tempBookmarks != null) {

						SimpleBookmark.shiftPageNumbers(tempBookmarks, page_offset, null);

						page_offset += pag;
						if (tempBookmarks != null)
							bookmarks.addAll(tempBookmarks);
		// MessageBox.Show(n.ToString());
						totalPages = pag;
					}

					else {
						SimpleBookmark.shiftPageNumbers(tempBookmarks, page_offset, null);
						if (tempBookmarks != null)
							bookmarks.addAll(tempBookmarks);

						page_offset += pag;
						totalPages += pag;
					}

		          // Create a new page in the target for each source page.
		          while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
		        	  
		            document.newPage();
		            pageOfCurrentReaderPDF++;
		            currentPageNumber++;
		            page = writer.getImportedPage(pdfReader, pageOfCurrentReaderPDF);
		           /* Iterator<HashMap<String, Object>> bookmark1=bookmarks.iterator();
		            while (bookmark1.hasNext()) {
		            	HashMap<String, Object> bookmark =bookmark1.next();
		            	
		            	if(bookmark.containsValue(pageOfCurrentReaderPDF)) {
		            		 writer.setOutlines(bookmark);
		            	}
						
					}*/
		           
		            cb.addTemplate(page, 0, 0);

		            // Code for pagination.
		            if (paginate) {
		              cb.beginText();
		              cb.setFontAndSize(bf, 9);
		              cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "" + currentPageNumber + " of " + totalPages, 520, 5, 0);
		              cb.endText();
		            }
		          }
		          writer.setOutlines(bookmarks);
		          //stamper.setOutlines(bookmarks);
		          pageOfCurrentReaderPDF = 0;
		        }
		      document.close();
		      //stamper.close();
		      outputStream.flush();
		      
		      
		      outputStream.close();
		    } catch (Exception e) {
		      e.printStackTrace();
		    } finally {
		      if (document.isOpen())
		        document.close();
		      try {
		        if (outputStream != null)
		        	
		          outputStream.close();
		      } catch (IOException ioe) {
		        ioe.printStackTrace();
		      }
		    }
		  }
	  /// live code
	 /* public static void doMerge(List<InputStream> streamOfPDFFiles, OutputStream outputStream,  List<HashMap<String, Object>> bookmark,boolean paginate) throws DocumentException {
		  Rectangle size=new Rectangle(612, 1040);
		 // PdfStamper stamper=null;
		    Document document = new Document(size);
		    List<HashMap<String, Object>> bookmarks = new ArrayList<HashMap<String, Object>>();
			List<HashMap<String, Object>> tempBookmarks;

			int pag = 0;
			
			int j = 0; int pdfNo = 0;

			int page_offset = 0;
		    try {
		      List<InputStream> pdfs = streamOfPDFFiles;
		      List<PdfReader> readers = new ArrayList<PdfReader>();
		      
		      int totalPages = 0;
		      Iterator<InputStream> iteratorPDFs = pdfs.iterator();

		      // Create Readers for the pdfs.
		      while (iteratorPDFs.hasNext()) {
		        InputStream pdf = iteratorPDFs.next();
		        PdfReader pdfReader = new PdfReader(pdf);
		        readers.add(pdfReader);
		        totalPages += pdfReader.getNumberOfPages();
		      }
		      // Create a writer for the outputstream
		      PdfWriter writer = PdfWriter.getInstance(document, outputStream);

		      document.open();
		      BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		      PdfContentByte cb = writer.getDirectContent(); // Holds the PDF
		      // data

		      PdfImportedPage page;
		     
		      int currentPageNumber = 0;
		      int pageOfCurrentReaderPDF = 0;
		      Iterator<PdfReader> iteratorPDFReader = readers.iterator();

		      // Loop through the PDF files and add to the output.
		      while (iteratorPDFReader.hasNext()) {
		          PdfReader pdfReader = iteratorPDFReader.next();
		         // stamper = new PdfStamper(pdfReader, outputStream);
		          
		          pag = pdfReader.getNumberOfPages();

					tempBookmarks = SimpleBookmark.getBookmark(pdfReader);

					if (j == 0 && tempBookmarks != null) {

						SimpleBookmark.shiftPageNumbers(tempBookmarks, page_offset, null);
						bookmark.get(pdfNo).put("Page", String.format("%d Fit",page_offset+1));

						page_offset += pag;
						if (tempBookmarks != null)
							bookmarks.addAll(tempBookmarks);
		// MessageBox.Show(n.ToString());
						totalPages = pag;
					}

					else {
						SimpleBookmark.shiftPageNumbers(tempBookmarks, page_offset, null);
						bookmark.get(pdfNo).put("Page", String.format("%d Fit",page_offset+1));
						if (tempBookmarks != null)
							bookmarks.addAll(tempBookmarks);

						page_offset += pag;
						totalPages += pag;
					}
					
					if(bookmarks !=null) {
					bookmark.get(pdfNo).put("Kids", bookmarks);
					bookmarks = new ArrayList<HashMap<String, Object>>();
					}
					pdfNo++;

		          // Create a new page in the target for each source page.
		          while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
		        	  
		            document.newPage();
		            pageOfCurrentReaderPDF++;
		            currentPageNumber++;
		            page = writer.getImportedPage(pdfReader, pageOfCurrentReaderPDF);
		            Iterator<HashMap<String, Object>> bookmark1=bookmarks.iterator();
		            while (bookmark1.hasNext()) {
		            	HashMap<String, Object> bookmark =bookmark1.next();
		            	
		            	if(bookmark.containsValue(pageOfCurrentReaderPDF)) {
		            		 writer.setOutlines(bookmark);
		            	}
						
					}
		           
		            cb.addTemplate(page, 0, 0);

		            // Code for pagination.
		            if (paginate) {
		              cb.beginText();
		              cb.setFontAndSize(bf, 9);
		              cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "" + currentPageNumber + " of " + totalPages, 520, 5, 0);
		              cb.endText();
		            }
		          }
		          writer.setOutlines(bookmark);
		          //stamper.setOutlines(bookmarks);
		          pageOfCurrentReaderPDF = 0;
		        }
		      document.close();
		      //stamper.close();
		      outputStream.flush();
		      
		      
		      outputStream.close();
		    } catch (Exception e) {
		      e.printStackTrace();
		    } finally {
		      if (document.isOpen())
		        document.close();
		      try {
		        if (outputStream != null)
		        	
		          outputStream.close();
		      } catch (IOException ioe) {
		        ioe.printStackTrace();
		      }
		    }
		  }
	  */
	  /// live download code
	  
	  public static void doMerge(List<InputStream> streamOfPDFFiles, OutputStream outputStream,  List<HashMap<String, Object>> bookmark,boolean paginate) throws DocumentException {
		 
		 // PdfStamper stamper=null;
		    Document document = new Document();
		    List<HashMap<String, Object>> bookmarks = new ArrayList<HashMap<String, Object>>();
			List<HashMap<String, Object>> tempBookmarks;

			int pag = 0;
			
			int j = 0; int pdfNo = 0;

			int page_offset = 0;
		    try {
		      List<InputStream> pdfs = streamOfPDFFiles;
		      List<PdfReader> readers = new ArrayList<PdfReader>();
		      
		      int totalPages = 0;
		      Iterator<InputStream> iteratorPDFs = pdfs.iterator();

		      // Create Readers for the pdfs.
		      while (iteratorPDFs.hasNext()) {
		        InputStream pdf = iteratorPDFs.next();
		        PdfReader pdfReader = new PdfReader(pdf);
		        readers.add(pdfReader);
		        totalPages += pdfReader.getNumberOfPages();
		      }
		      // Create a writer for the outputstream
		      PdfWriter writer = PdfWriter.getInstance(document, outputStream);

		      document.open();
		      BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		      PdfContentByte cb = writer.getDirectContent(); // Holds the PDF
		      // data

		      PdfImportedPage page;
		     
		      int currentPageNumber = 0;
		      int pageOfCurrentReaderPDF = 0;
		      Iterator<PdfReader> iteratorPDFReader = readers.iterator();

		      // Loop through the PDF files and add to the output.
		      while (iteratorPDFReader.hasNext()) {
		          PdfReader pdfReader = iteratorPDFReader.next();
		         // stamper = new PdfStamper(pdfReader, outputStream);
		          
		          pag = pdfReader.getNumberOfPages();

					tempBookmarks = SimpleBookmark.getBookmark(pdfReader);

					if (j == 0 && tempBookmarks != null) {

						SimpleBookmark.shiftPageNumbers(tempBookmarks, page_offset, null);
						bookmark.get(pdfNo).put("Page", String.format("%d Fit",page_offset+1));

						page_offset += pag;
						if (tempBookmarks != null)
							bookmarks.addAll(tempBookmarks);
		// MessageBox.Show(n.ToString());
						totalPages = pag;
					}

					else {
						SimpleBookmark.shiftPageNumbers(tempBookmarks, page_offset, null);
						bookmark.get(pdfNo).put("Page", String.format("%d Fit",page_offset+1));
						if (tempBookmarks != null)
							bookmarks.addAll(tempBookmarks);

						page_offset += pag;
						totalPages += pag;
					}
					
					if(bookmarks !=null) {
					bookmark.get(pdfNo).put("Kids", bookmarks);
					bookmarks = new ArrayList<HashMap<String, Object>>();
					}
					pdfNo++;

		          // Create a new page in the target for each source page.
		          while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
		        	  
		            document.newPage();
		            pageOfCurrentReaderPDF++;
		            currentPageNumber++;
		            page = writer.getImportedPage(pdfReader, pageOfCurrentReaderPDF);
		           
		            
		            Rectangle pagesize = pdfReader.getPageSizeWithRotation(pageOfCurrentReaderPDF);
		            float oWidth = pagesize.getWidth();
		            float oHeight = pagesize.getHeight();
		            float scale = getScale(oWidth, oHeight);
		            float scaledWidth = oWidth;
		            float scaledHeight = oHeight;
		            int rotation = pagesize.getRotation();

		            AffineTransform transform = new AffineTransform(scale, 0, 0, scale, 0, 0);
		            switch (rotation) {
		                case 0:
		                	 cb.addTemplate(page, scale, 0, 0, scale, 0, 0);
		                    break;
		                case 90:
		                    AffineTransform rotate90 = new AffineTransform(0, -1f, 1f, 0, 0, scaledHeight*scale);
		                    rotate90.concatenate(transform);
		                    cb.addTemplate(page, rotate90);
		                    break;
		                case 180:
		                    AffineTransform rotate180 = new AffineTransform(-1f, 0, 0, -1f, scaledWidth*scale,
		                        scaledHeight*scale);
		                    rotate180.concatenate(transform);
		                    cb.addTemplate(page, rotate180);
		                    break;
		                case 270:
		                    AffineTransform rotate270 = new AffineTransform(0, 1f, -1f, 0, scaledWidth*scale, 0);
		                    rotate270.concatenate(transform);
		                    cb.addTemplate(page, rotate270);
		                    break;
		                default:
		                    cb.addTemplate(page, scale, 0, 0, scale, 0, 0);
		            }
		            

		            // Code for pagination.
		            if (paginate) {
		              cb.beginText();
		              cb.setFontAndSize(bf, 9);
		              cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "" + currentPageNumber + " of " + totalPages, 520, 5, 0);
		              cb.endText();
		            }
		          }
		          writer.setOutlines(bookmark);
		          //stamper.setOutlines(bookmarks);
		          pageOfCurrentReaderPDF = 0;
		        }
		      document.close();
		      //stamper.close();
		      outputStream.flush();
		      
		      
		      outputStream.close();
		    } catch (Exception e) {
		      e.printStackTrace();
		    } finally {
		      if (document.isOpen())
		        document.close();
		      try {
		        if (outputStream != null)
		        	
		          outputStream.close();
		      } catch (IOException ioe) {
		        ioe.printStackTrace();
		      }
		    }
		  }
	
	  
	  private static float getScale(float width, float height) {
		    float scaleX = PageSize.A4.getWidth() / width;
		    float scaleY = PageSize.A4.getHeight() / height;
		    return Math.min(scaleX, scaleY);
		}
	
	
	public static void doMerge3(List<InputStream> list, OutputStream outputStream)
			throws DocumentException, IOException {
		//List<InputStream> list = new ArrayList<InputStream>();
		 /*File file2 = new File("D:\\Allahabad High Court\\Allahabad\\BAIL\\order_sheet\\BAIL129782020_ORDS_3.pdf");
	        File file1 = new File("D:\\Allahabad High Court\\Allahabad\\BAIL\\order_sheet\\BAIL129782020_ORDS_2.pdf");
	        File file3 = new File("D:\\Allahabad High Court\\Allahabad\\BAIL\\petition\\BAIL129782020_PETN_1.pdf");*/ 
		

	        
/*			for (InputStream in : list) {
				   Document document1 = new Document();
				   int count =1;
				List<HashMap<String, Object>> bookmarks1 = new ArrayList<HashMap<String, Object>>();
				List<HashMap<String, Object>> tempBookmarks1;
				String outputFilePath = "D:\\Allahabad High Court\\";
				OutputStream out;
				count =count+1;
				try {
					out = new FileOutputStream(new File(outputFilePath +File.separator+count+ ".pdf"));
				PdfSmartCopy copy1 = new PdfSmartCopy(document1, out);

				int pag = 0;

				int page_offset = 0;

				int totalPages = 0;
				PdfReader reader = new PdfReader(in);

	//pag=copy1.getPageNumber();
				pag = reader.getNumberOfPages();

				tempBookmarks1 = SimpleBookmark.getBookmark(reader);
				int j = 0;


				if (j == 0 && tempBookmarks1 != null) {

					SimpleBookmark.shiftPageNumbers(tempBookmarks1, page_offset, null);

					page_offset += pag;
					if (tempBookmarks1 != null)
						bookmarks1.addAll(tempBookmarks1);
	// MessageBox.Show(n.ToString());
					totalPages = pag;
				}

				else {
					SimpleBookmark.shiftPageNumbers(tempBookmarks1, page_offset, null);
					if (tempBookmarks1 != null)
						bookmarks1.addAll(tempBookmarks1);

					page_offset += pag;
					totalPages += pag;
				}

				for (int i = 1; i <= reader.getNumberOfPages(); i++) {

					copy1.addPage(copy1.getImportedPage(reader, i));
				}
				copy1.setOutlines(bookmarks1);
				document1.close();
				copy1.freeReader(reader);
				reader.close();
				
				j++;
			}
				catch(Exception e) {
					e.printStackTrace();
				}
			}*/
				
		
		//list.add(new FileInputStream(new File(srcPath)));
		
		Document document = new Document();
		
		List<HashMap<String, Object>> bookmarks = new ArrayList<HashMap<String, Object>>();
		List<HashMap<String, Object>> tempBookmarks;

		int pag = 0;

		int page_offset = 0;

		int totalPages = 0;

		PdfSmartCopy copy1 = new PdfSmartCopy(document, outputStream);

	//	copy1.setFullCompression();

		document.open();
		int j = 0;

		for (InputStream in : list) {

			PdfReader reader = new PdfReader(in);

//pag=copy1.getPageNumber();
			pag = reader.getNumberOfPages();

			tempBookmarks = SimpleBookmark.getBookmark(reader);

			if (j == 0 && tempBookmarks != null) {

				SimpleBookmark.shiftPageNumbers(tempBookmarks, page_offset, null);

				page_offset += pag;
				if (tempBookmarks != null)
					bookmarks.addAll(tempBookmarks);
// MessageBox.Show(n.ToString());
				totalPages = pag;
			}

			else {
				SimpleBookmark.shiftPageNumbers(tempBookmarks, page_offset, null);
				if (tempBookmarks != null)
					bookmarks.addAll(tempBookmarks);

				page_offset += pag;
				totalPages += pag;
			}

			for (int i = 1; i <= reader.getNumberOfPages(); i++) {

				copy1.addPage(copy1.getImportedPage(reader, i));
			}
			copy1.setOutlines(bookmarks);
			copy1.freeReader(reader);
			reader.close();
			j++;
		}

		
		document.close();
		outputStream.flush();
		outputStream.close();
		copy1.close();

	}
	
	
	public static void doMerge1(List<InputStream> list, OutputStream outputStream)
			throws DocumentException, IOException {
		//List<InputStream> list = new ArrayList<InputStream>();
		 /*File file2 = new File("D:\\Allahabad High Court\\Allahabad\\BAIL\\order_sheet\\BAIL129782020_ORDS_3.pdf");
	        File file1 = new File("D:\\Allahabad High Court\\Allahabad\\BAIL\\order_sheet\\BAIL129782020_ORDS_2.pdf");
	        File file3 = new File("D:\\Allahabad High Court\\Allahabad\\BAIL\\petition\\BAIL129782020_PETN_1.pdf");*/ 
		

	        
/*			for (InputStream in : list) {
				   Document document1 = new Document();
				   int count =1;
				List<HashMap<String, Object>> bookmarks1 = new ArrayList<HashMap<String, Object>>();
				List<HashMap<String, Object>> tempBookmarks1;
				String outputFilePath = "D:\\Allahabad High Court\\";
				OutputStream out;
				count =count+1;
				try {
					out = new FileOutputStream(new File(outputFilePath +File.separator+count+ ".pdf"));
				PdfSmartCopy copy1 = new PdfSmartCopy(document1, out);

				int pag = 0;

				int page_offset = 0;

				int totalPages = 0;
				PdfReader reader = new PdfReader(in);

	//pag=copy1.getPageNumber();
				pag = reader.getNumberOfPages();

				tempBookmarks1 = SimpleBookmark.getBookmark(reader);
				int j = 0;


				if (j == 0 && tempBookmarks1 != null) {

					SimpleBookmark.shiftPageNumbers(tempBookmarks1, page_offset, null);

					page_offset += pag;
					if (tempBookmarks1 != null)
						bookmarks1.addAll(tempBookmarks1);
	// MessageBox.Show(n.ToString());
					totalPages = pag;
				}

				else {
					SimpleBookmark.shiftPageNumbers(tempBookmarks1, page_offset, null);
					if (tempBookmarks1 != null)
						bookmarks1.addAll(tempBookmarks1);

					page_offset += pag;
					totalPages += pag;
				}

				for (int i = 1; i <= reader.getNumberOfPages(); i++) {

					copy1.addPage(copy1.getImportedPage(reader, i));
				}
				copy1.setOutlines(bookmarks1);
				document1.close();
				copy1.freeReader(reader);
				reader.close();
				
				j++;
			}
				catch(Exception e) {
					e.printStackTrace();
				}
			}*/
				
		
		//list.add(new FileInputStream(new File(srcPath)));
		
		Document document = new Document();
		
		

		int pag = 0;

		int page_offset = 0;

		int totalPages = 0;

		PdfSmartCopy copy1 = new PdfSmartCopy(document, outputStream);
		List<HashMap<String, Object>> bookmarks = new ArrayList<HashMap<String, Object>>();
		List<HashMap<String, Object>> tempBookmarks;

	//	copy1.setFullCompression();

		document.open();
		int j = 0;

		for (InputStream in : list) {

			PdfReader reader = new PdfReader(in);

//pag=copy1.getPageNumber();
			pag = reader.getNumberOfPages();
			
			tempBookmarks = SimpleBookmark.getBookmark(reader);

			if (j == 0) {

				

				page_offset += pag;
				if (tempBookmarks != null) {
					bookmarks.addAll(tempBookmarks);
					SimpleBookmark.shiftPageNumbers(tempBookmarks,1, null);
					for (int i = 1; i <= reader.getNumberOfPages(); i++) {

						copy1.addPage(copy1.getImportedPage(reader, i));
					}
					//copy1.setOutlines(bookmarks);
				}
				else {
					ArrayList<HashMap<String, Object>> kids = new ArrayList<HashMap<String, Object>>();
					HashMap<String, Object> link1 = new HashMap<String, Object>();
					link1.put("Title", "link1");
					link1.put("Action", "GoTo");
					link1.put("Page", String.format("%d Fit", page_offset));
					kids.add(link1);
					bookmarks.addAll(kids);
					//SimpleBookmark.shiftPageNumbers(kids, page_offset, null);
					for (int i = 1; i <= reader.getNumberOfPages(); i++) {

						copy1.addPage(copy1.getImportedPage(reader, i));
					}
					//copy1.setOutlines(bookmarks);
				
					
				}
// MessageBox.Show(n.ToString());
				totalPages = pag;
			}

			else {
			//	SimpleBookmark.shiftPageNumbers(tempBookmarks, page_offset, null);
				if (tempBookmarks != null) {
					bookmarks.addAll(tempBookmarks);

				page_offset += pag;
				totalPages += pag;
				SimpleBookmark.shiftPageNumbers(tempBookmarks, 1, null);
				for (int i = 1; i <= reader.getNumberOfPages(); i++) {

					copy1.addPage(copy1.getImportedPage(reader, i));
				}
			//	copy1.setOutlines(bookmarks);
				}
				
					else {
						System.out.println("page offser "+page_offset);
						page_offset += pag;
						System.out.println("page offser 2 "+page_offset);
						totalPages += pag;
						ArrayList<HashMap<String, Object>> kids = new ArrayList<HashMap<String, Object>>();
						HashMap<String, Object> link1 = new HashMap<String, Object>();
						link1.put("Title", "page"+page_offset);
						link1.put("Action", "GoTo");
						link1.put("Page", String.format("%d Fit", page_offset));
						kids.add(link1);
						bookmarks.addAll(kids);
						//SimpleBookmark.shiftPageNumbers(kids, page_offset, null);
						for (int i = 1; i <= reader.getNumberOfPages(); i++) {

							copy1.addPage(copy1.getImportedPage(reader, i));
						}
					//	copy1.setOutlines(bookmarks);
					
						
						//SimpleBookmark.shiftPageNumbers(kids, page_offset, null);
						
				}
			}

			
			copy1.freeReader(reader);
			reader.close();
			j++;
		}
		copy1.setOutlines(bookmarks);

		
		document.close();
		outputStream.flush();
		outputStream.close();
		copy1.close();

	}
}