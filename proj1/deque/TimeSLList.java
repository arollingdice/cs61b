package deque;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(ArrayList<Integer> Ns, ArrayList<Double> times, ArrayList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        ArrayList<Integer> Ns = new ArrayList<>();
        ArrayList<Integer> ops = new ArrayList<>();
        ArrayList<Double> time = new ArrayList<>();

        int M = 100000;
        for (int i = 1; i <= 1000; i ++) {
            lld.addLast(1);
        }

        for (int i = 1; i <= 14; i ++) {
            int curSize = lld.size();
            Ns.add(curSize);
            for (int j = 1; j <= curSize; j ++) {
                lld.addLast(1);

            }

            Stopwatch sw = new Stopwatch();
            for (int k = 1; k <= M; k ++) {
                lld.get(lld.size());
            }
            double timeInSeconds = sw.elapsedTime();
            time.add(timeInSeconds);
            ops.add(M);
        }

        printTimingTable(Ns, time, ops);
    }

}
