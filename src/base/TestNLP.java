/*
 * http://opennlp.sourceforge.net/models-1.5/
 * https://opennlp.apache.org/models.html
 */
package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.util.Span;

/**
 *
 * @author tejora
 */
public class TestNLP {

    final static String SENTENCE = "Ronan: This means 'little seal. .... Brayden: It means 'a broad hillside. .... Hugo: The name means 'intelligent. .... Diego: The name means 'the famous bearer' in Spanish. .... Antonio: It signifies someone who is 'beyond praise. .... Marco: The name means 'warring. .... Steffan: .... Devin:";

    public static void main(String[] args) {

        try {
            //Loading sentence detector model 
            InputStream inputStream = new FileInputStream("./OpenNLP_models/en-sent.bin");
            SentenceModel sent_model = new SentenceModel(inputStream);

            //Instantiating the SentenceDetectorME class 
            SentenceDetectorME detector = new SentenceDetectorME(sent_model);

            //Detecting the sentence
            String sentences[] = detector.sentDetect(SENTENCE);

            //Instantiating SimpleTokenizer class 
            SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;

            //Loading the NER-person model 
            InputStream inputStreamNameFinder = new FileInputStream("./OpenNLP_models/en-ner-person.bin");
            TokenNameFinderModel person_model = new TokenNameFinderModel(inputStreamNameFinder);

            //Instantiating the NameFinderME class 
            NameFinderME nameFinder = new NameFinderME(person_model);

            //Printing the sentences 
            for (String sentence : sentences) {
                System.out.println(sentence);

                String tokens[] = simpleTokenizer.tokenize(sentence);
                for (String token : tokens) {
                    //System.out.println(token);
                }

                Span names[] = nameFinder.find(tokens);
                for (Span name : names) {
                    System.out.println(tokens[name.getStart()]);
                }

                System.err.println("---------------------");
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
