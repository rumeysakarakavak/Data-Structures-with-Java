import java.util.Stack;

public class RumusStack implements MyStack{

    //region InstanceVariables
    private int maxSize;
    private int[][]stackArray;
    private int topElementIndex;
    //endregion InstanceVariables
    //region Constructors
    public RumusStack() {
        maxSize = 20000;
        topElementIndex = -1;
        stackArray = new int[maxSize][2];
    }
    //endregion Constructors
    //region Methods
    //region push
    public void push(int[] coordinates) {
        int index = ++topElementIndex;
        stackArray[index][0] = coordinates[0];
        stackArray[index][1] = coordinates[1];
    }
    //endregion push
    //region pop
    public int[] pop() {
        int[] ret = new int[2];
        int index = topElementIndex--;
        ret[0] = stackArray[index][0];
        ret[1] = stackArray[index][1];
        return ret;

    }
    //endregion pop
    //region peek
    public int[]  peek() {

        int[] ret = new int[2];
        int index = topElementIndex;
        ret[0] = stackArray[index][0];
        ret[1] = stackArray[index][1];
        return ret;
    }
    //endregion peek
    //region isEmpty
    public boolean isEmpty() {
        if(topElementIndex == -1)
            return true;
        else
            return false;

    }
    //endregion isEmpty

    //endregion Methods

}