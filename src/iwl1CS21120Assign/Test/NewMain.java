package iwl1CS21120Assign.Test;

import iwl1CS21120Assign.HuffmanResources.BuildTree;
import iwl1CS21120Assign.HuffmanResources.Node;

import java.util.*;

/**
 * Created by isaklovold on 24/11/2016.
 */
public class NewMain {

    Scanner in;
    String message;

    private HashMap<Character, Integer> frequency;
    private Node root;
    BuildTree huffmanTree;

    public NewMain(){
        in = new Scanner(System.in);
        frequency = new HashMap<>();
        root = new Node();
        huffmanTree = new BuildTree();
    }

    public void runApp(){
        String input;
        do{
            printMenu();
            input = in.nextLine().toUpperCase();
            switch (input){
                case "1":
                    System.out.println("Please enter a message that is greater than contains more than three characters: ");
                    message = in.nextLine();
                    if(!(message.length() > 2)){
                        message = null;
                    }
                    break;
                case "2":

                    break;
                case "3":
                    if(message != null) {
                        huffmanTree = new BuildTree(frequency, message);
                        root = huffmanTree.build();
                        System.out.println("Huffman Tree built!");
                    } else {
                        System.err.println("You have not entered a message or a file yet, please try again later!");
                    }
                    break;
                case "4":
                    if(huffmanTree != null) {
                        huffmanTree.printFrequencyTable();
                    } else {
                        System.err.println("Huffman Tree is not created yet, please try again later!");
                    }
                    break;
                case "5":
                    System.out.println(root.toString());
                    break;
                case "Q":
                    System.out.println("Thanks for using the Huffman Encoding System by Isak Wisth Løvold");
                    System.exit(0);
                    break;
                default:break;
            }
        } while(!input.equals("Q"));
    }

    public void printMenu(){
        System.out.println("\n@@@@@@@@@@ Menu @@@@@@@@@@");
        System.out.println( "1. Enter a message: \n" +
                            "2. Input a file \n" +
                            "3. Create Huffman Tree \n" +
                            "4. Print Table \n" +
                            "5. Print root node .toString" +
                            "Q. Quit");
    }

    public static void main(String[] args){
        NewMain app = new NewMain();
        app.runApp();
    }

}
