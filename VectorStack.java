package calculator.com;

import java.util.Vector;

public class VectorStack<E> implements Stack<E> {
    private Vector<E> stack;

    public VectorStack() {
        stack = new Vector<>();
    }

    @Override
    public void push(E item) {
        stack.add(item);
    }

    @Override
    public E pop() {
        if (isEmpty()) throw new RuntimeException("Stack vacío");
        return stack.remove(stack.size() - 1);
    }

    @Override
    public E peek() {
        if (isEmpty()) throw new RuntimeException("Stack vacío");
        return stack.lastElement();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public void clear() {

    }
}
