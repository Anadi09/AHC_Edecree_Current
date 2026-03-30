package com.eDecree.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.eDecree.model.SubDocument;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSmartCopy;
import com.itextpdf.text.pdf.PdfWriter;


public class GlobalFunction {

	public String encryptedCode="shcil@1234";
	/*
	 * Covert to json
	 */
	public String convert_to_json(Object object){
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = null;
		try {			 
			//System.out.println(mapper.writeValueAsString(object));
			jsonData = mapper.writeValueAsString(object);	
		} catch (JsonGenerationException e) {	 
			e.printStackTrace();	 
		} catch (JsonMappingException e) {	 
			e.printStackTrace();	 
		} catch (IOException e) {	 
			e.printStackTrace();	 
		}	 
		return jsonData;		
	}
	
	/*
	 * Generate random Integer
	 */  
	public String RandomInteger(){
		log("Generating Random Number");	
		String random_no = null;
		Random random = new Random();	      
	    // get next long value 
	    long LOWER_RANGE = 10000000000L; //assign lower range value
	    long UPPER_RANGE = 100000000000L; //assign upper range value
	    long value = LOWER_RANGE + (long)(random.nextDouble()*(UPPER_RANGE - LOWER_RANGE));
		log("Generated : " + value+"");
		random_no = value+"";
		return random_no;
	}
	
	public static void log(String aMessage){
	    System.out.println(aMessage);
	}
	/*
	 * Covert String date into Date
	 */
	public Date convertDate(String stringDate,String format){
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date date = null;
		try {	 
			date = formatter.parse(stringDate);			 
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(date);	
		return date;
	}
	
	public String getRandomString() {
        String SALTCHARS = "1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();       
        return saltStr;
    }

	public String RandomSmallInteger(){
		log("Generating Random Number");	
		String random_no = null;
		Random random = new Random();	      
	    // get next long value 
	    long LOWER_RANGE = 1000L; //assign lower range value
	    long UPPER_RANGE = 10000L; //assign upper range value
	    long value = LOWER_RANGE + (long)(random.nextDouble()*(UPPER_RANGE - LOWER_RANGE));
		log("Generated : " + value+"");
		random_no = value+"";
		return random_no;
	}
	
	
	public String getamountindigits(String amount)
	{
		String amountindigits="";
		String x=amount;
		if(x.length()<=3)
		{
			amountindigits= x;
		}else
		{
        x=x.toString();
        String lastThree = x.substring(x.length()-3);
        String otherNumbers = x.substring(0,x.length()-3);
        if(otherNumbers != "")
            lastThree = "," + lastThree;
        
        amountindigits = otherNumbers.replaceAll("(?<=\\d)(?=(\\d{2})+$)", ",") + lastThree;
		}
		return amountindigits;
	}

	public Boolean createfolder(String path) {
		// TODO Auto-generated method stub
		Boolean result=false;
		File theDir = new File(path);
		if (!theDir.exists()) {
			try {
				if(theDir.mkdir()){
					result=true;
				}else{
					System.out.println("Error occured while creating folder ="+path);
				}
			} catch (SecurityException se) {
			}
		}else{
			System.out.println("folder exist="+path);
		}
		return result;
	}
	
	public String md5encryption(String password)
	{
		String encryptpwd="";
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());

		    byte byteData[] = md.digest();
		    
		    StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        
	       // System.out.println("Digest(in hex format):: " + sb.toString());
	        
	      //convert the byte to hex format method 2
	        StringBuffer hexString = new StringBuffer();
	    	for (int i=0;i<byteData.length;i++) {
	    		String hex=Integer.toHexString(0xff & byteData[i]);
	   	     	if(hex.length()==1) hexString.append('0');
	   	     	hexString.append(hex);
	    	}
	    	
	    	encryptpwd = hexString.toString();
	    	//System.out.println("Digest(in hex format):: " + hexString.toString());
		    
		    
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		
		return encryptpwd;
	}
	
	public static void encrypt(String key, InputStream is, OutputStream os) throws Throwable {
		encryptOrDecrypt(key, Cipher.ENCRYPT_MODE, is, os);
	}
	
	public static void decrypt(String key, InputStream is, OutputStream os) throws Throwable {
		encryptOrDecrypt(key, Cipher.DECRYPT_MODE, is, os);
	}
	
	public static void encryptOrDecrypt(String key,int mode,InputStream is,OutputStream os) throws Throwable {
		DESKeySpec dks = new DESKeySpec(key.getBytes());
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey desKey = skf.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");	//DES/ECB/PKCS5Padding for SunJCE
		
		if(mode==Cipher.ENCRYPT_MODE){
			cipher.init(Cipher.ENCRYPT_MODE, desKey);
			CipherInputStream cis=new CipherInputStream(is, cipher);
			doCopy(cis,os);
		}else if(mode==Cipher.DECRYPT_MODE){
			cipher.init(Cipher.DECRYPT_MODE, desKey);
			CipherOutputStream cos = new CipherOutputStream(os, cipher);
			doCopy(	is, cos);
		}
	}
	
