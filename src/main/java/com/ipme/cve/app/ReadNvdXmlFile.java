package com.ipme.cve.app;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadNvdXmlFile {
    public static void main(final String[] args) {
        /*
         * Etape 1 : récupération d'une instance de la classe "DocumentBuilderFactory"
         */
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            /*
             * Etape 2 : création d'un parseur
             */
            final DocumentBuilder builder = factory.newDocumentBuilder();

            /*
             * Etape 3 : création d'un Document
             */
            final Document document= builder.parse(new File("nvdcve-recent.xml"));

            //Affichage du prologue
            System.out.println("*************PROLOGUE************");
            System.out.println("version : " + document.getXmlVersion());
            System.out.println("encodage : " + document.getXmlEncoding());
            System.out.println("standalone : " + document.getXmlStandalone());

            /*
             * Etape 4 : récupération de l'Element racine
             */
            final Element racine = document.getDocumentElement();

            //Affichage de l'élément racine
            System.out.println("\n*************RACINE************");
            System.out.println(racine.getNodeName());

            /*
             * Etape 5 : récupération des noeuds
             */
            final NodeList racineNoeuds = racine.getChildNodes();
            final int nbRacineNoeuds = racineNoeuds.getLength();

            for (int i = 0; i<20; i++){
                if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    if (racineNoeuds.item(i).getNodeName().equals("entry")){
                        final Element vulnerability = (Element) racineNoeuds.item(i);

                        //Affichage d'une vulnerabilité
                        System.out.println("\n*************VULNERABILITY************");
                        System.out.println("Severity : " + vulnerability.getAttribute("severity"));
                        System.out.println("Number : " + vulnerability.getAttribute("name"));
                        System.out.println("Published : " + vulnerability.getAttribute("published"));
                        System.out.println("Modified : " + vulnerability.getAttribute("modified"));

                        final Element descript = (Element) vulnerability.getElementsByTagName("descript").item(0);
                        System.out.println("Description : "+descript.getTextContent());

                        final NodeList prods = vulnerability.getElementsByTagName("prod");
                        final int nbProds = prods.getLength();
                        System.out.println("Système concerné : ");
                        for(int j = 0; j<nbProds; j++) {
                            final Element prod = (Element) prods.item(j);
                            System.out.print("- ");
                            System.out.print(prod.getAttribute("name"));
                            System.out.print(" / Vendor : ");
                            System.out.println(prod.getAttribute("vendor"));
                        }
                    }


                }
            }

        }
        catch (final ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (final SAXException e) {
            e.printStackTrace();
        }
        catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
