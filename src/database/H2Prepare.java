/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import static database.H2jdbc.DEBUG;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;

/**
 *
 * @author amit
 */
public class H2Prepare extends H2jdbc {

//    public int createTable() {
//        int stat = 0;
//        try {
//            try (Statement stmt = conn.createStatement()) {
//                String sql = "DROP TABLE IF EXISTS SCEMANTIC; CREATE TABLE SCEMANTIC ("
//                        + "id INTEGER NOT NULL,"
//                        + "file VARCHAR(50) NOT NULL,"
//                        + "path VARCHAR(255) NOT NULL,"
//                        + "size LONG NOT NULL,"
//                        + "type VARCHAR(10) NOT NULL,"
//                        + "PRIMARY KEY(id));";
//                stat = stmt.executeUpdate(sql);
//                if (DEBUG) {
//                    System.out.println("Table re-created");
//                }
//            }
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        }
//        return stat;
//    }
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
    public static int insertRecords(String name, String path, String type, long size, long modified, long hash, int tag, long search_hash) {
        int stat = 0;
        try {
            try (Statement stmt = conn.createStatement()) {
                String sql = "INSERT INTO PUBLIC.FILES (NAME, PATH, EXTENTION, SIZE, MODIFIED, HASH, TAGS, SEARCH_HASH) "
                        + "VALUES ('" + name + "', '" + path + "', '" + type + "', " + size + ", " + modified + ", " + hash + ", " + tag + ", " + search_hash + ")";
                stat = stmt.executeUpdate(sql);
                if (DEBUG) {
                    //System.out.println("Record inserted");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return stat;
    }

    public static int insertUserFolder(String dirPath, int status) {
        int stat = 0;
        try {
            try (Statement stmt = conn.createStatement()) {
                String sql = "INSERT INTO PUBLIC.USER_FOLDERS (PATH, STATUS) "
                        + "VALUES ('" + dirPath + "', " + status + " )";
                stat = stmt.executeUpdate(sql);
                if (DEBUG) {
                    System.out.println("New folder inserted");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return stat;
    }

    public static boolean truncateRecords() {
        boolean stat = false;
        try {
            try (Statement stmt = conn.createStatement()) {
                String sql = "TRUNCATE TABLE \"PUBLIC\".FILES";
                stat = stmt.execute(sql);
                if (DEBUG) {
                    System.out.println("All records removed");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return stat;
    }
    
    public static boolean truncateUserFolders() {
        boolean stat = false;
        try {
            try (Statement stmt = conn.createStatement()) {
                String sql = "TRUNCATE TABLE PUBLIC.USER_FOLDERS";
                stat = stmt.execute(sql);
                if (DEBUG) {
                    System.out.println("All user folders removed");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return stat;
    }

    public static ArrayList getFileHashList() {
        ArrayList<Long> result = new ArrayList<>();
        try {
            try (Statement stmt = conn.createStatement()) {
                String sql = "SELECT HASH FROM \"PUBLIC\".FILES";
                stmt.execute(sql);
                ResultSet r = stmt.getResultSet();
                while (r.next()) {
                    result.add(r.getLong("HASH"));
                }
                if (DEBUG) {
                    System.out.println("File hash list fetched");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            return result;
        }
    }

    public static ArrayList getUserFolders() {
        ArrayList<String> result = new ArrayList<>();
        try {
            try (Statement stmt = conn.createStatement()) {
                String sql = "SELECT PATH FROM \"PUBLIC\".USER_FOLDERS WHERE STATUS = 1";
                stmt.execute(sql);
                ResultSet r = stmt.getResultSet();
                while (r.next()) {
                    result.add(r.getString("PATH"));
                }
                if (DEBUG) {
                    System.out.println("User folder list fetched");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            return result;
        }
    }
}
