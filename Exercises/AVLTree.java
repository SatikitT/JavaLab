package Exercises;

public class AVLTree {
    private TreeNode root;

    // Get the height of a node
    private int height(TreeNode node) {
        if (node == null) return -1;
        return Math.max(node.h_left, node.h_right) + 1;
    }

    // Update the heights of a node
    private void updateHeights(TreeNode node) {
        if (node != null) {
            node.h_left = height(node.left);
            node.h_right = height(node.right);
        }
    }

    // Get balance factor of a node
    private int getBalance(TreeNode node) {
        if (node == null) return 0;
        return node.h_left - node.h_right;
    }

    // Right rotation
    private TreeNode rotateRight(TreeNode n) {
        TreeNode newRoot = n.left;
        TreeNode rightSubtree = newRoot.right;
    
        // Perform rotation
        newRoot.right = n;
        n.left = rightSubtree;
    
        // Update parent pointers
        if (rightSubtree != null) {
            rightSubtree.parent = n;
        }
        newRoot.parent = n.parent;
        n.parent = newRoot;
    
        // Update heights
        updateHeights(n);
        updateHeights(newRoot);
    
        return newRoot; // Return the new root after rotation
    }
    

    // Left rotation
    private TreeNode rotateLeft(TreeNode n) {
        TreeNode newRoot = n.right;
        TreeNode leftSubtree = newRoot.left;
    
        // Perform rotation
        newRoot.left = n;
        n.right = leftSubtree;
    
        // Update parent pointers
        if (leftSubtree != null) {
            leftSubtree.parent = n;
        }
        newRoot.parent = n.parent;
        n.parent = newRoot;
    
        // Update heights
        updateHeights(n);
        updateHeights(newRoot);
    
        return newRoot; // Return the new root after rotation
    }

    // Insert a node
    public void insert(int key) {
        root = insert(root, null, key);
    }

    private TreeNode insert(TreeNode node, TreeNode parent, int key) {
        // Perform standard BST insertion
        if (node == null) {
            TreeNode newNode = new TreeNode(key);
            newNode.parent = parent;
            return newNode;
        }

        if (key < node.data)
            node.left = insert(node.left, node, key);
        else if (key > node.data)
            node.right = insert(node.right, node, key);
        else // Duplicate keys not allowed
            return node;

        // Update heights
        updateHeights(node);

        // Get balance factor
        int balance = getBalance(node);

        // Balance the node
        if (balance > 1 && key < node.left.data) {
            return rotateRight(node);
        }
        if (balance < -1 && key > node.right.data) {
            return rotateLeft(node);
        }
        if (balance > 1 && key > node.left.data) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (balance < -1 && key < node.right.data) {
            node.right = rotateRight(node.left);
            return rotateLeft(node);
        }

        return node; // Unchanged node pointer
    }

    // Delete a node
    public void delete(int key) {
        root = delete(root, key);
    }

    private TreeNode delete(TreeNode node, int key) {
        if (node == null) return node;

        if (key < node.data) {
            node.left = delete(node.left, key);
        } else if (key > node.data) {
            node.right = delete(node.right, key);
        } else {
            if ((node.left == null) || (node.right == null)) {
                TreeNode temp = (node.left != null) ? node.left : node.right;

                if (temp == null) {
                    node = null;
                } else {
                    temp.parent = node.parent;
                    node = temp;
                }
            } else {
                TreeNode temp = findMin(node.right);
                node.data = temp.data;
                node.right = delete(node.right, temp.data);
            }
        }

        if (node == null) return node;

        // Update heights
        updateHeights(node);

        // Balance the node
        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.left) >= 0) {
            return rotateRight(node);
        }
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (balance < -1 && getBalance(node.right) <= 0) {
            return rotateLeft(node);
        }
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    // Helper function to find the minimum node
    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Print the tree (in-order traversal)
    public void printTree() {
        printTree(root);
    }

    private void printTree(TreeNode node) {
        if (node != null) {
            printTree(node.left);
            System.out.println(node);
            printTree(node.right);
        }
    }
}
