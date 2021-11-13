public class AcyclicSP {
	public static final int MAX = Integer.MAX_VALUE >> 1;
	private int[] distTo; // distTo[v] = distance of shortest s->v path
	private int[] edgeTo; // edgeTo[v] = last vertex on shortest s->v path

	private boolean hasCycles = false;

	// This shortest-paths algorithm for edge-weighted DAGs uses a topological sort
	public AcyclicSP(List<int[]>[] adjList, int s) {
		int n = adjList.length;
		distTo = new int[n];
		edgeTo = new int[n];

		Arrays.fill(distTo, MAX);
		Arrays.fill(edgeTo, -1);

		distTo[s] = 0;

		List<Integer> toporder = topsortDfs(adjList);
		if (toporder == null) {
			hasCycles = true;
			return;
		}

		for (int v : toporder) {
			for (int[] edge : adjList[v]) {
				relax(v, edge[0], edge[1]);
			}
		}
	}

	private final int WHITE = 0;
	private final int GRAY = 1;
	private final int BLACK = 2;

	private boolean dfs(List<int[]>[] adjList, int[] colors, int v, LinkedList<Integer> order) {
		colors[v] = GRAY;
		for (int[] p : adjList[v]) {
			int u = p[0];
			if (colors[u] == GRAY) {
				// has cycle
				return true;
			}

			if (colors[u] == WHITE && dfs(adjList, colors, u, order)) {
				// has cycle
				return true;
			}
		}

		colors[v] = BLACK;
		order.addFirst(v);
		return false;
	}

	private List<Integer> topsortDfs(List<int[]>[] adjList) {
		int n = adjList.length;

		LinkedList<Integer> toporder = new LinkedList<>();
		int[] colors = new int[n];
		Arrays.fill(colors, WHITE);
		for (int v = 0; v < n; v++) {
			if (colors[v] != WHITE)
				continue;
			if (dfs(adjList, colors, v, toporder)) {
				return null;
			}
		}

		return toporder;
	}

	// relax edge e
	private void relax(int v, int w, int weight) {
		if (distTo[w] > distTo[v] + weight) {
			distTo[w] = distTo[v] + weight;
			edgeTo[w] = v;
		}
	}

	public int distTo(int v) {
		return distTo[v];
	}

	public boolean hasCycles() {
		return hasCycles;
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