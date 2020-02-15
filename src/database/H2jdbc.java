/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class H2jdbc {

    // JDBC driver name and database URL 
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";

    //  Database credentials 
    static final String USER = "sa";
    static final String PASS = "";

    static final boolean DEBUG = true;

    private static Connection conn = null;

    public void createConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            if (DEBUG) {
                System.out.println("Database Connected");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
            if (DEBUG) {
                System.out.println("Connetion closed");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public int createTable() {
        int stat = 0;
        try {
            try (Statement stmt = conn.createStatement()) {
                String sql = "DROP TABLE IF EXISTS SCEMANTIC; CREATE TABLE SCEMANTIC ("
                        + "id INTEGER NOT NULL,"
                        + "file VARCHAR(50) NOT NULL,"
                        + "path VARCHAR(255) NOT NULL,"
                        + "PRIMARY KEY(id));";
                stat = stmt.executeUpdate(sql);
                if (DEBUG) {
                    System.out.println("Table re-created");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return stat;
    }

//    public int dropTable() {
//        int stat = 0;
//        try {
//            try (Statement stmt = conn.createStatement()) {
//                String sql = "DROP TABLE SCEMANTIC";
//                stat = stmt.executeUpdate(sql);
//                if (DEBUG)System.out.println("Table dropped");
//            }
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        }
//        return stat;
//    }
    public int insertRecords(int id, String file, String path) {
        int stat = 0;
        try {
            try (Statement stmt = conn.createStatement()) {
                String sql = "INSERT INTO SCEMANTIC VALUES(" + id + ", '" + file + "', '" + path + "');";
                stat = stmt.executeUpdate(sql);
                if (DEBUG) {
                    System.out.println("Record inserted");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return stat;
    }
}
