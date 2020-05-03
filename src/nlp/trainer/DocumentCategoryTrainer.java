/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nlp.trainer;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import opennlp.tools.doccat.DoccatFactory;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

/**
 *
 * @author amit
 */
public class DocumentCategoryTrainer {

    final static String MODEL_PATH = "resources\\";
    final static String CHARSET = "UTF-8";

    private ObjectStream objStrm;
    private TrainingParameters params;
    private ObjectStream lineStream;
    private String newModel;

    public DocumentCategoryTrainer(String trainerFile) {
        try {
            InputStreamFactory fileInput = new MarkableFileInputStreamFactory(new File(trainerFile));
            lineStream = new PlainTextByLineStream(fileInput, CHARSET);
            objStrm = new DocumentSampleStream(lineStream);
            params = new TrainingParameters();
            params.put(TrainingParameters.ITERATIONS_PARAM, "10");
            params.put(TrainingParameters.CUTOFF_PARAM, "0");
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(DocumentCategoryTrainer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            //Logger.getLogger(DocumentCategoryTrainer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void startTraining(String language, String documentType) {
        BufferedOutputStream modelOut = null;
        try {
            DoccatModel model = DocumentCategorizerME.train(language, objStrm, params, new DoccatFactory());
            newModel = MODEL_PATH + language + "-" + documentType + ".bin";
            modelOut = new BufferedOutputStream(new FileOutputStream(newModel));
            model.serialize(modelOut);
            System.out.println("Training completed.");
        } catch (IOException ex) {
            Logger.getLogger(DocumentCategoryTrainer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (modelOut != null) {
                    modelOut.close();
                }
                if (lineStream != null) {
                    lineStream.close();
                }
                if (objStrm != null) {
                    objStrm.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(DocumentCategoryTrainer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void testModel(String sentence) {
        try {
            InputStream inputStream = new FileInputStream(newModel);
            DoccatModel _model = new DoccatModel(inputStream);
            DocumentCategorizerME myCategorizer = new DocumentCategorizerME(_model);
            double[] outcomes = myCategorizer.categorize(sentence.replaceAll("[^A-Za-z]", " ").split(" "));
            String category = myCategorizer.getBestCategory(outcomes);

            System.out.println("\n---------------------------------\nCategory : Probability\n---------------------------------");
            for (int i = 0; i < myCategorizer.getNumberOfCategories(); i++) {
                System.out.println(myCategorizer.getCategory(i) + " : " + outcomes[i]);
            }
            System.out.println("---------------------------------");
            System.out.println("Test string is : " + category);
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(DocumentCategoryTrainer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            //Logger.getLogger(DocumentCategoryTrainer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
