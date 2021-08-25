class Solution {

    private final int WHITE = 0;
    private final int GRAY = 1;
    private final int BLACK = 2;

    private int cycleIndex = 0;
    private void findCycles(List<Integer>[] adjList, int u, int p, int[] color, int[] cycle, int[] parents) {
        // already (completely) visited vertex.
        if (color[u] == BLACK) {
            return;
        }

        // seen vertex, but was not completely visited -> cycle detected.
        // backtrack based on parents to find the complete cycle.
        if (color[u] == GRAY) {
            cycleIndex++;
            int cur = p;
            cycle[cur] = cycleIndex;

            // backtrack the vertex which are
            // in the current cycle thats found
            while (cur != u) {
                cur = parents[cur];
                cycle[cur] = cycleIndex;
            }
            return;
        }

        parents[u] = p;

        // partially visited.
        color[u] = GRAY;

        // simple dfs on graph
        for (int v : adjList[u]) {
            // if it has not been visited previously
            if (v == parents[u]) {
                continue;
            }

            findCycles(adjList, v, u, color, cycle, parents);
        }

        // completely visited.
        color[u] = BLACK;
    }

    public int[] findCycles(List<Integer>[] adjList) {
        int n = adjList.length;
        int[] color = new int[n];
        int[] parents = new int[n];
        int[] cycle = new int[n]; // cycle index a vertex belongs to (0 - doesn't belong to any cycle)
        findCycles(adjList, 0, -1, color, cycle, parents);

		return cycle;
    }
}