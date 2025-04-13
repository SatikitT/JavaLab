package Exercises;

import java.util.Scanner;

public class Practice {

    public static void main(String[] args) {
        System.out.println(reverseString("aek"));
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

    static String reverseString(String text) {
        if (text.length() == 0) 
            return text;

        return reverseString(text.substring(1)) + text.charAt(0);
    }   
}