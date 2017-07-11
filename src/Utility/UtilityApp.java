package Utility;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.print.DocFlavor.STRING;

public class UtilityApp {
	public static void main(String[] args) {
		
		
		
		ArrayList<LinkedHashMap<String, String>> answer = new ArrayList<>();
		try {
			answer = DBUtility.select("select * from customers where rownum <= 5");
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		System.out.println("-------------------------");
		
		//PROBLEM with order of columns since keyset of hashmap
		///use linked hashmap
		//can simplify
		for(HashMap<String, String> e : answer){
			
			for(String key : e.keySet()){
				System.out.println(key + ": " +e.get(key));
			}
			
			System.out.println("-------------------------");
		}
		
		try {
			System.out.println("Rows affected: " + DBUtility.update("update cities set city = 'Limbo' where citiesid < 395") + "");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println(DBUtility.getColumnCount());
	}
}
