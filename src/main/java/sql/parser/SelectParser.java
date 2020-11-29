package sql.parser;

import sql.InternalQuery;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SelectParser implements IParser {
    static SelectParser instance = null;

    public static SelectParser instance(){
        if(instance == null){
            instance = new SelectParser();
        }
        return instance;
    }

    @Override
    public InternalQuery parse(String query) {
        query = query.replaceAll(";", "");
        query = query+";";
        Pattern pattern = Pattern.compile("select\\s+(.*?)\\s*from\\s+(.*?)\\s*(where\\s(.*?)\\s*?);", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(query);
        matcher.find();

        String[] columns = (String[]) matcher.group(1).split(",");
        String table = matcher.group(2);
        String conditions = matcher.group(4);

        InternalQuery internalQuery = new InternalQuery();
        internalQuery.set("columns",columns);
        internalQuery.set("table",table);
        internalQuery.set("conditions",conditions);

        return internalQuery;
    }
}
