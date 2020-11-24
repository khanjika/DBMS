package sql.queries;

import java.util.ArrayList;
import java.util.Arrays;

public class CreateTable {

	public void parseQuery(String query) {
		query = query.replaceAll(",", " ");
		query = query.replaceAll("[^a-zA-Z ]", "");
		String[] sqlWords = query.split(" ");
		System.out.println(Arrays.toString(sqlWords));	
		System.out.println(sqlWords.length);	
	} 
	
	public ArrayList<String> getTableNames() {
		
		return null;
		
	}
}
