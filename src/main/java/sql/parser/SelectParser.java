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
        query = query.toLowerCase();
        Pattern pattern = Pattern.compile("select\\s(.*?)from\\s(.*?)(where\\s(.*?))?;", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(query);
        matcher.find();

        String option = matcher.group(1);
        String subject = matcher.group(2);
        String condition = matcher.group(4);

        InternalQuery internalQuery = new InternalQuery();
        internalQuery.setOption(option);
        internalQuery.setOption(subject);
        internalQuery.setOption(condition);

        return internalQuery;
    }
}
