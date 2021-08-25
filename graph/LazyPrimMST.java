public class LazyPrimMST {
	private int weight;              // total weight of MST
	private Queue<int[]> mst;        // edges { v, u, weight } in the MST
	private boolean[] marked;        // marked[v] = true iff v on tree
	private PriorityQueue<int[]> pq; // edges { v, u, weight } with one endpoint in tree

	// Compute a minimum spanning tree (or forest) of an edge-weighted graph.
	public LazyPrimMST(List<int[]>[] adjList) {
		int n = adjList.length;

		mst = new ArrayDeque<int[]>();
		pq = new PriorityQueue<int[]>((a, b) -> Integer.compare(a[2], b[2])); // order by weight
		marked = new boolean[n];

		for (int v = 0; v < n; v++)            // run Prim from all vertices to
			if (!marked[v]) prim(adjList, v);  // get a minimum spanning forest
	}

	// run Prim's algorithm
	private void prim(List<int[]>[] adjList, int s) {
		scan(adjList, s);
		while (!pq.isEmpty()) {                      // better to stop when mst has V-1 edges
			int[] edge = pq.poll();                  // smallest edge on pq
			int v = edge[0];
			int w = edge[1];
			int edgeWeight = edge[2];

			if (marked[v] && marked[w]) continue;    // lazy, both v and w already scanned
			mst.offer(edge);                         // add edge to MST
			weight += edgeWeight;

			if (!marked[v]) scan(adjList, v);        // v becomes part of tree
			if (!marked[w]) scan(adjList, w);        // w becomes part of tree
		}
	}

	// add all edges e incident to v onto pq if the other endpoint has not yet been scanned
	private void scan(List<int[]>[] adjList, int v) {
		marked[v] = true;
		for (int[] edge : adjList[v]) {
			int u = edge[0];
			int w = edge[1];
			if (!marked[u]) {
				pq.offer(new int[] { v, u, w });
			}
		}
	}

	public Iterable<int[]> edges() {
		return mst;
	}

	public int weight() {
		return weight;
	}
}