public class IntegerStack {
    //region InstanceVariables
    private int maxSize;
    private float[] stackArray;
    private int topElementIndex;
    //endregion InstanceVariables

    //region Constructors
    public IntegerStack() {
        maxSize = 100;
        topElementIndex = 0;
        stackArray = new float[maxSize];
    }

    //endregion Constructors

    //region Methods
    //region push
    public void push(float coordinates) {

        stackArray[++topElementIndex] = coordinates;
    }
    //endregion push
    //region pop
    public float pop() {

        return stackArray[topElementIndex--];
    }
    //endregion pop
    //region peek
    public float peek() {

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
