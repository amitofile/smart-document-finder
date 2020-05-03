/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import database.H2Task;
import java.util.ArrayList;
import reader.FileParser;

/**
 *
 * @author amit
 */
public class ContentParse extends Thread {

    long searchHash;
    String searchString;
    ArrayList<String> fileTypes;

    public ContentParse(long searchHash, ArrayList<String> fileTypes, String searchString) {
        this.searchHash = searchHash;
        this.fileTypes = fileTypes;
        this.searchString = searchString;
    }

    String html = "";

    @Override
    public void run() {
        ArrayList<String> paths = H2Task.getFiles(searchHash, fileTypes);
        MainLayout02.contentSearchProgress.setMaximum(MainLayout02.contentSearchProgress.getMaximum() + paths.size());
        paths.forEach((String path) -> {
            if (MainLayout02.contentSearchChk.isSelected() || MainLayout02.tagSearchChk.isSelected()) {
                String data = new FileParser(path).getContent();
                if (MainLayout02.contentSearchChk.isSelected()) {
                    new ContentSearch(path, searchString.split(" "), data).start();
                    MainLayout02.contentSearchProgress.setValue(MainLayout02.contentSearchProgress.getValue() + 1);
                    if (MainLayout02.contentSearchProgress.getValue() >= MainLayout02.contentSearchProgress.getMaximum()) {
                        MainLayout02.setStatus("Scan completed.");
                    }
                }
                if (MainLayout02.tagSearchChk.isSelected()) {

                }
            }
        });
    }
}
