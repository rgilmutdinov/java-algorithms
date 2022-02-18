public class SegmentTree {
	
	private SegmentTreeNode root;
	
	public SegmentTree(int[] nums) {
		root = buildTree(nums, 0, nums.length - 1);
	}
	
	public static class SegmentTreeNode {
		public int lo;
		public int hi;

		public SegmentTreeNode left;
		public SegmentTreeNode right;

		public int sum;

		public SegmentTreeNode(int lo, int hi) {
			this.lo = lo;
			this.hi = hi;

			this.left = null;
			this.right = null;
			this.sum = 0;
		}
	}

	public int sumRange(int lo, int hi) {
		return sumRange(root, lo, hi);
	}

	public void update(int pos, int val) {
		update(root, pos, val);
	}
	
	private SegmentTreeNode buildTree(int[] nums, int lo, int hi) {
		if (lo > hi) {
			return null;
		}

		SegmentTreeNode node = new SegmentTreeNode(lo, hi);
		if (lo == hi) {
			node.sum = nums[lo];
		} else {
			int mid = lo  + (hi - lo) / 2;             

			node.left = buildTree(nums, lo, mid);
			node.right = buildTree(nums, mid + 1, hi);

			node.sum = node.left.sum + node.right.sum;
		}

		return node;        
	}   

	private void update(SegmentTreeNode root, int pos, int val) {
		if (root.lo == root.hi) {
		   root.sum = val;
		} else {
			int mid = root.lo + (root.hi - root.lo) / 2;

			if (pos <= mid) {
				 update(root.left, pos, val);
			} else {
				 update(root.right, pos, val);
			}

			root.sum = root.left.sum + root.right.sum;
		}
	}    

	private int sumRange(SegmentTreeNode root, int lo, int hi) {
		if (root.hi == hi && root.lo == lo) {
			return root.sum;
		}

		int mid = root.lo + (root.hi - root.lo) / 2;

		if (hi <= mid) {
			return sumRange(root.left, lo, hi);
		} 

		if (lo >= mid + 1) {
			return sumRange(root.right, lo, hi);
		}

		return sumRange(root.right, mid + 1, hi) + sumRange(root.left, lo, mid);                
	}
} 