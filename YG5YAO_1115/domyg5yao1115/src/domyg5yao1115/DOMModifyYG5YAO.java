package domyg5yao1115;

import java.io.FileOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
public class DOMModifyYG5YAO {
	public static void main(String[] args) throws ParserConfigurationException, TransformerException, FileNotFoundException{
		NodeList list;
		try {
		FileOutputStream file = new FileOutputStream("./orarendModifyYG5YAO.xml");

		DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		//fajl olvasas
		Document document = builder.parse(new File("./orarendYG5YAO.xml"));
		document.getDocumentElement().normalize();
		// A resz kitalalni valamit mert nekem nincs olyan
		
		//B resz gyakorlat -> eloadas;
		Document doc2= builder.newDocument();

		String baseString=document.getDocumentElement().getNodeName();
		Element rootElement2 = doc2.createElement(baseString);
		doc2.appendChild(rootElement2);
		list=document.getElementsByTagName("Ora");
		for(int i =0; i<list.getLength();i++) {
			Node node = list.item(i);

			//Ora element
			Element element = (Element) node;
			Element ora = doc2.createElement(element.getNodeName());	
			if(node.getNodeType()==Node.ELEMENT_NODE) {
				rootElement2.appendChild(ora);
				if(node.hasAttributes()) {
					NamedNodeMap nodeMap = node.getAttributes();
					for(int j=0;j<nodeMap.getLength();j++) {
						Node atrNode = nodeMap.item(j);
						if(element.getAttribute("TIPUS").equals("Gyakorlat")) {
							ora.setAttribute("Id", element.getAttribute("Id") );
							ora.setAttribute("TIPUS", "Eloadas");
						}
						else {
								ora.setAttribute(atrNode.getNodeName(), atrNode.getNodeValue());}
						
					}
				}
			}
			//Kurzusnev element
			Element kurzusnevElement = doc2.createElement("Kurzusnev");
			kurzusnevElement.setTextContent(element.getElementsByTagName("Kurzusnev").item(0).getTextContent());
			ora.appendChild(kurzusnevElement);
			//Oktato element
			Element oktato = doc2.createElement("Oktato");
			oktato.setTextContent(element.getElementsByTagName("Oktato").item(0).getTextContent());
			ora.appendChild(oktato);
			//Tanterem element
			Element tanterem = doc2.createElement("Tanterem");
			tanterem.setTextContent(element.getElementsByTagName("Tanterem").item(0).getTextContent());
			ora.appendChild(tanterem);
			Node idopontNode = list.item(0);
			if(idopontNode.getNodeType()==Node.ELEMENT_NODE) {
				Element elementidopont = (Element) node;
				//Idopont element
				Element idopont = doc2.createElement("Idopont");
				ora.appendChild(idopont);
				//nap element
				Element nap = doc2.createElement("Nap");
				nap.setTextContent(elementidopont.getElementsByTagName("Nap").item(0).getTextContent());
				idopont.appendChild(nap);
				//kezdet element
				Element kezdet = doc2.createElement("Kezdet");
				kezdet.setTextContent(elementidopont.getElementsByTagName("Kezdet").item(0).getTextContent());
				idopont.appendChild(kezdet);
				//vege element
				Element vege = doc2.createElement("Vege");
				vege.setTextContent(elementidopont.getElementsByTagName("Vege").item(0).getTextContent());
				idopont.appendChild(vege);
			}
			//Szak element
			Element szak = doc2.createElement("Szak");
			szak.setTextContent(element.getElementsByTagName("Szak").item(0).getTextContent());
			ora.appendChild(szak);
			}
		writeXML(doc2, System.out, null);
		
		//C resz minden orat eleorebb hozni 2 oraval (idpont modositas) modositani kell stringrol intre
		Document doc= builder.newDocument();

		Element rootElement = doc.createElement(baseString);
		doc.appendChild(rootElement);
		for(int i =0; i<list.getLength();i++) {
			Node node = list.item(i);

			//Ora element
			Element element = (Element) node;
			Element ora = doc.createElement(element.getNodeName());	
			if(node.getNodeType()==Node.ELEMENT_NODE) {
				rootElement.appendChild(ora);
				if(node.hasAttributes()) {
					NamedNodeMap nodeMap = node.getAttributes();
					for(int j=0;j<nodeMap.getLength();j++) {
						Node atrNode = nodeMap.item(j);
						ora.setAttribute(atrNode.getNodeName(), atrNode.getNodeValue());
					}
				}
			}
			//Kurzusnev element
			Element kurzusnevElement = doc.createElement("Kurzusnev");
			kurzusnevElement.setTextContent(element.getElementsByTagName("Kurzusnev").item(0).getTextContent());
			ora.appendChild(kurzusnevElement);
			//Oktato element
			Element oktato = doc.createElement("Oktato");
			oktato.setTextContent(element.getElementsByTagName("Oktato").item(0).getTextContent());
			ora.appendChild(oktato);
			//Tanterem element
			Element tanterem = doc.createElement("Tanterem");
			tanterem.setTextContent(element.getElementsByTagName("Tanterem").item(0).getTextContent());
			ora.appendChild(tanterem);
			Node idopontNode = list.item(0);
			if(idopontNode.getNodeType()==Node.ELEMENT_NODE) {
				Element elementidopont = (Element) node;
				//Idopont element
				Element idopont = doc.createElement("Idopont");
				ora.appendChild(idopont);
				//nap element
				Element nap = doc.createElement("Nap");
				nap.setTextContent(elementidopont.getElementsByTagName("Nap").item(0).getTextContent());
				idopont.appendChild(nap);
				//kezdet element
				//NEM SZABADNA, mivel try catch-el kéne csinálni, mert lehet nem jól van megadva az "string" az eredeti xmlben
				//ami futás idejû hibát eredményezne amit nem kap el a rendszer. DE BÍZOK a saját xmlemben így ezt kihagyom.
				Element kezdet = doc.createElement("Kezdet");
				Integer kezdetInteger = Integer.valueOf(elementidopont.getElementsByTagName("Kezdet").item(0).getTextContent()) - 2;
				kezdet.setTextContent(kezdetInteger.toString());
				idopont.appendChild(kezdet);
				//vege element
				Element vege = doc.createElement("Vege");
				Integer vegeInteger = Integer.valueOf(elementidopont.getElementsByTagName("Vege").item(0).getTextContent()) - 2;
				vege.setTextContent(vegeInteger.toString());
				idopont.appendChild(vege);
			}
			//Szak element
			Element szak = doc.createElement("Szak");
			szak.setTextContent(element.getElementsByTagName("Szak").item(0).getTextContent());
			ora.appendChild(szak);
			}
		writeXML(doc, System.out, file);
		
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		
	}
	private static void writeXML(Document doc, OutputStream output, FileOutputStream file) throws TransformerException {
		TransformerFactory transFact = TransformerFactory.newInstance();
		Transformer trans = transFact.newTransformer();
		trans.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(output);
		StreamResult resultFile = new StreamResult(file);
		
		if(file==null) {
			trans.transform(source, result);
		}
		else {
			trans.transform(source, resultFile);
			trans.transform(source, result);
			}
		

	}
}
