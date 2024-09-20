package solutions.pack3_ArrAndList;

public class MyArrayBasic {
    protected int MAX_SIZE = 5;
    protected int data[] = new int[MAX_SIZE];
    protected int size = 0;
    
    public MyArrayBasic() {
        this.MAX_SIZE = 5;
        this.data = new int[MAX_SIZE];
    }

    public MyArrayBasic(int ... a){
        if (a == null){
            MAX_SIZE = 5;
            data = new int[MAX_SIZE];
            return;
        }
        MAX_SIZE = a.length;
        data = new int[MAX_SIZE];
        for (int element: a){
            add(element);
        }
    }

    public void add (int d) {
        if(isFull()) return;
        data[size++] = d;
    }

    public boolean isFull(){
        return size == MAX_SIZE;
    }

    public void insert(int d, int index) {
        for(int i = size; i > index; i--){
            data[i] = data[i-1];
        }
        data[index] = d;
        size++;
    }

    public int find(int d){
        for(int i = 0; i < size; i++){
            if(data[i]  == d){
                return i;
            }
        }
        return -1;
    }

    public int binarySearch(int d) {
        int a = 0, b = size - 1;
        while(a <= b){
            int m = (a+b)/2;
            if(data[m] == d) return m;
            if(d < data[m]) b = m-1;
            else a = m+1;
        }
        return -1;
    }

    public void delete(int index){
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
