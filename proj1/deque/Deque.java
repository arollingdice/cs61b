package deque;

public interface Deque<T> {

    boolean isEmpty();
    int size();
    T get(int index);
    void printDeque();

    void addFirst(T i);
    void addLast(T i);
    T removeFirst();
    T removeLast();
    boolean equals(Object o);

}
