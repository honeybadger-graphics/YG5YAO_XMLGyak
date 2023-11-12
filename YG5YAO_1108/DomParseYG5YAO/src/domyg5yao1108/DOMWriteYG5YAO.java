package domyg5yao1108;

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
public class DOMWriteYG5YAO {

	public static void main(String[] args) throws ParserConfigurationException, TransformerException, FileNotFoundException{
		NodeList list;
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		FileOutputStream file = new FileOutputStream("./orarend1YG5YAO.xml");
		try {
			Document base = docBuilder.parse(new File("./orarendYG5YAO.xml"));
			base.getDocumentElement().normalize();
			//RootElement
			Document doc= docBuilder.newDocument();
			String baseString=base.getDocumentElement().getNodeName();
			Element rootElement = doc.createElement(baseString);
			doc.appendChild(rootElement);
			list=base.getElementsByTagName("Ora");
			for(int i=0; i<list.getLength();i++) {
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
				Element kurzusnev = doc.createElement("Kurzusnev");
				kurzusnev.setTextContent(element.getElementsByTagName("Kurzusnev").item(0).getTextContent());
				ora.appendChild(kurzusnev);
				//Oktato element
				Element oktato = doc.createElement("Oktato");
				oktato.setTextContent(element.getElementsByTagName("Oktato").item(0).getTextContent());
				ora.appendChild(oktato);
				//Tanterem element
				Element tanterem = doc.createElement("Tanterem");
				tanterem.setTextContent(element.getElementsByTagName("Tanterem").item(0).getTextContent());
				ora.appendChild(tanterem);
				Node idopontNode = list.item(i);
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

		trans.transform(source, result);
		trans.transform(source, resultFile);

	}

}
