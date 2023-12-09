package hu.domparse.yg5yao;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomQueryYG5YAO {

	public static void main(String[] args) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			// fájl beolvasása
			Document document = builder.parse(new File("../XMLYG5YAO.xml"));
			document.getDocumentElement().normalize();
			// Gyökér elem megkeresése
			System.out.println("<" + document.getDocumentElement().getNodeName()+">");

			// Repülök gyártási számai
			query(document, "Repülö", "Gyártásiszám");
			System.out.println("<---------------->");
			// Légitársaság nevei
			query(document, "Légitársaság", "Név");
			System.out.println("<---------------->");

			//Utas adatai kiírása id alapján
			queryID(document, "Utas", "012");
			System.out.println("<---------------->");
			//Légitársaság adatai kiírása id alapján
			queryID(document, "Légitársaság", "LH");
			System.out.println("<---------------->");
			//Repülö adatainak kiírása id alapján
			queryID(document, "Repülö", "LH_02");
			System.out.println("<---------------->");
			//Repülö adatainak kiírása id alapján
			queryID(document, "Járat", "J_03");



			System.out.println("</" + document.getDocumentElement().getNodeName()+">");

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void query(Document document, String elemKeres, String mitKeres) {
		// Aktuális elem meghatározása
		NodeList list = document.getElementsByTagName(elemKeres);

		for (int i = 0; i < list.getLength(); i++) {
			NodeList nodeList = list.item(i).getChildNodes();
			System.out.println("<"+list.item(i).getNodeName()+">");
			for (int k = 0; k < nodeList.getLength(); k++) {
				Node gye = nodeList.item(k);
				if (gye.getNodeName().compareTo(mitKeres) == 0) {
					System.out.println("\t<"+gye.getNodeName()+">"+ gye.getTextContent()+"</"+gye.getNodeName()+">");
				}
			}
			System.out.println("</"+list.item(i).getNodeName()+">");

		}
	}
	public static void queryID(Document document, String elemKeres, String mitKeres) {
		// Aktuális elem meghatározása
		String holKeres;
		if(elemKeres=="Légitársaság") {
			holKeres="LTársaságID";
		}
		else if(elemKeres=="Repülö_Tipus")
		{
			holKeres="RTipusID";
		}
		else {
			holKeres= elemKeres+"ID";
		}
		NodeList list = document.getElementsByTagName(elemKeres);
		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			Element element = (Element) node;
			// Adatok kiirása
			if (node.getNodeType() == Node.ELEMENT_NODE && element.getAttribute(holKeres).equals(mitKeres)) {

				NodeList nodeList = list.item(i).getChildNodes();
				System.out.println("<"+list.item(i).getNodeName()+" "+holKeres+"= "+"\""+element.getAttribute(holKeres)+"\""+">");
				for (int k = 0; k < nodeList.getLength(); k++) {
					Node gye = nodeList.item(k);
					if (!gye.getNodeName().equals("#text")) {
						if(!gye.getNodeName().equals("Elérhetöség")) {
							System.out.println("\t<"+gye.getNodeName()+">"+ gye.getTextContent()+"</"+gye.getNodeName()+">");
						}
						else {
							System.out.println("\t<"+gye.getNodeName()+">");
							NodeList gyeList =gye.getChildNodes();
							for(int l=0;l<gyeList.getLength();l++) {
								Node gyeNode = gyeList.item(l);
								if (!gyeNode.getNodeName().equals("#text")) {
									System.out.println("\t\t<"+gyeNode.getNodeName()+">"+ gyeNode.getTextContent()+"</"+gyeNode.getNodeName()+">");
								}
							}
							System.out.println("\t</"+gye.getNodeName()+">");
						}
					}
				}
				System.out.println("</"+list.item(i).getNodeName()+">");
			}
		}
	}



}
