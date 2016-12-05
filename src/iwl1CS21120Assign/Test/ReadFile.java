package iwl1CS21120Assign.Test;

import java.io.File;

/**
 * Created by isaklovold on 27/11/2016.
 */
public class ReadFile {

    public ReadFile(){

    }

    public static void main(String[] args){
        File file = new File("test-short.txt");
        System.out.println(file.length());
    }

}
