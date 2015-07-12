package queues;

import java.util.Iterator;

import edu.princeton.cs.introcs.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] q;
    private int N;
    private int front; // position of next item to be dequeued
    private int back; // position of next item to be enqueued

    // construct an empty randomized queue
    public RandomizedQueue() {
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
        int i, j, k; // i for indexing the new array, j for the old array,
                     // k for counting the number of elements copied.
        for (i = 0, j = front, k = 0; k < N; i++) {
            if (q[j] == null)
                continue;
            temp[i] = q[j];
            q[j] = null; // required?
            j++;
            k++;
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
        int i = StdRandom.uniform(front, back);
        Item item = q[i];
        q[i] = null;
        N--;
        if (q.length / 2 == N) {
            resize(q.length / 2);
        }
        return item;
    }

    // return (but do not remove) a random item
    public Item sample() {
        int i = StdRandom.uniform(front, back);
        return q[i];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {

        return null;
    }

    // unit testing
    public static void main(String[] args) {

        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        for (int i = 0; i < 10; i++) {
            rq.enqueue(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.print(rq.sample());
        }

    }
}