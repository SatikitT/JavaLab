package Exercises;

import java.util.LinkedList;
import java.util.Queue;

import javax.management.QueryEval;

public class Sortp {
    int[] bubbleSort(int[] arr) {
        boolean sorted = false;
        int len = arr.length;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < len - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    sorted = false;
                }
            }
            len--;
        }
        return arr;
    }

    int[] selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;

            for (int j = min + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }

            if (min != i) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
        return arr;
    }

    int[] insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
        return arr;
    }

    int[] quickSort(int[] arr, int low, int high){
        if (low < high){
            int p = partition(arr, low, high);

            quickSort(arr, low, p-1);
            quickSort(arr, p+1, high);
        }
        return arr;
    }

    int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        
        return i + 1;
    }

    int[] mergeSort(int[] arr) {
        if (arr.length <= 1) return arr;
        int[] left = new int[arr.length / 2];
        int[] right = new int[arr.length - left.length];

        for (int i = 0; i < arr.length; i++) {
            if (i < left.length) {
                left[i] = arr[i];
            } else {
                right[i - left.length] = arr[i];
            }
        }

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, r = 0, l = 0;
        while (l < left.length || r < right.length) {
            if (left[l] < right[r]) {
                result[i++] = left[l++];
            } else {
                result[i++] = right[r++];
            }
        }

        while (l < left.length) {
            result[i++] = left[l++];
        }

        while (r < right.length) {
            result[i++] = right[r++];
        }

        return result;
    }

    int[] radixSort(int[] arr, int digit) {
        Queue<Integer>[] queue = new Queue[10];
        for (int i = 0; i < 10; i++) {
            queue[i] = new LinkedList<>();
        }
        int n = 1;
        for (int i = 1; i <= digit; i++) {

            for (int j = 0; j < arr.length; j++){
                queue[arr[j]/n % 10].add(arr[j]);
            }

            int k = 0;
            for (int j = 0; j < 10; j++){
                while(!queue[j].isEmpty()){
                    arr[k++] = queue[j].poll();
                }
            }
            n*=10;
        }
        return arr;
    }

}
