package solutions.pack9_Heap;

public class MyPriorityQueue implements MyQueueInterface{
    MyMinHeap heap = new MyMinHeap();

    public void enqueue(int d) {
        if (!isFull()){
            heap.insert(d);
        }
        
    }

    public int dequeue() {
        return heap.remove();
    }

    public int front() {
        return heap.peek();
    }


    public boolean isFull() {
        return heap.isFull();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }
    
    public String toString() {
        return heap.toString();
    }
}
