package xpathyg5yao;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class xPathYG5YAO {
	// TODO add xsd for xml

	public static void main(String[] args) {
		try {
			DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			//fajl olvasas
			Document document = builder.parse("./studentYG5YAO.xml");
			document.getDocumentElement().normalize();
			XPath xPath = XPathFactory.newInstance().newXPath();
			//�sszes Student element amely a class gyerekei kiv�laszt 
			String yg5yao="class/student";
			// student amely id = 02
			String yg5yao1="class/student[@id='02']";
			//�sszes student f�ggetlen�l, hol vannak dokumentumban
			String yg5yao2="//student";
			//m�sodik student element, amely a class root gyerekeleme
			String yg5yao3="class/student[position()=2]";
			//utols� student element, amely class root gyerekeelem
			String yg5yao4="class/student[last()]";
			//utols� el�tti student element, amely ....
			String yg5yao5="class/student[last()-1]";
			//els� k�t student element, amely....
			String yg5yao6="class/student[postion()=1 or position()=2]";
			//v�lassza ki class root �sszes gyerek elem�t
			String yg5yao7="class/*";
			//�sszes student element ahol van legal�bb egy valamilyen attributum
			String yg5yao8="class/student[@*]";
			//dokumentum �sszes eleme
			String yg5yao9="descendant-or-self::*";
			//student element ahol kor >20!
			String yg5yao10="class/student[number(kor)>20]";
			// �sszes student elem �sszes keresztnev vagy vezeteknev csom�pontot
			String yg5yao11="//studnet[name(keresztnev) and name(vezeteknev)]";
			
			NodeList nodeList= (NodeList) xPath.compile(yg5yao).evaluate(document, XPathConstants.NODESET);
			for(int i=0;i<nodeList.getLength();i++) {
				Node node = nodeList.item(i);
				System.out.println();
			}
		}
		catch(ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
			e.printStackTrace();
		}
	}

}
