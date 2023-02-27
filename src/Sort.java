// import dsa.LinkedQueue;
import dsa.LinkedStack;

import stdlib.StdIn;
import stdlib.StdOut;

public class Sort {
    // Entry point.
    public static void main(String[] args) {

        // created a linked stack and deque
        LinkedDeque<String> q = new LinkedDeque<String>();
        LinkedStack<String> s = new LinkedStack<String>();

        while (!StdIn.isEmpty()) {
            // loops through a stores each character into the variable
            String word = StdIn.readString();
            // if the q is empty adds the first character
            if (q.size() == 0) {
                q.addFirst(word);
                continue;
            }
            // checks if the deque is not empty and adds it to the back if the word is less
            if (less(q.peekLast(), word) && !q.isEmpty()) {
                q.addLast(word);
                // checks if the deque is not empty and adds it to the front if the word is less
            } else if (less(word, q.peekFirst()) && !q.isEmpty()) {
                q.addFirst(word);
            } else {
                // remove words that are less than word from the front of q
                while (less(q.peekFirst(), word) && !q.isEmpty()) {
                    // store the word in a temporary stack s
                    s.push(q.removeFirst());
                }
                // add words from s also to the front of q
                q.addFirst(word);
                // write the words from q to standard output.
                while (!s.isEmpty()) {
                    q.addFirst(s.pop());
                }
            }
        }

        while (!q.isEmpty()) {
            StdOut.println(q.removeFirst());
        }

    }

    // Returns true if v is less than w according to their lexicographic order, and false otherwise.
    private static boolean less(String v, String w) {
        return v.compareTo(w) < 0;
    }
}
