package hu.saxyg5yao;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler {
	private StringBuilder currentValue = new StringBuilder();
	@Override
	  public void startDocument() {
	      System.out.println("Start Document");
	  }

	  @Override
	  public void endDocument() {
	      System.out.println("End Document");
	  }

	  @Override
	  public void startElement(
	          String uri,
	          String localName,
	          String qName,
	          Attributes attributes) {
		  
		  currentValue.setLength(0);

		  if (qName.equalsIgnoreCase("YG5YAO_kurzusfelvetel")) {
			  String tanev = attributes.getValue(0);
			  String egyetem = attributes.getValue(1);
	          System.out.printf("YG5YAO_kurzusfelvetel, (tanev=%s, egyetem=%S) start %n", tanev, egyetem);
	      }
		  
		  
	      if (qName.equalsIgnoreCase("hallgato")) {
	          String hid = attributes.getValue(0);
	          System.out.printf("\t hallgato ( hid=%s ) start %n" , hid);
	      }

	      if (qName.equalsIgnoreCase("hnev")) {   
	          System.out.printf("\t\t hnev start %n");
	      }
	      
	      if (qName.equalsIgnoreCase("szulev")) {   
	          System.out.printf("\t\t szulev start %n");
	      }
	      
	      if (qName.equalsIgnoreCase("szak")) {
	    	  String evf = attributes.getValue(0);
	          System.out.printf("\t\t szak (evf=%s) start %n", evf);
	      }
	      
	      if (qName.equalsIgnoreCase("kurzusok")) {   
	          System.out.printf("\t kurzusok start %n");
	      }
	      
	      if (qName.equalsIgnoreCase("kurzus")) {
	          String id = attributes.getValue(0);
	          String jovahagy=attributes.getValue("jovahagy");
	          String nyelv=attributes.getValue("nyelv");
	          //Should switch it up and check if jovahagy, nyelv returns null, if not then write it out. 
	          //Either way, there will be DEAD code warning 'cause both can get Null when it is running depending on XML. 
	          if(jovahagy != null) {
	        	  System.out.printf("\t\t kurzus ( id=%s, jovahagy=%s ) start %n" , id,jovahagy);
	          }
	          else if(nyelv != null) {
	        	  System.out.printf("\t\t kurzus ( id=%s, nyelv=%s ) start %n" , id,nyelv);
	          }
	          //DEAD CODE, never gets called, depending on XML 
	          /*else if(nyelv != null && jovahagy != null ) { 
	        	  System.out.printf("\t\t kurzus ( id=%s, jovahagy=%s, nyelv=%s ) start %n" , id, jovahagy,nyelv);
	          }*/
	          else {
	          System.out.printf("\t\t kurzus ( id=%s ) start %n" , id);
	          }
	      }
	      
	      if (qName.equalsIgnoreCase("kurzusnev")) {   
	          System.out.printf("\t\t kurzusnev start %n");
	      }
	      
	      if (qName.equalsIgnoreCase("kredit")) {   
	          System.out.printf("\t\t kredit start %n");
	      }
	      
	      if (qName.equalsIgnoreCase("hely")) {   
	          System.out.printf("\t\t hely start %n");
	      }
	      
	      if (qName.equalsIgnoreCase("idopont")) {   
	          System.out.printf("\t\t idopont start %n");
	      }
	      if (qName.equalsIgnoreCase("oktato")) {   
	          System.out.printf("\t\t oktato start %n");
	      }
	      if (qName.equalsIgnoreCase("gyakvezer")) {   
	          System.out.printf("\t\t gyakvezer start %n");
	      }
	  }

	  @Override
	  public void endElement(String uri,
	                         String localName,
	                         String qName) {


		  if (qName.equalsIgnoreCase("YG5YAO_kurzusfelvetel")) {
	          System.out.printf("YG5YAO_kurzusfelvetel end %n");
	      }
		  if (qName.equalsIgnoreCase("hallgato")) {
	          System.out.printf("\t hallgato end %n");
	      }
	      if (qName.equalsIgnoreCase("hnev")) {
	    	  System.out.printf("\t\t\t %s%n" , currentValue.toString());
	          System.out.printf("\t\t hnev end %n");
	      }

	      if (qName.equalsIgnoreCase("szulev")) {
	    	  System.out.printf("\t\t\t %s%n" , currentValue.toString());
	          System.out.printf("\t\t szulev end %n");
	      }
	      
	      if (qName.equalsIgnoreCase("szak")) {
	    	  System.out.printf("\t\t\t %s%n" , currentValue.toString());
	          System.out.printf("\t\t szak end %n");
	      }
	      
	      if (qName.equalsIgnoreCase("kurzusok")) {   
	          System.out.printf("\t kurzusok end %n");
	      }
	      
	      if (qName.equalsIgnoreCase("kurzus")) {
	          System.out.printf("\t\t kurzus end %n");
	      }
	      
	      if (qName.equalsIgnoreCase("kurzusnev")) {  
	    	  System.out.printf("\t\t\t %s%n" , currentValue.toString());
	          System.out.printf("\t\t kurzusnev end %n");
	      }
	      
	      if (qName.equalsIgnoreCase("kredit")) {   
	    	  System.out.printf("\t\t\t %s%n" , currentValue.toString());
	          System.out.printf("\t\t kredit end %n");
	      }
	      
	      if (qName.equalsIgnoreCase("hely")) {   
	    	  System.out.printf("\t\t\t %s%n" , currentValue.toString());
	          System.out.printf("\t\t hely end %n");
	      }
	      
	      if (qName.equalsIgnoreCase("idopont")) {   
	    	  System.out.printf("\t\t\t %s%n" , currentValue.toString());
	          System.out.printf("\t\t idopont end %n");
	      }
	      if (qName.equalsIgnoreCase("oktato")) {   
	    	  System.out.printf("\t\t\t %s%n" , currentValue.toString());
	          System.out.printf("\t\t oktato end %n");
	      }
	      if (qName.equalsIgnoreCase("gyakvezer")) {   
	    	  System.out.printf("\t\t\t %s%n" , currentValue.toString());
	          System.out.printf("\t\t gyakvezer end %n");
	      }

	  }
	
	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		currentValue.append(ch, start, length);
	}

}
