package solutions.pack5_Postfix;

public class MyStackA {
    private int MAX_SIZE = 100;
    private double[] stack = new double[MAX_SIZE];
    private int top = 0;

    public void push(double d){
        stack[top++] = d;
    }

    public double pop() {
        return stack[--top];
    }

    public double top() {
        return stack[top-1];
    }

    public boolean isFull() {
        return top==MAX_SIZE;
    }

    public boolean isEmpty() {
        return top==0;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("top->");
        for (int i = top - 1; i >= 0; i--) {
            sb.append("[");
            sb.append(stack[i]);
            sb.append("]->");
        }
        sb.append("bottom");
        return new String(sb);
    }
}