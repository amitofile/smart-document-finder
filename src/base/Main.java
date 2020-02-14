/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author amit
 */
public class Main {

//    public static void main(String[] args) throws IOException {
//        Path start = Paths.get("C:\\\\Users\\\\amit\\\\Downloads\\");
//        try (Stream<Path> stream = Files.walk(start, Integer.MAX_VALUE)) {
//            List<String> collect = stream
//                    .map(String::valueOf)
//                    .sorted()
//                    .collect(Collectors.toList());
//            int i = 0;
//            i = collect.stream().map((String string) -> {
//                System.out.println(string);
//                return string;
//            }).map((_item) -> 1).reduce(i, Integer::sum);
//            System.out.println(i);
//        }
//    }

//    public static void main(String[] args) {
//
//        List<Path> resultPDF = new ArrayList<>();
//        List<Path> resultPPT = new ArrayList<>();
//        List<Path> resultDOC = new ArrayList<>();
//        List<Path> resultTXT = new ArrayList<>();
//
//        File[] directories = new File("C:\\Users\\amit\\Downloads").listFiles(File::isDirectory);
//
//        for (File directory : directories) {
//            Main.getAllFiles(directory.getPath());
//        }
//    }
//
//    private static void getAllFiles(String dir) {
//        Path folder = Paths.get(dir);
//        try (DirectoryStream<Path> stream = Files.newDirectoryStream(folder)) {
//            for (Path entry : stream) {
//                System.out.println(entry);
//            }
//        } catch (IOException ex) {
//        }
//    }
}
