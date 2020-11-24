package sql.queries;
import java.util.regex.Pattern;

public class SQLParser {
	
	public String queryProcessor(String query) {
		System.out.println(query);
		Pattern create_pattern = Pattern.compile("create", Pattern.CASE_INSENSITIVE);
		Pattern select_pattern = Pattern.compile("select", Pattern.CASE_INSENSITIVE);
		Pattern update_pattern = Pattern.compile("update", Pattern.CASE_INSENSITIVE);
		Pattern delete_pattern = Pattern.compile("delete", Pattern.CASE_INSENSITIVE);
		Pattern database_pattern = Pattern.compile("database", Pattern.CASE_INSENSITIVE);
	    if(create_pattern.matcher(query).find()) {
	    	System.out.println("create pattern found!");
	    	if (database_pattern.matcher(query).find()) {
	    		CreateDatabase createDatabase = new CreateDatabase();
	    		createDatabase.parseQuery(query);	
	    		
	    	} else {
	    		CreateTable createTable = new CreateTable();
		    	createTable.parseQuery(query);	    		
	    	}
	    } else if (select_pattern.matcher(query).find()) {
	    	System.out.println("select pattern found!");
	    } else if (update_pattern.matcher(query).find()) {
	    	System.out.println("update pattern found!");
	    } else if (delete_pattern.matcher(query).find()) {
	    	System.out.println("delete pattern found!");
	    } else {
	    	System.out.println("invalid query!");
	    }
	    System.out.println("Ended!");
		return null;
	}

}
