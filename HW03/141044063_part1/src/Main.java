import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    /**
     * calls all methods in it
     * @param args input file
     * @throws FileNotFoundException
     */
    public static void main(String ... args) throws IOException {

        String filename = args[0];
        MatrixOfElements matrix = new MatrixOfElements();
        matrix.createComponentMatrix(filename);
    }

}

