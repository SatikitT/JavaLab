package solutions.pack10_BST;

import java.util.ArrayList;
import java.util.List;

public class BST {
    TreeNode root;

    public BST() {
        root = null;
    }

    public TreeNode getRoot() {
        return root;
    }

    public TreeNode search(int d) {
        if (root == null)
            return null;
        if (d == root.data)
            return root;

        TreeNode current = root;
        while (current != null) {
            if (current.data == d) {
                return current;
            }
            if (d < current.data)
                current = current.left;
            else {
                current = current.right;
            }
        }
        return current;
    }

    public void insert(int d) {
        if (root == null) {
            root = new TreeNode(d);
            return;
        }
        TreeNode current = root;
        while (current != null) {
            if (d < current.data) {
                if (current.left != null) {
                    current = current.left;
                } else {
                    current.left = new TreeNode(d);
                    current.left.parent = current;
                    return;
                }
            } else { // ! (d < p.data)
                if (current.right != null)
                    current = current.right;
                else {
                    current.right = new TreeNode(d);
                    current.right.parent = current;
                    return;
                }
            }
        }
    }

    public void delete(int d) {
        if (root == null)
            return; // not found

        TreeNode current = root;
        while (current != null) {
            if (d < current.data)
                current = current.left;
            else if (d > current.data)
                current = current.right;
            else { // found!! now, time to delete
                if ((current.left == null) || (current.right == null)) { // 0 or 1 child
                    TreeNode q = (current.left == null) ? current.right : current.left;
                    if (current.parent.left == current) // this current is a left child
                        current.parent.left = q;
                    else
                        current.parent.right = q;
                    if (q != null)
                        q.parent = current.parent;
                } else { // 2 children
                    TreeNode q = findMaxFrom(current.left);
                    delete(q.data);
                    if (current.parent.left == current)
                        current.parent.left = q;
                    else
                        current.parent.right = q;
                    q.left = current.left;
                    q.right = current.right;
                    q.parent = current.parent;
                }
                current = null;
            }
        }
    }

    TreeNode findMaxFrom(TreeNode q) {
        TreeNode max = null;
        max = q;
        while (q != null) {
            if (q.data > max.data) {
                max = q;
            }
            if (q.right != null){
                q = q.right;
            } else {
                q = q.left;
            }
            
        }
        return max;
    }

    public TreeNode findMax() {
        TreeNode max = null;
        TreeNode q = root;
        max = q;
        while (q != null) {
            if (q.data > max.data) {
                max = q;
            }

            if (q.right != null){
                q = q.right;
            } else {
                q = q.left;
            }
        }
        return max;
    }

    public TreeNode findMin() {
        TreeNode min = null;
        TreeNode q = root;
        min = q;
        while (q != null) {
            if (q.data < min.data) {
                min = q;
            }
            if (q.left != null){
                q = q.left;
            } else {
                q = q.right;
            }
        }
        return min;
    }

    public int height() {
        return heightRecur(root);
    }

    int heightRecur(TreeNode q) {
        if (q == null) {
            return 0;
        }
        int left = heightRecur(q.left);
        int right = heightRecur(q.right);
        if (left > right) {
            return left + 1;
        } else {
            return right + 1;
        }
    }

    public int count() {
        return countRecur(root);
    };

    int countRecur(TreeNode q) {
        if (q == null ){
            return 0;
        }

        return countRecur(q.left) + countRecur(q.right) + 1;
    }

    public double findMedian() {
        List<Integer> result = getTreeAsList(root);
        if(result.size() % 2 == 1){
            return result.get(result.size()/2);
        }
        return (result.get(result.size()/2) + result.get(result.size()/2 - 1)) / 2;
    }

    public int findRank(int d) {
        List<Integer> result = getTreeAsList(root);
        for (int i = 0; i < result.size(); i++){
            if (result.get(i) == d) return i + 1;
        }
        return -1;
    }

    // get list of node
    public List<Integer> getTreeAsList(TreeNode q) {
        List<Integer> numList = new ArrayList<>();
        if (q == null)
            return List.of();
        if (q.left != null) numList.addAll(getTreeAsList(q.left));
        numList.add(q.data);
        if (q.right != null) numList.addAll(getTreeAsList(q.right));
        return numList;
    }

    public int findClosest(int d){
        if (root.data == d) return d;
        TreeNode q = root;
        int closest = root.data;

        while (q != null) {
            if (Math.abs(q.data - d) < Math.abs(closest - d)){
                closest = q.data;
            }
            if( d <= q.data) {
                q = q.left;
            } else {
                q = q.right;
            }
        }

        return closest;
    }


    public void printInOrder() {
        printInOrderRecurse(root);
        System.out.println();
    }

    public void printPreOrder() {
        printPreOrderRecurse(root);
        System.out.println();
    }

    public void printPostOrder() {
        printPostOrderRecurse(root);
        System.out.println();
    }

    private void printInOrderRecurse(TreeNode node) {
        if (node == null)
            return;
        printInOrderRecurse(node.left);
        System.out.print(node.data + " ");
        printInOrderRecurse(node.right);
    }

    void printPreOrderRecurse(TreeNode node) {
        if (node == null)
            return;
        System.out.print(node.data + " ");
        printPreOrderRecurse(node.left);
        printPreOrderRecurse(node.right);
    }

    void printPostOrderRecurse(TreeNode node) {
        if (node == null)
            return;
        printPostOrderRecurse(node.left);
        printPostOrderRecurse(node.right);
        System.out.print(node.data + " ");
    }
}
