public class Main {
	public static void main(String[] args) {
		System.out.println("*** Welcome to DBMS5408!***");
		System.out.println("");
		System.out.println("###########################");


		DatabaseSystem databaseSystem = new DatabaseSystem();
		if(databaseSystem.authenticate()){
			databaseSystem.init();
		}
	}
}
