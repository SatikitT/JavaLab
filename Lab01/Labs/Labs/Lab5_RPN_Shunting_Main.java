package Labs;

import java.util.Iterator;
import java.util.StringTokenizer;

import solutions.pack5_Postfix.*;

public class Lab5_RPN_Shunting_Main {
    static void task1() {
        MyStackA stack = new MyStackA();
        stack.push(1.0);
        stack.push(2.2);
        stack.push(4.4);
        stack.push(3.3);
        System.out.println(stack);
    }

    private static void testTokenizer(String toBeRPN) {
        StringTokenizer st = new StringTokenizer(toBeRPN);
        int i = 0;
        String t = "";
        while (st.hasMoreTokens()) {
            t = st.nextToken();
            if (MyRPN.isNumeric(t))
                System.out.println("Token " + i++ + " = " + t);
            else
                System.out.println("Token " + i++ + " = " + t + " is an opearator");
        }
    }
    static void task2(String postfixString) {
        //testTokenizer(postfixString);
        System.out.println(postfixString);
        System.out.println("= " + MyRPN.computeRPN(postfixString));

    }
    private static void task4() {
        System.out.println("-----MyQueueListWarp Tester-----");
        // MyQueueListWrap<String> queue = new MyQueueListWrap<>();
        MyQueueExtendsLinkedList<String> queue = new MyQueueExtendsLinkedList<>();
        queue.enqueue("1");
        queue.enqueue("3");
        queue.enqueue("5");
        queue.enqueue("7");
        System.out.println(queue.toString());
        System.out.println("Top " + queue.top());
        System.out.println("Last " + queue.getLast());
        System.out.println("Dequeue " + queue.dequeue());
        System.out.println("Dequeue " + queue.dequeue());
        System.out.println("Dequeue " + queue.dequeue());
        System.out.println("Dequeue " + queue.dequeue());
        queue.enqueue("9");
        System.out.println(queue);
        System.out.println("Testing Iterator");
        Iterator<String> iter = queue.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next());
        }
        System.out.println("\nTesting for-each");
        for (String ele : queue) {
            System.out.print(ele + " ");
        }
        System.out.println();
        System.out.println("----MyQueueListWrap Test End----");
    }
    
    public static void computeInfix(String infixString) {
        String postfixString = MyShuntingYard.infixToPostfix(infixString);
        double ans = MyRPN.computeRPN(postfixString);
        System.out.println(ans);
    }

    public static void main(String[] args) { 
        task1();
        // 3 1 - 4 5 + *
        String postfixString = "8 5 - 4 2 + 3 / *";
        task2(postfixString);
        task4();
        String infixString = "( 4 + 2 ) / 3 * ( 8 - 5 )";
        //expect 4 2 + 3 / 8 5 - *
        // if (args.length > 0)
        //     infixString = args[0];
        computeInfix(infixString);
    }    
}