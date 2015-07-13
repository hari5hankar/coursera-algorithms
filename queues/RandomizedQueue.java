import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] q;
    private int N; // index where the next element is to be inserted

    // construct an empty randomized queue
    public RandomizedQueue() {
        q = (Item[]) new Object[2];
        N = 0;
    }

    // is the queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items in the queue
    public int size() {
        return N;
    }

    // copies the queue array to a new array of given size.
    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = q[i];
            q[i] = null; // required?
        }
        q = temp;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new java.lang.NullPointerException();
        ;
        if (N == q.length) {
            resize(N * 2);
        }
        q[N++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("Queue underflow");
        int i = StdRandom.uniform(N);
        Item item = q[i];
        q[i] = q[--N];
        q[N] = null;
        if (N > 0 && q.length / 4 == N) {
            resize(q.length / 2);
        }
        return item;
    }

    // return (but do not remove) a random item
    public Item sample() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("Queue underflow");
        int i = StdRandom.uniform(N);
        return q[i];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {

        return new RandomizedDequeIterator();
    }

    private class RandomizedDequeIterator implements Iterator<Item> {

        int[] order;
        int index = 0;

        private RandomizedDequeIterator() {
            order = new int[N];
            for (int i = 0; i < N; i++) {
                order[i] = i;
            }
            StdRandom.shuffle(order);
        }

        public boolean hasNext() {
            return index < order.length;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

        public Item next() {
            if (index >= N)
                throw new java.util.NoSuchElementException();
            Item item = q[order[index++]];
            while (item == null) {
                item = q[order[index++]];
            }
            return item;
        }
    }

    // unit testing
    public static void main(String[] args) {

        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        System.out.println(rq.size());// ==> 0
        System.out.println(rq.isEmpty());// ==> true;
        rq.enqueue(270);//
        System.out.println(rq.size()); // ==> 1
        System.out.println(rq.size()); // ==> 1
        System.out.println(rq.dequeue()); // ==> 270
        rq.enqueue(27);//
        System.out.println(rq.size()); // ==> 1
        System.out.println(rq.isEmpty()); // ==> false
        rq.enqueue(476);
        System.out.println(rq.dequeue());

    }
}
