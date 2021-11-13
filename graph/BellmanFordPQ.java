public class BellmanFordSP {
	public static final int MAX = Integer.MAX_VALUE >> 1;

	private int[] distTo; // length of path to v
	private int[] edgeTo; // last edge on path to v
	private boolean[] onQ; // Is this vertex on the queue?
	private Queue<Integer> queue; // vertices being relaxed
	private int cost; // number of calls to relax()
	private boolean negativeCycle; // negative cycle in edgeTo[]?

	/*
	 * This implementation of the Bellman-Ford algorithm uses a version of relax()
	 * that puts vertices pointed to by edges that successfully relax on a FIFO
	 * queue (avoiding duplicates) and periodically checks for a negative cycle in
	 * edgeTo[]
	 * 
	 * Time: O(V * (V + E)) Space: O(V)
	 */
	public BellmanFordSP(List<int[]>[] adjList, int s) {
		int n = adjList.length;
		distTo = new int[n];
		edgeTo = new int[n];
		onQ = new boolean[n];

		queue = new ArrayDeque<>();

		Arrays.fill(distTo, MAX);
		Arrays.fill(edgeTo, -1);

		distTo[s] = 0;

		queue.offer(s);
		onQ[s] = true;

		while (!queue.isEmpty() && !hasNegativeCycle()) {
			int v = queue.poll();
			onQ[v] = false;
			relax(adjList, v);
		}
	}

	// relax vertex v and put other endpoints on queue if changed
	private void relax(List<int[]>[] adjList, int v) {
		int n = adjList.length;
		for (int[] edge : adjList[v]) {
			int u = edge[0];
			int weight = edge[1];
			if (distTo[u] > distTo[v] + weight) {
				distTo[u] = distTo[v] + weight;
				edgeTo[u] = v;
				if (!onQ[u]) {
					queue.offer(u);
					onQ[u] = true;
				}
			}
			if (++cost % n == 0) {
				negativeCycle = true;
				return;
			}
		}
	}

	public boolean hasNegativeCycle() {
		return negativeCycle;
	}

	public int distTo(int v) {
		return distTo[v];
	}

	public boolean hasPathTo(int v) {
		return distTo[v] < MAX;
	}

	public List<Integer> pathTo(int v) {
		if (!hasPathTo(v))
			return null;
		LinkedList<Integer> path = new LinkedList<>();
		for (int w = edgeTo[v]; w != -1; w = edgeTo[w]) {
			path.addFirst(w);
		}
		return path;
	}
}