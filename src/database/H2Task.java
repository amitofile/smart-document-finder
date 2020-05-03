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
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author amit
 */
public class H2Task extends H2jdbc {

    public static ArrayList getFiles(long searchHash, ArrayList<String> fileTypes, String[] searchKeywords) {
        ArrayList<String> result = new ArrayList<>();

        String str = "";
        String andExtention = "";
        String andName = "";

        if (!fileTypes.isEmpty()) {
            andExtention = "AND (";
            str = "";
            for (String extension : fileTypes) {
                andExtention += str + "EXTENTION = '" + extension + "'";
                str = " OR ";
            }
            andExtention += ")";
        }

        if (searchKeywords.length > 0) {
            andName = "AND (";
            andName += "NAME LIKE '%" + String.join("%", searchKeywords) + "%' OR ";
            ArrayUtils.reverse(searchKeywords);
            andName += "NAME LIKE '%" + String.join("%", searchKeywords) + "%' OR ";
            ArrayUtils.reverse(searchKeywords);
            str = "";
            for (String word : searchKeywords) {
                andName += str + "NAME LIKE '%" + word + "%'";
                str = " OR ";
            }
            andName += ")";
        }

        try {
            try (Statement stmt = conn.createStatement()) {
                String sql = "SELECT PATH FROM PUBLIC.FILES WHERE SEARCH_HASH = " + searchHash + " " + andExtention + " " + andName;
                System.out.println(sql);
                stmt.execute(sql);
                ResultSet r = stmt.getResultSet();
                while (r.next()) {
                    result.add(r.getString("PATH"));
                }
                if (DEBUG) {
                    //System.out.println("File search finished");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            return result;
        }
    }

    public static ArrayList getFiles(long searchHash, ArrayList<String> fileTypes) {
        ArrayList<String> result = new ArrayList<>();

        String str = "";
        String andExtention = "";

        if (!fileTypes.isEmpty()) {
            andExtention = "AND (";
            str = "";
            for (String extension : fileTypes) {
                andExtention += str + "EXTENTION = '" + extension + "'";
                str = " OR ";
            }
            andExtention += ")";
        }

        try {
            try (Statement stmt = conn.createStatement()) {
                String sql = "SELECT PATH FROM PUBLIC.FILES WHERE SEARCH_HASH = " + searchHash + " " + andExtention;
                stmt.execute(sql);
                ResultSet r = stmt.getResultSet();
                while (r.next()) {
                    result.add(r.getString("PATH"));
                }
                if (DEBUG) {
                    //System.out.println("File search finished");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            return result;
        }
    }

    public static ArrayList getFile(long fileHash, ArrayList<String> fileTypes) {
        ArrayList<String> result = new ArrayList<>();

        String str = "";
        String andExtention = "";

        if (!fileTypes.isEmpty()) {
            andExtention = "AND (";
            str = "";
            for (String extension : fileTypes) {
                andExtention += str + "EXTENTION = '" + extension + "'";
                str = " OR ";
            }
            andExtention += ")";
        }

        try {
            try (Statement stmt = conn.createStatement()) {
                String sql = "SELECT PATH FROM PUBLIC.FILES WHERE HASH = " + fileHash + " " + andExtention;
                stmt.execute(sql);
                ResultSet r = stmt.getResultSet();
                while (r.next()) {
                    result.add(r.getString("PATH"));
                }
                if (DEBUG) {
                    //System.out.println("File search finished");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            return result;
        }
    }

    public static ArrayList getTags(long fileHash) {
        ArrayList<String> result = new ArrayList<>();
        try {
            try (Statement stmt = conn.createStatement()) {
                String sql = "SELECT TAGS FROM PUBLIC.FILES WHERE HASH = " + fileHash;
                stmt.execute(sql);
                ResultSet r = stmt.getResultSet();
                while (r.next()) {
                    result.add(r.getString("TAGS"));
                }
                if (DEBUG) {
                    //System.out.println("File search finished");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            return result;
        }
    }

    public static int setTag(long fileHash, String tags) {
        int stat = 0;
        try {
            try (Statement stmt = conn.createStatement()) {
                String sql = "UPDATE \"PUBLIC\".FILES SET TAGS = '" + tags + "' WHERE HASH = " + fileHash + ";";
                stat = stmt.executeUpdate(sql);
                if (DEBUG) {
                    //System.out.println("Record updated");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            return stat;
        }
    }
}
