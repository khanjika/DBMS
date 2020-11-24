package sql.queries;

import java.io.File;

public class CreateDatabase {
	public void parseQuery(String query) {
		query = query.replaceAll(",", " ");
		query = query.replaceAll("[^a-zA-Z ]", "");
		String[] sqlWords = query.split(" ");	
		createDBdir(sqlWords);
	}
	
	public void createDBdir(String[] queryArr) {
		String path = "/Users/prabhjotkaur/Documents/dbms-5408/src/main/java/dataFiles/";
		path = path+queryArr[2];
		File file = new File(path);
		 System.out.println(path);
		boolean bool = file.mkdir();
		if(bool){
	         System.out.println("DB created successfully");
	      }else{
	         System.out.println("Sorry couldn’t create DB");
	      }
	}
	
}
