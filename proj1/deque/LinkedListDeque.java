package deque;

public class LinkedListDeque<T> implements Deque<T> {
    private class Node {
        private T item;
        private Node next;
        private Node prev;

        Node(T i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    /**
     *  sentinel.first always points at the front of the list.
     *  sentinel.prev always points at the end of the list.
     */
    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public LinkedListDeque(T i) {
        size = 1;
        sentinel = new Node(null, null, null);
        Node p = new Node(i, sentinel, sentinel);
        sentinel.next = p;
        sentinel.prev = p;

    }

    @Override
    public void addFirst(T i) {
        Node p = new Node(i, sentinel.next, sentinel);
        sentinel.next.prev = p;
        sentinel.next = p;
        size++;
    }

    @Override
    public void addLast(T i) {
        Node p = new Node(i, sentinel, sentinel.prev);
        sentinel.prev.next = p;
        sentinel.prev = p;
        size++;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node oldFirst = sentinel.next;
        Node newFirst = oldFirst.next;

        sentinel.next = newFirst;
        newFirst.prev = sentinel;

        if (size == 1) {
            sentinel.prev = sentinel;
        }

        size--;

        return oldFirst.item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node oldLast = sentinel.prev;
        Node newLast = oldLast.prev;

        sentinel.prev = newLast;
        newLast.next = sentinel;

        if (size == 1) {
            sentinel.next = sentinel;
        }

        size--;

        return oldLast.item;
    }

    @Override
    public T get(int index) {
        Node p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;

    }

    public T getRucursive(int index) {
        if (index >= size) {
            return null;
        }
        return recursiveGetHelper(sentinel.next, index);

    }

    private T recursiveGetHelper(Node current, int index) {
        if (index == 0) {
            return current.item;
        }
        return recursiveGetHelper(current.next, index - 1);
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                System.out.println(this.get(i));
            }
            System.out.print(this.get(i)+ " ");
        }
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> ld = new LinkedListDeque<>();
        ld.addFirst(1);
        ld.addFirst(2);
        ld.addFirst(3);

    }


}
