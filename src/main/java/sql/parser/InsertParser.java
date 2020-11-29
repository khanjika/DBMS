package sql.parser;

import sql.InternalQuery;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InsertParser implements IParser {
    static InsertParser instance = null;

    public static InsertParser instance(){
        if(instance == null){
            instance = new InsertParser();
        }
        return instance;
    }

    @Override
    public InternalQuery parse(String query) {
        Pattern pattern = Pattern.compile("insert into\\s(.*?)\\s(.*?)\\svalues\\s(.*?);", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(query);
        matcher.find();

        String table = matcher.group(1);
        String[] columns = matcher.group(2)
                .replaceAll("[\\[\\](){}]","")
                .replace(" ","")
                .split(",");
        String[] values = matcher.group(3)
                .replaceAll("[\\[\\](){}]","")
                .replace(" ","")
                .replace("'","")
                .replace("\"","")
                .split(",");

        InternalQuery internalQuery = new InternalQuery();
        internalQuery.set("table",table);
        internalQuery.set("columns",columns);
        internalQuery.set("values",values);
        return internalQuery;
    }
}
