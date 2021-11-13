public class Tarjan {
	private int pre; // preorder number counter
	private int count; // number of strongly-connected components

	private boolean[] visited; // visited[v] = has v been visited?
	private int[] id; // id[v] = id of strong component containing v
	private int[] low; // low[v] = low number of v

	private Deque<Integer> stack;

	public Tarjan(List<Integer>[] adjList) {
		int n = adjList.length;

		visited = new boolean[n];
		id = new int[n];
		low = new int[n];

		stack = new ArrayDeque<>();
		for (int v = 0; v < n; v++) {
			if (!visited[v]) {
				dfs(adjList, v);
			}
		}
	}

	public int count() {
		return count;
	}

	public boolean stronglyConnected(int v, int w) {
		return id[v] == id[w];
	}

	public int id(int v) {
		return id[v];
	}

	private void dfs(List<Integer>[] adjList, int v) {
		visited[v] = true;
		low[v] = pre++;
		int min = low[v];
		stack.addLast(v);
		for (int w : adjList[v]) {
			if (!visited[w]) {
				dfs(adjList, w);
			}

			if (low[w] < min) {
				min = low[w];
			}
		}

		if (min < low[v]) {
			low[v] = min;
			return;
		}

		int n = adjList.length;
		int w;
		do {
			w = stack.pollLast();
			id[w] = count;
			low[w] = n;
		} while (w != v);

		count++;
	}
}