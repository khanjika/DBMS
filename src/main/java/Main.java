import java.util.Scanner;

import erdGenerator.ERDGenerator;

public class Main {
	private static Scanner sc;
    public static void main(String[] args) {
        System.out.println ("*** Welcome to DBMS5408!***");
        System.out.println ("");
        System.out.println ("###########################");

        DatabaseSystem databaseSystem = new DatabaseSystem ();
        sc = new Scanner(System.in);
        String username = databaseSystem.authenticate ();
        if (username != null) {
        	System.out.println("Choose from one of the operations");
        	System.out.println("1. Enter Query");
        	System.out.println("2. SQL Dump");
        	System.out.println("3. Generate ERD");
        	System.out.println("4. Generate data dictionary");
            String userInput = sc.nextLine();
            switch(userInput) {
            case "1":
            	databaseSystem.init ();
            	break;
            case "2":
            	//call data dump method
            	break;
            case "3":
            	ERDGenerator erdObj = new ERDGenerator();
            	System.out.println("Enter database name");
            	String database = sc.nextLine();
            	erdObj.generateERD(username, database);
            	break;
            case "4":
            	//call Generate data dictionary
            	break;
            default:
            	System.out.println("Invalid input!");
            	
            }
            
        }
    }
}
