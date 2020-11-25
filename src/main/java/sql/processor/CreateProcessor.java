package sql.processor;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sql.Query;

import java.io.*;

public class CreateProcessor implements IProcessor {
    String BASE_PATH = "src/main/java/dataFiles/";
    String DB_PATH = "src/main/java/dataFiles/databases.json";
    static CreateProcessor instance = null;

    private boolean databaseExists = false;

    public static CreateProcessor instance(){
        if(instance == null){
            instance = new CreateProcessor();
        }
        return instance;
    }

    @Override
    public boolean process(Query queryObj, String username) {
        if(queryObj.getSubject().equals("database")){
            return createDB(queryObj,username);
        }else{
            return createTable(queryObj,username);
        }
    }

    private boolean createDB(Query queryObj, String username) {
        String dbName = queryObj.getOption();
        String path = BASE_PATH + dbName;
        File file = new File(path);
        System.out.println(path);
        boolean bool = file.mkdir();
        if(bool){
            System.out.println("DB created successfully");
            parseDBFile(dbName, username);
        }else{
            System.out.println("Sorry couldn’t create DB");
        }
        return true;
    }

    private boolean createTable(Query queryObj, String username) {
//        ToDo:: create table logic
        return true;
    }

    private void parseDBFile(String dbName, String username) {
        databaseExists = false;
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(DB_PATH)) {
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

    private void writeDBFile(JSONArray dblist) {
        try (FileWriter file = new FileWriter(DB_PATH)) {
            file.write(dblist.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
