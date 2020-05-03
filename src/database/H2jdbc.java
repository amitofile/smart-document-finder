/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class H2jdbc {

    // JDBC driver name and database URL 
    static final String JDBC_DRIVER = "org.h2.Driver";
    static String DB_URL;

    //  Database credentials 
    static final String USER = "sa";
    static final String PASS = "amit";

    static final boolean DEBUG = true;
    protected static Connection conn = null;

    public static void createConnection() {
        try {
            try (InputStream input = new FileInputStream("config.properties")) {
                Properties prop = new Properties();
                prop.load(input);
                DB_URL = "jdbc:h2:" + prop.getProperty("project_path") + "\\resources\\data";
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            if (DEBUG) {
                System.out.println("Database Connected");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
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
            System.err.println(e.getMessage());
        }
    }
}
