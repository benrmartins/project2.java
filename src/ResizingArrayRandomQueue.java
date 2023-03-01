import java.util.Iterator;
import java.util.NoSuchElementException;

import stdlib.StdOut;
import stdlib.StdRandom;

// A data type to represent a random queue, implemented using a resizing array as the underlying
// data structure.
public class ResizingArrayRandomQueue<Item> implements Iterable<Item> {
    // the array of queue
    Item[] q;
    // the size of the array
    int n;

    // Constructs an empty random queue.
    public ResizingArrayRandomQueue() {
        //  Initialize instance variables appropriately
        q = (Item[]) new Object [2];
        this.n = 0;

    }

    // Returns true if this queue is empty, and false otherwise.
    public boolean isEmpty() {
        if (n == 0) {
            return true;
        }
        return false;
    }

    // Returns the number of items in this queue.
    public int size() {
        return n;
    }

    // Adds item to the end of this queue.
    public void enqueue(Item item) {
        // throw an error if there is no item
        if (item == null) {
            throw new NullPointerException("item is null");
        }

        // when n is at the end of the list, it resizes it
        if (n == q.length) {
            resize(q.length * 2);
        }
        // adds the item to the end of the list
        q[n++] = item;



    }

    // Returns a random item from this queue.
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Random queue is empty");
        }
        // randomly choose a item from the list
        return q[StdRandom.uniform(0, n)];

    }

    // Removes and returns a random item from this queue.
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Random queue is empty");

        }
        // randomly choose a item from the list
        int ran = StdRandom.uniform(0, n);
        Item item = q[ran];

        // removes the random item from the list
        q[ran] = q[n-1];
        q[n-1] = null;

        n--;
        // resizes the array after removing it
        if (n <= q.length/4 && n > 0) {
            resize(q.length / 2);
        }

        return item;
    }

    // Returns an independent iterator to iterate over the items in this queue in random order.
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();

    }

    // Returns a string representation of this queue.
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : this) {
            sb.append(item);
            sb.append(", ");
        }
        return n > 0 ? "[" + sb.substring(0, sb.length() - 2) + "]" : "[]";
    }

    // An iterator, doesn't implement remove() since it's optional.
    private class RandomQueueIterator implements Iterator<Item> {
        // the array of queues of items
        Item[] items;
        // the current index in the queues
        int current;

        // Constructs an iterator.
        public RandomQueueIterator() {
            // adds the items from q to items
            Item[] temp = (Item[]) new Object[n];
            for (int i = 0; i < n; i++) {
                temp[i] = q[i];
            }
            items = temp;
            // shuffles the items in the array
            StdRandom.shuffle(items);
            this.current = 0;

        }

        // Returns true if there are more items to iterate, and false otherwise.
        public boolean hasNext() {
            // if current is less than items, there are no items to iterate
            if (current < items.length) {
                return true;
            }
            return false;

        }

        // Returns the next item.
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Iterator is empty");
            }

            Item item = null;
            // checks if the item is null
            while (item == null) {
                // returns the next item when it's not null
                item = items[current];
                current++;
            }
            return item;
        }
    }

    // Resizes the underlying array.
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < n; i++) {
            if (q[i] != null) {
                temp[i] = q[i];
            }
        }
        q = temp;
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        ResizingArrayRandomQueue<Integer> q = new ResizingArrayRandomQueue<Integer>();
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            int r = StdRandom.uniform(10000);
            q.enqueue(r);
            sum += r;
        }
        int iterSumQ = 0;
        for (int x : q) {
            iterSumQ += x;
        }
        int dequeSumQ = 0;
        while (q.size() > 0) {
            dequeSumQ += q.dequeue();
        }
        StdOut.println("sum       = " + sum);
        StdOut.println("iterSumQ  = " + iterSumQ);
        StdOut.println("dequeSumQ = " + dequeSumQ);
        StdOut.println("iterSumQ + dequeSumQ == 2 * sum? " + (iterSumQ + dequeSumQ == 2 * sum));
    }
}
