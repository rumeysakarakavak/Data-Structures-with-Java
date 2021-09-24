import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        MyGraph directedGraph = new MyGraph(fileName);
        System.out.println();
        System.out.print("Popular vertex count is : ");
        System.out.println(directedGraph.findMostPopular());

    }
}
