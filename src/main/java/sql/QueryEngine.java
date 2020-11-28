package sql;

import sql.parser.CreateParser;
import sql.parser.ListParser;
import sql.parser.SelectParser;
import sql.parser.UseParser;
import sql.processor.CreateProcessor;
import sql.processor.ListProcessor;
import sql.processor.SelectProcessor;
import sql.processor.UseProcessor;

public class QueryEngine {
	private String database = null;

	public void run(String query, String username) {
		query = query.toLowerCase();
		InternalQuery internalQuery = null;
		String action = query.replaceAll(" .*", "");
		boolean success = false;
		switch (action){
			case "use":
				internalQuery = UseParser.instance().parse(query);
				UseProcessor useProcessor = UseProcessor.instance();
				useProcessor.process(internalQuery,username);
				database = useProcessor.getDatabase();
				break;
			case "list":
				internalQuery = ListParser.instance().parse(query);
				internalQuery.setOption(database);
				ListProcessor.instance().process(internalQuery,username);
				break;
			case "create":
				internalQuery = CreateParser.instance().parse(query);
				CreateProcessor.instance().process(internalQuery,username);
				break;
			case "insert":
//				internalQuery = InsertParser.instance().parse(query);
//				InsertProcessor.instance().process(internalQuery,username);
				break;
			case "select":
				internalQuery = SelectParser.instance().parse(query);
				SelectProcessor.instance().process(internalQuery,username);
				break;
			case "update":
//				internalQuery = UpdateParser.instance().parse(query);
//				UpdateProcessor.instance().process(internalQuery,username);
				break;
			case "delete":
//				internalQuery = DeleteParser.instance().parse(query);
//				DeleteProcessor.instance().process(internalQuery,username);
				break;
			default:
				System.out.println("invalid query!");
		}
	}
}
