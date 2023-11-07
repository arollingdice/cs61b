package deque;

public interface Deque<T> {

    default boolean isEmpty() {
        return size() == 0;
    }
    int size();
    T get(int index);
    void printDeque();

    void addFirst(T i);
    void addLast(T i);
    T removeFirst();
    T removeLast();

}
