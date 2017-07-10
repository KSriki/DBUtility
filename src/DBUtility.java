import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DBUtility {
	
	private int cCount = 0;
	private int rCount = 0;
	public ArrayList<HashMap<String, String>> select(String sql){
		
		//should probably strip the sql
		
		//USE PREPARED STATEMENTS
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		//Should parse and white list here
		ArrayList<HashMap<String, String>> temp = new ArrayList<HashMap<String,String>>();

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
				this.cCount = numberofColumns;
				
				HashMap<String, String> record = new HashMap<String,String>();
				
				for(int i = 1; i <= numberofColumns;i++){
					record.put(resultSetMetaData.getColumnName(i), rs.getString(i));
				}
				ct++;
				temp.add(record);
			}
			this.rCount = ct;
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
	
	public int update(String sql){
		Connection con = null;
		//Should parse and white list here
		Statement stmt = null;
		int rs = -1;
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
	
		return rs;
	}
	
	
	
}
