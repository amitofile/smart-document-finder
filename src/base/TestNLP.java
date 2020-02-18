/*
 * http://opennlp.sourceforge.net/models-1.5/
 * https://opennlp.apache.org/models.html
 */
package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.SimpleTokenizer;

/**
 *
 * @author tejora
 */
public class TestNLP {

    final static String SENTENCE = "I am thrilled to welcome the Class of 2017 to our Graduation Party. I can scarcely believe we are graduates – it really is a time for a huge celebration. I love the party venue already, it is outstanding!";

    public static void main(String[] args) {

        try {
            //Loading sentence detector model 
            InputStream inputStream = new FileInputStream("./OpenNLP_models/en-sent.bin");
            SentenceModel model = new SentenceModel(inputStream);

            //Instantiating the SentenceDetectorME class 
            SentenceDetectorME detector = new SentenceDetectorME(model);

            //Detecting the sentence
            String sentences[] = detector.sentDetect(SENTENCE);

            //Instantiating SimpleTokenizer class 
            SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;

            //Printing the sentences 
            for (String sent : sentences) {
                System.out.println(sent);
                String tokens[] = simpleTokenizer.tokenize(sent); 
                for(String token : tokens){
                    System.out.println(token);
                }
                System.err.println("---------------------");
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
