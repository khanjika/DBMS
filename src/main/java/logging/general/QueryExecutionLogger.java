package logging.general;

import java.util.logging.Logger;

public class QueryExecutionLogger implements ICustomLogger {
    Logger logger = Logger.getLogger(QueryExecutionLogger.class.getName());

    private final String query;
    private final float execution_time;
    private final int rows_affected;

    public QueryExecutionLogger(String query, float execution_time, int rows_affected) {
        this.query = query;
        this.execution_time = execution_time;
        this.rows_affected = rows_affected;
    }

    @Override
    public void log() {
        logger.info("Query executed: " + query + "; execution time: " + execution_time + "; rows affected: " + rows_affected);
    }
}
