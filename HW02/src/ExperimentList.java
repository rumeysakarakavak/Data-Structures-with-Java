import com.sun.xml.internal.bind.v2.TODO;
import java.security.spec.ECField;
import java.util.*;


public class ExperimentList implements Iterable<Experiment> {

    //region Properties
    private Node<Experiment> head;
    private Node<Experiment> tail;
    private Integer size = 0;
    //endregion Properties
    //region Constructors
    public ExperimentList() {
        head = null;
        tail = null;
        size = 0;
    }
    //endregion Constructors
    //region Setters
    public void setSize(Integer size) {
        this.size = size;
    }

    public void setHead(Node<Experiment> head) {
        this.head = head;
    }

    public void setFirst(Node<Experiment> first) {
        this.tail = first;
    }
    //endregion Setters
    //region Getters

    public Integer getSize() {
        return size;
    }

    public Node<Experiment> getHead() {
        return head;
    }

    public Node<Experiment> getFirst() {
        return tail;
    }

    //endregion Getters
    //region Methods

    //region addExp
    public void addExp(Experiment exp) {

        Node<Experiment> node = new Node<>(exp);
        int indexCount = 0;
        int listSize = 0;
        int dayIndex = 0;
        Node<Experiment> current = new Node<>();

        if (head == null){
            tail = head = node;
            size++;
        }


        else {

            Node<Experiment> walkerNode = head;
            for (int i = 0; i < size; i++) {

                if (walkerNode.data.getDay() == exp.getDay() && walkerNode.getNext() != null && walkerNode.getNext().next != null) {
                    ++indexCount;
                    while (listSize != getSize() && walkerNode.getNext() != null && walkerNode.getNext().next != null) {
                        if (walkerNode.getNext().data.getDay() == exp.getDay()) {
                            ++indexCount;
                            if (walkerNode.next != null)
                                walkerNode = walkerNode.getNext();
                        }
                        ++listSize;
                    }
                    indexCount += i;
                    i = size;
                }

                walkerNode = walkerNode.getNext();
            }
            if (indexCount == 0) {

                tail.setNext(node);
                tail = node;
            } else {
                Node crunchifyCurrent = head;
                if (head != null) {
                    for (int i = 0; i < indexCount - 1; i++) {
                        if (crunchifyCurrent.getNext() != null)
                            crunchifyCurrent = crunchifyCurrent.getNext();
                    }
                    if (crunchifyCurrent.getNext() != null) {
                        node.setNext(crunchifyCurrent.getNext());
                        //node = crunchifyCurrent;
                        crunchifyCurrent.setNext(node);
                        crunchifyCurrent = node;
                    }

                }

            }
            size++;
        }

        Node<Experiment> currentGeneral = head;
        ExperimentList tempList = new ExperimentList();

        Node<Experiment> currentTemp = head;
        Node<Experiment> tempNode = new Node<Experiment>();

        while (currentGeneral!= null){
            tempList.add(currentGeneral.getData(), tempList.size);
            currentGeneral = currentGeneral.next;
            ++tempList.size;
        }

        currentTemp = tempList.head;
        for (int i = 0 ; i < tempList.size ; ++i){
            currentTemp = tempList.head;
            while (currentTemp.getNext() != null) {
                if(currentTemp.getData().getDay() > currentTemp.next.getData().getDay()){
                    tempNode.setData(currentTemp.getData());
                    currentTemp.setData(currentTemp.next.getData());
                    currentTemp.next.setData(tempNode.getData());
                }
                currentTemp = currentTemp.getNext();
            }

        }
        currentGeneral = head;
        currentTemp = tempList.head;
        while (currentTemp != null) {
            currentGeneral.setData(currentTemp.getData());
            currentGeneral = currentGeneral.next;
            currentTemp = currentTemp.next;

        }


    }

    //endregion addExp
    //region getExp
    public Experiment getExp(Integer day, Integer index) {
        Experiment e = new Experiment();
        String listString = new String();
        Node<Experiment> current = head;
        int indexBegin = 0;
        int flag = 0;
        while(current!= null && flag != 1){

            if (current.getData().getDay() == day){
                flag = 1;
            }
            ++indexBegin;
            current = current.next;
        }
        current = head;
        for (int i = 0; i < indexBegin - 1; ++i){
            if (current.next != null){
                current = current.next;
            }
        }
        if (current != null ) {
            for (int i = indexBegin; i <indexBegin + index ; ++i){
                if (current.next != null)
                    current = current.next;
            }
        }
        if(current.getData().getDay() == day){
            System.out.println(current.toString());
        }
        else{
            try {
                throw new ObjectNotFoundException("Not found experiment given day and index !");
            } catch (ObjectNotFoundException e1) {
                e1.printStackTrace();
            }

        }
        e = current.getData();
        return e;
    }

