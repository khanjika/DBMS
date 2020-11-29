package sql.parser;

import sql.InternalQuery;

public class ListParser implements IParser {
    static ListParser instance = null;

    public static ListParser instance(){
        if(instance == null){
            instance = new ListParser();
        }
        return instance;
    }

    @Override
    public InternalQuery parse(String query) {
        query = query.replaceAll(";", "");
        String[] sqlWords = query.split(" ");

        String action = sqlWords[0];
        String subject = sqlWords[1];

        InternalQuery internalQuery = new InternalQuery();
        internalQuery.set("action",action);
        internalQuery.set("subject",subject);

        return internalQuery;
    }
}
