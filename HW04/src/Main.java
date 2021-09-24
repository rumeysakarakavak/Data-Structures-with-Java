public class Main {

    /***
     *
     * @param args
     */
    public static void main(String[] args) {

        // create an object
        TraverseClockwise object = new TraverseClockwise();
        // initialize a matrix for give to TraverseClockwiseRecursion method as a parameter
        int[][] matrix = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16}
        };

        // call method with arguments matrix, rowindex = 0, columnindex = 0,
        // columnlength = matrix first row Length, rowlength = matrix length
        object.TraverseClockwiseRecursion(matrix,0,0,matrix[0].length-1,matrix.length-1);

    }
}
