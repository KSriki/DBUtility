package Utility;
import java.util.ArrayList;
import java.util.HashMap;

import javax.print.DocFlavor.STRING;

public class UtilityApp {
	public static void main(String[] args) {
		
		DBUtility db = new DBUtility();
		
		
		ArrayList<HashMap<String, String>> answer = db.select("select * from cities where rownum <= 5");
		System.out.println("-------------------------");
		for(HashMap<String, String> e : answer){
			
			for(String key : e.keySet()){
				System.out.println(key + ": " +e.get(key));
			}
			
			System.out.println("-------------------------");
		}
		
		System.out.println("Rows affected: " + db.update("update cities set city = 'Limbo' where citiesid < 395") + "");
		
		System.out.println(db.getColumnCount());
	}
}
