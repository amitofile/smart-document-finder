/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.io.File;
import java.io.IOException;

public class Main {

    static int i = 0;
    

    public static void main(String[] args) {
        File currentDir = new File("C:\\Users\\amit\\Downloads"); // current directory
        scanDirRecursive(currentDir);
        System.out.println(i);
    }

    public static void scanDirRecursive(File dir) {
        try {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println("directory:" + file.getCanonicalPath());
                    scanDirRecursive(file);
                } else {
                    String file_name = file.getName();
                    
                    System.out.println("file:" + file.getCanonicalPath());
                    i++;
                }
            }
        } catch (IOException e) {
        }
    }
}
