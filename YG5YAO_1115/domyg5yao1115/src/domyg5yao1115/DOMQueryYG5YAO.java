package domyg5yao1115;
//TODO: Több külömbözö egyedbõl lekérni adatokat (pl indopont)

import java.io.FileOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

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
public class DOMQueryYG5YAO {

	public static void main(String[] args)throws ParserConfigurationException, TransformerException, FileNotFoundException {
		// TODO Auto-generated method stub
		NodeList list;
		List<String> kurzusnev = new ArrayList<String>();
		List<String> oktatonev =new ArrayList<String>();
		List<Idopont> idopontokList = new ArrayList<Idopont>();
		

		try {
			FileOutputStream file = new FileOutputStream("./orarendQuerryYG5YAO.xml");

			DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			//fajl olvasas
			Document document = builder.parse(new File("./orarendYG5YAO.xml"));
			document.getDocumentElement().normalize();
			//A C resz
			list=document.getElementsByTagName("Ora");
			for(int i=0; i<list.getLength();i++) {
				Node node = list.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					Element element = (Element) node;
					oktatonev.add(element.getElementsByTagName("Oktato").item(0).getTextContent());
					kurzusnev.add(element.getElementsByTagName("Kurzusnev").item(0).getTextContent());
				}
			}
			System.out.println("Kurzusnev: "+kurzusnev.toString());
			System.out.println("Oktatok: "+oktatonev.toString());
			
			//B resz
			
			//RootElement
			Document doc= builder.newDocument();
			String baseString=document.getDocumentElement().getNodeName();
			Element rootElement = doc.createElement(baseString);
			doc.appendChild(rootElement);
			list=document.getElementsByTagName("Ora");
				Node node = list.item(0);

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
					Element kezdet = doc.createElement("Kezdet");
					kezdet.setTextContent(elementidopont.getElementsByTagName("Kezdet").item(0).getTextContent());
					idopont.appendChild(kezdet);
					//vege element
					Element vege = doc.createElement("Vege");
					vege.setTextContent(elementidopont.getElementsByTagName("Vege").item(0).getTextContent());
					idopont.appendChild(vege);
				}
				//Szak element
				Element szak = doc.createElement("Szak");
				szak.setTextContent(element.getElementsByTagName("Szak").item(0).getTextContent());
				ora.appendChild(szak);
			writeXML(doc, System.out, file);
			
			//D resz
			list=document.getElementsByTagName("Ora");
			for(int i=0; i<list.getLength();i++) {
				Node oraNode = list.item(i);
				//Adatkiiratas
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					Node idopont = list.item(i);
					if(idopont.getNodeType()==Node.ELEMENT_NODE) {
						Idopont ido = new Idopont();
						Element elementidopont = (Element) oraNode;
						//System.out.println("Nap:" + elementidopont.getElementsByTagName("Nap").item(0).getTextContent());
						//System.out.println("Kezdet:" + elementidopont.getElementsByTagName("Kezdet").item(0).getTextContent());
						//System.out.println("Vege:" + elementidopont.getElementsByTagName("Vege").item(0).getTextContent());
						ido.setNap(elementidopont.getElementsByTagName("Nap").item(0).getTextContent());
						ido.setKezdet(elementidopont.getElementsByTagName("Kezdet").item(0).getTextContent());
						ido.setVege(elementidopont.getElementsByTagName("Vege").item(0).getTextContent());
						idopontokList.add(ido);
					}
				}
				
			}
			System.out.println(idopontokList.toString());
			
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

		trans.transform(source, result);
		trans.transform(source, resultFile);

	}

}
