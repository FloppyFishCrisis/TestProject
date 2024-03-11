/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package troyrenaudgr12pat;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 * The Connect class makes connections to the database and allows queries and
 * updates to run
 */
public class Connect {

    private static Connection conn = null;
    boolean testingMode = true;//if true this will display all running sql commands
    //if false this will hide all sql commands

    /**
     * Creates a new instance of the connect class 
     */
    Connect() {
        try {
            String userName = "root";
            String password = "root";
            String url = "jdbc:mysql://localhost/wyverndb";
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
            if (testingMode) {
                System.out.println("Database connection established");
            }

        } catch (Exception e) {
            System.err.println("Cannot connect to database server" + e);
        }
    }

    /**
     * This method will take in a String "sql" and run that string as if it were SQL code
     * @param sql code to run
     * @return int showing the number of rows that were updated.
     * @throws SQLException
     */
    public int makeChange(String sql) throws SQLException {
        int numRowsUpdated = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            //run query
            numRowsUpdated = stmt.executeUpdate();

            if (testingMode) {
                System.out.println("SQL Code run : " + sql);
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "USERNAME ALREADY IN USE");
        } catch (SQLException e) {
            System.err.println("[DB] - update/delete/insert error - " + e);
            System.out.println("SQL CODE THAT FAILED: " + sql);
        }
        return numRowsUpdated;
    }//update/insert/delete method

    /**
     * This method will take in a String "sql", run that sql code against the database
     * and return the resulting data in a ResultSet
     * @param sql code to run
     * @return ResultSet with the results of the query
     * @throws SQLException
     */
    public ResultSet query(String sql) throws SQLException {
        ResultSet rs = null;
        try {
            // allow free movement of cursor
            PreparedStatement stmt = conn.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery();

            if (testingMode) {
                System.out.println("SQL Code run : " + sql);
            }
        } catch (SQLException e) {
            System.err.println("[DB] - query error - " + e);
            System.out.println("SQL CODE THAT FAILED: " + sql);
        }

        return rs;
    }//query method

    /**
     *
     * @throws SQLException
     */
    void close() throws SQLException {
        this.conn.close();
    }
}
