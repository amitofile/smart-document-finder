package reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;

import org.xml.sax.SAXException;

public class FileParser {

    private BodyContentHandler handler;
    private final Metadata metadata;
    private final ParseContext context;

    public FileParser() {
        handler = new BodyContentHandler();
        metadata = new Metadata();
        context = new ParseContext();
    }

    public void parse(String filePath) {
        FileInputStream inputstream = null;
        try {
            File file = new File(filePath);
            Parser parser = new AutoDetectParser();
            inputstream = new FileInputStream(file);
            parser.parse(inputstream, handler, metadata, context);
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(FileParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | SAXException | TikaException ex) {
            //Logger.getLogger(FileParser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (inputstream != null) {
                    inputstream.close();
                }
            } catch (IOException ex) {
                //Logger.getLogger(FileParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String getContent() {
        String temp = handler.toString();
        //temp = temp.replaceAll("[\\n\\t]", " ");
        temp = temp.replaceAll("[^a-zA-Z0-9 ]", " ");
        return temp;
    }

    public HashMap<String, String> getMetadata() {
        HashMap<String, String> temp = new HashMap<>();
        String[] metadataNames = metadata.names();
        for (String name : metadataNames) {
            temp.put(name, metadata.get(name));
        }
        return temp;
    }

    public void clean() {
        handler = null;
    }
}
