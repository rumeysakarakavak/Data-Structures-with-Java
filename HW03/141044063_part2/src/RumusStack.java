public class RumusStack implements MyStack{

    //region InstanceVariables
    private int maxSize;
    private String[]stackArray;
    private int topElementIndex;
    //endregion InstanceVariables

    //region Constructors
    public RumusStack() {
        maxSize = 1000;
        topElementIndex = -1;
        stackArray = new String[maxSize];
    }
    //endregion Constructors

    //region Methods
    //region push
    public void push(String coordinates) {

        stackArray[++topElementIndex] = coordinates;
    }
    //endregion push
    //region pop
    public String pop() {

        return stackArray[topElementIndex--];
    }
    //endregion pop
    //region peek
    public String peek() {

        return stackArray[topElementIndex];
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