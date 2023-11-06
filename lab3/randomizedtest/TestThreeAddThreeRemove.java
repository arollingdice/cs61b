package randomizedtest;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TestThreeAddThreeRemove {
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        for (int i = 1; i <= 3; i ++) {
            int toAdd = i + 3;
            correct.addLast(toAdd);
            broken.addLast(toAdd);
        }


        assertEquals(correct.size(), broken.size());

        assertEquals(correct.removeLast(), broken.removeLast());
        assertEquals(correct.removeLast(), broken.removeLast());
        assertEquals(correct.removeLast(), broken.removeLast());

    }
}
