package iwl1CS21120Assign.HuffmanResources;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by isaklovold on 26/11/2016.
 */
public class BuildTree {

    private String message;

    private ArrayList<Character> occurrences;
    private Map<Character, Integer> frequency;
    private LinkedList<Node> nodeLinkedList;
    private PriorityQueue<Node> huffManTreeNodes;

    private HashMap<Character, String> encodedCharacter;

    private Node leftChild, rightChild, root, current;
    private String nodeName;
    private int nodeValue;

    private float averageNodeHeight;
    private int treeHeight, numberOfNodes;

    /**
     * Constructor that makes it possible to create an
     * object of this class without passing any information
     */
    public BuildTree(){

    }

    /**
     * Constructor that initialize this object of BuildTree
     * @param frequency
     * @param msg
     */
    public BuildTree(Map<Character, Integer> frequency, String msg) {
        this.frequency = frequency;
        this.message = msg;

        leftChild = null;
        rightChild = null;
        root = null;
        current = null;
        occurrences = new ArrayList<>();
        nodeLinkedList = new LinkedList<>();
        huffManTreeNodes = new PriorityQueue<Node>();
        encodedCharacter = new HashMap<>();
    }

    /**
     * Main method that calls all classes in right order to create a Huffman Tree
     * 1. Gets the frequency of the all characters in the map
     * 2. Sorts all characters in the map after value
     * 3. Sets all characters with their value to a node and adds them to a LinkedList
     * 4. Building the Huffman Tree by pairing nodes until there is only one node left
     * 5. Prints out the frequency table to the console
     * 6. Creating information about the tree, node height, average node height etc...
     * @return, returns the root node of Huffman Tree
     */
    public Node build() {
        getFrequency(message);
        frequency = sortMap(frequency);
        setNodes();
        pairNodes();
        printFrequencyTable();
        getTreeInformation();

        return root;
    }

