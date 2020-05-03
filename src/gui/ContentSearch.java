/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.io.StringReader;
import javax.swing.text.BadLocationException;
import reader.KeywordFinder;

/**
 *
 * @author amit
 */
public class ContentSearch extends Thread {

    private final String filePath;
    private final String[] words;
    private final String data;

    public ContentSearch(String filePath, String[] words, String data) {
        this.filePath = filePath;
        this.words = words;
        this.data = data;
    }

    String html = "";

    @Override
    public void run() {
        String temp;
        for (String word : words) {
            temp = new KeywordFinder(data).find(word);
            if (temp != null) {
                html = "<p><a href=\"" + filePath + "\">" + filePath + "</a><br><span class=\"chunk\">" + temp + "</span></p>";
                try {
                    MainLayout02.fileSearchKit.read(new StringReader(html), MainLayout02.contentSearchEdp.getDocument(), MainLayout02.contentSearchEdp.getDocument().getLength());
                } catch (BadLocationException | IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
}
