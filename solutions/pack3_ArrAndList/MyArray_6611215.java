package solutions.pack3_ArrAndList;

public class MyArray_6611215 {
    protected int MAX_SIZE;
    protected int[] data;
    protected int size = 0;

    public MyArray_6611215() {
        this.MAX_SIZE = 100000;
        this.data = new int[MAX_SIZE];
    }

    public MyArray_6611215(int max) {
        this.MAX_SIZE = max;
        this.data = new int[MAX_SIZE];
        this.size = 0;
    }

    public void add(int d) {
        if (isFull()) expand();
        data[size++] = d;
    }

    public boolean isFull() {
        return size == MAX_SIZE;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void expandByK(int k) {
        MAX_SIZE += k;
        int[] newData = new int[MAX_SIZE];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    public void expand() {
        MAX_SIZE *= 2;
        int[] newData = new int[MAX_SIZE];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    public void insert(int d, int index) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Index out of bounds");
        if (isFull()) expand();
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = d;
        size++;
    }

    public int find(int d) {
        for (int i = 0; i < size; i++) {
            if (data[i] == d) {
                return i;
            }
        }
        return -1;
    }

    public int binarySearch(int d) {
        int a = 0, b = size - 1;
        while (a <= b) {
            int m = (a + b) / 2;
            if (data[m] == d) return m;
            if (d < data[m]) b = m - 1;
            else a = m + 1;
        }
        return -1;
    }

    public void delete(int index) {
        if (index < 0 || index >= size) return;
        data[index] = data[--size];
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
