package solutions.pack9_Heap;

public class MyHeapSort extends MyMinHeap {

    public String sort(){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; size > 0; i++) {
            int d = remove();
            sb.append(d + " ");
        }
        return new String(sb);
    }
}
