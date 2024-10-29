package Exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Sort {
    
    public static void main(String[] arg){
        int[] a = {9, 5, 3, 1, 4, 7, 8};
        System.out.println(Arrays.toString(bubbleSort(a.clone())));
        System.out.println(Arrays.toString(selectionSort(a.clone())));
        System.out.println(Arrays.toString(insertionSort(a.clone())));
        System.out.println(Arrays.toString(countSort(a)));
        System.out.println(Arrays.toString(quickSort(a, 0, a.length - 1)));
        System.out.println(Arrays.toString(mergeSort(a)));
        System.out.println(Arrays.toString(radixSort(a, 1)));
        System.out.println(Arrays.toString(bucketSort(a)));
        System.out.println(Arrays.toString(shellSort(a)));
        System.out.println(Arrays.toString(heapSort(a)));
    }

    //              Compare Swap
    //best case:    O(n)    O(1)
    //worst case:   O(n^2)  O(n^2)
    static int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]){
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    //              Compare Swap
    //best case:    O(n^2)    O(1)
    //worst case:   O(n^2)  O(n)
    static int[] selectionSort(int[] a){
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++){
                if (a[j] < a[min]) {
                    min = j;
                }
            }

            if (i != min){
                int temp = a[i];
                a[i] = a[min];
                a[min] = temp;
            }
        }
        return a;
    }

    static int[] insertionSort(int[] a){
        for (int i = 1; i < a.length; i++) {
            int key = a[i];
            int j = i - 1;
            while (j>=0 && a[j] > key) {
                a[j+1] = a[j];
                j--;
            }
            a[j+1] = key;
        }
        return a;
    }

    static int[] quickSort(int[] a, int lo, int hi) {
        if (lo >= 0 && hi >=0 && lo < hi) {
            int p = partition(a, lo, hi);

            quickSort(a, lo, p - 1);
            quickSort(a, p + 1, hi);
        }

        return a;
    }

    static int partition(int[] a, int lo, int hi) {
        int pivot = a[hi];
        int i = lo - 1;
        for (int j = lo; j < hi; j++){
            if (a[j] <= pivot) {
                i++;
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }

        int temp = a[i + 1];
        a[i + 1] = a[hi];
        a[hi] = temp;

        return i + 1;
    }

    static int[] mergeSort(int[] a){
        if (a.length <= 1) return a;

        int[] left = new int[a.length/2];
        int[] right = new int[a.length - left.length];
        for (int i = 0; i < a.length; i++){
            if (i < a.length/2){
                left[i] = a[i];
            } else {
                right[i - left.length] = a[i]; 
            }
        }

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    static int[] merge(int[] left, int[] right){
        int[] result = new int[left.length + right.length];
        int i = 0;
        int r = 0;
        int l = 0;
        while (left.length > l && right.length > r){

            if (left[l] <= right[r]){
                result[i] = left[l];
                i++;
            } else {
                result[i] = right[r];
                r++;
            }
            l++;
        }
    
        while (l < left.length) {
            result[i] = left[l];
            l++;
            i++;
        }

        while (r < right.length) {
            result[i] = right[r];
            r++;
            i++;
        }
        return result;
    }

    static int[] radixSort(int[] a, int d){
        Queue<Integer>[] q = new Queue[10];
        for (int i = 0; i < 10; i++) {
            q[i] = new LinkedList<>();
        }

        int n = 1;
        for (int i = 0; i < d; i++) {
      
            for (int j = 0; j < a.length; j++) {
                int t = (a[j] / n) % 10; 
                q[t].add(a[j]); 
            }
            int k = 0;
            for (int j = 0; j < 10; j++) {
                while (!q[j].isEmpty()) {
                    a[k++] = q[j].poll();
                }
            }

            n *= 10;
        }

        return a; 
    }

    static int[] bucketSort(int[] a) {
        int[] code = hash(a);

        List[] buckets = new List[code[1]];
        for (int i = 0; i < code[1]; i++){
            buckets[i] = new ArrayList();
        }

        for (int i : a) {
            buckets[hash(i, code)].add(i);
        }

        for (List bucket : buckets) {
            Collections.sort(bucket);
        }

        int ndx = 0;

        for (List<Integer> innerBucket : buckets) {
            for (int v : innerBucket) {
                a[ndx++] = v;
            }
        }

        return a;
    }

    static int[] hash(int[] a){
        int m = a[0];
        for (int i = 1; i < a.length; i++) {
            if (m < a[i]) {
                m = a[i];
            }
        }
        return new int[] {m, (int) Math.sqrt(a.length)};
    }

    static int hash(int i, int[] code) {
        return (int) ((double) i / code[0] * (code[1] - 1));
    }

    static int[] shellSort(int[] a) {
        for (int gap = a.length/2; gap > 0; gap/=2) {
            for (int i = gap; i < a.length; i++) {
                int temp = a[i];
                int j;
                for (j = i; (j >= gap) && (a[j - gap] > temp); j -= gap) {
                    a[j] = a[j - gap];
                }
                a[j] = temp;
            }
        }
        return a;
    }

    static int[] heapSort(int[] a){

        int n = a.length;

        for (int i = n/2 - 1; i >= 0; i--) {
            heapify(a, n, i);
        }

        for (int i = n -1; i>=0; i--) {
            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;

            heapify(a, i, 0);
        }

        return a;
    }

    static void heapify(int[] a, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && a[l] > a[largest])
            largest = l;

        if (r < n && a[r] > a[largest])
            largest = r;

        if (largest != i) {
            int swap = a[i];
            a[i] = a[largest];
            a[largest] = swap;
        
            heapify(a, n, largest);
        }
    }

    static int[] countSort(int[] array) {
        int size = array.length;
        int[] output = new int[array.length + 1];

        int max = array[0];
        //find max
        for (int i = 1; i < size; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        int[] count = new int[max + 1];

        for (int i = 0; i < max; i++) {
            count[i] = 0;
        }

        for (int i = 0; i < size; i++) {
            count[array[i]]++;
        }

        for (int i = 1; i <= max; i++) {
            count[i] += count[i-1];
        }

        for (int i = size - 1; i >= 0; i--) {
            output[count[array[i]] - 1] = array[i];
            count[array[i]]--;
        }

        output = Arrays.copyOf(output, size);
        return output;
    }
}
