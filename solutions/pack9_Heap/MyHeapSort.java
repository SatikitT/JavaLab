package solutions.pack9_Heap;

public class MyHeapSort extends MyMinHeap {

    public String sort(){
        StringBuffer sb = new StringBuffer();
        while (size > 0) {
            int d = remove();
            sb.append(d).append(" ");
        }
        return new String(sb);
    }
}
