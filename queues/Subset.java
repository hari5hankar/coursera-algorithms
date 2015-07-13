import java.util.Iterator;

public class Subset {

    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        int k = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }
        Iterator<String> iter = rq.iterator();
        while (k > 0) {
            StdOut.println(iter.next());
            k--;
        }

    }
}
