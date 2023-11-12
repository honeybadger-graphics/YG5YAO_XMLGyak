package domyg5yao1108;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMReadYG5YAO {
	public static void main(String[] args) {
		NodeList list;
		try {
			DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			//fajl olvasas
			Document document = builder.parse(new File("orarendYG5YAO.xml"));
			document.getDocumentElement().normalize();
			System.out.println("Root element : "+document.getDocumentElement().getNodeName());
			System.out.println("------------------------");
			list=document.getElementsByTagName("Ora");
			for(int i=0; i<list.getLength();i++) {
				Node node = list.item(i);
				System.out.println("\nCurrent element : "+node.getNodeName());
				//Adatkiiratas
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					Element element = (Element) node;
					System.out.println("Ora Id: "+element.getAttribute("Id"));
					System.out.println("Ora Tipus: "+element.getAttribute("TIPUS"));
					System.out.println("Kurzusnev: "+element.getElementsByTagName("Kurzusnev").item(0).getTextContent());
					System.out.println("Oktato: "+element.getElementsByTagName("Oktato").item(0).getTextContent());
					System.out.println("Tanterem: "+element.getElementsByTagName("Tanterem").item(0).getTextContent());
					Node idopont = list.item(i);
					if(idopont.getNodeType()==Node.ELEMENT_NODE) {
						Element elementidopont = (Element) node;
						System.out.println("Nap: "+elementidopont.getElementsByTagName("Nap").item(0).getTextContent());
						System.out.println("Kezdet: "+elementidopont.getElementsByTagName("Kezdet").item(0).getTextContent());
						System.out.println("Vege: "+elementidopont.getElementsByTagName("Vege").item(0).getTextContent());
					}
					System.out.println("Szak: "+element.getElementsByTagName("Szak").item(0).getTextContent());
				}

			}
		}
		catch(ParserConfigurationException pe){
			pe.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
}
