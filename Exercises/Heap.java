package Exercises;

public class Heap {
    int MAX_SIZE = 100;
    int[] heap = new int[MAX_SIZE];
    int size = 0;

    void swap(int a, int b){
        int temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    void insert(int n) {
        int p = size++;
        heap[p] = n;
        int parent = (n-1)/2;
        while(p > 0 && heap[parent] > heap[p]) {
            swap(p, parent);
            p = parent;
            parent = (p-1)/2;
        }
    }

    int delete(int n){
        int d = heap[0];
        heap[0] = heap[--size];
        int p = 0;
        while(true){
            int left = (p*2) + 1;
            if (left >= size) break;

            int right = (p*2) + 2;
            int smaller = right < size && heap[right] < heap[left] ? right : left;
            if (heap[p] <= heap[smaller]) break;

            swap(smaller, p);
            p = smaller;
        }
        return d;
    }
}
