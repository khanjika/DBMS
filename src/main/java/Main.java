import logging.events.*;
import logging.general.DatabaseLogger;
import logging.general.ICustomLogger;
import logging.general.QueryExecutionLogger;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        //General Logs

        ICustomLogger queryExecutionLogger = new QueryExecutionLogger("SELECT * FROM users", (float) 1.00, 1);
        queryExecutionLogger.log();

        Map<String,Integer> table_stats = new HashMap<>();

        for(int i=0;i<10;i++){
            table_stats.put("table"+i,10);
        }
        ICustomLogger databaseLogger = new DatabaseLogger(10, table_stats);
        databaseLogger.log();

        //Event Logs

        IEventListener queryListener = new QueryListener();
        queryListener.recordEvent();

        IEventListener databaseListener = new DatabaseListener();
        databaseListener.recordEvent();

        IEventListener concurrentTransactionListener = new ConcurrentTransactionListener();
        concurrentTransactionListener.recordEvent();

        IEventListener crashListener = new CrashListener();
        crashListener.recordEvent();
    }
}
