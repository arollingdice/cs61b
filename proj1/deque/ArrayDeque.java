package deque;

/** Array based deque.
 *  @author Feiyun
 */

//         0 1  2 3 4 5 6 7
// items: [6 9 -1 2 0 0 0 0 ...]
// size: 5

/* Invariants:
 addLast: The next item we want to add, will go into position size
 getLast: The item we want to return is in position size - 1
 size: The number of items in the list should be size.
*/

public class ArrayDeque<Item> {
    private Item[] items;
    private int size;

    private int nextFirst;

    private int nextLast;

    /** Creates an empty list. */
    public ArrayDeque() {
        items = (Item[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    /**
     * Resizes the array to the target capacity while preserving deque order.
     * Existing items are transferred to the new array starting at the position
     * right after nextFirst. After resizing, nextLast points to the next
     * available position, and nextFirst is set to the end of the array,
     * enabling circular wrap-around on the next addition.
     *
     * @param capacity The target capacity for resizing.
     */
    private void resize(int capacity) {
        Item[] newItems = (Item[]) new Object[capacity];
        // 'nextFirst' points to the location
        // before the first element (due to circular array),
        // so we start copying from one position right next to 'nextFirst'.
        int oldIndex = plusOne(nextFirst);
        for (int i = 0; i < size; i ++) {
            newItems[i] = items[oldIndex];
            oldIndex = plusOne(oldIndex);
        }
        items = newItems;
        nextLast = size;
        nextFirst = capacity - 1;
    }

    /**
     * Effectively move the index one unit to the left in the array.
     *
     * If the index is currently at the start of the array (i.e., index is 0)
     * and minusOne() is called, the index is moved to the last position
     * of the array, reflecting the circular nature of the array.
     *
     * @param index The current index position.
     * @return the index after it is moved 1 unit to the left.
     */
    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }

    /**
     * Effectively move the index one unit to the right in the array.
     *
     * If the index is currently at the end of the array
     * (i.e., index is item.length - 1), and plusOne() is called,
     * the index is moved to the first position of the array, reflecting
     * the circular nature of the array.
     *
     * @param index The current index position.
     * @return the index after it is moved 1 unit to the right.
     */
    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

    /**
     * Adds item x to the beginning of the deque.
     *
     * The Items array resizes itself to a 2 times larger array
     * if the array is filled.
     *
     * @param x
     */
    public void addFirst(Item x) {
        if (size == items.length) {
            resize(2 * size);
        }

        items[nextFirst] = x;
        // nextFirst is moved 1 unit to the left with minusOne
        nextFirst = minusOne(nextFirst);
        size ++;

    }


    /**
     * Adds item x to the end of the deque.
     *
     * The Items array resizes itself to a 2 times larger array
     * if the array is filled.
     *
     * @param x
     */

    public void addLast(Item x) {
        if (size == items.length) {
            resize(2 * size);
        }

        items[nextLast] = x;
        // nextFirst is moved 1 unit to the right with plusOne
        nextLast = plusOne(nextLast);
        size ++;
    }

    /** Returns the item from the back of the list. */
    public Item getLast() {

        return this.get(size - 1);
    }

    /** Gets the ith item in the list (0 is the front). */
    public Item get(int i) {
        if (i >= size || i < 0 ) {
            return null;
        }

        int index = (nextFirst + 1 + i) % items.length;
        return items[index];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Deletes item from back of the list and
      * returns deleted item. */
    public Item removeLast() {
        Item x = getLast();
        nextLast = minusOne(nextLast);
        items[nextLast] = null;
        if (size > 0) {
            size --;
        }
        return x;
    }

    /**
     *  Removes the first item of the deque and returns it.
     * @return the first item in the deque.
     */
    public Item removeFirst() {
        Item x = this.get(0);
        nextFirst = plusOne(nextFirst);
        items[nextFirst] = null;
        if (size > 0) {
            size --;
        }

        return x;

    }

    public boolean isEmpty() {
        return size == 0;
    }

}
