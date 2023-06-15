package deque;

/** A LinkedListDeque is a doubly linked list implemented using
 * a singly linked list under hood.
 *
 * @author Feiyun
 * */

public class LinkedListDeque<T> {

    /** An IntNode has a previous and next pointer. */
    private class IntNode {
        public T item;
        public IntNode prev;
        public IntNode next;

        public IntNode(T i, IntNode n) {
            item = i;
            next = n;
            if (n == null) {
                prev = null;
            } else {
                prev = n.prev;
            }
        }

    }

    /* The first item (if it exists) is at sentinel.next. */
    private IntNode sentinel;
    private int size;

    /**
     * Creates an empty deque.
     * Sentinel node points to itself.
     * Size is set to 0.
     */
    public LinkedListDeque() {
        sentinel = new IntNode(null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /**
     * Creates a deque with node x. Size is set to 1.
     * @param x
     */
    public LinkedListDeque(T x) {
        sentinel = new IntNode(null, null);
        sentinel.next = new IntNode(x, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    /**
     * Return the size of the deque.
     * @return size of the deque.
     * @see this.size
     */
    public int size() {
        return size;
    }

    /**
     * Checks if deque is empty
     *
     * @return true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Adds a node x to the beginning of the deque.
     * x is never null.
     *
     * @param x
     */
    public void addFirst(T x) {
        IntNode newNode = new IntNode(x, null);
        IntNode oldFirstNode = sentinel.next;

        newNode.prev = sentinel;
        newNode.next = sentinel.next;

        oldFirstNode.prev = newNode;

        sentinel.next = newNode;
        size ++;

    }

    /**
     * Adds a node x at the end of the deque.
     * x is never null.
     *
     * @param x
     */
    public void addLast(T x) {
        IntNode newNode = new IntNode(x, null);
        IntNode oldLastNode = sentinel.prev;

        newNode.prev = oldLastNode;
        newNode.next = sentinel;

        oldLastNode.next = newNode;

        sentinel.prev = newNode;
        size ++;

    }

    /**
     * Returns item at index t. If index is illegal, returns null.
     * @param t an integer index number
     * @return item's value at index t.
     */
    public T get(int t) {
        IntNode pointer = sentinel;
        if (t < 0 || t >= size) {
            return null;
        }
        int i = 0;
        while(i <= t) {
            pointer = pointer.next;
            i ++;
        }
        return pointer.item;
    }

    /**
     * Returns the first node of the deque.
     * If deque is empty, returns null.
     *
     * @return first node.
     */
    public T removeFirst() {
        IntNode first = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        if(size > 0) {
            size --;
        }

        return first.item;

    }

    /**
     * Returns the last node of the deque.
     * If deque is empty, returns null.
     *
     * @return last node.
     */
    public T removeLast() {
        IntNode last = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.prev.next = sentinel;
        if(size > 0){
            size --;
        }

        return last.item;
    }

    /**
     * Prints the items in the deque from first to last,
     * separated by a space.
     *
     * Once all the items have been printed, print out a
     * new line.
     */
    public void printDeque() {
        IntNode pointer = sentinel;
        pointer = pointer.next;
        while(pointer.next != sentinel) {
            System.out.print(pointer.item + " ");
            pointer = pointer.next;
        }

        System.out.println(pointer.item);
    }
}
