package Labs;

import solutions.pack2.*;

public class Lab2_IsPrimeMain {
    public static void main(String[] args) {
        testIsPrime012();
        L2_IsPrimeInterface obj = new L2_IsPrime0();
        bench_isPrime(obj);
    }
    private static void testIsPrime012() {
        int N = 100;
        int count = 0;
        L2_IsPrimeInterface obj = new L2_IsPrime0();
        for (int n = 1; n < N; n++) {
            if (obj.isPrime(n)) count++;
        }
        System.out.println("Pi ("+ N + ")= " + count);
        count = 0;
        obj = new L2_IsPrime1();
        for (int n = 1; n < N; n++) {
            if (obj.isPrime(n)) count++;
        }
        System.out.println("Pi ("+ N + ")= " + count);
        count = 0;
        obj = new L2_IsPrime2();
        for (int n = 1; n < N; n++) {
            if (obj.isPrime(n)) count++;
        }
        System.out.println("Pi ("+ N + ")= " + count);
    }
    public static void bench_isPrime(L2_IsPrimeInterface obj) { 
        int your_cpu_factor = 1; /* increase by 10 times */
        int N = 100;
        int count = 0;
        // long start = 0;
        for (N = 100_000; N <= 1_000_000 * your_cpu_factor; N+= 100_000 * your_cpu_factor) {
            long start = System.currentTimeMillis();
            for (int n = 1; n < N; n++) {
                if (obj.isPrime(n)) count++;
            }
            long time = (System.currentTimeMillis() - start);
            System.out.println(N + "\t" + count + "\t" + time);
        }
    }
}