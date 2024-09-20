package solutions.pack7_Recursion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EqualSubsets {

    private static void testEqualSubsets() {
        int a [] = {1, 5, 11, 5};
        int b [] = {1, 5, 3};
        System.out.println(EqualSubsets.canPartition_Recurse(a));
        System.out.println(EqualSubsets.canPartition_Recurse(b));
        System.out.println(EqualSubsets.canPartition_Memoiz(a));
        System.out.println(EqualSubsets.canPartition_Memoiz(b));
        System.out.println(EqualSubsets.canPartition_DP(a));
        System.out.println(EqualSubsets.canPartition_DP(b));
    }

    public static boolean canPartition_Recurse(int [] arr) {
        int sum = Arrays.stream(arr).sum();
        if (sum % 2 != 0){
            return false;
        }
        return inner_recursion(arr, sum / 2, 0);
    }

    public static boolean inner_recursion(int[] arr, int sum, int index){
        if (sum == 0){
            return true;
        }
        
        if (sum < 0 || index == arr.length){
            return false;
        }

        return inner_recursion(arr, sum - arr[index], index + 1) || inner_recursion(arr, sum, index + 1);
    }

    public static boolean canPartition_Memoiz(int [] arr){
        int sum = Arrays.stream(arr).sum();
        if (sum % 2 != 0){
            return false;
        }
        Map<Integer, Integer> memo = new HashMap<>();
        return inner_recursion_memo(arr, sum / 2, 0, memo);
    }

    public static boolean inner_recursion_memo(int[] arr, int sum, int index, Map<Integer, Integer> mem){

        if (sum == 0){
            return true;
        }
        
        if (sum < 0 || index == arr.length){
            return false;
        }

        if(mem.get(sum) != null && mem.get(sum) == index){
            return false;
        }

        mem.put(sum, index);

        return inner_recursion(arr, sum - arr[index], index + 1) || inner_recursion(arr, sum, index + 1);
    }
    
    public static boolean canPartition_DP(int[] arr) {
        int sum = Arrays.stream(arr).sum();
        if (sum % 2 != 0) {
            return false;
        }
    
        sum /= 2;
        int n = arr.length;
        boolean subset[][] = new boolean[n + 1][sum + 1];
    
        for (int i = 0; i <= n; i++)
            subset[i][0] = true;

        for (int i = 1; i <= sum; i++)
            subset[0][i] = false;
    
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j < arr[i - 1])
                    subset[i][j] = subset[i - 1][j];
                if (j >= arr[i - 1])
                    subset[i][j] = subset[i - 1][j] || subset[i - 1][j - arr[i - 1]];
            }
        }
    
        return subset[n][sum];
    }
    

}
