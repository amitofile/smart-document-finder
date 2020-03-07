/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.io.StringReader;
import javax.swing.text.BadLocationException;
import reader.FileParser;
import reader.KeywordFinder;

/**
 *
 * @author amit
 */
public class ContentScanner extends Thread {

    private final String filePath;
    private final String[] words;
    private final String data;

    public ContentScanner(String filePath, String[] words) {
        this.filePath = filePath;
        this.words = words;
        FileParser parser = new FileParser(filePath);
        data = parser.getContent();
    }

    String html = "";

    @Override
    public void run() {
        for (String word : words) {
            String temp = new KeywordFinder(data).find(word);
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
