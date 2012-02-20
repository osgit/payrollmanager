package payroll.controller;

import java.io.File;
import java.sql.*;
import payroll.model.PayrollException;

/**
 * @author scott julian */
public class DataAccess {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    /**
     * Constructor for new connection
     */
    public DataAccess() throws PayrollException {
        try {
            File dbpath = new File(DataAccess.class.getResource("").toURI());
            conn = DriverManager.getConnection("jdbc:derby:"
                    + dbpath.getCanonicalPath()
                    + System.getProperty("file.separator") + "payroll_db");
        }catch (Exception e) {
            throw new PayrollException(e);
        }
    }

    /**
     * Checks if user exists and has the right password
     * Get the user type
     *
     * @param String userID
     * @param String pass
     * @return String: "admin", "user", or "fail"
     */
    public String checkUserValidityAndGetType(String userID, String pass) {
        String userType = "fail";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM passwd WHERE \"id\"='" + userID
                    + "' AND passwd='" + pass + "'");
            if (rs.next()) {
                userType = rs.getString("user_type");
            }
        }catch (SQLException ex) {
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
            while (rs.next()) {
                count++; // row count
            }
            m_TableData = new Object[count][numHeaders];
            rs.first(); // move to first row
            for (int i = 0; i < count; i++) {
                // need to change when we actually have a table that holds hours
                m_TableData[i][0] = rs.getString("id");
                m_TableData[i][1] = rs.getString("last_name");
                m_TableData[i][2] = rs.getString("first_name");
                m_TableData[i][3] = new Float(7);
                m_TableData[i][4] = new Float(0);
                m_TableData[i][5] = new Float(666);
                if (!rs.next()) {
                    break;
                }
            }
        }catch (SQLException ex) {
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
    public void close() {
        if (rs != null) {
            try {
                rs.close();
            }catch (SQLException sqlEx) {
            } // ignore
            rs = null;
        }
        if (stmt != null) {
            try {
                stmt.close();
            }catch (SQLException sqlEx) {
            } // ignore
            stmt = null;
        }
        try {
            conn.close();
        }catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
