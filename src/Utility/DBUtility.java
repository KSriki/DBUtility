package Utility;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class DBUtility {
	
	private static int cCount = -1;
	private static int rCount = -1;
	public static ArrayList<LinkedHashMap<String, String>> select(String sql) throws IOException{
		
		//should probably strip the sql
		
		//USE PREPARED STATEMENTS
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		//Should parse and white list here
		
		if(!(sql.substring(0,7).toLowerCase().equals("select "))){
			throw new IOException("Unsafe usage of select(String)");
		}
		
		ArrayList<LinkedHashMap<String, String>> temp = new ArrayList<LinkedHashMap<String,String>>();

		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
                        //con = DriverManager.getConnection("jdbc:oracle:thin:sys as sysdba/oracle@localhost:1521:orcl");
            con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			int ct = 0;
			while(rs.next()){
				
				ResultSetMetaData resultSetMetaData = rs.getMetaData();
				int numberofColumns = resultSetMetaData.getColumnCount();
				cCount = numberofColumns;
				
				LinkedHashMap<String, String> record = new LinkedHashMap<String,String>();
				
				for(int i = 1; i <= numberofColumns;i++){
					record.put(resultSetMetaData.getColumnName(i), rs.getString(i));
				}
				ct++;
				temp.add(record);
			}
			rCount = ct;
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	
		return temp;
	
		
		
	}
	
	public static int update(String sql) throws IOException{
		Connection con = null;
		//Should parse and white list here
		Statement stmt = null;
		int rs = -1;
		//Should parse and white list 
		if(!(sql.substring(0,7).toLowerCase().equals("update "))){
			throw new IOException("Unsafe usage of update(String)");
		}
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
            //con = DriverManager.getConnection("jdbc:oracle:thin:sys as sysdba/oracle@localhost:1521:orcl");
            con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
            stmt = con.createStatement();
			rs = stmt.executeUpdate(sql);
         
		            		
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
			
			con.close();
			}catch(SQLException e){
			e.printStackTrace();
			}
		}
	
		return rs;
	}

	public static int insert(String sql) throws IOException{
		Connection con = null;
		//Should parse and white list here
		Statement stmt = null;
		int rs = -1;
		
		if(!(sql.substring(0,7).toLowerCase().equals("insert "))){
			throw new IOException("Unsafe usage of insert(String)");
		}
		
		//Should parse and white list 
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
            //con = DriverManager.getConnection("jdbc:oracle:thin:sys as sysdba/oracle@localhost:1521:orcl");
            con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
            stmt = con.createStatement();
			rs = stmt.executeUpdate(sql);
         
		            		
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
			
			con.close();
			}catch(SQLException e){
			e.printStackTrace();
			}
		}
		//total number of rows after insertion
		return rs;
	}
	
	public static int getColumnCount(){
		return cCount;
	}
	
	public static int getRowCount(){
		return rCount;
	}
	

	public static int delete(String sql) throws IOException{
		Connection con = null;
		//Should parse and white list here
		Statement stmt = null;
		int rs = -1;

		if(!(sql.substring(0,7).toLowerCase().equals("delete "))){
			throw new IOException("Unsafe usage of delete(String)");
		}
		//Should parse and white list 
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
            //con = DriverManager.getConnection("jdbc:oracle:thin:sys as sysdba/oracle@localhost:1521:orcl");
            con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
            stmt = con.createStatement();
			rs = stmt.executeUpdate(sql);
         
		            		
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
			
			con.close();
			}catch(SQLException e){
			e.printStackTrace();
			}
		}
		//total number of rows after deletion
		return rs;
	}
	
}
