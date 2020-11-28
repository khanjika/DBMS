package sql.processor;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sql.InternalQuery;

import java.io.FileReader;
import java.io.IOException;

public class SelectProcessor implements IProcessor {
    String BASE_PATH = "src/main/java/dataFiles/";
    String DB_PATH = "src/main/java/dataFiles/databases.json";
    static SelectProcessor instance = null;

    private String username = null;
    private String database = null;

    public static SelectProcessor instance(){
        if(instance == null){
            instance = new SelectProcessor();
        }
        return instance;
    }

    @Override
    public boolean process(InternalQuery query, String username, String database) {
        this.username = username;
        this.database = database;

        String table = query.getSubject();
        String[] columns = query.getOption().split(",");
        String[] conditions = query.getCondition().split(",");

        String path = BASE_PATH+database+"/"+table+".json";
        JSONObject jsonObject = readFile(path);

        for (String column: columns) {
            System.out.print(column+" | ");
        }
        System.out.println();
        JSONArray rows = (JSONArray) jsonObject.get("data");
        for(int i=0;i<rows.size();i++){
            JSONObject row = (JSONObject) rows.get(i);
            for (String column: columns) {
                column = column.replace(" ","");
                System.out.print(row.get(column)+" | ");
            }
        }
        System.out.println();
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
