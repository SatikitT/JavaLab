package solutions.pack5_Postfix;

import java.util.LinkedList;
import java.util.Iterator;

public class MyQueueListWrap<T> implements Iterable<T> {
    private LinkedList<T> queue = new LinkedList<>();

    public void enqueue(T d) {
        queue.add(d);
    }

    public T dequeue() {
        return queue.poll();
    }

    public T top() {
        return queue.peek();
    }

    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<T> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < queue.size();
        }

        @Override
        public T next() {
            return queue.get(index++);
        }
    }

    public String dumpToString() {
        StringBuilder sb = new StringBuilder();
        for (T item : queue) {
            sb.append(item).append(" ");
        }
        return sb.toString().trim();
    }
}
