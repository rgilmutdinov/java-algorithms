class Solution {

    private final int WHITE = 0;
    private final int GRAY = 1;
    private final int BLACK = 2;

    private List<Integer> cycle;

    public void findCycle(List<Integer>[] adjList, boolean[] marked, int[] edgeTo, int u, int v) {
        marked[v] = true;
        for (int w : adjList[v]) {
            // short circuit if cycle already found
            if (cycle != null)
                return;

            if (!marked[w]) {
                edgeTo[w] = v;
                findCycle(adjList, marked, edgeTo, v, w);
            } else if (w != u) {
                cycle = new ArrayList<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.add(x);
                }
                cycle.add(w);
                cycle.add(v);
            }
        }
    }

    public List<Integer> findCycle(List<Integer>[] adjList) {
        int n = adjList.length;
        boolean[] marked = new boolean[n];

        int[] edgeTo = new int[n];

        for (int v = 0; v < n; v++) {
            if (!marked[v]) {
                findCycle(adjList, marked, edgeTo, -1, v);
            }
        }

        return cycle != null ? cycle : new ArrayList<>();
    }
}