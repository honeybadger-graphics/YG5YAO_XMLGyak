package yg5yao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONWrite {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) 
    {
    	JSONParser parser = new JSONParser();
    	JSONArray newJSON = new JSONArray();
        try {  
    	JSONArray a = (JSONArray) parser.parse(new FileReader("./orarendYG5YAO.json"));
    	
    	  for (Object o : a)
    	  {
    	    JSONObject classes = (JSONObject) o;
    	    JSONObject newObject = new JSONObject();
    	    JSONObject newObject2 = new JSONObject();
    	    
    	    
    	    
    	    JSONObject time = (JSONObject) classes.get("Idopont");
    	    String day = (String) time.get("Nap");
    	    newObject2.put("Nap", day);
    	    String start = (String) time.get("Kezdet");
    	    newObject2.put("Kezdet",start);
    	   
    	    
    	    String end = (String) time.get("Vege");
    	  	newObject2.put("Vege",end);
    	    
    	   
    	    String name = (String) classes.get("Kurzusnev");
    	    newObject.put("Kurzusnev", name);
    	    String teacher = (String) classes.get("Oktato");
    	    newObject.put("Oktato", teacher);
    	    String classroom = (String) classes.get("Tanterem");
    	    newObject.put("Tanterem", classroom);
    	    newObject.put("Idopont", newObject2);
    	    String course = (String) classes.get("Szak");
    	    newObject.put("Szak", course);
    	    
    	    newJSON.add(newObject);
    	    //System.out.println(newObject);
    	    }
    	  } catch (FileNotFoundException e) {
              e.printStackTrace();
          } catch (IOException e) {
              e.printStackTrace();
          } catch (ParseException e) {
              e.printStackTrace();
          }
        try {
        	FileWriter file = new FileWriter("./orarendYG5YAO1.json");
        	file.write(newJSON.toJSONString());
        	file.flush();
        	file.close();
        }catch(IOException e){e.printStackTrace();}
        System.out.println("New JSON file:");
        System.out.println(newJSON);
    	  
    }
}
