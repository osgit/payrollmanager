package payroll.controller;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import payroll.model.elements.Employee;

/**
 * @author scott julian
 */
public class DataAccess {
	//public static final String SERVER = "192.168.1.4";
	public static final String SERVER = "70.112.250.60";
	public static final String LOGIN = "bigboss";
	public static final String PASSWD = "payrollmanager";
	public static final String DB = "super_awesome_company";
	
	private Connection conn = null;
	private Statement  stmt = null;
	private ResultSet    rs = null;
	
	
	public DataAccess(){
		this.init();
	}
	
	// Initialize new connection
	private void init(){
		try{
			conn = DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DB + "?" + 
		    								   "user=" + LOGIN + "&" + "password=" + PASSWD );
		}catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	public void close(){
		if (rs != null) {
			try { rs.close(); } 
			catch (SQLException sqlEx) { } // ignore
			rs = null;
		}
		if (stmt != null) {
			try { stmt.close(); } catch (SQLException sqlEx) { } // ignore
			stmt = null;
		}
		try { conn.close(); } 
		catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	} 
	
	/**
	 * authenticate user by ID and PASSWORD
	 * @param String userID
	 * @param String userPass
	 * @return String: "admin", "user", or "fail"
	 */
	public String getUserType(String userID, String pass){
		try {
		    stmt = conn.createStatement();
		    rs = stmt.executeQuery("SELECT * FROM passwd JOIN employee_details ON passwd.id=employee_details.id WHERE passwd.id=" + userID + " AND passwd=\"" + pass + "\" ORDER BY passwd.id"); 
		    if(rs.next()){
		    	String userLevel = rs.getString("level");
		    	if(userLevel.equals("manager"))
		    		return "admin";
		    	else
		    		return "user";
		    }
		   return "fail";
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		return "fail";
	}
	
	// For testing
	public static void main(String [] args){
		DataAccess test = new DataAccess();
		System.out.println(test.getUserType("1", "password"));
	}
	
}
