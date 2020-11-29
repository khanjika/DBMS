package sql.processor;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sql.InternalQuery;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class InsertProcessor implements IProcessor {
    String BASE_PATH = "src/main/java/dataFiles/";
    String DB_PATH = "src/main/java/dataFiles/databases.json";
    static InsertProcessor instance = null;

    private String username = null;
    private String database = null;

    public static InsertProcessor instance(){
        if(instance == null){
            instance = new InsertProcessor();
        }
        return instance;
    }

    @Override
    public boolean process(InternalQuery query, String username, String database) {
        this.username = username;
        this.database = database;

        String table = (String) query.get("table");
        String[] columns = (String[]) query.get("columns");
        String[] values = (String[]) query.get("values");

        if(columns.length != values.length){
            System.out.println("Invalid columns and values pair.");
            return false;
        }

        String path = BASE_PATH+database+"/"+table+".json";
        JSONObject jsonObject = readFile(path);
        JSONArray data = (JSONArray) jsonObject.get("data");
        JSONObject row = new JSONObject();
        for(int i=0; i< columns.length;i++){
            row.put(columns[i],values[i]);
        }
        data.add(row);
        jsonObject.remove("data");
        jsonObject.put("data",data);

        try (Writer out = new FileWriter(path)) {
            out.write(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private JSONObject readFile(String path){
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        try(FileReader reader = new FileReader(path)){
            obj = (JSONObject) parser.parse(reader);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
