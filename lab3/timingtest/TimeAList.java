package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        AList<Integer> toTest = new AList<>();
        AList<Integer> nSize = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> ops = new AList<>();

        for (int i = 1; i <= 1000; i ++) {
            toTest.addLast(1);
        }
        times.addLast(0.00);
        ops.addLast(1000);
        nSize.addLast(1000);

        for (int i = 1; i <= 6; i ++) {
            Stopwatch sw = new Stopwatch();
            int curSize = toTest.size();
            for (int j = 1; j <= curSize; j ++) {
                toTest.addLast(1);
            }
            double timesInSeconds = sw.elapsedTime();
            ops.addLast(toTest.size());
            nSize.addLast(toTest.size());
            times.addLast(timesInSeconds);
        }

        printTimingTable(nSize, times, ops);

    }
}
