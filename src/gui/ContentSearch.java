/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import database.H2Task;
import java.util.ArrayList;

/**
 *
 * @author amit
 */
public class ContentSearch extends Thread {
    
    long searchHash;
    String words;
    ArrayList<String> fileTypes;
    
    public ContentSearch(long searchHash, ArrayList<String> fileTypes, String words) {
        this.searchHash = searchHash;
        this.fileTypes = fileTypes;
        this.words = words;
    }
    
    String html = "";
    
    @Override
    public void run() {
        if (!words.isEmpty()) {
            ArrayList<String> paths = H2Task.getFiles(searchHash, fileTypes);
            MainLayout02.contentSearchProgress.setMaximum(MainLayout02.contentSearchProgress.getMaximum() + paths.size());
            paths.forEach((String path) -> {
                new ContentScanner(path, words.split(" ")).start();
                MainLayout02.contentSearchProgress.setValue(MainLayout02.contentSearchProgress.getValue() + 1);
                if (MainLayout02.contentSearchProgress.getValue() >= MainLayout02.contentSearchProgress.getMaximum()) {
                    MainLayout02.setStatus("Scan completed.");
                }
            });
        }
    }
    
}
