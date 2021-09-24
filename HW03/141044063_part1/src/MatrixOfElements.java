import javax.swing.text.Style;
import java.io.*;
import java.util.stream.StreamSupport;

public class MatrixOfElements {

    //region InstanceVariables
    private int maxSize;
    private int[][] arrayOfIntegerss;
    private int width = 0;
    private int length = 0;
    //endregion InstanceVariables
    //region Constructors
    public MatrixOfElements(){
        maxSize = 10000000;
    }
    //endregion Constructors
    //region SettersAndGetters

    public void setArrayOfIntegers(int[][] arrayOfIntegers) {
        this.arrayOfIntegerss = arrayOfIntegers;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public int getWidth() {
        return width;
    }

    public int[][] getArrayOfIntegers() {
        return arrayOfIntegerss;
    }
    //endregion SettersAndGetters
    //region Methods
    //region ReadFromFile
    public void ReadFromFile(String fileName) throws IOException {

        File file = new File(fileName);
        // get width and length
        InputStream in = new FileInputStream(fileName);
        Reader reader = new InputStreamReader(in);
        Reader buffer = new BufferedReader(reader) ;
        int r;
        while ((r = reader.read()) != -1) {
            char ch = (char) r;

            if (ch == '\n') {

                length = 0;
                ++width;
            }
            if(ch!= ' ' && ch != '\n'){
                ++length;
            }
        }
        in.close();
        reader.close();
        buffer.close();
        //Get charactes in array
        int[][] arrayOfIntegers = new int[width+1][length + 1];
        int i = 0;
        int j = 0;
        try (InputStream ino = new FileInputStream(fileName);
             Reader readero = new InputStreamReader(ino);
             Reader buffero = new BufferedReader(readero)) {
            r = 0;
            while ((r = readero.read()) != -1) {
                char ch = (char) r;
                if (ch == '\n') {
                    j = 0;
                    ++i;
                }

                if(ch!= ' ' && ch != '\n'){
                    arrayOfIntegers[i][j] = ch - 48;
                    ++j;
                }
            }
        }

        setArrayOfIntegers(arrayOfIntegers);

    }
    //endregion ReadFromFile
    //region createComponentMatrix
    public void createComponentMatrix(String filename) throws IOException {

        ReadFromFile(filename);

        int rowSize = width;
        int columnSize = length;
        int marker = 'A';
        int[] indexArray =new int[2];
        RumusStack myStack = new RumusStack();
        char[][] componentMatrix = new char[rowSize + 1][columnSize + 1];

        // Look for 1's
        for (int i= 0; i <= rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                if (componentMatrix[i][j] == 0) {
                    if (arrayOfIntegerss[i][j] == 1) {
                        indexArray[0] = i;
                        indexArray[1] = j;
                        myStack.push(indexArray);
                        componentMatrix[i][j] = (char)marker;
                    }
                    //stack is not empty, keep looking neighbors
                    while (!myStack.isEmpty()) {
                        indexArray = myStack.pop();
                        int row = indexArray[0];
                        int column = indexArray[1];
                        //if there is 1 at left of the given index
                        if (column - 1 >= 0) {
                            if (arrayOfIntegerss[row][column - 1] == 1 && componentMatrix[row][column - 1] == 0) {
                                componentMatrix[row][column - 1] = (char)marker;
                                indexArray[0] = row;
                                indexArray[1] = column - 1;
                                myStack.push(indexArray);
                            }
                        }
                        //if there is 1 at right of the given index
                        if (column + 1 <= length) {
                            if (arrayOfIntegerss[row][column + 1] == 1 && componentMatrix[row][column + 1] == 0) {
                                componentMatrix[row][column + 1] = (char)marker;
                                indexArray[0] = row;
                                indexArray[1] = column + 1;
                                myStack.push(indexArray);
                            }
                        }
                        //if there is 1 at bottom of the given index
                        if (row + 1 <= width) {
                            if (arrayOfIntegerss[row + 1][column] == 1 && componentMatrix[row + 1][column] == 0) {
                                componentMatrix[row + 1][column] = (char)marker;
                                indexArray[0] = row + 1;
                                indexArray[1] = column;
                                myStack.push(indexArray);
                            }
                        }
                        //if there is 1 at top of the given index
                        if (row - 1 >= 0) {
                            if (arrayOfIntegerss[row - 1][column] == 1 && componentMatrix[row - 1][column] == 0) {
                                componentMatrix[row - 1][column] = (char)marker;
                                indexArray[0] = row - 1;
                                indexArray[1] = column;
                                myStack.push(indexArray);
                            }
                        }
                        if(myStack.isEmpty()){
                            ++marker;
                        }
                    }

                }
            }

        }
        for (int i= 0; i <= rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                if (componentMatrix[i][j] == '\0'){
                    System.out.print('0');
                }
                else
                    System.out.print(componentMatrix[i][j]);
            }
            System.out.println();
        }
        int components = marker - 'A' ;
        System.out.println("\nNumber of white components : " + components);
        //endregion fillOnesOInStack
        //endregion Methods
    }
    //endregion createComponentMatrix
    //endregion Methods
}
