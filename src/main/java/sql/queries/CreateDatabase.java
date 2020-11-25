package sql.queries;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CreateDatabase {
    boolean databaseExists = false;
	public void parseQuery(String query, String username) {
		query = query.replaceAll(",", " ");
		query = query.replaceAll("[^a-zA-Z ]", "");
		String[] sqlWords = query.split(" ");	
		createDBdir(sqlWords, username);
	}
	
	public void createDBdir(String[] queryArr, String username) {
		String path = "/Users/prabhjotkaur/Documents/dbms-5408/src/main/java/dataFiles/";
		path = path + queryArr[2];
		File file = new File(path);
		System.out.println(path);
		boolean bool = file.mkdir();
		if(bool){
	         System.out.println("DB created successfully");
	         parseDBFile(queryArr[2], username);
	      }else{
	         System.out.println("Sorry couldn’t create DB");
	      }
	}
	
	public void parseDBFile(String dbName, String username) {
		databaseExists = false;
		JSONParser parser = new JSONParser();
		try (FileReader reader = new FileReader(
				"/Users/prabhjotkaur/Documents/dbms-5408/src/main/java/dataFiles/databases.json"))
        {
            //Read JSON file
            Object obj = parser.parse(reader);
 
            JSONArray dblist = (JSONArray) obj;
            System.out.println(dblist);

            dblist.forEach(db -> {
            	System.out.println(db);
            	if(((JSONObject) db).get("dbName") == dbName) {
            		if (((JSONObject) db).get("username") == username) {
            			databaseExists = true;
            		}
            	}
            });
            if(!databaseExists) {
	    		JSONObject dbObj = new JSONObject();
	    		dbObj.put("dbName", dbName);
	    		dbObj.put("username", username);
	    		dblist.add(dbObj);
	    		writeDBFile(dblist);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}

		public void writeDBFile(JSONArray dblist) {
		try (FileWriter file = new FileWriter(
				"/Users/prabhjotkaur/Documents/dbms-5408/src/main/java/dataFiles/databases.json")) {
			 
            file.write(dblist.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}
