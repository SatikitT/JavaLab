package Exercises;

public class SplayTreep {
    TreeNode root;

    public TreeNode search(int key) {
        TreeNode n = root;
        while (n != null) {
            if (key < n.data) {
                n = n.left;
            } else if (key > n.data) {
                n = n.right;
            } else {
                splay(n);
                return n;
            }
        }
        return n;
    }

    public void delete(int key) {
        TreeNode n = search(key);
        if (n == null)
            return;
        
        splay(n);
        if (n.left != null) {
            TreeNode leftSubTree = n.left;
            leftSubTree.parent = null;
            TreeNode rightSubTree = n.right;
            n.left = n.right = null;

            TreeNode max = findMax(leftSubTree);
            splay(max);

            if (rightSubTree != null) {
                max.right = rightSubTree;
                rightSubTree.parent = max;
            }
            root = max;
        } else if (n.right != null) {
            root = n.right;
            root.parent = null;
        } else {
            root = null;
        }
    }

    TreeNode findMax(TreeNode n) {
        if (n.right == null) return n;
        return findMax(n.right);
    }

    public void splay(TreeNode n) {
        while (n.parent != null) {
            if (n.parent.parent == null) {
                if (n.parent.left == n) {
                    rotateRight(n.parent);
                } else {
                    rotateLeft(n.parent);
                }
            } else if (n.parent.left == n && n.parent.parent.left == n.parent) {
                rotateRight(n.parent.parent);
                rotateRight(n.parent);
            } else if (n.parent.right == n && n.parent.parent.right == n.parent) {
                rotateLeft(n.parent.parent);
                rotateLeft(n.parent);
            } else if (n.parent.left == n && n.parent.parent.right == n.parent) {
                rotateRight(n.parent);
                rotateLeft(n.parent);
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
