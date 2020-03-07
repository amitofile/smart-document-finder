/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import reader.FileParser;
import reader.KeywordFinder;

public class Main {

    public static void main(String[] args) {
        //FileParser parser = new FileParser();
        //parser.parse("C:\\Users\\amit\\Downloads\\ATIPP4539J_PARTB_2019-20.pdf");
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss:SSS");
        String s = "";//parser.getContent();

        //System.err.println(dtf.format(LocalDateTime.now()));
        String[] words = {"surcharge", "amit"};
        String result[] = new String[words.length];

        for (int i = 0; i < words.length; i++) {
            Finder f = new Finder(words[i], s);
            f.start();
               result[i] = f.getResult();
        }

        for (String data : result) {
            System.out.println(data);
        }
    }

}

class Finder extends Thread {

    private final String word, string;
    private String result;

    public Finder(String word, String string) {
        System.out.println("Searching "+ word);
        this.word = word;
        this.string = string;
    }

    @Override
    public void run() {
        result = new KeywordFinder(string).find(word);
    }

    public String getResult() {
        return result;
    }
}
