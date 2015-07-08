package queues;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> head;
    private Node<Item> tail;
    private int N;

    private static class Node<Item> {
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
        if (item == null)
            throw new java.lang.NullPointerException();
        Node<Item> oldHead = head;
        head = new Node<Item>();
        head.data = item;
        if (tail == null) {
            tail = head;
            head.next = null; // default?
            tail.previous = null; // default?
        } else {
            head.next = oldHead;
            head.previous = null; // default?
            oldHead.previous = head;
        }
        N++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null)
            throw new java.lang.NullPointerException();
        Node<Item> oldTail = tail;
        tail = new Node<Item>();
        tail.data = item;
        if (head == null) {
            head = tail;
            tail.next = null;
            tail.previous = null;
        } else {
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
        head.data = null; // required?
        head = head.next;
        N--;
        if (head != null) {
            head.previous = null;
        }
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
        tail.data = null;
        tail = tail.previous;
        N--;
        if (tail != null) {
            tail.next = null;
        }
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

        Node<Item> currentNode = head;

        public boolean hasNext() {
            return currentNode != null;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

        public Item next() {
            Item data = currentNode.data;
            currentNode = currentNode.next;
            return data;
        }
    }

    // unit testing
    public static void main(String[] args) {

        Deque<Integer> d = new Deque<>();
        d.addFirst(1);
        System.out.println(d.removeFirst());
        System.out.println(d.removeFirst());
    }
}
