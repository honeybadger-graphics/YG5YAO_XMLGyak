package hu.domparse.yg5yao;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomModifyYG5YAO {

	public static void main(String[] args) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			// fájl beolvasása
			Document document = builder.parse(new File("../XMLYG5YAO.xml"));
			document.getDocumentElement().normalize();
			// Gyökér elem megkeresése
			System.out.println("<" + document.getDocumentElement().getNodeName()+">");



			//Utas adatai kiírása id alapján
			System.out.println("<!-------Modosítás előtt------->");
			hu.domparse.yg5yao.DomQueryYG5YAO.queryID(document, "Utas", "003");
			queryIDChangeThings(document, "Utas", "003","Poggyász","1db nagy");
			System.out.println("<!-------Modosítás után------->");
			hu.domparse.yg5yao.DomQueryYG5YAO.queryID(document, "Utas", "003");
			System.out.println("<!-------Modosítás előtt------->");
			hu.domparse.yg5yao.DomQueryYG5YAO.queryID(document, "Repülö", "AA_01");
			queryIDChangeThings(document, "Repülö", "AA_01","Hívószám","AA_02");
			System.out.println("<!-------Modosítás után------->");
			hu.domparse.yg5yao.DomQueryYG5YAO.queryID(document, "Repülö", "AA_01");
			System.out.println("<!-------Modosítás előtt------->");
			hu.domparse.yg5yao.DomQueryYG5YAO.queryID(document, "Légitársaság", "AA");
			queryIDChangeThings(document, "Légitársaság", "AA","E-mailcím","customerSupport@aa.com");
			System.out.println("<!-------Modosítás után------->");
			hu.domparse.yg5yao.DomQueryYG5YAO.queryID(document, "Légitársaság", "AA");



			System.out.println("</" + document.getDocumentElement().getNodeName()+">");
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			Source input = new DOMSource(document);
			Result output = new StreamResult(new File("../XMLYG5YAOModify.xml"));
			transformer.transform(input, output);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void queryIDChangeThings(Document document, String elemKeres, String mitKeres, String holValtoztat, String mireValtoztat) {
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
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				Node nodecim = list.item(i);
				if (nodecim.getNodeType() == Node.ELEMENT_NODE) {
					Element elemValtoztat = (Element) node;
					// id vizsgálata és ha megegyezzik vas1-el akkor irányítószám módosítás
					if (element.getAttribute(holKeres).equals(mitKeres)) {
						elemValtoztat.getElementsByTagName(holValtoztat).item(0).setTextContent(mireValtoztat);

					}

				}

			}
		}
	}

}
