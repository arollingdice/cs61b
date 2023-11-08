package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    /** Inner class to define iterator */
    private class ArrayDequeIterator implements Iterator<T> {
        private int current = (nextFirst + 1) % items.length;
        private int numItemsIterated = 0;

        @Override
        public boolean hasNext() {
            return numItemsIterated < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T item = items[current];
            current = movePointer(current, 1);
            numItemsIterated++;
            return item;
        }
    }

    /** Creates an empty list */
    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
        nextFirst = items.length - 1;
        nextLast = 0;
    }

    public int al() {
        return items.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        int logicFirst = nextFirst + 1;
        return items[(logicFirst + index) % items.length];
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
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
        int capacity = (int) Math.ceil(x * items.length);
        T[] newArray = (T[]) new Object[capacity];

        if (x >= 1) {
            System.out.println("expanding:" + items.length + "->" + capacity);
            for (int i = 0; i < items.length; i++) {
                newArray[i] = this.get(i);
            }
        } else {
            System.out.println("shrinking:" + items.length + "->" + capacity);
            for (int i = 0; i < capacity; i++) {
                newArray[i] = this.get(i);
            }
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
        if (size == items.length) {
            resize(2);
        }
        items[nextFirst] = i;
        nextFirst = movePointer(nextFirst, -1);
        size++;

    }

    @Override
    public void addLast(T i) {
        if (size  == items.length) {
            resize(2);
        }

        items[nextLast] = i;
        nextLast = movePointer(nextLast, 1);
        size++;
    }

    private void checkResize() {
        if ((size < items.length /4 + 1) && (items.length > 16)) {
            resize(0.5);
        }
    }
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        checkResize();
        T target = this.get(0);
        // move nextFirst to the right.
        nextFirst = movePointer(nextFirst, 1);
        // destroy the original target.
        items[nextFirst] = null;
        size--;
        return target;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        checkResize();

        T target = this.get(size - 1);
        // move nextFirst to the right.
        nextLast = movePointer(nextLast, -1);
        // destroy the original target.
        items[nextLast] = null;
        size--;
        return target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> other = (Deque<T>) o;
        if (this.size != other.size()) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            T item1 = this.get(i);
            T item2 = other.get(i);
            if (!item1.equals(item2)) {
                return false;
            }
        }

        return true;

    }


}
