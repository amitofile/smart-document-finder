/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import database.H2Task;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import javax.swing.text.BadLocationException;

/**
 *
 * @author amit
 */
public class FileNameSearch extends Thread {

    long searchHash;
    String words;
    ArrayList<String> fileTypes;

    public FileNameSearch(long searchHash, ArrayList<String> fileTypes, String words) {
        this.searchHash = searchHash;
        this.fileTypes = fileTypes;
        this.words = words;
    }

    String html = "";

    @Override
    public void run() {
        if (!words.isEmpty()) {
            ArrayList<String> paths = H2Task.getFiles(searchHash, fileTypes, words.split(" "));
            MainLayout02.fileNameSearchProgress.setMaximum(MainLayout02.fileNameSearchProgress.getMaximum() + paths.size());
            html = paths.stream().map((path) -> "<a href=\"" + path + "\">" + path + "</a><br>").reduce(html, String::concat);
            try {
                MainLayout02.fileSearchKit.read(new StringReader(html), MainLayout02.fileSearchEdp.getDocument(), MainLayout02.fileSearchEdp.getDocument().getLength());
            } catch (BadLocationException | IOException ex) {
                System.err.println(ex.getMessage());
            }
            MainLayout02.fileNameSearchProgress.setValue(MainLayout02.fileNameSearchProgress.getValue() + paths.size());
        }
    }

}
