package solutions.pack5_Postfix;


public class MyStackL {

    public class Node {
        String value;
        Node next;
    
        public Node(String d) {
            value = d;
            next = null;
        }
    }
    
    private Node top;

    public MyStackL() {
        top = null;
    }

    public void push(String d) {
        Node p = new Node(d);
        p.next = top; 
        top = p;
    }

    public String pop() {
        String d = top.value; 
        top = top.next; 
        return d;
    }

    public String peek() {
        return top.value;
    }

    public boolean isFull() {
        return false;
    }

    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Top->");
        Node temp = top;
        while (temp != null) {
            sb.append(temp.value).append("->");
            temp = temp.next;
        }
        sb.append("Bottom");
        return sb.toString();
    }
}
