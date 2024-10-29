package Exercises;

public class AVLTreep {
    TreeNode root;

    int height(TreeNode n) {
        if (n == null) return -1;
        return Math.max(n.h_left, n.h_right) + 1;
    }

    void updateHeights(TreeNode n) {
        if (n != null) {
            n.h_left = height(n.left);
            n.h_right = height(n.right);
        }
    }

    int getBalance(TreeNode n) {
        if (n == null) return 0;
        return n.h_left - n.h_right;
    }

    TreeNode rotateLeft(TreeNode n) {
        TreeNode newRoot = n.right;
        TreeNode leftSubTree = newRoot;

        newRoot.left = n;

        if (n.parent == null) {
            root = newRoot;
        } else if (n.parent.left == n) {
            n.parent.left = newRoot;
        } else {
            n.parent.right = newRoot;
        }

        n.parent = newRoot;
        n.right = leftSubTree;

        if (leftSubTree != null)
            leftSubTree.parent = n;

        updateHeights(n);
        updateHeights(newRoot);

        return newRoot;
    }

    TreeNode rotateRight (TreeNode n) {
        TreeNode newRoot = n.left;
        TreeNode rightSubTree = newRoot.right;

        newRoot.right = n;

        if (n.parent == null) {
            root = newRoot;
        } else if (n.parent.left == n) {
            n.parent.left = n;
        } else {
            n.parent.right = n;
        }

        n.parent = newRoot;
        n.left = rightSubTree;

        if (rightSubTree != null)
            rightSubTree.parent = n;

        updateHeights(n);
        updateHeights(newRoot);

        return newRoot;
        
    }

    void insert(int d) {
        root = insert(root, null, d);
    }

    TreeNode insert(TreeNode node, TreeNode parent, int d) {
        if (node == null) {
            TreeNode newNode = new TreeNode(d);
            newNode.parent = parent;
            return newNode;
        }

        if (d < node.data) {
            node.left = insert(node.left, node, d);
        } else if (d > node.data) {
            node.right = insert(node.right, node, d);
        } else {
            return null;
        }

        updateHeights(node);

        int balance = getBalance(node);

        if (balance > 1 && d < node.left.data) {
            return rotateLeft(node);
        } 
        if (balance < -1 && d > node.right.data) {
            return rotateRight(node);
        } 
        if (balance > 1 && d > node.left.data) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        } 
        if (balance < -1 && d < node.right.data) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    void delete(int d) {
        root = delete(root, d);
    }

    TreeNode delete(TreeNode n, int d){
        if (n == null) return n;

        if (d < n.left.data){
            n.left = delete(n.left, d);
        } else if (d > n.right.data) {
            n.right = delete(n.right, d);
        } else {
            if (n.right == null || n.left == null) {
                TreeNode q = n.right != null ? n.right : n.left;

                if (q == null) {
                    n = null;
                } else {
                    q.parent = n.parent;
                    n = q;
                }
            } else {
                TreeNode q = findMin(n.right);
                n.data = q.data;
                delete(n.right, q.data);
            }
        }

        return n;
    }

    TreeNode findMin(TreeNode n) {
        if (n.left == null) return n;
        return findMin(n.left);
    }
}
