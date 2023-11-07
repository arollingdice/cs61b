package deque;

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
}
