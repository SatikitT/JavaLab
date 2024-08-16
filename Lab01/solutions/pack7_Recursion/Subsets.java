package solutions.pack7_Recursion;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    public static void printAllSubsets_Recursive(List<Integer> set){
        if (set.isEmpty()){
            return;
        }

        System.out.print("[");
        for (int i = 0; i < set.size(); i++){
            if(set.get(i) != null) {
                System.out.print(set.get(i));
            }
            if(i < set.size() - 1){
                System.out.print(",");
            }
        }
        System.out.print("]");

        for(int i = 0; i < set.size(); i++){
            List<Integer> tempSet = new ArrayList<Integer>(set);
            tempSet.remove(i);
            printAllSubsets_Recursive(tempSet);
        }
    }

    public static void printAllSubsets_DP(List<Integer> set){

    }

}
