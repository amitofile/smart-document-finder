/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nlp.trainer;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.NameSample;
import opennlp.tools.namefind.NameSampleDataStream;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

/**
 *
 * @author tejora
 */
public class PersonNameTrainer {

    public static void main(String[] args) {
        try {
            ObjectStream<String> lineStream = new PlainTextByLineStream(new MarkableFileInputStreamFactory(new File("en-ner-person.train")), StandardCharsets.UTF_8);

            TokenNameFinderModel model;

            try (ObjectStream<NameSample> sampleStream = new NameSampleDataStream(lineStream)) {
                model = NameFinderME.train("en", "person", sampleStream, TrainingParameters.defaultParams(), null);
                sampleStream.close();
            }

            try (OutputStream modelOut = new BufferedOutputStream(new FileOutputStream(new File("en-ner-person.bin")))) {
                model.serialize(modelOut);
            }
            System.out.println("\tmodel generated");
        } catch (IOException e) {
            //System.err.println(e.getMessage());
        }
    }
}
