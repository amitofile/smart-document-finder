/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import static database.H2jdbc.DEBUG;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author amit
 */
public class Initialize extends H2jdbc {

    public int createTable() {
        int stat = 0;
        try {
            try (Statement stmt = conn.createStatement()) {
                String sql = "DROP TABLE IF EXISTS SCEMANTIC; CREATE TABLE SCEMANTIC ("
                        + "id INTEGER NOT NULL,"
                        + "file VARCHAR(50) NOT NULL,"
                        + "path VARCHAR(255) NOT NULL,"
                        + "size LONG NOT NULL,"
                        + "type VARCHAR(10) NOT NULL,"
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
    public int insertRecords(int id, String file, String path, long size, String type) {
        int stat = 0;
        try {
            try (Statement stmt = conn.createStatement()) {
                String sql = "INSERT INTO SCEMANTIC VALUES(" + id + ", '" + file + "', '" + path + "'," + size + ",'" + type.toLowerCase() + "');";
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
