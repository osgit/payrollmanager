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
	public static final String SERVER = "192.168.1.4";
	//public static final String SERVER = "70.112.250.60";
	public static final String  LOGIN = "bigboss";
	public static final String PASSWD = "payrollmanager";
	public static final String     DB = "super_awesome_company";
	
	private Connection conn = null;
	private Statement  stmt = null;
	private ResultSet    rs = null;
	
	/**
	 * Constructor for new connection
	 */
	public DataAccess(){
		System.out.println("starting connection to " + SERVER + "... ");
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
	
	public Object[][] popEmployeeTable(int numHeaders) {
		/* TODO
		 * implement this function to take in a variable for how to sort it. 
		 */
		Object[][] m_TableData = null;
		try {
		    stmt = conn.createStatement();
		    rs = stmt.executeQuery("SELECT * FROM employees ORDER BY last_name"); 
		    int count = 0;
		    while(rs.next())
		    	count++; // row count
		    m_TableData = new Object[count][numHeaders];
		    rs.first(); // move to first row
		    for(int i = 0; i < count; i++){
		    	// need to change when we actually have a table that holds hours
		    	m_TableData[i][0] = rs.getString("id");
		    	m_TableData[i][1] = rs.getString("last_name");
		    	m_TableData[i][2] = rs.getString("first_name");
		    	m_TableData[i][3] = new Float(7);
		    	m_TableData[i][4] = new Float(0);
		    	m_TableData[i][5] = new Float(666);
		    	if(!rs.next()) break;
		    }
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		return m_TableData;
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
	
	/**
	 * Populates the given table with the employee info fram the Database.
	 * Creates a new Object[][] with the number of rows from the Database results,
	 * and with the number of columns from the number of headers.
	 * 
	 * @param int numHeaders
	 * @return Object[][]
	 */

	// For testing
	public static void main(String [] args){
		DataAccess test = new DataAccess();
		//System.out.println(test.getUserType("1"));
	}

}
