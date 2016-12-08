import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {

    public static void main(String[] args) {

        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> queue = new RandomizedQueue<>();
        for (int i = 0; i < k; i++) {
            String s = StdIn.readString();

            queue.enqueue(s);
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }

    }

}
