package solutions.pack5_Postfix;

import java.util.LinkedList;

public class MyQueueExtendsLinkedList<T>
        extends LinkedList<T> {
    public void enqueue(T d) {
        this.add(d);
    }

    public T dequeue() {
        return this.poll();
    }

    public T top() {
        return this.peek();
    }
}
