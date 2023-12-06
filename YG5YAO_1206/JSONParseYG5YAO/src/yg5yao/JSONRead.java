package yg5yao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
 
public class JSONRead
{
    
    public static void main(String[] args) 
    {
    	JSONParser parser = new JSONParser();

        try {  
    	JSONArray a = (JSONArray) parser.parse(new FileReader("./orarendYG5YAO.json"));

    	  for (Object o : a)
    	  {
    	    JSONObject classes = (JSONObject) o;
    	    String id = (String) classes.get("@Id");
    	    System.out.println("ID: "+id);
    	    String type = (String) classes.get("@TIPUS");
    	    System.out.println("TIPUS: "+type);
    	    String name = (String) classes.get("Kurzusnev");
    	    System.out.println("Kurzusnev: "+name);

    	    String teacher = (String) classes.get("Oktato");
    	    System.out.println("Oktato:"+teacher);

    	    String classroom = (String) classes.get("Tanterem");
    	    System.out.println("Tanterem: "+classroom);
    	    
    	    System.out.println("Idopont: ");
    	    JSONObject time = (JSONObject) classes.get("Idopont");
    	    String day = (String) time.get("Nap");
    	    System.out.println("	Nap: "+day);
    	    String start = (String) time.get("Kezdet");
    	    System.out.println("	Kezdet: "+start);
    	    String end = (String) time.get("Vege");
    	    System.out.println("	Vege: "+end);
    	    String course = (String) classes.get("Szak");
    	    System.out.println("Szak: "+course);
    	    }
    	  } catch (FileNotFoundException e) {
              e.printStackTrace();
          } catch (IOException e) {
              e.printStackTrace();
          } catch (ParseException e) {
              e.printStackTrace();
          }
    	  
    }
 
   
}