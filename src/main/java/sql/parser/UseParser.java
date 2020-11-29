package sql.parser;

import sql.InternalQuery;

public class UseParser implements IParser {
    static UseParser instance = null;

    public static UseParser instance(){
        if(instance == null){
            instance = new UseParser();
        }
        return instance;
    }

    @Override
    public InternalQuery parse(String query) {
        query = query.replaceAll(";", "");
        String[] sqlWords = query.split(" ");

        String action = sqlWords[0];
        String database = sqlWords[1];

        InternalQuery internalQuery = new InternalQuery();
        internalQuery.set("action",action);
        internalQuery.set("database",database);

        return internalQuery;
    }
}
