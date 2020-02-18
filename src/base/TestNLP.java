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

    final static String SENTENCE = "During the next 24 hours, isolated to scattered light rain and snow with one or two moderate spells may occur in Jammu and Kashmir, Ladakh, Himachal Pradesh and Arunachal Pradesh. Isolated rain and snow may occur in Sikkim. Scattered rain and thundershowers are possible in Assam. Isolated showers are likely in Nagaland. Delhi pollution will increase further. Minimums are expected to increase in the nothern plains including Delhi and NCR. Any information taken from here should be credited to Skymet Weather.";

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
            InputStream inputStreamNameFinder = new FileInputStream("./OpenNLP_models/en-ner-location.bin");
            TokenNameFinderModel person_model = new TokenNameFinderModel(inputStreamNameFinder);

            //Instantiating the NameFinderME class 
            NameFinderME nameFinder = new NameFinderME(person_model);

            //Printing the sentences 
            for (String sentence : sentences) {
                //System.out.println(sentence);

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
