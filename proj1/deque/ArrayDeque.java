package deque;

public class ArrayDeque<T> implements Deque<T> {
    private int size;
    private int nextFirst;

    private int nextLast;
    private T[] items;

    /** Creates an empty list */
    public ArrayDeque() {
        size = 0;
        items = (T[])new Object[8];
        nextFirst = items.length - 1;
        nextLast = 0;
    }

    /** Creates a list of size x */
    public ArrayDeque(int x) {
        size = x;
        items = (T[])new Object[x + 1];
        nextFirst = 4;
        nextLast = 6;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }
    public int arrayLength() {
        return items.length;
    }

    @Override
    public T get(int index) {
        int logicFirst = nextFirst + 1;
        return items[(logicFirst + index) % items.length];
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i ++) {
            if (i == size - 1) {
                System.out.println(this.get(i));
                return;
            }
            System.out.print(this.get(i) + " ");
        }

    }

    /**
     *  when the size is at capacity,
     *  Resizes up the underlying array by x (x > 0) times;
     *
     *  when there is too many unused memory boxes in the array,
     *  Shrink the size of underlying array by x times
     *  to maintain a good usage factor(size / item_length > 25%)
     * */
    private void resize(double x) {
        int capacity = (int)(x * items.length);
        T[] newArray = (T[])new Object[capacity];
        int length = items.length;
        for (int i = 0; i < items.length; i ++) {
                newArray[i] = this.get(i);
        }
        items = newArray;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    private int movePointer(int pointer, int offset) {
        return (pointer + offset + items.length) % items.length;
    }
    @Override
    public void addFirst(T i) {
        if (size == items.length) resize(2);
        items[nextFirst] = i;
        nextFirst = movePointer(nextFirst,-1);
        size ++;

    }

    @Override
    public void addLast(T i) {
        if (size  == items.length) resize(2);
        items[nextLast] = i;
        nextLast = movePointer(nextLast, 1);
        size ++;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) return null;
        double usageFactor = size * 1.0 / items.length;
        while (usageFactor < 0.25 && usageFactor > 0) resize(0.1);
        T target = this.get(0);
        // move nextFirst to the right.
        nextFirst = movePointer(nextFirst, 1) ;
        // destroy the original target.
        items[nextFirst] = null;
        size --;
        return target;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) return null;
        double usageFactor = size * 1.0 / items.length;
        int i = 0;
        while (usageFactor < 0.25 && usageFactor > 0) {
            resize(0.1);
            i ++;
        }

        T target = this.get(size - 1);
        // move nextFirst to the right.
        nextLast = movePointer(nextLast, -1);
        // destroy the original target.
        items[nextLast] = null;
        size --;
        return target;
    }
}
