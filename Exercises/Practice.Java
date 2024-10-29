package Exercises;

import java.util.Scanner;

public class Practice {

    public static void main(String[] args) {
        BST tree = new BST();
        tree.insert(2);
        tree.insert(1);
        tree.insert(9);
        tree.insert(7);
        tree.insert(4);
        tree.delete(7);
        tree.printInOrder();
    }
    
    public static void ex6(){
        int a,b;
        Scanner inputHandler = new Scanner(System.in);

        System.out.println("Input first number: ");
        a = inputHandler.nextInt(); 

        System.out.println("Input second number: ");
        b = inputHandler.nextInt();
         
        System.out.println(String.format("%d + %d = %d", a, b, a+b));
    }
}