import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> head;
    private Node<Item> tail;
    private int N;

    private static class Node<Item> {
        Item item;
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
        return head == null || tail == null;
    }

    // return the number of items in the deque
    public int size() {
        return N;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new java.lang.NullPointerException();
        Node<Item> oldHead = head;
        head = new Node<Item>();
        head.item = item;
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
        tail.item = item;
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
        Node<Item> oldHead = head;
        head = oldHead.next;
        Item item = oldHead.item;
        N--;
        if (head != null) {
            head.previous = null;
        }
        if (isEmpty()) {
            tail = null;
        }
        return item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("Deque underflow");
        Node<Item> oldTail = tail;
        tail = oldTail.previous;
        Item item = oldTail.item;
        N--;
        if (tail != null) {
            tail.next = null;
        }
        if (isEmpty()) {
            head = null;
        }
        return item;
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
            if (currentNode == null)
                throw new java.util.NoSuchElementException();
            Item item = currentNode.item;
            currentNode = currentNode.next;
            return item;
        }
    }

    // unit testing
    public static void main(String[] args) {

        Deque<Integer> d = new Deque<>();
        for (int i = 0; i < 2; i++) {
            d.addFirst(i);
        }
        for (int i = 0; i < 2; i++) {
            System.out.println(d.removeLast());
        }
        System.out.println(d.isEmpty());

    }
}
