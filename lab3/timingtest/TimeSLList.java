package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
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
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        SLList<Integer> testSLL = new SLList<>();
        AList<Integer> Ns = new AList<>();
        AList<Integer> ops = new AList<>();
        AList<Double> time = new AList<>();

        int M = 10000;
        for (int i = 1; i <= 1000; i ++) {
            testSLL.addLast(1);
        }

        for (int i = 1; i <= 8; i ++) {
            int curSize = testSLL.size();
            Ns.addLast(curSize);
            for (int j = 1; j <= curSize; j ++) {
                testSLL.addLast(1);
            }

            Stopwatch sw = new Stopwatch();
            for (int k = 1; k <= M; k ++) {
                testSLL.getLast();
            }
            double timeInSeconds = sw.elapsedTime();
            time.addLast(timeInSeconds);
            ops.addLast(M);
        }

        printTimingTable(Ns, time, ops);
    }

}