    /**
     * Creating nodes out of all characters and their frequency value,
     * and adds them to a LinkedList and to a PriorityQue.
     */
    public void setNodes() {
        Set set = frequency.entrySet();
        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            Character keyValue = (Character) mentry.getKey();
            Integer value = (Integer) mentry.getValue();
            nodeName = Character.toString(keyValue.charValue());
            nodeValue = value.intValue();
            Node node = new Node(nodeName, nodeValue);
            node.setName(nodeName);
            nodeLinkedList.add(node);
            huffManTreeNodes.add(node);
            numberOfNodes++;
        }
    }

    /**
     * 1. Pairs the two nodes with lowest value.
     * 2. Creates a new Node of the combined name and value.
     * 3. Sets the first node to be the left child and the second
     *    to be the right child of the new node that is the root node.
     * 4. Adds root node to LinkedList and to PriorityQue.
     *
     * 4. Repeats step 1-4 until there is only one node left and sets
     *    root node to this node.
     */
    public void pairNodes() {
        do{
            if(huffManTreeNodes.size() > 1){
                leftChild = huffManTreeNodes.poll();
                rightChild = huffManTreeNodes.poll();
                nodeName = leftChild.getName() + rightChild.getName();
                nodeValue = leftChild.getValue() + rightChild.getValue();

                Node node = new Node(nodeName, nodeValue);
                node.setLeftChild(leftChild);
                node.setRightChild(rightChild);
                node.setName(nodeName);
                root = node;
                huffManTreeNodes.add(root);
                nodeLinkedList.add(root);
                numberOfNodes++;

            } else {
                root = huffManTreeNodes.poll();
            }
        } while (huffManTreeNodes.size() > 0);




    }

    /**
     * Gets the frequency of all characters in the map
     * @param msg, name of the node
     */
    public void getFrequency(String msg) {
        int charCount = 0;

        for (int i = 0; i < msg.length(); i++) {
            if (!(occurrences.contains(msg.charAt(i)))) {
                occurrences.add(msg.charAt(i));
                for (int j = 0; j < msg.length(); j++) {
                    if (msg.charAt(i) == msg.charAt(j)) {
                        charCount++;
                    }
                }
                frequency.put(msg.charAt(i), charCount);
            }
            charCount = 0;
        }

        Set set = frequency.entrySet();
        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            charCount++;
        }

    }

    /**
     *
     * THIS METHOD IS NOT MINE AND IS GOTTEN ONLINE
     * LINK TO DESTINATION OF METHOD DATED: 21.11.2016:
     * http://stackoverflow.com/questions/109383/sort-a-mapkey-value-by-values-java
     *
     *
     * Sorting the map in an increasing order, if you remove the comment
     * inside compareByValue(Collections.reverseOrder()),
     * the map will be sorted in an decreasing order
     * @param map, map with all characters and their frequency <K>,<V>
     * @param <K>, keys
     * @param <V>, values
     * @return, returns a sorted map
     */
    public static <K,V extends Comparable<? super V>> HashMap<K, V> sortMap(Map<K, V> map){
        return (map.entrySet()).stream().sorted(Map.Entry.comparingByValue(/*Collections.reverseOrder()*/)).collect
                (Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    /**
     * Printing out:
     * Symbol
     * Frequency of the symbol
     * Encoding of the symbol
     */
    public void printFrequencyTable(){
        System.out.println("\n@@@@@@@@@@ Frequency Table @@@@@@@@@@@");
        System.out.println("Symbol:     Frequency:      Encoding:");
        for(int i = frequency.size(); i > frequency.size(); i++){
            System.out.print("");
        }
        Set set = frequency.entrySet();
        Iterator iterator = set.iterator();

        while (iterator.hasNext()){
            Map.Entry mentry = (Map.Entry)iterator.next();
            String c = new StringBuilder().append(mentry.getKey()).toString();
            if(c.equals("\r")) {
                System.out.print("  \\r            ");
            } else {
                System.out.print("  " + mentry.getKey() + "             ");
            }
            System.out.print(mentry.getValue() + "            ");
            System.out.println(characterEncoding(c));

            encodedCharacter.put((Character)mentry.getKey(), characterEncoding(c));
        }
        System.out.println();
    }

    /**
     * Finding the binary encoding for symbol c by setting
     * nodes depth to be 0 if it's to the left and 1 if
     * it's to the right in the tree.
     * @param c, symbol that needs to be encoded
     * @return, returns string that represents symbol c
     */
    public String characterEncoding(String c){
        String encoding = "";
        boolean found = false;
        current = root;
        int depthCount = 0;

        if(!current.getName().equals(c) && current.getName().contains(c)) {
            do {
                if (!current.getName().equals(c)) {
                    if (current.getLeftChild().getName().contains(c)) {
                        depthCount++;
                        current = current.getLeftChild();
                        current.setIndex(0);
                        encoding += current.getIndex();
                        current.setDepth(depthCount);

                    } else {
                        depthCount++;
                        current = current.getRightChild();
                        current.setIndex(1);
                        encoding += current.getIndex();
                        current.setDepth(depthCount);
                    }
                } else {
                    found = true;
                }
            } while (!found);
        }

        return encoding;
    }

    /**
     * Returns a HashMap of the symbol and the its encoded string
     * @return, returns a HashMap of the symbol an its encoded string
     */
    public HashMap<Character, String> getEncodedCharacter(){
        return encodedCharacter;
    }

    /**
     * This method finds the height of the tree and calculates
     * the average node height of the tree.
     */
    public void getTreeInformation(){
        treeHeight = 0;
        int totalDepth = 0;

        for(Node n: nodeLinkedList)
        {
            if(n.getDepth() > treeHeight)
            {
                treeHeight = n.getDepth();
            }
            System.out.println(n.getDepth());
            totalDepth += n.getDepth();
        }
        averageNodeHeight = (float) totalDepth/numberOfNodes;
    }

    /**
     *
     * @return number of nodes in the tree.
     */
    public int getNumberOfNodes(){
        return numberOfNodes;
    }

    /**
     *
     * @return average node height in the tree.
     */
    public float getAverageHeight(){
        return averageNodeHeight;
    }

    /**
     *
     * @return height of the tree.
     */
    public int getTreeHeight(){
        return treeHeight;
    }
}
