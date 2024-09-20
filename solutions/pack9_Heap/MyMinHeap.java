package solutions.pack9_Heap;

public class MyMinHeap {
    int MAX_SIZE = 6;
    int heap[] = new int[MAX_SIZE];
    int size = 0;

    public void insert(int d) {
        int p = size++;
        heap[p] = d;
        int parent = (p - 1) / 2;
        while ((p > 0) && (heap[p] < heap[parent])) {
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
        int d = heap[0];
        heap[0] = heap[--size];
        heap[size] = d; // keep root value at the unused space int p = 0;
        int p =0;
        while (true) {
            int left = 2 * p + 1;
            if (left >= size) break; // no child int right = 2*p+2;
            int right = 2*p+2;
            if (right == size) { // one child
                if (heap[p] > heap[left])
                    swap(p, left);
                break; // no more child,
                // nothing to do
            } else { // two childs
                int q = heap[left] < heap[right] ? left : right;
                if (heap[p] > heap[q])
                    swap(p, q);
                else
                    break;
                p = q;
            } // } end while return d;
        }
        return d;
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