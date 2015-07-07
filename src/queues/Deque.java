package queues;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> head;
    private Node<Item> tail;
    private int N;

    private class Node<Item> {
        Item data;
        Node<Item> next;
        Node<Item> previous;
    }

    // construct an empty deque
    public Deque() {
        head = null;
        tail = null;
        N = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return head == null;
    }

    // return the number of items on the deque
    public int size() {
        return N;
    }

    // add the item to the front
    public void addFirst(Item item) {
        Node<Item> oldHead = head;
        head = new Node<Item>();
        if (tail == null)
            tail = head;
        else {
            head.next = oldHead;
            head.previous = null;
            oldHead.previous = head;
        }
        N++;
    }

    // add the item to the end
    public void addLast(Item item) {

        Node<Item> oldTail = tail;
        tail = new Node<Item>();
        tail.data = item;
        if (head == null)
            head = tail;
        else {
            oldTail.next = tail;
            tail.previous = oldTail;
            tail.next = null;
        }
        N++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("Deque underflow");
        Item data = head.data;
        head = head.next;
        head.previous = null;
        N--;
        if (isEmpty()) {
            tail = null;
        }
        return data;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("Deque underflow");
        Item data = tail.data;
        tail = tail.previous;
        tail.next = null;
        if (isEmpty()) {
            head = null;
        }
        return data;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        public boolean hasNext() {
            return false;
        }

        public void remove() {
            /* not supported */
        }

        public Item next() {
            return null;
        }
    }

    public static void main(String[] args) {
        // unit testing
    }
}
