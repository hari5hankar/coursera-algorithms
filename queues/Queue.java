
import java.util.Iterator;

public class Queue<Item> implements Iterable<Item> {

    private Item[] q;   //array to hold the queue
    private int N;      //current size of the queue
    private int front;  // position of next item to be dequeued
    private int back;   // position of next item to be enqueued

    // construct an empty randomized queue
    public Queue() {
        q = (Item[]) new Object[2];
        N = 0;
        front = 0;
        back = 0;
    }

    // is the queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the queue
    public int size() {
        return N;
    }

    // copies the queue array to a new array of given size.
    public void resize(int size) {
        Item[] temp = (Item[]) new Object[size];
        int i, j;
        for (i = 0, j = front; i < N; i++, j++) {
            temp[i] = q[j];
            q[j] = null; // required?
        }
        front = 0;
        back = N;
        q = temp;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new java.lang.IllegalArgumentException();
        if (N == q.length) {
            resize(N * 2);
        }
        q[back++] = item;
        N++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("Queue underflow");
        Item item = q[front++];
        N--;
        if (q.length / 2 == N) {
            resize(q.length / 2);
        }
        return item;
    }

    // return (but do not remove) a random item
    public Item sample() {

        return null;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {

        return null;
    }

    // unit testing
    public static void main(String[] args) {

        Queue<Integer> rq = new Queue<>();
        for (int i = 0; i < 100; i++) {
            rq.enqueue(i);
        }
        for (int i = 0; i < 100; i++) {
            System.out.println(rq.dequeue());
        }

    }
}
