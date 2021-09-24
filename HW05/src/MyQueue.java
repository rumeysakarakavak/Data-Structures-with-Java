import java.util.NoSuchElementException;

public class MyQueue<T> {


        private MyNode<T> firstElement;
        private MyNode<T> lastElement;
        private int size;

    /***
     * node class
     * @param <T>
     */
    private static class MyNode<T> {
            private T value;
            private MyNode<T> next;

            public MyNode(T val) {
                this.value = val;
                this.next = null;
            }

            public void setNext(MyNode<T> nextNode) {
                next = nextNode;
            }

            public MyNode<T> getNext() {
                return next;
            }

            public T getValue() {
                return value;
            }

            @Override
            public String toString() {
                if(value == null) {
                    return "null";
                }
                return value.toString();
            }

        }

    /*MyQueue Functions*/

    /**
     * add elements at the tail of queue
     * @param value new node
     */
    public void add(T value) {
            MyNode<T> current = new MyNode(value);
            if(size == 0) {
                current.setNext(firstElement);
                firstElement = current;
                lastElement = current;
                size++;
                return;
            }

            lastElement.setNext(current);
            lastElement = current;
            size++;
        }

    /**
     * View the head of queue without removing it. It returns Null if the queue is empty.
     * @return firstElement
     */
    public T peek() {
            if(!isEmpty()) {
                return firstElement.getValue();
            }
            else {
                return null;
            }
        }

    /**
     * View the head of queue without removing it.It throws NoSuchElementException when the queue is empty.
     * @return firstElement
     */
    public T element() {
            if(!isEmpty()) {
                return firstElement.getValue();
            }
            else {
                throw new NoSuchElementException();
            }
        }

    /***
     * It throws NoSuchElementException when the queue is impty.
     * @return  the head of the queue
     */
         public MyNode<T> remove() {
            if(isEmpty()) {
                throw new NoSuchElementException();
            }

            MyNode<T> cur = firstElement;
            firstElement = firstElement.getNext();
            size--;
            return cur;
        }

    /***
     * It returns null if the queue is empty.
     * @return  the head of the queue
     */
    public MyNode<T> poll() {
            if(isEmpty()) {
                return null;
            }

            MyNode<T> cur = firstElement;
            firstElement = firstElement.getNext();
            size--;
            return cur;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void clear() {
            size = 0;
            firstElement = null;
            lastElement = null;
        }

        public int size() {
            return size;
        }

        @Override
        public String toString() {
            if(size == 0) {
                return "[]";
            }

            StringBuilder sb = new StringBuilder();
            MyNode<T> cur = firstElement;
            sb.append(cur.getValue() + "<-");

            while(cur.getNext() != null && !cur.equals(lastElement)) {
                sb.append(cur.getNext().getValue() + "<-");
                cur = cur.getNext();
            }

            return sb.append("null").toString();
        }


}
