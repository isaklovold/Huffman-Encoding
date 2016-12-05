package iwl1CS21120Assign.HuffmanResources;

import java.util.Comparator;

/**
 * Created by isaklovold on 22/11/2016.
 */
public class Node implements Comparable<Node> {

    private Node leftChild, rightChild;
    private String character;
    private int value, index, depth;

    /**
     * Constructor that makes it possible to create an
     * object of iwl1CS21120Assign.HuffmanResources.Node without passing any information
     */
    public Node(){
        leftChild = null;
        rightChild = null;
        value = 0;
        index = 0;
        depth = 0;
    }

    /**
     * Constructor that initialises this object of iwl1CS21120Assign.HuffmanResources.Node
     * @param character
     * @param value
     */
    public Node(String character, Integer value){
        this.character = character;
        this.value = value;
    }

    /**
     * Gets left child of this iwl1CS21120Assign.HuffmanResources.Node object
     * @return, returns left child of this iwl1CS21120Assign.HuffmanResources.Node object
     */
    public Node getLeftChild() {
        return leftChild;
    }

    /**
     * Sets left child of this iwl1CS21120Assign.HuffmanResources.Node object
     * @param leftChild
     */
    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    /**
     * Gets right child of this iwl1CS21120Assign.HuffmanResources.Node object
     * @return, returns right child of this iwl1CS21120Assign.HuffmanResources.Node object
     */
    public Node getRightChild() {
        return rightChild;
    }

    /**
     * Sets right child of this iwl1CS21120Assign.HuffmanResources.Node object
     * @param rightChild
     */
    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    /**
     * Gets the name of this iwl1CS21120Assign.HuffmanResources.Node object
     * @return, returns the name of this iwl1CS21120Assign.HuffmanResources.Node object
     */
    public String getCharacter() {
        return character;
    }

    /**
     * Sets the name of this iwl1CS21120Assign.HuffmanResources.Node object
     * @param character
     */
    public void setCharacter(String character) {
        this.character = character;
    }

    /**
     * Gets the value of this iwl1CS21120Assign.HuffmanResources.Node object
     * @return, returns the value of this iwl1CS21120Assign.HuffmanResources.Node object
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value of this iwl1CS21120Assign.HuffmanResources.Node object
     * @param value
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     *
     * @return
     */
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Gets the depth value that this iwl1CS21120Assign.HuffmanResources.Node object has in the Huffman Tree
     * @return, returns the depth value that this iwl1CS21120Assign.HuffmanResources.Node object has in the Huffman Tree
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Sets the depth value that this iwl1CS21120Assign.HuffmanResources.Node object has in the Huffman Tree
     * @param depth
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public int compareTo(Node n1) {
        if(this.getValue() == n1.getValue()) {
            return 0;
        } else if(this.getValue() > n1.getValue()) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * Prints out information about this iwl1CS21120Assign.HuffmanResources.Node object
     * @return, returns a message that contains information about this iwl1CS21120Assign.HuffmanResources.Node object
     */
    @Override
    public String toString() {
        return "iwl1CS21120Assign.HuffmanResources.Node{" +
                "leftChild=" + leftChild +
                ", rightChild=" + rightChild +
                ", character='" + character + '\'' +
                ", value=" + value +
                ", index=" + index +
                ", depth=" + depth +
                '}';
    }

}