    //endregion getExp
    //region setExp
    public void setExp(Integer day, Integer index, Experiment e) {

        String listString = new String();
        Node<Experiment> current = head;
        int indexBegin = 0;
        int flag = 0;
            while(current!= null && flag != 1){
                ++indexBegin;
                if (current.getData().getDay() == day){
                    flag = 1;
                }
                current = current.next;
        }

        current = head;
        for (int i = 0; i < indexBegin - 1; ++i){
            if (current.next != null){
                current = current.next;
            }
        }
        for (int i = indexBegin; i < indexBegin + index ; ++i){
            if (current.next != null)
                current = current.next;
        }
        if(current.getData().getDay() == day && day == e.getDay()){

            current.setData(e);
        }
        else if (current.getData().getDay() != day){
            try {
                throw new ObjectNotFoundException("Not found experiment given day and index !");
            } catch (ObjectNotFoundException e1) {
                e1.printStackTrace();
            }

        }
        else if (day != e.getDay()){
            try {
                throw new ObjectNotFoundException("Given day and experiment day are not compatible");
            } catch (ObjectNotFoundException e1) {
                e1.printStackTrace();
            }

        }

    }
    //endregion setExp
    //region removeExp
    public Boolean removeExp(Integer day, Integer index) {

        Boolean result = false;
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        Node<Experiment> crunchifyCurrent = head;
        int indexBegin = 0;
        int flag = 0;
        while(crunchifyCurrent!= null && flag != 1){

            if (crunchifyCurrent.getData().getDay() == day){
                flag = 1;
            }
            ++indexBegin;
            crunchifyCurrent = crunchifyCurrent.next;
        }
        crunchifyCurrent = head;
        for (int i = 0; i < indexBegin - 1; ++i){
            if (crunchifyCurrent.next != null){
                crunchifyCurrent = crunchifyCurrent.next;
            }
        }
        for (int i = indexBegin; i < indexBegin + index ; ++i){
            if (crunchifyCurrent.next != null)
                crunchifyCurrent = crunchifyCurrent.next;
        }
            if(crunchifyCurrent.getNext()!= null)
                crunchifyCurrent.setNext(crunchifyCurrent.getNext().getNext());
            else
                removeLast();
            // decrement the number of elements variable
            size--;
            result =  true;


        return result;
    }

    //endregion removeExp
    //region listExp
    public void listExp(Integer day) {
        String listString = new String();
        Node<Experiment> current = head;
        while (current != null ) {
            if (current.data.getDay() == day && current.data.getCompleted() == true){
                listString += "Experiment{" +
                        "setup='" + current.getData().getSetup() + '\'' +
                        ", day=" + current.getData().getDay() +
                        ", time='" + current.getData().getTime() + '\'' +
                        ", accuracy=" + current.getData().getAccuracy() +
                        ", completed=" + current.getData().getCompleted() +
                        '}' + "\n";
            }
            current = current.getNext();
        }
        System.out.println(listString);
    }

    //endregion listExp
    //region removeDay
    public void removeDay(Integer day) {
        Node<Experiment> walkerNode = new Node<Experiment>();
        walkerNode = head;
        int count = 0;
        int firstIndex = 0;
        if (head == null){
            throw new NoSuchElementException();
        }
        else{
            for (int i = 0; i < size; i++) {
                if(walkerNode.data.getDay() == day){
                    firstIndex = i;
                    i = size;
                }

                walkerNode = walkerNode.getNext();
            }
            walkerNode = head;
            for (int i = 0; i < size; i++) {
                if(walkerNode.data.getDay() == day) {
                    ++count;

                }
                if (walkerNode.getNext() != null){
                    walkerNode = walkerNode.getNext();
                }
            }
        }
        for (int j = 0; j < count; ++j){
            Node<Experiment> current = head;
            if (head != null) {
                if(firstIndex == 0){
                    removeFirst();
                }
                else{
                    for (int i = 0; i < firstIndex; i++) {
                        if (current.getNext() != null)
                            current = current.getNext();
                    }
                    if(current.getNext() != null)
                        current.setNext(current.getNext().getNext());
                    else{
                        removeLast();
                        size--;
                    }

                    // decrement the number of elements variable

                }

            }
        }

    }

    //endregion removeDay
    //region orderDay
    public ExperimentList  orderDay(Integer day) {

        Experiment e = new Experiment();
        String listString = new String();
        Node<Experiment> currentGeneral = head;
        ExperimentList tempList = new ExperimentList();
        int sizeTemp = 0;

        while (currentGeneral != null ) {
            if(currentGeneral.getData().getDay() == day){
                tempList.add(currentGeneral.getData(), sizeTemp);
                ++sizeTemp;
            }
            currentGeneral = currentGeneral.getNext();
        }
        Node<Experiment> currentTemp = head;
        Node<Experiment> tempNode = new Node<>();
        currentTemp = tempList.head;
        for (int i = 0 ; i < sizeTemp ; ++i){
            currentTemp = tempList.head;
            while (currentTemp.next != null ) {
                if(currentTemp.getData().getAccuracy() > currentTemp.next.getData().getAccuracy()){
                    tempNode.setData(currentTemp.getData());
                    currentTemp.setData(currentTemp.next.getData());
                    currentTemp.next.setData(tempNode.getData());
                }
                currentTemp = currentTemp.getNext();
            }
        }
        System.out.println(tempList.toString());
        currentGeneral = head;
        currentTemp = tempList.head;
        while (currentGeneral != null) {
            if(currentGeneral.getData().getDay() == currentTemp.getData().getDay()){
                currentGeneral.setData(currentTemp.getData());
                if (currentTemp.getNext() != null)
                    currentTemp = currentTemp.getNext();
            }
            currentGeneral = currentGeneral.getNext();

        }

        return tempList;

    }

