package sql.processor;

import org.json.simple.parser.ParseException;
import sql.InternalQuery;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class UseProcessor implements IProcessor {
    String BASE_PATH = "src/main/java/dataFiles/";
    String DB_PATH = "src/main/java/dataFiles/databases.json";

    static UseProcessor instance = null;
    private String username = null;
    private String database = null;

    public static UseProcessor instance(){
        if(instance == null){
            instance = new UseProcessor();
        }
        return instance;
    }

    public String getDatabase() {
        return database;
    }

    @Override
    public boolean process(InternalQuery query, String username, String database) {
        this.username = username;
        this.database = database;

        this.database = query.getSubject();
        Path path = Path.of(BASE_PATH + database);
        if (Files.exists(path)) {
            return true;
        }else{
            System.out.println("Database not found.");
            return false;
        }
    }
}
