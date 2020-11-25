package sql.processor;

import sql.Query;

public class SelectProcessor implements IProcessor {
    static SelectProcessor instance = null;

    public static SelectProcessor instance(){
        if(instance == null){
            instance = new SelectProcessor();
        }
        return instance;
    }

    @Override
    public boolean process(Query query, String Username) {
        String table = query.getSubject();
        String[] columns = query.getOption().split(",");
        String[] conditions = query.getCondition().split(",");
//        ToDo: Logic to list data
        return false;
    }
}
