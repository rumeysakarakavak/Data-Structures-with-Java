public class Node<Experiment> {

        // reference to the next node in the chain, or null if there isn't one.
        public Node<Experiment> next;
        // reference to the next node in the chain, or null if there isn't one.
        public Node<Experiment> previous;

        // data carried by this node. could be of any type you need.
        public Experiment data;

        // No parameter constructor
        public Node() {
            next = null;
            data = null;
        }
        // Node constructor
        public Node(Experiment dataValue) {
            next = null;
            data = dataValue;
        }

        // another Node constructor if we want to specify the node to point to.
        @SuppressWarnings("unused")
        public Node(Experiment dataValue, Node<Experiment> nextValue) {
            next = nextValue;
            data = dataValue;
        }

    public void setData(Experiment data) {
        this.data = data;
    }

    public Experiment getData() {
        return data;
    }

    public void setNext(Node<Experiment> next) {
        this.next = next;
    }

    public Node<Experiment> getNext() {
        return next;
    }

    public Node<Experiment> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<Experiment> previous) {
        this.previous = previous;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}

