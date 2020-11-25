import logging.events.*;
import java.util.Scanner; 

import logging.general.DatabaseLogger;
import logging.general.ICustomLogger;
import logging.general.QueryExecutionLogger;
import sql.queries.SQLParser;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
    	
    	Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("For login enter: 1 ");
        System.out.println("For creating new user enter: 2 ");

        String userInput = myObj.nextLine();  // Read user input
        if(Integer.parseInt(userInput) == 1) {
        	System.out.println("logged in ");
        	String Username = "user1";
        	Scanner queryInput = new Scanner(System.in);
        	System.out.println("Enter SQL query to Create/Update/Insert/Delete in Database tables!");
        	
        	String sqlQuery = queryInput.nextLine();
        	
        	SQLParser sqlparser = new SQLParser();
        	sqlparser.queryProcessor(sqlQuery, Username);
        	queryInput.close();
        	
        } else if (Integer.parseInt(userInput) == 2) {
        	System.out.println("create new user");
        } else {
        	System.out.println("Invalid input");
        }
        myObj.close();
        
        
        //General Logs

//        ICustomLogger queryExecutionLogger = new QueryExecutionLogger("SELECT * FROM users", (float) 1.00, 1);
//        queryExecutionLogger.log();
//
//        Map<String,Integer> table_stats = new HashMap<>();
//
//        for(int i=0;i<10;i++){
//            table_stats.put("table"+i,10);
//        }
//        ICustomLogger databaseLogger = new DatabaseLogger(10, table_stats);
//        databaseLogger.log();
//
//        //Event Logs
//
//        IEventListener queryListener = new QueryListener();
//        queryListener.recordEvent();
//
//        IEventListener databaseListener = new DatabaseListener();
//        databaseListener.recordEvent();
//
//        IEventListener concurrentTransactionListener = new ConcurrentTransactionListener();
//        concurrentTransactionListener.recordEvent();
//
//        IEventListener crashListener = new CrashListener();
//        crashListener.recordEvent();
    }
}
