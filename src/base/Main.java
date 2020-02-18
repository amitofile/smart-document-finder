/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.io.File;

public class Main {

    static final String DIRECTORY = "/home/tejora/Downloads";
    //static final String directory = "C:\\Users\\amit\\Downloads";

    public static void main(String[] args) {
        File currentDir = new File(DIRECTORY);
        scanDirRecursive(currentDir);
    }

    public static void scanDirRecursive(File dir) {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                scanDirRecursive(file);
            } else {
                try {
                    String file_name = file.getName();
                    String type = file_name.substring(file_name.lastIndexOf('.') + 1).toLowerCase();
                    System.out.println(type);
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
}
