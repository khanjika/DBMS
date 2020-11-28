import java.util.Scanner;

import login.Login;
import sql.QueryEngine;
import sun.rmi.runtime.Log;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		Login login = new Login();
		System.out.println("Please enter UserName:");
		String userName = sc.nextLine();

		System.out.println("Please enter Password:");
		String password = sc.nextLine();

		String loggedInUser = login.verification(userName, password);
		System.out.println(loggedInUser);

		if (loggedInUser != null) {

			Scanner queryInput = new Scanner(System.in);
			System.out.println("Enter SQL query to Create/Update/Insert/Delete in Database tables!");

			String sqlQuery = queryInput.nextLine();

			QueryEngine queryEngine = new QueryEngine();
			queryEngine.run(sqlQuery, loggedInUser);
			queryInput.close();
		}

		// General Logs

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
