import stdlib.StdOut;

public class Sample {
    // Entry point.
    public static void main(String[] args) {

        // Accept lo (int), hi (int), k (int), and mode (String) as command-line arguments
        int lo = Integer.parseInt(args[0]);
        int hi = Integer.parseInt(args[1]);
        int k = Integer.parseInt(args[2]);
        String mode = args[3];

        // threw an IllegalArgumentException("Illegal mode") if mode is different from “+” or “-”
        if (!(mode.equals("+") || mode.equals("-"))) {
            throw new IllegalArgumentException("Illegal mode");
        }
        // Create a random queue q containing integers from the interval [lo, hi].
        ResizingArrayRandomQueue<Integer> q = new ResizingArrayRandomQueue<Integer>();
        for (int i = lo; i <= hi; i++) {
            q.enqueue(i);
        }

        // mode is “-”, dequeue and write k integers from q to standard. output
        if (mode.equals("-")) {
            for (int i = 0; i < k; i++) {
                StdOut.println(q.dequeue());
            }
        }
        // If mode is “+” sample and write k integers from q to standard output.
        if (mode.equals("+")) {
            for (int i = 0; i < k; i++) {
                StdOut.println(q.sample());
            }

        }

    }
}
