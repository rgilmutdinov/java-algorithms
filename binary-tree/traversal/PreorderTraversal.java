public class Solution {
    public class PreorderTraversal {
        private void preorder(TreeNode root, List<Integer> L) {
            if (root != null) {
                L.add(root.val);
                preorder(root.left, L);
                preorder(root.right, L);
            }
        }

        public List<Integer> preorder(TreeNode root) {
            List<Integer> L = new ArrayList<>();
            preorder(root, L);
            return L;
        }
    }

    public class PreorderTraversalIterative {
        public List<Integer> preorder(TreeNode root) {
            List<Integer> L = new ArrayList<>();
            if (root == null) {
                return L;
            }

            Deque<TreeNode> s = new ArrayDeque<>();
            s.push(root);
            while (!s.isEmpty()) {
                TreeNode n = s.pop();
                L.add(n.val);
                if (n.right != null) {
                    s.push(n.right);
                }

                if (n.left != null) {
                    s.push(n.left);
                }
            }
            return L;
        }
    }
}
