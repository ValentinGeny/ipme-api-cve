package com.ipme.cve.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.DocumentBuilderFactory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;


@Service
public class ScrapingService {

	final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	
	
	/**
	 * Analyse le code source de nvd.list pour en extraire la partie xml
	 * Compare ensuite la date des modification avec celle d'aujourd'hui
	 * Si elle est égal, alors on re-télécharge le fichier xml pour le parser et en faire les modifications
	 */
	public void scrapingHtml() {
		
		try {
			//Date d'aujourd'hui
			Date auj = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("M/d/yyyy");
			
			//Scrape le code source et en extrait un bout spécifique
			Document doc = Jsoup.connect("https://nvd.nist.gov/vuln/data-feeds").get();
			Element table = doc.select("table").get(1);
			Element tr = table.select("tr").get(2);
			Element td = tr.select("td").get(1);
			String dateModified = td.text();
			
			//Convertissement du string en date et comparer avec la date de la dernière modification
			try {
				Date date1 = new SimpleDateFormat("M/d/yyyy").parse(dateModified);
				
				if (formater.format(date1).equals(formater.format(auj))) {
					downloadXmlGz();
					downloadXmlZip();
					unzip();
				}
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  

		} 
	
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void downloadXmlGz() {
		
		try (BufferedInputStream in = new BufferedInputStream(new URL("https://nvd.nist.gov/feeds/xml/cve/1.2/nvdcve-modified.xml.gz").openStream());
				  FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/filesXml/nvdcve-modified.xml.gz")) {
				    byte dataBuffer[] = new byte[1024];
				    int bytesRead;
				    while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
				        fileOutputStream.write(dataBuffer, 0, bytesRead);
				    }
				}
		
		catch (IOException e) {
			// handle exception
		}
	}
	
	public void downloadXmlZip() {
		
		try (BufferedInputStream in = new BufferedInputStream(new URL("https://nvd.nist.gov/feeds/xml/cve/1.2/nvdcve-modified.xml.zip").openStream());
				  FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/filesXml/nvdcve-modified.xml.zip")) {
				    byte dataBuffer[] = new byte[1024];
				    int bytesRead;
				    while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
				        fileOutputStream.write(dataBuffer, 0, bytesRead);
				    }
				}
		
		catch (IOException e) {
			// handle exception
		}
	}
	
	public void unzip() throws IOException {
		String fileZip = "nvdcve-modified.xml.zip";
		File destDir = new File("src/main/resources/filesXml/");
		byte[] buffer = new byte[1024];
        ZipInputStream zis;
		try {
			zis = new ZipInputStream(new FileInputStream(fileZip));
			ZipEntry zipEntry = zis.getNextEntry();
	        while (zipEntry != null) {
	            File newFile = newFile(destDir, zipEntry);
	            FileOutputStream fos = new FileOutputStream(newFile);
	            int len;
	            while ((len = zis.read(buffer)) > 0) {
	                fos.write(buffer, 0, len);
	            }
	            fos.close();
	            zipEntry = zis.getNextEntry();
	        }
	        zis.closeEntry();
	        zis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());
         
        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();
         
        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }
         
        return destFile;
    }
	
}
