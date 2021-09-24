import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static void main(String ... args) throws IOException {
        String filename = args[0];
        Main myMain = new Main();
        NLP read = new NLP();
        myMain.parseInputFile(filename,read);



    }
    public void parseInputFile(String fileName, NLP object) throws IOException {
        Path path = Paths.get(fileName);
        List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
        for (String line : allLines) {
            String[] splited = line.split(" ");
            if(splited[0].equals("bigram")){
                //System.out.println(line);
                System.out.println();
                object.bigrams(splited[1]);
            }
            if(splited[0].equals("tfidf")){
                //System.out.println(line);
                System.out.println();
                object.tfIDF(splited[1],splited[2]);
            }
        }
    }
}