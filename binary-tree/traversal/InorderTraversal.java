class Solution {
    public class InorderTraversal {
        private List<Integer> L = new ArrayList<>();

        public List<Integer> inorder(TreeNode root) {
            if (root != null) {
                inorder(root.left);
                L.add(root.val);
                inorder(root.right);
            }
            return L;
        }
    }

    public class InorderTraversalIterative {
        public List<Integer> Inorder(TreeNode root) {
            List<Integer> L = new ArrayList<>();
            Deque<TreeNode> S = new ArrayDeque<>();
            while (!S.isEmpty() || root != null) {
                while (root != null) {
                    S.push(root);
                    root = root.left;
                }

                root = S.pop();
                L.add(root.val);
                root = root.right;
            }
            return L;
        }
    }
}
