package sql;

import sql.parser.CreateParser;
import sql.parser.SelectParser;
import sql.processor.CreateProcessor;
import sql.processor.SelectProcessor;

public class QueryEngine {
	
	public void run(String query, String username) {
		query = query.toLowerCase();
		Query queryObj = null;
		String action = query.replaceAll(" .*", "");
		boolean success = false;
		switch (action){
			case "create":
				queryObj = CreateParser.instance().parse(query);
				CreateProcessor.instance().process(queryObj,username);
				break;
			case "select":
				queryObj = SelectParser.instance().parse(query);
				SelectProcessor.instance().process(queryObj,username);
				break;
			case "update":
//				queryObj = UpdateParser.instance().parse(query);
//				UpdateProcessor.instance().process(queryObj,username);
				break;
			case "delete":
//				queryObj = DeleteParser.instance().parse(query);
//				DeleteProcessor.instance().process(queryObj,username);
				break;
			default:
				System.out.println("invalid query!");
		}
	}

}
