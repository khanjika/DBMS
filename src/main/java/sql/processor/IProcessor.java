package sql.processor;

import sql.Query;

public interface IProcessor {
    boolean process(Query query, String Username);
}
