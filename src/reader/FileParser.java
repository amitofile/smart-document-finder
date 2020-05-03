package reader;

import gui.MainLayout02;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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

    public FileParser(String filePath) {
        handler = new BodyContentHandler();
        metadata = new Metadata();
        context = new ParseContext();

        Parser parser = new AutoDetectParser();
        try (FileInputStream inputstream = new FileInputStream(new File(filePath))) {
            //System.out.println(filePath);
            MainLayout02.setStatus("Scanning file:<br>" + filePath);
            parser.parse(inputstream, handler, metadata, context);
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(FileParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | SAXException | TikaException ex) {
            //Logger.getLogger(FileParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            //Logger.getLogger(FileParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getContent() {
        return handler.toString().replaceAll(" +", " ").replaceAll("[^a-zA-Z0-9\\'\\-]", " ").trim();
    }

    public ArrayList<String> getContentChunks() {
        ArrayList<String> result = new ArrayList<>();
        String s = handler.toString().trim().replaceAll(" +", " ").replaceAll("[^a-zA-Z0-9\\'\\-]", " ");
        int len = s.length(), nchar = 2500;
        for (int i = 0; i < len; i += nchar) {
            result.add(s.substring(i, Math.min(len, i + nchar)).replaceAll("\\s{2,}", " ").replaceAll("\\-{2,}", "").trim());
        }
        return result;
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
