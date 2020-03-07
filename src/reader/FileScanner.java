/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reader;

import database.H2Prepare;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author amit
 */
public class FileScanner extends Thread {

    private final ArrayList<String> fileTypes;
    private final String dirPath;
    public int totalFiles = 0, relatedFiles = 0;
    public HashMap<String, Integer> relatedFilesCount = new HashMap<>();
    private int progressBarMin = 0, progressBarMax = 100, progressBarVal = 0;
    private JProgressBar progressBar = null;
    private JLabel label = null;

    public FileScanner(String dirPath, ArrayList<String> fileTypes, JProgressBar progressBar, JLabel label) {
        this.dirPath = dirPath;
        this.fileTypes = fileTypes;
        fileTypes.forEach((String _item) -> {
            relatedFilesCount.put(_item, 0);
        });
        this.progressBar = progressBar;
        this.label = label;
    }

    @Override
    public void run() {
        progressBarMin = progressBar.getMinimum();
        progressBarMax = progressBar.getMaximum();
        progressBar.setVisible(true);
        progressBar.setString("scanning folders...");
        scanDirRecursive(new File(dirPath));
    }

    private void scanDirRecursive(File dir) {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                try {
                    label.setText(file.getCanonicalPath());
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
                scanDirRecursive(file);
            } else {
                totalFiles++;
                try {
                    String file_name = file.getName();
                    String file_type = file_name.substring(file_name.lastIndexOf('.') + 1).toLowerCase();
                    if (fileTypes.contains(file_type)) {
                        file_name = file_name.substring(0, file_name.lastIndexOf('.'));
                        String file_path = file.getCanonicalPath();
                        //if (2 < 10 && 1 == H2Prepare.insertRecords(file_name, file_path, file_type, file.length(), file.lastModified(), file_path.hashCode(), 0)) {
                            relatedFiles++;
                            relatedFilesCount.put(file_type, relatedFilesCount.get(file_type) + 1);
                       // }
                        progressBar.setValue(progressBarVal++);
                        if (progressBarVal >= progressBarMax) {
                            progressBarVal = progressBarMin;
                        }
                    }
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
}
