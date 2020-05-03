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
    String dir, searchString;
    ArrayList<String> fileTypes;

    public FileNameSearch(String dir, ArrayList<String> fileTypes, String words) {
        this.dir = dir;
        searchHash = dir.hashCode();
        this.fileTypes = fileTypes;
        this.searchString = words;
    }

    String html = "";

    @Override
    public void run() {
        if (!searchString.isEmpty()) {
            ArrayList<String> paths = H2Task.getFiles(searchHash, fileTypes, searchString.split(" "));
            MainLayout02.fileNameSearchProgress.setMaximum(MainLayout02.fileNameSearchProgress.getMaximum() + paths.size());
            html = paths.stream().map((path) -> "<a href=\"" + path + "\">" + path + "</a><br>").reduce(html, String::concat);
            if (!html.isEmpty()) {
                html = "<a href=\"" + dir + "\"><b>" + dir + "</b></a><br>" + html;
            }
            try {
                MainLayout02.fileSearchKit.read(new StringReader(html), MainLayout02.fileSearchEdp.getDocument(), MainLayout02.fileSearchEdp.getDocument().getLength());
            } catch (BadLocationException | IOException ex) {
                System.err.println(ex.getMessage());
            }
            MainLayout02.fileNameSearchProgress.setValue(MainLayout02.fileNameSearchProgress.getValue() + paths.size());
        }
    }

}
