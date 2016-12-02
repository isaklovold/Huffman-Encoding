import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by isaklovold on 17/11/2016.
 */
public class Main {

    private Scanner in;
    private String message;

    private File fileName, dir;
    private Scanner fileScanner;

    private HashMap<Character, Integer> frequency;
    private Node root;
    private BuildTree huffmanTree;

    private HashMap<BuildTree, Node> huffmanTreeMap;

    private HashMap<Character, String> encodedCharacter;

    private long uncompressed, compressed, compressionRatio;
    private float ratio;


    /**
     * Constructor that initialize instance variables
     */
    public Main() {
        in = new Scanner(System.in);

        root = new Node();
        huffmanTree = new BuildTree();
        encodedCharacter = new HashMap<>();
        huffmanTreeMap = new HashMap<>();
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
                    optionOne();
                    break;
                case "2":
                    optionTwo();
                    break;
                case "3":
                    optionThree();
                    break;
                case "4":
                    optionFour();
                    break;
                case "5":
                    // PRINT ROOT NODE INFO
                    System.out.println(root.toString());
                    break;
                case "6":
                    // PRINTS ALL ROOT NODES INFO
                    if(huffmanTreeMap != null){
                        for(Node n: huffmanTreeMap.values())
                        {
                            System.out.println(n.toString());
                        }
                    }
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

    public void optionOne() throws IOException{
        try {
            // DECLARES FREQUENCY MAP HERE SO THAT WE CAN READ MULTIPLE FILES AFTER EACH OTHER
            frequency = new HashMap<>();

            // COMPRESS SINGLE FILE
            System.out.println("Please enter name of file to compress without file extension: ");
            fileName = new File(in.next() + ".txt");
            createHuffmantree(fileName);

            // CREATE A COMPRESSED FILE
            compressFile();

            // ADDS HUFFMANTREE AND ROOT NODE OF HUFFMANTREE TO A MAP IF IT'S NOT ALREADY THERE
            if(huffmanTreeMap != null && !huffmanTreeMap.containsKey(huffmanTree) && !huffmanTreeMap.containsValue(root)){
                huffmanTreeMap.put(huffmanTree, root);
            }
        } catch (FileNotFoundException e){
            System.err.println("Sorry, couldn't find that file...");
        }
    }

    public void optionTwo() throws IOException {
        try{
            // CHOOSE FOLDER WITH MULTIPLE FILES TO COMPRESS
            System.out.println("Please enter path to folder with files to compress: ");
            dir = new File(in.next());
            for(File file : dir.listFiles()){
                try {
                    // CHECK THAT IT'S NOT THE DEFAULT FOLDER FILE FOR MAC
                    if(!file.getName().equals(".DS_Store")) {
                        // DECLARES FREQUENCY MAP HERE SO THAT WE CAN READ MULTIPLE FILES AFTER EACH OTHER
                        frequency = new HashMap<>();
                        createHuffmantree(file);

                        // ADDS HUFFMANTREE AND ROOT NODE OF HUFFMANTREE TO A MAP
                        if(huffmanTreeMap != null && !huffmanTreeMap.containsKey(huffmanTree) && !huffmanTreeMap.containsValue(root)){
                            huffmanTreeMap.put(huffmanTree, root);
                        }
                    }
                } catch (FileNotFoundException er){
                    System.err.println("Sorry, couldn't any files inside that folder...");
                }
            }
        } catch (NullPointerException e){
            System.err.println("Sorry, couldn't find that file or folder...");
        }
    }

    public void optionThree(){
        // PRINT FREQUENCY TABLE
        if(huffmanTree != null) {
            huffmanTree.printFrequencyTable();
        } else {
            System.err.println("Huffman Tree is not created yet, please try again later!");
        }
    }

    public void optionFour(){
        if(huffmanTreeMap != null){
            for(BuildTree bt: huffmanTreeMap.keySet())
            {
                bt.printFrequencyTable();
            }
        }
    }

    public void createHuffmantree(File file) throws IOException{
        // READ FILE
        fileScanner = new Scanner(file);

        message = "";

        while (fileScanner.hasNextLine()){
            message += fileScanner.nextLine();
            if(fileScanner.hasNextLine()){
                message += "\r";
            }
        }

        // CREATE HUFFMAN TREE
        System.out.println("\n\nFile name: " + file.getName());
        if(message != null) {
            huffmanTree = new BuildTree(frequency, message);
            root = huffmanTree.build();
        } else {
            System.err.println("You have not entered a message or a file yet, please try again later!");
        }
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
        System.out.println( "1. Compress single file: \n" +
                "2. Compress all files in a folder: \n" +
                "3. Print frequency table \n" +
                "4. Print frequency table off all files: \n" +
                "5. Print root node: \n" +
                "6. Print root node of huffman trees: \n" +
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