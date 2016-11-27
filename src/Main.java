import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by isaklovold on 17/11/2016.
 */
public class Main {

    private Scanner in;
    private String message;

    private File fileName;
    private Scanner fileScanner;

    private HashMap<Character, Integer> frequency;
    private Node root;
    private BuildTree huffmanTree;

    private HashMap<Character, String> encodedCharacter;

    private long uncompressed, compressed, compressionRatio;
    private float ratio;


    /**
     * Constructor that initialize instance variables
     */
    public Main() {
        in = new Scanner(System.in);

        frequency = new HashMap<>();
        root = new Node();
        huffmanTree = new BuildTree();
        encodedCharacter = new HashMap<>();
    }

    /**
     * Main method that runs the program cycle
     * Case "1": reads file, create huffman tree and create a compressed file of the selected file
     * Case "2": prints out the frequency table
     * Case "3": prints out information about the root note in huffman tree
     * case "Q": exits the program
     * @throws IOException, for user input
     */
    public void runApp() throws IOException {
        String input;
        do{
            printMenu();
            input = in.nextLine().toUpperCase();
            switch (input){
                case "1":
                    // READ FILE
                    System.out.println("Enter name of file to compress: ");
                    fileName = new File(in.next()+".txt");
                    fileScanner = new Scanner(fileName);

                    message = "";

                    while (fileScanner.hasNextLine()){
                        message += fileScanner.nextLine() + "\n";
                    }

                    // CREATE HUFFMAN TREE
                    if(message != null) {
                        huffmanTree = new BuildTree(frequency, message);
                        root = huffmanTree.build();
                    } else {
                        System.err.println("You have not entered a message or a file yet, please try again later!");
                    }

                    // CREATE A COMPRESSED FILE
                    compressFile();
                    break;
                case "2":
                    // PRINT FREQUENCY TABLE
                    if(huffmanTree != null) {
                        huffmanTree.printFrequencyTable();
                    } else {
                        System.err.println("Huffman Tree is not created yet, please try again later!");
                    }
                    break;
                case "3":
                    // PRINT ROOT NODE INFO
                    System.out.println(root.toString());
                    break;
                case "Q":
                    // QUIT APPLICATION
                    System.out.println("Thanks for using the Huffman Encoding System by Isak Wisth Løvold");
                    System.exit(0);
                    break;
                default:break;
            }
        } while(!input.equals("Q"));
    }

    /**
     * Creates a compressed version of a selected file
     */
    public void compressFile(){
        System.out.println("\n@@@@@ File Compression @@@@@");
        encodedCharacter = huffmanTree.getEncodedCharacter();
        try{
            File newFile = new File("encoded_" + fileName);
            PrintWriter write = new PrintWriter(newFile, "UTF-8");
            for(Character c: message.toCharArray())
            {
                Set set = encodedCharacter.entrySet();
                Iterator iterator = set.iterator();

                while (iterator.hasNext()){
                    Map.Entry mentry = (Map.Entry)iterator.next();
                    if(c.equals(mentry.getKey())){
                        write.print(mentry.getValue());
                    }
                }
            }
            write.close();


            System.out.println(fileName.length() * 3);

            uncompressed = (fileName.length()*3);
            compressed = newFile.length();
            ratio = (float) compressed/uncompressed;
            compressionRatio = (long) (100-(ratio*100));


            System.out.println("Encoded file created!");
            System.out.println("Uncompressed file name: " + fileName);
            System.out.println("Compressed file name: " + newFile);
            System.out.println("Uncompressed file (bits): " + uncompressed);
            System.out.println("Compressed file (bits): " + compressed);
            System.out.println("Compression ratio: " + compressionRatio + "%");


        } catch (IOException e){

        }
    }

    /**
     * Prints out menu options
     */
    public void printMenu(){
        System.out.println("\n@@@@@@@@@@ Menu @@@@@@@@@@");
        System.out.println( "1. Enter a message: \n" +
                "2. Input a file \n" +
                "3. Create Huffman Tree \n" +
                "4. Print Table \n" +
                "5. Print root node .toString \n" +
                "6. Create a Huffman encoded file \n" +
                "Q. Quit");
    }

    /**
     * Main method
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Main app = new Main();
        app.runApp();
    }

}