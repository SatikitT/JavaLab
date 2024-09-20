package solutions.pack10_BST;

public class TreeNode {
    int data;
    TreeNode left, right, parent;

    public TreeNode(int d) {
        data = d;
    }

    @Override
    public String toString() {
    // There are 4 cases
    // no child,
    // left-child-only,
    // right-child-only,
    // and both children
    /* your code */

    String output = "";

    if ( left == null ) {
        output += "null<-";
    } else {
        output += left.data + "<-";
    }

    output += data;
    
    if ( right == null ) {
        output += "->null";
    } else {
        output += "->" + right.data;
    }

    return output;
    }
}