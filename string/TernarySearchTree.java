// Memory usage: 64N to 64Nw (w - average length)
public class TernarySearchTree {

    class TreeNode {
        TreeNode left, middle, right;
        char ch;
        boolean complete;

        public TreeNode(char ch, boolean complete) {
            this.ch = ch;
            this.complete = complete;
        }
    }

    TreeNode root;

	// Time complexity: O(log N)
    public void insert(String str) {
        root = insert(root, str, 0);
    }

    private TreeNode insert(TreeNode node, String str, int pos) {
        if (pos >= str.length()) {
            return null;
        }
        if (node == null) {
            node = new TreeNode(str.charAt(pos), pos == str.length() - 1);
        }

        if (node.ch == str.charAt(pos)) {
            if (pos == str.length() - 1) {
                node.complete = true;
            } else {
                node.middle = insert(node.middle, str, pos + 1);
            }
        } else if (str.charAt(pos) < node.ch) {
            node.left = insert(node.left, str, pos);
        } else if (str.charAt(pos) > node.ch) {
            node.right = insert(node.right, str, pos);
        }

        return node;
    }

	// Time complexity: O(log N) - actually: 1.39 lg N
    public boolean search(String str) {
        return search(root, str, 0);
    }

    private boolean search(TreeNode node, String str, int pos) {
        if (node == null) {
            return false;
        }

        if (node.ch == str.charAt(pos)) {
            if (pos == str.length() - 1) {
                return node.complete;
            }

            return search(node.middle, str, pos + 1);
        } else if (str.charAt(pos) < node.ch) {
            return search(node.left, str, pos);
        } else if (str.charAt(pos) > node.ch) {
            return search(node.right, str, pos);
        }

        return false;
    }
}