package iwl1CS21120Assign.HuffmanResources;

/**
 * Created by isaklovold on 22/11/2016.
 */
public class Node implements Comparable<Node> {

    private Node leftChild, rightChild;
    private String name;
    private int value, index, depth;

    /**
     * Constructor that makes it possible to create an
     * object of Node without passing any information.
     */
    public Node(){
        leftChild = null;
        rightChild = null;
        value = 0;
        index = 0;
        depth = 0;
    }

    /**
     * Constructor that initialises this object of Node.
     * @param name
     * @param value
     */
    public Node(String name, Integer value){
        this.name = name;
        this.value = value;
    }

    /**
     * @return returns left child of this Node object.
     */
    public Node getLeftChild() {
        return leftChild;
    }

    /**
     * Sets left child of this Node object.
     * @param leftChild
     */
    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    /**
     * @return returns right child of this Node object.
     */
    public Node getRightChild() {
        return rightChild;
    }

    /**
     * Sets right child of this Node object.
     * @param rightChild
     */
    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    /**
     * @return returns the name of this Node object.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this Node object.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return returns the value of this Node object.
     */
    public int getValue() {
        return value;
    }

    /**
     * @return index of which depth this node has in the tree.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Sets the index of the its position in the tree,
     * left = 0 and right = 1.
     * @param index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @return returns the depth value that this Node object has in the Huffman Tree.
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Sets the depth value that this Node object has in the Huffman Tree.
     * @param depth
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }

    /**
     * An abstract method of the implemented Comparable class
     * which compare nodes in an increasing order by the nodes value.
     * @param n1
     * @return value that determines which node has the lowest value.
     */
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
     * @return a message that contains information about this Node object.
     */
    @Override
    public String toString() {
        return "Node{" +
                "leftChild=" + leftChild +
                ", rightChild=" + rightChild +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", index=" + index +
                ", depth=" + depth +
                '}';
    }

}
