package deque;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class ArrayDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        ArrayDeque<String> ad1 = new ArrayDeque<String>();

        assertTrue("A newly initialized LLDeque should be empty", ad1.isEmpty());
        ad1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, ad1.size());
        assertFalse("ad1 should now contain 1 item", ad1.isEmpty());

        ad1.addLast("middle");
        assertEquals(2, ad1.size());

        ad1.addLast("back");
        assertEquals(3, ad1.size());

        System.out.println("Printing out deque: ");
        ad1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        // should be empty
        assertTrue("ad1 should be empty upon initialization", ad1.isEmpty());

        ad1.addFirst(10);
        // should not be empty
        assertFalse("ad1 should contain 1 item", ad1.isEmpty());

        ad1.removeFirst();
        // should be empty
        assertTrue("ad1 should be empty after removal", ad1.isEmpty());
    }

    @Test
    /* Tests get method from a list */
    public void getItemTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(1);
        ad1.addLast(2);
        ad1.addLast(3);
        ad1.addLast(4);
        ad1.addLast(5);

        ArrayDeque<String> ad2 = new ArrayDeque<>();
        ad2.addFirst("I ");
        ad2.addLast("Love");
        ad2.addLast("My");
        ad2.addLast("Mom");
        ad2.addLast("And");
        ad2.addLast("Dad!\n");

        int item1 = ad1.get(0);
        String errorMsg1 = "Test list_1 : 1 2 3 4 5" + "\n";
        errorMsg1 += "  Bad item returned when getting item on index 0:\n";
        errorMsg1 += "list_1.get(0) returned " + item1 + "\n";
        errorMsg1 += "expect 1\n";


        assertEquals(errorMsg1, 1, item1);

        String item2 = ad2.get(4);
        String errorMsg2 = "Test list_2 : 'I Love My Mom And Dad!\\n'" + "\n";
        errorMsg2 += "  Bad item returned when getting item on index 4:\n";
        errorMsg2 += "list_2.get(4) returned " + item2 + "\n";
        errorMsg2 += "expect: \"And\"\n";
        assertEquals(errorMsg2, "And", item2);
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(3);

        ad1.removeLast();
        ad1.removeFirst();
        ad1.removeLast();
        ad1.removeFirst();

        int size = ad1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create ArrayDeques with different parameterized types*/
    public void multipleParamTest() {

        ArrayDeque<String>  ad1 = new ArrayDeque<String>();
        ArrayDeque<Double>  ad2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> ad3 = new ArrayDeque<Boolean>();

        ad1.addFirst("string");
        ad2.addFirst(3.14159);
        ad3.addFirst(true);

        String s = ad1.removeFirst();
        double d = ad2.removeFirst();
        boolean b = ad3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty ArrayDeque. */
    public void emptyNullReturnTest() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, ad1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, ad1.removeLast());

    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigADequeTest() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            ad1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            double target = (double) ad1.removeFirst();
            assertEquals("Should have the same value", i, target, 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            double target = (double) ad1.removeLast();
            assertEquals("Should have the same value", i, target, 0.0);
        }

    }


    @Test
    public void equalsTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 30000; i++) {
            ad1.addLast(i);
        }
        ArrayDeque<Integer> ad2 = new ArrayDeque<Integer>();
        for (int i = 0; i < 30000; i++) {
            ad2.addLast(i);
        }

        assertTrue("ad1 and ad2 should be equal", ad1.equals(ad2));
        for (int i = 0; i < 9; i++) {
            int x = ad1.removeLast();
        }
        assertTrue("after removing some elements "
                + "from ad1, ad1 and ad2 should not be equal", !ad1.equals(ad2));
    }


    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        for (int i = 0; i < 16; i++) {
            lld1.addLast(i);
        }

        assertEquals(16, lld1.size());

        for (double i = 0; i < 8; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 15; i > 8; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }

    }


    @Test
    /* Test Iterator */ public void iteratorTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        int[] data = {5, 23, 42};
        for (int i = 0; i < data.length; i++) {
            lld1.addLast(data[i]);
        }

        Iterator<Integer> aseer = lld1.iterator();
        int j = 0;
        while (aseer.hasNext()) {
            int i = aseer.next();
            assertEquals("should have the same value", i, data[j]);
            j += 1;
        }

        for (int i : lld1) {
            System.out.println(i);
        }
    }

    @Test
    public void checkResizeTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        for (int N = 8; N <= 64; N = N * N) {
            for (int i = 1; i <= N; i++) {
                lld1.addLast(i);
            }
            for (int i = N; i >= 1; i--) {
                lld1.removeLast();
            }
        }
    }
}
