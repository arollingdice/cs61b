/** Class that prints the Collatz sequence starting from a given number.
 *  @author Feiyun
 */
public class Collatz {

    /** Elegant implementation of nextNumber!
     *  Collatz number:
     *  if n is even, the next number is n / 2;
     *  if n is odd, the next number is 3 * n + 1;
     *  the sequence ends when n hits 1.
     * */
    public static int nextNumber(int n) {
        if(n % 2 == 0) n /= 2;
        else n = 3 * n + 1;

        return n;
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.print(n + " ");
        while (n != 1) {
            n = nextNumber(n);
            System.out.print(n + " ");
        }
        System.out.println();
    }
}

