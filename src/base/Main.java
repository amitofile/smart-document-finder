/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<String> resultPDF = new ArrayList<>();
    static List<String> resultPPT = new ArrayList<>();
    static List<String> resultDOC = new ArrayList<>();
    static List<String> resultTXT = new ArrayList<>();

    public static void main(String[] args) {
        File currentDir = new File("C:\\Users\\amit\\Downloads"); // current directory
        scanDirRecursive(currentDir);

        resultPDF.forEach((string) -> {
            System.out.println(string);
        });
        System.out.println("");
        resultPPT.forEach(System.out::println);
        System.out.println("");
        resultDOC.forEach(System.out::println);
        System.out.println("");
        resultTXT.forEach(System.out::println);

    }

    public static void scanDirRecursive(File dir) {
        try {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    //System.out.println("directory:" + file.getCanonicalPath());
                    scanDirRecursive(file);
                } else {
                    String file_name = file.getName();

                    switch (file_name.substring(file_name.lastIndexOf('.') + 1)) {
                        case "pdf":
                            resultPDF.add(file.getCanonicalPath());
                            break;
                        case "ppt":
                        case "pptx":
                            resultPPT.add(file.getCanonicalPath());
                            break;
                        case "doc":
                        case "docx":
                            resultDOC.add(file.getCanonicalPath());
                            break;
                        case "txt":
                            resultTXT.add(file.getCanonicalPath());
                            break;
                    }
                }
            }
        } catch (IOException e) {
        }
    }
}
