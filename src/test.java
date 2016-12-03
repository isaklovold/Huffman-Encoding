import java.util.PriorityQueue;
import java.util.function.Supplier;

/**
 * Created by isakl on 02/12/2016.
 */
public class test {

    public static void main(String[] args){
        PriorityQueue<Integer> huffmantree = new PriorityQueue<>();
        huffmantree.add(4);
        huffmantree.add(112);
        huffmantree.add(13);
        huffmantree.add(5);
        huffmantree.add(3);
        huffmantree.add(7);
        huffmantree.add(1);

        int x, y, z;

        do{
            x = huffmantree.poll();
            y = huffmantree.poll();
            z = x+y;
            huffmantree.add(z);
            System.out.println("X: "+ x);
            System.out.println("Y: "+ y);
            System.out.println("Z: "+ z);

        } while (huffmantree.size() > 1);
        System.out.println(z);
    }

}
