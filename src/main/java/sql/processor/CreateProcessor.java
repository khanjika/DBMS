package sql.processor;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sql.InternalQuery;

import java.io.*;
import java.sql.Timestamp;

public class CreateProcessor implements IProcessor {
    String BASE_PATH = "src/main/java/dataFiles/";
    String DB_PATH = "src/main/java/dataFiles/databases.json";
    static CreateProcessor instance = null;

    private boolean databaseExists = false;
    private String username = null;
    private String database = null;

    public static CreateProcessor instance(){
        if(instance == null){
            instance = new CreateProcessor();
        }
        return instance;
    }

    
    public boolean processCreateQuery(InternalQuery internalQuery, String query, String username, String database) {
        this.username = username;
        this.database = database;
        if(internalQuery.getSubject().equals("database")){
            return createDB(internalQuery);
        }else{
            return createTable(internalQuery,query, username, database);
        }
    }

    private boolean createDB(InternalQuery internalQuery) {
        String name = internalQuery.getOption();
        String path = BASE_PATH + name;
        File file = new File(path);
        System.out.println(path);
        boolean bool = file.mkdir();
        if(bool){
            System.out.println("DB created successfully");
            parseDBFile(name);
        }else{
            System.out.println("Sorry couldn’t create DB");
        }
        return true;
    }

    private boolean createTable(InternalQuery internalQuery, String query, String username, String database) {
    	query = query.replaceAll(";", "");
        query = query.replaceAll(",", " ");
        query = query.replaceAll("[^a-zA-Z ]", "");
        String[] sqlWords = query.split(" ");
        System.out.println(sqlWords);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        JSONObject colObj = new JSONObject();
        JSONObject meta = new JSONObject();
        JSONObject indexes = new JSONObject();
        JSONArray  data = new JSONArray();
        
        meta.put("rows", 0);
        meta.put("createdAt", timestamp.toString());
        for(int i = 3; i< sqlWords.length; i+=2) {
        	colObj.put(sqlWords[i], sqlWords[i+1]);
        }
        String tableName = internalQuery.getOption();
        JSONObject tableObj = new JSONObject();
        
        tableObj.put("columns",colObj);
        tableObj.put("meta",meta);
        tableObj.put("indexes",indexes);
        tableObj.put("data",data);
        
        
        
		try (FileWriter file = new FileWriter(BASE_PATH + database +"/"+tableName+".json")) {
		    file.write(tableObj.toJSONString());
		    file.flush();
		} catch (IOException e) {
		    e.printStackTrace();
		}
        return true;
    }

    private void parseDBFile(String name) {
        databaseExists = false;
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(DB_PATH)) {
            //Read JSON file
            Object obj = parser.parse(reader);

            JSONArray dblist = (JSONArray) obj;
            System.out.println(dblist);

            dblist.forEach(db -> {
                System.out.println(db);
                if(((JSONObject) db).get("name") == name) {
                    if (((JSONObject) db).get("username") == username) {
                        databaseExists = true;
                    }
                }
            });
            if(!databaseExists) {
                JSONObject dbObj = new JSONObject();
                dbObj.put("name", name);
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

    private void writeDBFile(JSONArray dblist) {
        try (FileWriter file = new FileWriter(DB_PATH)) {
            file.write(dblist.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


	@Override
	public boolean process(InternalQuery query, String username, String database) {
		// TODO Auto-generated method stub
		return false;
	}


}
