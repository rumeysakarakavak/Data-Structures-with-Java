import java.util.Iterator;
import java.util.NoSuchElementException;


public class ListIterator<Experiment> implements Iterator<Experiment>{

    private Node<Experiment> nextNode;
    private Node<Experiment> lastReturnedNode;
    private Node<Experiment> previousNode;
    private int removed = 0;

    public ListIterator(Node<Experiment> node){
        this.nextNode = node;
    }

    public boolean hasNext ()
    {
        return nextNode != null;
    }

    public Experiment next () throws NoSuchElementException
    {
        if (!this.hasNext ())
            throw new NoSuchElementException (
                    "end of the iteration");

        previousNode = lastReturnedNode;
        lastReturnedNode = nextNode;
        nextNode = nextNode.next;

        return lastReturnedNode.data;
    }

    public void remove() throws IllegalStateException
    {
        removed++;
        if (lastReturnedNode == null){
            throw new IllegalStateException (
                    "improper iterator state for remove operation");
        }
        else{
            lastReturnedNode = null;
        }
    }


}