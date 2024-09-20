package solutions.pack3_ArrAndList;

import java.util.Stack;

public class MyLinkedListTricky extends MyLinkedList {

    public void q1_rotate_counter_clockwise(int k){
        if (k > size()){
            return;
        }
        for(int i = 0; i < k; i++){
            Node q = new Node(head.data);
            Node p = head;
            while(p != null){
                //System.out.println(p.data);
                if(p.next == null){
                    p.data = q.data;
                    break;
                } else {
                    p.data = p.next.data;
                }
                p = p.next;
            } 
        }
    }

    public void q2_reverse() {
        Node prev = null;
        Node current = head;
        Node next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    public void q3_remove_dup() {
        Node p = head;
        while (p != null){
            Node q = p;
            while (q != null){    
                if(q.next != null && q.next.data == p.data){
                    q.next = q.next.next;
                }
                q = q.next;
            }
            p = p.next;
        }
    }

    public void q4_add_one() {
        q2_reverse();
        Node current = head;
        int carry = 1;
        while (current != null) {
            int sum = current.data + carry;
            carry = sum / 10;
            current.data = sum % 10;
            if (current.next == null && carry > 0) {
                current.next = new Node(carry);
                carry = 0;
            }
            current = current.next;
        }
        q2_reverse();
    }

    public boolean q5_isPalindrome() {
        if (head == null || head.next == null) {
            return true;
        }
        Node slow = head;
        Node fast = head;
        Stack<Integer> stack = new Stack<>();

        while (fast != null && fast.next != null) {
            stack.push(slow.data);
            slow = slow.next;
            fast = fast.next.next;
        }

        if (fast != null) {
            slow = slow.next;
        }

        while (slow != null) {
            if (stack.pop().intValue() != slow.data) {
                return false;
            }
            slow = slow.next;
        }

        return true;
    }
}
