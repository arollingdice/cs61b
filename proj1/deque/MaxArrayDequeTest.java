package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;

public class MaxArrayDequeTest {

    // A helper Comparator class for tests
    private static class IntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return a.compareTo(b);
        }
    }

    private static class StringLengthComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return Integer.compare(a.length(), b.length());
        }
    }

    // Test the constructor
    @Test
    public void testConstructor() {
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(new IntComparator());
        assertTrue(deque.isEmpty());
    }
    @Test
    public void removeTest() {
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();

        arrayDeque.addFirst("front");
        arrayDeque.addLast("middle");
        arrayDeque.addLast("back");

        assertEquals("Should remove last item", "back", arrayDeque.removeLast());
        assertEquals("Should remove first item", "front", arrayDeque.removeFirst());

        assertEquals("Should have size 1", 1, arrayDeque.size());
    }

    // Test adding elements and retrieving the max
    @Test
    public void testMax() {
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(new IntComparator());
        deque.addLast(3);
        deque.addLast(1);
        deque.addLast(4);
        deque.addLast(1);
        deque.addLast(5);

        assertEquals(Integer.valueOf(5), deque.max()); // Test without a Comparator
        assertEquals(Integer.valueOf(5), deque.max(new IntComparator())); // Test with a Comparator
    }

    // Test with different data types and Comparator
    @Test
    public void testMaxWithDifferentComparator() {
        MaxArrayDeque<String> deque = new MaxArrayDeque<>(new StringLengthComparator());
        deque.addLast("hello");
        deque.addLast("world");
        deque.addLast("!");

        assertEquals("hello", deque.max()); // Should find "hello" as the longest string
    }

    // ... other tests for methods like addFirst, addLast, max(Comparator<T> c), etc.
    // Test taken from exuanbo
    // https://github.com/exuanbo/cs61b-sp21

    @Test
    public void addTest() {
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();

        assertTrue("Should be empty", arrayDeque.isEmpty());

        arrayDeque.addFirst("front");
        assertEquals("Should have size 1", 1, arrayDeque.size());

        arrayDeque.addLast("middle");
        assertEquals("Should have size 2", 2, arrayDeque.size());

        arrayDeque.addLast("back");
        assertEquals("Should have size 3", 3, arrayDeque.size());

        System.out.println("Printing out deque: ");
        arrayDeque.printDeque();
    }

    @Test
    public void addWithResizingTest() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

        for (int i = 0; i < 20; i++) {
            arrayDeque.addLast(i);
        }

        assertEquals("Should have size 20", 20, arrayDeque.size());
    }

    @Test
    public void addBigAmountTest() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

        int M = 1000000;

        for (int i = 0; i < M; i++) {
            arrayDeque.addLast(i);
        }

        assertEquals("Should have size 1000000", M, arrayDeque.size());
    }


    @Test
    public void removeWithResizingTest() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

        for (int i = 0; i < 20; i++) {
            arrayDeque.addLast(i);
        }

        for (int i = 0; i < 20; i++) {
            assertEquals("Should be equal", i, (int) arrayDeque.removeFirst());
        }

        assertTrue("Should be empty", arrayDeque.isEmpty());

        for (int i = 0; i < 20; i++) {
            arrayDeque.addLast(i);
        }

        assertEquals("Should have size 20", 20, arrayDeque.size());
    }

    @Test
    public void removeBigAmountTest() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

        int M = 1000000;

        for (int i = 0; i < M; i++) {
            arrayDeque.addLast(i);
        }

        assertEquals("Should have size 1000000", M, arrayDeque.size());

        for (int i = 0; i < M; i++) {
            assertEquals("Should be equal", i, (int) arrayDeque.removeFirst());
        }

        assertTrue("Should be empty", arrayDeque.isEmpty());
    }

    @Test
    public void getTest() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

        for (int i = 0; i < 20; i++) {
            arrayDeque.addLast(i);
        }

        for (int i = 0; i < 20; i++) {
            assertEquals("Should be equal", i, (int) arrayDeque.get(i));
        }

        assertNull("Should be null when index out of bound", arrayDeque.get(20));
    }

    @Test
    public void getBigAmountTest() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

        int M = 1000000;

        for (int i = 0; i < M; i++) {
            arrayDeque.addLast(i);
        }

        for (int i = 0; i < M; i++) {
            assertEquals("Should be equal", i, (int) arrayDeque.get(i));
        }
    }

    @Test
    public void iteratorTest() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

        for (int i = 0; i < 20; i++) {
            arrayDeque.addLast(i);
        }

        int index = 0;
        for (int item : arrayDeque) {
            assertEquals("Should be equal", index, item);
            index += 1;
        }
    }

    @Test
    public void equalsTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ArrayDeque<Integer> ad2 = new ArrayDeque<>();

        ad1.addLast(0);
        ad2.addLast(0);
        assertEquals(ad1, ad2);

        ad1.addLast(1);
        assertNotEquals(ad1, ad2);

        ad2.addLast(2);
        assertNotEquals(ad1, ad2);
    }

    @Test
    public void randomizedTest() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

        int N = 1000000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 6);
            if (operationNumber == 0) {
                int randVal = StdRandom.uniform(0, 100);
                arrayDeque.addFirst(randVal);
            } else if (operationNumber == 1) {
                int randVal = StdRandom.uniform(0, 100);
                arrayDeque.addLast(randVal);
            } else if (arrayDeque.size() == 0) {
                assertTrue(arrayDeque.isEmpty());
            } else if (operationNumber == 2) {
                assertTrue(arrayDeque.size() > 0);
            } else if (operationNumber == 3) {
                arrayDeque.removeFirst();
            } else if (operationNumber == 4) {
                arrayDeque.removeLast();
            } else if (operationNumber == 5) {
                int randIndex = StdRandom.uniform(0, arrayDeque.size());
                arrayDeque.get(randIndex);
            }
        }
    }
}
