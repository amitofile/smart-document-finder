/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import database.H2jdbc;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    H2jdbc h2;
    int i = 1;

    public static void main(String[] args) {
        Main m = new Main();
        m.init();
        m.scanDir();
    }

    public void init() {
        h2 = new H2jdbc();
        h2.createConnection();
        h2.createTable();
        h2.closeConnection();
        i = 1;
    }

    public void scanDir() {
        File currentDir = new File("C:\\Users\\amit\\Downloads");
        h2.createConnection();
        scanDirRecursive(currentDir);
        h2.closeConnection();
    }

    private void scanDirRecursive(File dir) {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                scanDirRecursive(file);
            } else {
                try {
                    h2.insertRecords(i, file.getName(), file.getCanonicalPath());
                    i++;
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
