package hu.domparse.yg5yao;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomReadYG5YAO {

	public static void main(String[] args) {
		NodeList list;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			// fájl beolvasása
			Document document = builder.parse(new File("../XMLYG5YAO.xml"));
			document.getDocumentElement().normalize();
			// Gyökér elem megkeresése és kiíratás
			System.out.println("<" + document.getDocumentElement().getNodeName()+">");
			// Aktuális elem meghatározása (Légitársaság)
			list = document.getElementsByTagName("Légitársaság");

			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);
				// Légitársaság adatainak kiirása
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					System.out.println("\t<"+node.getNodeName()+" "+"LTársaságID="+ "\""+element.getAttribute("LTársaságID")+ "\""+">" );
					System.out.println("\t\t<Név>" + element.getElementsByTagName("Név").item(0).getTextContent()+"</Név>");
					System.out.println("\t\t<Weboldal>" + element.getElementsByTagName("Weboldal").item(0).getTextContent()+"</Weboldal>");
					System.out.println("\t\t<Elérhetöség>");
					

					// Légitársaság elérhetőségeinek kiirása
					Node elerhetoseg = list.item(i);
					if (elerhetoseg.getNodeType() == Node.ELEMENT_NODE) {
						Element elementelerhetoseg = (Element) node;
						System.out.println("\t\t\t<E-mailcím>" + elementelerhetoseg.getElementsByTagName("E-mailcím").item(0).getTextContent()+"</E-mailcím>");
						System.out.println("\t\t\t<Telefonszám>" + elementelerhetoseg.getElementsByTagName("Telefonszám").item(0).getTextContent()+"</Telefonszám>");
					}
					System.out.println("\t\t<Elérhetöség>");
					System.out.println("\t</"+node.getNodeName()+">");
				}
			}
			// Aktuális elem meghatározása (Repülö_Tipus)
			
			  list = document.getElementsByTagName("Repülö_Tipus");
			  for (int i = 0; i < list.getLength(); i++) {
					Node node = list.item(i);
					// Repülö_Tipus adatainak kiirása
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;
						System.out.println("\t<"+node.getNodeName()+" "+"RTipusID="+ "\""+element.getAttribute("RTipusID")+ "\""+">" );
						System.out.println("\t\t<Gyartó>" + element.getElementsByTagName("Gyartó").item(0).getTextContent()+"</Gyartó>");
						System.out.println("\t\t<Elnevezés>" + element.getElementsByTagName("Elnevezés").item(0).getTextContent()+"</Elnevezés>");
						System.out.println("\t\t<Hajtómű>" + element.getElementsByTagName("Hajtómű").item(0).getTextContent()+"</Hajtómű>");
						System.out.println("\t</"+node.getNodeName()+">");
					}
				}
			// Aktuális elem meghatározása (Flotta)
				
			  list = document.getElementsByTagName("Flotta");
			  for (int i = 0; i < list.getLength(); i++) {
					Node node = list.item(i);
					// Flotta adatainak kiirása
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;
						System.out.println("\t<"+node.getNodeName()+" "+"RTipusID="+ "\""+element.getAttribute("RTipusID")+" "+"LTársaságID="+ "\""+element.getAttribute("LTársaságID")+ "\""+">"  );
						System.out.println("\t\t<Darabszám>" + element.getElementsByTagName("Darabszám").item(0).getTextContent()+"</Darabszám>");
						System.out.println("\t</"+node.getNodeName()+">");
					}
				}
			// Aktuális elem meghatározása (Repülö)
				
			  list = document.getElementsByTagName("Repülö");
			  for (int i = 0; i < list.getLength(); i++) {
					Node node = list.item(i);
					// Repülö adatainak kiirása
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;
						System.out.println("\t<"+node.getNodeName()+" "+"RepülöID="+ "\""+element.getAttribute("RepülöID")+" "+"RTipusID="+ "\""+element.getAttribute("RTipusID")+" "+"LTársaságID="+ "\""+element.getAttribute("LTársaságID")+ "\""+">"  );
						System.out.println("\t\t<Gyártásiszám>" + element.getElementsByTagName("Gyártásiszám").item(0).getTextContent()+"</Gyártásiszám>");
						System.out.println("\t\t<Hívószám>" + element.getElementsByTagName("Hívószám").item(0).getTextContent()+"</Hívószám>");
						//Személyzetek lekérése
						NodeList links = element.getElementsByTagName("Személyzet");
				          for(int j=0; j<links.getLength(); j++)
				          {
				             Node link = links.item(j);
				             System.out.println("\t\t<Személyzet>" +link.getTextContent()+"</Személyzet>");
				          }
						System.out.println("\t</"+node.getNodeName()+">");
					}
				}
			// Aktuális elem meghatározása (Járat)
				
			  list = document.getElementsByTagName("Járat");
			  for (int i = 0; i < list.getLength(); i++) {
					Node node = list.item(i);
					// Járat adatainak kiirása
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;
						System.out.println("\t<"+node.getNodeName()+" "+"JáratID="+ "\""+element.getAttribute("JáratID")+" "+"RepülöID="+ "\""+element.getAttribute("RepülöID")+ "\""+">" );
						System.out.println("\t\t<Indulás>" + element.getElementsByTagName("Indulás").item(0).getTextContent()+"</Indulás>");
						System.out.println("\t\t<Cél>" + element.getElementsByTagName("Cél").item(0).getTextContent()+"</Cél>");
						System.out.println("\t\t<Idöpont>" + element.getElementsByTagName("Idöpont").item(0).getTextContent()+"</Idöpont>");
						System.out.println("\t</"+node.getNodeName()+">");
					}
				}
			// Aktuális elem meghatározása (Utas)
				
			  list = document.getElementsByTagName("Utas");
			  for (int i = 0; i < list.getLength(); i++) {
					Node node = list.item(i);
					// Utas adatainak kiirása
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;
						System.out.println("\t<"+node.getNodeName()+" "+"UtasID="+ "\""+element.getAttribute("UtasID")+" "+"JáratID="+ "\""+element.getAttribute("JáratID")+ "\""+">" );
						System.out.println("\t\t<Név>" + element.getElementsByTagName("Név").item(0).getTextContent()+"</Név>");
						System.out.println("\t\t<Elérhetöség>");
						// Utas elérhetőségeinek kiirása
						Node elerhetoseg = list.item(i);
						if (elerhetoseg.getNodeType() == Node.ELEMENT_NODE) {
							Element elementelerhetoseg = (Element) node;
							System.out.println("\t\t\t<E-mailcím>" + elementelerhetoseg.getElementsByTagName("E-mailcím").item(0).getTextContent()+"</E-mailcím>");
							System.out.println("\t\t\t<Telefonszám>" + elementelerhetoseg.getElementsByTagName("Telefonszám").item(0).getTextContent()+"</Telefonszám>");
						}
						System.out.println("\t\t<Elérhetöség>");						System.out.println("\t\t<Poggyász>" + element.getElementsByTagName("Poggyász").item(0).getTextContent()+"</Poggyász>");
						System.out.println("\t</"+node.getNodeName()+">");
					}
				}
			System.out.println("</" + document.getDocumentElement().getNodeName()+">");

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}