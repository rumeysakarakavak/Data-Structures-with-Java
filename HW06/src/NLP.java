import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class NLP
{
    static String [][] biGramTable = new String[10000000][];
    static int  indexCount = 0;
    float allTerms = 0;
    public Word_Map wmap;

    /*You should not use File_Map class in this file since only word hash map is aware of it.
    In fact, you can define the File_Map class as a nested class in Word_Map,
     but for easy evaluation we defined it separately.
     If you need to access the File_Map instances, write wrapper methods in Word_Map class.
    * */

    /*Reads the dataset from the given dir and created a word map */
    public void readDataset(String dir)
    {
        File folder = new File(dir);
        ListFiles listFiles = new ListFiles();
        listFiles.listAllFiles(dir);
    }
    public class ListFiles {

        public void listAllFiles(String path){

            try(Stream<Path> paths = Files.walk(Paths.get(path))) {
                paths.forEach(filePath -> {
                    if (Files.isRegularFile(filePath)) {
                        try {
                            readContent(filePath);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        public void readContent(Path filePath) throws IOException{
            //System.out.println("read file " + filePath);
            List<String> fileList = Files.readAllLines(filePath);
            String [][] table = new String[1000000][2];

            String keyString = "";
            String printString = "";
            int count = 0;
            for (String item : fileList) {
                item = item.trim().replaceAll("\t", "");
                item = item.trim().replaceAll("\"", "");
                item = item.trim().replaceAll("--", "");
                item = item.trim().replaceAll("-", "");
                String[] splited = item.split(" ");
                for (String itemIn : splited) {
                    if(itemIn.length()>0){
                        if (itemIn.indexOf(',') == itemIn.length() -1 || itemIn.indexOf('.') == itemIn.length() -1){
                            printString = itemIn.substring(0,itemIn.length() -1);
                        }
                        else{
                            printString = itemIn;
                        }
                        //System.out.println("[" + count + "]" + printString +  "     " + indexCount);
                        ++count;
                    }

                    if (count > 1 && count < item.length() * item.length()){
                        //table[count - 1][0] = new ArrayList(); // add another ArrayList object to [0,0]
                        table[count - 1][0]= keyString;
                        table[count - 1][1]= printString;
                        keyString = printString;
                        biGramTable[indexCount] = new String[2];
                        biGramTable[indexCount][0] = table[count - 1][0];
                        biGramTable[indexCount][1] = table[count - 1][1];
                        ++indexCount;
                        //System.out.println(table[count - 1][0] + " " + table[count - 1][1] );
                    }


                }

               // biGramTable[indexCount] = new String[2];
                /*
                ++indexCount;*/
            }


        }

    }
    public void walkAllFiles(Path filePath, String word) throws IOException {

        List<String> fileList = Files.readAllLines(filePath);
        String printString = "";
        int count = 0;
        for (String item : fileList) {
            item = item.trim().replaceAll("\t", "");
            item = item.trim().replaceAll("\"", "");
            item = item.trim().replaceAll("--", "");
            item = item.trim().replaceAll("-", "");
            String[] splited = item.split(" ");
            for (String itemIn : splited) {
                if (itemIn.length() > 0) {
                    if (itemIn.indexOf(',') == itemIn.length() - 1 || itemIn.indexOf('.') == itemIn.length() - 1) {
                        printString = itemIn.substring(0, itemIn.length() - 1);
                    } else {
                        printString = itemIn;
                    }
                }
                if (printString.equals(word)){
                    ++allTerms;
                }
            }
        }
    }

    /*Finds all the bigrams starting with the given word*/
    public List<String> bigrams(String word){
        HashSet<String > printList = new HashSet<String>();
        NLP read = new NLP();
        int flag = 0;
        read.readDataset("dataset");
        for (int i = 0; i < indexCount; ++i) {
            //System.out.println(biGramTable[i][0] + " " + biGramTable[i][1] );
            if (biGramTable[i][0].equals(word)){
                //System.out.println(biGramTable[i][0] + "->" + biGramTable[i][1] );
                 printList.add(biGramTable[i][0] + " " + biGramTable[i][1]);

            }
        }
        System.out.println(printList);
        List <String> animalList = new ArrayList <String> (printList);
        return animalList;
    }


    /*Calculates the tfIDF value of the given word for the given file */
    public float tfIDF(String word, String fileName) throws IOException {
        float value = TF(word, "dataset/" +fileName)*IDF(word,"dataset");
        System.out.println(value);
        return value;
    }

    public float TF(String word, String fileName) throws IOException {

        Path path = Paths.get(fileName);
        float count = 0;
        float countGeneral = 0;
        byte[] bytes = Files.readAllBytes(path);
        List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);

        for (String line : allLines) {
            line = line.trim().replaceAll("\t", "");
            line = line.trim().replaceAll("\"", "");
            line = line.trim().replaceAll("--", "");
            line = line.trim().replaceAll("-", "");
            String[] splited = line.split(" ");
            for (String itemIn : splited) {
                if (itemIn.length()>0){
                    if(itemIn.indexOf('.') == itemIn.length() -1 || itemIn.indexOf(',') == itemIn.length() -1){
                        itemIn = itemIn.substring(0, (itemIn.length() -1));
                    }
                }
                ++countGeneral;
                //System.out.println(itemIn);
                if (itemIn.equals(word)){
                    //System.out.println(itemIn);
                    ++count;
                }
            }
        }

        return (count/countGeneral);
    }
    public float IDF(String word, String path){

      float fileCount = 0;
        try(Stream<Path> paths = Files.walk(Paths.get(path))) {
            fileCount = (int) paths.filter(filePath -> Files.isRegularFile(filePath)).count();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try(Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    try {
                        walkAllFiles(filePath,word);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {

            e.printStackTrace();
        }

        return fileCount/allTerms;
    }

    /*Print the WordMap by using its iterator*/
    public  void printWordMap()
    {
        NLP read = new NLP();
        read.readDataset("dataset");
        for (int i = 0; i < indexCount; ++i) {
            System.out.println(biGramTable[i][0] + " " + biGramTable[i][1] );
        }
    }

}
