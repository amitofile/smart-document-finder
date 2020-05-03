/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import database.H2Setup;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import reader.FileScanner;

/**
 *
 * @author amit
 */
public class ScanFolders extends Thread {

    public final FileScanner fs;
    public JProgressBar progressBar = null;
    public JLabel label = null;

    public ScanFolders(String dir, JProgressBar progressBar, JLabel label) {
        this.progressBar = progressBar;
        this.label = label;
        fs = new FileScanner(dir, H2Setup.getAllowedFileTypes(), progressBar, label);
    }

    @Override
    public void run() {
        new Monitor(fs, progressBar, label).start();
        fs.start();
    }
}

class Monitor extends Thread {

    FileScanner worker;
    JProgressBar progressBar = null;
    JLabel label = null;

    public Monitor(FileScanner worker, JProgressBar progressBar, JLabel label) {
        this.worker = worker;
        this.progressBar = progressBar;
        this.label = label;
    }

    @Override
    public void run() {
        while (worker.isAlive()) {
        }
        progressBar.setValue(progressBar.getMaximum());
        progressBar.setVisible(false);
        label.setText("Scan completed | Total files: " + worker.totalFiles + " | New files: " + worker.relatedFilesCount);
    }

}
