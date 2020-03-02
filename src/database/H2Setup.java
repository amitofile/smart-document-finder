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

/**
 *
 * @author amit
 */
public class H2Setup extends H2jdbc {

    public static ArrayList getAllowedFileTypes() {
        ArrayList<String> result = new ArrayList<>();
        try {
            try (Statement stmt = conn.createStatement()) {
                String sql = "SELECT EXTENSION FROM \"PUBLIC\".FILE_TYPES WHERE STATUS = 1";
                stmt.execute(sql);
                ResultSet r = stmt.getResultSet();
                while (r.next()) {
                    result.add(r.getString("EXTENSION"));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            return result;
        }
    }
}
