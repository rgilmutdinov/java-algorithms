public class UF {
	private int[] root;
	private int[] rank;
	private int count;

	public UF(int n) {
		root = new int[n];
		rank = new int[n];

		for (int i = 0; i < n; i++) {
			root[i] = i;
			rank[i] = 1;
		}

		count = n;
	}

	public int find(int x) {
		if (x == root[x]) {
			return x;
		}

		return root[x] = find(root[x]); // recursive path compression
	}

	public boolean union(int x, int y) {
		int rootx = find(x);
		int rooty = find(y);

		if (rootx == rooty) {
			return false;
		}

		if (rank[rootx] < rank[rooty]) {
			root[rootx] = rooty;
		} else if (rank[rootx] > rank[rooty]) {
			root[rooty] = rootx;
		} else {
			root[rootx] = rooty;
			rank[rooty]++;
		}

		count--;
		return true;
	}

	public int count() {
		return this.count;
	}
}
