package com.ipme.cve.service;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ipme.cve.model.Cve;
import com.ipme.cve.model.Product;
import com.ipme.cve.model.Vendor;
import com.ipme.cve.repository.CveRepository;
import com.ipme.cve.repository.ProductRepository;
import com.ipme.cve.repository.VendorRepository;

@Service
public class CveService {
	
	final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	
	@Autowired
	private CveRepository cveRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private VendorRepository vendorRepository;
	
	public Cve findByTitle(String title) {
		return cveRepository.findByTitle(title);
	}
	
	
	public void createAllCve(Cve cve) {
		
		try {
			//Permet la création de la classe Document
			final DocumentBuilder builder = factory.newDocumentBuilder();
			
			//Intègre le fichier Xml dans la classe Document
            final Document document= builder.parse(new File("nvdcve-recent.xml"));
            
            
            
            //Récupère des racines
            final Element racine = document.getDocumentElement();
            
            //Récupère les noeuds
            final NodeList racineNoeuds = racine.getChildNodes();
            final int nbRacineNoeuds = racineNoeuds.getLength();
            
            System.out.println("Récupération des noeuds");
            
            //Boucle sur tout les noeuds de la racine
            for (int i = 0; i<nbRacineNoeuds; i++){
                if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
                	//Entre uniquement dans les noeuds "entry" du doc xml
                    if (racineNoeuds.item(i).getNodeName().equals("entry")){
                    	Cve cveCreate= new Cve();
                    	Cve cveCompare = new Cve();
                    	Product productCreate = new Product();
                    	Vendor vendorCreate = new Vendor();
                    	//On conserve uniquement les vulnérabilité grace à la condition "entry"
                        final Element vulnerability = (Element) racineNoeuds.item(i);
                        String title = vulnerability.getAttribute("name");
                        System.out.println("Le titre :"+title);
                        /*if (!findByTitle(title).equals(null)) {
                            cveCompare = findByTitle(title);
						}*/

                        System.out.println("Le titre de la cve à comparé :"+cveCompare.getTitle());
                        
                        //On set les attributs cve avec les attributs du noeuds
                        cveCreate.setVersion(document.getXmlVersion());
                        cveCreate.setSeverity(vulnerability.getAttribute("severity"));
                        cveCreate.setTitle(vulnerability.getAttribute("name"));
                        cveCreate.setPublished(vulnerability.getAttribute("published"));
                        cveCreate.setModified(vulnerability.getAttribute("modified"));
                        System.out.println("Test");
                        /* if (!cveCompare.getTitle().equals(cveCreate.getTitle())) {
                        	cve = cveRepository.save(cveCreate);
						}*/
                        cve = cveRepository.save(cveCreate);
                        
                        final Element descript = (Element) vulnerability.getElementsByTagName("descript").item(0);
                        cve.setDescription(descript.getTextContent());
                        
                        
                        //Récupére "prod" soit le product et on boucle sur le nombre de produits concernés
                        final NodeList prods = vulnerability.getElementsByTagName("prod");
                        final int nbProds = prods.getLength();
                        
                        System.out.println("Récupérations des éléments");
                        
                        for(int j = 0; j<nbProds; j++) {
                            final Element prod = (Element) prods.item(j);
                            productCreate.setLabel(prod.getAttribute("name"));
                            vendorCreate.setLabel(prod.getAttribute("vendor"));
                            
                    		System.out.println("Enregistrement des produits");
                    		System.out.println(productCreate.getLabel());
                    		System.out.println("Enregistrement des labels");
                    		System.out.println(vendorCreate.getLabel());

                        }
                        



                    }

                    
                }
                
            }
           

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public List<Cve> findAll() {
		List<Cve> cves = cveRepository.findAll();
		return cves;
	}
	

}
