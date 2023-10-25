package edu.hw3;

import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;

public class BackwardIterator<T> implements Iterator<T> {
    Stack<T> items = new Stack<>();

    public BackwardIterator(Collection<T> collection) {
        for (T item : collection) {
            items.push(item);
        }
    }

    @Override
    public boolean hasNext() {
        return !items.isEmpty();
    }

    @Override
    public T next() {
        return items.pop();
    }
}
