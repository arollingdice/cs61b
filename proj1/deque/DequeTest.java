package deque;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DequeTest {

    /** ArrayDeques and LinkedListDeques with the same elements should be equal */
    @Test
    public void sameDequeTest() {
        Deque<String> ad1 = new ArrayDeque<>();
        Deque<String> ld1 = new LinkedListDeque<>();

        for (int i = 0; i < 100; i ++) {
            ad1.addLast("fuck this shit!!!");
            ld1.addLast("fuck this shit!!!");
        }

        for (int i = 0; i < 100; i ++) {
            assertTrue("ArrayDeques and LinkedListDeques" +
                    "with the same elmements should be equal", ad1.equals(ld1));
        }
    }
}
