package hu.domparse.yg5yao;

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
public class DomWriteYG5YAO {

	public static void main(String[] args) throws ParserConfigurationException, TransformerException, FileNotFoundException{
		NodeList list;
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		FileOutputStream file = new FileOutputStream("../XMLYG5YAO1.xml");
		try {
			Document base = docBuilder.parse(new File("../XMLYG5YAO.xml"));
			base.getDocumentElement().normalize();
			//RootElement
			Document doc= docBuilder.newDocument();
			String baseString=base.getDocumentElement().getNodeName();
			Element rootElement = doc.createElement(baseString);
			doc.appendChild(rootElement);

			list=base.getElementsByTagName("Légitársaság");
			for(int i=0; i<list.getLength();i++) {
				Node node = list.item(i);

				//Légitársaság element letrehozas
				Element element = (Element) node;
				Element Ltarsasag = doc.createElement(element.getNodeName());
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					rootElement.appendChild(Ltarsasag);
					//checks for attributes then adds them
					if(node.hasAttributes()) {
						NamedNodeMap nodeMap = node.getAttributes();
						for(int j=0;j<nodeMap.getLength();j++) {
							Node atrNode = nodeMap.item(j);
							Ltarsasag.setAttribute(atrNode.getNodeName(), atrNode.getNodeValue());
						}
					}
				}
				//név element
				Element Ltnev = doc.createElement("Név");
				Ltnev.setTextContent(element.getElementsByTagName("Név").item(0).getTextContent());
				Ltarsasag.appendChild(Ltnev);
				//weboldal element
				Element webold = doc.createElement("Weboldal");
				webold.setTextContent(element.getElementsByTagName("Weboldal").item(0).getTextContent());
				Ltarsasag.appendChild(webold);
				Node elerhetosegNode = list.item(i);
				//elerhetoseg
				if(elerhetosegNode.getNodeType()==Node.ELEMENT_NODE) {
					Element elerhetoBase = (Element) node;
					Element elerhetoNew = doc.createElement("Elérhetöség");
					Ltarsasag.appendChild(elerhetoNew);
					//email element
					Element nap = doc.createElement("E-mailcím");
					nap.setTextContent(elerhetoBase.getElementsByTagName("E-mailcím").item(0).getTextContent());
					elerhetoNew.appendChild(nap);
					//telefonszam element
					Element kezdet = doc.createElement("Telefonszám");
					kezdet.setTextContent(elerhetoBase.getElementsByTagName("Telefonszám").item(0).getTextContent());
					elerhetoNew.appendChild(kezdet);
				}
			}
			list=base.getElementsByTagName("Repülö_Tipus");
			for(int i=0; i<list.getLength();i++) {
				Node node = list.item(i);

				//Repülö_Tipus element
				Element element = (Element) node;
				Element rTipus = doc.createElement(element.getNodeName());	
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					rootElement.appendChild(rTipus);
					if(node.hasAttributes()) {
						NamedNodeMap nodeMap = node.getAttributes();
						for(int j=0;j<nodeMap.getLength();j++) {
							Node atrNode = nodeMap.item(j);
							rTipus.setAttribute(atrNode.getNodeName(), atrNode.getNodeValue());
						}
					}
				}
				//gyarto element
				Element gyarto = doc.createElement("Gyartó");
				gyarto.setTextContent(element.getElementsByTagName("Gyartó").item(0).getTextContent());
				rTipus.appendChild(gyarto);
				//elnevezes element
				Element elnev = doc.createElement("Elnevezés");
				elnev.setTextContent(element.getElementsByTagName("Elnevezés").item(0).getTextContent());
				rTipus.appendChild(elnev);
				//hajtomu element
				Element hajtmu = doc.createElement("Hajtómű");
				hajtmu.setTextContent(element.getElementsByTagName("Hajtómű").item(0).getTextContent());
				rTipus.appendChild(hajtmu);

			}
			list=base.getElementsByTagName("Flotta");
			for(int i=0; i<list.getLength();i++) {
				Node node = list.item(i);

				//flotta element
				Element element = (Element) node;
				Element flotta = doc.createElement(element.getNodeName());	
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					rootElement.appendChild(flotta);
					if(node.hasAttributes()) {
						NamedNodeMap nodeMap = node.getAttributes();
						for(int j=0;j<nodeMap.getLength();j++) {
							Node atrNode = nodeMap.item(j);
							flotta.setAttribute(atrNode.getNodeName(), atrNode.getNodeValue());
						}
					}
				}
				//darabszam element
				Element db = doc.createElement("Darabszám");
				db.setTextContent(element.getElementsByTagName("Darabszám").item(0).getTextContent());
				flotta.appendChild(db);
			}
			list=base.getElementsByTagName("Repülö");
			for(int i=0; i<list.getLength();i++) {
				Node node = list.item(i);

				//repulo element
				Element element = (Element) node;
				Element repulo = doc.createElement(element.getNodeName());	
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					rootElement.appendChild(repulo);
					if(node.hasAttributes()) {
						NamedNodeMap nodeMap = node.getAttributes();
						for(int j=0;j<nodeMap.getLength();j++) {
							Node atrNode = nodeMap.item(j);
							repulo.setAttribute(atrNode.getNodeName(), atrNode.getNodeValue());
						}
					}
				}
				//gyartasiszam element
				Element factoryNumber = doc.createElement("Gyártásiszám");
				factoryNumber.setTextContent(element.getElementsByTagName("Gyártásiszám").item(0).getTextContent());
				repulo.appendChild(factoryNumber);
				//hivojel element
				Element callsign = doc.createElement("Hívószám");
				callsign.setTextContent(element.getElementsByTagName("Hívószám").item(0).getTextContent());
				repulo.appendChild(callsign);
				//szemelyzet element
				NodeList links = element.getElementsByTagName("Személyzet");
				for(int j=0; j<links.getLength(); j++)
				{
					Node link = links.item(j);
					Element szemelyzet = doc.createElement("Személyzet");
					szemelyzet.setTextContent(link.getTextContent());
					repulo.appendChild(szemelyzet);
				}
			}
			list=base.getElementsByTagName("Járat");
			for(int i=0; i<list.getLength();i++) {
				Node node = list.item(i);

				//jarat element
				Element element = (Element) node;
				Element jarat = doc.createElement(element.getNodeName());	
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					rootElement.appendChild(jarat);
					if(node.hasAttributes()) {
						NamedNodeMap nodeMap = node.getAttributes();
						for(int j=0;j<nodeMap.getLength();j++) {
							Node atrNode = nodeMap.item(j);
							jarat.setAttribute(atrNode.getNodeName(), atrNode.getNodeValue());
						}
					}
				}
				//indulas element
				Element from = doc.createElement("Indulás");
				from.setTextContent(element.getElementsByTagName("Indulás").item(0).getTextContent());
				jarat.appendChild(from);
				//cel element
				Element to = doc.createElement("Cél");
				to.setTextContent(element.getElementsByTagName("Cél").item(0).getTextContent());
				jarat.appendChild(to);
				//idopont element
				Element date = doc.createElement("Idöpont");
				date.setTextContent(element.getElementsByTagName("Idöpont").item(0).getTextContent());
				jarat.appendChild(date);
			}
			list=base.getElementsByTagName("Utas");
			for(int i=0; i<list.getLength();i++) {
				Node node = list.item(i);

				//utas element letrehozas
				Element element = (Element) node;
				Element utas = doc.createElement(element.getNodeName());
				if(node.getNodeType()==Node.ELEMENT_NODE) {
					rootElement.appendChild(utas);
					//checks for attributes then adds them
					if(node.hasAttributes()) {
						NamedNodeMap nodeMap = node.getAttributes();
						for(int j=0;j<nodeMap.getLength();j++) {
							Node atrNode = nodeMap.item(j);
							utas.setAttribute(atrNode.getNodeName(), atrNode.getNodeValue());
						}
					}
				}
				//név element
				Element uNev = doc.createElement("Név");
				uNev.setTextContent(element.getElementsByTagName("Név").item(0).getTextContent());
				utas.appendChild(uNev);

				Node elerhetosegNode = list.item(i);
				//elerhetoseg
				if(elerhetosegNode.getNodeType()==Node.ELEMENT_NODE) {
					Element elerhetoBase = (Element) node;
					Element elerhetoNew = doc.createElement("Elérhetöség");
					utas.appendChild(elerhetoNew);
					//email element
					Element nap = doc.createElement("E-mailcím");
					nap.setTextContent(elerhetoBase.getElementsByTagName("E-mailcím").item(0).getTextContent());
					elerhetoNew.appendChild(nap);
					//telefon element
					Element kezdet = doc.createElement("Telefonszám");
					kezdet.setTextContent(elerhetoBase.getElementsByTagName("Telefonszám").item(0).getTextContent());
					elerhetoNew.appendChild(kezdet);
				}
				//poggyasz element
				Element bagge = doc.createElement("Poggyász");
				bagge.setTextContent(element.getElementsByTagName("Poggyász").item(0).getTextContent());
				utas.appendChild(bagge);
			}
			writeXML(doc, System.out, file);
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
	//Kiiras file, console
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




