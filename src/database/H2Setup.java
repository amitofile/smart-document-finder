/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import static database.H2jdbc.conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author amit
 */
public class H2Setup extends H2jdbc {

    public static HashMap getAllowedFileTypesAndGroups() {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        try {
            try (Statement stmt = conn.createStatement()) {
                String sql = "SELECT EXTENSION, GROUP_NAME FROM PUBLIC.FILE_TYPES WHERE STATUS = 1";
                stmt.execute(sql);
                ResultSet r = stmt.getResultSet();
                while (r.next()) {
                    if (map.containsKey(r.getString("GROUP_NAME"))) {
                        ArrayList a = map.get(r.getString("GROUP_NAME"));
                        a.add(r.getString("EXTENSION"));
                        map.put(r.getString("GROUP_NAME"), a);
                    } else {
                        ArrayList a = new ArrayList();
                        a.add(r.getString("EXTENSION"));
                        map.put(r.getString("GROUP_NAME"), a);
                    }
                }
                if (DEBUG) {
                    System.out.println("Allowed file extentions fetched");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            return map;
        }
    }

    public static ArrayList getAllowedFileTypes() {
        ArrayList<String> result = new ArrayList<>();
        try {
            try (Statement stmt = conn.createStatement()) {
                String sql = "SELECT EXTENSION FROM PUBLIC.FILE_TYPES WHERE STATUS = 1";
                stmt.execute(sql);
                ResultSet r = stmt.getResultSet();
                while (r.next()) {
                    result.add(r.getString("EXTENSION"));
                }
                if (DEBUG) {
                    System.out.println("Allowed file extentions fetched");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            return result;
        }
    }

    public static ArrayList getAllowedFileTypes(String group) {
        ArrayList<String> result = new ArrayList<>();
        try {
            try (Statement stmt = conn.createStatement()) {
                String sql = "SELECT EXTENSION FROM PUBLIC.FILE_TYPES WHERE GROUP_NAME = '" + group + "' AND STATUS = 1";
                stmt.execute(sql);
                ResultSet r = stmt.getResultSet();
                while (r.next()) {
                    result.add(r.getString("EXTENSION"));
                }
                if (DEBUG) {
                    System.out.println("Allowed file extentions fetched for " + group);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            return result;
        }
    }

    public static ArrayList getFileTypes() {
        ArrayList<String> result = new ArrayList<>();
        try {
            result.add("All");
            try (Statement stmt = conn.createStatement()) {
                String sql = "SELECT DISTINCT GROUP_NAME FROM PUBLIC.FILE_TYPES WHERE STATUS = 1";
                stmt.execute(sql);
                ResultSet r = stmt.getResultSet();
                while (r.next()) {
                    result.add(r.getString("GROUP_NAME"));
                }
                if (DEBUG) {
                    System.out.println("File types fetched");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            return result;
        }
    }

    public static ArrayList getTags() {
        ArrayList<String> result = new ArrayList<>();
        try {
            try (Statement stmt = conn.createStatement()) {
                String sql = "SELECT DISTINCT TAG FROM \"PUBLIC\".TAGS WHERE STATUS = 1";
                stmt.execute(sql);
                ResultSet r = stmt.getResultSet();
                while (r.next()) {
                    result.add(r.getString("TAG"));
                }
                if (DEBUG) {
                    System.out.println("Tags fetched");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            return result;
        }
    }

    public static ArrayList getSubTags(String tag) {
        ArrayList<String> result = new ArrayList<>();
        try {
            try (Statement stmt = conn.createStatement()) {
                String sql = "SELECT SUB_TAG FROM \"PUBLIC\".TAGS WHERE TAG = '" + tag + "' AND STATUS = 1";
                stmt.execute(sql);
                ResultSet r = stmt.getResultSet();
                while (r.next()) {
                    result.add(r.getString("SUB_TAG"));
                }
                if (DEBUG) {
                    System.out.println("Sub tags fetched");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            return result;
        }
    }
}
