package iwl1CS21120Assign.HuffmanResources;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by isaklovold on 26/11/2016.
 */
public class BuildTree {

    private String message;

    private Map<Character, Integer> frequency;
    private LinkedList<Node> nodeLinkedList;
    private PriorityQueue<Node> huffManTreeNodes;
    private ArrayList<Character> occurrences;
    private HashMap<Character, String> encodedCharacter;

    private Node leftChild, rightChild, root, current;

    private float averageNodeHeight;
    private int treeHeight;

    /**
     * Constructor that makes it possible to create an
     * object of this class without passing any information
     */
    public BuildTree(){

    }

    /**
     * Constructor that initialize this object of iwl1CS21120Assign.HuffmanResources.BuildTree
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
     * iwl1CS21120Assign.Application.iwl1CS21120Assign method that calls all classes in right order to create a Huffman Tree
     * 1. Gets the frequency of the all characters in the map
     * 2. Sorts all characters in the map after value
     * 3. Sets all characters with their value to a node and adds them to a LinkedList
     * 4. Building the Huffman Tree by pairing nodes until there is only one node left
     * 5. Prints
     * @return, returns the root node of Huffman Tree
     */
    public Node build() {
        getFrequency(message);
        frequency = sortMap(frequency);
        setNodes();
        pairNodes();
        printFrequencyTable();
        setNodeHeight();

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
            String kv = Character.toString(keyValue.charValue());
            int v = value.intValue();
            Node node = new Node(kv, v);
            nodeLinkedList.add(node);
            huffManTreeNodes.add(node);
        }
    }

    /**
     * 1. Pairs the two nodes with lowest value.
     * 2. Creates a new iwl1CS21120Assign.HuffmanResources.Node of the combined name and value.
     * 3. Sets the first node to be the left child and the second
     * to be the right child of the new node that is the root node.
     * 4. Adds root node to LinkedList and to PriorityQue before it
     *    deletes child nodes in the LinkedList.
     *
     * 4. Repeats step 1-4 until there is only one node left
     */
    public void pairNodes() {

        /*do {
            leftChild = nodeLinkedList.get(0);
            leftChild.setIndex(0);
            rightChild = nodeLinkedList.get(1);
            rightChild.setIndex(1);

            String name = leftChild.getCharacter() + rightChild.getCharacter();
            Integer value = leftChild.getValue() + rightChild.getValue();

            root = new Node(name, value);
            root.setLeftChild(leftChild);
            root.setRightChild(rightChild);

            huffManTreeNodes.add(root);

            for (int i = 0; i < nodeLinkedList.size(); i++) {
                if (nodeLinkedList.get(i).equals(leftChild)) {
                    nodeLinkedList.remove(i);
                }
                if (nodeLinkedList.get(i).equals(rightChild)) {
                    nodeLinkedList.remove(i);
                }
            }

            nodeLinkedList.add(root);

            // THIS IS NOT MY CODE
            Collections.sort(nodeLinkedList, new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return Integer.compare(o1.getValue(), o2.getValue());
                }
            });

        } while (nodeLinkedList.size() > 1);

        //pairLastNodes();

        if(nodeLinkedList.size() == 2) {
            leftChild = nodeLinkedList.get(0);
            leftChild.setIndex(0);
            rightChild = nodeLinkedList.get(1);
            rightChild.setIndex(1);
            Node n = new Node(leftChild.getCharacter() + rightChild.getCharacter(), leftChild.getValue() + rightChild.getValue());
            n.setLeftChild(leftChild);
            n.setRightChild(rightChild);
            root = n;
            nodeLinkedList.add(root);

            for (int i = 0; i < nodeLinkedList.size(); i++) {
                if (!nodeLinkedList.get(i).equals(n)) {
                    nodeLinkedList.remove(i);
                } else {
                    //System.out.println(root.getLeftChild().getLeftChild().getCharacter());
                }
            }
            nodeLinkedList.clear();
        }*/

         //THIS SECTION UNDER IS AN IMPLEMENTATION I WAS WORKING ON TO SHORTEN THEN CODE USED ABOVE, BUT NOT FULLY FUNCTIONAL

        do{

            if(huffManTreeNodes.size() > 1){

                leftChild = huffManTreeNodes.poll();
                rightChild = huffManTreeNodes.poll();

                System.out.println(leftChild.getValue());
                System.out.println(rightChild.getValue());

                Node node = new Node(leftChild.getCharacter() + rightChild.getCharacter(), leftChild.getValue() + rightChild.getValue());
                node.setLeftChild(leftChild);
                node.setRightChild(rightChild);
                root = node;
                huffManTreeNodes.add(root);

            } else {
                root = huffManTreeNodes.poll();
            }
            nodeLinkedList.add(root);
        } while (huffManTreeNodes.size() > 0);

    }


    public void pairLastNodes(){
        /*
        // IF HUFFMANTREE ONLY HAS A SIZE OF 2
        if(nodeLinkedList.size() == 2) {
            leftChild = nodeLinkedList.get(0);
            leftChild.setIndex(0);
            rightChild = nodeLinkedList.get(1);
            rightChild.setIndex(1);
            Node n = new Node(leftChild.getCharacter() + rightChild.getCharacter(), leftChild.getValue() + rightChild.getValue());
            n.setLeftChild(leftChild);
            n.setRightChild(rightChild);
            root = n;
            nodeLinkedList.add(root);

            for (int i = 0; i < nodeLinkedList.size(); i++) {
                if (!nodeLinkedList.get(i).equals(n)) {
                    nodeLinkedList.remove(i);
                } else {
                    //System.out.println(root.getLeftChild().getLeftChild().getCharacter());
                }
            }
            nodeLinkedList.clear();
            // ELSE IS IF HUFFMANTREE ONLY HAS A SIZE OF 1
        } else {
            System.out.println("HuffmanTree: " + nodeLinkedList.toString());
            System.out.println("HuffmanTreeQue: " + huffManTreeNodes.toString());
            leftChild = nodeLinkedList.get(0);
            rightChild = root;
            Node n = new Node(leftChild.getCharacter() + rightChild.getCharacter(), leftChild.getValue() + rightChild.getValue());
            root = n;
            nodeLinkedList.add(root);

            for (int i = 0; i < nodeLinkedList.size(); i++) {
                if (!nodeLinkedList.get(i).equals(n)) {
                    nodeLinkedList.remove(i);
                } else {
                    //System.out.println(root.getLeftChild().getLeftChild().getCharacter());
                }
            }

            nodeLinkedList.clear();
        }*/
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
     * Finding the encoding for symbol c
     * @param c, symbol that needs to be encoded
     * @return, returns string that represents symbol c
     */
    public String characterEncoding(String c){
        String encoding = "";
        boolean found = false;
        current = root;
        do{
            if(!current.getCharacter().equals(c)) {
                if (current.getCharacter().contains(c)) {
                    do {
                        if (!current.getCharacter().equals(c)) {
                            if (current.getLeftChild().getCharacter().contains(c)) {
                                current = current.getLeftChild();
                                encoding += "0";
                            } else {
                                current = current.getRightChild();
                                encoding += "1";
                            }
                        } else {
                            found = true;
                        }

                    } while (!found);
                } else {
                    found = true;
                }
            } else {
                found = true;
            }
        } while (!found);

        return encoding;
    }

    /**
     * Returns a HashMap of the symbol and the its encoded string
     * @return, returns a HashMap of the symbol an its encoded string
     */
    public HashMap<Character, String> getEncodedCharacter(){
        return encodedCharacter;
    }

    public void setNodeHeight(){
        int count;
        int totalCount = 0;
        int numberOfNodes = 0;
        treeHeight = 0;

        Iterator iterator = encodedCharacter.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry mentry = (Map.Entry) iterator.next();
            count = ((String) mentry.getValue()).toCharArray().length;
            for(Node n: huffManTreeNodes)
            {
                if(n.getCharacter().equals(mentry.getKey().toString())){
                    n.setDepth(count);
                    totalCount += count;
                    numberOfNodes++;
                    if(count > treeHeight){
                        treeHeight = count;
                    }
                    System.out.println(n.getDepth());
                }
            }
        }

        averageNodeHeight = (float) totalCount/numberOfNodes;

    }

    public float getAverageHeight(){
        return averageNodeHeight;
    }

    public int getTreeHeight(){
        return treeHeight;
    }
}
