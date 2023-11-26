package xpathyg5yao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class xPathModifyYG5YAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			FileOutputStream file = new FileOutputStream("./orarendYG5YAO1.xml");
			DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			//fajl olvasas
			Document document = builder.parse("./orarendYG5YAO.xml");
			document.getDocumentElement().normalize();
			XPath xPath = XPathFactory.newInstance().newXPath();
			
			String[] yg5yao =  {"YG5YAO_Orarend/Ora[last()]/Oktato","YG5YAO_Orarend/Ora/Idopont/Nap[text()='Kedd']","YG5YAO_Orarend/Ora/Tanterem[text()='In/202 In II. em. 202']"};
			for(int i=0;i<yg5yao.length;i++) {
				NodeList nodes= (NodeList) xPath.compile(yg5yao[i]).evaluate(document, XPathConstants.NODESET);
				for(int j=0;j<nodes.getLength();j++) {
				if(i==0) {
					nodes.item(j).setTextContent("Dr. Kovacs Laszlo");
				}
				else if(i==1) {
					nodes.item(j).setTextContent("Csutortok");
				}
				else {
					nodes.item(j).setTextContent("In/203 In II. em. 203");
				}
				
				}
				}
			write(document,System.out);
			write(document,file);
			}
		catch(ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
			e.printStackTrace();
			}
		}
	public static void write(Document doc, OutputStream out) throws IOException {
	    try {
	      Transformer t = TransformerFactory.newInstance().newTransformer();
	      DocumentType dt = doc.getDoctype();
	      if (dt != null) {
	        String pub = dt.getPublicId();
	        if (pub != null) {
	          t.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, pub);
	        }
	        t.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, dt.getSystemId());
	      }
	      t.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); // NOI18N
	      t.setOutputProperty(OutputKeys.INDENT, "yes"); // NOI18N
	      t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); // NOI18N
	      Source source = new DOMSource(doc);
	      Result result = new StreamResult(out);
	      t.transform(source, result);
	    } catch (Exception e) {
	      throw (IOException) new IOException(e.toString()).initCause(e);
	    } catch (TransformerFactoryConfigurationError e) {
	      throw (IOException) new IOException(e.toString()).initCause(e);
	    }
	  }
	}
