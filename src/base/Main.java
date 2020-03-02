/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import database.H2Setup;

public class Main {

    public static void main(String[] args) {
        H2Setup s = new H2Setup();
        s.createConnection();
        System.err.println(s.getAllowedFileTypes());
        
        H2Setup ss = new H2Setup();
        System.err.println(ss.getAllowedFileTypes());
        ss.createConnection();
        s.closeConnection();
        ss.closeConnection();
    }

}
