package Exercises;

public class SplayTree {
    private TreeNode root;

    private static class TreeNode {
        int data;
        TreeNode left, right, parent;

        TreeNode(int data) {
            this.data = data;
            left = right = parent = null;
        }

        @Override
        public String toString() {
            return Integer.toString(data);
        }
    }

    // Rotate Right (Reused from AVL)
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

        // Update tree root if necessary
        if (newRoot.parent == null) {
            root = newRoot;
        } else if (newRoot.parent.left == n) {
            newRoot.parent.left = newRoot;
        } else {
            newRoot.parent.right = newRoot;
        }

        return newRoot;
    }

    // Rotate Left (Reused from AVL)
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

        // Update tree root if necessary
        if (newRoot.parent == null) {
            root = newRoot;
        } else if (newRoot.parent.left == n) {
            newRoot.parent.left = newRoot;
        } else {
            newRoot.parent.right = newRoot;
        }

        return newRoot;
    }

    // Splay operation to bring a node to the root
    private void splay(TreeNode x) {
        while (x.parent != null) {
            if (x.parent.parent == null) { // Zig rotation
                if (x.parent.left == x) {
                    rotateRight(x.parent);
                } else {
                    rotateLeft(x.parent);
                }
            } else if (x.parent.left == x && x.parent.parent.left == x.parent) { // Zig-Zig
                rotateRight(x.parent.parent);
                rotateRight(x.parent);
            } else if (x.parent.right == x && x.parent.parent.right == x.parent) { // Zig-Zig
                rotateLeft(x.parent.parent);
                rotateLeft(x.parent);
            } else if (x.parent.left == x && x.parent.parent.right == x.parent) { // Zig-Zag
                rotateRight(x.parent);
                rotateLeft(x.parent);
            } else { // Zig-Zag
                rotateLeft(x.parent);
                rotateRight(x.parent);
            }
        }
    }

    // Insert a node and splay it to the root
    public void insert(int key) {
        TreeNode node = root, parent = null;
        while (node != null) {
            parent = node;
            if (key < node.data) {
                node = node.left;
            } else if (key > node.data) {
                node = node.right;
            } else {
                splay(node); // Key exists, bring it to root
                return;
            }
        }
        TreeNode newNode = new TreeNode(key);
        newNode.parent = parent;
        if (parent == null) {
            root = newNode;
        } else if (key < parent.data) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        splay(newNode); // Splay new node to root
    }

    // Search for a node and splay it to the root
    public TreeNode search(int key) {
        TreeNode node = root;
        while (node != null) {
            if (key < node.data) {
                node = node.left;
            } else if (key > node.data) {
                node = node.right;
            } else {
                splay(node); // Splay the found node to root
                return node;
            }
        }
        return null; // Key not found
    }

    // Delete a node and maintain the splay property
    public void delete(int key) {
        TreeNode node = search(key);
        if (node == null) return; // Key not found, nothing to delete

        splay(node); // Bring the node to the root
        if (node.left != null) {
            TreeNode leftSubtree = node.left;
            leftSubtree.parent = null;

            if (node.right != null) {
                TreeNode rightSubtree = node.right;
                node.left = node.right = null;
                root = leftSubtree;

                TreeNode maxNode = findMax(leftSubtree);
                splay(maxNode);
                maxNode.right = rightSubtree;
                rightSubtree.parent = maxNode;
            } else {
                root = leftSubtree;
            }
        } else if (node.right != null) {
            root = node.right;
            root.parent = null;
        } else {
            root = null;
        }
    }

    // Helper function to find the maximum node in a subtree
    private TreeNode findMax(TreeNode node) {
        while (node.right != null) {
            node = node.right;
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

    void delete2(int d) {
        TreeNode node = search(d);
        if (node == null) return;

        splay(node);
        if (node.left != null) {
            TreeNode leftSubTree = node.left;
            leftSubTree.parent = null;

            if (node.right != null) {
                TreeNode rigthSubTree = node.right;

                TreeNode max = findMax(leftSubTree);
                splay(max);
                root = max;
                max.right = rigthSubTree;
                rigthSubTree.parent = max;
            } else {
                root = leftSubTree;
            }
        } else if (node.right != null) {
            root = node.right;
            root.parent = null;
        } else {
            root = null;
        }

    }
}
