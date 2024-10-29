package Exercises;

public class TreeNode {
    int data, h_left = -1, h_right = -1;
    TreeNode left, parent, right;

    public TreeNode (int d) {
        data = d;
    }

    @Override
    public String toString() {
        String output = "";

        output += left == null ? "null<- " : left.data + "<- ";
        output += data;
        output += right == null ? " ->null" : "-> " + right.data;
        
        return output;
    }
}
