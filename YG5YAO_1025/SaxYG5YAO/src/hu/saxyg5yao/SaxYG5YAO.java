package hu.saxyg5yao;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class SaxYG5YAO {
		private static final String FILENAME = "src/resources/YG5YAO_kurzusfelvetel.xml";

	    public static void main(String[] args) {

	        SAXParserFactory factory = SAXParserFactory.newInstance();

	        try {
	            SAXParser saxParser = factory.newSAXParser();

	            XMLHandler handler = new XMLHandler();

	            saxParser.parse(FILENAME, handler);

	        } catch (ParserConfigurationException | SAXException | IOException e) {
	            e.printStackTrace();
	        }

	    }
	}


