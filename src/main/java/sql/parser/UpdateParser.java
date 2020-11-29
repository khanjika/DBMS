package sql.parser;

import sql.InternalQuery;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateParser implements IParser{
    static UpdateParser instance = null;

    public static UpdateParser instance(){
        if(instance == null){
            instance = new UpdateParser();
        }
        return instance;
    }

    @Override
    public InternalQuery parse(String query) {
        Pattern pattern = Pattern.compile("update\\s(.*?)set\\s(.*?)where\\s(.*?)?;");
        Matcher matcher = pattern.matcher(query);
        boolean match = matcher.matches ();

        if(match == true) {
            String tableName = matcher.group (1);
            String option = matcher.group (2);
            String condition = matcher.group (3);

            InternalQuery internalQuery = new InternalQuery ();
            internalQuery.setTableName (tableName);
            internalQuery.setOption (option);
            internalQuery.setCondition (condition);
            return internalQuery;
        }
        System.out.println ("Invalid query !!");
        return null;
    }
}
