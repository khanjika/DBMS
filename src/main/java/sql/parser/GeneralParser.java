package sql.parser;

import sql.InternalQuery;

public class GeneralParser implements IParser{
	static GeneralParser instance = null;

    public static GeneralParser instance(){
        if(instance == null){
            instance = new GeneralParser();
        }
        return instance;
    }

    @Override
    public InternalQuery parse(String query) {
        query = query.replaceAll(";", "");
        query = query.replaceAll(",", " ");
        query = query.replaceAll("[^a-zA-Z ]", "");
        String[] sqlWords = query.split(" ");

        String action = sqlWords[0];
        String subject = sqlWords[1];
        //String option = sqlWords[2];

        InternalQuery internalQuery = new InternalQuery();
        internalQuery.setAction(action);
        internalQuery.setSubject(subject);
        //internalQuery.setOption(option);

        return internalQuery;
    }

}
