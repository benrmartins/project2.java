// import dsa.LinkedQueue;
import dsa.LinkedStack;

import stdlib.StdIn;
// import stdlib.StdOut;

public class Sort {
    // Entry point.
    public static void main(String[] args) {
        LinkedDeque<String> q = new LinkedDeque<String>();
        LinkedStack<String> s = new LinkedStack<String>();
        String[] w = StdIn.readAllStrings();



    }

    // Returns true if v is less than w according to their lexicographic order, and false otherwise.
    private static boolean less(String v, String w) {
        return v.compareTo(w) < 0;
    }
}