	public static void doCopy(InputStream is,OutputStream os) throws IOException{
		byte[] bytes = new byte[64];
		int numBytes;
		while((numBytes = is.read(bytes)) != -1){
			os.write(bytes,0,numBytes);
		}
		os.flush();
		os.close();
		is.close();
	}
	
	public static String getFolderSize(long bytes, boolean si) {
	    int unit = si ? 1000 : 1024;
	    if (bytes < unit) return bytes + " B";
	    int exp = (int) (Math.log(bytes) / Math.log(unit));
	    String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
	    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}
	public void zipFolder(String srcFolderPath, String zipPath) throws Exception 
    {
    	 File zipFile=new File(zipPath);
    	 final Path sourceFolderPath=Paths.get(srcFolderPath);
        final ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile));
        Files.walkFileTree(sourceFolderPath, new SimpleFileVisitor<Path>()
        		{
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                zos.putNextEntry(new ZipEntry(sourceFolderPath.relativize(file).toString()));
                Files.copy(file, zos);
                zos.closeEntry();
                return FileVisitResult.CONTINUE;
            }
        });
        zos.close();
    }

	public void merge(String casefile,String casefilebk, List<SubDocument> subDocuments, String basePath) {
		// TODO Auto-generated method stub
		List<InputStream> list = new ArrayList<InputStream>();
		Map<String,Integer> map=new HashMap<String, Integer>();
		Integer pagecount=0;
		int i=0;
        try {
        	if(subDocuments.size()>1){
        	for(SubDocument subDocument:subDocuments){
    			String srcPath=basePath+File.separator+subDocument.getIndexField().getIf_name()+File.separator+subDocument.getSd_document_name()+".pdf";
    			String label=subDocument.getIndexField().getIf_label();
    			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    			if(subDocument.getSd_submitted_date()!=null){
    			String date = formatter.format(subDocument.getSd_submitted_date());
    			label=label+"_"+date;
    			}
    			if(i==0){
    				String lastfilePath=srcPath;
        			PdfReader reader = new PdfReader(lastfilePath);
    				map.put(label, 1);    			
    				reader.close();
    			}
    			if(i>0){
    				String lastfilePath=basePath+File.separator+subDocuments.get(i-1).getIndexField().getIf_name()+File.separator+subDocuments.get(i-1).getSd_document_name()+".pdf";
        			PdfReader reader = new PdfReader(lastfilePath);
        			pagecount+=reader.getNumberOfPages();
        			map.put(label, pagecount+1);    			
    				reader.close();
    			}
    			list.add(new FileInputStream(new File(srcPath)));
    			i++;
    		}
                       // Resulting pdf
            //OutputStream out = new FileOutputStream(new File(casefile));

            doMerge(list, casefile);

            doBookmark(casefile,casefilebk,map);
            File f=new File(casefile);
            f.delete();
        	}else{
        		String srcPath=basePath+File.separator+subDocuments.get(0).getIndexField().getIf_name()+File.separator+subDocuments.get(0).getSd_document_name()+".pdf";
        		String destPath=casefile;
        		File src=new File(srcPath);
        		File dest=new File(destPath);
        		FileUtils.copyFile(src, dest);
        	}
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	public void doMerge(List<InputStream> list, String destPath)
            throws DocumentException, IOException {
		Document document = new Document();
        FileOutputStream outputStream = new FileOutputStream(destPath);

        PdfSmartCopy copy1 = new PdfSmartCopy(document, outputStream);

        copy1.setFullCompression();

        document.open();
        
        for (InputStream in : list) {
            PdfReader reader = new PdfReader(in);
            
            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                
            	copy1.addPage(copy1.getImportedPage(reader,i) );
            }
            copy1.freeReader(reader);
            reader.close();
        }
        

        outputStream.flush();
        document.close();
        outputStream.close();
        copy1.close();
    }
	public void doBookmark(String src, String dest, Map<String, Integer> map) {
		// TODO Auto-generated method stub
     	Document document = new Document();
    	
    	
		try {
			PdfReader reader1 = new PdfReader(src);
			int noPages = reader1.getNumberOfPages();
			PdfCopy copy = new PdfCopy(document, new FileOutputStream(dest));
			document.open();
			ArrayList<HashMap<String, Object>> outlines = new ArrayList<HashMap<String, Object>>();
			// add the first document
			
			copy.addDocument(reader1);
			
			HashMap<String, Object> helloworld;
			
			for (Map.Entry<String, Integer> entry : map.entrySet())
			{		        
				helloworld = new HashMap<String, Object>();
			    helloworld.put("Title", entry.getKey());
			    helloworld.put("Action", "GoTo");
			    helloworld.put("Page", ""+entry.getValue());
			    outlines.add(helloworld);
		    }
		    copy.setOutlines(outlines);
			document.close();
			reader1.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}

}
