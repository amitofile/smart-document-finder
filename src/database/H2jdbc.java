/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2jdbc {

    // JDBC driver name and database URL 
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:/home/tejora/app/mit/smart-document-finder/resources/data";
    //static final String DB_URL = "jdbc:h2:~/Documents/NetBeansProjects/smart-document-finder/resources/data";

    //  Database credentials 
    static final String USER = "sa";
    static final String PASS = "amit";

    static final boolean DEBUG = true;
    protected static Connection conn = null;

    public static void createConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            if (DEBUG) {
                System.out.println("Database Connected");
            }
        } catch (ClassNotFoundException | SQLException e) {
            //System.err.println(e.getMessage());
        }
    }

    public static void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
                if (DEBUG) {
                    System.out.println("Connetion closed");
                }
            }
        } catch (SQLException e) {
            //System.err.println(e.getMessage());
        }
    }
}
