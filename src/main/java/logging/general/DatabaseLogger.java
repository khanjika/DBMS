package logging.general;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class DatabaseLogger implements ICustomLogger {
    Logger logger = Logger.getLogger(DatabaseLogger.class.getName());

    private int table_count;
    private Map<String,Integer> table_stats = new HashMap<>();

    public DatabaseLogger(){
        //ToDo: Calculate table and table stats on own
    }

    public DatabaseLogger(int table_count, Map<String, Integer> table_stats) {
        this.table_count = table_count;
        this.table_stats = table_stats;
    }

    @Override
    public void log() {
        logger.info("No. of tables: " + table_count );
        table_stats.forEach((table_name, row_count) -> {
            logger.info(table_name + " has " + table_count + " rows");
        });
    }
}
