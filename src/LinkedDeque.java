import java.util.Iterator;
import java.util.NoSuchElementException;

import stdlib.StdOut;
import stdlib.StdRandom;

// A data type to represent a double-ended queue (aka deque), implemented using a doubly-linked
// list as the underlying data structure.
public class LinkedDeque<Item> implements Iterable<Item> {

    // first item in the node
    Node first;
    // last item in the node
    Node last;
    // the length of the node
    int n;

    // Constructs an empty deque.
    public LinkedDeque() {
        // Initialize instance variables to appropriate values
        this.n = 0;
        this.first = null;
        this.last = null;

    }

    // Returns true if this deque is empty, and false otherwise.
    public boolean isEmpty() {
        return n == 0;
    }

    // Returns the number of items in this deque.
    public int size() {
        return n;
    }

    // Adds item to the front of this deque.
    public void addFirst(Item item) {

        // returns error if item is null
        if (item == null) {
            throw new NullPointerException("item is null");
        }
        // Add the given item to the front of the deque
        Node oldFirst = first;
        first = new Node();
        // setting the old node to the first
        first.next = oldFirst;
        // exchanging the node with each other
        first.item = item;

        if (!isEmpty()) {
            // if the deque is empty and set the previous node to the first
            oldFirst.prev = first;
        } else {
            // exchange last to first
            last = first;
        }
        n++;


    }

    // Adds item to the back of this deque.
    public void addLast(Item item) {
        // throw a error if the item is null
        if (item == null) {
            throw new NullPointerException("item is null");
        }
        // create new node
        Node oldLast = new Node();
        // sets the new prev to the last
        oldLast.prev = last;
        oldLast.item = item;
        n++;
        // if the last node is not null
        if (last != null) {
            last.next = oldLast;
        }
        // sets last node to the new created node
        last = oldLast;

        if (first == null) {
            first = last;
        }


    }

    // Returns the item at the front of this deque.
    public Item peekFirst() {
        // throw a error if the deque is empty
        if (isEmpty() || first == null) {
            throw new NoSuchElementException("Deque is empty");
        }
        return first.item;
    }

    // Removes and returns the item at the front of this deque.
    public Item removeFirst() {
        // throw a error if the deque is empty
        if (isEmpty() || first == null) {
            throw new NoSuchElementException("Deque is empty");
        }
        // sets a new node to the first
        Node newItem = first;
        first = first.next;
        n--;
        // checks if it first is not empty set
        if (first != null) {
            // sets the last node to null
            first.prev = null;
        } else {
            last = null;
        }
        return newItem.item;

    }

    // Returns the item at the back of this deque.
    public Item peekLast() {
        // throw a error if the deque is empty
        if (isEmpty() || first == null) {
            throw new NoSuchElementException("Deque is empty");
        }

        return last.item;
    }

    // Removes and returns the item at the back of this deque.
    public Item removeLast() {
        if (isEmpty() || last == null) {
            throw new NoSuchElementException("Deque is empty");
        }
        // temporarily saves the last node
        Node oldItem = last;
        last = oldItem.prev;
        n--;
        // If this is the last item that is being removed
        if (last != null) {
            //  both first and last must point to null
            last.next = null;
        } else {
            first = null;
        }

        return oldItem.item;


    }

    // Returns an iterator to iterate over the items in this deque from front to back.
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // Returns a string representation of this deque.
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : this) {
            sb.append(item);
            sb.append(", ");
        }
        return n > 0 ? "[" + sb.substring(0, sb.length() - 2) + "]" : "[]";
    }

    // A deque iterator.
    private class DequeIterator implements Iterator<Item> {

        // the current node that iterator is on
        Node current;

        // Constructs an iterator.
        public DequeIterator() {
            this.current = first;
        }

        // Returns true if there are more items to iterate, and false otherwise.
        public boolean hasNext() {
            return current != null;
        }

        // Returns the next item.
        public Item next() {
            // checks if the Iterator is finished
            if (!hasNext()) {
                throw new NoSuchElementException("Iterator is empty");
            }
            // returns the current to the next node
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // A data type to represent a doubly-linked list. Each node in the list stores a generic item
    // and references to the next and previous nodes in the list.
    private class Node {
        private Item item;  // the item
        private Node next;  // the next node
        private Node prev;  // the previous node
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        LinkedDeque<Character> deque = new LinkedDeque<Character>();
        String quote = "There is grandeur in this view of life, with its several powers, having " +
                "been originally breathed into a few forms or into one; and that, whilst this " +
                "planet has gone cycling on according to the fixed law of gravity, from so simple" +
                " a beginning endless forms most beautiful and most wonderful have been, and are " +
                "being, evolved. ~ Charles Darwin, The Origin of Species";
        int r = StdRandom.uniform(0, quote.length());
        StdOut.println("Filling the deque...");
        for (int i = quote.substring(0, r).length() - 1; i >= 0; i--) {
            deque.addFirst(quote.charAt(i));
        }
        for (int i = 0; i < quote.substring(r).length(); i++) {
            deque.addLast(quote.charAt(r + i));
        }
        StdOut.printf("The deque (%d characters): ", deque.size());
        for (char c : deque) {
            StdOut.print(c);
        }
        StdOut.println();
        StdOut.println("Emptying the deque...");
        double s = StdRandom.uniform();
        for (int i = 0; i < quote.length(); i++) {
            if (StdRandom.bernoulli(s)) {
                deque.removeFirst();
            } else {
                deque.removeLast();
            }
        }
        StdOut.println("deque.isEmpty()? " + deque.isEmpty());
    }
}
