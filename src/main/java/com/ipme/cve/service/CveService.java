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
import com.ipme.cve.model.ProductCve;
import com.ipme.cve.model.Vendor;
import com.ipme.cve.repository.CveRepository;
import com.ipme.cve.repository.ProductCveRepository;
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
	
	@Autowired
	private ProductCveRepository productCveRepository;
	
	
	/**
	 * Function who get XML file and parse it for create object
	 * @param cve
	 */
	public void createAllCve(Cve cve, Product product, Vendor vendor, ProductCve productCve) {
		
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

                    	//On conserve uniquement les vulnérabilité grace à la condition "entry"
                        final Element vulnerability = (Element) racineNoeuds.item(i);
                        
                        //Recuperation des titres dans un String
                        String title = vulnerability.getAttribute("name");
                        
                        //On cherche si la cve n'existe pas
                        //If true create cve
                        cve = cveRepository.findByTitle(title);                       
                        if (cve == null) {
                        	
                            
                            //Récupération des attributs du noeuds dans des variables pour créer une cve
                            String version = document.getXmlVersion();
                            String severity = vulnerability.getAttribute("severity");
                            String published = vulnerability.getAttribute("published");
                            String modified = vulnerability.getAttribute("modified");
                            final Element descript = (Element) vulnerability.getElementsByTagName("descript").item(0);
                            String description = descript.getTextContent();
                            if (description==null) {
								description="Pas de description";
							}
                            cve = createCve(title, version, description, published, modified, severity);
                   
                            //Récupération du noeud "prod" et on boucle sur le nombre de produits concernés
                            final NodeList prods = vulnerability.getElementsByTagName("prod");
                            final int nbProds = prods.getLength();
                            
                            for(int j = 0; j<nbProds; j++) {
                                final Element prod = (Element) prods.item(j);
                                String labelProduct = prod.getAttribute("name");
                                String labelVendor = prod.getAttribute("vendor");
                                
                                //Vérification et création si !exist
                                vendor = checkAndCreateVendor(labelVendor, vendor);
                                product = checkAndCreateProduct(labelProduct, product, vendor);
                                productCve = createLinkProductCve(product, cve);

                            }
                            
                            
						}                   

                    }
          
                }
                
            }
           

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private ProductCve createLinkProductCve(Product product, Cve cve) {
		ProductCve productCve = new ProductCve(product, cve);
		productCve = productCveRepository.save(productCve);
		return productCve;
	}

	/**
	 * Find all CVE
	 * @return
	 */
	public List<Cve> findAll() {
		List<Cve> cves = cveRepository.findAll();
		return cves;
	}
	
	public Cve createCve(String title, String version, String description, String published, String modified, String severity) {
		Cve cve = new Cve(severity, title, version, description, published, modified);
		cve = cveRepository.save(cve);
		return cve;
	}
	
	/**
	 * Create vendor if not exist
	 * @param vendor
	 */
	public Vendor checkAndCreateVendor(String label, Vendor vendor) {
		vendor = vendorRepository.findByLabel(label);
		if (vendor==null) {
			Vendor vendorCreate = new Vendor(label);
			vendor = vendorRepository.save(vendorCreate);
		}
		return vendor;
	}
	
	/**
	 * Create product if not exist
	 * @param product
	 */
	public Product checkAndCreateProduct(String label, Product product, Vendor vendor) {
		product = productRepository.findByLabel(label);
		if (product==null) {
			Product productCreate = new Product(label, vendor);
			product = productRepository.save(productCreate);
		}
		return product;
	}
	

}
