public class Solution {
    public class PostorderTraversal {
        private List<Integer> L = new ArrayList<>();

        public List<Integer> postorder(TreeNode root) {
            if (root == null) {
                return L;
            }

            postorder(root.left);
            postorder(root.right);
            L.add(root.val);

            return L;
        }
    }

    // Iterative Preorder Traversal: Tweak the Order of the Output
    public class PostorderTraversalStack {
        public LinkedList<Integer> postorder(TreeNode root) {
            LinkedList<Integer> L = new LinkedList<>();
            Deque<TreeNode> S = new ArrayDeque<>();

            if (root == null) {
                return L;
            }

            S.push(root);
            while (!S.isEmpty()) {
                root = S.pop();
                L.addFirst(root.val);
                if (root.left != null)
                    S.push(root.left);
                if (root.right != null)
                    S.push(root.right);
            }

            return L;
        }
    }

    public class PostorderTraversalIterative {
        public List<Integer> postorder(TreeNode root) {
            List<Integer> L = new ArrayList<>();
            if (root == null) {
                return L;
            }

            Deque<TreeNode> S = new ArrayDeque<>();
            while (!S.isEmpty() || root != null) {
                if (root != null) {
                    S.push(root);
                    root = root.left;
                } else {
                    TreeNode temp = S.peek().right;
                    if (temp == null) {
                        temp = S.pop();
                        L.add(temp.val);
                        while (!S.isEmpty() && S.peek().right == temp) {
                            temp = S.pop();
                            L.add(temp.val);
                        }
                    } else {
                        root = temp;
                    }
                }
            }

            return L;
        }

        public List<Integer> postorderTraversal1(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            Deque<TreeNode> S = new ArrayDeque<>();
            while (!S.isEmpty() || root != null) {
                // find leaf nodes
                while (root != null) {
                    S.push(root);
                    if (root.left != null) {
                        root = root.left;
                    } else {
                        root = root.right;
                    }
                }
                TreeNode node = S.pop();
                res.add(node.val);
                if (!S.isEmpty() && S.peek().left == node) {
                    root = S.peek().right;
                }
            }
            return res;
        }
    }
}
