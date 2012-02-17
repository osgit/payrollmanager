package payroll.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	/**
	 * Constructor for new connection
	 */
	public DataAccess(){
		System.out.println("starting connection to " + SERVER + "... ");
		this.init();
	}
	
	/**
	 * Intializes and starts database connection
	 */
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
	
	//popEmployeeTable
	
	/**
	 * Checks if user exists and has the right password
	 * Get the user type
	 * @param String userID
	 * @param String pass
	 * @return String: "admin", "user", or "fail"
	 */
	public String checkUserValidityAndGetType(String userID, String pass){
		String userType = "fail";
		try {
		    stmt = conn.createStatement();
		    rs = stmt.executeQuery("SELECT * FROM passwd WHERE id=" + userID + 
		    		" AND passwd=\"" + pass + "\" ORDER BY passwd.id LIMIT 1"); 
		    if(rs.next()) userType = rs.getString("user_type");
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}		
		return userType;
	}
	
	/**
	 * Closes the database connection
	 */
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
	

	// For testing
	public static void main(String [] args){
		DataAccess test = new DataAccess();
		//System.out.println(test.getUserType("1"));
	}

}
