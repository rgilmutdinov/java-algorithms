class Solution {

    private final int WHITE = 0;
    private final int GRAY = 1;
    private final int BLACK = 2;

    private boolean hasCycle(List<Integer>[] adjList, int[] colors, int v) {
        colors[v] = GRAY;
        for (int u : adjList[v]) {
            if (colors[u] == GRAY) {
                return true;
            }

            if (colors[u] == WHITE && hasCycle(adjList, colors, u)) {
                return true;
            }
        }

        colors[v] = BLACK;
        return false;
    }

	public boolean hasCycle(List<Integer>[] adjList) {
		int n = adjList.length;

		int[] colors = new int[n];
		Arrays.fill(colors, WHITE);

		for (int v = 0; v < n; v++) {
			if (colors[v] != WHITE) continue;
			if (hasCycle(adjList, colors, v)) {
				return true;
			}
		}
		return false;
	}
}