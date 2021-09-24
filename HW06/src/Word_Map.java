import java.util.*;

public class Word_Map implements Map, Iterable
{

    final static int INITCAP=10;  //initial capacity
    int CURRCAP = INITCAP;   //current capacity
    final static float LOADFACT = 0.75f;
    private Node table[];

    public Word_Map() {
        this.table = new Node[INITCAP];

    }

    private int find(Object key) {
        // Calculate the starting index.
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length; // Make it positive.

        // Increment index until an empty slot is reached
        // or the key is found.
        while ( (table[index] != null)
                && (!key.equals(table[index].key))) {
            index++;
            // Check for wraparound.
            if (index >= table.length)
                index = 0; // Wrap around.
        }
        return index;
    }
    @Override
    public Iterator iterator() {
        return null;
    }

    //region Node
    static class Node<K, V> {
        // reference to the next node in the chain, or null if there isn't one.
        public Node<K, V> next;
        // reference to the next node in the chain, or null if there isn't one.
        public K key;

        // data carried by this node. could be of any type you need.
        public V value;

        // No parameter constructor
        public Node() {
            next = null;
            value = null;
        }
        // Node constructor
        public Node(V dataValue) {
            next = null;
            value = dataValue;
        }

        // another Node constructor if we want to specify the node to point to.
        @SuppressWarnings("unused")
        public Node(V dataValue, Node<K,V> nextValue) {
            next = nextValue;
            value = dataValue;
        }

        public void setData(V data) {
            this.value = data;
        }

        public V getData() {
            return value;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K  previous) {
            this.key = previous;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }
    //endregion Node
    //region size
    @Override
    public int size() {
        int returnValue = 0;

        int i = 0;
        while (i <= table.length && table[i] != null){
            ++returnValue;
            ++i;
        }

        return returnValue;
    }
    //endregion size
    //region isEmpty
    @Override
    public boolean isEmpty() {

        int i = 0;
        while (i < table.length && table[i] != null){
            ++i;
        }

        if(i == 0)
            return true;
        else
            return false;
    }
    //endregion isEmpty
    //region containsKey
    @Override
    /*Use linked structure instead of table index
    to perform search operation effectively
     * */
    public boolean containsKey(Object key) {
        boolean returnBool = false;
        int i = 0;
        while (table[i] != null && i < table.length){
            if (table[i].key == key){
                returnBool = true;
            }
            ++i;
        }
        return returnBool;
    }
    //endregion containsKey
    //region containsValue
    @Override
    /*Use linked structure instead of table index
    to perform search operation effectively
     * */
    public boolean containsValue(Object value) {
        boolean returnBool = false;
        int i = 0;
        while (table[i] != null && i <= table.length){
            if (table[i].value == value){
                returnBool = true;
            }
            ++i;
        }
        return returnBool;
    }
    //endregion containsValue
    //region get
    @Override
    public Object get(Object key) {
        Object returnValue = null;
        int i = -1;
        while (table[i+1] != null && i < table.length){
            if (table[i+1].key == key){
                returnValue = table[i+1].value;
            }
            ++i;
        }
        return returnValue;
    }
    //endregion get
    //region put
    @Override
    /*
    Use linear probing in case of collision
    * */
    public Object put(Object key, Object value) {
        Node returnNode = new Node();
        int index = find(key);

        Node list = table[index];
        int i = -1;
        int lengthOf = 0;
        while (lengthOf < table.length){
            if (table[index] == null){
                table[index] = new Node();
                table[index].key = key;
                table[index].value = value;
                lengthOf = table.length;
            }
            ++i;
            ++lengthOf;
        }


        returnNode.key = key;
        returnNode.value = value;
        return returnNode;
    }
    //endregion put
    //region remove
    @Override
    /*You do not need to implement remove function
     * */
    public Object remove(Object key) {
        Node returnNode = new Node();
        // If the array is empty
        // or the index is not in array range
        // return the original array
        if (table == null) {

            return null;
        }

        // Create another array of size one less
        Node[] tempNode = new Node[table.length - 1];

        // Copy the elements except the index
        // from original array to the other array
        for (int i = 0, k = 0; i < table.length; i++) {
            if(table[i] != null){
                if (table[i].key != key) {
                    // if the index is not
                    // the removal element index
                    tempNode[k] = new Node();
                    tempNode[k].key = table[i].key;
                    tempNode[k].value = table[i].value;
                    ++k;
                }
            }

        }
        table = tempNode;

        // return the resultant array
        return table;

    }
    //endregion remove
    //region putAll
    //TODO: putall size
    @Override
    public void putAll(Map m) {

        if (m == null){
            throw new NullPointerException();
        }
        int i = -1;
        for (Object name: m.keySet()){
            int index = find(name);

            Node list = table[index];
            table[index] = new Node();
            table[index].key = name.toString();
            table[index].value = m.get(name).toString();
            ++i;
        }
    }
    //endregion putAll
    //region clear
    @Override
    public void clear() {
        for (int i = 0; i < table.length; i++) {
            if(table[i] != null){
                table[i] = null;
            }

        }
    }
    //endregion clear
    //region keySet
    @Override
    /*Use linked structure instead of table index
    for efficiency
     * */
    public Set keySet() {
        Set<Object> myKeys= new HashSet<Object>();
        int i = -1;
        while (i <= table.length && table[i + 1] != null){
            myKeys.add(table[i + 1].key);
            ++i;
        }
        return myKeys;

    }
    //endregion keySet
    //region values
    @Override
    /*Use linked structure instead of table index
    for efficiency
     * */
    public Collection values() {
        Collection<Object> myValues= new HashSet<Object>();
        int i = 0;
        while (i <= table.length && table[i] != null){
            myValues.add(table[i].value);
            ++i;
        }
        return myValues;
    }
    //endregion values
    //region entrySet
    @Override
    /*You do not need to implement entrySet function
     * */
    public Set<Entry> entrySet() {
        return null;
    }
    //endregion entrySet
}
