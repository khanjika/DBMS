package sql.parser;

import sql.Query;

public class CreateParser implements IParser {
    static CreateParser instance = null;

    public static CreateParser instance(){
        if(instance == null){
            instance = new CreateParser();
        }
        return instance;
    }

    @Override
    public Query parse(String query) {
        query = query.replaceAll(";", "");
        query = query.replaceAll(",", " ");
        query = query.replaceAll("[^a-zA-Z ]", "");
        String[] sqlWords = query.split(" ");

        String action = sqlWords[0];
        String subject = sqlWords[1];
        String option = sqlWords[2];

        Query queryObj = new Query();
        queryObj.setAction(action);
        queryObj.setSubject(subject);
        queryObj.setOption(option);

        return queryObj;
    }
}
