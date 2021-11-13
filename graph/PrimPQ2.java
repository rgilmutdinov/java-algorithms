public class Prim {
	private final int MAX = Integer.MAX_VALUE >> 1;

	private int weight;
	private List<int[]> mst;
	private boolean hasMst;

	// Time complexity: O((V + E) * log V)
	public Prim(List<int[]>[] adjList) {
		int n = adjList.length;
		int[] distTo = new int[n];
		int[] parent = new int[n];

		Arrays.fill(distTo, MAX);
		Arrays.fill(parent, -1);
		distTo[0] = 0;

		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((e1, e2) -> e1[1] - e2[1]);
		pq.offer(new int[] { 0, 0 });
		boolean[] visited = new boolean[n];

		int count = 0;
		mst = new ArrayList<>();
		while (!pq.isEmpty()) {
			int v = pq.poll()[0];
			if (visited[v])
				continue;

			visited[v] = true;
			weight += distTo[v];
			count++;

			if (parent[v] != -1) {
				mst.add(new int[] { v, parent[v] });
			}

			for (int[] edge : adjList[v]) {
				int u = edge[0];
				int w = edge[1];
				if (distTo[u] > w) {
					distTo[u] = w;
					parent[u] = v;
					pq.add(new int[] { u, distTo[u] });
				}
			}
		}

		hasMst = count == n;
	}

	public boolean hasMst() {
		return hasMst;
	}

	public List<int[]> mst() {
		return mst;
	}

	public int weight() {
		return weight;
	}
}