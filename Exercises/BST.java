package Exercises;

public class BST {
    TreeNode root;

    public BST() {
        root = null;
    }

    TreeNode search(int d) {
        if (root == null) return null;
        TreeNode current = root;
        while(current != null) {
            if (d < current.data) {
                current = current.left;
            } else if (d > current.data) {
                current = current.right;
            } else {
                return current;
            }
        }
        return current;
    }

    void insert (int d) {
        TreeNode newNode = new TreeNode(d);
        if (root == null)
            root = newNode;
        
        TreeNode current = root;
        while (current != null) {
            if (d < current.data) {
                if (current.left == null) {
                    current.left = newNode;
                    newNode.parent = current;
                } else {
                    current = current.left;
                }
            } else {
                if (current.right == null) {
                    current.right = newNode;
                    newNode.parent = current;
                } else {
                    current = current.right;
                }
            }
        }
    }

    public void delete(int d) {
        if (root == null) 
            return;

        TreeNode current = root;
        while (true) {
            if (d < current.data) {
                current = current.left;
            } else if (d > current.data) {
                current = current.right;
            } else {
                if (current.right == null || current.left == null){
                    TreeNode temp = current.right != null ? current.right : current.left;
                    
                    if (current.parent == null) {
                        root = temp;
                    } else if (current.parent.right == current) {
                        current.parent.right = temp;
                    } else {
                        current.parent.left = temp;
                    }

                    if (temp != null)
                        temp.parent = current.parent;
                } else {
                    TreeNode temp = findMax(current.left);
                    delete(temp.data);
                    int tempData = temp.data;
                    current.data = tempData;
                }
                break;
            }
        }
    }

    TreeNode findMax(TreeNode n) {
        if (n.right == null) return n;
        return findMax(n.right);
    }

    public int height(){
        return heightRecursive(root);
    }

    int heightRecursive(TreeNode p) {
        if (p == null) 
            return 0;
        int right = heightRecursive(p.right);
        int left = heightRecursive(p.left);
        if (left > right)
            return left + 1;
        else 
            return right + 1;
    }

    public int count() {
        return countRecursive(root);
    }

    public int countRecursive(TreeNode q){
        if(q == null) {
            return 0;
        }
        return countRecursive(q.left) + countRecursive(q.right) + 1;
    }

    public void printInOrder(){
        printInOrderRecursive(root);
    }

    public void printInOrderRecursive(TreeNode p){
        if(p == null) return;
        printInOrderRecursive(p.left);
        System.out.print(p.data + " ");
        printInOrderRecursive(p.right);
    }

    
}
