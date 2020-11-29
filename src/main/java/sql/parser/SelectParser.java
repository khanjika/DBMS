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
        Pattern pattern = Pattern.compile("select\\s(.*?)from\\s(.*?)(where\\s(.*?))?;", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(query);
        matcher.find();

        String[] columns = matcher.group(1).split(",");
        String table = matcher.group(2);
        String[] conditions = matcher.group(4).split(",");

        InternalQuery internalQuery = new InternalQuery();
        internalQuery.set("columns",columns);
        internalQuery.set("table",table);
        internalQuery.set("conditions",conditions);

        return internalQuery;
    }
}
