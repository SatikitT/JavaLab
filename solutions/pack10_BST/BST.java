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
        if (root.data == d)
            return root;
        TreeNode current = root;
        while (current != null) {
            if (current.data == d) {
                return current;
            }
            if (d < current.data) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return current;
    }

    public void insert(int d) {
        TreeNode q = new TreeNode(d);
        if (root == null) {
            root = q;
            return;
        }
        TreeNode current = root;
        while (true) {
            if (d < current.data)
                if (current.left == null){
                    current.left = q;
                    q.parent = current;
                    break;
                }
                else 
                    current = current.left;
            else 
                if (current.right == null) {
                    current.right = q;
                    q.parent = current;
                    break;
                } else {
                    current = current.right;
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

    TreeNode findMax(TreeNode p) {
        if (p.right == null) return p;
        return findMax(p.right);
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
