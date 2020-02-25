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

/**
 *
 * @author amit
 */
public class SetupGUI extends H2jdbc {
    
    public String[] loadFileTypes() {
        String[] result = {};
        try {
            try (Statement stmt = conn.createStatement()) {
                String sql = "SELECT VAL FROM \"PUBLIC\".CONFIG WHERE KEY = 'file_types';";
                stmt.execute(sql);
                ResultSet r = stmt.getResultSet();
                while (r.next()) {
                    result = r.getString("VAL").split(",");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            return result;
        }
    }
}
