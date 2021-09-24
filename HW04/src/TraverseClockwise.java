
public class TraverseClockwise {
    /***
     *
     * @param matrix
     * @param rowIndex
     * @param columnIndex
     * @param columnLength
     * @param rowLength
     */
    public void TraverseClockwiseRecursion(int[][] matrix, int rowIndex, int columnIndex, int columnLength,  int rowLength){

        // start row index to column length and print
        // left to right
        for (int i = rowIndex; i <= columnLength; i++) {
            System.out.print(matrix[rowIndex][i]+ " ");
        }
        // start row index plus 1 to row length and print
        // top to bottom
        for (int i = rowIndex+1; i <= rowLength; i++) {
            System.out.print(matrix[i][columnLength] + " ");
        }

        // right to left
        if(columnIndex+1 <= columnLength){
            for (int i = rowLength-1; i > rowIndex; i--) {
                System.out.print(matrix[i][columnIndex] + " ");
            }
        }
        // bottom to up
        if(rowIndex+1 <= rowLength){
            for (int i = columnLength-1; i >= columnIndex; i--) {
                System.out.print(matrix[rowLength][i] + " ");
            }
        }

        // if matrix traverse not done, get inner matrix
        if(columnIndex+1 <= columnLength-1 && rowIndex+1 <= rowLength-1 ){
            TraverseClockwiseRecursion(matrix, rowIndex+1, columnIndex+1, columnLength-1, rowLength-1);
        }
    }
}