    //endregion orderDay
    //region orderExperiments
    public ExperimentList orderExperiments() {
        Experiment e = new Experiment();
        String listString = new String();
        Node<Experiment> currentGeneral = head;
        ExperimentList tempList = new ExperimentList();

        Node<Experiment> currentTemp = head;
        Node<Experiment> tempNode = new Node<Experiment>();

        while (currentGeneral!= null){
            tempList.add(currentGeneral.getData(), tempList.size);
            currentGeneral = currentGeneral.next;
            ++tempList.size;
        }
        currentTemp = tempList.head;
        for (int i = 0 ; i < tempList.size ; ++i){
            currentTemp = tempList.head;
            while (currentTemp.getNext() != null) {
                if(currentTemp.getData().getAccuracy() > currentTemp.next.getData().getAccuracy()){
                    tempNode.setData(currentTemp.getData());
                    currentTemp.setData(currentTemp.next.getData());
                    currentTemp.next.setData(tempNode.getData());
                }
                currentTemp = currentTemp.getNext();
            }
        }
        System.out.println(tempList.toString());
        return tempList;
    }
    //endregion orderExperiments
    //region HelperMethods
    //region RemoveLast
    private void removeLast() {
        Node temp = head;
        Node head1 = null;
        Node tail1 = null;
        while (temp.next != null) {

            Node node = new Node();
            node.data = temp.data;
            if (head1 == null) {
                head1 = node;
                tail1 = node;
            } else {
                tail1.next = node;
                tail1 = node;
            }
            if (temp.next.next == null) {
                temp.next = null;
                break;
            }

            temp = temp.next;

        }
        head = head1;
    }
    //endregion RemoveLast
    //region removeFirst
    public void removeFirst(){
        if(head == null)
            throw new NullPointerException();
        else{
            Node<Experiment> temp = head;
            head = head.getNext();
            temp.setNext(null);   //Which I don't understand, is it necessary?
            size --;
        }
    }
    //endregion removeFirst
    //region addLast
    public  Node<Experiment> addLast(Experiment x) {
        // save the reference to the header so we can return it.
        Node<Experiment> ret = head;

        // check base case, header is null.
        if (head == null) {
            return new Node(x);
        }

        // loop until we find the end of the list
        while ((head.next != null)) {
            head = head.next;
        }

        // set the new node to the Object x, next will be null.
        head.next = new Node(x);
        return ret;
    }
    //endregion addLast
    //region toString
    @Override
    public String toString() {
        String printString = "All experiment in linked list are : \n\n";
        Node current = head;
        while (current!= null) {
            printString += current.getData() + "\n";
            current = current.getNext();

        }

        return printString;
    }
    //endregion toString
    //region add
    public void add(Experiment element,int size)
    {
        /*if (index > size)
        {
            throw new IndexOutOfBoundsException("Oops!  Out of bounds!");
        }*/

        Node<Experiment> node = new Node<Experiment>(element);

        //Add first and last
        if(size == 0)
        {
            head = tail = node;
        }
        else
        {
            //Add first
            /*if(index == 0)
            {
                node.next = head;
                head.previous = node;
                head = node;
            }*/

            //Add last

                node.previous = tail;
                tail.next = node;
                tail = node;


            /*//Add between
            else
            {
                Node<Experiment> current = this.head;

                for(int i = 0; i < index; i++)
                {
                    current = current.next;
                }
                node.next = current;
                node.previous = current.previous;
                current.previous.next = node;
            }
        }*/
        size++;
    }
    }
    //endregion add
    //region isEmpty
    public boolean isEmpty() {
        if (size == 0)
            return true;
        else
            return false;

    }
    //endregion isEmpty
    //region IteratorMethod
    public Iterator<Experiment> iterator() {

        return new ListIterator<Experiment>(tail);
    }
    //endregion IteratorMethod
    public void listAll()
    {
        System.out.println("List experiment view:");
        Node<Experiment> last = head;
        while( last != null) {
            System.out.println(last.data.toString());
            last = last.next;
        }
        System.out.println("List day view:");
        last = head;
        if(size == 1 || size == 2){
            System.out.println(last.data.toString());
        }
        else{
            for(int i = 0; i < size; ++i){
                int flag = 0;
                last = head;
                while( last != null) {
                    if(last.getData().getDay() == i && flag != 1){
                        System.out.println(last.data.toString());
                        flag = 1;
                    }

                    last = last.next;
                }

            }
        }

    }

    //endregion HelperMethods

    //endregion Methods
    //region Exceptions
    private class ObjectNotFoundException extends Throwable {
        public ObjectNotFoundException(String s) {
        }
    }
    //endregion Exceptions

}

