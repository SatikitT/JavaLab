package solutions.pack9_Heap;

import java.util.Arrays;

public class MyMinHeap {
    int MAX_SIZE = 7;
    int heap[] = new int[MAX_SIZE];
    int size = 0;

    public void insert(int d) {
        int p = size++;
        heap[p] = d;
        int parent = (p - 1) / 2;
        while ((p > 0) && heap[p] < heap[parent]) {
            swap(p, parent);
            p = parent;
            parent = (p - 1) / 2;
        }
    }

    void swap(int a, int b) {
        heap[a] = heap[a] ^ heap[b];
        heap[b] = heap[a] ^ heap[b];
        heap[a] = heap[a] ^ heap[b];
    }

    public int remove() {
        int smallest = heap[0];
        heap[0] = heap[--size];
        int index = 0;
        while (true) {
            int left = (index * 2) + 1;
            if (left >= size) break;

            int right = (index * 2) + 2;
            if (right >= size) {
                if (heap[index] > heap[left])
                    swap(index, left);
                break;
            } 

            int smaller = heap[left] < heap[right] ? left : right;
            if (heap[smaller] < heap[index]){
                swap(smaller, index);
                index = smaller;
            } else {
                break;
            }
        }
        return smallest;
    }

    public int peek() {
        return heap[0];
    }

    public boolean isFull() {
        return size == MAX_SIZE;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        int count = 1;
        for (int i = 0; i <= size - 1; i++) {
            sb.append(heap[i]);
            if(i % count == 0){
                count*=2;
                count += i;
                sb.append("\n");
            }
        }
        return new String(sb);
    }

}