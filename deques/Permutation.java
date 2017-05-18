import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

/**
 * Permutation of size k of the strings from standard input.
 */
public class Permutation {

    /**
     * Simple client to test
     * @param args
     */
    public static void main(String[] args) {

        int k = Integer.parseInt(args[0]);
        // StdOut.println("debug: k= " + k);

        if (k == 0)
            return;

        RandomizedQueue<String> rq = new RandomizedQueue<>();
        String s;

        while (!StdIn.isEmpty()) {
            s = StdIn.readString();
            rq.enqueue(s);
            // StdOut.println("debug" + s);
        }

        // StdOut.println("debug: done");

        for(String p : rq) {
            StdOut.println(p);
            k--;
            if (k == 0)
                break;
        }
    }
}