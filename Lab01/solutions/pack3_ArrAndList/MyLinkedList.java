package solutions.pack3_ArrAndList;

public class MyLinkedList {

    public class Node {
        int data; 
        Node next; 
        public Node(int d) {
        data = d;
        }

        public String toString() {
            return String.valueOf(data);
        }
    } 
    
    Node head = null;

    public int getAt(int i) {
        Node p = head;
        while(i>0) {
            p = p.next; i--;
        }
        return p.data;
    }

    public void setAt(int d, int i) {
        Node p = head;
        while(i>0) {
            p = p.next; i--;
        }
        p.data = d;
    }

    public void add(int d) {
        Node p = new Node(d); 
        p.next = head;
        head = p;
    }

    public void add(int [] d){
        for (int i = d.length - 1; i > -1; i--){
            add(d[i]);
        }
    }

    public int size(){
        Node p = head;
        int i = 0;
        while(p!=null) {
            i++;
            p = p.next;
        }
        return i;
    }

    public void insert(int d) {
        Node q = new Node(d);
        Node p = head;
        while(p!=null) {    
            if (p.data < d && p.next.data > d){
                q.next = p.next; 
                p.next = q;
                break;
            }
            if (d < p.data){
                Node a = new Node(p.data);
                a.next = p.next;       
                p.next = a;   
                p.data = d;      
                break;
            }
            p = p.next;
        }
    }

    public void insert(int[] d) {
        for (int i : d){
            if(head == null || head.next == null){
                add(i);
            } else {
                insert(i);
            }
        }
    }

    public void delete(int d) {
        Node t = new Node(0);
        t.next = head; 
        Node p = t;
        while( (p.next!=null) && (p.next.data!=d) ) {
            p = p.next;
        }
        if(p.next!=null) {
            p.next = p.next.next; 
        }
        head = t.next;
    }

    public int find(int d) {
        Node p = head;
        int i = 0;
        while(p!=null) {
            if (p.data==d) 
                return i; 
            p = p.next;
            i++;
        }
        return -1;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("head "); Node p = head;
        while(p!=null) {
        sb.append("--> ["); sb.append(p.data); sb.append("] ");
        p = p.next; }sb.append("-> null"); return new String(sb);
        }
    }