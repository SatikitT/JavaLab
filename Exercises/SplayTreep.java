package Exercises;

public class SplayTreep {
    TreeNode root;

    TreeNode search(int d) {
        TreeNode current = root;
        while(current != null) {
            if (d < current.data) {
                current = current.left;
            } else if (d > current.data) {
                current = current.right;
            } else {
                splay(current);
                return current;
            }
        }
        return null;
    }

    public void delete(int key) {
        TreeNode n = search(key);
        if (n == null) return;

        splay(n);
        if (n.left != null) {
            TreeNode leftTree = n.left;
            n.left.parent = null;
            if (n.right != null){
                TreeNode rightTree = n.right;
                n.right.parent = null;

                root = leftTree;
                TreeNode max = findMax(leftTree);
                splay(max);
                max.right = rightTree;
                rightTree.parent = max;
            } else {
                root = leftTree;
            }
        } else if (n.right != null){
            root = n.right;
            n.right.parent = null;
        } else {
            root = null;
        }
    }

    TreeNode findMax(TreeNode n) {
        if (n.right == null) return n;
        return findMax(n.right);
    }

    void splay(TreeNode n) {
        if (n == null) return;
        while (n.parent != null){
            if (n.parent.parent == null) {
                if (n.parent.left == n) {
                    rotateRight(n.parent);
                } else {
                    rotateLeft(n);
                }
            } else if (n.parent.left == n && n.parent.parent.left == n.parent ) {
                rotateRight(n.parent.parent);
                rotateRight(n.parent);
            } else if (n.parent.right == n && n.parent.parent.right == n.parent) {
                rotateLeft(n.parent.parent);
                rotateLeft(n.parent);
            } else if (n.parent.right == n && n.parent.parent.left == n.parent){
                rotateLeft(n.parent);
                rotateRight(n.parent);
            } else {
                rotateLeft(n.parent);
                rotateRight(n.parent);
            }
        }
    }

    void rotateRight(TreeNode n) {
        TreeNode newRoot = n.left;
        TreeNode rightSubTree = newRoot.right;

        newRoot.right = n;
        n.left = rightSubTree;

        if (rightSubTree != null)
            rightSubTree.parent = n;
        
        newRoot.parent = n.parent;
        n.parent = newRoot;

        if (newRoot.parent == null) {
            root = newRoot;
        } else if (newRoot.parent.left == n) {
            newRoot.parent.left = newRoot;
        } else {
            newRoot.parent.right = newRoot;
        }
    }

    void rotateLeft(TreeNode n) {
        TreeNode newRoot = n.right;
        TreeNode leftSubTree = newRoot;

        newRoot.left = n;
        n.right = leftSubTree;

        if (leftSubTree != null)
            leftSubTree.parent = n;

        newRoot.parent = n.parent;
        n.parent = newRoot;

        if (newRoot.parent == null) {
            root = newRoot;
        } else if (newRoot.parent.left == n) {
            newRoot.parent.left = newRoot;
        } else {
            newRoot.parent.right = newRoot;
        }
    }
}
