package solutions.pack7_Recursion;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    public static void printAllSubsets_Recursive(List<Integer> set){
        if (set.isEmpty()){
            return;
        }

        List<Integer> emptyList = new ArrayList<>();
        printAllSubsets_Recursive(set, 0, emptyList);
    }

    public static void printAllSubsets_Recursive(List<Integer> set, int index, List<Integer> subset){
        if (index == set.size()){
            if(subset.size() == 0){
                System.out.println(subset);
                return;
            }
            System.out.print(subset + ", ");
            return;
        }
        subset.add(set.get(index));
        printAllSubsets_Recursive(set, index + 1, subset);
        subset.remove(subset.size() - 1);
        printAllSubsets_Recursive(set, index + 1, subset);

    }

    public static void printAllSubsets_DP(List<Integer> set){
        if(set.isEmpty()){
            System.out.println(set);
            return;
        }

        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<>());

        for(int i=0; i < set.size(); i++){

            //System.out.println(i);

            int n = subsets.size();

            //System.out.println("subsets: " + subsets);
            
            for(int j=0; j<n; j++){
                List<Integer> subset = new ArrayList<>(subsets.get(j));
                subset.add(set.get(i));
                //System.out.println("Subset:" + subset);
                subsets.add(subset);
            }

            //System.out.println(subsets);
        }

        System.out.println(subsets);
    }

}
