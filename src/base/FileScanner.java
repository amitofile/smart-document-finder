/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author amit
 */
public class FileScanner {

    private ArrayList<String> fileTypes = new ArrayList<>(Arrays.asList("txt", "pdf", "doc", "docx"));
    private int totalScannedFiles = 0;
    private int relatedScannedFiles = 0;
    private HashMap<String, Integer> fileCount = new HashMap<>();
    private int totalFiles;

    public FileScanner(String dirPath) {
        fileTypes.forEach((String _item) -> {
            fileCount.put(_item, 0);
        });
        scanDirRecursive(new File(dirPath));
    }

    public FileScanner(String dirPath, ArrayList<String> fileTypes) {
        this.fileTypes = fileTypes;
        fileTypes.forEach((String _item) -> {
            fileCount.put(_item, 0);
        });
        scanDirRecursive(new File(dirPath));
    }

    public int getTotalFiles() {
        return totalFiles;
    }

    private void setTotalFiles(int totalFiles) {
        this.totalFiles = totalFiles;
    }
    
    

    private void scanDirRecursive(File dir) {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                scanDirRecursive(file);
            } else {
                totalScannedFiles++;
                try {
                    String file_name = file.getName();
                    String type = file_name.substring(file_name.lastIndexOf('.') + 1).toLowerCase();
                    if (fileTypes.contains(type)) {
                        relatedScannedFiles++;
                        String path = file.getCanonicalPath();
                        //System.out.println(path);
                        fileCount.put(type, fileCount.get(type) + 1);
                    }
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
    
    

    public ArrayList getInfo() {
        ArrayList temp = new ArrayList();
        temp.add(totalFiles);
       // temp.add(relatedFiles);
        temp.add(fileCount);
        return temp;
    }
}
