package calculator.com;

public interface Stack<E> {
    void push(E item);
    E pop();
    E peek();
    boolean isEmpty();
    int size();
    void clear();
}
