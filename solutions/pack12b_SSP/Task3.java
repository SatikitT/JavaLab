package solutions.pack12b_SSP;

import java.util.*;

public class Task3 {

    public class State {
        private final int[] array;
    
        public State(int[] array) {
            this.array = array.clone();
        }
    
        public int[] getArray() {
            return array.clone();
        }
    
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            State state = (State) obj;
            return Arrays.equals(array, state.array);
        }
    
        @Override
        public int hashCode() {
            return Arrays.hashCode(array);
        }
    }

    public int stateSpace(int[] beginState, int[] goalState) {

        InterfaceSwapAdjacentPairs swapPairs = (State s) -> {
            int[] arr = s.getArray();
            int[] newArr = arr.clone();
            for (int i = 0; i < newArr.length - 1; i += 2) {
                int temp = newArr[i];
                newArr[i] = newArr[i + 1];
                newArr[i + 1] = temp;
            }
            return newArr;
        };

        InterfaceSwapCorrespondingHalves swapHalves = (State s) -> {
            int[] arr = s.getArray();
            int n = arr.length;
            if (n % 2 != 0) {
                return arr;
            }
            int[] newArr = arr.clone();
            for (int i = 0; i < n / 2; i++) {
                int temp = newArr[i];
                newArr[i] = newArr[i + n / 2];
                newArr[i + n / 2] = temp;
            }
            return newArr;
        };

        Queue<State> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();

        State begin = new State(beginState);
        State goal = new State(goalState);

        queue.offer(begin);
        visited.add(begin);
        int operations = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                State current = queue.poll();
                if (current.equals(goal)) {
                    return operations;
                }

                int[] swappedPairsArr = swapPairs.swapAdjacentPairs(current);
                State swappedPairs = new State(swappedPairsArr);
                if (!visited.contains(swappedPairs)) {
                    visited.add(swappedPairs);
                    queue.offer(swappedPairs);
                }

                int[] swappedHalvesArr = swapHalves.swapCorrespondingHalves(current);
                State swappedHalves = new State(swappedHalvesArr);
                if (!visited.contains(swappedHalves)) {
                    visited.add(swappedHalves);
                    queue.offer(swappedHalves);
                }
            }
            operations++;
            if (operations > 1000) {
                break;
            }
        }

        return -1;
    }
}
